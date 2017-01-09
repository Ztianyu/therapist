package com.zty.therapist.ui.activity.personal;

import android.view.View;

import com.zty.therapist.R;
import com.zty.therapist.base.BaseActivity;
import com.zty.therapist.base.TherapistApplication;
import com.zty.therapist.model.UserModel;
import com.zty.therapist.utils.MyImageLoader;
import com.zty.therapist.widget.CircleImageView;
import com.zty.therapist.widget.StripViewNoImg;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by zty on 2016/12/26.
 */

public class UserMessageActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.imgUserHeader)
    CircleImageView imgUserHeader;
    @BindView(R.id.textUserName)
    StripViewNoImg textUserName;
    @BindView(R.id.textUserSex)
    StripViewNoImg textUserSex;
    @BindView(R.id.textUserAge)
    StripViewNoImg textUserAge;
    @BindView(R.id.textUserPhone)
    StripViewNoImg textUserPhone;
    @BindView(R.id.textUserPlace)
    StripViewNoImg textUserPlace;
    @BindView(R.id.textUserIdCard)
    StripViewNoImg textUserIdCard;
    @BindView(R.id.textUserSpecialty)
    StripViewNoImg textUserSpecialty;
    @BindView(R.id.textUserSkill)
    StripViewNoImg textUserSkill;
    @BindView(R.id.textUserAddress)
    StripViewNoImg textUserAddress;
    @BindView(R.id.textUserWorkSpaceName)
    StripViewNoImg textUserWorkSpaceName;
    @BindView(R.id.textUserWorkSpaceAdd)
    StripViewNoImg textUserWorkSpaceAdd;
    @BindView(R.id.textUserWorkSpacePhone)
    StripViewNoImg textUserWorkSpacePhone;

    private UserModel userModel;

    @Override
    protected int getContentView() {
        return R.layout.activity_user_data;
    }

    @Override
    protected void initData() {
        title.setText("个人资料");

        userModel = TherapistApplication.getInstance().getUserModel();
        setText();
    }

    @Override
    public void onFailureCallback(int requestCode, String errorMsg) {

    }

    @Override
    public void onSuccessCallback(int requestCode, String response) {

    }


    private void setText() {
        MyImageLoader.load(this, userModel.getPhoto(), imgUserHeader);
        textUserName.setAdditionText(userModel.getTeacherNm());
        textUserSex.setAdditionText(userModel.getSex());
        textUserAge.setAdditionText(userModel.getAge() + "岁");
        textUserPhone.setAdditionText(userModel.getMobile());
        textUserIdCard.setAdditionText(userModel.getIdCard());
        textUserPlace.setAdditionText(userModel.getNativePlaceValue());

    }

    @OnClick({R.id.textUserName, R.id.imgUserHeader, R.id.textUserSex, R.id.textUserAge, R.id.textUserPhone, R.id.textUserPlace, R.id.textUserIdCard, R.id.textUserSpecialty, R.id.textUserSkill, R.id.textUserAddress, R.id.textUserWorkSpaceName, R.id.textUserWorkSpaceAdd, R.id.textUserWorkSpacePhone})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgUserHeader:
                break;
            case R.id.textUserName:
                break;
            case R.id.textUserSex:
                break;
            case R.id.textUserAge:
                break;
            case R.id.textUserPhone:
                break;
            case R.id.textUserPlace:
                break;
            case R.id.textUserIdCard:
                break;
            case R.id.textUserSpecialty:
                break;
            case R.id.textUserSkill:
                break;
            case R.id.textUserAddress:
                break;
            case R.id.textUserWorkSpaceName:
                break;
            case R.id.textUserWorkSpaceAdd:
                break;
            case R.id.textUserWorkSpacePhone:
                break;
        }
    }
}
