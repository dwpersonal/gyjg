package com.zcreate.offline.gyjg.oracletomysql.service;

import com.zcreate.offline.gyjg.oracletomysql.service.mysql.impl.ExamResultMysqlService;
import com.zcreate.offline.gyjg.oracletomysql.service.mysql.impl.ExamineeMysqlService;
import com.zcreate.offline.gyjg.oracletomysql.service.mysql.impl.SchoolInfoMysqlService;
import com.zcreate.offline.gyjg.oracletomysql.service.oracle.impl.ExamIneeService;
import com.zcreate.offline.gyjg.oracletomysql.service.oracle.impl.ExamResultService;
import com.zcreate.offline.gyjg.oracletomysql.service.oracle.impl.SchoolInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

/**
 * @ClassName: Transfer
 * @Author: majun
 * @CreateDate: 2019/1/2 11:20
 * @Version: 1.0
 * @Description: TODO
 */

@Component
public class Transfer {

    Logger logger = LoggerFactory.getLogger(Transfer.class);

    @Autowired
    private ExamIneeService examIneeService;
    @Autowired
    private ExamResultService examResultService;
    @Autowired
    private SchoolInfoService schoolInfoService;

    @Autowired
    private ExamineeMysqlService examineeMysqlService;
    @Autowired
    private ExamResultMysqlService examResultMysqlService;
    @Autowired
    private SchoolInfoMysqlService schoolInfoMysqlService;

    public int trant() {
        try {
            examineeMysqlService.clear();
            examineeMysqlService.save(examIneeService.listAllExamInee());
            schoolInfoMysqlService.update(schoolInfoService.listAllSchoolInfo());
            examResultMysqlService.save(examResultService.listExamResultYesterday());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return 0;
        }
        return 1;
    }
}
