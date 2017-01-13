package com.zty.therapist.model;

/**
 * Created by tianyu on 2017/1/2.
 */

public class HouseRentModel {


    /**
     * contacts : 张海林
     * contactsNumber : 13578654126
     * content : 硕士生、博士生想找个好工作?机会来了!昨日，记者从河南省大中专学生就业服务中心获悉，12月25日，省教育厅、省发改委、省工信委、省人社厅、省卫计委及省国资委6部门将联合开展“河南省2017届高校毕业研究生就业专场双选会”，为全省27所高等院校、科研院所的硕士、博士毕业生找“东家”。这一类型的招聘会在河南省属首次，在全国也不多见。
     * createDate : 2017-01-13 16:18
     * id : 5ec6c15234a1414cbbf42c07c51d12ba
     * state : 0
     * title : 找工作
     * updateDate : 2017-01-13 16:18
     * userId : 7fff984d4892413c925ed2909e2a0b92
     */

    private String contacts;
    private String contactsNumber;
    private String content;
    private String createDate;
    private String id;
    private int state;
    private String title;
    private String updateDate;
    private String userId;

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getContactsNumber() {
        return contactsNumber;
    }

    public void setContactsNumber(String contactsNumber) {
        this.contactsNumber = contactsNumber;
    }

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

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
