package com.zcreate.offline.gyjg.redistohive;

import com.zcreate.offline.gyjg.entity.MenjinPassCount;

import java.util.List;

/**
 * @ClassName: MenjinRedisDao
 * @Author: majun
 * @CreateDate: 2018/11/28 9:51
 * @Version: 1.0
 * @Description: 从redis中获取门禁统计相关的数据接口
 */

public interface MenjinRedisDao {

    /**
     * 获取昨天通过每个考场通过门禁进入的统计量
     * @return
     */
    List<MenjinPassCount> getYesterdayMenjinPassCount(String hosts);
}
