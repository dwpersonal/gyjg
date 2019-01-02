package com.zcreate.offline.gyjg.oracletomysql.service.mysql;

import com.zcreate.offline.gyjg.redistohive.entity.ExamResult;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @ClassName: ExamResultMysqlDao
 * @Author: majun
 * @CreateDate: 2018/12/29 17:02
 * @Version: 1.0
 * @Description: TODO
 */

@Mapper
public interface ExamResultMysqlDao {

    @Insert("INSERT INTO m_exam_record(examinee_code," +
            "examinee_name, " +
            "idetity_card, " +
            "school_code, " +
            "school_name, " +
            "examroom_code, " +
            "examroom_name, " +
            "exam_date, " +
            "exam_score, " +
            "exam_res, " +
            "update_time) VALUES(#{examineeId}," +
            "#{examineeName}, " +
            "#{idCard}," +
            "#{schoolId}," +
            "#{schoolName}," +
            "#{examDate}," +
            "#{score}," +
            "#{examResult}," +
            "#{updateTime})")
    void save(ExamResult result);
}
