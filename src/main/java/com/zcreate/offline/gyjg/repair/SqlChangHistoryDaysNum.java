package com.zcreate.offline.gyjg.repair;

import java.io.*;

/**
 * @ClassName: SqlChangHistoryDaysNum
 * @Author: majun
 * @CreateDate: 2019/1/4 10:57
 * @Version: 1.0
 * @Description: TODO
 */

public class SqlChangHistoryDaysNum {

    public static void change(String path, int historyDaysNum) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
        StringBuffer buffer = new StringBuffer(1024);
        String str = "";
        while((str = bufferedReader.readLine()) != null){
            if(str.length() > 0){
                buffer.append(str);
            }else {
                break;
            }
        }
        String result = buffer.toString();
        result = result.replaceAll("<<history_days_num>>", historyDaysNum+"");
        bufferedReader.close();

        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("/tmp/select.sql")));
        bufferedWriter.write(result);
        bufferedWriter.close();
    }
}
