package com.zty.therapist.ui.activity.personal;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.zty.therapist.R;
import com.zty.therapist.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by zty on 2016/12/27.
 */

public class ApplyActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.textApplyNote)
    TextView textApplyNote;
    @BindView(R.id.btnApply)
    Button btnApply;
    @BindView(R.id.textApplyResult)
    TextView textApplyResult;

    @Override
    protected int getContentView() {
        return R.layout.activity_apply;
    }

    @Override
    protected void initData() {
        title.setText("组长申请");
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

    @OnClick(R.id.btnApply)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.titleLeft:
                finish();
                break;
            case R.id.btnApply:
                break;
        }
    }
}
