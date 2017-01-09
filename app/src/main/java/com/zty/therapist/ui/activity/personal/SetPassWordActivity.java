package com.zty.therapist.ui.activity.personal;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.loopj.android.http.RequestParams;
import com.zty.therapist.MainActivity;
import com.zty.therapist.R;
import com.zty.therapist.base.BaseActivity;
import com.zty.therapist.manager.AppManager;
import com.zty.therapist.model.ResultBean;
import com.zty.therapist.url.RequestManager;
import com.zty.therapist.url.Urls;
import com.zty.therapist.utils.ResultUtil;
import com.zty.therapist.utils.SharedPrefUtils;
import com.zty.therapist.utils.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by zty on 2016/12/27.
 */

public class SetPassWordActivity extends BaseActivity implements View.OnClickListener {

    private static final int CODE_REGIST = 0;
    private static final int CODE_SETPW = 1;

    @BindView(R.id.editPassWord)
    EditText editPassWord;
    @BindView(R.id.editSurePassWord)
    EditText editSurePassWord;
    @BindView(R.id.btnSetPassWord)
    Button btnSetPassWord;

    private String phone;
    private int type;

    @Override
    protected int getContentView() {
        return R.layout.activity_set_pw;
    }

    @Override
    protected void initData() {

        phone = getIntent().getStringExtra("phone");
        type = getIntent().getIntExtra("type", 0);

        title.setText("设置密码");

    }

    @Override
    public void onFailureCallback(int requestCode, String errorMsg) {

    }

    @Override
    public void onSuccessCallback(int requestCode, String response) {
        ResultBean resultBean = ResultUtil.getResult(response);
        if (resultBean.isSuccess()) {
            if (requestCode == CODE_REGIST) {
                ToastUtils.show(this, "注册成功");
            } else {
                ToastUtils.show(this, "密码设置成功");
            }
            toLogin();
        } else {
            ToastUtils.show(this, resultBean.getMsg());
        }

    }

    @OnClick({R.id.btnSetPassWord})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.titleLeft:
                finish();
                break;
            case R.id.btnSetPassWord:
                if (checkData()) {
                    if (type == 0) {
                        register();
                    } else {
                        setPw();
                    }
                }
                break;
        }
    }

    private void toLogin() {
        if (checkData()) {
            AppManager.getInstance().finishActivity(ConfirmPhoneActivity.class);
            AppManager.getInstance().finishActivity(this);
        }
    }

    private void setPw() {
        RequestParams params = new RequestParams();
        params.put("loginName", phone);
        params.put("newPassword", editPassWord.getText().toString());
        params.put("userType", 3);
        RequestManager.post(CODE_SETPW, Urls.setNewPwd, params, this);
    }

    private void register() {
        RequestParams params = new RequestParams();
        params.put("mobile", phone);
        params.put("password", editPassWord.getText().toString());
        params.put("userType", 3);
        params.put("officeId", 2);
        RequestManager.post(CODE_REGIST, Urls.register, params, this);
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
