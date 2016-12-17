package com.zty.therapist.model;

/**
 * Created by zty on 2016/12/3.
 */

public class GuideModel {
    private int type;//0：知识；1：视频
    private String title;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
