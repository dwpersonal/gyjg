package com.zcreate.offline.gyjg.entity;


import java.sql.Timestamp;

/**
 * @ClassName: SchoolInfo
 * @Author: majun
 * @CreateDate: 2018/12/29 15:08
 * @Version: 1.0
 * @Description: 驾校信息
 */

public class SchoolInfo {
    private String schoolName;
    private String schoolId;
    private String telephone;
    private String address;
    private String lawPerson;
    private String business;
    private String photo;
    private Timestamp update;

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Timestamp getUpdate() {
        return update;
    }

    public void setUpdate(Timestamp update) {
        this.update = update;
    }

    public String getLawPerson() {
        return lawPerson;
    }

    public void setLawPerson(String lawPerson) {
        this.lawPerson = lawPerson;
    }

    @Override
    public String toString() {
        return "SchoolInfo{" +
                "schoolName='" + schoolName + '\'' +
                ", schoolId='" + schoolId + '\'' +
                ", telephone='" + telephone + '\'' +
                ", address='" + address + '\'' +
                ", business='" + business + '\'' +
                ", photo='" + photo + '\'' +
                ", update=" + update +
                '}';
    }
}
