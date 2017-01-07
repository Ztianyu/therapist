package com.zty.therapist.model;

/**
 * Created by zty on 2016/12/3.
 */

public class LectureModel {


    /**
     * createDate : 2017-01-05 15:26
     * id : 6b5fd08e6f694e44acfed80ebbd1111d
     * state : 1
     * title : 翻身训练
     * type : 3
     * videoUrl : http://14.29.68.166:8862/healthLecture/2017/01/ba47603dcd3f4063923bb05a49bcce50.mpg
     */

    private String createDate;
    private String id;
    private String state;
    private String title;
    private String type;
    private String videoUrl;

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

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }
}
