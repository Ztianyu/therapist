package com.zty.therapist.model;

import java.io.Serializable;

/**
 * Created by zty on 2016/12/27.
 */

public class DoctorOrderModel implements Serializable {

    /**
     * address : 和我王一诺咯咯咙哦咯兔子呜呜呜呜咯徐誉滕呜呜呜他
     * city : 1
     * contactsTel : 13874698745
     * createDate : 2017-01-17 14:43
     * doctorId : f958602207b3439f92177e4769c66f62
     * doctorNm : 钱素云
     * endDate : 2017-01-27
     * expert : 内科常见病，儿科危重症、呼吸及感染性疾病
     * hospital : 北京儿童医院
     * id : d4e59f05e94047e8be6d0fd592a650bb
     * imgUrl : http://14.29.68.166:8862/doctor/2017/01/69b9e1d2785d4de8857757259ce95e8d.png
     * item : 骨髓移植
     * memberHealthCurrency : 5000
     * mobile : 13877900762
     * orderNumber : YSKFSDDBH100000
     * patientNm : 王天琪
     * remarks : 医学博士，教授，博士生导师，特级专家，重症医学科主任。先后在美国BUFFALO儿童医院，美国纽约MONTEFIORE儿童医院和意大利罗马BAMBINO GESU儿童医院进修学习。曾获北京市卫生局科技进步一等奖，北京儿童医院诸福棠/吴瑞萍儿科奖励基金中青年医学成就一等奖，首都医科大学教学成果二等奖，北京卫生系统先进个人和北京地区百名优秀医生。先后获北京市科技新星项目、北京市优秀人才项目、首都医学发展科研基金等资助。发表论文100余篇，SCI4篇，主编、副主编专著4部，参编10余部。主要研究方向为儿科重症的基础与临床研究，包括各种感染性疾病(如重症肺炎、脑炎、严重脓毒症、胃肠道感染、手足口病、EB及CMV病毒感染等)，呼吸系统疾病及呼吸衰竭、中毒及意外伤害、休克的诊治及营养支持治疗等。
     * startDate : 2017-01-26
     * state : 0
     * teacherNm : 段景瑞
     * thumbImg : http://14.29.68.166:8862/doctor/2017/01/69b9e1d2785d4de8857757259ce95e8d_small.png
     * transactor : 6d911d325cb64a34a05fa088221ca511
     * transactorHealthCurrency : 3000
     * transactorNm : 高虹
     * type : 3
     * userId : 498731624dee40d89aae4aa0f8798204
     */

    private String address;
    private String city;
    private String contactsTel;
    private String createDate;
    private String doctorId;
    private String doctorNm;
    private String endDate;
    private String expert;
    private String hospital;
    private String id;
    private String imgUrl;
    private String item;
    private int memberHealthCurrency;
    private String mobile;
    private String orderNumber;
    private String patientNm;
    private String remarks;
    private String startDate;
    private int state;
    private String teacherNm;
    private String thumbImg;
    private String transactor;
    private int transactorHealthCurrency;
    private String transactorNm;
    private int type;
    private String userId;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getContactsTel() {
        return contactsTel;
    }

    public void setContactsTel(String contactsTel) {
        this.contactsTel = contactsTel;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getDoctorNm() {
        return doctorNm;
    }

    public void setDoctorNm(String doctorNm) {
        this.doctorNm = doctorNm;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getExpert() {
        return expert;
    }

    public void setExpert(String expert) {
        this.expert = expert;
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

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getMemberHealthCurrency() {
        return memberHealthCurrency;
    }

    public void setMemberHealthCurrency(int memberHealthCurrency) {
        this.memberHealthCurrency = memberHealthCurrency;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getPatientNm() {
        return patientNm;
    }

    public void setPatientNm(String patientNm) {
        this.patientNm = patientNm;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getTeacherNm() {
        return teacherNm;
    }

    public void setTeacherNm(String teacherNm) {
        this.teacherNm = teacherNm;
    }

    public String getThumbImg() {
        return thumbImg;
    }

    public void setThumbImg(String thumbImg) {
        this.thumbImg = thumbImg;
    }

    public String getTransactor() {
        return transactor;
    }

    public void setTransactor(String transactor) {
        this.transactor = transactor;
    }

    public int getTransactorHealthCurrency() {
        return transactorHealthCurrency;
    }

    public void setTransactorHealthCurrency(int transactorHealthCurrency) {
        this.transactorHealthCurrency = transactorHealthCurrency;
    }

    public String getTransactorNm() {
        return transactorNm;
    }

    public void setTransactorNm(String transactorNm) {
        this.transactorNm = transactorNm;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
