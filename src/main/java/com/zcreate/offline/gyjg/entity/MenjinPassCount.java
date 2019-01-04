package com.zcreate.offline.gyjg.entity;


import java.io.Serializable;

/**
 * @ClassName: MenjinPassCount
 * @Author: majun
 * @CreateDate: 2018/11/28 9:13
 * @Version: 1.0
 * @Description: 科目一门禁通过统计类
 */

public class MenjinPassCount implements Serializable {

    private String auditDate;
    private String examroomCode;
    private Integer auditNum;

    public MenjinPassCount() {
    }

    public MenjinPassCount(String auditDate, String examroomCode, Integer auditNum) {
        this.auditDate = auditDate;
        this.examroomCode = examroomCode;
        this.auditNum = auditNum;
    }

    public String getAuditDate() {
        return auditDate;
    }

    public void setAuditDate(String auditDate) {
        this.auditDate = auditDate;
    }

    public String getExamroomCode() {
        return examroomCode;
    }

    public void setExamroomCode(String examroomCode) {
        this.examroomCode = examroomCode;
    }

    public Integer getAuditNum() {
        return auditNum;
    }

    public void setAuditNum(Integer auditNum) {
        this.auditNum = auditNum;
    }

    @Override
    public String toString() {
        return "MenjinPassCount{" +
                "auditDate='" + auditDate + '\'' +
                ", examroomCode='" + examroomCode + '\'' +
                ", auditNum=" + auditNum +
                '}';
    }
}
