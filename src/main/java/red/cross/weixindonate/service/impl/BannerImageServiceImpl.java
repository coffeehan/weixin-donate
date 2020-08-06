package red.cross.weixindonate.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import red.cross.weixindonate.dao.BannerImageDao;
import red.cross.weixindonate.domain.BannerImageDO;
import red.cross.weixindonate.service.BannerImageService;
import sun.misc.BASE64Decoder;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
@Service
public class BannerImageServiceImpl implements BannerImageService {
    @Autowired
    BannerImageDao bannerImageDao;

    String localImagePath="/svelldata/website/pthh.svell.cn/app/images";

    @Override
    public List<String> getBannerImages() {
        return bannerImageDao.getBannerImages();
    }

    @Override
    public List<BannerImageDO> getBannerImageDOs(){
        return bannerImageDao.getBannerImageDOs();
    }

    @Override
    public Integer deleteBannerImageById(long id) {
        return bannerImageDao.deleteBannerImageById(id);
    }

    @Override
    public Integer insertBannerImage(BannerImageDO bannerImageDO) {
        return bannerImageDao.insertBannerImage(bannerImageDO);
    }

    @Override
    public void saveImage(String imageStr, String path) throws IOException {
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] b = decoder.decodeBuffer(imageStr.split(",")[1]);
        for (int i = 0; i < b.length; ++i) {
            if (b[i] < 0) {
                b[i] += 256;
            }
        }
        FileOutputStream fout = new FileOutputStream(path);
        //将字节写入文件
        fout.write(b);
        fout.flush();
        fout.close();
    }

}
