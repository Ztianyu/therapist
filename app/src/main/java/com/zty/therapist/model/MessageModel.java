package com.zty.therapist.model;

/**
 * Created by zty on 2016/12/27.
 */

public class MessageModel {

    /**
     * content : 加入组后系统赠送您积分，并可申请替班以及与其他组员沟通互动
     * createDate : 2017-01-13 15:11
     * id : 2258416f907342d39bfb6010589f0672
     * operTeacherNm : 段景瑞
     * operation : 邀请加入组
     * operator : 498731624dee40d89aae4aa0f8798204
     * state : 0
     * teacherNm : 王丹
     * title : 加入组通知
     * type : 1
     * userId : 7fff984d4892413c925ed2909e2a0b92
     */

    private String content;
    private String createDate;
    private String id;
    private String operTeacherNm;
    private String operation;
    private String operator;
    private int state;
    private String teacherNm;
    private String title;
    private int type;
    private String userId;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOperTeacherNm() {
        return operTeacherNm;
    }

    public void setOperTeacherNm(String operTeacherNm) {
        this.operTeacherNm = operTeacherNm;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
