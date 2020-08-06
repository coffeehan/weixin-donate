package red.cross.weixindonate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import red.cross.weixindonate.domain.DonateOrderDO;
import red.cross.weixindonate.entity.OrderQueryCondition;
import red.cross.weixindonate.entity.ResultCodeMsgEnum;
import red.cross.weixindonate.entity.TotalStatisticsResult;
import red.cross.weixindonate.entity.WeiXinDonateResponse;
import red.cross.weixindonate.service.BannerImageService;
import red.cross.weixindonate.service.DonateOrderService;

import java.util.Date;
import java.util.List;
@RestController
public class DonateOrderController {
    @Autowired
    DonateOrderService donateOrderService;

    @Autowired
    BannerImageService bannerImageService;

    @RequestMapping(path="/getOrderByCondition",method=RequestMethod.GET)
    public WeiXinDonateResponse<List<DonateOrderDO>> getOrderByCondition(String name,String mobile){
        WeiXinDonateResponse<List<DonateOrderDO>> response=new WeiXinDonateResponse<List<DonateOrderDO>>();
        OrderQueryCondition condition=new OrderQueryCondition();
        condition.setName(name);
        condition.setNumber(mobile);
        try {
            response.setData(donateOrderService.getOrderByCondition(condition));
        } catch (Exception e) {
            response.setErrorMsg(ResultCodeMsgEnum.SQL_ERROR);
            System.out.println(new Date()+"#getOrderByCondition#error#"+e);
        }
        return response;
    }

    @RequestMapping(path="/getTotalStatisticsData",method=RequestMethod.GET)
    public WeiXinDonateResponse<TotalStatisticsResult> getTotalStatisticsData(){
        WeiXinDonateResponse<TotalStatisticsResult> response=new WeiXinDonateResponse<TotalStatisticsResult>();
        try {
            TotalStatisticsResult result= donateOrderService.getTotalStatisticsData()  ;
            result.setBannerList(bannerImageService.getBannerImageDOs());
            response.setData(result);
        } catch (Exception e) {
            response.setErrorMsg(ResultCodeMsgEnum.SQL_ERROR);
            System.out.println(new Date()+"#getTotalStatisticsData#error#"+e);
        }
        return response;
    }

    @RequestMapping(path="/getDonateOrderList",method=RequestMethod.GET)
    public WeiXinDonateResponse<List<DonateOrderDO>> getDonateOrderList(String index, String size){
        WeiXinDonateResponse<List<DonateOrderDO>> response=new WeiXinDonateResponse<List<DonateOrderDO>>();
        try {
            //response.setData(donateOrderService.getDonateOrderList(Integer.valueOf(index), Integer.valueOf(size)));
            response.setData(donateOrderService.getDonateOrderAll());
        } catch (Exception e) {
            response.setErrorMsg(ResultCodeMsgEnum.SQL_ERROR);
            System.out.println(new Date()+"#getDonateOrderList#error#"+e);
        }
        return response;
    }

    @RequestMapping(path="/getOrderById",method=RequestMethod.GET)
    public WeiXinDonateResponse<DonateOrderDO> getOrderById(String id){
        WeiXinDonateResponse<DonateOrderDO> response=new WeiXinDonateResponse<DonateOrderDO>();
        try {
            response.setData(donateOrderService.getOrderById(Long.valueOf(id)));
        } catch (Exception e) {
            response.setErrorMsg(ResultCodeMsgEnum.SQL_ERROR);
            System.out.println(new Date()+"#getDonateOrderList#error#"+e);
        }
        return response;
    }

}
