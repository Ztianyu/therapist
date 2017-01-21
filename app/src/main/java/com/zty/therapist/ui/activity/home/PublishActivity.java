package com.zty.therapist.ui.activity.home;

import android.content.Intent;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.GridView;

import com.google.gson.Gson;
import com.lling.photopicker.PhotoPickerActivity;
import com.loopj.android.http.RequestParams;
import com.zty.therapist.R;
import com.zty.therapist.adapter.PublishImageAdapter;
import com.zty.therapist.base.BaseActivity;
import com.zty.therapist.inter.OnSelectListener;
import com.zty.therapist.model.ImageModel;
import com.zty.therapist.model.ResultBean;
import com.zty.therapist.url.RequestManager;
import com.zty.therapist.url.Urls;
import com.zty.therapist.utils.LoadImageUtils;
import com.zty.therapist.utils.PicUtils;
import com.zty.therapist.utils.ResourceUtil;
import com.zty.therapist.utils.ResultUtil;
import com.zty.therapist.utils.SelectPicUtils;
import com.zty.therapist.utils.ToastUtils;
import com.zty.therapist.utils.ValidateUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 发帖、发送活动留影
 * Created by zty on 2016/12/19.
 */

public class PublishActivity extends BaseActivity implements OnSelectListener {

    private static final int CODE_SEND_SUBMIT = 0;
    private static final int CODE_LOAD_PIC = 1;

    @BindView(R.id.editPublish)
    EditText editPublish;
    @BindView(R.id.gridPublish)
    GridView gridPublish;

    private int type;
    private String activityId;

    private String strTitle;
    private String strSend;
    private String strHint;

    private PublishImageAdapter adapter;

    private List<String> mResults;

    private List<String> picturePaths;
    private List<String> thumbPaths;

    private int picCount = 0;

    private int loadPosition = 0;

    @Override
    protected int getContentView() {
        return R.layout.activity_publish;
    }

    @Override
    protected void initData() {
        type = getIntent().getIntExtra("type", 0);

        if (type == 0) {
            strTitle = "发帖";
            strSend = "发表";
            strHint = "请输入文字……";
        } else {
            strTitle = "活动留影";
            strSend = "发送";
            strHint = "请输入留言……";
            activityId = getIntent().getStringExtra("activityId");
        }

        title.setText(strTitle);
        right.setText(strSend);
        editPublish.setHint(strHint);

        adapter = new PublishImageAdapter(this, getSupportFragmentManager());
        gridPublish.setAdapter(adapter);
        setData();
    }

    private void setData() {
        adapter.addListAtEnd(ResourceUtil.resToStr(this, R.string.add));
    }

    private void submitCard() {
        String content = editPublish.getText().toString();
        if (TextUtils.isEmpty(content)) {
            ToastUtils.show(this, "请输入文字");
        } else {
            if (type == 0) {
                sendCommunity(content);
            } else {
                sendInfoPhoto(content);
            }
        }
    }

    private void sendCommunity(String content) {
        RequestParams params = new RequestParams();
        params.put("content", content);
        params.put("pictures", ValidateUtil.changeListToArray(picturePaths));
        params.put("thumb", ValidateUtil.changeListToArray(thumbPaths));
        RequestManager.post(CODE_SEND_SUBMIT, Urls.submitForum, params, this);
    }

    private void sendInfoPhoto(String content) {
        RequestParams params = new RequestParams();
        params.put("activityId", activityId);
        params.put("remarks", content);
        params.put("pictures", ValidateUtil.changeListToArray(picturePaths));
        params.put("thumb", ValidateUtil.changeListToArray(thumbPaths));
        RequestManager.post(CODE_SEND_SUBMIT, Urls.submitCityPhoto, params, this);
    }

    private void loadPic(String filePath) {
        File file = new File(filePath);
        LoadImageUtils.load(CODE_LOAD_PIC, LoadImageUtils.healthForum, file, this);
    }

    @Override
    public void onFailureCallback(int requestCode, String errorMsg) {

    }

    @Override
    public void onSuccessCallback(int requestCode, String response) {
        ResultBean resultBean = ResultUtil.getResult(response);
        if (resultBean.isSuccess()) {
            switch (requestCode) {
                case CODE_SEND_SUBMIT:
                    ToastUtils.show(this, "发布成功");
                    finish();
                    break;
                case CODE_LOAD_PIC:
                    if (picturePaths == null)
                        picturePaths = new ArrayList<>();
                    if (thumbPaths == null)
                        thumbPaths = new ArrayList<>();
                    ImageModel model = new Gson().fromJson(resultBean.getResult(), ImageModel.class);

                    if (model != null) {
                        picturePaths.add(model.getAudioUrl());
                        thumbPaths.add(model.getAudioThumbUrl());
                    }

                    if (loadPosition == picCount - 1) {
                        submitCard();
                    } else {
                        loadPosition++;
                        loadPic(mResults.get(loadPosition));
                    }
                    break;
            }
        } else {
            ToastUtils.show(this, resultBean.getResult());
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

        if (picCount == 9) {
            ToastUtils.show(this, "最多只能选择9张照片");
        } else {
            SelectPicUtils.pickPic(this, PhotoPickerActivity.DEFAULT_NUM - picCount);
        }
    }

    @Override
    public void rightClick() {
        if (mResults != null && mResults.size() > 0) {
            loadPic(mResults.get(loadPosition));
        } else {
            submitCard();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SelectPicUtils.PICK_PHOTO) {
            if (resultCode == RESULT_OK) {
                ArrayList<String> result = data.getStringArrayListExtra(PhotoPickerActivity.KEY_RESULT);
                showResult(result);
            }
        } else if (requestCode == SelectPicUtils.TAKE_PHOTO) {
            if (resultCode == RESULT_OK) {
                ArrayList<String> result = new ArrayList<>();
                result.add(PicUtils.photoPath.getPath());
                showResult(result);
            }
        }
    }

    private void showResult(ArrayList<String> result) {
        if (mResults == null) {
            mResults = new ArrayList<>();
        }
        mResults.remove(ResourceUtil.resToStr(this, R.string.add));
        mResults.addAll(result);

        if (mResults.size() < 9) {
            mResults.add(ResourceUtil.resToStr(this, R.string.add));
            picCount = mResults.size() - 1;
        } else {
            picCount = mResults.size();
        }

        adapter.setData(mResults);
    }
}
