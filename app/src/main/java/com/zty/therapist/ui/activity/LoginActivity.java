package com.zty.therapist.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.zty.therapist.MainActivity;
import com.zty.therapist.R;
import com.zty.therapist.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by zty on 2016/11/23.
 */

public class LoginActivity extends BaseActivity {


    @BindView(R.id.imgLogin)
    ImageView imgLogin;
    @BindView(R.id.editLoginName)
    EditText editLoginName;
    @BindView(R.id.editLoginPassWord)
    EditText editLoginPassWord;
    @BindView(R.id.textForgetPw)
    TextView textForgetPw;
    @BindView(R.id.btnLogin)
    Button btnLogin;

    @Override
    protected int getContentView() {
        return R.layout.activity_login;
    }

    @Override
    protected void initData() {
        title.setText("登  录");
        left.setVisibility(View.INVISIBLE);
        right.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onFailureCallback(int requestCode, String errorMsg) {

    }


    @Override
    public void onSuccessCallback(int requestCode, String response) {

    }

    @OnClick({R.id.textForgetPw, R.id.btnLogin})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.textForgetPw:
                break;
            case R.id.btnLogin:
                startActivity(new Intent(this, MainActivity.class));
                break;
        }
    }
}
