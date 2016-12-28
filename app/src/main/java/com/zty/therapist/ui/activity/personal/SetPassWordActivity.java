package com.zty.therapist.ui.activity.personal;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.zty.therapist.MainActivity;
import com.zty.therapist.R;
import com.zty.therapist.base.BaseActivity;
import com.zty.therapist.manager.AppManager;
import com.zty.therapist.utils.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by zty on 2016/12/27.
 */

public class SetPassWordActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.editPassWord)
    EditText editPassWord;
    @BindView(R.id.editSurePassWord)
    EditText editSurePassWord;
    @BindView(R.id.btnSetPassWord)
    Button btnSetPassWord;

    private String phone;

    @Override
    protected int getContentView() {
        return R.layout.activity_set_pw;
    }

    @Override
    protected void initData() {

        phone = getIntent().getStringExtra("phone");

        title.setText("设置密码");
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

    @OnClick({R.id.btnSetPassWord})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.titleLeft:
                finish();
                break;
            case R.id.btnSetPassWord:
                toMain();
                break;
        }
    }

    private void toMain() {
        if (checkData()) {
            AppManager.getInstance().finishAllActivity();
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }

    private boolean checkData() {

        if (TextUtils.isEmpty(editPassWord.getText().toString())) {
            ToastUtils.show(this, "请输入密码");
            return false;
        }
        if (TextUtils.isEmpty(editSurePassWord.getText().toString())) {
            ToastUtils.show(this, "请确认密码");
        }
        if (!editSurePassWord.getText().toString().equals(editPassWord.getText().toString())) {
            ToastUtils.show(this, "两次密码不一致");
        }
        return true;
    }
}
