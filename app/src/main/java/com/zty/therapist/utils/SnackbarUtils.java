package com.zty.therapist.utils;

import android.support.design.widget.Snackbar;
import android.view.View;

/**
 * Created by zty on 2017/1/5.
 */

public class SnackbarUtils {

    private static Snackbar snackbar = null;

    public static void show(View view, String message) {
        snackbar = Snackbar.make(view, message, Snackbar.LENGTH_SHORT).setDuration(1500);
        snackbar.setDuration(1500);
    }

}
