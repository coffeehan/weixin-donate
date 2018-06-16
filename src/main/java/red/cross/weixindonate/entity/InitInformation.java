package red.cross.weixindonate.entity;

import red.cross.weixindonate.domain.AppealRecordDO;

import java.util.List;

public class InitInformation {
    private List<String> urlList;
    private List<AppealRecordDO> appealRecordList;

    public List<String> getUrlList() {
        return urlList;
    }

    public void setUrlList(List<String> urlList) {
        this.urlList = urlList;
    }

    public List<AppealRecordDO> getAppealRecordList() {
        return appealRecordList;
    }

    public void setAppealRecordList(List<AppealRecordDO> appealRecordList) {
        this.appealRecordList = appealRecordList;
    }
}
