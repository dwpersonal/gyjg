package com.zcreate.offline.gyjg.oracletomysql.service.mysql;

import com.zcreate.offline.gyjg.entity.SchoolInfo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName: SchoolInfoMysqlDao
 * @Author: majun
 * @CreateDate: 2018/12/29 17:01
 * @Version: 1.0
 * @Description: TODO
 */

@Mapper
public interface SchoolInfoMysqlDao {

    @Insert("INSERT INTO m_driving_school_info(school_name," +
            "school_code," +
            "telephone," +
            "address," +
            "employee_count," +
            "involve_busi," +
            "school_photo," +
            "update_time) VALUES(#{schoolName}," +
            "#{schoolId}, " +
            "#{contactWay}," +
            "#{address}," +
            "#{numOfStaff}," +
            "#{business}," +
            "#{photo}," +
            "#{update})")
    int save(SchoolInfo schoolInfo);

    @Delete("DELETE FROM m_driving_school_info")
    int truncate();
}
