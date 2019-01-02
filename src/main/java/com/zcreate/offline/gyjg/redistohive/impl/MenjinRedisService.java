package com.zcreate.offline.gyjg.redistohive.impl;

import com.zcreate.offline.gyjg.redistohive.entity.MenjinPassCount;
import com.zcreate.offline.gyjg.redistohive.redis.RedisClient;
import com.zcreate.offline.gyjg.redistohive.MenjinRedisDao;
import com.zcreate.offline.gyjg.redistohive.utils.DateUtil;
import com.zcreate.offline.gyjg.redistohive.utils.RedisClientUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * @ClassName: MenjinRedisService
 * @Author: majun
 * @CreateDate: 2018/11/28 9:54
 * @Version: 1.0
 * @Description: 门禁统计数据与redis交互实现类
 */
@Service
public class MenjinRedisService implements MenjinRedisDao {

    @Override
    public List<MenjinPassCount> getYesterdayMenjinPassCount(String hosts) {

        List<MenjinPassCount> menjinPassCounts = new ArrayList<MenjinPassCount>();

        RedisClient redisClient = RedisClientUtil.getRedisClient(hosts);
        String yesterday = DateUtil.getYesterday("yyyy-MM-dd");

        String key = "menjin_pass_count_".concat(DateUtil.getYesterday("yyyyMMdd"));
        Set<String> examRoomIds = redisClient.hkeys(key);
        Iterator<String> iterators = examRoomIds.iterator();
        while (iterators.hasNext()) {
            String examRoomId = iterators.next();
            Integer passNum = Integer.parseInt(redisClient.hget(key, examRoomId));
            MenjinPassCount menjinPassCount = new MenjinPassCount(yesterday, examRoomId, passNum);
            menjinPassCounts.add(menjinPassCount);
        }
        return menjinPassCounts;
    }
}
