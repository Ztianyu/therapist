package com.zty.therapist.ui.activity.home;

import android.view.View;

import com.zty.therapist.R;
import com.zty.therapist.base.BaseActivity;
import com.zty.therapist.base.BaseFragment;
import com.zty.therapist.base.TherapistApplication;
import com.zty.therapist.ui.fragment.administrator.AdministratorGroupFragment;
import com.zty.therapist.ui.fragment.group.MemberGroupFragment;
import com.zty.therapist.ui.fragment.monitor.MonitorGroupFragment;

/**
 * Created by zty on 2016/12/17.
 */

public class GroupActivity extends BaseActivity implements View.OnClickListener {

    private int type = 0;//0:普通成员；1：组长；2：班长；3：管理员

    private BaseFragment fragment;

    @Override
    protected int getContentView() {
        return R.layout.activity_group;
    }

    @Override
    protected void initData() {
        title.setText("班组管理");

        type = TherapistApplication.getInstance().getRole();

        setFragment();
    }

    private void setFragment() {
        if (type == 0 || type == 1) {
            fragment = MemberGroupFragment.newInstance(type);
        } else if (type == 2) {
            fragment = new MonitorGroupFragment();
        } else if (type == 3) {
            fragment = new AdministratorGroupFragment();
        } else {
            fragment = MemberGroupFragment.newInstance(0);
        }

        getSupportFragmentManager().beginTransaction().add(R.id.fragmentGroup, fragment).commit();

    }

    @Override
    public void onFailureCallback(int requestCode, String errorMsg) {

    }

    @Override
    public void onSuccessCallback(int requestCode, String response) {

    }
}
