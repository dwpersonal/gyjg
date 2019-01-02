package com.zcreate.offline.gyjg.redistohive.entity;

import java.sql.Timestamp;

/**
 * @ClassName: Examinee
 * @Author: majun
 * @CreateDate: 2018/12/29 15:19
 * @Version: 1.0
 * @Description:考场信息
 */

public class Examinee {
    private String examineeId;
    private String examineeName;
    private Timestamp updateTime;

    public String getExamineeId() {
        return examineeId;
    }

    public void setExamineeId(String examineeId) {
        this.examineeId = examineeId;
    }

    public String getExamineeName() {
        return examineeName;
    }

    public void setExamineeName(String examineeName) {
        this.examineeName = examineeName;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "Examinee{" +
                "examineeId='" + examineeId + '\'' +
                ", examineeName='" + examineeName + '\'' +
                ", updateTime=" + updateTime +
                '}';
    }
}
