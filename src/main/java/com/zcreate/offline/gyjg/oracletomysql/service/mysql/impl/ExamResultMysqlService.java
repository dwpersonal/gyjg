package com.zcreate.offline.gyjg.oracletomysql.service.mysql.impl;

import com.zcreate.offline.gyjg.oracletomysql.service.mysql.ExamResultMysqlDao;
import com.zcreate.offline.gyjg.entity.ExamResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: ExamResultMysqlService
 * @Author: majun
 * @CreateDate: 2018/12/29 17:11
 * @Version: 1.0
 * @Description: TODO
 */

@Service
public class ExamResultMysqlService {

    @Autowired
    private ExamResultMysqlDao examResultMysqlDao;

    public void save(List<ExamResult> examResults) {
        for(ExamResult examResult : examResults){
            examResultMysqlDao.save(examResult);
        }
    }
}
