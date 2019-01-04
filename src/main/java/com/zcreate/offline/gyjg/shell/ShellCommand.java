package com.zcreate.offline.gyjg.shell;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;

/**
 * @ClassName: ShellCommand
 * @Author: majun
 * @CreateDate: 2019/1/2 16:08
 * @Version: 1.0
 * @Description: TODO
 */

public class ShellCommand {

    private static Logger logger = LoggerFactory.getLogger(ShellCommand.class);

    public static int doShell(String command, String name) throws IOException, InterruptedException {

        logger.info("starting " + name + " --> command : " + command);

        String[] cmd = command.split("\\|");

        Process process = Runtime.getRuntime().exec(cmd);
        int status = process.waitFor();

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String str = "";
        while ((str = bufferedReader.readLine()) != ""){
            if(str == null){
                break;
            }
            logger.info(str);
        }

        bufferedReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
        while ((str = bufferedReader.readLine())  != ""){
            if(str == null){
                break;
            }
            logger.info(str);
        }
        if(status == 0){
            logger.info("succeed " + name);
        }else {
            logger.info("failed to " + name);
        }
        logger.info("\n\n\n");
        return status;
    }
}
