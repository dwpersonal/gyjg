package com.zcreate.offline.gyjg.oracletomysql.service.mysql.impl;

import com.zcreate.offline.gyjg.entity.ExamResult;
import com.zcreate.offline.gyjg.oracletomysql.service.mysql.ExamResultMysqlDao;
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
public class ExamineeMysqlService implements ExamineeMysqlDao {

    @Autowired
    private ExamineeMysqlDao examineeMysqlDao;

    public void save(List<Examinee> examinees){
        for (Examinee examinee : examinees){
            save(examinee);
        }
    }

    public void update(List<Examinee> examinees){
        for(Examinee examinee : examinees){
            if(getExaminee(examinee.getExamineeId()) == null){
                save(examinee);
            }else {
                update(examinee);
            }
        }
    }

    @Override
    public int save(Examinee examinee) {
        return examineeMysqlDao.save(examinee);
    }

    @Override
    public Examinee getExaminee(String examineeId) {
        return examineeMysqlDao.getExaminee(examineeId);
    }

    @Override
    public int update(Examinee examinee) {
        return examineeMysqlDao.update(examinee);
    }

    @Override
    public int clear() {
        return examineeMysqlDao.clear();
    }
}
