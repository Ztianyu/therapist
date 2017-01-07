package com.zty.therapist.url;

/**
 * Created by zty on 2017/1/5.
 */

public class Urls {

    public static String HOST = "http://14.29.68.166:8181/rehabilitationSystem" + "/admin/ws";

    public static String sysLogin = HOST + "/user/sysLogin";

    public static String getDoctorList = HOST + "/doctor/getDoctorList";

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

    public static String updatePwd   = HOST + "/user/updatePwd";

}
