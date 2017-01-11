package com.zty.therapist.ui.activity.home;

import com.zty.therapist.R;
import com.zty.therapist.base.BaseActivity;

/**
 * Created by zty on 2017/1/11.
 */

public class MemberDetailActivity extends BaseActivity {

    private String userId;

    @Override
    protected int getContentView() {
        return R.layout.activity_member_detail;
    }

    @Override
    protected void initData() {
        userId = getIntent().getStringExtra("userId");
    }

    @Override
    public void onFailureCallback(int requestCode, String errorMsg) {

    }

    @Override
    public void onSuccessCallback(int requestCode, String response) {

    }
}
