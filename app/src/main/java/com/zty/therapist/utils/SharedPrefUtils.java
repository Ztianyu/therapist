package com.zty.therapist.utils;

import android.content.Context;

public class SharedPrefUtils {

    /**
     * SharedPreferences xml 名称
     */
    private static final String APP_SHARED_STR = "OldPeopleZone_Pref";

    public static final String USER_ID = "userId";
    public static final String TOKEN_ID = "tokenId";

    public static final String LOGIN_NAME = "loginName";
    public static final String LOGIN_PASSWORD = "passWord";
    public static final String ROLE = "role";

    public static final String USER_MESSAGE = "userMessage";

    public static boolean getBoolean(Context context, String key) {
        return context.getSharedPreferences(APP_SHARED_STR,
                Context.MODE_PRIVATE).getBoolean(key, false);
    }

    public static synchronized void setBoolean(Context context, String key,
                                               boolean value) {
        context.getSharedPreferences(APP_SHARED_STR, Context.MODE_PRIVATE)
                .edit().putBoolean(key, value).commit();
    }

    public static String getString(Context context, String key) {
        return context.getSharedPreferences(APP_SHARED_STR,
                Context.MODE_PRIVATE).getString(key, null);
    }

    public static synchronized void setString(Context context, String key,
                                              String value) {
        context.getSharedPreferences(APP_SHARED_STR, Context.MODE_PRIVATE)
                .edit().putString(key, value).commit();
    }

    public static synchronized void updateString(Context context, String key,
                                                 String value) {
        context.getSharedPreferences(APP_SHARED_STR, Context.MODE_PRIVATE)
                .edit().putString(key, value).apply();
    }

    public static int getInt(Context context, String key) {
        return context.getSharedPreferences(APP_SHARED_STR,
                Context.MODE_PRIVATE).getInt(key, ~0);
    }

    public static synchronized void setInt(Context context, String key,
                                           int value) {
        context.getSharedPreferences(APP_SHARED_STR, Context.MODE_PRIVATE)
                .edit().putInt(key, value).commit();
    }

}
