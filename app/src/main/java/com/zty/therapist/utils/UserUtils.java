package com.zty.therapist.utils;

import android.content.Context;
import android.text.TextUtils;

import com.loopj.android.http.RequestParams;
import com.zty.therapist.base.TherapistApplication;
import com.zty.therapist.model.LoginModel;
import com.zty.therapist.url.RequestCallback;
import com.zty.therapist.url.RequestManager;
import com.zty.therapist.url.Urls;

/**
 * Created by zty on 2017/1/5.
 */

public class UserUtils {

    public static final int CODE_GET_MESSAGE = 101;

    public static void saveUser(Context context, LoginModel model, String loginName, String passWord) {

        SharedPrefUtils.setString(context, SharedPrefUtils.USER_ID, model.getUserId());
        SharedPrefUtils.setString(context, SharedPrefUtils.TOKEN_ID, model.getTokenId());
        SharedPrefUtils.setInt(context, SharedPrefUtils.ROLE, model.getRole());
        SharedPrefUtils.setString(context, SharedPrefUtils.LOGIN_NAME, loginName);
        SharedPrefUtils.setString(context, SharedPrefUtils.LOGIN_PASSWORD, passWord);

        TherapistApplication.getInstance().setUserId(model.getUserId());
        TherapistApplication.getInstance().setTokenId(model.getTokenId());
        TherapistApplication.getInstance().setRole(model.getRole());
    }

    public static void clearUser(Context context) {

        SharedPrefUtils.setString(context, SharedPrefUtils.USER_ID, "");
        SharedPrefUtils.setString(context, SharedPrefUtils.TOKEN_ID, "");
        SharedPrefUtils.setInt(context, SharedPrefUtils.ROLE, 0);
        SharedPrefUtils.setString(context, SharedPrefUtils.USER_MESSAGE, "");
        SharedPrefUtils.setString(context, SharedPrefUtils.LOGIN_PASSWORD, "");

        TherapistApplication.getInstance().setUserId("");
        TherapistApplication.getInstance().setUserId("");
        TherapistApplication.getInstance().setRole(0);
        TherapistApplication.getInstance().clearUser();

    }

    public static void getUserMessage(String userId, RequestCallback callback) {
        RequestParams params = new RequestParams();
        if (!TextUtils.isEmpty(userId))
            params.put("userId", userId);
        RequestManager.get(CODE_GET_MESSAGE, Urls.getRehabilitationTeacher, params, callback);
    }
}
