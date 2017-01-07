package com.zty.therapist.model;

/**
 * Created by zty on 2016/12/3.
 */

public class TestModel {


    /**
     * createBy : 1
     * createDate : 2017-01-05 15:15
     * createNm : 系统管理员
     * id : 05b8841013b448ada6485ed879e28395
     * loreUrl : http://14.29.68.166:8862/healthLore/2017/01/b95d7f34a3c84f949133bd2663b3c003.pdf
     * sort : 21
     * state : 1
     * title : 运动损伤的治疗与康复
     * type : 1
     * typeNm : 骨科类
     */

    private String createBy;
    private String createDate;
    private String createNm;
    private String id;
    private String loreUrl;//知识url
    private String questionUrl;//试题url
    private int sort;
    private String state;
    private String title;
    private String type;
    private String typeNm;

    public String getQuestionUrl() {
        return questionUrl;
    }

    public void setQuestionUrl(String questionUrl) {
        this.questionUrl = questionUrl;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getCreateNm() {
        return createNm;
    }

    public void setCreateNm(String createNm) {
        this.createNm = createNm;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLoreUrl() {
        return loreUrl;
    }

    public void setLoreUrl(String loreUrl) {
        this.loreUrl = loreUrl;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
}
