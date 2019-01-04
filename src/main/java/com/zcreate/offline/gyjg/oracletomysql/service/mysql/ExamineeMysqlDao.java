package com.zcreate.offline.gyjg.oracletomysql.service.mysql;

import com.zcreate.offline.gyjg.entity.Examinee;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

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

    @Delete("DELETE FROM m_examroom_info")
    int clear();
}
