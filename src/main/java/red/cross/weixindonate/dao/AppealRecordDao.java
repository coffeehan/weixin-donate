package red.cross.weixindonate.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import red.cross.weixindonate.domain.AppealRecordDO;

import java.util.List;

public interface AppealRecordDao {
    @Select("select * from APPEAL_DEPLOY_RECORD order by sort_value desc limit #{0},#{1}")
    List<AppealRecordDO> getDeployAppealList(int index, int size);

    @Select("select * from APPEAL_DEPLOY_RECORD where id=#{id}")
    AppealRecordDO getDeployAppealById(long id);

    @Select("select * from APPEAL_RECORD order by sort_value desc limit #{0},#{1}")
    List<AppealRecordDO> getDraftAppealList(int index, int size);

    @Select("select * from APPEAL_RECORD where id=#{id}")
    AppealRecordDO getDraftAppealById(long id);

    @Delete("delete from APPEAL_DEPLOY_RECORD where id=#{id}")
    Integer deleteDeployAppealById(long id);

    @Delete("delete from APPEAL_RECORD where id=#{id}")
    Integer deleteDraftAppealById(long id);

    @Insert("insert into APPEAL_RECORD(id,title,family_desc,patient_desc,image_url,video_url,name,sex,age,disease,mobile,target_money,deploy_time,deploy_department,status,sort_value,current_money,donator_num,gmt_create,gmt_modify) values(#{id},#{title},#{familyDesc},#{patientDesc},#{imageUrl},#{videoUrl},#{name},#{sex},#{age},#{disease},#{mobile},#{targetMoney},#{deployTime},#{deployDepartment},#{status},#{sortValue},#{currentMoney},#{donatorNum},#{gmtCreate},#{gmtModify})")
    Integer saveAppealDrafInfo(AppealRecordDO appealRecordDO);

    @Insert("insert into APPEAL_DEPLOY_RECORD(id,title,family_desc,patient_desc,image_url,video_url,name,sex,age,disease,mobile,target_money,deploy_time,deploy_department,status,sort_value,current_money,donator_num,gmt_create,gmt_modify) values(#{id},#{title},#{familyDesc},#{patientDesc},#{imageUrl},#{videoUrl},#{name},#{sex},#{age},#{disease},#{mobile},#{targetMoney},#{deployTime},#{deployDepartment},#{status},#{sortValue},#{currentMoney},#{donatorNum},#{gmtCreate},#{gmtModify})")
    Integer saveAppealDeployInfo(AppealRecordDO appealRecordDO);

    @Delete("update APPEAL_DEPLOY_RECORD set status=2 where id=#{id}")
    Integer unDeployAppealDeployRecord(long id);

    @Update("update APPEAL_RECORD set status=2 where id=#{id}")
    Integer unDeployAppealDraftRecord(long id);

    //更新支付金额和支付总人数
    @Update("update APPEAL_DEPLOY_RECORD set current_money=current_money+#{currentMoney} ,donator_num=donator_num+#{donatorNum} where id=#{id}")
    Integer updateDeployPayResult(AppealRecordDO appealRecordDO);

    @Update("update APPEAL_RECORD set current_money=current_money+#{currentMoney} ,donator_num=donator_num+#{donatorNum} where id=#{id}")
    Integer updateDraftPayResult(AppealRecordDO appealRecordDO);

    @Update("update APPEAL_RECORD " +
            " set " +
            "        title=#{title}," +
            "        family_desc=#{familyDesc}," +
            "        patient_desc=#{patientDesc}," +
            "        image_url=#{imageUrl}," +
            "        video_url=#{videoUrl}," +
            "        name=#{name}," +
            "        sex=#{sex}," +
            "        age=#{age}," +
            "        disease=#{disease}," +
            "        mobile=#{mobile}," +
            "        target_money=#{targetMoney}," +
            "        deploy_department=#{deployDepartment}," +
            "        status=#{status}," +
            "        current_money=#{currentMoney}," +
            "        donator_num=#{donatorNum}," +
            "        project_follow_up=#{projectFollowUp}," +
            "        gmt_modify=#{gmtModify}" +
            "where id=#{id}")
    Integer updateAppealDrafInfo(AppealRecordDO appealRecordDO);

    @Update("update APPEAL_DEPLOY_RECORD" +
            " set" +
            "        title = #{title}," +
            "        family_desc=#{familyDesc}," +
            "        patient_desc=#{patientDesc}," +
            "        image_url=#{imageUrl}," +
            "        video_url=#{videoUrl}," +
            "        name=#{name}," +
            "        sex=#{sex}," +
            "        age=#{age}," +
            "        disease=#{disease}," +
            "        mobile=#{mobile}," +
            "        target_money=#{targetMoney}," +
            "        deploy_department=#{deployDepartment}," +
            "        status=#{status}," +
            "        current_money=#{currentMoney}," +
            "        donator_num=#{donatorNum}," +
            "        project_follow_up=#{projectFollowUp}," +
            "        gmt_modify=#{gmtModify}" +
            "where id=#{id}")
    Integer updateAppealDeployInfo(AppealRecordDO appealRecordDO);

    @Update("update APPEAL_DEPLOY_RECORD set sort_value=#{1},is_top=1 where id=#{0}")
    Integer setAppealDeployRecordTop(Long id,long sortValue );

    @Update("update APPEAL_RECORD set sort_value=#{1} ,is_top=1 where id=#{0}")
    Integer setAppealDraftRecordTop(Long id,long sortValue );

    @Update("update APPEAL_DEPLOY_RECORD set sort_value=#{1} ,is_top=0 where id=#{0}")
    Integer unsetAppealDeployRecordTop(Long id,long sortValue );

    @Update("update APPEAL_RECORD set sort_value=#{1} ,is_top=0 where id=#{0}")
    Integer unsetAppealDraftRecordTop(Long id,long sortValue);
}
