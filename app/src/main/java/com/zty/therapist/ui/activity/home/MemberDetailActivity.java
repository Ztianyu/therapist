package com.zty.therapist.ui.activity.home;

import android.support.v7.widget.AppCompatSpinner;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.zty.therapist.R;
import com.zty.therapist.base.BaseActivity;
import com.zty.therapist.base.TherapistApplication;
import com.zty.therapist.config.Config;
import com.zty.therapist.inter.DialogListener;
import com.zty.therapist.model.AbilityOptionModel;
import com.zty.therapist.model.ResultBean;
import com.zty.therapist.model.UserModel;
import com.zty.therapist.url.RequestManager;
import com.zty.therapist.url.Urls;
import com.zty.therapist.utils.DialogUtils;
import com.zty.therapist.utils.OptionUtils;
import com.zty.therapist.utils.ResultUtil;
import com.zty.therapist.utils.SharedPrefUtils;
import com.zty.therapist.utils.ToastUtils;
import com.zty.therapist.utils.UserUtils;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by zty on 2017/1/11.
 */

public class MemberDetailActivity extends BaseActivity implements AdapterView.OnItemSelectedListener, DialogListener {

    private static final int CODE_GET_OPTION = 0;
    private static final int CODE_SUBMIT_OPTION = 1;
    private static final int CODE_EXIT_GROUP = 3;

    @BindView(R.id.textMemberDetailName)
    TextView textMemberDetailName;
    @BindView(R.id.textMemberDetailSex)
    TextView textMemberDetailSex;
    @BindView(R.id.textMemberDetailPhone)
    TextView textMemberDetailPhone;
    @BindView(R.id.textMemberDetailAge)
    TextView textMemberDetailAge;
    @BindView(R.id.editUserWorkTime)
    TextView editUserWorkTime;
    @BindView(R.id.editUserIdCard)
    TextView editUserIdCard;
    @BindView(R.id.editUserPlace)
    TextView editUserPlace;
    @BindView(R.id.editUserSpecialty)
    TextView editUserSpecialty;
    @BindView(R.id.editUserSkill)
    TextView editUserSkill;
    @BindView(R.id.editUserAddress)
    TextView editUserAddress;
    @BindView(R.id.editUserWorkSpaceName)
    TextView editUserWorkSpaceName;
    @BindView(R.id.editUserWorkAddress)
    TextView editUserWorkAddress;
    @BindView(R.id.editUserWorkPhone)
    TextView editUserWorkPhone;
    @BindView(R.id.btnMemberHandle)
    TextView btnMemberHandle;
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

    private String userId;
    private String teacherId;

    private int role;

    private boolean isCanRemark = false;

    @Override
    protected int getContentView() {
        return R.layout.activity_member_detail;
    }

    @Override
    protected void initData() {
        userId = getIntent().getStringExtra("userId");

        getAbilityOption();

        title.setText("详 情");
        role = TherapistApplication.getInstance().getRole();
        if (role == 0) {
            right.setVisibility(View.INVISIBLE);
            btnMemberHandle.setVisibility(View.INVISIBLE);
        } else if (role == 1) {
            right.setText("评价");
            btnMemberHandle.setText("从该组移除");
            btnMemberHandle.setVisibility(View.VISIBLE);
        }

        spinnerAbility1.setOnItemSelectedListener(this);
        spinnerAbility2.setOnItemSelectedListener(this);
        spinnerAbility3.setOnItemSelectedListener(this);
        spinnerAbility4.setOnItemSelectedListener(this);
        spinnerAbility5.setOnItemSelectedListener(this);
    }

    private void getAbilityOption() {
        RequestManager.get(CODE_GET_OPTION, Urls.getAbilityOption, null, this);
    }

    private void setAbility() {
        RequestParams params = new RequestParams();
        params.put("teacherId", teacherId);
        params.put("workAbility", OptionUtils.workLastCode);
        params.put("orgAbility", OptionUtils.orgLastCode);
        params.put("communicationAbility", OptionUtils.communicationLastCode);
        params.put("temper", OptionUtils.temperLastCode);
        params.put("figure", OptionUtils.figureLastCode);
        RequestManager.post(CODE_SUBMIT_OPTION, Urls.setAbility, params, this);
    }

    private void setSpinnerData(AbilityOptionModel abilityOptionModel) {
        OptionUtils.setList(abilityOptionModel);
        spinnerAbility1.setAdapter(new ArrayAdapter(this, R.layout.item_spinner, R.id.textSpinner, OptionUtils.workList));
        spinnerAbility2.setAdapter(new ArrayAdapter(this, R.layout.item_spinner, R.id.textSpinner, OptionUtils.orgList));
        spinnerAbility3.setAdapter(new ArrayAdapter(this, R.layout.item_spinner, R.id.textSpinner, OptionUtils.communicationList));
        spinnerAbility4.setAdapter(new ArrayAdapter(this, R.layout.item_spinner, R.id.textSpinner, OptionUtils.temperList));
        spinnerAbility5.setAdapter(new ArrayAdapter(this, R.layout.item_spinner, R.id.textSpinner, OptionUtils.figureList));
    }

    private void setAbilityState(boolean state) {
        spinnerAbility1.setSelected(state);
        spinnerAbility2.setSelected(state);
        spinnerAbility3.setSelected(state);
        spinnerAbility4.setSelected(state);
        spinnerAbility5.setSelected(state);
    }

    @Override
    public void onFailureCallback(int requestCode, String errorMsg) {

    }

    @Override
    public void onSuccessCallback(int requestCode, String response) {
        switch (requestCode) {
            case UserUtils.CODE_GET_MESSAGE:
                ResultBean resultBean = ResultUtil.getResult(response);
                if (resultBean.isSuccess()) {
                    UserModel userModel = new Gson().fromJson(resultBean.getResult(), UserModel.class);
                    setUserMessage(userModel);
                } else {
                    ToastUtils.show(this, resultBean.getMsg());
                }
                break;
            case CODE_GET_OPTION:
                UserUtils.getUserMessage(userId, this);
                handleOptions(response);
                break;
            case CODE_SUBMIT_OPTION:
                ResultBean resultBean1 = ResultUtil.getResult(response);
                if (resultBean1.isSuccess()) {
                    ToastUtils.show(this, "评价成功");
                    UserUtils.getUserMessage(userId, this);
                    isCanRemark = !isCanRemark;
                    setAbilityState(isCanRemark);
                    right.setText("评价");
                } else {
                    ToastUtils.show(this, resultBean1.getMsg());
                }
                break;
            case CODE_EXIT_GROUP:
                ResultBean resultBean2 = ResultUtil.getResult(response);
                if (resultBean2.isSuccess()) {
                    ToastUtils.show(this, "移除成功");
                    btnMemberHandle.setVisibility(View.INVISIBLE);
                } else {
                    ToastUtils.show(this, resultBean2.getMsg());
                }
                break;
        }
    }

    private void setUserMessage(UserModel userModel) {
        teacherId = userModel.getId();

        textMemberDetailName.setText(userModel.getTeacherNm());
        textMemberDetailSex.setText(userModel.getSex());
        textMemberDetailPhone.setText(userModel.getMobile());
        textMemberDetailAge.setText(userModel.getAge() + " 岁");
        editUserWorkTime.setText(userModel.getWorkingLife() + " 年");
        editUserIdCard.setText(userModel.getIdCard());
        editUserPlace.setText(userModel.getNativePlaceValue());
        editUserSpecialty.setText(userModel.getExpert());
        editUserSkill.setText(userModel.getServiceSkill());
        editUserAddress.setText(userModel.getAddress());
        editUserWorkSpaceName.setText(userModel.getWorkUnit());
        editUserWorkAddress.setText(userModel.getUnitAddress());
        editUserWorkPhone.setText(userModel.getUnitTel());

        if (userModel.getWorkAbility() > 0)
            spinnerAbility1.setSelection(userModel.getWorkAbility() - 1);
        if (userModel.getOrgAbility() > 0)
            spinnerAbility2.setSelection(userModel.getOrgAbility() - 1);
        if (userModel.getCommunicationAbility() > 0)
            spinnerAbility3.setSelection(userModel.getCommunicationAbility() - 1);
        if (userModel.getTemper() > 0)
            spinnerAbility4.setSelection(userModel.getTemper() - 1);
        if (userModel.getFigure() > 0)
            spinnerAbility5.setSelection(userModel.getFigure() - 1);
    }

    private void handleOptions(String result) {
        SharedPrefUtils.setString(this, SharedPrefUtils.AbilityOption, result);
        try {
            JSONObject jsonObject = new JSONObject(result);
            if (jsonObject.has("head")) {
                AbilityOptionModel abilityOptionModel = new Gson().fromJson(jsonObject.getString("head"), AbilityOptionModel.class);
                if (abilityOptionModel != null) {
                    setSpinnerData(abilityOptionModel);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    @OnClick({R.id.btnMemberHandle})
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.btnMemberHandle:
                handle();
                break;
        }
    }

    @Override
    public void rightClick() {
        isCanRemark = !isCanRemark;
        setAbilityState(isCanRemark);
        if (isCanRemark) {
            right.setText("保存");
        } else {
            setAbility();
        }
    }

    private void handle() {
        DialogUtils.show(this, "是否移除该成员", this);
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

    @Override
    public void onSure() {
        RequestParams params = new RequestParams();
        params.put("type", Config.CODE_SUBMIT_INCITE_INFORM_1);
        params.put("operator", userId);
        params.put("operation", Config.EXIT_GROUP);
        params.put("state", 3);
        RequestManager.post(CODE_EXIT_GROUP, Urls.submitInviteInform, params, this);
    }
}
