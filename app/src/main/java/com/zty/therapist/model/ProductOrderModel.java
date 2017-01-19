package com.zty.therapist.model;

import java.io.Serializable;

/**
 * Created by zty on 2016/12/27.
 */

public class ProductOrderModel implements Serializable {


    /**
     * address : 河北省秦皇岛市北戴河区河北省秦皇岛市北戴河区嗯呀五天噢噢噢哦哦
     * addressId : 541b22b252ab4c399a1f078c4358c6b9
     * area : 河北省秦皇岛市北戴河区
     * createDate : 2017-01-18 13:11
     * healthCurrency : 30
     * id : da4bd7fdb97e4c56ac63a72566c909f8
     * imgUrl : http://14.29.68.166:8862/healthProducts/2017/01/26514cb1895248b2aeb7f0f6b92d1e8e.jpg
     * mobile : 13874569874
     * orderNumber : SPKFSDDBH100000
     * payState : 0
     * price : 128
     * productId : 0aa49e868cec402b926c783a041c0f12
     * productNm : 新西兰汤普森欧米伽3深海鱼油软胶囊
     * quantity : 1
     * remarks : 产地新西兰，主要功效为可协助清除附着于血管壁上的胆固醇与硬化斑，阻止中风或心肌梗塞的发生。有助于保持血管通畅，预防血栓产生，预防动脉硬化及血管阻塞的发生。适当补充DHA可以增强记忆力，预防老年痴呆。帮助维护关节灵活和健康肌肤，产品本身采用野生鱼类加工而成，并通过常规的汞、重金属和农药测试。
     * state : 0
     * takeName : 张潇
     * teacherNm : 段景瑞
     * thumbImg : http://14.29.68.166:8862/healthProducts/2017/01/26514cb1895248b2aeb7f0f6b92d1e8e_small.jpg
     * totalPrice : 128
     * userId : 498731624dee40d89aae4aa0f8798204
     */

    private String address;
    private String addressId;
    private String area;
    private String createDate;
    private int healthCurrency;
    private String id;
    private String imgUrl;
    private String mobile;
    private String orderNumber;
    private int payState;
    private String price;
    private String productId;
    private String productNm;
    private int quantity;
    private String remarks;
    private int state;
    private String takeName;
    private String teacherNm;
    private String thumbImg;
    private String totalPrice;
    private String userId;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public int getPayState() {
        return payState;
    }

    public void setPayState(int payState) {
        this.payState = payState;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductNm() {
        return productNm;
    }

    public void setProductNm(String productNm) {
        this.productNm = productNm;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getTakeName() {
        return takeName;
    }

    public void setTakeName(String takeName) {
        this.takeName = takeName;
    }

    public String getTeacherNm() {
        return teacherNm;
    }

    public void setTeacherNm(String teacherNm) {
        this.teacherNm = teacherNm;
    }

    public String getThumbImg() {
        return thumbImg;
    }

    public void setThumbImg(String thumbImg) {
        this.thumbImg = thumbImg;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
