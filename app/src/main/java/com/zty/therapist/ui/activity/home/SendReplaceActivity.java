package com.zty.therapist.ui.activity.home;

import android.widget.EditText;
import android.widget.TextView;

import com.zty.therapist.R;
import com.zty.therapist.base.BaseActivity;

import butterknife.BindView;

/**
 * 发布替班申请
 * Created by tianyu on 2017/1/2.
 */

public class SendReplaceActivity extends BaseActivity {
    @BindView(R.id.editReplacerName)
    EditText editReplacerName;
    @BindView(R.id.editReplacerSex)
    EditText editReplacerSex;
    @BindView(R.id.editReplacerPhone)
    EditText editReplacerPhone;
    @BindView(R.id.editReplacerAge)
    EditText editReplacerAge;
    @BindView(R.id.editReplacerPro)
    EditText editReplacerPro;
    @BindView(R.id.editReplacerTime)
    EditText editReplacerTime;
    @BindView(R.id.editReplacerAddress)
    EditText editReplacerAddress;
    @BindView(R.id.editReplacerNote)
    EditText editReplacerNote;
    @BindView(R.id.editReplacerIntegral)
    EditText editReplacerIntegral;
    @BindView(R.id.textReplaceNote)
    TextView textReplaceNote;

    @Override
    protected int getContentView() {
        return R.layout.activity_send_replace;
    }

    @Override
    protected void initData() {
        title.setText("替班申请");
        right.setText("申请");
    }

    @Override
    public void onFailureCallback(int requestCode, String errorMsg) {

    }

    @Override
    public void onSuccessCallback(int requestCode, String response) {

    }

    @Override
    public void rightClick() {
        super.rightClick();
    }
}
