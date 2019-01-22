package com.zcreate.offline.gyjg.redistohive.impl;

import com.zcreate.offline.gyjg.redistohive.config.HiveClient;
import com.zcreate.offline.gyjg.entity.MenjinPassCount;
import com.zcreate.offline.gyjg.redistohive.MenjinHiveDao;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * @ClassName: MenjinCountImpl
 * @Author: majun
 * @CreateDate: 2018/11/28 9:28
 * @Version: 1.0
 * @Description: 门禁统计数据写入hive实现类
 */
@Service
public class MenjinCountService implements MenjinHiveDao {

    @Override
    public void insertMenjinToHive(MenjinPassCount menjinCount) throws Exception {

        String sql = "INSERT INTO manual_audit(audit_date, examroom_code, audit_num) values(?, ?, ?)";

        //获取连接
        Connection connection = HiveClient.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        //设置参数
        preparedStatement.setString(1, menjinCount.getAuditDate());
        preparedStatement.setString(2, menjinCount.getExamroomCode());
        preparedStatement.setInt(3, menjinCount.getAuditNum());

        //执行写入
        preparedStatement.execute();
        preparedStatement.close();

        //关闭连接
        HiveClient.closeConnection(connection);
    }
}
