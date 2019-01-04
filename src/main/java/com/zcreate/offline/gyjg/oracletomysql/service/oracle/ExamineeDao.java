package com.zcreate.offline.gyjg.oracletomysql.service.oracle;

import com.zcreate.offline.gyjg.entity.Examinee;

import java.sql.SQLException;
import java.util.List;

public interface ExamineeDao {

    List<Examinee> listAllExamInee() throws SQLException;
}
