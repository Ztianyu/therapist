package com.zty.therapist.utils;

import android.content.Context;

import com.zty.therapist.base.TherapistApplication;
import com.zty.therapist.model.LoginModel;

/**
 * Created by zty on 2017/1/5.
 */

public class UserUtils {

    public static void saveUser(Context context, LoginModel model, String loginName, String passWord) {

        SharedPrefUtils.setString(context, SharedPrefUtils.USER_ID, model.getUserId());
        SharedPrefUtils.setString(context, SharedPrefUtils.TOKEN_ID, model.getTokenId());
        SharedPrefUtils.setString(context, SharedPrefUtils.LOGIN_NAME, loginName);
        SharedPrefUtils.setString(context, SharedPrefUtils.LOGIN_PASSWORD, passWord);

        TherapistApplication.getInstance().setUserId(model.getUserId());
        TherapistApplication.getInstance().setTokenId(model.getTokenId());
    }

    public static void clearUser(Context context) {

        SharedPrefUtils.setString(context, SharedPrefUtils.USER_ID, "");
        SharedPrefUtils.setString(context, SharedPrefUtils.TOKEN_ID, "");
        SharedPrefUtils.setString(context, SharedPrefUtils.USER_MESSAGE, "");
        SharedPrefUtils.setString(context, SharedPrefUtils.LOGIN_PASSWORD, "");

        TherapistApplication.getInstance().setUserId("");
        TherapistApplication.getInstance().setUserId("");
        TherapistApplication.getInstance().clearUser();

    }
}
