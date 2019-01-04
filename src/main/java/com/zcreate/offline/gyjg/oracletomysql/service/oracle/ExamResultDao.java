package com.zcreate.offline.gyjg.oracletomysql.service.oracle;

import com.zcreate.offline.gyjg.entity.ExamResult;

import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName: ExamResultDao
 * @Author: majun
 * @CreateDate: 2018/12/29 15:23
 * @Version: 1.0
 * @Description: TODO
 */

public interface ExamResultDao {

    List<ExamResult> listExamResultYesterday() throws SQLException;
}
