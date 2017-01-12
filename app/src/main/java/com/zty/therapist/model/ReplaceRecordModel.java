package com.zty.therapist.model;

import java.io.Serializable;

/**
 * Created by tianyu on 2017/1/2.
 */

public class ReplaceRecordModel implements Serializable {

    public static final String strState1 = "审批中";
    public static final String strState2 = "待重新审批";
    public static final String strState3 = "待确认";
    public static final String strState4 = "已确定";
    public static final String strState5 = "已取消";


    /**
     * address : 来广营西路恒祥小区12栋1单元1508
     * age : 68
     * auditor : 7fff984d4892413c925ed2909e2a0b92
     * auditorNm : 王丹
     * createDate : 2017-01-12 11:12
     * description : 老人十分慈祥，只需要帮助收拾一下，做好康复准备就可以了
     * elderNm : 张宏宇
     * endDate : 2017-01-14 19:00
     * groupNumber : ZH-10001
     * id : f0bc31c130fc458fbd6256192a0ff5af
     * integral : 90
     * item : 脑中风护理
     * mobile : 15968478963
     * officeId : 2
     * sex : 男
     * startDate : 2017-01-14 08:00
     * state : 0  ( 0：待处理；1：待重新处理；2：待确认；3：已确定；4：已取消)
     * teacherNm : 段景瑞
     * userId : 498731624dee40d89aae4aa0f8798204
     * delTime:处理时间
     * confirmState:确认状态（0不同意1同意）
     * confirmTime:确认时间
     * relay:替班人ID
     * relayNm:替班人姓名
     */

    private String address;
    private int age;
    private String auditor;
    private String auditorNm;
    private String createDate;
    private String description;
    private String elderNm;
    private String endDate;
    private String groupNumber;
    private String id;
    private int integral;
    private String item;
    private String mobile;
    private String officeId;
    private String sex;
    private String startDate;
    private int state;
    private String teacherNm;
    private String userId;
    private String delTime;
    private int confirmState;
    private String confirmTime;
    private String relay;
    private String relayNm;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAuditor() {
        return auditor;
    }

    public void setAuditor(String auditor) {
        this.auditor = auditor;
    }

    public String getAuditorNm() {
        return auditorNm;
    }

    public void setAuditorNm(String auditorNm) {
        this.auditorNm = auditorNm;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getElderNm() {
        return elderNm;
    }

    public void setElderNm(String elderNm) {
        this.elderNm = elderNm;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(String groupNumber) {
        this.groupNumber = groupNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getIntegral() {
        return integral;
    }

    public void setIntegral(int integral) {
        this.integral = integral;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getOfficeId() {
        return officeId;
    }

    public void setOfficeId(String officeId) {
        this.officeId = officeId;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDelTime() {
        return delTime;
    }

    public void setDelTime(String delTime) {
        this.delTime = delTime;
    }

    public int getConfirmState() {
        return confirmState;
    }

    public void setConfirmState(int confirmState) {
        this.confirmState = confirmState;
    }

    public String getConfirmTime() {
        return confirmTime;
    }

    public void setConfirmTime(String confirmTime) {
        this.confirmTime = confirmTime;
    }

    public String getRelay() {
        return relay;
    }

    public void setRelay(String relay) {
        this.relay = relay;
    }

    public String getRelayNm() {
        return relayNm;
    }

    public void setRelayNm(String relayNm) {
        this.relayNm = relayNm;
    }
}
