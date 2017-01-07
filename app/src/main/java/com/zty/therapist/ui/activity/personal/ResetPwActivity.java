package com.zty.therapist.ui.activity.personal;

import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;

import com.loopj.android.http.RequestParams;
import com.zty.therapist.R;
import com.zty.therapist.base.BaseActivity;
import com.zty.therapist.manager.AppManager;
import com.zty.therapist.model.ResultBean;
import com.zty.therapist.url.RequestManager;
import com.zty.therapist.url.Urls;
import com.zty.therapist.utils.ResultUtil;
import com.zty.therapist.utils.SharedPrefUtils;
import com.zty.therapist.utils.ToastUtils;
import com.zty.therapist.utils.UserUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by zty on 2017/1/7.
 */

public class ResetPwActivity extends BaseActivity {
    @BindView(R.id.editOldPassWord)
    EditText editOldPassWord;
    @BindView(R.id.editPassWord)
    EditText editPassWord;
    @BindView(R.id.editSurePassWord)
    EditText editSurePassWord;
    @BindView(R.id.btnSetPassWord)
    Button btnSetPassWord;

    @Override
    protected int getContentView() {
        return R.layout.activity_reset_pw;
    }

    @Override
    protected void initData() {
        title.setText("修改密码");

    }

    @Override
    public void onFailureCallback(int requestCode, String errorMsg) {

    }

    @Override
    public void onSuccessCallback(int requestCode, String response) {
        ResultBean resultBean = ResultUtil.getResult(response);
        if (resultBean.isSuccess()) {
            UserUtils.clearUser(this);
            ToastUtils.show(this, "修改成功，请重新登录");
            AppManager.getInstance().finishAllActivity();
            startActivity(new Intent(this, LoginActivity.class));
        } else {
            ToastUtils.show(this, resultBean.getMsg());
        }
    }


    @OnClick(R.id.btnSetPassWord)
    public void onClick() {
        resetPw();
    }

    private void resetPw() {
        RequestParams params = new RequestParams();
        params.put("loginName", SharedPrefUtils.getString(this, SharedPrefUtils.LOGIN_NAME));
        params.put("oldPassword", editOldPassWord.getText().toString());
        params.put("newPassword", editPassWord.getText().toString());
        params.put("userType", 3);
        RequestManager.post(-1, Urls.updatePwd, params, this);
    }
}
