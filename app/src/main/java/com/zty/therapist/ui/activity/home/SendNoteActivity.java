package com.zty.therapist.ui.activity.home;

import android.text.TextUtils;
import android.widget.EditText;

import com.loopj.android.http.RequestParams;
import com.zty.therapist.R;
import com.zty.therapist.base.BaseActivity;
import com.zty.therapist.model.ResultBean;
import com.zty.therapist.url.RequestManager;
import com.zty.therapist.url.Urls;
import com.zty.therapist.utils.ResultUtil;
import com.zty.therapist.utils.ToastUtils;

import butterknife.BindView;

/**
 * Created by zty on 2017/1/14.
 */

public class SendNoteActivity extends BaseActivity {
    @BindView(R.id.editSendHelpTitle)
    EditText editSendHelpTitle;
    @BindView(R.id.editSendHelpContent)
    EditText editSendHelpContent;

    @Override
    protected int getContentView() {
        return R.layout.activity_send_note;
    }

    @Override
    protected void initData() {
        title.setText("公 告");
        right.setText("发布");
    }

    @Override
    public void onFailureCallback(int requestCode, String errorMsg) {

    }

    @Override
    public void onSuccessCallback(int requestCode, String response) {
        ResultBean resultBean = ResultUtil.getResult(response);
        if (resultBean.isSuccess()) {
            ToastUtils.show(this, "发布成功");
            finish();
        } else {
            ToastUtils.show(this, resultBean.getMsg());
        }

    }

    @Override
    public void rightClick() {
        if (checkData()) {
            RequestParams params = new RequestParams();
            params.put("title", editSendHelpTitle.getText().toString());
            params.put("content", editSendHelpContent.getText().toString());
            RequestManager.post(-1, Urls.submitRehabilitationBulletin, params, this);
        }
    }

    private boolean checkData() {
        if (TextUtils.isEmpty(editSendHelpTitle.getText().toString())) {
            ToastUtils.show(this, "请输入标题");
            return false;
        }
        if (TextUtils.isEmpty(editSendHelpContent.getText().toString())) {
            ToastUtils.show(this, "请输入公告内容");
            return false;
        }
        return true;
    }
}
