package com.zty.therapist.ui.activity.personal;

import android.os.Bundle;
import android.widget.EditText;

import com.loopj.android.http.RequestParams;
import com.zty.therapist.R;
import com.zty.therapist.base.BaseActivity;
import com.zty.therapist.base.TherapistApplication;
import com.zty.therapist.model.ResultBean;
import com.zty.therapist.url.RequestManager;
import com.zty.therapist.utils.MyTextUtils;
import com.zty.therapist.utils.ResultUtil;
import com.zty.therapist.utils.ToastUtils;
import com.zty.therapist.utils.UserUtils;

import butterknife.BindView;

/**
 * Created by zty on 2017/1/9.
 */

public class SetTextActivity extends BaseActivity {
    @BindView(R.id.editSetText)
    EditText editSetText;

    private String message;
    private String url;
    private String strTitle;
    private String key;

    @Override
    protected int getContentView() {
        return R.layout.activity_set_text;
    }

    @Override
    protected void initData() {
        Bundle bundle = getIntent().getExtras();
        message = bundle.getString("message");
        url = bundle.getString("url");
        strTitle = bundle.getString("title");
        key = bundle.getString("key");

        editSetText.setText(MyTextUtils.isEmpty(message));
        title.setText(strTitle);
        right.setText("保存");
    }

    @Override
    public void onFailureCallback(int requestCode, String errorMsg) {

    }

    @Override
    public void onSuccessCallback(int requestCode, String response) {
        ResultBean resultBean = ResultUtil.getResult(response);
        if (resultBean.isSuccess()) {
            if (requestCode == UserUtils.CODE_GET_MESSAGE) {
                TherapistApplication.getInstance().setCurrentUser(resultBean.getResult());
                finish();
            } else {
                ToastUtils.show(this, "修改成功");
                UserUtils.getUserMessage("", this);
            }
        } else {
            ToastUtils.show(this, resultBean.getResult());
        }

    }

    @Override
    public void rightClick() {
        RequestParams params = new RequestParams();
        params.put(key, editSetText.getText().toString());
        RequestManager.post(-1, url, params, this);
    }

}
