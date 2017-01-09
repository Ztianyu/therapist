package com.zty.therapist.ui.activity.personal;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.zty.therapist.R;
import com.zty.therapist.base.BaseActivity;
import com.zty.therapist.config.Config;
import com.zty.therapist.utils.ToastUtils;
import com.zty.therapist.utils.ValidateUtil;

import butterknife.BindView;
import butterknife.OnClick;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

/**
 * 手机验证码界面
 * Created by zty on 2016/12/27.
 */

public class ConfirmPhoneActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.editPhone)
    EditText editPhone;
    @BindView(R.id.editCode)
    EditText editCode;
    @BindView(R.id.btnGetCode)
    Button btnGetCode;
    @BindView(R.id.btnConfirmPhone)
    Button btnConfirmPhone;

    private int type;//0:注册；1：忘记密码

    private String strPhone;

    @Override
    protected int getContentView() {
        return R.layout.activity_confirm;
    }

    @Override
    protected void initData() {
        type = getIntent().getIntExtra("type", 0);

        String strTitle = "";

        if (type == 0) {
            strTitle = "注  册";
        } else if (type == 1) {
            strTitle = "忘记密码";
        }
        title.setText(strTitle);
        left.setBackgroundResource(R.mipmap.ic_back);
        left.setOnClickListener(this);
        right.setVisibility(View.INVISIBLE);

        initMob();
    }

    private void initMob() {
        SMSSDK.initSDK(this, Config.MOB_APP_KEY, Config.MOB_APP_SECRET);
        SMSSDK.registerEventHandler(eventHandler);
    }

    EventHandler eventHandler = new EventHandler() {

        @Override
        public void afterEvent(int event, int result, Object data) {
            Message msg = new Message();
            msg.arg1 = event;
            msg.arg2 = result;
            msg.obj = data;
            codeHandler.sendMessage(msg);
        }
    };

    @Override
    public void onFailureCallback(int requestCode, String errorMsg) {

    }

    @Override
    public void onSuccessCallback(int requestCode, String response) {

    }

    @OnClick({R.id.btnGetCode, R.id.btnConfirmPhone})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.titleLeft:
                finish();
                break;
            case R.id.btnGetCode:
                if (confirmPhone())
                    SMSSDK.getVerificationCode("86", editPhone.getText().toString());
                break;
            case R.id.btnConfirmPhone:
                if (!TextUtils.isEmpty(editCode.getText().toString())) {
                    SMSSDK.submitVerificationCode("86", editPhone.getText().toString(), editCode.getText().toString());
                } else {
                    ToastUtils.show(this, "请输入验证码");
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        SMSSDK.unregisterEventHandler(eventHandler);
    }

    private boolean confirmPhone() {
        strPhone = editPhone.getText().toString();
        if (TextUtils.isEmpty(strPhone)) {
            ToastUtils.show(this, "请输入手机号");
            return false;
        }

        if (!ValidateUtil.isPhone(strPhone)) {
            ToastUtils.show(this, "请输入正确的手机号");
            return false;
        }
        return true;
    }

    int i = 30;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if (msg.what == -9) {
                btnGetCode.setText("重新发送(" + i + ")");
            } else if (msg.what == -8) {
                btnGetCode.setText("获取验证码");
                btnGetCode.setClickable(true);
                i = 30;
            }
        }
    };

    Handler codeHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            int event = msg.arg1;
            int result = msg.arg2;
            Object data = msg.obj;

            if (result == SMSSDK.RESULT_COMPLETE) {
                //回调完成
                if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                    //提交验证码成功
                    startActivity(new Intent(ConfirmPhoneActivity.this, SetPassWordActivity.class).putExtra("phone", strPhone).putExtra("type", type));
                } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                    //获取验证码成功
                    sendCode();
                } else if (event == SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES) {
                    //返回支持发送验证码的国家列表
                }
            } else {
                ((Throwable) data).printStackTrace();
            }
        }
    };

    private void sendCode() {
        btnGetCode.setClickable(false);
        btnGetCode.setText("重新发送(" + i + ")");

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (; i > 0; i--) {
                    handler.sendEmptyMessage(-9);
                    if (i <= 0) {
                        break;
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                handler.sendEmptyMessage(-8);
            }
        }).start();
    }
}
