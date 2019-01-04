package com.zcreate.offline.gyjg.oracletomysql.service.mysql.impl;

import com.zcreate.offline.gyjg.oracletomysql.service.mysql.SchoolInfoMysqlDao;
import com.zcreate.offline.gyjg.entity.SchoolInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: SchoolInfoMysqlService
 * @Author: majun
 * @CreateDate: 2018/12/29 17:10
 * @Version: 1.0
 * @Description: TODO
 */

@Service
public class SchoolInfoMysqlService {

    @Autowired
    private SchoolInfoMysqlDao schoolInfoMysqlDao;

    public void save(List<SchoolInfo> schoolInfos) {
        for(SchoolInfo schoolInfo : schoolInfos){
            schoolInfoMysqlDao.save(schoolInfo);
        }
    }

    public void clean(){
        schoolInfoMysqlDao.truncate();
    }
}
