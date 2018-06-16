package red.cross.weixindonate.service;

import red.cross.weixindonate.domain.AppealRecordDO;
import red.cross.weixindonate.entity.TotalStatisticsResult;

import java.util.List;

public interface AppealRecordService {
    Integer deleteDeployAppealById(long id);

    Integer deleteDraftAppealById(long id);

    List<AppealRecordDO> getDeployAppealList(int start, int end);

    List<AppealRecordDO> getDraftAppealList(int index, int size);

    Integer saveAppealDrafInfo(AppealRecordDO appealRecordDO);

    Integer saveAppealDeployInfo(AppealRecordDO appealRecordDO);

    Integer updateAppealDrafInfo(AppealRecordDO appealRecordDO);

    Integer updateAppealDeployInfo(AppealRecordDO appealRecordDO);

    AppealRecordDO getDeployAppealById(long id);

    AppealRecordDO getDraftAppealById(long id);

    //取消发布
    Integer undeployAppealRecord(long id);

    Integer updateDeployPayResult(AppealRecordDO appealRecordDO);

    Integer updateDraftPayResult(AppealRecordDO appealRecordDO);

    Integer setAppealRecordTop(Long id,Long sortValue);

    Integer unsetAppealRecordTop(Long id);
}
