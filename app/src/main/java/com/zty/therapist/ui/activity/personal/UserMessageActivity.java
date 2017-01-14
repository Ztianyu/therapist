package com.zty.therapist.ui.activity.personal;

import android.content.Intent;
import android.view.View;

import com.google.gson.Gson;
import com.lling.photopicker.PhotoPickerActivity;
import com.lling.photopicker.utils.ImageLoader;
import com.loopj.android.http.RequestParams;
import com.zty.therapist.R;
import com.zty.therapist.base.BaseActivity;
import com.zty.therapist.base.TherapistApplication;
import com.zty.therapist.inter.OnSelectListener;
import com.zty.therapist.model.ImageModel;
import com.zty.therapist.model.ResultBean;
import com.zty.therapist.model.UserModel;
import com.zty.therapist.url.RequestManager;
import com.zty.therapist.url.Urls;
import com.zty.therapist.utils.LoadImageUtils;
import com.zty.therapist.utils.MyImageLoader;
import com.zty.therapist.utils.PicUtils;
import com.zty.therapist.utils.ResultUtil;
import com.zty.therapist.utils.SelectPicUtils;
import com.zty.therapist.utils.ToastUtils;
import com.zty.therapist.utils.UserUtils;
import com.zty.therapist.widget.CircleImageView;
import com.zty.therapist.widget.StripViewNoImg;

import java.io.File;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by zty on 2016/12/26.
 */

public class UserMessageActivity extends BaseActivity implements OnSelectListener {

    private static final int CODE_SUBMIT = 0;
    private static final int CODE_LOAD_PIC = 1;

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
    private String userId;

    private String imagePath;
    private String imageThumbPath;

    @Override
    protected int getContentView() {
        return R.layout.activity_user_data;
    }

    @Override
    protected void initData() {
        title.setText("个人资料");

        userModel = TherapistApplication.getInstance().getUserModel();
        userId = TherapistApplication.getInstance().getUserId();
        setText();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        initData();
    }

    @Override
    public void onFailureCallback(int requestCode, String errorMsg) {

    }

    @Override
    public void onSuccessCallback(int requestCode, String response) {
        ResultBean resultBean = ResultUtil.getResult(response);
        if (resultBean.isSuccess()) {
            switch (requestCode) {
                case CODE_SUBMIT:
                    UserUtils.getUserMessage(userId, this);
                    ToastUtils.show(this, "上传成功");
                    break;
                case CODE_LOAD_PIC:
                    ImageModel model = new Gson().fromJson(resultBean.getResult(), ImageModel.class);
                    if (model != null) {
                        imagePath = model.getAudioUrl();
                        imageThumbPath = model.getAudioThumbUrl();
                    }
                    LoadNickImage();
                    break;
                case UserUtils.CODE_GET_MESSAGE:
                    TherapistApplication.getInstance().setCurrentUser(resultBean.getResult());
                    break;
            }
        } else {
            ToastUtils.show(this, resultBean.getResult());
        }
    }

    private void LoadNickImage() {
        RequestParams params = new RequestParams();
        params.put("photo", imagePath);
        params.put("photoThumb", imageThumbPath);
        RequestManager.post(CODE_SUBMIT, Urls.updatePhoto, params, this);
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
        super.onClick(view);
        switch (view.getId()) {
            case R.id.imgUserHeader:
                SelectPicUtils.showDialog(getSupportFragmentManager());
                break;
            case R.id.textUserName:
                toSetText("修改姓名", userModel.getTeacherNm(), "teacherNm", 0);
                break;
            case R.id.textUserSex:
                startActivity(new Intent(this, SetSexActivity.class).putExtra("sex", userModel.getSex()));
                break;
            case R.id.textUserAge:
                toSetText("年龄", userModel.getAge() + "", "age", 1);
                break;
            case R.id.textUserPhone:
                toSetText("手机号", userModel.getMobile(), "mobile", 2);
                break;
            case R.id.textUserPlace:
                break;
            case R.id.textUserIdCard:
                toSetText("身份证号码", userModel.getIdCard(), "idCard", 0);
                break;
            case R.id.textUserSpecialty:
                toSetText("擅长", userModel.getExpert(), "expert", 0);
                break;
            case R.id.textUserSkill:
                toSetText("服务技能", userModel.getServiceSkill(), "serviceSkill", 0);
                break;
            case R.id.textUserAddress:
                toSetText("地址", userModel.getAddress(), "teacherNm", 0);
                break;
            case R.id.textUserWorkSpaceName:
                toSetText("工作单位", userModel.getWorkUnit(), "workUnit", 0);
                break;
            case R.id.textUserWorkSpaceAdd:
                toSetText("单位地址", userModel.getUnitAddress(), "unitAddress", 0);
                break;
            case R.id.textUserWorkSpacePhone:
                toSetText("单位电话", userModel.getUnitTel(), "unitTel", 2);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SelectPicUtils.PICK_PHOTO) {
            if (resultCode == RESULT_OK) {
                ArrayList<String> result = data.getStringArrayListExtra(PhotoPickerActivity.KEY_RESULT);
                showResult(result.get(0));
            }
        } else if (requestCode == SelectPicUtils.TAKE_PHOTO) {
            if (resultCode == RESULT_OK) {
                showResult(PicUtils.photoPath.getPath());
            }
        }
    }

    private void showResult(String result) {
        imagePath = result;
        ImageLoader.getInstance(this).display(result, imgUserHeader, 112, 112);

        File file = new File(result);
        LoadImageUtils.load(CODE_LOAD_PIC, LoadImageUtils.rehabilitationTeacher, file, this);
    }

    @Override
    public void onTakePhoto() {
        SelectPicUtils.hidePicFragment(getSupportFragmentManager());
        SelectPicUtils.takePhoto(this);
    }

    @Override
    public void onPickPic() {
        SelectPicUtils.hidePicFragment(getSupportFragmentManager());
        SelectPicUtils.pickPic(this, 1);
    }

    private void toSetText(String title, String message, String key, int type) {
        Intent intent = new Intent();
        intent.setClass(this, SetTextActivity.class);
        intent.putExtra("message", message);
        intent.putExtra("url", Urls.update);
        intent.putExtra("title", title);
        intent.putExtra("key", key);
        intent.putExtra("type", type);
        startActivity(intent);
    }
}
