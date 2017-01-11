package com.zty.therapist.model;

import java.util.List;

/**
 * Created by zty on 2017/1/11.
 */

public class InfoPhotoModel {


    /**
     * activityId : 24d83731107c4080a6bba5c69d702a3f
     * createDate : 2017-01-11 13:58
     * id : c220fff836a94df7b11bb4018da637a0
     * pictures : [{"id":"aca4e9585e8d46b6ae832659aa9409f9","picture":"http://14.29.68.166:8862/healthArchives/2017/01/95685472f69f4314a80706341d8db24c.jpg","thumb":"http://14.29.68.166:8862/healthArchives/2017/01/95685472f69f4314a80706341d8db24c_small.jpg"}]
     * remarks : 办的很不错
     * teacherNm : 段景瑞
     * userId : 498731624dee40d89aae4aa0f8798204
     */

    private String activityId;
    private String createDate;
    private String id;
    private String remarks;
    private String teacherNm;
    private String userId;
    private List<PicturesBean> pictures;

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
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

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
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

    public List<PicturesBean> getPictures() {
        return pictures;
    }

    public void setPictures(List<PicturesBean> pictures) {
        this.pictures = pictures;
    }

    public static class PicturesBean {
        /**
         * id : aca4e9585e8d46b6ae832659aa9409f9
         * picture : http://14.29.68.166:8862/healthArchives/2017/01/95685472f69f4314a80706341d8db24c.jpg
         * thumb : http://14.29.68.166:8862/healthArchives/2017/01/95685472f69f4314a80706341d8db24c_small.jpg
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
