package com.zty.therapist.utils;

import android.text.TextUtils;

/**
 * Created by zty on 2016/11/15.
 */

public class MyTextUtils {

    public static String isEmpty(String str) {
        return TextUtils.isEmpty(str) ? " " : str;
    }
}
