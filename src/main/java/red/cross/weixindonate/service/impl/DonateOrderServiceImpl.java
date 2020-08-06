package red.cross.weixindonate.service.impl;

import org.springframework.stereotype.Service;
import red.cross.weixindonate.dao.DonateOrderDao;
import red.cross.weixindonate.domain.DonateOrderDO;
import red.cross.weixindonate.domain.PageDO;
import red.cross.weixindonate.entity.OrderQueryCondition;
import red.cross.weixindonate.entity.TotalStatisticsResult;
import red.cross.weixindonate.service.DonateOrderService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class DonateOrderServiceImpl implements DonateOrderService {
    @Autowired
    DonateOrderDao donateOrderDao;
    @Override
    public Integer insertOrder(DonateOrderDO donateOrderDO) {
        return donateOrderDao.insertOrder(donateOrderDO);
    }

    @Override
    public DonateOrderDO getOrderById(Long id) {
        return donateOrderDao.getOrderById(id);
    }

    @Override
    public List<DonateOrderDO> getOrderByCondition(OrderQueryCondition condition) {
        return donateOrderDao.getOrderByCondition(condition);
    }

    @Override
    public Integer updateDonateOrder(DonateOrderDO donateOrderDO) {
        return donateOrderDao.updatePayResult(donateOrderDO);
    }

    @Override
    public TotalStatisticsResult getTotalStatisticsData(){
        TotalStatisticsResult result=new TotalStatisticsResult();
        if(donateOrderDao.getTotalMoney()==null){
            result.setTotalMoney(0);
        }else{
            result.setTotalMoney(donateOrderDao.getTotalMoney());
        }
        if(donateOrderDao.getTotalDonator()==null){
            result.setTotalDonator(0);
        }else{
            result.setTotalDonator(donateOrderDao.getTotalDonator());
        }
        return result;
    }

    @Override
    public List<DonateOrderDO> getDonateOrderList(int index, int size) {
        PageDO pageDO = new PageDO();
        pageDO.setIndex(index);
        pageDO.setSize(size);
        return donateOrderDao.getDonateOrderList(pageDO);
    }

    @Override
    public List<DonateOrderDO> getDonateOrderAll() {
        return donateOrderDao.getDonateOrderAll();
    }
}
