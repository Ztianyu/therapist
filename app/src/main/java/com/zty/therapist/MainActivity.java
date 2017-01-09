package com.zty.therapist;

import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.RadioGroup;

import com.zty.therapist.base.BaseActivity;
import com.zty.therapist.base.BaseFragment;
import com.zty.therapist.ui.fragment.main.ScrollingFragment;
import com.zty.therapist.ui.fragment.main.HomeFragment2;
import com.zty.therapist.ui.fragment.main.PersonalFragment;
import com.zty.therapist.utils.ResourceUtil;
import com.zty.therapist.utils.TimeUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {

    @BindView(R.id.radioGroup)
    RadioGroup radioGroup;

    HomeFragment2 homeFragment;
    ScrollingFragment doctorFragment;
    ScrollingFragment productFragment;
    PersonalFragment personalFragment;

    BaseFragment currentFragment;

    private int currentPage = 1;

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        left.setVisibility(View.INVISIBLE);
        radioGroup.setOnCheckedChangeListener(this);

        if (homeFragment == null) {
            homeFragment = new HomeFragment2();
        }
        if (!homeFragment.isAdded()) {
            getSupportFragmentManager().beginTransaction().add(R.id.fragmentHome, homeFragment).commit();
            currentFragment = homeFragment;
            currentPage = 1;
            setTitle();
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
                currentPage = 1;
                if (homeFragment == null) {
                    homeFragment = new HomeFragment2();
                }
                addOrShowFragment(getSupportFragmentManager().beginTransaction(), homeFragment);
                break;
            case R.id.radioButton2:
                currentPage = 2;
                if (doctorFragment == null) {
                    doctorFragment = ScrollingFragment.newInstance(0);
                }
                addOrShowFragment(getSupportFragmentManager().beginTransaction(), doctorFragment);
                break;
            case R.id.radioButton3:
                currentPage = 3;
                if (productFragment == null) {
                    productFragment = ScrollingFragment.newInstance(1);
                }
                addOrShowFragment(getSupportFragmentManager().beginTransaction(), productFragment);
                break;
            case R.id.radioButton4:
                currentPage = 4;
                if (personalFragment == null) {
                    personalFragment = new PersonalFragment();
                }
                addOrShowFragment(getSupportFragmentManager().beginTransaction(), personalFragment);
                break;
        }
    }

    /**
     * 添加或者显示 fragment
     */
    private void addOrShowFragment(FragmentTransaction transaction, BaseFragment fragment) {
        if (currentFragment == fragment)
            return;

        setTitle();

        if (!fragment.isAdded()) { // 如果当前fragment未被添加，则添加到Fragment管理器中
            transaction.hide(currentFragment).add(R.id.fragmentHome, fragment).commit();
        } else {
            transaction.hide(currentFragment).show(fragment).commit();
        }
        currentFragment = fragment;
    }

    private void setTitle() {
        switch (currentPage) {
            case 1:
                title.setText(ResourceUtil.resToStr(this, R.string.Housekeeping));
                break;
            case 2:
                title.setText(ResourceUtil.resToStr(this, R.string.expert));
                break;
            case 3:
                title.setText(ResourceUtil.resToStr(this, R.string.product));
                break;
            case 4:
                title.setText(ResourceUtil.resToStr(this, R.string.personal));
                break;
        }

    }
}
