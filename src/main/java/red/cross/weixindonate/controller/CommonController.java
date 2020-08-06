package red.cross.weixindonate.controller;

import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import red.cross.weixindonate.domain.AppealRecordDO;
import red.cross.weixindonate.domain.DonateOrderDO;
import red.cross.weixindonate.entity.*;
import red.cross.weixindonate.service.AppealRecordService;
import red.cross.weixindonate.service.BannerImageService;
import red.cross.weixindonate.service.DonateOrderService;
import red.cross.weixindonate.service.UnifiedOrderService;
import red.cross.weixindonate.util.WXPayUtil;
import red.cross.weixindonate.util.WeiXinPayConstants;
import red.cross.weixindonate.util.XmlUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.*;

@RestController
public class CommonController {
    @Autowired
    AppealRecordService appealRecordService;
    @Autowired
    DonateOrderService donateOrderService;
    @Autowired
    BannerImageService bannerImageService;
    @Autowired
    UnifiedOrderService unifiedOrderService;



    @RequestMapping(path="/getInitInformation",method=RequestMethod.GET)
    public WeiXinDonateResponse<InitInformation> getInitInformation() {
        WeiXinDonateResponse<InitInformation> response=new WeiXinDonateResponse<InitInformation>();
        InitInformation information=new InitInformation();
        try {
            information.setUrlList(bannerImageService.getBannerImages());
            //information.setAppealRecordList(appealRecordService.getDeployAppealList(0, 10000));
            information.setAppealRecordList(appealRecordService.getDeployAppealAll());
        }catch (Exception e){
            response.setErrorMsg(ResultCodeMsgEnum.SQL_ERROR);
            System.out.println(new Date()+"#getInitInformation#error#"+e);
        }
        response.setData(information);
        return response;
    }

    /**
     * 申请同意下单，返回配置
     * @param donateOrderDO
     * @return
     */
    @RequestMapping(path="/unifiedOrder")
    public WeiXinDonateResponse<JsApiConfig> unifiedOrder(@RequestBody DonateOrderDO donateOrderDO,HttpServletRequest request) {
        WeiXinDonateResponse<JsApiConfig> result=new WeiXinDonateResponse<JsApiConfig>();
        try {
            Long id=System.currentTimeMillis();
            donateOrderDO.setId(id);
            donateOrderDO.setSpbillCreateIp(request.getRemoteAddr());
            donateOrderDO.setIsPaySuccess(0);
            donateOrderDO.setGmtCreate(new Date());
            donateOrderDO.setOpenId(WXPayUtil.wxgetOpenid(donateOrderDO.getOpenId()));
            donateOrderDO.setGmtModify(new Date());
            donateOrderService.insertOrder(donateOrderDO);
            UnifiedOrderParam param=new UnifiedOrderParam();
            param.setOpenid(donateOrderDO.getOpenId());
            param.setTotal_fee(donateOrderDO.getMoney());
            param.setSpbill_create_ip(donateOrderDO.getSpbillCreateIp());
            param.setOut_trade_no(String.valueOf(id));
            param.setNonce_str(String.valueOf(donateOrderDO.getAppealRecordId()));
            JsApiConfig config=unifiedOrderService.unifiedOrder(param);
            result.setData(config);
        } catch (Exception e) {
            result.setErrorMsg(e.getMessage());
            System.out.println(new Date()+"#unifiedOrder#error#"+e);
        }
        return result;
    }

//    /**
//     * 微信支付结果回调
//     * @param weiXinPayResult
//     * @return
//     */
//    @RequestMapping(path="/unifiedOrderResult")
//    public WeiXinDonateResponse<Void> weixinPayCallBack(@RequestBody  WeiXinPayResult weiXinPayResult) {
//        //收到支付结果，先判断是否成功：成功则更新DONATE_ORDER表状态，更新APPEAL_RECORD表中的捐赠信息
//        WeiXinDonateResponse<JsApiConfig> result=new WeiXinDonateResponse<JsApiConfig>();
//        try {
//            Map<String, Object> xmlResultMap=XmlUtil.xml2map(weiXinPayResult.getPayResult());
//            //支付成功
//            if(((HashMap)xmlResultMap.get("return_code")).get("return_code").equals("SUCCESS")){
//
//            }else{
//
//            }
//        } catch (DocumentException e) {
//            System.out.println(new Date()+"#unifiedOrderResult#error#"+e);
//        }
//        return null;
//    }

    @ResponseBody
    @RequestMapping("/notify")
    public ResultState notify(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println(new Date()+"#WeixinNotify#微信回调结果处理开始");
        ResultState resultState = new ResultState();
        String resXml = ""; // 反馈给微信服务器
        String notifyXml = inputStream2String(request.getInputStream(),"UTF-8");// 微信支付系统发送的数据（<![CDATA[product_001]]>格式）
        // 验证签名
        Map<String,String> returnMap=WXPayUtil.xmlToMap(notifyXml);
        System.out.println(new Date()+"#WeixinNotify#notify="+returnMap);
        if (WXPayUtil.isSignatureValid(returnMap,WeiXinPayConstants.key)) {
            if ("SUCCESS".equals(returnMap.get("return_code"))) {
                resultState.setErrcode(0);// 表示成功
                resultState.setErrmsg(returnMap.get("return_code"));
                /**** 业务逻辑  保存openid之类的****/
                DonateOrderDO orderDO=new DonateOrderDO();
                orderDO.setId(Long.valueOf(returnMap.get("out_trade_no")));
                orderDO.setWeixinPayId(returnMap.get("transaction_id"));
                orderDO.setMoney(Integer.valueOf(returnMap.get("total_fee")));
                orderDO.setIsPaySuccess(1);
                orderDO.setAppealRecordId(Long.valueOf(returnMap.get("nonce_str")));
                if(StringUtils.isEmpty(donateOrderService.getOrderById(orderDO.getId()).getWeixinPayId())){
                    donateOrderService.updateDonateOrder(orderDO);
                    AppealRecordDO appealRecordDO=new AppealRecordDO();
                    appealRecordDO.setCurrentMoney(orderDO.getMoney());
                    appealRecordDO.setId(orderDO.getAppealRecordId());
                    appealRecordDO.setDonatorNum(1);
                    appealRecordService.updateDeployPayResult(appealRecordDO);
                    appealRecordService.updateDraftPayResult(appealRecordDO);
                }
                // 通知微信.异步确认成功.必写.不然会一直通知后台.八次之后就认为交易失败了
                resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>" + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
            } else {
                resultState.setErrcode(-1);// 支付失败
                resultState.setErrmsg(returnMap.get("return_code"));
                resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>" + "<return_msg><![CDATA[" +returnMap.get("return_msg") + "]]></return_msg>" + "</xml> ";
            }
        } else {
            resultState.setErrcode(-1);// 支付失败
            resultState.setErrmsg("签名验证错误");
            resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>" + "<return_msg><![CDATA[签名验证错误]]></return_msg>" + "</xml> ";
        }
        resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>" + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
        BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
        out.write(resXml.getBytes());
        out.flush();
        out.close();
        System.out.println(new Date()+"#WeixinNotify#微信回调结果处理成功结束");
        return resultState;
    }

    /**
     * InputStream流转换成String字符串
     * @param inStream InputStream流
     * @param encoding 编码格式
     * @return String字符串
     */
    public static String inputStream2String(InputStream inStream, String encoding){
        String result = null;
        try {
            if(inStream != null){
                ByteArrayOutputStream outStream = new ByteArrayOutputStream();
                byte[] tempBytes = new byte[1024];
                int count = -1;
                while((count = inStream.read(tempBytes, 0, 1024)) != -1){
                    outStream.write(tempBytes, 0, count);
                }
                tempBytes = null;
                outStream.flush();
                result = new String(outStream.toByteArray(), encoding);
            }
        } catch (Exception e) {
            result = null;
        }
        return result;
    }

}
