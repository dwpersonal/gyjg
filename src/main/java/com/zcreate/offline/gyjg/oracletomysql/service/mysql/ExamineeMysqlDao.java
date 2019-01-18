package com.zcreate.offline.gyjg.oracletomysql.service.mysql;

import com.zcreate.offline.gyjg.entity.Examinee;
import org.apache.ibatis.annotations.*;

/**
 * @ClassName: ExamineeMysqlDao
 * @Author: majun
 * @CreateDate: 2018/12/29 17:02
 * @Version: 1.0
 * @Description: TODO
 */
@Mapper
public interface ExamineeMysqlDao {

    @Insert("INSERT INTO m_examroom_info(examroom_code," +
            "examroom_name," +
            "update_time) VALUES(#{examineeId}, " +
            "#{examineeName}," +
            "#{updateTime})")
    int save(Examinee examinee);

    @Select("SELECT examroom_code AS examineeId," +
            " examroom_name AS examineeName " +
            " FROM m_examroom_info " +
            " WHERE examroom_code = #{examineeId}")
    Examinee getExaminee(@Param("examineeId") String examineeId);

    @Update("UPDATE m_examroom_info SET examroom_name = #{examineeName}," +
            " update_time = #{updateTime} WHERE examroom_code = #{examineeId}")
    int update(Examinee examinee);

    @Delete("DELETE FROM m_examroom_info")
    int clear();
}
