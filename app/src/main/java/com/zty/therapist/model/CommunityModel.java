package com.zty.therapist.model;

import java.util.List;

/**
 * Created by zty on 2016/12/19.
 */

public class CommunityModel {

    /**
     * browsTimes : 0
     * content : 还有吗
     * createDate : 2017-01-09 13:35
     * id : f32b39540d334d55b4c13de877e5c6f7
     * likesTimes : 0
     * mobile : 13877900762
     * nickName : 小杯子拉
     * pictures : [{"forumId":"f32b39540d334d55b4c13de877e5c6f7","id":"df48745a89fd4568afc06618549c7047","picture":"http://14.29.68.166:8862/healthArchives/2017/01/4bf3f6ac97ec4116b92c3d0f2c8c8f12.jpg","thumb":"http://14.29.68.166:8862/healthArchives/2017/01/4bf3f6ac97ec4116b92c3d0f2c8c8f12_small.jpg"},{"forumId":"f32b39540d334d55b4c13de877e5c6f7","id":"db32505a1be74591a1206e9f89419e26","picture":"http://14.29.68.166:8862/healthArchives/2017/01/553030454fa74a2b8d56296c422d530e.jpg","thumb":"http://14.29.68.166:8862/healthArchives/2017/01/553030454fa74a2b8d56296c422d530e_small.jpg"},{"forumId":"f32b39540d334d55b4c13de877e5c6f7","id":"a955592cc1ee4ec28a580518b6ad0e47","picture":"http://14.29.68.166:8862/healthArchives/2017/01/87ea3fa4f9824bb1924418e0f89666d2.jpg","thumb":"http://14.29.68.166:8862/healthArchives/2017/01/87ea3fa4f9824bb1924418e0f89666d2_small.jpg"},{"forumId":"f32b39540d334d55b4c13de877e5c6f7","id":"8af3de54bb3e47a7b3468de0e439817d","picture":"http://14.29.68.166:8862/healthArchives/2017/01/1c28628a7b4b47a68bab357a7a8bf7ea.jpg","thumb":"http://14.29.68.166:8862/healthArchives/2017/01/1c28628a7b4b47a68bab357a7a8bf7ea_small.jpg"}]
     * postedPhoto : http://14.29.68.166:8862/healthArchives/2017/01/8066abadd6364869ae6218da31457ec5.jpg
     * postedPhotoThumb : http://14.29.68.166:8862/healthArchives/2017/01/8066abadd6364869ae6218da31457ec5_small.jpg
     * replayTimes : 1
     * replays : [{"commentObject":"498731624dee40d89aae4aa0f8798204","commentObjectNm":"段景瑞","content":"还有一个呀","createDate":"2017-01-10 19:06","forumId":"f32b39540d334d55b4c13de877e5c6f7","id":"a7750c75064f49a3aae44c37508336c2","mobile":"13877900762","nickName":"小杯子拉","teacherNm":"段景瑞","userId":"498731624dee40d89aae4aa0f8798204"}]
     * userId : 498731624dee40d89aae4aa0f8798204
     */

    private int browsTimes;
    private String content;
    private String createDate;
    private String id;
    private int likesTimes;
    private String mobile;
    private String nickName;
    private String postedPhoto;
    private String postedPhotoThumb;
    private int replayTimes;
    private String userId;
    private List<PicturesBean> pictures;
    private List<ReplaysBean> replays;

    public int getBrowsTimes() {
        return browsTimes;
    }

    public void setBrowsTimes(int browsTimes) {
        this.browsTimes = browsTimes;
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

    public int getLikesTimes() {
        return likesTimes;
    }

    public void setLikesTimes(int likesTimes) {
        this.likesTimes = likesTimes;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPostedPhoto() {
        return postedPhoto;
    }

    public void setPostedPhoto(String postedPhoto) {
        this.postedPhoto = postedPhoto;
    }

    public String getPostedPhotoThumb() {
        return postedPhotoThumb;
    }

    public void setPostedPhotoThumb(String postedPhotoThumb) {
        this.postedPhotoThumb = postedPhotoThumb;
    }

    public int getReplayTimes() {
        return replayTimes;
    }

    public void setReplayTimes(int replayTimes) {
        this.replayTimes = replayTimes;
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

    public List<ReplaysBean> getReplays() {
        return replays;
    }

    public void setReplays(List<ReplaysBean> replays) {
        this.replays = replays;
    }

    public static class PicturesBean {
        /**
         * forumId : f32b39540d334d55b4c13de877e5c6f7
         * id : df48745a89fd4568afc06618549c7047
         * picture : http://14.29.68.166:8862/healthArchives/2017/01/4bf3f6ac97ec4116b92c3d0f2c8c8f12.jpg
         * thumb : http://14.29.68.166:8862/healthArchives/2017/01/4bf3f6ac97ec4116b92c3d0f2c8c8f12_small.jpg
         */

        private String forumId;
        private String id;
        private String picture;
        private String thumb;

        public String getForumId() {
            return forumId;
        }

        public void setForumId(String forumId) {
            this.forumId = forumId;
        }

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

    public static class ReplaysBean {
        /**
         * commentObject : 498731624dee40d89aae4aa0f8798204
         * commentObjectNm : 段景瑞
         * content : 还有一个呀
         * createDate : 2017-01-10 19:06
         * forumId : f32b39540d334d55b4c13de877e5c6f7
         * id : a7750c75064f49a3aae44c37508336c2
         * mobile : 13877900762
         * nickName : 小杯子拉
         * teacherNm : 段景瑞
         * userId : 498731624dee40d89aae4aa0f8798204
         */

        private String commentObject;
        private String commentObjectNm;
        private String content;
        private String createDate;
        private String forumId;
        private String id;
        private String mobile;
        private String nickName;
        private String teacherNm;
        private String userId;

        public String getCommentObject() {
            return commentObject;
        }

        public void setCommentObject(String commentObject) {
            this.commentObject = commentObject;
        }

        public String getCommentObjectNm() {
            return commentObjectNm;
        }

        public void setCommentObjectNm(String commentObjectNm) {
            this.commentObjectNm = commentObjectNm;
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

        public String getForumId() {
            return forumId;
        }

        public void setForumId(String forumId) {
            this.forumId = forumId;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
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
    }
}
