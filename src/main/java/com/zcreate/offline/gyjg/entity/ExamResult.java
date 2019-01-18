package com.zcreate.offline.gyjg.entity;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @ClassName: ExamResult
 * @Author: majun
 * @CreateDate: 2018/12/29 15:03
 * @Version: 1.0
 * @Description: 考试结果
 */

public class ExamResult {

    private String idCard;
    private String name;
    private String schoolId;
    private String schoolName;
    private String examineeId;
    private String examineeName;
    private Date examDate;
    private double score;
    private String examResult;
    private String supervisorName;
    private Timestamp updateTime;

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

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

    public Date getExamDate() {
        return examDate;
    }

    public void setExamDate(Date examDate) {
        this.examDate = examDate;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public String getExamResult() {
        return examResult;
    }

    public void setExamResult(String examResult) {
        this.examResult = examResult;
    }

    public String getSupervisorName() {
        return supervisorName;
    }

    public void setSupervisorName(String supervisorName) {
        this.supervisorName = supervisorName;
    }

    @Override
    public String toString() {
        return "ExamResult{" +
                "idCard='" + idCard + '\'' +
                ", name='" + name + '\'' +
                ", schoolId='" + schoolId + '\'' +
                ", schoolName='" + schoolName + '\'' +
                ", examineeId='" + examineeId + '\'' +
                ", examineeName='" + examineeName + '\'' +
                ", examDate=" + examDate +
                ", score=" + score +
                ", examResult='" + examResult + '\'' +
                ", updateTime=" + updateTime +
                '}';
    }
}
