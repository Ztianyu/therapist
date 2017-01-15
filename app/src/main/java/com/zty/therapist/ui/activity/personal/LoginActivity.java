package com.zty.therapist.ui.activity.personal;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.loopj.android.http.RequestParams;
import com.zty.therapist.MainActivity;
import com.zty.therapist.R;
import com.zty.therapist.base.BaseActivity;
import com.zty.therapist.base.TherapistApplication;
import com.zty.therapist.manager.AppManager;
import com.zty.therapist.model.LoginModel;
import com.zty.therapist.model.ResultBean;
import com.zty.therapist.url.RequestManager;
import com.zty.therapist.url.Urls;
import com.zty.therapist.utils.GsonUtils;
import com.zty.therapist.utils.ResultUtil;
import com.zty.therapist.utils.SharedPrefUtils;
import com.zty.therapist.utils.ToastUtils;
import com.zty.therapist.utils.UserUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by zty on 2016/11/23.
 */

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private static final int CODE_LOGIN = 1;

    @BindView(R.id.editLoginName)
    EditText editLoginName;
    @BindView(R.id.editLoginPassWord)
    EditText editLoginPassWord;
    @BindView(R.id.btnLogin)
    Button btnLogin;
    @BindView(R.id.btnRegister)
    Button btnRegister;
    @BindView(R.id.textForgetPw)
    TextView textForgetPw;

    @Override
    protected int getContentView() {
        return R.layout.activity_login;
    }

    @Override
    protected void initData() {
        title.setText("登  录");

        String loginName = SharedPrefUtils.getString(this, SharedPrefUtils.LOGIN_NAME);

        if (!TextUtils.isEmpty(loginName))
            editLoginName.setText(loginName);
    }

    @Override
    public void onFailureCallback(int requestCode, String errorMsg) {

    }


    @Override
    public void onSuccessCallback(int requestCode, String response) {
        ResultBean resultBean = ResultUtil.getResult(response);
        if (resultBean.isSuccess()) {
            switch (requestCode) {
                case CODE_LOGIN:
                    LoginModel model = GsonUtils.changeGsonToBean(resultBean.getResult(), LoginModel.class);
                    UserUtils.saveUser(this, model, editLoginName.getText().toString(), editLoginPassWord.getText().toString());
                    UserUtils.getUserMessage(model.getUserId(), this);
                    break;
                case UserUtils.CODE_GET_MESSAGE:
                    toMain();
                    TherapistApplication.getInstance().setCurrentUser(resultBean.getResult());
                    break;
            }
        } else {
            ToastUtils.show(this, resultBean.getMsg());
        }
    }

    @OnClick({R.id.btnLogin, R.id.btnRegister, R.id.textForgetPw})
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.btnLogin:
                login();
                break;
            case R.id.btnRegister:
                startActivity(new Intent(this, ConfirmPhoneActivity.class).putExtra("type", 0));
                break;
            case R.id.textForgetPw:
                startActivity(new Intent(this, ConfirmPhoneActivity.class).putExtra("type", 1));
                break;
        }
    }

    private void login() {
        RequestParams params = new RequestParams();
        params.put("loginName", editLoginName.getText().toString());
        params.put("password", editLoginPassWord.getText().toString());
        params.put("userType", 3);
        RequestManager.post(CODE_LOGIN, Urls.sysLogin, params, this);
    }

    private void toMain() {
        AppManager.getInstance().finishAllActivity();
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
