package com.zty.therapist.ui.activity.home;

import android.support.v7.widget.AppCompatSpinner;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.zty.therapist.R;
import com.zty.therapist.base.BaseActivity;
import com.zty.therapist.base.TherapistApplication;
import com.zty.therapist.model.AbilityOptionModel;
import com.zty.therapist.model.ResultBean;
import com.zty.therapist.model.UserModel;
import com.zty.therapist.url.RequestManager;
import com.zty.therapist.url.Urls;
import com.zty.therapist.utils.ResultUtil;
import com.zty.therapist.utils.UserUtils;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by zty on 2017/1/11.
 */

public class MemberDetailActivity extends BaseActivity {

    private static final int CODE_GET_OPTION = 0;

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

    private int role;

    private boolean isCanRemark = false;

    @Override
    protected int getContentView() {
        return R.layout.activity_member_detail;
    }

    @Override
    protected void initData() {
        userId = getIntent().getStringExtra("userId");
        UserUtils.getUserMessage(userId, this);
        title.setText("详 情");

        role = TherapistApplication.getInstance().getRole();
        if (role == 0) {
            btnMemberHandle.setVisibility(View.INVISIBLE);
        } else if (role == 1) {
            right.setText("评价");
            btnMemberHandle.setText("从该组移除");
            btnMemberHandle.setVisibility(View.VISIBLE);
        }
    }

    private void getAbilityOption() {
        RequestManager.get(CODE_GET_OPTION, Urls.getAbilityOption, null, this);
    }

    private void setAbilityState(boolean state) {
        spinnerAbility1.setEnabled(state);
        spinnerAbility2.setEnabled(state);
        spinnerAbility3.setEnabled(state);
        spinnerAbility4.setEnabled(state);
        spinnerAbility5.setEnabled(state);
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
                }
                break;
            case CODE_GET_OPTION:
                handleOptions(response);
                break;
        }
    }

    private void setUserMessage(UserModel userModel) {
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

        getAbilityOption();
    }

    AbilityOptionModel optionModel;

    private void handleOptions(String result) {
        try {
            JSONObject jsonObject = new JSONObject(result);
            if (jsonObject.has("head")) {
                AbilityOptionModel abilityOptionModel = new Gson().fromJson(jsonObject.getString("head"), AbilityOptionModel.class);
                if (abilityOptionModel != null)
                    optionModel = abilityOptionModel;
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

    }

    private void handle() {

    }
}
