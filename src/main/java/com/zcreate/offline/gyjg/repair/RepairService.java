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

import java.io.*;
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

    @Value("${repair.hive.import.schoolinfo}")
    private String importSchoolinfo;
    @Value("${hive.load.schoolinfo}")
    private String loadSchoolinfo;

    @Value("${repair.hive.import.perasign}")
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
//    @Value("${repair.hive.select.m_driving_school_statistics}")
//    private String selectMDrivingSchoolStatistics;
    @Value("${repair.hive.select.graduate_mid_num}")
    private String selectGraduateMidNum;
    @Value("${repair.hive.select.m_apply_statistics}")
    private String selectMApplyStatictics;
    @Value("${repair.hive.select.m_driving_school_earliest}")
    private String selectMDrivingSchoolEarliest;
    @Value("${repair.hive.select.m_driving_school_total_day}")
    private String selectMDrivingSchoolTotalDay;
    @Value("${repair.hive.select.m_driving_school_total_month}")
    private String selectMDrivingSchoolTotalMonth;
    @Value("${repair.hive.select.m_driving_school_total_year}")
    private String selectMDrivingSchoolTotalYear;
    @Value("${repair.hive.select.students_num_change}")
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
    private DataComputeLogService dataComputeLogService;
    @Autowired
    private Task task;

    public void doTask(boolean isContinue, String taskName, int historyDaysNum) throws IOException {
        switch (taskName){
            case "import exam_ks_result":
                importExamKsResult = replaceHistorydaysNum(importExamKsResult, historyDaysNum);
                if(!task.doShell(importExamKsResult, "import exam_ks_result")){
                    return;
                }
                if(!task.doShell(loadExamKsResult, "load exam_ks_result")){
                    return;
                }
                if(!isContinue){
                    break;
                }
            case "import perasign":
                importPerasign = replaceHistorydaysNum(importPerasign, historyDaysNum);
                if(!task.doShell(importPerasign, "import perasign")){
                    return;
                }
                if(!task.doShell(loadPerasign, "load perasign")){
                    return;
                }
                if(!isContinue){
                    break;
                }
            case "import schoolinfo":
                importSchoolinfo = replaceHistorydaysNum(importSchoolinfo, historyDaysNum);
                if(!task.doShell(importSchoolinfo, "import schoolinfo")){
                    return;
                }
                if(!task.doShell(loadSchoolinfo, "load schoolinfo")){
                    return;
                }
                if(!isContinue){
                    return;
                }
            case "select m_absent_examinee":
                replaceHistoryDaysNumFile(selectMAbsentExaminee, historyDaysNum);
                if(!task.doShell(repairSelect, "select m_absent_examinee")){
                    return;
                }
                if(!isContinue){
                    return;
                }
            case "select exam_mid_result":
                replaceHistoryDaysNumFile(selectExamMidResult, historyDaysNum);
                if(!task.doShell(repairSelect, "select exam_mid_result")){
                    return;
                }
                if(!isContinue){
                    return;
                }
            case "select exam_mid_perasign":
                replaceHistoryDaysNumFile(selectExamMidPerasign, historyDaysNum);
                if(!task.doShell(repairSelect, "select exam_mid_perasign")){
                    return;
                }
                if(!isContinue){
                    return;
                }
            case "select t_examroom_exam_statistics":
                replaceHistoryDaysNumFile(selectTExamroomExamStatistics, historyDaysNum);
                if(!task.doShell(repairSelect, "select t_examroom_exam_statistics")){
                    return;
                }
                if(!isContinue){
                    return;
                }
            case "select m_station_statistics":
                replaceHistoryDaysNumFile(selectMStationStatistics, historyDaysNum);
                if(!task.doShell(repairSelect, "select m_station_statistics")){
                    return;
                }
                if(!isContinue){
                    return;
                }
            case "select m_mutil_examinee":
                replaceHistoryDaysNumFile(selectMMutilExaminee, historyDaysNum);
                if(!task.doShell(repairSelect, "select m_mutil_examinee")){
                    return;
                }
                if(!isContinue){
                    return;
                }
            case "select school_mid_count":
                replaceHistoryDaysNumFile(selectSchoolMidCount, historyDaysNum);
                if(!task.doShell(repairSelect, "select school_mid_count")){
                    return;
                }
                if(!isContinue){
                    return;
                }
            case "select graduate_mid_num":
                replaceHistoryDaysNumFile(selectGraduateMidNum, historyDaysNum);
                if(!task.doShell(repairSelect, "select graduate_mid_num")){
                    return;
                }
                if(!isContinue){
                    return;
                }
            case "select students_num_change":
                replaceHistoryDaysNumFile(selectStudentsNumChange, historyDaysNum);
                if(!task.doShell(repairSelect, "select students_num_change")){
                    return;
                }
                if(!isContinue){
                    return;
                }
            case "select m_driving_school_total":
                replaceHistoryDaysNumFile(selectMDrivingSchoolTotal, historyDaysNum);
                if(!task.doShell(repairSelect, taskName)){
                    return;
                }
                if(!isContinue){
                    return;
                }
            case "select m_apply_statistics":
                replaceHistoryDaysNumFile(selectMApplyStatictics, historyDaysNum);
                if(!task.doShell(repairSelect, "select m_apply_statistics")){
                    return;
                }
                if(!isContinue){
                    return;
                }
            case "select m_driving_school_earliest":
                replaceHistoryDaysNumFile(selectMDrivingSchoolEarliest, historyDaysNum);
                if(!task.doShell(repairSelect, "select m_driving_school_earliest")){
                    return;
                }
                if(!isContinue){
                    return;
                }
            case "select m_driving_school_total_day":
                replaceHistoryDaysNumFile(selectMDrivingSchoolTotalDay, historyDaysNum);
                if(!task.doShell(repairSelect, "select m_driving_school_total_day")){
                    return;
                }
                if(!isContinue){
                    return;
                }
            case "select m_driving_school_total_month":
                replaceHistoryDaysNumFile(selectMDrivingSchoolTotalMonth, historyDaysNum);
                if(!task.doShell(repairSelect, "select m_driving_school_total_month")){
                    return;
                }
                if(!isContinue){
                    return;
                }
            case "select m_driving_school_total_year":
                replaceHistoryDaysNumFile(selectMDrivingSchoolTotalYear, historyDaysNum);
                if(!task.doShell(repairSelect, "select m_driving_school_total_year")){
                    return;
                }
                if(!isContinue){
                    return;
                }

            case "export t_examroot_exam_statistics":
                exportTExamroomExamStatistics = replaceHistorydaysNum(exportTExamroomExamStatistics, historyDaysNum);
                if(!task.doShell(exportTExamroomExamStatistics, "export t_examroot_exam_statistics")){
                    return;
                }
                if(!isContinue){
                    return;
                }
            case "export m_absent_examinee":
                exportMAbsentExaminee = replaceHistorydaysNum(exportMAbsentExaminee, historyDaysNum);
                if(!task.doShell(exportMAbsentExaminee, "export m_absent_examinee")){
                    return;
                }
                if(!isContinue){
                    return;
                }
            case "export m_station_statistics":
                exportMStationStatistics = replaceHistorydaysNum(exportMStationStatistics, historyDaysNum);
                if(!task.doShell(exportMStationStatistics, "export m_station_statistics")){
                    return;
                }
                if(!isContinue){
                    return;
                }
            case "export m_mutil_examinee":
                exportMMutilExaminee = replaceHistorydaysNum(exportMMutilExaminee, historyDaysNum);
                if(!task.doShell(exportMMutilExaminee, "export m_mutil_examinee")){
                    return;
                }
                if(!isContinue){
                    return;
                }
            case "export m_driving_school_total":
                exportMDrivingSchoolTotal = replaceHistorydaysNum(exportMDrivingSchoolTotal, historyDaysNum);
                if(!task.doShell(exportMDrivingSchoolTotal, "export m_driving_school_total")){
                    return;
                }
                if(!isContinue){
                    return;
                }
            case "export m_apply_statistics":
                exportMApplyStatistics = replaceHistorydaysNum(exportMApplyStatistics, historyDaysNum);
                if(!task.doShell(exportMApplyStatistics, "export m_apply_statistics")){
                    return;
                }
                if(!isContinue){
                    return;
                }
            case "export m_driving_school_earliest":
                exportMDrivingSchoolEarliest = replaceHistorydaysNum(exportMDrivingSchoolEarliest, historyDaysNum);
                if(!task.doShell(exportMDrivingSchoolEarliest, "export m_driving_school_earliest")){
                    return;
                }
                if(!isContinue){
                    return;
                }
            case "export m_driving_school_total_day":
                exportMDrivingSchoolTotalDay = replaceHistorydaysNum(exportMDrivingSchoolTotalDay, historyDaysNum);
                if(!task.doShell(exportMDrivingSchoolTotalDay, "export m_driving_school_total_day")){
                    return;
                }
                if(!isContinue){
                    return;
                }
            case "export m_driving_school_total_month":
                exportMDrivingSchoolMonth = replaceHistorydaysNum(exportMDrivingSchoolMonth, historyDaysNum);
                if(!task.doShell(exportMDrivingSchoolMonth, "export m_driving_school_total_month")){
                    return;
                }
                if(!isContinue){
                    return;
                }
            case "export m_driving_school_total_year":
                exportMDrivingSchoolYear = replaceHistorydaysNum(exportMDrivingSchoolYear, historyDaysNum);
                if(!task.doShell(exportMDrivingSchoolYear, "export m_driving_school_total_year")){
                    return;
                }
                if(!isContinue){
                    return;
                }
        }
    }

    public String replaceHistorydaysNum(String command, int historyDaysNum){
        return command.replaceAll("<<history_days_num>>", historyDaysNum+"");
    }

    public void replaceHistoryDaysNumFile(String path, int historyDaysNum) throws IOException {
        logger.info("file path : " + path);
        BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
        String result = "";
        String str = null;
        while ((str = bufferedReader.readLine()) != null){
            result += str + "\n";
        }
        result = replaceHistorydaysNum(result, historyDaysNum);
        bufferedReader.close();

        File file = new File("/tmp/select.sql");
        if(!file.exists()){
            file.createNewFile();
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        writer.write(result);
        writer.close();
    }
}
