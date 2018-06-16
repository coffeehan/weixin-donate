package red.cross.weixindonate.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import red.cross.weixindonate.domain.BannerImageDO;

import java.util.List;

public interface BannerImageDao {
    @Select("select image_url from BANNER_IMAGE order by id desc limit 5")
    List<String> getBannerImages();

    @Select("select * from BANNER_IMAGE order by id desc limit 5")
    List<BannerImageDO> getBannerImageDOs();

    @Select("Insert into BANNER_IMAGE(image_url) values(#{imageUrl})")
    Integer insertBannerImage(BannerImageDO bannerImageDO);

    @Delete("delete from BANNER_IMAGE where id=#{id}")
    Integer deleteBannerImageById(long id);
}
