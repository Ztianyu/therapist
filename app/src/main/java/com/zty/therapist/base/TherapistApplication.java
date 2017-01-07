package com.zty.therapist.base;

import android.app.Application;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.lzy.okhttputils.OkHttpUtils;
import com.lzy.okhttputils.cookie.store.PersistentCookieStore;
import com.lzy.okhttputils.model.HttpParams;
import com.zty.therapist.model.UserModel;
import com.zty.therapist.utils.SharedPrefUtils;

/**
 * Created by zty on 2017/1/6.
 */

public class TherapistApplication extends Application {

    private static TherapistApplication instance;

    private UserModel userModel;

    private String userId;
    private String tokenId;

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;
        setCurrentUser();
        initHttpUtils();
    }

    public static synchronized TherapistApplication getInstance() {
        return instance;
    }

    private void setCurrentUser() {

        userId = SharedPrefUtils.getString(this, SharedPrefUtils.USER_ID);
        tokenId = SharedPrefUtils.getString(this, SharedPrefUtils.TOKEN_ID);

        String userMessage = SharedPrefUtils.getString(this, SharedPrefUtils.USER_MESSAGE);
        if (!TextUtils.isEmpty(userMessage))
            userModel = new Gson().fromJson(userMessage, UserModel.class);
    }

    public void setCurrentUser(String userMessage) {
        SharedPrefUtils.setString(this, SharedPrefUtils.USER_MESSAGE, userMessage);
        userModel = new Gson().fromJson(userMessage, UserModel.class);
    }

    public void clearUser() {
        this.userModel = null;
    }

    public UserModel getUserModel() {
        return userModel;
    }

    private void initHttpUtils() {
        HttpParams params = new HttpParams();
        //        params.put("commonParamsKey1", "commonParamsValue1");     //所有的 params 都 支持 中文
        //        params.put("commonParamsKey2", "这里支持中文参数");

        //必须调用初始化
        OkHttpUtils.init(this);
        //以下都不是必须的，根据需要自行选择
        OkHttpUtils.getInstance()//
                .debug("OkHttpUtils")                                              //是否打开调试
                .setConnectTimeout(OkHttpUtils.DEFAULT_MILLISECONDS)               //全局的连接超时时间
                .setReadTimeOut(OkHttpUtils.DEFAULT_MILLISECONDS)                  //全局的读取超时时间
                .setWriteTimeOut(OkHttpUtils.DEFAULT_MILLISECONDS)                 //全局的写入超时时间
                //                .setCookieStore(new MemoryCookieStore())                           //cookie使用内存缓存（app退出后，cookie消失）
                .setCookieStore(new PersistentCookieStore())                       //cookie持久化存储，如果cookie不过期，则一直有效
                //                .addCommonHeaders(headers)                                         //设置全局公共头
                .addCommonParams(params);                                          //设置全局公共参数
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }
}
