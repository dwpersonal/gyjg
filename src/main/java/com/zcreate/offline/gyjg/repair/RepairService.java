package com.zcreate.offline.gyjg.repair;

import com.zcreate.offline.gyjg.entity.DataComputeLog;
import com.zcreate.offline.gyjg.oracletomysql.service.Transfer;
import com.zcreate.offline.gyjg.oracletomysql.service.mysql.impl.DataComputeLogService;
import com.zcreate.offline.gyjg.redistohive.RedisToHiveService;
import com.zcreate.offline.gyjg.redistohive.utils.DateUtil;
import com.zcreate.offline.gyjg.schedule.Task;
import com.zcreate.offline.gyjg.shell.ShellCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @ClassName: Task
 * @Author: majun
 * @CreateDate: 2019/1/2 11:17
 * @Version: 1.0
 * @Description: TODO
 */

@Service
public class RepairService {

    Logger logger = LoggerFactory.getLogger(RepairService.class);

//    repair.hive.import.exam.ks.result=sqoop|import|--connect|"jdbc:oracle:thin:@172.16.17.82:1521:gyjg"|--username|zckj|--password|ZCKJ2018|-m|1|--target-dir|/user/admin/gyjg/temp/time_paration_exam_ks_result_temp|--delete-target-dir|--fields-terminated-by|"\\t"|--lines-terminated-by|"\\n"|--split-by|XH|--where|1=1|--query|"SELECT result.*,to_char(result.JSSJ,'yyyy-MM-dd') as day ,to_char(result.JSSJ,'yyyy-MM') as month FROM GYJG.VM_JG_EXAM_KS_RESULT result where result.JSSJ> to_date(to_char(sysdate-<<history_days_num>>,'yyyy-mm-dd'), 'yyyy-mm-dd') AND result.JSSJ <= to_date(to_char(sysdate+1-<<history_days_num>>,'yyyy-mm-dd'), 'yyyy-mm-dd') AND $CONDITIONS"
//    repair.hive.select= hive|-f|/tmp/select.sql
//
//    repair.hive.select.m_absent_examinee= /var/lib/hadoop-hdfs/gyjg/repair/select/m_absent_examinee.sql
//    repair.hive.select.exam_mid_result= /var/lib/hadoop-hdfs/gyjg/repair/select/exam_mid_result.sql
//    repair.hive.select.exam_mid_perasign= /var/lib/hadoop-hdfs/gyjg/repair/select/exam_mid_perasign.sql
//    repair.hive.select.t_examroom_exam_statistics= /var/lib/hadoop-hdfs/gyjg/repair/select/t_examroom_exam_statistics.sql
//    repair.hive.select.m_station_statistics= /var/lib/hadoop-hdfs/gyjg/repair/select/m_station_statistics.sql
//    repair.hive.select.m_mutil_examinee= /var/lib/hadoop-hdfs/gyjg/repair/select/m_mutil_examinee.sql
//    repair.hive.select.school_mid_count= /var/lib/hadoop-hdfs/gyjg/repair/select/school_mid_count.sql
//    repair.hive.select.m_driving_school_total= /var/lib/hadoop-hdfs/gyjg/repair/select/m_driving_school_total.sql
//    repair.hive.select.m_driving_school_statistics= /var/lib/hadoop-hdfs/gyjg/repair/select/m_driving_school_statistics.sql


    @Value("${repair.hive.import.exam.ks.result}")
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


    @Value("${repair.hive.select}")
    private String repairSelect;

    @Value("${repair.hive.select.m_absent_examinee}")
    private String selectMAbsentExaminee;
    @Value("${repair.hive.select.exam_mid_result}")
    private String selectExamMidResult;
    @Value("${repair.hive.select.exam_mid_perasign}")
    private String selectExamMidPerasign;
    @Value("${repair.hive.select.t_examroom_exam_statistics}")
    private String selectTExamroomExamStatistics;
    @Value("${repair.hive.select.m_station_statistics}")
    private String selectMStationStatistics;
    @Value("${repair.hive.select.m_mutil_examinee}")
    private String selectMMutilExaminee;
    @Value("${repair.hive.select.school_mid_count}")
    private String selectSchoolMidCount;
    @Value("${repair.hive.select.m_driving_school_total}")
    private String selectMDrivingSchoolTotal;
    @Value("${repair.hive.select.m_driving_school_statistics}")
    private String selectMDrivingSchoolStatistics;

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
    @Value("${sqoop.export.m_driving_school_statistics}")
    private String exportMDrivingSchoolStatistics;


    @Autowired
    private DataComputeLogService dataComputeLogService;
    @Autowired
    private Task task;

    public void doTask(boolean isloadExamKsResult, int historyDaysNum) {

        if (isloadExamKsResult) {
            importExamKsResult = importExamKsResult.replaceAll("<<history_days_num>>", historyDaysNum+"");

            if (!task.doShell(importExamKsResult, "repair import exam_ks_result")) {
                return;
            }
            if (!task.doShell(loadExamKsResult, "repair load exam_ks_result")) {
                return;
            }
        }

        if (!task.doShell(importSchoolinfo, "repair import schoolinfo")) {
            return;
        }
        if (!task.doShell(loadSchoolinfo, "repair load schoolinfo")) {
            return;
        }

        if (!task.doShell(importPerasign, "repair import perasign")) {
            return;
        }
        if(!task.doShell(loadPerasign, "repair load perasign")){
            return;
        }

        try{
            SqlChangHistoryDaysNum.change(selectMAbsentExaminee, historyDaysNum);
            if (!task.doShell(repairSelect, "repair select m_absent_examinee")) {
                return;
            }

            SqlChangHistoryDaysNum.change(selectExamMidPerasign, historyDaysNum);
            if (!task.doShell(repairSelect, "repair select exam_mid_perasing")) {
                return;
            }

            SqlChangHistoryDaysNum.change(selectExamMidResult, historyDaysNum);
            if (!task.doShell(repairSelect, "repair select exam_mid_result")) {
                return;
            }

            SqlChangHistoryDaysNum.change(selectTExamroomExamStatistics, historyDaysNum);
            if (!task.doShell(repairSelect, "repair select t_examroot_statistics")) {
                return;
            }

            SqlChangHistoryDaysNum.change(selectMStationStatistics, historyDaysNum);
            if (!task.doShell(repairSelect, "repair select m_station_statistics")) {
                return;
            }

            SqlChangHistoryDaysNum.change(selectMMutilExaminee, historyDaysNum);
            if (!task.doShell(repairSelect, "repair select m_mutil_examinee")) {
                return;
            }

            SqlChangHistoryDaysNum.change(selectSchoolMidCount, historyDaysNum);
            if (!task.doShell(repairSelect, "repair select school_mid_count")) {
                return;
            }

            SqlChangHistoryDaysNum.change(selectMDrivingSchoolTotal, historyDaysNum);
            if (!task.doShell(repairSelect, "repair select m_driving_school_total")) {
                return;
            }

            SqlChangHistoryDaysNum.change(selectMDrivingSchoolStatistics, historyDaysNum);
            if (!task.doShell(selectMDrivingSchoolStatistics, "repairselect m_driving_school_statistics")) {
                return;
            }
        }catch (Exception e) {
            logger.error(e.getMessage(), e);
            return;
        }


        if (!task.doShell(exportMAbsentExaminee, "repair export m_absent_examinee")) {
            return;
        }

        if (!task.doShell(exportMDrivingSchoolStatistics, "repair export m_driving_shool_statistics")) {
            return;
        }

        if (!task.doShell(exportMDrivingSchoolTotal, "repair export m_driving_school_total")) {
            return;
        }

        if (!task.doShell(exportMMutilExaminee, "repair export m_mutil_examinee")) {
            return;
        }

        if (!task.doShell(exportMStationStatistics, "repair export m_station_statistics")) {
            return;
        }

        if (!task.doShell(exportTExamroomExamStatistics, "repair export t_examroot_exam_statistics")) {
            return;
        }

        logger.info("OK");
    }
}
