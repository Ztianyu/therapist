package com.zty.therapist.base;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.zty.therapist.R;
import com.zty.therapist.manager.AppManager;
import com.zty.therapist.url.RequestCallback;

import butterknife.ButterKnife;

/**
 * Created by zty on 2016/8/16.
 */
public abstract class BaseActivity extends AppCompatActivity implements RequestCallback, View.OnClickListener {

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

        setPermissions();
    }

    private void initTitleBar() {

        if (actionBarView == null) {
            actionBarView = LayoutInflater.from(this).inflate(R.layout.view_title, null);
            title = (TextView) actionBarView.findViewById(R.id.title);
            left = (TextView) actionBarView.findViewById(R.id.titleLeft);
            left.setOnClickListener(this);
            right = (TextView) actionBarView.findViewById(R.id.titleRight);
            right.setOnClickListener(this);
        }
        if (getSupportActionBar() != null) {
            //去除阴影
            getSupportActionBar().setElevation(0);

            getSupportActionBar().setDisplayShowCustomEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setDisplayShowHomeEnabled(false);
            ActionBar.LayoutParams layout = new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
            getSupportActionBar().setCustomView(actionBarView, layout);
        }
    }

    public void setFullScreen() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.0 全透明状态栏
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//4.4 全透明状态栏
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
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

    private void setPermissions() {
        PackageManager pm = getPackageManager();
        boolean hasCamera = (PackageManager.PERMISSION_GRANTED == pm.checkPermission("android.permission.CAMERA", "com.zty.therapist"));
        boolean hasStorage = (PackageManager.PERMISSION_GRANTED == pm.checkPermission("android.permission.WRITE_EXTERNAL_STORAGE", "com.zty.therapist"));
        boolean hasAudio = (PackageManager.PERMISSION_GRANTED == pm.checkPermission("android.permission.RECORD_AUDIO", "com.zty.therapist"));
        boolean hasCoarseLocation = (PackageManager.PERMISSION_GRANTED == pm.checkPermission("android.permission.ACCESS_COARSE_LOCATION", "com.zty.therapist"));
        boolean hasFindLocation = (PackageManager.PERMISSION_GRANTED == pm.checkPermission("android.permission.ACCESS_FINE_LOCATION", "com.zty.therapist"));

        if (!hasCamera)
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 1);

        if (!hasStorage)
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);

        if (!hasAudio)
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, 1);

        if (!hasCoarseLocation)
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1);

        if (!hasFindLocation)
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.titleLeft) {
            finish();
        } else if (view.getId() == R.id.titleRight) {
            rightClick();
        }
    }

    public void rightClick() {
    }

    public void setRight(int id) {
        Drawable drawable = ResourcesCompat.getDrawable(getResources(), id, null);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        right.setCompoundDrawables(null, null, drawable, null);
    }
}
