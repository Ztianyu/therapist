package com.zty.therapist.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by zty on 2016/8/31.
 */
public class ToastUtils {

    public static void show(Context context, String text) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }

    public static void showLong(Context context, String text){
        Toast.makeText(context, text, Toast.LENGTH_LONG).show();
    }
}
