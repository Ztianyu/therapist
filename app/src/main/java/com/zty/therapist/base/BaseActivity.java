package com.zty.therapist.base;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zty.therapist.R;
import com.zty.therapist.manager.AppManager;
import com.zty.therapist.url.RequestCallback;

import butterknife.ButterKnife;

/**
 * Created by zty on 2016/8/16.
 */
public abstract class BaseActivity extends AppCompatActivity implements RequestCallback {

    private View actionBarView;
    public TextView title;
    public TextView left;
    public TextView right;

    public static boolean isGetSize = false;
    public static int screenHeight = 0;
    public static int screenWidth = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getContentView());
        ButterKnife.bind(this);

        AppManager.getInstance().addActivity(this);

        getScreenSize();

        initTitleBar();
        initData();
    }

    private void initTitleBar() {

        if (actionBarView == null) {
            actionBarView = LayoutInflater.from(this).inflate(R.layout.view_title, null);
            title = (TextView) actionBarView.findViewById(R.id.title);
            left = (TextView) actionBarView.findViewById(R.id.titleLeft);
            right = (TextView) actionBarView.findViewById(R.id.titleRight);
        }
        if (getSupportActionBar() != null) {
            //去除阴影
            if (Build.VERSION.SDK_INT >= 21) {
                getSupportActionBar().setElevation(0);
            }
            getSupportActionBar().setDisplayShowCustomEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setDisplayShowHomeEnabled(false);
            ActionBar.LayoutParams layout = new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
            getSupportActionBar().setCustomView(actionBarView, layout);
        }

    }

    protected abstract int getContentView();

    protected abstract void initData();

    private void getScreenSize() {

        if (!isGetSize) {
            DisplayMetrics dm = new DisplayMetrics();
            // 获取屏幕信息
            getWindowManager().getDefaultDisplay().getMetrics(dm);

            screenHeight = dm.heightPixels;
            screenWidth = dm.widthPixels;

            isGetSize = true;
        }
    }
}
