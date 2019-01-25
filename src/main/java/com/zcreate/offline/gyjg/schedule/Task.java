package com.zcreate.offline.gyjg.schedule;

import com.zcreate.offline.gyjg.entity.DataComputeLog;
import com.zcreate.offline.gyjg.oracletomysql.service.Transfer;
import com.zcreate.offline.gyjg.oracletomysql.service.mysql.impl.DataComputeLogService;
import com.zcreate.offline.gyjg.redistohive.RedisToHiveService;
import com.zcreate.offline.gyjg.redistohive.utils.DateUtil;
import com.zcreate.offline.gyjg.shell.ShellCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @ClassName: Task
 * @Author: majun
 * @CreateDate: 2019/1/2 11:17
 * @Version: 1.0
 * @Description: TODO
 */

@Component
public class Task {

    Logger logger = LoggerFactory.getLogger(Task.class);

//    hive.import.exam.ks.result=sqoop import --connect "jdbc:oracle:thin:@172.16.17.82:1521:gyjg" --username zckj --password ZCKJ2018 -m 1 --target-dir /user/admin/gyjg/temp/time_paration_exam_ks_result_temp --delete-target-dir --fields-terminated-by "\t" --lines-terminated-by "\n" --split-by XH --where "1=1" --query "SELECT result.*, to_char(result.JSSJ,'yyyy-MM-dd') as day, to_char(result.JSSJ,'yyyy-MM') as month FROM GYJG.VM_JG_EXAM_KS_RESULT result where result.JSSJ >= to_date(sysdate - 365) AND result.JSSJ <= to_date(sysdate) OR $CONDITIONS"
//    hive.load.exam.ks.result= hive -f  /root/gyjg/everyday/import/load_exam_ks_result.sql
//
//    hive.import.schoolinfo=sqoop import --connect "jdbc:oracle:thin:@172.16.17.82:1521:gyjg" --username zckj --password ZCKJ2018 -m 1 --target-dir /user/admin/gyjg/temp/v_jg_drv_schoolinfo --delete-target-dir --fields-terminated-by "\t"--lines-terminated-by "\n" --split-by xh --where "1=1" --query "SELECT * FROM GYJG.VM_JG_EXAM_SCHOOLINFO WHERE $CONDITIONS"
//    hive.load.schoolinfo= hive -f  /root/gyjg/everyday/import/load_schoolinfo.sql
//
//    hive.import.perasign=sqoop import --connect "jdbc:oracle:thin:@172.16.17.82:1521:gyjg" --username zckj --password ZCKJ2018 -m 1 --target-dir /user/admin/gyjg/temp/exam_drv_perasign_temp --delete-target-dir --fields-terminated-by "\t" --lines-terminated-by "\n" --split-by XH --where "1=1" --query "SELECT result.*, to_char(result.ykrq,'yyyy-MM-dd') as day, result.dlr as jxdm FROM GYJG.VM_JG_EXAM_DRV_PREASIGN result where $CONDITIONS"
//    hive.load.perasign=hive -f /root/gyjg/everyday/import/load_exam_preasign.sql
//
//    hive.select.m_absent_examinee= hive|-f|/var/lib/hadoop-hdfs/gyjg/every_day/select/m_absent_examinee.sql
//    hive.select.exam_mid_result= hive|-f|/var/lib/hadoop-hdfs/gyjg/every_day/select/exam_mid_result.sql
//    hive.select.exam_mid_perasign= hive|-f|/var/lib/hadoop-hdfs/gyjg/every_day/select/exam_mid_perasign.sql
//    hive.select.t_examroom_exam_statistics= hive|-f|/var/lib/hadoop-hdfs/gyjg/every_day/select/t_examroom_exam_statistics.sql
//    hive.select.m_station_statistics= hive|-f|/var/lib/hadoop-hdfs/gyjg/every_day/select/m_station_statistics.sql
//    hive.select.m_mutil_examinee= hive|-f|/var/lib/hadoop-hdfs/gyjg/every_day/select/m_mutil_examinee.sql
//    hive.select.school_mid_count= hive|-f|/var/lib/hadoop-hdfs/gyjg/every_day/select/school_mid_count.sql
//    hive.select.m_driving_school_total= hive|-f|/var/lib/hadoop-hdfs/gyjg/every_day/select/m_driving_school_total.sql
//#hive.select.m_driving_school_statistics= hive|-f|/var/lib/hadoop-hdfs/gyjg/every_day/select/m_driving_school_statistics.sql
//
//    hive.select.graduate_mid_num= hive|-f|/var/lib/hadoop-hdfs/gyjg/every_day/select/graduate_mid_num.sql
//    hive.select.m_apply_statistics= hive|-f|/var/lib/hadoop-hdfs/gyjg/every_day/select/m_apply_statistics.sql
//    hive.select.m_driving_school_earliest= hive|-f|/var/lib/hadoop-hdfs/gyjg/every_day/select/m_driving_school_earliest.sql
//    hive.select.m_driving_school_total_day= hive|-f|/var/lib/hadoop-hdfs/gyjg/every_day/select/m_driving_school_total_day.sql
//    hive.select.m_driving_school_total_month= hive|-f|/var/lib/hadoop-hdfs/gyjg/every_day/select/m_driving_school_total_month.sql
//    hive.select.m_driving_school_total_year= hive|-f|/var/lib/hadoop-hdfs/gyjg/every_day/select/m_driving_school_total_year.sql
//    hive.select.students_num_change= hive|-f|/var/lib/hadoop-hdfs/gyjg/every_day/select/students_num_change.sql
//
//
//
//    sqoop.export.t_examroom_exam_statistics=sqoop|export|--connect|"jdbc:mysql://172.16.25.13:3306/fmsmp"|--username|zcsupervise|--password|zcsupervise|-m|1|--export-dir|/user/hive/warehouse/gyjg.db/t_examroom_exam_statistics/|--fields-terminated-by|"\\t"|--lines-terminated-by|"\\n"|--table|t_examroom_exam_statistics|--columns|"examroom_code, examroom_date, subscribe_count, exam_count, pass_count, pass_rate, missing_count, absent_rate, manual_audit_count, manual_audit_rate, update_time"
//    sqoop.export.m_absent_examinee=sqoop|export|--connect|"jdbc:mysql://172.16.25.13:3306/fmsmp"|--username|zcsupervise|--password|zcsupervise|-m|1|--export-dir|/user/hive/warehouse/gyjg.db/m_absent_examinee/|--fields-terminated-by|"\\t"|--lines-terminated-by|"\\n"|--table|m_absent_examinee|--columns|"examinee_name, identity_code, examroom_code, exam_date, update_time"
//    sqoop.export.m_station_statistics=sqoop|export|--connect|"jdbc:mysql://172.16.25.13:3306/fmsmp"|--username|zcsupervise|--password|zcsupervise|-m|1|--export-dir|/user/hive/warehouse/gyjg.db/m_station_statistics/|--fields-terminated-by|"\\t"|--lines-terminated-by|"\\n"|--table|m_station_statistics|--columns|"examroom_code, station_code, exam_date, exam_count, pass_count, station_pass_rate, update_time"
//    sqoop.export.m_mutil_examinee=sqoop|export|--connect|"jdbc:mysql://172.16.25.13:3306/fmsmp"|--username|zcsupervise|--password|zcsupervise|-m|1|--export-dir|/user/hive/warehouse/gyjg.db/m_mutil_examinee/|--fields-terminated-by|"\\t"|--lines-terminated-by|"\\n"|--table|m_mutil_examinee|--columns|"examinee_name, identity_card, examroom_code, update_time"
//    sqoop.export.m_driving_school_total= sqoop|export|--connect|"jdbc:mysql://172.16.25.13:3306/fmsmp"|--username|zcsupervise|--password|zcsupervise|-m|1|--export-dir|/user/hive/warehouse/gyjg.db/m_driving_school_total/|--fields-terminated-by|"\\t"|--lines-terminated-by|"\\n"|--table|m_driving_school_total|--columns|"school_count, in_school_count, graduate_count, graduate_date, pass_rate, update_time"
//            #sqoop.export.m_driving_school_statistics=sqoop|export|--connect|"jdbc:mysql://172.16.25.13:3306/fmsmp"|--username|zcsupervise|--password|zcsupervise|-m|1|--export-dir|/user/hive/warehouse/gyjg.db/m_driving_school_statistics/|--fields-terminated-by|"\\t"|--lines-terminated-by|"\\n"|--table|m_driving_school_statistics|--columns|"school_code, school_name, in_school_sdudent, in_school_rate, graduate_count, graduate_date, graduate_rate, sub_one_rate, sub_one_rank, sub_two_rate, sub_two_rank, sub_three_rate, sub_three_rank, sub_four_rate, sub_four_rank, one_pass_rate, extra_pass_rate, unpass_rate,|update_time"
//    sqoop.export.m_apply_statistics=sqoop|export|--connect|"jdbc:mysql://172.16.25.13:3306/fmsmp"|--username|zcsupervise|--password|zcsupervise|-m|1|--export-dir|/user/hive/warehouse/gyjg.db/m_apply_statistics/|--fields-terminated-by|"\\t"|--lines-terminated-by|"\\n"|--table|m_apply_statistics|--columns|"school_code, applyer_count, apply_date, update_time"
//    sqoop.export.m_driving_school_earliest=sqoop|export|--connect|"jdbc:mysql://172.16.25.13:3306/fmsmp"|--username|zcsupervise|--password|zcsupervise|-m|1|--export-dir|/user/hive/warehouse/gyjg.db/|m_driving_school_earliest/|--fields-terminated-by|"\\t"|--lines-terminated-by|"\\n"|--table|m_driving_school_earliest|--columns|"school_code, in_school_count, pass_rate, sub_one_rank, one_pass_rate, extra_pass_rate, unpass_rate, update_time"
//    sqoop.export.m_driving_school_total_day=sqoop|export|--connect|"jdbc:mysql://172.16.25.13:3306/fmsmp"|--username|zcsupervise|--password|zcsupervise|-m|1|--export-dir|/user/hive/warehouse/gyjg.db/m_driving_school_total_day/|--fields-terminated-by|"\\t"|--lines-terminated-by|"\\n"|--table|m_driving_school_total_day|--columns|"school_code, school_name, in_school_count, graduate_count, graduate_date, pass_rate, update_time"
//    sqoop.export.m_driving_school_month=sqoop|export|--connect|"jdbc:mysql://172.16.25.13:3306/fmsmp"|--username|zcsupervise|--password|zcsupervise|-m|1|--export-dir|/user/hive/warehouse/gyjg.db/m_driving_school_month/|--fields-terminated-by|"\\t"|--lines-terminated-by|"\\n"|--table|m_driving_school_month|--columns|"school_code, school_name, pass_rate, sub_one_rank, one_pass_rate, extra_pass_rate, unpass_rate, month, update_time"
//    sqoop.export.m_driving_school_year=sqoop|export|--connect|"jdbc:mysql://172.16.25.13:3306/fmsmp"|--username|zcsupervise|--password|zcsupervise|-m|1|--export-dir|/user/hive/warehouse/gyjg.db/m_driving_school_year/|--fields-terminated-by|"\\t"|--lines-terminated-by|"\\n"|--table|m_driving_school_year|--columns|"school_code, school_name, pass_rate, sub_one_rank, one_pass_rate, extra_pass_rate, unpass_rate, year, update_time"




    @Value("${hive.import.exam.ks.result}")
    private String importExamKsResult;
    @Value("${hive.load.exam.ks.result}")
    private String loadExamKsResult;

    @Value("${hive.import.schoolinfo}")
    private String importSchoolinfo;
    @Value("${hive.load.schoolinfo}")
    private String loadSchoolinfo;

    @Value("${hive.import.perasign}")
    private String importPerasign;
    @Value("${hive.load.perasign}")
    private String loadPerasign;

    @Value("${hive.select.m_absent_examinee}")
    private String selectMAbsentExaminee;
    @Value("${hive.select.exam_mid_result}")
    private String selectExamMidResult;
    @Value("${hive.select.exam_mid_perasign}")
    private String selectExamMidPerasign;
    @Value("${hive.select.t_examroom_exam_statistics}")
    private String selectTExamroomExamStatistics;
    @Value("${hive.select.m_station_statistics}")
    private String selectMStationStatistics;
    @Value("${hive.select.m_mutil_examinee}")
    private String selectMMutilExaminee;
    @Value("${hive.select.school_mid_count}")
    private String selectSchoolMidCount;
    @Value("${hive.select.m_driving_school_total}")
    private String selectMDrivingSchoolTotal;
//    @Value("${hive.select.m_driving_school_statistics}")
//    private String selectMDrivingSchoolStatistics;
    @Value("${hive.select.graduate_mid_num}")
    private String selectGraduateMidNum;
    @Value("${hive.select.m_apply_statistics}")
    private String selectMApplyStatictics;
    @Value("${hive.select.m_driving_school_earliest}")
    private String selectMDrivingSchoolEarliest;
    @Value("${hive.select.m_driving_school_total_day}")
    private String selectMDrivingSchoolTotalDay;
    @Value("${hive.select.m_driving_school_total_month}")
    private String selectMDrivingSchoolTotalMonth;
    @Value("${hive.select.m_driving_school_total_year}")
    private String selectMDrivingSchoolTotalYear;
    @Value("${hive.select.students_num_change}")
    private String selectStudentsNumChange;


    @Value("${sqoop.export.t_examroom_exam_statistics}")
    private String exportTExamroomExamStatistics;
    @Value("${sqoop.export.m_absent_examinee}")
    private String exportMAbsentExaminee;
    @Value("${sqoop.export.m_station_statistics}")
    private String exportMStationStatistics;
    @Value("${sqoop.export.m_mutil_examinee}")
    private String exportMMutilExaminee;
    @Value("${sqoop.export.m_driving_school_total}")
    private String exportMDrivingSchoolTotal;
//    @Value("${sqoop.export.m_driving_school_statistics}")
//    private String exportMDrivingSchoolStatistics;
    @Value("${sqoop.export.m_apply_statistics}")
    private String exportMApplyStatistics;
    @Value("${sqoop.export.m_driving_school_earliest}")
    private String exportMDrivingSchoolEarliest;
    @Value("${sqoop.export.m_driving_school_total_day}")
    private String exportMDrivingSchoolTotalDay;
    @Value("${sqoop.export.m_driving_school_month}")
    private String exportMDrivingSchoolMonth;
    @Value("${sqoop.export.m_driving_school_year}")
    private String exportMDrivingSchoolYear;



    @Autowired
    private RedisToHiveService redisToHiveService;
    @Autowired
    private Transfer transfer;
    @Autowired
    private DataComputeLogService dataComputeLogService;

    @Scheduled(cron = "0 30 6 * * *")
    public void doTask() {
        logger.info("starting redis to hive");
        if (redisToHiveService.doImport() != 1) {
            logger.info("redis_to_hive fail");
            return;
        }
        logger.info("succeed redis to hive");

        logger.info("starting oracle to mysql");
        if (transfer.trant() != 1) {
            logger.info("oracle to mysql fail");
            return;
        }
        logger.info("succeed oracle to mysql");

        if (!doShell(importExamKsResult, "import exam_ks_result")) {
            return;
        }

        if (!doShell(loadExamKsResult, "load exam_ks_result")) {
            return;
        }

        if (!doShell(importSchoolinfo, "import schoolinfo")) {
            return;
        }

        if (!doShell(loadSchoolinfo, "load schoolinfo")) {
            return;
        }

        if (!doShell(importPerasign, "import perasign")) {
            return;
        }
        if (!doShell(loadPerasign, "load perasign")) {
            return;
        }

        if (!doShell(selectMAbsentExaminee, "select m_absent_examinee")) {
            return;
        }

        if (!doShell(selectExamMidPerasign, "select exam_mid_perasing")) {
            return;
        }

        if (!doShell(selectExamMidResult, "select exam_mid_result")) {
            return;
        }

        if (!doShell(selectTExamroomExamStatistics, "select t_examroot_statistics")) {
            return;
        }

        if (!doShell(selectMStationStatistics, "select m_station_statistics")) {
            return;
        }

        if (!doShell(selectMMutilExaminee, "select m_mutil_examinee")) {
            return;
        }

        if (!doShell(selectSchoolMidCount, "select school_mid_count")) {
            return;
        }

//        if (!doShell(selectMDrivingSchoolStatistics, "select m_driving_school_statistics")) {
//            return;
//        }

        if(!doShell(selectStudentsNumChange, "select students_num_change")){
            return;
        }
        if(!doShell(selectGraduateMidNum, "select graduate_mid_num")){
            return;
        }
        if(!doShell(selectMApplyStatictics, "select m_apply_statistics")){
            return;
        }
        if (!doShell(selectMDrivingSchoolTotal, "select m_driving_school_total")) {
            return;
        }
        if(!doShell(selectMDrivingSchoolEarliest, "select m_driving_school_earliest")){
            return;
        }
        if(!doShell(selectMDrivingSchoolTotalDay, "select m_driving_school_total_day")){
            return;
        }
        if(!doShell(selectMDrivingSchoolTotalMonth, "select m_driving_school_total_month")){
            return;
        }
        if(!doShell(selectMDrivingSchoolTotalYear, "select m_driving_school_total_year")){
            return;
        }

        if (!doShell(exportMAbsentExaminee, "export m_absent_examinee")) {
            return;
        }
//        if (!doShell(exportMDrivingSchoolStatistics, "export m_driving_shool_statistics")) {
//            return;
//        }
        if (!doShell(exportMDrivingSchoolTotal, "export m_driving_school_total")) {
            return;
        }
        if (!doShell(exportMMutilExaminee, "export m_mutil_examinee")) {
            return;
        }
        if (!doShell(exportMStationStatistics, "export m_station_statistics")) {
            return;
        }
        if (!doShell(exportTExamroomExamStatistics, "export t_examroot_exam_statistics")) {
            return;
        }
        if(!doShell(exportMApplyStatistics, "export m_apply_statistics")){
            return;
        }
        if(!doShell(exportMDrivingSchoolEarliest, "export m_driving_school_earliest")){
            return;
        }
        if(!doShell(exportMDrivingSchoolTotalDay, "export m_driving_school_total_day")){
            return;
        }
        if(!doShell(exportMDrivingSchoolMonth, "export m_driving_school_month")){
            return;
        }
        if(!doShell(exportMDrivingSchoolYear, "export m_driving_school_year")){
            return;
        }

        logger.info("OK");
    }

    public boolean doShell(String cammand, String name) {
        boolean result = false;
        int time = 3;
        while (time > 0) {
            try {
                if (ShellCommand.doShell(cammand, name) == 0) {
                    result = true;
                    break;
                }
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
            time--;
        }

//        //保存状态到数据库
//        try {
//            DataComputeLog dataComputeLog = new DataComputeLog();
//            dataComputeLog.setComputeName(name);
//            dataComputeLog.setUpdateTime(Timestamp.valueOf(DateUtil.parse(new Date(), "yyyy-MM-dd HH:mm:ss")));
//            if (result) {
//                dataComputeLog.setStatus("OK");
//            } else {
//                dataComputeLog.setStatus("Exception");
//            }
//            dataComputeLogService.save(dataComputeLog);
//        } catch (Exception e) {
//            logger.error(e.getMessage(), e);
//        }
        return result;
    }
}
