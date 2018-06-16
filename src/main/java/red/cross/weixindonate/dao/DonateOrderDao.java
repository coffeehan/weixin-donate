package red.cross.weixindonate.dao;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import red.cross.weixindonate.domain.AppealRecordDO;
import red.cross.weixindonate.domain.DonateOrderDO;
import org.apache.ibatis.annotations.Insert;
import red.cross.weixindonate.entity.OrderQueryCondition;

import java.util.List;

public interface DonateOrderDao {
    @Insert("insert into DONATE_ORDER(id,name,appeal_record_Id,weixin_Pay_Id,appeal_Record_title,money,mobile,\n" +
            "donate_Type,is_Need_Invoice,invoice_Header,invoice_Name,invoice_Mobile,invoice_Address,\n" +
            "remark,is_Disclosure,is_Pay_Success,open_Id,spbill_Create_Ip,gmt_Create,gmt_Modify)values(#{id},#{name},\n" +
            "#{appealRecordId},#{ weixinPayId},#{appealRecordTitle},#{money},#{mobile},\n" +
            "#{donateType},#{isNeedInvoice},#{invoiceHeader},#{invoiceName},#{invoiceMobile},#{invoiceAddress},\n" +
            "#{remark},#{isDisclosure},#{isPaySuccess},#{openId},#{spbillCreateIp},#{gmtCreate},\n" +
            "#{gmtModify})")
    Integer insertOrder(DonateOrderDO donateOrderDO);

    @Select("select * from DONATE_ORDER where id=#{id}")
    DonateOrderDO getOrderById(Long id);
    /**
     * 根据姓名+手机号后四位查询订单信息
     * @return
     */
    @Select("select * from DONATE_ORDER  where name=#{name} and mobile like '%${number}%' and IS_PAY_SUCCESS=1")
    List<DonateOrderDO> getOrderByCondition(OrderQueryCondition condition);

    /**
     * 更新微信支付结果
     * @param donateOrderDO
     * @return
     */
    @Update("update DONATE_ORDER set WEIXIN_PAY_ID=#{weixinPayId},IS_PAY_SUCCESS=#{isPaySuccess} where id=#{id}")
    Integer updatePayResult(DonateOrderDO donateOrderDO);

    /**
     * 更新项目的当前金额和捐赠人数
     * @return
     */
    Integer updateDonateMoneyAndPersonNum();

    @Select(" select sum(money) from DONATE_ORDER where IS_PAY_SUCCESS=1;")
    Integer getTotalMoney();

    @Select(" select count(money) from DONATE_ORDER where IS_PAY_SUCCESS=1;")
    Integer getTotalDonator();

    @Select("select * from DONATE_ORDER where IS_PAY_SUCCESS=1 limit #{0},#{1}")
    List<DonateOrderDO> getDonateOrderList(int index, int size);
}
