package com.zty.therapist.ui.activity.home;

import android.view.View;
import android.widget.EditText;

import com.zty.therapist.R;
import com.zty.therapist.base.BaseActivity;

import butterknife.BindView;

/**
 * Created by zty on 2017/1/4.
 */

public class SendHelpActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.editSendHelpTitle)
    EditText editSendHelpTitle;
    @BindView(R.id.editSendHelpContent)
    EditText editSendHelpContent;
    @BindView(R.id.editSendHelpName)
    EditText editSendHelpName;
    @BindView(R.id.editSendHelpPhone)
    EditText editSendHelpPhone;

    @Override
    protected int getContentView() {
        return R.layout.activity_send_help;
    }

    @Override
    protected void initData() {
        title.setText("发布信息");
        right.setText("发布");
    }

    @Override
    public void onFailureCallback(int requestCode, String errorMsg) {

    }

    @Override
    public void onSuccessCallback(int requestCode, String response) {

    }

    @Override
    public void rightClick() {
    }
}
