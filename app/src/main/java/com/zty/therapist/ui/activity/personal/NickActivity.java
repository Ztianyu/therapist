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
import com.zty.therapist.utils.MyTextUtils;
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
 * Created by zty on 2017/1/9.
 */

public class NickActivity extends BaseActivity implements OnSelectListener {

    private static final int CODE_SUBMIT = 0;
    private static final int CODE_LOAD_PIC = 1;

    @BindView(R.id.imgUserHeaderNick)
    CircleImageView imgUserHeaderNick;
    @BindView(R.id.textUserNickName)
    StripViewNoImg textUserNickName;

    private UserModel userModel;

    private String userId;

    private String imagePath;
    private String imageThumbPath;

    @Override
    protected int getContentView() {
        return R.layout.activity_nick_message;
    }

    @Override
    protected void initData() {

        title.setText("贴吧信息");

        userModel = TherapistApplication.getInstance().getUserModel();

        userId = TherapistApplication.getInstance().getUserId();

        MyImageLoader.load(this, userModel.getPostedPhoto(), imgUserHeaderNick);
        textUserNickName.setAdditionText(MyTextUtils.isEmpty(userModel.getNickName()));
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        userModel = TherapistApplication.getInstance().getUserModel();

        MyImageLoader.load(this, userModel.getPostedPhoto(), imgUserHeaderNick);
        textUserNickName.setAdditionText(MyTextUtils.isEmpty(userModel.getNickName()));
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

    @OnClick({R.id.imgUserHeaderNick, R.id.textUserNickName})
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.imgUserHeaderNick:
                SelectPicUtils.showDialog(getSupportFragmentManager());
                break;
            case R.id.textUserNickName:
                toSetText();
                break;
        }
    }

    private void toSetText() {
        Intent intent = new Intent();
        intent.setClass(this, SetTextActivity.class);
        intent.putExtra("message", userModel.getNickName());
        intent.putExtra("url", Urls.setNickName);
        intent.putExtra("title", "修改昵称");
        intent.putExtra("key", "nickName");
        startActivity(intent);
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
        ImageLoader.getInstance(this).display(result, imgUserHeaderNick, 112, 112);

        File file = new File(result);
        LoadImageUtils.load(CODE_LOAD_PIC, LoadImageUtils.rehabilitationTeacher, file, this);
    }

    private void LoadNickImage() {
        RequestParams params = new RequestParams();
        params.put("postedPhoto", imagePath);
        params.put("postedPhotoThumb", imageThumbPath);
        RequestManager.post(CODE_SUBMIT, Urls.setNickName, params, this);
    }
}
