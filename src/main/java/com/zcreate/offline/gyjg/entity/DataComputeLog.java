package com.zcreate.offline.gyjg.entity;

import java.sql.Timestamp;

/**
 * @ClassName: DataComputeLog
 * @Author: majun
 * @CreateDate: 2019/1/4 9:25
 * @Version: 1.0
 * @Description: TODO
 */

public class DataComputeLog {
    private int id;
    private String computeName;
    private String status;
    private Timestamp updateTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComputeName() {
        return computeName;
    }

    public void setComputeName(String computeName) {
        this.computeName = computeName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "DataComputeLog{" +
                "id=" + id +
                ", computeName='" + computeName + '\'' +
                ", status='" + status + '\'' +
                ", updateTime=" + updateTime +
                '}';
    }
}
