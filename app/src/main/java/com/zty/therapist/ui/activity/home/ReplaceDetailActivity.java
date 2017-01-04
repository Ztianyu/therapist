package com.zty.therapist.ui.activity.home;

import android.support.v4.app.Fragment;
import android.widget.TextView;

import com.zty.therapist.R;
import com.zty.therapist.base.BaseActivity;
import com.zty.therapist.model.ReplaceRecordModel;
import com.zty.therapist.ui.fragment.home.AllotFragment;

import butterknife.BindView;

/**
 * Created by zty on 2017/1/4.
 */

public class ReplaceDetailActivity extends BaseActivity {

    ReplaceRecordModel model;
    @BindView(R.id.textReplaceName)
    TextView textReplaceName;
    @BindView(R.id.textReplaceSex)
    TextView textReplaceSex;
    @BindView(R.id.textReplacePhone)
    TextView textReplacePhone;
    @BindView(R.id.textReplaceAge)
    TextView textReplaceAge;
    @BindView(R.id.editReplacerPro)
    TextView editReplacerPro;
    @BindView(R.id.editReplacerTime)
    TextView editReplacerTime;
    @BindView(R.id.editReplacerAddress)
    TextView editReplacerAddress;
    @BindView(R.id.editReplacerNote)
    TextView editReplacerNote;
    @BindView(R.id.textReplaceApplyName)
    TextView textReplaceApplyName;
    @BindView(R.id.textReplaceReName)
    TextView textReplaceReName;
    @BindView(R.id.textReplaceIntegral)
    TextView textReplaceIntegral;
    @BindView(R.id.textReplaceState)
    TextView textReplaceState;

    @Override
    protected int getContentView() {
        return R.layout.activity_replace_detail;
    }

    @Override
    protected void initData() {
        model = (ReplaceRecordModel) getIntent().getSerializableExtra("model");

        title.setText("替班详情");

        right.setText("处理");
    }

    @Override
    public void onFailureCallback(int requestCode, String errorMsg) {

    }

    @Override
    public void onSuccessCallback(int requestCode, String response) {

    }

    @Override
    public void rightClick() {
        Fragment fragment = getSupportFragmentManager().findFragmentByTag("allotFragment");

        if (fragment != null)
            getSupportFragmentManager().beginTransaction().remove(fragment);

        AllotFragment allotFragment = new AllotFragment();
        allotFragment.show(getSupportFragmentManager().beginTransaction(), "allotFragment");
    }
}
