package com.zty.therapist;

import android.widget.FrameLayout;
import android.widget.RadioGroup;

import com.zty.therapist.base.BaseActivity;
import com.zty.therapist.base.BaseFragment;
import com.zty.therapist.ui.fragment.main.CommunicationFragment;
import com.zty.therapist.ui.fragment.main.HomeFragment;
import com.zty.therapist.ui.fragment.main.PersonalFragment;
import com.zty.therapist.ui.fragment.main.TrainFragment;

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

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        radioGroup.setOnCheckedChangeListener(this);

        homeFragment = new HomeFragment();
        changeFragment(homeFragment);
    }

    private void changeFragment(BaseFragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentHome, fragment).commit();
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
                homeFragment = new HomeFragment();
                changeFragment(homeFragment);
                break;
            case R.id.radioButton2:
                trainFragment = new TrainFragment();
                changeFragment(trainFragment);
                break;
            case R.id.radioButton3:
                communicationFragment = new CommunicationFragment();
                changeFragment(communicationFragment);
                break;
            case R.id.radioButton4:
                personalFragment = new PersonalFragment();
                changeFragment(personalFragment);
                break;
        }

    }
}
