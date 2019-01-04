package com.zcreate.offline.gyjg.oracletomysql.service.oracle.impl;

import com.zcreate.offline.gyjg.oracletomysql.service.oracle.DataResource;
import com.zcreate.offline.gyjg.oracletomysql.service.oracle.SchoolInfoDao;
import com.zcreate.offline.gyjg.oracletomysql.service.oracle.util.ConnectUtil;
import com.zcreate.offline.gyjg.entity.SchoolInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName: SchoolInfoService
 * @Author: majun
 * @CreateDate: 2018/12/29 16:46
 * @Version: 1.0
 * @Description: TODO
 */

@Service
public class SchoolInfoService implements SchoolInfoDao {

    @Autowired
    private ConnectUtil connectUtil;

    public List<SchoolInfo> listAllSchoolInfo() throws SQLException {
        List<SchoolInfo> schoolInfos = null;

        Connection connection = connectUtil.getConnect();

        String sql = "select JXDM, JXMC, LXR, JXDZ from gyjg.VM_JG_EXAM_SCHOOLINFO";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();

        schoolInfos = new ArrayList<SchoolInfo>();

        while(resultSet.next()){
            SchoolInfo schoolInfo = new SchoolInfo();
            schoolInfo.setSchoolId(resultSet.getString("JXDM"));
            schoolInfo.setSchoolName(resultSet.getString("JXMC"));
            schoolInfo.setContactWay(resultSet.getString("LXR"));
            schoolInfo.setAddress(resultSet.getString("JXDZ"));
            Date date = new Date();
            schoolInfo.setUpdate(new Timestamp(date.getTime()));

            DataResource.schoolMap.put(schoolInfo.getSchoolId(), schoolInfo.getSchoolName());

            schoolInfos.add(schoolInfo);
        }

        ConnectUtil.close(preparedStatement);
        ConnectUtil.close(connection);

        return schoolInfos;
    }
}
