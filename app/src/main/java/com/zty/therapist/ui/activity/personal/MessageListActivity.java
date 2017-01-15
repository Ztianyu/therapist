package com.zty.therapist.ui.activity.personal;

import com.zty.therapist.base.BaseRefreshActivity;

/**
 * Created by tianyu on 2017/1/15.
 */

public class MessageListActivity extends BaseRefreshActivity {

    private int type;

    @Override
    protected void initReadyData() {
        type = getIntent().getIntExtra("type", 0);
    }

    @Override
    protected void setAdapter() {

    }

    @Override
    protected boolean isHaveDivider() {
        return false;
    }

    @Override
    protected void fetchData() {

    }

    @Override
    public void onFailureCallback(int requestCode, String errorMsg) {

    }

    @Override
    public void onSuccessCallback(int requestCode, String response) {

    }
}
