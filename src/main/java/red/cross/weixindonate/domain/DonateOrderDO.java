package red.cross.weixindonate.domain;

import java.io.Serializable;
import java.util.Date;

public class DonateOrderDO implements Serializable {

    private long id;//id
    private String name;//捐款人姓名/单位名
    private long appealRecordId;//求助项目id
    private String weixinPayId;//微信订单号
    private String appealRecordTitle;//求助项目标题
    private Integer money;//捐助金额
    private String mobile;//捐款人手机号
    private Date donateTime;//捐款时间
    private Integer donateType;//捐款类型(0-个人；1-单位)
    private Integer isNeedInvoice;//是否需要发票(0-不需要；1-需要)
    private String invoiceHeader;//发票抬头
    private String invoiceName;//发票联系人
    private String invoiceMobile;//发票联系电话
    private String invoiceAddress;//发票联系地址
    private String remark;//备注
    private Integer isDisclosure;//是否公开(0-公开;1-不公开)
    private Integer isPaySuccess;//是否支付成功(0-失败,1-成功)
    private String  openId;//关注用户在商户的ID
    private String  spbillCreateIp;//客户端IP
    private Date gmtCreate;//记录创建时间
    private Date gmtModify;//最近修改时间

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getAppealRecordId() {
        return appealRecordId;
    }

    public void setAppealRecordId(long appealRecordId) {
        this.appealRecordId = appealRecordId;
    }

    public String getAppealRecordTitle() {
        return appealRecordTitle;
    }

    public void setAppealRecordTitle(String appealRecordTitle) {
        this.appealRecordTitle = appealRecordTitle;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Date getDonateTime() {
        return donateTime;
    }

    public void setDonateTime(Date donateTime) {
        this.donateTime = donateTime;
    }

    public Integer getDonateType() {
        return donateType;
    }

    public void setDonateType(Integer donateType) {
        this.donateType = donateType;
    }

    public Integer getIsNeedInvoice() {
        return isNeedInvoice;
    }

    public void setIsNeedInvoice(Integer isNeedInvoice) {
        this.isNeedInvoice = isNeedInvoice;
    }

    public String getInvoiceHeader() {
        return invoiceHeader;
    }

    public void setInvoiceHeader(String invoiceHeader) {
        this.invoiceHeader = invoiceHeader;
    }

    public String getInvoiceName() {
        return invoiceName;
    }

    public void setInvoiceName(String invoiceName) {
        this.invoiceName = invoiceName;
    }

    public String getInvoiceMobile() {
        return invoiceMobile;
    }

    public void setInvoiceMobile(String invoiceMobile) {
        this.invoiceMobile = invoiceMobile;
    }

    public String getInvoiceAddress() {
        return invoiceAddress;
    }

    public void setInvoiceAddress(String invoiceAddress) {
        this.invoiceAddress = invoiceAddress;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getIsDisclosure() {
        return isDisclosure;
    }

    public void setIsDisclosure(Integer isDisclosure) {
        this.isDisclosure = isDisclosure;
    }

    public String getWeixinPayId() {
        return weixinPayId;
    }

    public void setWeixinPayId(String weixinPayId) {
        this.weixinPayId = weixinPayId;
    }

    public Integer getIsPaySuccess() {
        return isPaySuccess;
    }

    public void setIsPaySuccess(Integer isPaySuccess) {
        this.isPaySuccess = isPaySuccess;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModify() {
        return gmtModify;
    }

    public void setGmtModify(Date gmtModify) {
        this.gmtModify = gmtModify;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getSpbillCreateIp() {
        return spbillCreateIp;
    }

    public void setSpbillCreateIp(String spbillCreateIp) {
        this.spbillCreateIp = spbillCreateIp;
    }


}
