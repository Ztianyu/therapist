package com.zty.therapist.ui.activity.home;

import com.zty.therapist.R;
import com.zty.therapist.base.BaseActivity;

/**
 * Created by tianyu on 2017/1/2.
 */

public class SendHouseRentActivity extends BaseActivity {
    @Override
    protected int getContentView() {
        return R.layout.activity_send_house_rent;
    }

    @Override
    protected void initData() {
        title.setText("房屋租赁");
        right.setText("发布");

    }

    @Override
    public void onFailureCallback(int requestCode, String errorMsg) {

    }

    @Override
    public void onSuccessCallback(int requestCode, String response) {

    }

    @Override
    public void rightClick() {
        super.rightClick();
    }
}
