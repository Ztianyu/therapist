package com.zty.therapist.ui.activity.personal;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.zty.therapist.R;
import com.zty.therapist.base.BaseActivity;
import com.zty.therapist.imlib.LibManger;
import com.zty.therapist.utils.UserUtils;
import com.zty.therapist.widget.StripViewNoImg;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by zty on 2017/1/7.
 */

public class SettingActivity extends BaseActivity {
    @BindView(R.id.textUserMessageSet)
    StripViewNoImg textUserMessageSet;
    @BindView(R.id.textUserSetPw)
    StripViewNoImg textUserSetPw;
    @BindView(R.id.btnExitLogin)
    Button btnExitLogin;
    @BindView(R.id.textUserNickSet)
    StripViewNoImg textUserNickSet;

    @Override
    protected int getContentView() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initData() {
        title.setText("设 置");
    }

    @Override
    public void onFailureCallback(int requestCode, String errorMsg) {

    }

    @Override
    public void onSuccessCallback(int requestCode, String response) {

    }

    @OnClick({R.id.textUserMessageSet, R.id.textUserSetPw, R.id.textUserNickSet, R.id.btnExitLogin})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.titleLeft:
                finish();
                break;
            case R.id.textUserMessageSet:
                startActivity(new Intent(this, UserMessageActivity.class));
                break;
            case R.id.textUserSetPw:
                startActivity(new Intent(this, ResetPwActivity.class));
                break;
            case R.id.textUserNickSet:
                startActivity(new Intent(this, NickActivity.class));
                break;
            case R.id.btnExitLogin:
                UserUtils.clearUser(this);
                LibManger.exit();
                finish();
                break;
        }
    }

}
