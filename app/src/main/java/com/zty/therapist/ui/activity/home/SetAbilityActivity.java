package com.zty.therapist.ui.activity.home;

import android.support.v7.widget.AppCompatSpinner;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.loopj.android.http.RequestParams;
import com.zty.therapist.R;
import com.zty.therapist.base.BaseActivity;
import com.zty.therapist.model.ResultBean;
import com.zty.therapist.url.RequestManager;
import com.zty.therapist.url.Urls;
import com.zty.therapist.utils.OptionUtils;
import com.zty.therapist.utils.ResultUtil;
import com.zty.therapist.utils.ToastUtils;

import butterknife.BindView;

/**
 * Created by zty on 2017/1/14.
 */

public class SetAbilityActivity extends BaseActivity implements AdapterView.OnItemSelectedListener {

    @BindView(R.id.spinnerAbility1)
    AppCompatSpinner spinnerAbility1;
    @BindView(R.id.spinnerAbility2)
    AppCompatSpinner spinnerAbility2;
    @BindView(R.id.spinnerAbility3)
    AppCompatSpinner spinnerAbility3;
    @BindView(R.id.spinnerAbility4)
    AppCompatSpinner spinnerAbility4;
    @BindView(R.id.spinnerAbility5)
    AppCompatSpinner spinnerAbility5;
    private String teacherId;

    @Override
    protected int getContentView() {
        return R.layout.view_member_evaluate;
    }

    @Override
    protected void initData() {
        title.setText("评 价");
        teacherId = getIntent().getStringExtra("teacherId");
        right.setText("评价");

        spinnerAbility1.setOnItemSelectedListener(this);
        spinnerAbility2.setOnItemSelectedListener(this);
        spinnerAbility3.setOnItemSelectedListener(this);
        spinnerAbility4.setOnItemSelectedListener(this);
        spinnerAbility5.setOnItemSelectedListener(this);
        setSpinnerData();
    }

    @Override
    public void rightClick() {
        setAbility();
    }

    private void setAbility() {
        RequestParams params = new RequestParams();
        params.put("teacherId", teacherId);
        params.put("workAbility", OptionUtils.workLastCode);
        params.put("orgAbility", OptionUtils.orgLastCode);
        params.put("communicationAbility", OptionUtils.communicationLastCode);
        params.put("temper", OptionUtils.temperLastCode);
        params.put("figure", OptionUtils.figureLastCode);
        RequestManager.post(-1, Urls.setAbility, params, this);
    }

    @Override
    public void onFailureCallback(int requestCode, String errorMsg) {

    }

    @Override
    public void onSuccessCallback(int requestCode, String response) {
        ResultBean resultBean = ResultUtil.getResult(response);
        if (resultBean.isSuccess()) {
            ToastUtils.show(this, "评价成功");
            finish();
        } else {
            ToastUtils.show(this, resultBean.getMsg());
        }
    }

    private void setSpinnerData() {
        spinnerAbility1.setAdapter(new ArrayAdapter(this, R.layout.item_spinner, R.id.textSpinner, OptionUtils.workList));
        spinnerAbility2.setAdapter(new ArrayAdapter(this, R.layout.item_spinner, R.id.textSpinner, OptionUtils.orgList));
        spinnerAbility3.setAdapter(new ArrayAdapter(this, R.layout.item_spinner, R.id.textSpinner, OptionUtils.communicationList));
        spinnerAbility4.setAdapter(new ArrayAdapter(this, R.layout.item_spinner, R.id.textSpinner, OptionUtils.temperList));
        spinnerAbility5.setAdapter(new ArrayAdapter(this, R.layout.item_spinner, R.id.textSpinner, OptionUtils.figureList));
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        switch (adapterView.getId()) {
            case R.id.spinnerAbility1:
                OptionUtils.workLastCode = OptionUtils.workListCode.get(i);
                break;
            case R.id.spinnerAbility2:
                OptionUtils.orgLastCode = OptionUtils.orgListCode.get(i);
                break;
            case R.id.spinnerAbility3:
                OptionUtils.communicationLastCode = OptionUtils.communicationListCode.get(i);
                break;
            case R.id.spinnerAbility4:
                OptionUtils.temperLastCode = OptionUtils.temperListCode.get(i);
                break;
            case R.id.spinnerAbility5:
                OptionUtils.figureLastCode = OptionUtils.figureListCode.get(i);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
