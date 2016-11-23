package com.zty.therapist.manager;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by zty on 2016/9/14.
 */
public class LayoutManager {

    public static void setVertical(Context context, RecyclerView recyclerView) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
    }
}
