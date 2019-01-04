package com.zcreate.offline.gyjg.controller;

import com.zcreate.offline.gyjg.repair.RepairService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

    @Autowired
    private RepairService repairService;

    @RequestMapping(path = "/repair", method = RequestMethod.POST)
    public void doRepair(int historyDaysNum, boolean isLoadExamKsResult){
        new Thread(new Runnable() {
            @Override
            public void run() {
                repairService.doTask(isLoadExamKsResult, historyDaysNum);
            }
        }).start();
    }
}
