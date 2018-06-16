package red.cross.weixindonate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import red.cross.weixindonate.domain.BannerImageDO;
import red.cross.weixindonate.entity.ResultCodeMsgEnum;
import red.cross.weixindonate.entity.WeiXinDonateResponse;
import red.cross.weixindonate.service.BannerImageService;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;

@RestController
public class BannerImageController {
    @Autowired
    BannerImageService bannerImageService;

    String IMAGE_PATH="/usr/local/weixin-donate/images/";

    @RequestMapping(path = "/insertBannerImage")
    public WeiXinDonateResponse<String> insertBannerImage(@RequestBody BannerImageDO bannerImageDO,HttpServletRequest request) {
        WeiXinDonateResponse<String> response = new WeiXinDonateResponse<String>();
        if (bannerImageDO.getImageUrl() == null && bannerImageDO.getImageData() == null) {
            response.setErrorMsg("图片信息为空");
            return response;
        }
        if (null != bannerImageDO.getImageData()) {
            String imageName=System.currentTimeMillis()+".jpg";
            String imagePath=IMAGE_PATH+imageName;
            try {
                bannerImageService.saveImage(bannerImageDO.getImageData(),imagePath);
                System.out.println(new Date()+"#insertBannerImage#imagePath="+imagePath);
                bannerImageDO.setImageUrl("http://47.92.75.66/"+imageName);
                response.setData(imagePath);
            } catch (IOException e) {
                System.out.println(new Date()+"#insertBannerImage#error#"+e);
                response.setErrorMsg("保存图片失败");
                return response;
            }
        }
        try {
            bannerImageService.insertBannerImage(bannerImageDO);
        } catch (Exception e) {
            System.out.println(new Date()+"#insertBannerImage#error#"+e);
            response.setErrorMsg(ResultCodeMsgEnum.SQL_ERROR);
            return response;
        }
        return response;
    }

    @RequestMapping(path = "/deleteBannerImageById")
    public WeiXinDonateResponse<Integer> deleteBannerImageById(long id) {
        WeiXinDonateResponse<Integer> response = new WeiXinDonateResponse<Integer>();
        try {
            response.setData(bannerImageService.deleteBannerImageById(id));
        } catch (Exception e) {
            response.setErrorMsg(ResultCodeMsgEnum.SQL_ERROR);
            System.out.println(new Date()+"#deleteBannerImageById#error#"+e.getMessage());
        }
        return response;
    }
}
