package com.zcreate.offline.gyjg.oracletomysql.service.mysql;

import com.zcreate.offline.gyjg.entity.DataComputeLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.sql.Timestamp;
import java.util.List;

@Mapper
public interface DataComputeLogDao {

    @Insert("INSERT INTO m_data_compute_log(compute_name," +
            " status, " +
            "update_time) VALUES(#{computeName}, " +
            "#{status}, " +
            "#{updateTime})")
    void save(DataComputeLog dataComputeLog);

    @Select("SELECT id, c" +
            "ompute_name AS computeName, " +
            "status, " +
            "update_time AS updateTime " +
            "FROM m_data_compute_log " +
            "WHERE update_time > #{startUpdateTime} AND update_time < endUpdateTime")
    List<DataComputeLog> listDataComputeLog(@Param("startUpdateTime") Timestamp startUpdateTime,
                                            @Param("endUpdateTime") Timestamp endUpdateTime);
}
