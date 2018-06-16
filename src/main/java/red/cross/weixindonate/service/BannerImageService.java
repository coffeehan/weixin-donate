package red.cross.weixindonate.service;

import red.cross.weixindonate.domain.BannerImageDO;

import java.io.IOException;
import java.util.List;

public interface BannerImageService {
    List<String> getBannerImages();

    List<BannerImageDO> getBannerImageDOs();

    Integer deleteBannerImageById(long id);

    Integer insertBannerImage(BannerImageDO bannerImageDO);

    void saveImage(String imageStr,String path) throws IOException;
}
