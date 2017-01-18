package com.zty.therapist.ui.activity.home;

import android.content.Intent;
import android.view.View;
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
import com.zty.therapist.service.InviteInformUtils;
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

public class MemberDetailActivity extends BaseActivity implements DialogListener {

    private static final int CODE_GET_OPTION = 0;
    private static final int CODE_EXIT_GROUP = 3;
    private static final int CODE_EXIT_CLASS = 4;

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
    @BindView(R.id.textAbility1)
    TextView textAbility1;
    @BindView(R.id.textAbility2)
    TextView textAbility2;
    @BindView(R.id.textAbility3)
    TextView textAbility3;
    @BindView(R.id.textAbility4)
    TextView textAbility4;
    @BindView(R.id.textAbility5)
    TextView textAbility5;
    @BindView(R.id.btnMemberEvaluate)
    TextView btnMemberEvaluate;

    private String userId;
    private String teacherId;

    private int role;

    private int type;

    @Override
    protected int getContentView() {
        return R.layout.activity_member_detail;
    }

    @Override
    protected void initData() {
        userId = getIntent().getStringExtra("userId");
        type = getIntent().getIntExtra("type", 0);

        getAbilityOption();

        title.setText("详 情");
        role = TherapistApplication.getInstance().getRole();
        if (role == 0) {
            right.setVisibility(View.INVISIBLE);
            btnMemberHandle.setVisibility(View.INVISIBLE);
            btnMemberEvaluate.setVisibility(View.INVISIBLE);
        } else if (role == 1) {
            btnMemberHandle.setText("移除该成员");
            btnMemberHandle.setVisibility(View.VISIBLE);
        } else if (role == 2) {
            if (type == 0) {
                setRight(R.mipmap.ic_group_member);
                btnMemberHandle.setText("移除该组长");
                btnMemberHandle.setVisibility(View.VISIBLE);
                btnMemberEvaluate.setVisibility(View.VISIBLE);
            } else {
                btnMemberHandle.setVisibility(View.INVISIBLE);
                btnMemberEvaluate.setVisibility(View.INVISIBLE);
            }
        } else if (role == 3) {
            btnMemberHandle.setVisibility(View.INVISIBLE);
            btnMemberEvaluate.setVisibility(View.INVISIBLE);
            setRight(R.mipmap.ic_group_member);
            if (type == 0) {
                right.setVisibility(View.VISIBLE);
            } else if (type == 1) {
                right.setVisibility(View.VISIBLE);
            } else {
                right.setVisibility(View.INVISIBLE);
            }
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        UserUtils.getUserMessage(userId, this);
    }

    private void getAbilityOption() {
        RequestManager.get(CODE_GET_OPTION, Urls.getAbilityOption, null, this);
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
            case CODE_EXIT_GROUP:
            case CODE_EXIT_CLASS:
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
            textAbility1.setText(OptionUtils.getValue(OptionUtils.TYPE_WORK, userModel.getWorkAbility()));
        if (userModel.getOrgAbility() > 0)
            textAbility2.setText(OptionUtils.getValue(OptionUtils.TYPE_ORG, userModel.getOrgAbility()));
        if (userModel.getCommunicationAbility() > 0)
            textAbility3.setText(OptionUtils.getValue(OptionUtils.TYPE_COMMUNICATION, userModel.getCommunicationAbility()));
        if (userModel.getTemper() > 0)
            textAbility4.setText(OptionUtils.getValue(OptionUtils.TYPE_TEMPER, userModel.getTemper()));
        if (userModel.getFigure() > 0)
            textAbility5.setText(OptionUtils.getValue(OptionUtils.TYPE_FIGURE, userModel.getFigure()));

    }

    private void handleOptions(String result) {
        SharedPrefUtils.setString(this, SharedPrefUtils.AbilityOption, result);
        try {
            JSONObject jsonObject = new JSONObject(result);
            if (jsonObject.has("head")) {
                AbilityOptionModel abilityOptionModel = new Gson().fromJson(jsonObject.getString("head"), AbilityOptionModel.class);
                if (abilityOptionModel != null) {
                    OptionUtils.setList(abilityOptionModel);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    @OnClick({R.id.btnMemberHandle, R.id.btnMemberEvaluate})
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.btnMemberHandle:
                handle();
                break;
            case R.id.btnMemberEvaluate:
                startActivity(new Intent(this, SetAbilityActivity.class).putExtra("teacherId", teacherId));
                break;
        }
    }

    @Override
    public void rightClick() {
        if (role == 3) {
            if (type == 0) {
                startActivity(new Intent(this, MemberListActivity.class).putExtra("userId", userId).putExtra("type", 1));
            } else if (type == 1) {
                startActivity(new Intent(this, MemberListActivity.class).putExtra("userId", userId).putExtra("type", 2));
            }
        } else {
            startActivity(new Intent(this, MemberListActivity.class).putExtra("userId", userId).putExtra("type", 0));
        }
    }

    private void handle() {
        if (role == 1) {
            DialogUtils.show(this, "是否移除该成员", this);
        } else if (role == 2) {
            DialogUtils.show(this, "是否移除该组长", this);
        }
    }

    @Override
    public void onSure() {
        if (role == 1) {
            InviteInformUtils.submitInviteInform(CODE_EXIT_GROUP, Config.CODE_SUBMIT_INCITE_INFORM_1, userId, Config.EXIT_GROUP, 3, this);
        } else {
            InviteInformUtils.submitInviteInform(CODE_EXIT_CLASS, Config.CODE_SUBMIT_INCITE_INFORM_2, userId, Config.EXIT_CLASS, 3, this);
        }
    }
}
