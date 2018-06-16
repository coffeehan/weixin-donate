package red.cross.weixindonate.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import red.cross.weixindonate.dao.AppealRecordDao;
import red.cross.weixindonate.domain.AppealRecordDO;
import red.cross.weixindonate.service.AppealRecordService;

import java.util.List;

@Service
public class AppealRecordServiceImpl implements AppealRecordService {
    @Autowired
    AppealRecordDao appealRecordDao;

    @Override
    public Integer deleteDeployAppealById(long id) {
        return appealRecordDao.deleteDeployAppealById(id);
    }

    @Override
    public Integer deleteDraftAppealById(long id) {
        return appealRecordDao.deleteDraftAppealById(id);
    }

    @Override
    public List<AppealRecordDO> getDeployAppealList(int index, int size) {
        return appealRecordDao.getDeployAppealList(index, size);
    }

    @Override
    public List<AppealRecordDO> getDraftAppealList(int index, int size) {
        return appealRecordDao.getDraftAppealList(index, size);
    }

    @Override
    public Integer saveAppealDrafInfo(AppealRecordDO appealRecordDO) {
        return appealRecordDao.saveAppealDrafInfo(appealRecordDO);
    }

    @Override
    public Integer saveAppealDeployInfo(AppealRecordDO appealRecordDO) {
        return appealRecordDao.saveAppealDeployInfo(appealRecordDO);
    }

    @Override
    public Integer updateAppealDrafInfo(AppealRecordDO appealRecordDO) {
        return appealRecordDao.updateAppealDrafInfo(appealRecordDO);
    }

    @Override
    public Integer updateAppealDeployInfo(AppealRecordDO appealRecordDO) {
        return appealRecordDao.updateAppealDeployInfo(appealRecordDO);
    }

    @Override
    public AppealRecordDO getDeployAppealById(long id) {
        return appealRecordDao.getDeployAppealById(id);
    }

    @Override
    public AppealRecordDO getDraftAppealById(long id) {
        return appealRecordDao.getDraftAppealById(id);
    }

    @Override
    public Integer undeployAppealRecord(long id) {
        return appealRecordDao.unDeployAppealDraftRecord(id) * appealRecordDao.unDeployAppealDeployRecord(id);
    }

    @Override
    public Integer updateDeployPayResult(AppealRecordDO appealRecordDO) {
        return appealRecordDao.updateDeployPayResult(appealRecordDO);
    }

    @Override
    public Integer updateDraftPayResult(AppealRecordDO appealRecordDO) {
        return appealRecordDao.updateDraftPayResult(appealRecordDO);
    }

    @Override
    public Integer setAppealRecordTop(Long id, Long sortValue) {
        return appealRecordDao.setAppealDeployRecordTop(id,sortValue)*appealRecordDao.setAppealDraftRecordTop(id,sortValue);
    }

    @Override
    public Integer unsetAppealRecordTop(Long id) {
        return appealRecordDao.unsetAppealDeployRecordTop(id,(long)(10000000000000L-id))*appealRecordDao.unsetAppealDraftRecordTop(id,(long)(10000000000000L-id));
    }

}
