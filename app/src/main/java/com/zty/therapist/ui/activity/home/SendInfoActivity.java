package com.zty.therapist.ui.activity.home;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.lling.photopicker.PhotoPickerActivity;
import com.lling.photopicker.utils.ImageLoader;
import com.lling.photopicker.utils.OtherUtils;
import com.zty.therapist.R;
import com.zty.therapist.base.BaseActivity;
import com.zty.therapist.inter.OnSelectListener;
import com.zty.therapist.utils.PicUtils;
import com.zty.therapist.utils.SelectPicUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 发布活动
 * Created by zty on 2016/12/29.
 */

public class SendInfoActivity extends BaseActivity implements View.OnClickListener, OnSelectListener {
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

    @Override
    protected int getContentView() {
        return R.layout.activity_send_info;
    }

    @Override
    protected void initData() {
        title.setText("同城活动");
        right.setText("发布");

    }

    @Override
    public void onFailureCallback(int requestCode, String errorMsg) {

    }

    @Override
    public void onSuccessCallback(int requestCode, String response) {

    }

    @OnClick(R.id.btnUpload)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnUpload:
                SelectPicUtils.showDialog(getSupportFragmentManager());
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
    public void rightClick() {
        super.rightClick();
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
        int mColumnWidth = screenWidth - 2 * OtherUtils.dip2px(this, 16) / 2;
        ImageLoader.getInstance(this).display(path, imgUpload, mColumnWidth, mColumnWidth);
    }
}
