package com.zty.therapist.model;

/**
 * Created by zty on 2016/12/23.
 */

public class DoctorModel {

    /**
     * city : 1
     * createDate : 2017-01-05 20:12:14.0
     * departmentId : 27c74efe144144fe945c809963175fde
     * departmentNm : 神经外科
     * doctorNm : 焦力群
     * expert : 脑血管病专业，擅长脑梗塞所有的手术和支架治疗，包括颈动脉狭窄、脑血管狭窄、烟雾病等
     * healthCurrencyType : 2
     * hospital : 首都医科大学宣武医院
     * id : ba89ef2d1d994003bf4572f58475f2d5
     * imgUrl : http://14.29.68.166:8862/doctor/2017/01/d8f5b39c199f4143b6b4aa6fc619b304.jpg
     * level : 1
     * mobile : 13822699642
     * remarks : 宣武医院介入中心副主任
     * sex : 男
     * thumbImg : http://14.29.68.166:8862/doctor/2017/01/d8f5b39c199f4143b6b4aa6fc619b304_small.jpg
     * type : 3
     * typeNm : 主任医师
     * updateDate : 2017-01-05 20:12:22.0
     * userId : 7e321321f39d4474af283165deee74ed
     */

    private String city;
    private String createDate;
    private String departmentId;
    private String departmentNm;
    private String doctorNm;
    private String expert;
    private String healthCurrencyType;
    private int memberHealthCurrency; //提供者健康币
    private int transactorHealthCurrency; //处理人健康币
    private String hospital;
    private String id;
    private String imgUrl;
    private String level;
    private String mobile;
    private String remarks;
    private String sex;
    private String thumbImg;
    private String type;
    private String typeNm;
    private String updateDate;
    private String userId;

    public int getMemberHealthCurrency() {
        return memberHealthCurrency;
    }

    public void setMemberHealthCurrency(int memberHealthCurrency) {
        this.memberHealthCurrency = memberHealthCurrency;
    }

    public int getTransactorHealthCurrency() {
        return transactorHealthCurrency;
    }

    public void setTransactorHealthCurrency(int transactorHealthCurrency) {
        this.transactorHealthCurrency = transactorHealthCurrency;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentNm() {
        return departmentNm;
    }

    public void setDepartmentNm(String departmentNm) {
        this.departmentNm = departmentNm;
    }

    public String getDoctorNm() {
        return doctorNm;
    }

    public void setDoctorNm(String doctorNm) {
        this.doctorNm = doctorNm;
    }

    public String getExpert() {
        return expert;
    }

    public void setExpert(String expert) {
        this.expert = expert;
    }

    public String getHealthCurrencyType() {
        return healthCurrencyType;
    }

    public void setHealthCurrencyType(String healthCurrencyType) {
        this.healthCurrencyType = healthCurrencyType;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getThumbImg() {
        return thumbImg;
    }

    public void setThumbImg(String thumbImg) {
        this.thumbImg = thumbImg;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTypeNm() {
        return typeNm;
    }

    public void setTypeNm(String typeNm) {
        this.typeNm = typeNm;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
