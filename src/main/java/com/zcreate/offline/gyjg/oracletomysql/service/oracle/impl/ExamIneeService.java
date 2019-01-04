package com.zcreate.offline.gyjg.oracletomysql.service.oracle.impl;

import com.zcreate.offline.gyjg.entity.Examinee;
import com.zcreate.offline.gyjg.oracletomysql.service.oracle.DataResource;
import com.zcreate.offline.gyjg.oracletomysql.service.oracle.ExamineeDao;
import com.zcreate.offline.gyjg.oracletomysql.service.oracle.util.ConnectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName: ExamIneeService
 * @Author: majun
 * @CreateDate: 2018/12/29 16:29
 * @Version: 1.0
 * @Description: TODO
 */

@Service
public class ExamIneeService implements ExamineeDao {

    @Autowired
    private ConnectUtil connectUtil;

    public List<Examinee> listAllExamInee() throws SQLException {
        List<Examinee> examinees = null;

        Connection connection = connectUtil.getConnect();

        String sql = "select KCDM, KCMC from gyjg.VM_JG_EXAM_EXAM_KCDM";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();

        examinees = new ArrayList<Examinee>();

        while(resultSet.next()){
            Examinee examinee = new Examinee();
            examinee.setExamineeId(resultSet.getString("KCDM"));
            examinee.setExamineeName(resultSet.getString("KCMC"));
            Date date = new Date();
            examinee.setUpdateTime(new Timestamp(date.getTime()));
            DataResource.examineeMap.put(examinee.getExamineeId(), examinee.getExamineeName());

            examinees.add(examinee);
        }
        ConnectUtil.close(preparedStatement);
        ConnectUtil.close(connection);

        return examinees;
    }
}
