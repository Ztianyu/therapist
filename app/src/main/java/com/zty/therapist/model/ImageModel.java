package com.zty.therapist.model;

/**
 * 上传文件后返回数据
 * Created by zty on 2017/1/9.
 */

public class ImageModel {


    /**
     * audioThumbUrl : /healthArchives/2017/01/5b50df55866c4429af0ac97d6a019516_small.jpg
     * audioUrl : /healthArchives/2017/01/5b50df55866c4429af0ac97d6a019516.jpg
     * fileType : jpg
     * website : http://14.29.68.166:8862
     */

    private String audioThumbUrl;
    private String audioUrl;
    private String fileType;
    private String website;

    public String getAudioThumbUrl() {
        return audioThumbUrl;
    }

    public void setAudioThumbUrl(String audioThumbUrl) {
        this.audioThumbUrl = audioThumbUrl;
    }

    public String getAudioUrl() {
        return audioUrl;
    }

    public void setAudioUrl(String audioUrl) {
        this.audioUrl = audioUrl;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
}
