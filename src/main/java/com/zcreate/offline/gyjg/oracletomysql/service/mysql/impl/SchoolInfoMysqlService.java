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
public class SchoolInfoMysqlService implements SchoolInfoMysqlDao {

    @Autowired
    private SchoolInfoMysqlDao schoolInfoMysqlDao;

    public void save(List<SchoolInfo> schoolInfos) {
        for(SchoolInfo schoolInfo : schoolInfos){
            save(schoolInfo);
        }
    }

    public void update(List<SchoolInfo> schoolInfos){
        for(SchoolInfo schoolInfo : schoolInfos){
            if(getSchoolInfo(schoolInfo.getSchoolId()) != null){
                update(schoolInfo);
            }else {
                save(schoolInfo);
            }
        }
    }

    @Override
    public int save(SchoolInfo schoolInfo) {
        return schoolInfoMysqlDao.save(schoolInfo);
    }

    @Override
    public int update(SchoolInfo schoolInfo) {
        return schoolInfoMysqlDao.update(schoolInfo);
    }

    @Override
    public SchoolInfo getSchoolInfo(String schoolId) {
        return schoolInfoMysqlDao.getSchoolInfo(schoolId);
    }

    @Override
    public int truncate() {
        return schoolInfoMysqlDao.truncate();
    }
}
