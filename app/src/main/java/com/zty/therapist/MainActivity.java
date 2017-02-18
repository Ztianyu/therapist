package com.zty.therapist;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.zty.therapist.base.BaseActivity;
import com.zty.therapist.base.BaseFragment;
import com.zty.therapist.imlib.ChatNotification;
import com.zty.therapist.imlib.LibManger;
import com.zty.therapist.imlib.LoginCallback;
import com.zty.therapist.manager.AppManager;
import com.zty.therapist.ui.fragment.main.ScrollingFragment;
import com.zty.therapist.ui.fragment.main.HomeFragment2;
import com.zty.therapist.ui.fragment.main.PersonalFragment;
import com.zty.therapist.utils.SharedPrefUtils;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {

    @BindView(R.id.radioGroup)
    RadioGroup radioGroup;

    HomeFragment2 homeFragment;
    ScrollingFragment doctorFragment;
    ScrollingFragment productFragment;
    PersonalFragment personalFragment;

    BaseFragment currentFragment;


    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {

        getSupportActionBar().hide();

        setFullScreen();

        left.setVisibility(View.INVISIBLE);
        radioGroup.setOnCheckedChangeListener(this);

        LibManger.login(SharedPrefUtils.getString(this, SharedPrefUtils.USER_ID), "123456", new LoginCallback() {
            @Override
            public void onLoginSuccess() {
                ChatNotification.init(getApplicationContext());
            }
        });

        if (homeFragment == null) {
            homeFragment = new HomeFragment2();
        }
        if (!homeFragment.isAdded()) {
            getSupportFragmentManager().beginTransaction().add(R.id.fragmentHome, homeFragment).commit();
            currentFragment = homeFragment;
        }
    }

    @Override
    public void onFailureCallback(int requestCode, String errorMsg) {

    }

    @Override
    public void onSuccessCallback(int requestCode, String response) {

    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (i) {
            case R.id.radioButton1:
                if (homeFragment == null) {
                    homeFragment = new HomeFragment2();
                }
                addOrShowFragment(getSupportFragmentManager(), homeFragment);
                break;
            case R.id.radioButton2:
                if (doctorFragment == null) {
                    doctorFragment = ScrollingFragment.newInstance(0);
                }
                addOrShowFragment(getSupportFragmentManager(), doctorFragment);
                break;
            case R.id.radioButton3:
                if (productFragment == null) {
                    productFragment = ScrollingFragment.newInstance(1);
                }
                addOrShowFragment(getSupportFragmentManager(), productFragment);
                break;
            case R.id.radioButton4:
                if (personalFragment == null) {
                    personalFragment = new PersonalFragment();
                }
                addOrShowFragment(getSupportFragmentManager(), personalFragment);
                break;
        }
    }

    /**
     * 添加或者显示 fragment
     */
    private void addOrShowFragment(FragmentManager fragmentManager, BaseFragment fragment) {
        if (currentFragment == fragment)
            return;

        if (!fragment.isAdded()) { // 如果当前fragment未被添加，则添加到Fragment管理器中
            fragmentManager.beginTransaction().hide(currentFragment).add(R.id.fragmentHome, fragment).commit();
        } else {
            fragmentManager.beginTransaction().hide(currentFragment).show(fragment).commit();
        }
        currentFragment = fragment;
    }


    private boolean bExit = false;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (bExit) {
                AppManager.getInstance().AppExit(this, false);
                finish();
                return true;
            }
            bExit = true;
            mHandler.sendEmptyMessageDelayed(0x100, 2000);
            Toast.makeText(this, "再按一次退出程序",
                    Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private Handler mHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            bExit = false;
        }
    };
}
