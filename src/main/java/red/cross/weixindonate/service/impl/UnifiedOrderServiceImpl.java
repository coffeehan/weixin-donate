package red.cross.weixindonate.service.impl;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.naming.NoNameCoder;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import org.dom4j.DocumentException;
import org.springframework.stereotype.Service;
import red.cross.weixindonate.entity.JsApiConfig;
import red.cross.weixindonate.entity.UnifiedOrderParam;
import red.cross.weixindonate.service.UnifiedOrderService;
import red.cross.weixindonate.util.WXPayUtil;
import red.cross.weixindonate.util.WeiXinPayConstants;
import red.cross.weixindonate.util.XmlUtil;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class UnifiedOrderServiceImpl implements UnifiedOrderService {
    @Override
    public JsApiConfig unifiedOrder(UnifiedOrderParam param) throws Exception {
        String prepayId=httpRequestPost(formatXmlData(param));
        JsApiConfig config = new JsApiConfig();

        String timestamp = Long.toString(System.currentTimeMillis() / 1000);
        String packageName = "prepay_id="+prepayId;
        StringBuffer sign = new StringBuffer();
        sign.append("appId=").append(WeiXinPayConstants.appid);
        sign.append("&nonceStr=").append(param.getNonce_str());
        sign.append("&package=").append(packageName);
        sign.append("&signType=").append("MD5");
        sign.append("&timeStamp=").append(timestamp);
        sign.append("&key=").append(WeiXinPayConstants.key);
        String signature = WXPayUtil.MD5(sign.toString());

        config.setAppId(WeiXinPayConstants.appid);
        config.setNonce(param.getNonce_str());
        config.setTimestamp(timestamp);
        config.setPackageName(packageName);
        config.setSignature(signature);
        config.setOrderId(param.getOut_trade_no());
        return config;
    }

    private String formatXmlData(UnifiedOrderParam param) throws Exception {
        param.setAppid(WeiXinPayConstants.appid);
        param.setBody(WeiXinPayConstants.body);
        param.setDevice_info(WeiXinPayConstants.device_info);
        param.setMch_id(WeiXinPayConstants.mch_id);
        param.setNotify_url(WeiXinPayConstants.notify_url);
        param.setTrade_type(WeiXinPayConstants.trade_type);
        param.setSign(getMD5Sign(param));
        XStream stream = new XStream(new XppDriver(new NoNameCoder()) {
            @Override
            public HierarchicalStreamWriter createWriter(Writer out) {
                return new PrettyPrintWriter(out) {
                    // 对所有xml节点的转换都增加CDATA标记
                    boolean cdata = true;

                    @Override
                    @SuppressWarnings("rawtypes")
                    public void startNode(String name, Class clazz) {
                        super.startNode(name, clazz);
                    }

                    @Override
                    public String encodeNode(String name) {
                        return name;
                    }

                    @Override
                    protected void writeText(QuickWriter writer, String text) {
                        if (cdata) {
                            writer.write("<![CDATA[");
                            writer.write(text);
                            writer.write("]]>");
                        } else {
                            writer.write(text);
                        }
                    }
                };
            }
        });
        stream.alias("xml", param.getClass());
        String xml = stream.toXML(param);
        System.out.println(xml);
        return xml;
    }

    private String getMD5Sign(UnifiedOrderParam param) throws Exception {
        StringBuffer sign = new StringBuffer();
        sign.append("appid=").append(WeiXinPayConstants.appid);
        sign.append("&body=").append(WeiXinPayConstants.body);
        sign.append("&device_info=").append(WeiXinPayConstants.device_info);
        sign.append("&mch_id=").append(WeiXinPayConstants.mch_id);
        sign.append("&nonce_str=").append(param.getNonce_str());
        sign.append("&notify_url=").append(WeiXinPayConstants.notify_url);
        sign.append("&openid=").append(param.getOpenid());
        sign.append("&out_trade_no=").append(param.getOut_trade_no());
        sign.append("&spbill_create_ip=").append(param.getSpbill_create_ip());
        sign.append("&total_fee=").append(param.getTotal_fee());
        sign.append("&trade_type=").append(WeiXinPayConstants.trade_type);
        sign.append("&key=").append(WeiXinPayConstants.key);
        return WXPayUtil.MD5(sign.toString());
    }

    private String httpRequestPost(String param) throws Exception {
        String UTF8 = "UTF-8";
        URL httpUrl = new URL(WeiXinPayConstants.UNIFIED_ORDER_URL);
        HttpURLConnection httpURLConnection = (HttpURLConnection) httpUrl.openConnection();
        httpURLConnection.setRequestProperty("Host", "api.mch.weixin.qq.com");
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setConnectTimeout(10 * 1000);
        httpURLConnection.setReadTimeout(10 * 1000);
        httpURLConnection.connect();
        OutputStream outputStream = httpURLConnection.getOutputStream();
        outputStream.write(param.getBytes(UTF8));

        //获取内容
        InputStream inputStream = httpURLConnection.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, UTF8));
        final StringBuffer stringBuffer = new StringBuffer();
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            stringBuffer.append(line);
        }
        String resp = stringBuffer.toString();
        Map<String, Object> responseMap = XmlUtil.xml2map(resp);
        System.out.println(new Date()+"#unifiedOrder#param="+param+",resp="+responseMap);
        if (stringBuffer != null) {
            bufferedReader.close();
        }
        if (inputStream != null) {
            inputStream.close();
        }
        if (outputStream != null) {
            outputStream.close();
        }
        if( ((String) ((HashMap)responseMap.get("return_code")).get("return_code")).contains("FAIL")){
            throw new Exception((String) ((HashMap)responseMap.get("return_msg")).get("return_msg"));
        }
        return (String)((HashMap)responseMap.get("prepay_id")).get("prepay_id");
    }
}
