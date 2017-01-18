package com.zty.therapist.model;

/**
 * Created by zty on 2017/1/18.
 */

public class AddressModel {


    /**
     * address : 河北省秦皇岛市北戴河区嗯呀五天噢噢噢哦哦
     * area : 130304
     * city : 130300
     * createDate : 1484703187000
     * id : 541b22b252ab4c399a1f078c4358c6b9
     * isDefault : 1
     * mobile : 13874569874
     * province : 130000
     * searchFromPage : false
     * takeName : 张潇
     * userId : 498731624dee40d89aae4aa0f8798204
     */

    private String address;
    private String area;
    private String city;
    private String createDate;
    private String id;
    private int isDefault;
    private String mobile;
    private String province;
    private boolean searchFromPage;
    private String takeName;
    private String userId;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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

    public int getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(int isDefault) {
        this.isDefault = isDefault;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public boolean isSearchFromPage() {
        return searchFromPage;
    }

    public void setSearchFromPage(boolean searchFromPage) {
        this.searchFromPage = searchFromPage;
    }

    public String getTakeName() {
        return takeName;
    }

    public void setTakeName(String takeName) {
        this.takeName = takeName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
