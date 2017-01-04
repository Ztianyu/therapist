package com.zty.therapist.ui.activity.personal;

import android.view.View;
import android.widget.EditText;

import com.zty.therapist.R;
import com.zty.therapist.base.BaseActivity;
import com.zty.therapist.utils.MyImageLoader;
import com.zty.therapist.widget.CircleImageView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by zty on 2016/12/26.
 */

public class UserMessageActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.imgUserHeader)
    CircleImageView imgUserHeader;
    @BindView(R.id.editUserName)
    EditText editUserName;
    @BindView(R.id.editUserSex)
    EditText editUserSex;
    @BindView(R.id.editUserAge)
    EditText editUserAge;
    @BindView(R.id.editUserPhone)
    EditText editUserPhone;
    @BindView(R.id.editUserSpecialty)
    EditText editUserSpecialty;
    @BindView(R.id.editUserPlace)
    EditText editUserPlace;
    @BindView(R.id.editUserIdCard)
    EditText editUserIdCard;
    @BindView(R.id.editUserWorkTime)
    EditText editUserWorkTime;
    @BindView(R.id.editUserSkill)
    EditText editUserSkill;
    @BindView(R.id.editUserAddress)
    EditText editUserAddress;
    @BindView(R.id.editUserWorkSpaceName)
    EditText editUserWorkSpaceName;
    @BindView(R.id.editUserWorkAddress)
    EditText editUserWorkAddress;
    @BindView(R.id.editUserWorkPhone)
    EditText editUserWorkPhone;

    private boolean isEnable = false;


    @Override
    protected int getContentView() {
        return R.layout.activity_user_message;
    }

    @Override
    protected void initData() {

        title.setText("购 买");
        left.setBackgroundResource(R.mipmap.ic_back);
        left.setOnClickListener(this);
        right.setBackgroundResource(R.mipmap.ic_publish);
        right.setOnClickListener(this);
        setText();
    }

    @Override
    public void onFailureCallback(int requestCode, String errorMsg) {

    }

    @Override
    public void onSuccessCallback(int requestCode, String response) {

    }

    @OnClick({R.id.imgUserHeader, R.id.editUserSex})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.titleLeft:
                finish();
                break;
            case R.id.titleRight:
                setEdit();
                break;
            case R.id.imgUserHeader:
                break;
            case R.id.editUserSex:
                break;
        }
    }

    private void setText() {
        MyImageLoader.load(this, "http://img3.imgtn.bdimg.com/it/u=4066817619,1880978772&fm=214&gp=0.jpg", imgUserHeader);
        editUserName.setText("王丽");
        editUserSex.setText("女");
        editUserAge.setText("35");
        editUserPhone.setText("15975463216");
        editUserSpecialty.setText("脑血栓康复护理");
        editUserPlace.setText("河北省保定市原阳县东桥石坡村");
        editUserIdCard.setText("1101051199201642568");
        editUserWorkTime.setText("3");
        editUserSkill.setText("家庭护理、育儿护理、室内保洁、脑血栓护理");
        editUserAddress.setText("北京市海淀区东阳街道梅花小区2栋1单元105号");
        editUserWorkSpaceName.setText("北京康乐汇家政公司");
        editUserWorkAddress.setText("北京市海淀区东阳街道28号");
        editUserWorkPhone.setText("010-2579381");
    }

    private void setEdit() {

        isEnable = !isEnable;
        
        editUserName.setEnabled(isEnable);
        editUserSex.setEnabled(isEnable);
        editUserAge.setEnabled(isEnable);
        editUserPhone.setEnabled(isEnable);
        editUserSpecialty.setEnabled(isEnable);
        editUserPlace.setEnabled(isEnable);
        editUserIdCard.setEnabled(isEnable);
        editUserWorkTime.setEnabled(isEnable);
        editUserSkill.setEnabled(isEnable);
        editUserAddress.setEnabled(isEnable);
        editUserWorkSpaceName.setEnabled(isEnable);
        editUserWorkAddress.setEnabled(isEnable);
        editUserWorkPhone.setEnabled(isEnable);

    }
}
