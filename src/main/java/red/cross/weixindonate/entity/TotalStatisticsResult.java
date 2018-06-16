package red.cross.weixindonate.entity;

import red.cross.weixindonate.domain.BannerImageDO;

import java.io.Serializable;
import java.util.List;

public class TotalStatisticsResult implements Serializable {
    private int totalMoney;
    private int totalDonator;
    private List<BannerImageDO> bannerList;//最近的图片列表

    public int getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(int totalMoney) {
        this.totalMoney = totalMoney;
    }

    public int getTotalDonator() {
        return totalDonator;
    }

    public void setTotalDonator(int totalDonator) {
        this.totalDonator = totalDonator;
    }

    public List<BannerImageDO> getBannerList() {
        return bannerList;
    }

    public void setBannerList(List<BannerImageDO> bannerList) {
        this.bannerList = bannerList;
    }
}
