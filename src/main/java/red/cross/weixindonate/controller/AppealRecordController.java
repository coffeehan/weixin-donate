package red.cross.weixindonate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import red.cross.weixindonate.domain.AppealRecordDO;
import red.cross.weixindonate.entity.ResultCodeMsgEnum;
import red.cross.weixindonate.entity.WeiXinDonateResponse;
import red.cross.weixindonate.service.AppealRecordService;
import red.cross.weixindonate.service.BannerImageService;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@RestController
public class AppealRecordController {
    @Autowired
    AppealRecordService appealRecordService;

    String IMAGE_PATH="/usr/local/weixin-donate/images/";

    @Autowired
    BannerImageService bannerImageService;

    @RequestMapping(path = "/getDeployAppealById", method = RequestMethod.GET)
    public WeiXinDonateResponse<AppealRecordDO> getDeployAppealById(Long id) {
        WeiXinDonateResponse<AppealRecordDO> response = new WeiXinDonateResponse<AppealRecordDO>();
        try {
            response.setData(appealRecordService.getDeployAppealById(id));
        } catch (Exception e) {
            response.setErrorMsg(ResultCodeMsgEnum.SQL_ERROR);
            System.out.println(new Date()+"#getDeployAppealById#error#"+e.getMessage());
        }
        return response;
    }

    @RequestMapping(path = "/deleteAppealRecordById")
    public WeiXinDonateResponse<Integer> deleteAppealRecordById(Long id) {
        WeiXinDonateResponse<Integer> response = new WeiXinDonateResponse<Integer>();
        try {
            response.setData(appealRecordService.deleteDeployAppealById(id)*appealRecordService.deleteDraftAppealById(id));
        } catch (Exception e) {
            response.setErrorMsg(ResultCodeMsgEnum.SQL_ERROR);
            System.out.println(new Date()+"#deleteDeployAppealById#error#"+e.getMessage());
        }
        return response;
    }

    @RequestMapping(path = "/getDrftAppealById", method = RequestMethod.GET)
    public WeiXinDonateResponse<AppealRecordDO> getDrftAppealById(Long id) {
        WeiXinDonateResponse<AppealRecordDO> response = new WeiXinDonateResponse<AppealRecordDO>();
        try {
            response.setData(appealRecordService.getDraftAppealById(id));
        } catch (Exception e) {
            response.setErrorMsg(ResultCodeMsgEnum.SQL_ERROR);
            System.out.println(new Date()+"#getDrftAppealById#error#"+e.getMessage());
        }
        return response;
    }

    @RequestMapping(path = "/getDeployAppealList", method = RequestMethod.GET)
    public WeiXinDonateResponse<List<AppealRecordDO>> getAppealInfo(String index, String size) {
        WeiXinDonateResponse<List<AppealRecordDO>> response = new WeiXinDonateResponse<List<AppealRecordDO>>();
        try {
            response.setData(appealRecordService.getDeployAppealList(Integer.valueOf(index), Integer.valueOf(size)));
        } catch (Exception e) {
            response.setErrorMsg(ResultCodeMsgEnum.SQL_ERROR);
            System.out.println(new Date()+"#getDeployAppealList#error#"+e.getMessage());
        }
        return response;
    }

    @RequestMapping(path = "/getDrftAppealList", method = RequestMethod.GET)
    public WeiXinDonateResponse<List<AppealRecordDO>> getDrftAppealList(String index, String size) {
        WeiXinDonateResponse<List<AppealRecordDO>> response = new WeiXinDonateResponse<List<AppealRecordDO>>();
        try {
            response.setData(appealRecordService.getDraftAppealList(Integer.valueOf(index), Integer.valueOf(size)));
        } catch (Exception e) {
            response.setErrorMsg(ResultCodeMsgEnum.SQL_ERROR);
            System.out.println(new Date()+"#getDrftAppealList#error#"+e.getMessage());
        }
        return response;
    }

    //编辑保存：draft --UPDATE --INSERT  如果状态为已发布，需要同步更新发布表
    @RequestMapping(path = "/saveAppealRecordNotDeploy", method = RequestMethod.POST)
    public WeiXinDonateResponse<Void> saveAppealRecordNotDeploy(@RequestBody AppealRecordDO appealRecordDO) {
        WeiXinDonateResponse<Void> response = new WeiXinDonateResponse<Void>();
        appealRecordDO.setGmtModify(new Date());
        try {
            if(appealRecordDO.getImageUrl()!=null&&!appealRecordDO.getImageUrl().contains("http://47.92.75.66/")){
                appealRecordDO.setImageUrl(getImageUrl(appealRecordDO.getImageUrl()));
            }
            if (appealRecordDO.getId() != null) {
                appealRecordService.updateAppealDrafInfo(appealRecordDO);
                if(appealRecordDO.getStatus()!=0){
                    appealRecordService.updateAppealDeployInfo(appealRecordDO);
                }
            } else {
                appealRecordDO.setStatus(0);
                appealRecordDO.setId(System.currentTimeMillis());
                appealRecordDO.setSortValue(10000000000000L-appealRecordDO.getId());
                appealRecordDO.setGmtCreate(new Date());
                appealRecordService.saveAppealDrafInfo(appealRecordDO);
            }
        } catch (Exception e) {
            response.setErrorMsg(ResultCodeMsgEnum.SQL_ERROR);
            System.out.println(new Date()+"#saveAppealRecordNotDeploy#error#"+e.getMessage());
        }
        return response;
    }

    //编辑发布：id为null:draft insert,deploy insert; id不为null,draft update,deploy 若库中有数据则UPDATE,没有则insert
    @RequestMapping(path = "/saveAppealRecordDeploy", method = RequestMethod.POST)
    public WeiXinDonateResponse<Void> saveAppealRecordDeploy(@RequestBody AppealRecordDO appealRecordDO) {
        WeiXinDonateResponse<Void> response = new WeiXinDonateResponse<Void>();
        appealRecordDO.setGmtModify(new Date());
        appealRecordDO.setStatus(1);
        try {
            if(appealRecordDO.getImageUrl()!=null&&!appealRecordDO.getImageUrl().contains("http://47.92.75.66/")){
                appealRecordDO.setImageUrl(getImageUrl(appealRecordDO.getImageUrl()));
            }
            if (appealRecordDO.getId() != null) {
                appealRecordService.updateAppealDrafInfo(appealRecordDO);
                AppealRecordDO tmp=appealRecordService.getDeployAppealById(appealRecordDO.getId());
                if(tmp==null){
                    appealRecordDO.setGmtCreate(new Date());
                    appealRecordDO.setDeployTime(new Date());
                    appealRecordService.saveAppealDeployInfo(appealRecordDO);
                }else{
                    appealRecordService.updateAppealDeployInfo(appealRecordDO);
                }

            } else {
                appealRecordDO.setId(System.currentTimeMillis());
                appealRecordDO.setSortValue(10000000000000L-appealRecordDO.getId());
                appealRecordDO.setGmtCreate(new Date());
                appealRecordDO.setDeployTime(new Date());
                appealRecordService.saveAppealDrafInfo(appealRecordDO);
                appealRecordService.saveAppealDeployInfo(appealRecordDO);
            }
        } catch (Exception e) {
            response.setErrorMsg(ResultCodeMsgEnum.SQL_ERROR);
            System.out.println(new Date()+"#saveAppealRecordDeploy#error#"+e.getMessage());
        }
        return response;
    }

    @RequestMapping(path = "/undeployAppealRecord", method = RequestMethod.GET)
    public WeiXinDonateResponse<Integer> getAppealInfo(Long id) {

        WeiXinDonateResponse<Integer> response = new WeiXinDonateResponse<Integer>();
        try {
            response.setData(appealRecordService.undeployAppealRecord(id));
        } catch (Exception e) {
            response.setErrorMsg(ResultCodeMsgEnum.SQL_ERROR);
            System.out.println(new Date()+"#undeployAppealRecord#error#"+e.getMessage());
        }
        return response;
    }

    private  String getImageUrl(String imageData) throws Exception {
        String imageName=System.currentTimeMillis()+".jpg";
        String imagePath=IMAGE_PATH+imageName;
        try {
            bannerImageService.saveImage(imageData,imagePath);
            System.out.println(new Date()+"#insertBannerImage#imagePath="+imagePath);
            return "http://47.92.75.66/"+imageName;
        } catch (IOException e) {
            throw new Exception("图片保存失败");
        }
    }

    @RequestMapping(path = "/setAppealRecordTop")
    public WeiXinDonateResponse<Integer> setAppealRecordTop(Long id) {

        WeiXinDonateResponse<Integer> response = new WeiXinDonateResponse<Integer>();
        try {
            response.setData(appealRecordService.setAppealRecordTop(id,System.currentTimeMillis()*100));
        } catch (Exception e) {
            response.setErrorMsg(ResultCodeMsgEnum.SQL_ERROR);
            System.out.println(new Date()+"#setAppealRecordTop#error#"+e.getMessage());
        }
        return response;
    }

    @RequestMapping(path = "/unsetAppealRecordTop")
    public WeiXinDonateResponse<Integer> unsetAppealRecordTop(Long id) {

        WeiXinDonateResponse<Integer> response = new WeiXinDonateResponse<Integer>();
        try {
            response.setData(appealRecordService.unsetAppealRecordTop(id));
        } catch (Exception e) {
            response.setErrorMsg(ResultCodeMsgEnum.SQL_ERROR);
            System.out.println(new Date()+"#unsetAppealRecordTop#error#"+e.getMessage());
        }
        return response;
    }
}
