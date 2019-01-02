package com.zcreate.offline.gyjg.redistohive;


import com.zcreate.offline.gyjg.redistohive.entity.MenjinPassCount;

public interface MenjinHiveDao {

    /**
     * 添加门禁数通过人数到hive
     * @param menjinCount
     */
    void insertMenjinToHive(MenjinPassCount menjinCount) throws Exception;
}
