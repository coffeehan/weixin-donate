package red.cross.weixindonate.service;

import red.cross.weixindonate.domain.AppealRecordDO;
import red.cross.weixindonate.domain.DonateOrderDO;
import red.cross.weixindonate.entity.OrderQueryCondition;
import red.cross.weixindonate.entity.TotalStatisticsResult;

import java.util.List;

public interface DonateOrderService {
    Integer insertOrder(DonateOrderDO donateOrderDO);
    DonateOrderDO getOrderById(Long id);

    List<DonateOrderDO> getDonateOrderList(int start, int end);

    /**
     * 根据姓名+手机号后四位查询订单信息
     * @return
     */
    List<DonateOrderDO> getOrderByCondition(OrderQueryCondition condition);

    /**
     * 更新微信支付结果
     * @param donateOrderDO
     * @return
     */
    Integer updateDonateOrder(DonateOrderDO donateOrderDO);

    /**
     * 总捐助额，总人数
     * @return
     */
    TotalStatisticsResult getTotalStatisticsData();
}
