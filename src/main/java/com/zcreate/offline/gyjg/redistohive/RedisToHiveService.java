package com.zcreate.offline.gyjg.redistohive;


import com.zcreate.offline.gyjg.redistohive.impl.MenjinCountService;
import com.zcreate.offline.gyjg.redistohive.impl.MenjinRedisService;
import com.zcreate.offline.gyjg.redistohive.entity.MenjinPassCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RedisToHiveService {

//    线上
//    private static String hosts = "172.16.25.11:7000";
//    测试
//    private static String hosts = "10.197.236.21:6379";

    @Value("${redis.hosts}")
    private String hosts;

    @Autowired
    private MenjinRedisService menjinRedisService;
    @Autowired
    private MenjinCountService menjinCountService;



    public int doImport() {

        List<MenjinPassCount> menjinPassCounts = menjinRedisService.getYesterdayMenjinPassCount(hosts);

        try {
            for (int i = 0; i < menjinPassCounts.size(); i++) {
                MenjinPassCount menjinPassCount = menjinPassCounts.get(i);
                menjinCountService.insertMenjinToHive(menjinPassCount);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return 1;
    }
}
