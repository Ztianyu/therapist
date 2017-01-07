package com.zty.therapist.model;

import java.io.Serializable;

/**
 * Created by zty on 2016/12/22.
 */

public class HealthProductModel implements Serializable{

    /**
     * createDate : 2017-01-05 16:34
     * healthCurrency : 30
     * id : 0aa49e868cec402b926c783a041c0f12
     * imgUrl : http://14.29.68.166:8862/healthProducts/2017/01/26514cb1895248b2aeb7f0f6b92d1e8e.jpg
     * price : 128
     * productNm : 新西兰汤普森欧米伽3深海鱼油软胶囊
     * remarks : 产地新西兰，主要功效为可协助清除附着于血管壁上的胆固醇与硬化斑，阻止中风或心肌梗塞的发生。有助于保持血管通畅，预防血栓产生，预防动脉硬化及血管阻塞的发生。适当补充DHA可以增强记忆力，预防老年痴呆。帮助维护关节灵活和健康肌肤，产品本身采用野生鱼类加工而成，并通过常规的汞、重金属和农药测试。
     * state : 0
     * thumbImg : http://14.29.68.166:8862/healthProducts/2017/01/26514cb1895248b2aeb7f0f6b92d1e8e_small.jpg
     * type : 0
     * updateDate : 2017-01-05 16:34
     */

    private String createDate;
    private int healthCurrency;
    private String id;
    private String imgUrl;
    private String price;
    private String productNm;
    private String remarks;
    private String state;
    private String thumbImg;
    private String type;
    private String updateDate;

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public int getHealthCurrency() {
        return healthCurrency;
    }

    public void setHealthCurrency(int healthCurrency) {
        this.healthCurrency = healthCurrency;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getProductNm() {
        return productNm;
    }

    public void setProductNm(String productNm) {
        this.productNm = productNm;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getThumbImg() {
        return thumbImg;
    }

    public void setThumbImg(String thumbImg) {
        this.thumbImg = thumbImg;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }
}
