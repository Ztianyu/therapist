package com.zty.therapist.ui.activity.home;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lling.photopicker.PhotoPickerActivity;
import com.lling.photopicker.utils.ImageLoader;
import com.lling.photopicker.utils.OtherUtils;
import com.loopj.android.http.RequestParams;
import com.zty.therapist.R;
import com.zty.therapist.base.BaseActivity;
import com.zty.therapist.inter.OnSelectListener;
import com.zty.therapist.model.ImageModel;
import com.zty.therapist.model.ResultBean;
import com.zty.therapist.url.RequestManager;
import com.zty.therapist.url.Urls;
import com.zty.therapist.utils.LoadImageUtils;
import com.zty.therapist.utils.PicUtils;
import com.zty.therapist.utils.ResultUtil;
import com.zty.therapist.utils.SelectPicUtils;
import com.zty.therapist.utils.TimeWheelUtils;
import com.zty.therapist.utils.ToastUtils;

import java.io.File;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 发布活动
 * Created by zty on 2016/12/29.
 */

public class SendInfoActivity extends BaseActivity implements View.OnClickListener, OnSelectListener {

    private static final int CODE_SUBMIT = 0;
    private static final int CODE_LOAD_PIC = 1;

    @BindView(R.id.editSendInfoTitle)
    EditText editSendInfoTitle;
    @BindView(R.id.editSendInfoTime)
    EditText editSendInfoTime;
    @BindView(R.id.editSendInfoAddress)
    EditText editSendInfoAddress;
    @BindView(R.id.editSendInfoContent)
    EditText editSendInfoContent;
    @BindView(R.id.editSendInfoVenue)
    EditText editSendInfoVenue;
    @BindView(R.id.editSendInfoNote)
    EditText editSendInfoNote;
    @BindView(R.id.editSendInfoName)
    EditText editSendInfoName;
    @BindView(R.id.editSendInfoPhone)
    EditText editSendInfoPhone;
    @BindView(R.id.editSendInfoEndTime)
    EditText editSendInfoEndTime;
    @BindView(R.id.btnUpload)
    ImageButton btnUpload;
    @BindView(R.id.textUploadState)
    TextView textUploadState;
    @BindView(R.id.imgUpload)
    ImageView imgUpload;
    @BindView(R.id.btnSetSendBeginTime)
    Button btnSetSendBeginTime;
    @BindView(R.id.btnSetSendEndTime)
    Button btnSetSendEndTime;

    private String result;

    private String picPath;
    private String thumbPath;

    @Override
    protected int getContentView() {
        return R.layout.activity_send_info;
    }

    @Override
    protected void initData() {
        title.setText("同城活动");
        right.setText("发布");
    }

    private void send() {
        RequestParams params = new RequestParams();
        params.put("activityNm", editSendInfoTitle.getText().toString());
        params.put("startTime", editSendInfoTime.getText().toString());
        params.put("deadlineTime", editSendInfoEndTime.getText().toString());
        params.put("activeSite", editSendInfoAddress.getText().toString());
        params.put("activePlace", editSendInfoVenue.getText().toString());
        params.put("content", editSendInfoContent.getText().toString());
        params.put("mattersAttention", editSendInfoNote.getText().toString());
        params.put("contacts", editSendInfoName.getText().toString());
        params.put("contactsNumber", editSendInfoPhone.getText().toString());
        if (!TextUtils.isEmpty(picPath)) {
            params.put("pictures", picPath);
            params.put("thumb", thumbPath);
        }
        RequestManager.post(CODE_SUBMIT, Urls.submitCityActivity, params, this);
    }

    private void loadPic() {
        File file = new File(result);
        LoadImageUtils.load(CODE_LOAD_PIC, LoadImageUtils.rehabilitationTeacher, file, this);
        textUploadState.setText("照片上传中，请等待……");
    }

    @Override
    public void onFailureCallback(int requestCode, String errorMsg) {

    }

    @Override
    public void onSuccessCallback(int requestCode, String response) {
        ResultBean resultBean = ResultUtil.getResult(response);
        if (resultBean.isSuccess()) {
            switch (requestCode) {
                case CODE_LOAD_PIC:
                    ImageModel model = new Gson().fromJson(resultBean.getResult(), ImageModel.class);
                    if (model != null) {
                        picPath = model.getAudioUrl();
                        thumbPath = model.getAudioThumbUrl();
                    }
                    textUploadState.setText("上传成功");
                    send();
                    break;
                case CODE_SUBMIT:
                    ToastUtils.show(this, "发布成功");
                    finish();
                    break;
            }
        } else {
            ToastUtils.show(this, resultBean.getMsg());
        }
    }

    @OnClick({R.id.btnSetSendBeginTime, R.id.btnSetSendEndTime, R.id.btnUpload})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.titleLeft:
                finish();
                break;
            case R.id.btnUpload:
                SelectPicUtils.showDialog(getSupportFragmentManager());
                break;
            case R.id.titleRight:
                if (!TextUtils.isEmpty(result)) {
                    loadPic();
                } else {
                    send();
                }
                break;
            case R.id.btnSetSendBeginTime:
                TimeWheelUtils.show(this, editSendInfoTime);
                break;
            case R.id.btnSetSendEndTime:
                TimeWheelUtils.show(this, editSendInfoEndTime);
                break;
        }
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

    private void showResult(String path) {
        result = path;
        int mColumnWidth = screenWidth - 2 * OtherUtils.dip2px(this, 16) / 2;
        ImageLoader.getInstance(this).display(path, imgUpload, mColumnWidth, mColumnWidth);
    }
}
