package com.zcreate.offline.gyjg.oracletomysql.service.oracle.impl;

import com.zcreate.offline.gyjg.oracletomysql.service.oracle.DataResource;
import com.zcreate.offline.gyjg.oracletomysql.service.oracle.ExamResultDao;
import com.zcreate.offline.gyjg.oracletomysql.service.oracle.util.ConnectUtil;
import com.zcreate.offline.gyjg.entity.ExamResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName: ExamResultService
 * @Author: majun
 * @CreateDate: 2018/12/29 15:30
 * @Version: 1.0
 * @Description: TODO
 */

@Service
public class ExamResultService implements ExamResultDao {

    @Autowired
    private ConnectUtil connectUtil;

    public List<ExamResult> listExamResultYesterday() throws SQLException {

        List<ExamResult> examResults = null;

        Connection connection = connectUtil.getConnect();

        String sql = "SELECT JSZH,XM,JXDM,KCDM,JSSJ,JGFS, HGBJ, ksy1xm" +
                " FROM gyjg.VM_JG_EXAM_KS_RESULT " +
                "where to_date(JSSJ) = to_date(sysdate - 1)";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();


        examResults = new ArrayList<ExamResult>();

        while (resultSet.next()){
            ExamResult examResult = new ExamResult();
            examResult.setIdCard(resultSet.getString("JSZH"));
            examResult.setName(resultSet.getString("XM"));
            examResult.setSchoolId(resultSet.getString("JXDM"));
            examResult.setExamineeId(resultSet.getString("KCDM"));
            examResult.setExamDate(resultSet.getDate("JSSJ"));
            examResult.setExamResult(resultSet.getString("HGBJ"));
            examResult.setScore(resultSet.getInt("JGFS"));
            examResult.setSupervisorName(resultSet.getString("ksy1xm"));
            examResult.setSchoolName(DataResource.schoolMap.get(examResult.getSchoolId()));
            examResult.setExamineeName(DataResource.examineeMap.get(examResult.getExamineeId()));
            Date date = new Date();
            examResult.setUpdateTime(new Timestamp(date.getTime()));

            examResults.add(examResult);
        }

        ConnectUtil.close(preparedStatement);
        ConnectUtil.close(connection);

        return examResults;
    }
}
