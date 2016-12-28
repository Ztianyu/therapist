package com.zty.therapist.ui.activity.personal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.zty.therapist.MainActivity;
import com.zty.therapist.R;
import com.zty.therapist.base.BaseActivity;
import com.zty.therapist.manager.AppManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zty on 2016/11/23.
 */

public class LoginActivity extends BaseActivity implements View.OnClickListener {


    @BindView(R.id.editLoginName)
    EditText editLoginName;
    @BindView(R.id.editLoginPassWord)
    EditText editLoginPassWord;
    @BindView(R.id.textForgetPw)
    TextView textForgetPw;
    @BindView(R.id.btnLogin)
    Button btnLogin;
    @BindView(R.id.btnRegister)
    Button btnRegister;
    @BindView(R.id.textResetPw)
    TextView textResetPw;

    @Override
    protected int getContentView() {
        return R.layout.activity_login;
    }

    @Override
    protected void initData() {
        title.setText("登  录");
        left.setBackgroundResource(R.mipmap.ic_back);
        left.setOnClickListener(this);
        right.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onFailureCallback(int requestCode, String errorMsg) {

    }


    @Override
    public void onSuccessCallback(int requestCode, String response) {

    }

    @OnClick({R.id.btnLogin, R.id.btnRegister, R.id.textResetPw, R.id.textForgetPw})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.titleLeft:
                finish();
                break;
            case R.id.btnLogin:
                toMain();
                break;
            case R.id.btnRegister:
                startActivity(new Intent(this, ConfirmPhoneActivity.class).putExtra("type", 0));
                break;
            case R.id.textResetPw:
                break;
            case R.id.textForgetPw:
                startActivity(new Intent(this, ConfirmPhoneActivity.class).putExtra("type", 1));
                break;
        }
    }

    private void toMain() {
        AppManager.getInstance().finishAllActivity();
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
