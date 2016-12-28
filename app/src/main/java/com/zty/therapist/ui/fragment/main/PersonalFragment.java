package com.zty.therapist.ui.fragment.main;

import android.content.Intent;
import android.view.View;

import com.zty.therapist.R;
import com.zty.therapist.base.BaseFragment;
import com.zty.therapist.ui.activity.personal.AccountActivity;
import com.zty.therapist.ui.activity.personal.ApplyActivity;
import com.zty.therapist.ui.activity.personal.LoginActivity;
import com.zty.therapist.ui.activity.personal.MessageActivity;
import com.zty.therapist.ui.activity.personal.MyOrderActivity;
import com.zty.therapist.ui.activity.personal.UserMessageActivity;
import com.zty.therapist.widget.MyStripMenuView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by zty on 2016/11/25.
 */

public class PersonalFragment extends BaseFragment {
    @BindView(R.id.textStrip1)
    MyStripMenuView textStrip1;
    @BindView(R.id.textStrip2)
    MyStripMenuView textStrip2;
    @BindView(R.id.textStrip3)
    MyStripMenuView textStrip3;
    @BindView(R.id.textStrip4)
    MyStripMenuView textStrip4;
    @BindView(R.id.textStrip5)
    MyStripMenuView textStrip5;
    @BindView(R.id.textStrip6)
    MyStripMenuView textStrip6;

    @Override
    public int getContentVew() {
        return R.layout.fragment_personal;
    }

    @Override
    public void initData() {

    }

    @Override
    public void onFailureCallback(int requestCode, String errorMsg) {

    }

    @Override
    public void onSuccessCallback(int requestCode, String response) {

    }

    @OnClick({R.id.textStrip1, R.id.textStrip2, R.id.textStrip3, R.id.textStrip4, R.id.textStrip5, R.id.textStrip6})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.textStrip1:
                toAnother(1);
                break;
            case R.id.textStrip2:
                toAnother(2);
                break;
            case R.id.textStrip3:
                toAnother(3);
                break;
            case R.id.textStrip4:
                toAnother(4);
                break;
            case R.id.textStrip5:
                toAnother(5);
                break;
            case R.id.textStrip6:
                toAnother(6);
                break;
        }
    }

    private void toAnother(int type) {

        Class<?> object = null;
        if (type == 1) {
            object = LoginActivity.class;
        } else if (type == 2) {
            object = UserMessageActivity.class;
        } else if (type == 3) {
            object = MyOrderActivity.class;
        } else if (type == 4) {
            object = AccountActivity.class;
        } else if (type == 5) {
            object = MessageActivity.class;
        } else if (type == 6) {
            object = ApplyActivity.class;
        }
        if (object != null)
            startActivity(new Intent(context, object));
    }

}
