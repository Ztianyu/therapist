package com.zty.therapist.url;

/**
 * Created by zty on 2017/1/5.
 */

public class Urls {

    public static String HOST_PROJECT = "http://14.29.68.166:8181/rehabilitationSystem";
    public static String HOST = HOST_PROJECT + "/admin/ws";

    //登录
    public static String sysLogin = HOST + "/user/sysLogin";

    //医生列表
    public static String getDoctorList = HOST + "/doctor/getDoctorList";

    //保健品列表
    public static String getHealthProductsList = HOST + "/healthProducts/getHealthProductsList";

    public static String getDissessOption = HOST + "/dict/getDissessOption";

    public static String getHealthLoreList = HOST + "/healthLore/getHealthLoreList";

    public static String getTestQuestionsList = HOST + "/testQuestions/getTestQuestionsList";

    public static String getVideoTypeOption = HOST + "/dict/getVideoTypeOption";

    public static String getHealthLectureList = HOST + "/healthLecture/getHealthLectureList";

    public static String getProductTypeOption = HOST + "/dict/getProductTypeOption";

    public static String getDoctorCityOption = HOST + "/dict/getDoctorCityOption";

    public static String getDoctorDetail = HOST + "/doctor/getDoctorDetail";

    public static String getRehabilitationTeacher = HOST + "/rehabilitationTeacher/getRehabilitationTeacher";

    public static String updatePwd = HOST + "/user/updatePwd";

    public static String setNewPwd = HOST + "/user/setNewPwd";

    public static String register = HOST + "/user/register";

    public static String submitForum = HOST + "/healthForum/submitForum";

    public static String uploadAndroidServlet = HOST_PROJECT + "/uploadAndroidServlet";

    public static String getHealthForumList = HOST + "/healthForum/getHealthForumList";

    public static String getHealthForum = HOST + "/healthForum/getHealthForum";

    public static String submitForumReplay = HOST + "/forumReplay/submitForumReplay";

    public static String setNickName = HOST + "/rehabilitationTeacher/setNickName";

    public static String getCityActivityList = HOST + "/cityActivity/getCityActivityList";

    public static String submitCityActivity = HOST + "/cityActivity/submitCityActivity";

    public static String getCityPhotoList = HOST + "/cityPhoto/getCityPhotoList";

    public static String getCityActivity = HOST + "/cityActivity/getCityActivity";

    public static String getCityEnrollList = HOST + "/cityEnroll/getCityEnrollList";

    public static String submitCityEnroll = HOST + "/cityEnroll/submitCityEnroll";

    public static String deleteCityEnroll = HOST + "/cityEnroll/deleteCityEnroll";

    public static String submitCityPhoto = HOST + "/cityPhoto/submitCityPhoto";

    public static String getGroupMemberList = HOST + "/rehabilitationGroup/getGroupMemberList";

    public static String submitShiftRecord = HOST + "/shiftRecord/submitShiftRecord";

    public static String getShiftRecordList = HOST + "/shiftRecord/getShiftRecordList";

    public static String getAuditShiftRecordList = HOST + "/shiftRecord/getAuditShiftRecordList";

    public static String confirmOrder = HOST + "/shiftRecord/confirmOrder";

    public static String cancelRelay = HOST + "/shiftRecord/cancelRelay";

    public static String getShiftRecord = HOST + "/shiftRecord/getShiftRecord";

    public static String distributeRelay = HOST + "/shiftRecord/distributeRelay";

    public static String getAbilityOption = HOST + "/dict/getAbilityOption";

    public static String setAbility = HOST + "/rehabilitationTeacher/setAbility";

    public static String getMemberList = HOST + "/rehabilitationTeacher/getMemberList";

    public static String submitInviteInform = HOST + "/inviteInform/submitInviteInform";

    public static String submitMutualHelp = HOST + "/mutualHelp/submitMutualHelp";

    public static String getMutualHelpList = HOST + "/mutualHelp/getMutualHelpList";

    public static String submitHouseRent = HOST + "/houseRent/submitHouseRent";

    public static String getHouseRentList = HOST + "/houseRent/getHouseRentList";

}
