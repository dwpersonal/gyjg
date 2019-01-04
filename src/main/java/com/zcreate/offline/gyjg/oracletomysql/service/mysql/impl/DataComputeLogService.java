package com.zcreate.offline.gyjg.oracletomysql.service.mysql.impl;

import com.zcreate.offline.gyjg.entity.DataComputeLog;
import com.zcreate.offline.gyjg.oracletomysql.service.mysql.DataComputeLogDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

/**
 * @ClassName: DataComputeLogService
 * @Author: majun
 * @CreateDate: 2019/1/4 9:34
 * @Version: 1.0
 * @Description: TODO
 */

@Service
public class DataComputeLogService implements DataComputeLogDao {

    @Autowired
    private DataComputeLogDao dataComputeLogDao;

    @Override
    public void save(DataComputeLog dataComputeLog) {
        dataComputeLogDao.save(dataComputeLog);
    }

    @Override
    public List<DataComputeLog> listDataComputeLog(Timestamp startUpdateTime, Timestamp endUpdateTime) {
        return dataComputeLogDao.listDataComputeLog(startUpdateTime, endUpdateTime);
    }
}
