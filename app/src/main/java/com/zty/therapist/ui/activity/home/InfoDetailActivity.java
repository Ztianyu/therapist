package com.zty.therapist.ui.activity.home;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.RequestParams;
import com.zty.therapist.R;
import com.zty.therapist.adapter.InfoPhotoAdapter;
import com.zty.therapist.base.BaseActivity;
import com.zty.therapist.base.TherapistApplication;
import com.zty.therapist.model.EnrollModel;
import com.zty.therapist.model.InfoModel;
import com.zty.therapist.model.InfoPhotoModel;
import com.zty.therapist.model.ResultBean;
import com.zty.therapist.url.RequestManager;
import com.zty.therapist.url.Urls;
import com.zty.therapist.utils.MyImageLoader;
import com.zty.therapist.utils.ResultUtil;
import com.zty.therapist.utils.TimeUtils;
import com.zty.therapist.utils.ToastUtils;
import com.zty.therapist.widget.MyListView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by zty on 2017/1/10.
 */

public class InfoDetailActivity extends BaseActivity {

    private static final int CODE_GET_ENROLL_LIST = 0;
    private static final int CODE_ENROLL = 1;
    private static final int CODE_DETAIL = 2;
    private static final int CODE_CANCEL_ENROLL = 3;
    private static final int CODE_GET_PHOTOS = 4;

    @BindView(R.id.editSendInfoTitle)
    TextView editSendInfoTitle;
    @BindView(R.id.imgInfoDetailLogo)
    ImageView imgInfoDetailLogo;
    @BindView(R.id.editSendInfoTime)
    TextView editSendInfoTime;
    @BindView(R.id.editSendInfoAddress)
    TextView editSendInfoAddress;
    @BindView(R.id.editSendInfoContent)
    TextView editSendInfoContent;
    @BindView(R.id.editSendInfoNote)
    TextView editSendInfoNote;
    @BindView(R.id.editSendInfoVenue)
    TextView editSendInfoVenue;
    @BindView(R.id.editSendInfoName)
    TextView editSendInfoName;
    @BindView(R.id.editSendInfoPhone)
    TextView editSendInfoPhone;
    @BindView(R.id.editSendInfoEndTime)
    TextView editSendInfoEndTime;
    @BindView(R.id.editSendInfoCount)
    TextView editSendInfoCount;
    @BindView(R.id.editSendInfoNames)
    TextView editSendInfoNames;
    @BindView(R.id.btnSubmitInfoPhoto)
    Button btnSubmitInfoPhoto;
    @BindView(R.id.recyclerViewInfoPhotos)
    MyListView recyclerViewInfoPhotos;

    private InfoPhotoAdapter adapter;

    private String activityId;
    private String userId;
    private boolean isCanEnroll = false;
    private boolean isEnroll = false;

    @Override
    protected int getContentView() {
        return R.layout.activity_info_detail;
    }

    @Override
    protected void initData() {
        userId = TherapistApplication.getInstance().getUserId();
        activityId = getIntent().getStringExtra("activityId");
        title.setText("活动详情");

        adapter = new InfoPhotoAdapter(this);
        recyclerViewInfoPhotos.setAdapter(adapter);

        getCityActivity();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        getCityPhotoList();
    }

    private void getCityActivity() {
        RequestParams params = new RequestParams();
        params.put("activityId", activityId);
        RequestManager.get(CODE_DETAIL, Urls.getCityActivity, params, this);
    }

    private void getCityPhotoList() {
        RequestParams params = new RequestParams();
        params.put("activityId", activityId);
        RequestManager.get(CODE_GET_PHOTOS, Urls.getCityPhotoList, params, this);
    }

    private void getCityEnrollList() {
        RequestParams params = new RequestParams();
        params.put("activityId", activityId);
        RequestManager.get(CODE_GET_ENROLL_LIST, Urls.getCityEnrollList, params, this);
    }

    private void submitCityEnroll() {
        RequestParams params = new RequestParams();
        params.put("activityId", activityId);
        RequestManager.post(CODE_ENROLL, Urls.submitCityEnroll, params, this);
    }

    private void deleteCityEnroll() {
        RequestParams params = new RequestParams();
        params.put("activityId", activityId);
        RequestManager.post(CODE_CANCEL_ENROLL, Urls.deleteCityEnroll, params, this);
    }

    @Override
    public void onFailureCallback(int requestCode, String errorMsg) {

    }

    @Override
    public void onSuccessCallback(int requestCode, String response) {
        ResultBean resultBean = ResultUtil.getResult(response);
        if (resultBean.isSuccess()) {
            switch (requestCode) {
                case CODE_GET_ENROLL_LIST:
                    List<EnrollModel> enrollModels = new Gson().fromJson(resultBean.getResult(), new TypeToken<List<EnrollModel>>() {
                    }.getType());
                    judgeIsEnroll(enrollModels);
                    break;
                case CODE_ENROLL:
                    ToastUtils.show(this, "报名成功");
                    getCityEnrollList();
                    getCityActivity();
                    break;
                case CODE_DETAIL:
                    InfoModel infoModel = new Gson().fromJson(resultBean.getResult(), InfoModel.class);
                    setMessage(infoModel);
                    break;
                case CODE_CANCEL_ENROLL:
                    ToastUtils.show(this, "取消成功");
                    getCityEnrollList();
                    getCityActivity();
                    break;
                case CODE_GET_PHOTOS:
                    List<InfoPhotoModel> infoPhotoModels = new Gson().fromJson(resultBean.getResult(), new TypeToken<List<InfoPhotoModel>>() {
                    }.getType());

                    if (infoPhotoModels != null) {
                        adapter.setData(infoPhotoModels);
                    }

                    break;
            }
        } else {
            ToastUtils.show(this, resultBean.getMsg());
        }
    }

    private void setMessage(InfoModel infoModel) {
        if (infoModel != null) {
            editSendInfoTitle.setText(infoModel.getActivityNm());
            MyImageLoader.load(this, infoModel.getPictures().get(0).getPicture(), imgInfoDetailLogo);
            editSendInfoTime.setText(infoModel.getStartTime());
            editSendInfoAddress.setText(infoModel.getActiveSite());
            editSendInfoContent.setText(infoModel.getContent());
            editSendInfoNote.setText(infoModel.getMattersAttention());
            editSendInfoVenue.setText(infoModel.getActivePlace());
            editSendInfoName.setText(infoModel.getContacts());
            editSendInfoPhone.setText(infoModel.getContactsNumber());
            editSendInfoEndTime.setText(infoModel.getDeadlineTime());
            editSendInfoCount.setText(infoModel.getIsNumber() + "人");
            editSendInfoNames.setText(infoModel.getTeacherNms());
        }
        judgeIsCanEnroll(infoModel);
        getCityPhotoList();
    }

    private void judgeIsCanEnroll(InfoModel infoModel) {
        isCanEnroll = false;

        String deadlineTime = infoModel.getDeadlineTime();
        String nowData = TimeUtils.getDateAndMinute();
        int compareValue = TimeUtils.compareDate(deadlineTime, nowData, "yyyy-MM-dd HH:mm");
        if (compareValue >= 0) {
            isCanEnroll = true;
        } else {
            isCanEnroll = false;
        }

        if (isCanEnroll)
            getCityEnrollList();
    }

    private void judgeIsEnroll(List<EnrollModel> enrollModels) {
        isEnroll = false;
        if (enrollModels != null && enrollModels.size() > 0) {
            for (EnrollModel enrollModel : enrollModels) {
                if (enrollModel.getUserId().equals(userId))
                    isEnroll = true;
            }
        }
        if (isEnroll) {
            right.setText("取消报名");
            btnSubmitInfoPhoto.setVisibility(View.VISIBLE);
        } else {
            right.setText("报名");
            btnSubmitInfoPhoto.setVisibility(View.INVISIBLE);
        }
    }

    @OnClick(R.id.btnSubmitInfoPhoto)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.titleLeft:
                finish();
                break;
            case R.id.titleRight:
                if (isEnroll) {
                    deleteCityEnroll();
                } else {
                    submitCityEnroll();
                }
                break;
            case R.id.btnSubmitInfoPhoto:
                startActivity(new Intent(this, PublishActivity.class)
                        .putExtra("activityId", activityId)
                        .putExtra("type", 1));
                break;
        }
    }
}
