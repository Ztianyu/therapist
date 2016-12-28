package com.zty.therapist.utils;

import android.view.View;
import android.widget.LinearLayout;

import com.zty.therapist.base.BaseActivity;

/**
 * Created by zty on 2016/12/26.
 */

public class ViewAdaptionUtils {

    private static int baseWidth = 720;

    private static int baseHeight = 1280;

    /**
     * 父布局是LinearLayout 的高度适配
     */
    public static void LinearLayoutAdaptation(View view, int height) {

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                (int) (BaseActivity.screenHeight * height / baseHeight));

        view.setLayoutParams(layoutParams);

    }
}
