package red.cross.weixindonate.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import red.cross.weixindonate.dao.AppealRecordDao;
import red.cross.weixindonate.domain.AppealRecordDO;
import red.cross.weixindonate.domain.PageDO;
import red.cross.weixindonate.domain.SortDO;
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
        PageDO pageDO = new PageDO();
        pageDO.setIndex(index);
        pageDO.setSize(size);
        return appealRecordDao.getDeployAppealList(pageDO);
    }

    @Override
    public List<AppealRecordDO> getDeployAppealAll() {
        return appealRecordDao.getDeployAppealAll();
    }

    @Override
    public List<AppealRecordDO> getDraftAppealList(int index, int size) {
        PageDO pageDO = new PageDO();
        pageDO.setIndex(index);
        pageDO.setSize(size);
        return appealRecordDao.getDraftAppealList(pageDO);
    }

    @Override
    public List<AppealRecordDO> getDraftAppealAll() {
        return appealRecordDao.getDraftAppealAll();
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
        SortDO sortDO = new SortDO();
        sortDO.setId(id);
        sortDO.setSortValue(sortValue);
        return appealRecordDao.setAppealDeployRecordTop(sortDO) * appealRecordDao.setAppealDraftRecordTop(sortDO);
    }

    @Override
    public Integer unsetAppealRecordTop(Long id) {
        SortDO sortDO = new SortDO();
        sortDO.setId(id);
        sortDO.setSortValue((long) (10000000000000L - id));
        return appealRecordDao.unsetAppealDeployRecordTop(sortDO) * appealRecordDao.unsetAppealDraftRecordTop(sortDO);
    }

}
