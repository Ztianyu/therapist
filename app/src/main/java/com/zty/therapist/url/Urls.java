package com.zty.therapist.url;

/**
 * Created by zty on 2017/1/5.
 */

public class Urls {

    public static String HOST_PROJECT = "http://14.29.68.166:8181/rehabilitationSystem";
    //    public static String HOST_PROJECT = "http://10.0.0.110:8080/elink";
    public static String HOST = HOST_PROJECT + "/admin/ws";

    //注册
    public static String register = HOST + "/user/register";
    //登录
    public static String sysLogin = HOST + "/user/sysLogin";
    //修改密码
    public static String updatePwd = HOST + "/user/updatePwd";
    //设置密码
    public static String setNewPwd = HOST + "/user/setNewPwd";
    //更新头像
    public static String updatePhoto = HOST + "/rehabilitationTeacher/updatePhoto";
    //更新个人资料
    public static String update = HOST + "/rehabilitationTeacher/update";
    //修改帖子头像、昵称
    public static String setNickName = HOST + "/rehabilitationTeacher/setNickName";

    //医生列表
    public static String getDoctorList = HOST + "/doctor/getDoctorList";
    //保健品列表
    public static String getHealthProductsList = HOST + "/healthProducts/getHealthProductsList";
    //健康知识分类
    public static String getDissessOption = HOST + "/dict/getDissessOption";
    //康复知识列表
    public static String getHealthLoreList = HOST + "/healthLore/getHealthLoreList";
    //健康试题列表
    public static String getTestQuestionsList = HOST + "/testQuestions/getTestQuestionsList";
    //健康视频分类
    public static String getVideoTypeOption = HOST + "/dict/getVideoTypeOption";
    //健康视频列表
    public static String getHealthLectureList = HOST + "/healthLecture/getHealthLectureList";
    //保健品分类
    public static String getProductTypeOption = HOST + "/dict/getProductTypeOption";
    //城市分类
    public static String getDoctorCityOption = HOST + "/dict/getDoctorCityOption";
    //医生详情
    public static String getDoctorDetail = HOST + "/doctor/getDoctorDetail";
    //康复师详情
    public static String getRehabilitationTeacher = HOST + "/rehabilitationTeacher/getRehabilitationTeacher";

    //发布帖子
    public static String submitForum = HOST + "/healthForum/submitForum";

    //上传照片
    public static String uploadAndroidServlet = HOST_PROJECT + "/uploadAndroidServlet";
    //感情天地帖子列表
    public static String getHealthForumList = HOST + "/healthForum/getHealthForumList";
    //帖子详情
    public static String getHealthForum = HOST + "/healthForum/getHealthForum";
    //评论帖子
    public static String submitForumReplay = HOST + "/forumReplay/submitForumReplay";

    //同城活动列表
    public static String getCityActivityList = HOST + "/cityActivity/getCityActivityList";
    //发布同城活动
    public static String submitCityActivity = HOST + "/cityActivity/submitCityActivity";
    //活动风采列表
    public static String getCityPhotoList = HOST + "/cityPhoto/getCityPhotoList";
    //活动详情
    public static String getCityActivity = HOST + "/cityActivity/getCityActivity";
    //活动报名列表
    public static String getCityEnrollList = HOST + "/cityEnroll/getCityEnrollList";
    //活动报名
    public static String submitCityEnroll = HOST + "/cityEnroll/submitCityEnroll";
    //活动取消报名
    public static String deleteCityEnroll = HOST + "/cityEnroll/deleteCityEnroll";
    //提交我的风采
    public static String submitCityPhoto = HOST + "/cityPhoto/submitCityPhoto";
    //获取组成员列表
    public static String getGroupMemberList = HOST + "/rehabilitationGroup/getGroupMemberList";
    //发布替班申请
    public static String submitShiftRecord = HOST + "/shiftRecord/submitShiftRecord";
    //成员替班申请列表
    public static String getShiftRecordList = HOST + "/shiftRecord/getShiftRecordList";
    //组长替班处理列表
    public static String getAuditShiftRecordList = HOST + "/shiftRecord/getAuditShiftRecordList";
    //同意替班
    public static String confirmOrder = HOST + "/shiftRecord/confirmOrder";
    //不同意替班
    public static String cancelRelay = HOST + "/shiftRecord/cancelRelay";
    //替班详情
    public static String getShiftRecord = HOST + "/shiftRecord/getShiftRecord";
    //分配替班人员
    public static String distributeRelay = HOST + "/shiftRecord/distributeRelay";
    //评价选项
    public static String getAbilityOption = HOST + "/dict/getAbilityOption";
    //评价
    public static String setAbility = HOST + "/rehabilitationTeacher/setAbility";
    //查询成员
    public static String getMemberList = HOST + "/rehabilitationTeacher/getMemberList";
    //组长添加成员到该组
    public static String submitInviteInform = HOST + "/inviteInform/submitInviteInform";
    //发布互帮互助
    public static String submitMutualHelp = HOST + "/mutualHelp/submitMutualHelp";
    //获取互帮互助列表
    public static String getMutualHelpList = HOST + "/mutualHelp/getMutualHelpList";
    //发布房租出租信息
    public static String submitHouseRent = HOST + "/houseRent/submitHouseRent";
    //获取房屋出租列表
    public static String getHouseRentList = HOST + "/houseRent/getHouseRentList";
    //获取班长所管理的组长列表
    public static String getTeamGroupList = HOST + "/rehabilitationTeam/getTeamGroupList";
    //获取组长列表（未分配班长）
    public static String getGroupList = HOST + "/rehabilitationTeacher/getGroupList";


    //系统公告
    public static String getSystemBulletinList = HOST + "/systemBulletin/getSystemBulletinList";
    //发布通知
    public static String submitRehabilitationBulletin = HOST + "/rehabilitationBulletin/submitRehabilitationBulletin";
    //通知列表
    public static String getRehabilitationBulletinList = HOST + "/rehabilitationBulletin/getRehabilitationBulletinList";
    //班组通知内容
    public static String getInviteContent = HOST + "/inviteContent/getInviteContent";
    //班组通知信息
    public static String getInviteInformList = HOST + "/inviteInform/getInviteInformList";
    //同意、不同意邀请
    public static String updateState = HOST + "/inviteInform/updateState";
    //获取通知详情
    public static String getInviteInform = HOST + "/inviteInform/getInviteInform";
    //获取申请组长是否通过
    public static String getInviteInformGroup = HOST + "/inviteInform/getInviteInformGroup";
    //获取班长列表
    public static String getRehabilitationTeamList = HOST + "/rehabilitationTeam/getRehabilitationTeamList";
    //管理员分配处理人
    public static String distributeTransactor = HOST + "/doctorOrder/distributeTransactor";
    //管理员获取全部人员列表
    public static String getAdminMemberList = HOST + "/rehabilitationTeacher/getAdminMemberList";
    //设置组长
    public static String submitGroupLeader = HOST + "/rehabilitationGroup/submitGroupLeader";
    //设置班长
    public static String submitMonitor = HOST + "/rehabilitationTeam/submitMonitor";
    //换组长
    public static String replaceGroupLeader = HOST + "/rehabilitationGroup/replaceGroupLeader";
    //换班长
    public static String replaceMonitor = HOST + "/rehabilitationTeam/replaceMonitor";


    //预定医生
    public static String submitDoctorOrder = HOST + "/doctorOrder/submitDoctorOrder";
    //医生订单列表
    public static String getDoctorOrderList = HOST + "/doctorOrder/getDoctorOrderList";
    //医生订单详情
    public static String getDoctorOrder = HOST + "/doctorOrder/getDoctorOrder";
    //处理益生订单
    public static String processOrder = HOST + "/doctorOrder/processOrder";

    //健康产品订单列表
    public static String getProductsOrderList = HOST + "/productsOrder/getProductsOrderList";
    //提交健康产品订单
    public static String submitProductsOrder = HOST + "/productsOrder/submitProductsOrder";
    //收货地址
    public static String getAddressList = HOST + "/userAddress/getAddressList";
    //添加收货地址
    public static String submitAddress = HOST + "/userAddress/submitAddress";
    //删除收货地址
    public static String deleteAddress = HOST + "/userAddress/deleteAddress";
    //获取默认收货地址
    public static String getIsDefault = HOST + "/userAddress/getIsDefault";
    //设置默认收货地址
    public static String isDefault = HOST + "/userAddress/isDefault";

    //省
    public static String getProvinceArea = HOST + "/area/getProvinceArea";
    //市
    public static String getCityArea = HOST + "/area/getCityArea";
    //县
    public static String getDistrictArea = HOST + "/area/getDistrictArea";

    //保健品积分订单
    public static String getAccountInquiryOrderList = HOST + "/accountInquiry/getProductsOrderList";

    //医生积分订单
    public static String getAccountInquiryDoctorOrderList = HOST + "/accountInquiry/getDoctorOrderList";

    //医生积分订单
    public static String getInviteInformAdminList = HOST + "/inviteInform/getInviteInformAdminList";


}
