package com.zcreate.offline.gyjg.oracletomysql.service.mysql;

import com.zcreate.offline.gyjg.entity.SchoolInfo;
import org.apache.ibatis.annotations.*;

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
            "law_person," +
            "involve_busi," +
            "school_photo," +
            "update_time) VALUES(#{schoolName}," +
            "#{schoolId}, " +
            "#{contactWay}," +
            "#{address}," +
            "#{lawPerson}," +
            "#{business}," +
            "#{photo}," +
            "#{update})")
    int save(SchoolInfo schoolInfo);

    @Update("UPDATE m_driving_school_info SET school_name = #{schoolName}, " +
            " telephone = #{telephone}," +
            " address=#{address}," +
            " law_person= #{lawPerson}," +
            " involve_busi=#{business}," +
            " update_time = #{update} " +
            " WHERE school_code = #{schoolId}")
    int update(SchoolInfo schoolInfo);

    @Select("SELECT school_name AS schoolName," +
            " school_code AS schoolId," +
            " telephone AS telephone," +
            " address, " +
            " law_person AS lawPerson, " +
            " involve_busi AS business FROM m_driving_school_info WHERE school_code = #{schoolId}")
    SchoolInfo getSchoolInfo(@Param("schoolId") String schoolId);

    @Delete("DELETE FROM m_driving_school_info")
    int truncate();
}
