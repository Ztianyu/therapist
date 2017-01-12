package com.zty.therapist.ui.activity.home;

import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.zty.therapist.R;
import com.zty.therapist.base.BaseActivity;
import com.zty.therapist.base.TherapistApplication;
import com.zty.therapist.inter.OnDistributeRelay;
import com.zty.therapist.model.ReplaceRecordModel;
import com.zty.therapist.model.ResultBean;
import com.zty.therapist.ui.fragment.home.AllotFragment;
import com.zty.therapist.url.RequestManager;
import com.zty.therapist.url.Urls;
import com.zty.therapist.utils.MyTextUtils;
import com.zty.therapist.utils.ResultUtil;
import com.zty.therapist.utils.ToastUtils;

import butterknife.BindView;

/**
 * Created by zty on 2017/1/4.
 */

public class ReplaceDetailActivity extends BaseActivity implements OnDistributeRelay {

    private static final int CODE_GET_DETAIL = 0;
    private static final int CODE_SUBMIT = 1;

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

    private String shiftId;

    private int role;
    private int state;

    private String strState;

    @Override
    protected int getContentView() {
        return R.layout.activity_replace_detail;
    }

    @Override
    protected void initData() {
        role = TherapistApplication.getInstance().getRole();
        shiftId = getIntent().getStringExtra("shiftId");
        title.setText("替班详情");
        getDetail();
    }

    private void getDetail() {
        RequestParams params = new RequestParams();
        params.put("shiftId", shiftId);
        RequestManager.get(CODE_GET_DETAIL, Urls.getShiftRecord, params, this);
    }

    private void setData(ReplaceRecordModel model) {
        state = model.getState();

        if (state == 0) {
            strState = ReplaceRecordModel.strState1;
        } else if (state == 1) {
            strState = ReplaceRecordModel.strState2;
        } else if (state == 2) {
            strState = ReplaceRecordModel.strState3;
        } else if (state == 3) {
            strState = ReplaceRecordModel.strState4;
        } else if (state == 4) {
            strState = ReplaceRecordModel.strState5;
        }

        if (role == 0) {
            right.setVisibility(View.INVISIBLE);
        } else {
            if (state == 0 || state == 1) {
                right.setVisibility(View.VISIBLE);
            } else {
                right.setVisibility(View.INVISIBLE);
            }
        }

        right.setText("处理");
        textReplaceName.setText(model.getElderNm());
        textReplaceSex.setText(model.getSex());
        textReplacePhone.setText(model.getMobile());
        textReplaceAge.setText(model.getAge() + " 岁");
        editReplacerPro.setText(model.getItem());
        editReplacerTime.setText(model.getStartDate() + " ~ " + model.getEndDate());
        editReplacerAddress.setText(model.getAddress());
        editReplacerNote.setText(model.getDescription());
        textReplaceApplyName.setText("申请人：" + model.getTeacherNm());
        textReplaceReName.setText("替班人：" + MyTextUtils.isEmpty(model.getRelayNm()));
        textReplaceIntegral.setText("替班积分：" + model.getIntegral());
        textReplaceState.setText(strState);
    }

    @Override
    public void onFailureCallback(int requestCode, String errorMsg) {

    }

    @Override
    public void onSuccessCallback(int requestCode, String response) {
        ResultBean resultBean = ResultUtil.getResult(response);
        if (resultBean.isSuccess()) {
            switch (requestCode) {
                case CODE_GET_DETAIL:
                    ReplaceRecordModel model = new Gson().fromJson(resultBean.getResult(), ReplaceRecordModel.class);
                    if (model != null)
                        setData(model);
                    break;
                case CODE_SUBMIT:
                    ToastUtils.show(this, "分配成功");
                    getDetail();
                    break;
            }
        } else {
            ToastUtils.show(this, resultBean.getMsg());
        }
    }

    @Override
    public void rightClick() {
        Fragment fragment = getSupportFragmentManager().findFragmentByTag("allotFragment");

        if (fragment != null)
            getSupportFragmentManager().beginTransaction().remove(fragment);

        AllotFragment allotFragment = new AllotFragment();
        allotFragment.show(getSupportFragmentManager().beginTransaction(), "allotFragment");
    }

    @Override
    public void onDistribute(String lastUserId) {
        RequestParams params = new RequestParams();
        params.put("shiftId", shiftId);
        params.put("relay", lastUserId);
        RequestManager.post(CODE_SUBMIT, Urls.distributeRelay, params, this);
    }
}
