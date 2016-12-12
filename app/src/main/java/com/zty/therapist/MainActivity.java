package com.zty.therapist;

import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import com.zty.therapist.base.BaseActivity;
import com.zty.therapist.base.BaseFragment;
import com.zty.therapist.ui.fragment.main.CommunicationFragment;
import com.zty.therapist.ui.fragment.main.HomeFragment;
import com.zty.therapist.ui.fragment.main.PersonalFragment;
import com.zty.therapist.ui.fragment.main.TrainFragment;
import com.zty.therapist.utils.ResourceUtil;
import com.zty.therapist.utils.ToastUtils;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {

    @BindView(R.id.fragmentHome)
    FrameLayout fragmentHome;
    @BindView(R.id.radioGroup)
    RadioGroup radioGroup;

    HomeFragment homeFragment;
    TrainFragment trainFragment;
    CommunicationFragment communicationFragment;
    PersonalFragment personalFragment;

    BaseFragment currentFragment;

    private int currentPage = 1;

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        radioGroup.setOnCheckedChangeListener(this);

        if (homeFragment == null) {
            homeFragment = new HomeFragment();
        }
        if (!homeFragment.isAdded()) {
            getSupportFragmentManager().beginTransaction().add(R.id.fragmentHome, homeFragment).commit();
            currentFragment = homeFragment;
            currentFragment.setUserVisibleHint(true);
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
                    homeFragment = new HomeFragment();
                }
                addOrShowFragment(getSupportFragmentManager().beginTransaction(), homeFragment);
                break;
            case R.id.radioButton2:
                currentPage = 1;
                if (trainFragment == null) {
                    trainFragment = new TrainFragment();
                }
                addOrShowFragment(getSupportFragmentManager().beginTransaction(), trainFragment);
                break;
            case R.id.radioButton3:
                currentPage = 3;
                if (communicationFragment == null) {
                    communicationFragment = new CommunicationFragment();
                }
                addOrShowFragment(getSupportFragmentManager().beginTransaction(), communicationFragment);
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
        currentFragment.setUserVisibleHint(false);
        currentFragment = fragment;
        currentFragment.setUserVisibleHint(true);
    }

    private void setTitle() {

        switch (currentPage) {
            case 1:
                left.setVisibility(View.INVISIBLE);
                right.setVisibility(View.INVISIBLE);
                title.setText(ResourceUtil.resToStr(this, R.string.app_name));
                break;
            case 2:
                left.setVisibility(View.INVISIBLE);
                right.setVisibility(View.INVISIBLE);
                title.setText(ResourceUtil.resToStr(this, R.string.train));
                break;
            case 3:
                left.setVisibility(View.INVISIBLE);
                right.setVisibility(View.VISIBLE);
                right.setBackgroundResource(R.mipmap.ic_publish);
                title.setText(ResourceUtil.resToStr(this, R.string.train));
                right.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ToastUtils.show(MainActivity.this, "发帖");
                    }
                });
                break;
            case 4:
                left.setVisibility(View.INVISIBLE);
                right.setVisibility(View.INVISIBLE);
                title.setText(ResourceUtil.resToStr(this, R.string.personal));
                break;
        }

    }
}
