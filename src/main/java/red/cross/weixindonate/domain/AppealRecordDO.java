package red.cross.weixindonate.domain;

import java.util.Date;

/**
 * 发布救助信息数据对象
 */
public class AppealRecordDO {
    private Long id;//id
    private String title;//标题
    private String familyDesc;//家庭情况
    private String patientDesc;//患者情况
    private String imageUrl;//图片路径，多张图片以,号分隔
    private String videoUrl;//视频路径，多张图片以,号分隔
    private String name;//求助者姓名
    private Integer sex;//求助者性别(0-男,1-女)
    private Integer age;//求助者年龄
    private String disease;//病种
    private String mobile;//手机号码
    private Integer targetMoney;//目标金额
    private Date deployTime;//发布时间
    private String deployDepartment;//发布单位
    private Integer status;//项目状态(0-未发布；1-进行中；2-已结束)
    private Integer currentMoney;//已筹金额
    private Integer donatorNum;//捐款人数
    private String   projectFollowUp  ;//项目跟进
    private Long sortValue;//排序值
    private Boolean isTop;//是否置顶
    private Date gmtCreate;//记录创建时间
    private Date gmtModify;//最近修改时间

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getTargetMoney() {
        return targetMoney;
    }

    public void setTargetMoney(Integer targetMoney) {
        this.targetMoney = targetMoney;
    }

    public Date getDeployTime() {
        return deployTime;
    }

    public void setDeployTime(Date deployTime) {
        this.deployTime = deployTime;
    }

    public String getDeployDepartment() {
        return deployDepartment;
    }

    public void setDeployDepartment(String deployDepartment) {
        this.deployDepartment = deployDepartment;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getCurrentMoney() {
        return currentMoney;
    }

    public void setCurrentMoney(Integer currentMoney) {
        this.currentMoney = currentMoney;
    }

    public Integer getDonatorNum() {
        return donatorNum;
    }

    public void setDonatorNum(Integer donatorNum) {
        this.donatorNum = donatorNum;
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

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getFamilyDesc() {
        return familyDesc;
    }

    public void setFamilyDesc(String familyDesc) {
        this.familyDesc = familyDesc;
    }

    public String getPatientDesc() {
        return patientDesc;
    }

    public void setPatientDesc(String patientDesc) {
        this.patientDesc = patientDesc;
    }

    public String getProjectFollowUp() {
        return projectFollowUp;
    }

    public void setProjectFollowUp(String projectFollowUp) {
        this.projectFollowUp = projectFollowUp;
    }

    public Long getSortValue() {
        return sortValue;
    }

    public void setSortValue(Long sortValue) {
        this.sortValue = sortValue;
    }

    public Boolean getTop() {
        return isTop;
    }

    public void setTop(Boolean top) {
        isTop = top;
    }
}
