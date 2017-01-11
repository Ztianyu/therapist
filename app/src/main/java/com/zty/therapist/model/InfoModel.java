package com.zty.therapist.model;

import java.util.List;

/**
 * Created by zty on 2016/12/29.
 */

public class InfoModel {


    /**
     * activePlace : 景山公园南门
     * activeSite : 景山公园
     * activityNm : 太极拳
     * contacts : 王小丽
     * contactsNumber : 15649784687
     * content : 太极拳晨练
     * createDate : 2017-01-10 15:04
     * deadlineTime : 2017-01-11 18:00
     * id : 24d83731107c4080a6bba5c69d702a3f
     * isNumber : 1
     * mattersAttention : 带好水
     * pictures : [{"id":"00481e9f1a944e48b26c08db828e00c2","picture":"http://14.29.68.166:8862/healthArchives/2017/01/27424b5cf5e744d58797cbd205a36e80.jpg","thumb":"http://14.29.68.166:8862/healthArchives/2017/01/27424b5cf5e744d58797cbd205a36e80_small.jpg"}]
     * show : false
     * startTime : 2017-01-12 06:30
     * state : 0
     * teacherNms : 段景瑞
     */

    private String activePlace;
    private String activeSite;
    private String activityNm;
    private String contacts;
    private String contactsNumber;
    private String content;
    private String createDate;
    private String deadlineTime;
    private String id;
    private int isNumber;
    private String mattersAttention;
    private boolean show;
    private String startTime;
    private String state;
    private String teacherNms;
    private List<PicturesBean> pictures;

    public String getActivePlace() {
        return activePlace;
    }

    public void setActivePlace(String activePlace) {
        this.activePlace = activePlace;
    }

    public String getActiveSite() {
        return activeSite;
    }

    public void setActiveSite(String activeSite) {
        this.activeSite = activeSite;
    }

    public String getActivityNm() {
        return activityNm;
    }

    public void setActivityNm(String activityNm) {
        this.activityNm = activityNm;
    }

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

    public String getDeadlineTime() {
        return deadlineTime;
    }

    public void setDeadlineTime(String deadlineTime) {
        this.deadlineTime = deadlineTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getIsNumber() {
        return isNumber;
    }

    public void setIsNumber(int isNumber) {
        this.isNumber = isNumber;
    }

    public String getMattersAttention() {
        return mattersAttention;
    }

    public void setMattersAttention(String mattersAttention) {
        this.mattersAttention = mattersAttention;
    }

    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTeacherNms() {
        return teacherNms;
    }

    public void setTeacherNms(String teacherNms) {
        this.teacherNms = teacherNms;
    }

    public List<PicturesBean> getPictures() {
        return pictures;
    }

    public void setPictures(List<PicturesBean> pictures) {
        this.pictures = pictures;
    }

    public static class PicturesBean {
        /**
         * id : 00481e9f1a944e48b26c08db828e00c2
         * picture : http://14.29.68.166:8862/healthArchives/2017/01/27424b5cf5e744d58797cbd205a36e80.jpg
         * thumb : http://14.29.68.166:8862/healthArchives/2017/01/27424b5cf5e744d58797cbd205a36e80_small.jpg
         */

        private String id;
        private String picture;
        private String thumb;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }

        public String getThumb() {
            return thumb;
        }

        public void setThumb(String thumb) {
            this.thumb = thumb;
        }
    }
}
