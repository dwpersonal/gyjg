package com.zcreate.offline.gyjg.oracletomysql.service.mysql.impl;

import com.zcreate.offline.gyjg.oracletomysql.service.mysql.ExamineeMysqlDao;
import com.zcreate.offline.gyjg.entity.Examinee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: ExamineeMysqlService
 * @Author: majun
 * @CreateDate: 2018/12/29 17:12
 * @Version: 1.0
 * @Description: TODO
 */

@Service
public class ExamineeMysqlService {

    @Autowired
    private ExamineeMysqlDao examineeMysqlDao;

    public void save(List<Examinee> examinees){
        for (Examinee examinee : examinees){
            examineeMysqlDao.save(examinee);
        }
    }

    public void clear(){
        examineeMysqlDao.clear();
    }
}
