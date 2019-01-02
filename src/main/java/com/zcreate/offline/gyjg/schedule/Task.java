package com.zcreate.offline.gyjg.schedule;

import com.zcreate.offline.gyjg.oracletomysql.service.Transfer;
import com.zcreate.offline.gyjg.redistohive.RedisToHiveService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @ClassName: Task
 * @Author: majun
 * @CreateDate: 2019/1/2 11:17
 * @Version: 1.0
 * @Description: TODO
 */

@Component
public class Task {

    Logger logger = LoggerFactory.getLogger(Task.class);

    @Autowired
    private RedisToHiveService redisToHiveService;
    @Autowired
    private Transfer transfer;

    @Scheduled(cron = "36 * * * * *")
    public void doTask() {
        if (redisToHiveService.doImport() != 1) {
            logger.info("redis_to_hive fail");
            return;
        }

        if (transfer.trant() != 1) {
            return;
        }
    }
}
