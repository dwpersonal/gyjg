package com.zcreate.offline.gyjg.oracletomysql.service.oracle;

import com.zcreate.offline.gyjg.entity.SchoolInfo;

import java.sql.SQLException;
import java.util.List;

public interface SchoolInfoDao {

    List<SchoolInfo> listAllSchoolInfo() throws SQLException;
}
