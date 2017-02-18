package com.zty.therapist.ui.activity.home;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.loopj.android.http.RequestParams;
import com.zty.therapist.R;
import com.zty.therapist.base.BaseActivity;
import com.zty.therapist.model.ResultBean;
import com.zty.therapist.url.RequestManager;
import com.zty.therapist.url.Urls;
import com.zty.therapist.utils.ResultUtil;
import com.zty.therapist.utils.TimeWheelUtils;
import com.zty.therapist.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 发布替班申请
 * Created by tianyu on 2017/1/2.
 */

public class SendReplaceActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {
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
    @BindView(R.id.textReplaceNote)
    TextView textReplaceNote;
    @BindView(R.id.editReplacerEndTime)
    EditText editReplacerEndTime;
    @BindView(R.id.btnReplacerTime)
    Button btnReplacerTime;
    @BindView(R.id.btnReplacerEndTime)
    Button btnReplacerEndTime;
    @BindView(R.id.radioGroupIntegral)
    RadioGroup radioGroupIntegral;
    @BindView(R.id.textIntegralMin)
    TextView textIntegralMin;
    @BindView(R.id.textIntegralMid)
    TextView textIntegralMid;
    @BindView(R.id.textIntegralMax)
    TextView textIntegralMax;

    @Override
    protected int getContentView() {
        return R.layout.activity_send_replace;
    }

    @Override
    protected void initData() {
        title.setText("替班申请");
        right.setText("申请");

        radioGroupIntegral.setOnCheckedChangeListener(this);
    }

    private void submitShiftRecord() {
        RequestParams params = new RequestParams();
        params.put("elderNm", editReplacerName.getText().toString());
        params.put("sex", editReplacerSex.getText().toString());
        params.put("age", editReplacerAge.getText().toString());
        params.put("mobile", editReplacerPhone.getText().toString());
        params.put("item", editReplacerPro.getText().toString());
        params.put("startDate", editReplacerTime.getText().toString());
        params.put("endDate", editReplacerEndTime.getText().toString());
        params.put("address", editReplacerAddress.getText().toString());
        params.put("description", editReplacerNote.getText().toString());
        RequestManager.post(-1, Urls.submitShiftRecord, params, this);
    }

    @Override
    public void onFailureCallback(int requestCode, String errorMsg) {

    }

    @Override
    public void onSuccessCallback(int requestCode, String response) {
        ResultBean resultBean = ResultUtil.getResult(response);
        if (resultBean.isSuccess()) {
            ToastUtils.show(this, "提交成功");
            finish();
        } else {
            ToastUtils.show(this, resultBean.getMsg());
        }

    }

    @Override
    public void rightClick() {
        if (checkData())
            submitShiftRecord();
    }

    private boolean checkData() {
        return true;
    }

    @OnClick({R.id.btnReplacerTime, R.id.btnReplacerEndTime})
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.btnReplacerTime:
                TimeWheelUtils.show(this, editReplacerTime);
                break;
            case R.id.btnReplacerEndTime:
                TimeWheelUtils.show(this, editReplacerEndTime);
                break;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (i) {
            case R.id.radioButtonIntegral1:
                break;
            case R.id.radioButtonIntegral2:
                break;
            case R.id.radioButtonIntegral3:
                break;
        }

    }
}
