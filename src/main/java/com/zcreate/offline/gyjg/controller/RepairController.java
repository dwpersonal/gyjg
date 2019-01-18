package com.zcreate.offline.gyjg.controller;

import com.zcreate.offline.gyjg.oracletomysql.service.Transfer;
import com.zcreate.offline.gyjg.redistohive.RedisToHiveService;
import com.zcreate.offline.gyjg.repair.RepairService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @ClassName: RepairController
 * @Author: majun
 * @CreateDate: 2019/1/4 11:18
 * @Version: 1.0
 * @Description: TODO
 */

@RestController
@RequestMapping(path = "/gyjg")
public class RepairController {

    Logger logger = LoggerFactory.getLogger(RestController.class);

    @Autowired
    private RedisToHiveService redisToHiveService;
    @Autowired
    private Transfer transfer;

    @Autowired
    private RepairService repairService;

    @RequestMapping(path = "/repair", method = RequestMethod.GET)
    public void doRepair(boolean isContinue, String taskName, int historyDaysNum, boolean synBaseData){

        if(synBaseData){
            logger.info("starting redis to hive");
            if (redisToHiveService.doImport() != 1) {
                logger.info("redis_to_hive fail");
                return;
            }
            logger.info("succeed redis to hive");

            logger.info("starting oracle to mysql");
            if (transfer.trant() != 1) {
                logger.info("oracle to mysql fail");
                return;
            }
            logger.info("succeed oracle to mysql");
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    repairService.doTask(isContinue, taskName, historyDaysNum);
                } catch (IOException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }).start();
    }
}
