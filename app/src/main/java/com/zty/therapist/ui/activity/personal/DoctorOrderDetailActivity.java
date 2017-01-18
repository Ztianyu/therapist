package com.zty.therapist.ui.activity.personal;


import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ms.square.android.expandabletextview.ExpandableTextView;
import com.zty.therapist.R;
import com.zty.therapist.base.BaseActivity;
import com.zty.therapist.model.DoctorOrderModel;
import com.zty.therapist.utils.MyImageLoader;

import butterknife.BindView;

/**
 * Created by zty on 2016/12/29.
 */

public class DoctorOrderDetailActivity extends BaseActivity {

    @BindView(R.id.imgDoctorDetail)
    ImageView imgDoctorDetail;
    @BindView(R.id.textDoctorName)
    TextView textDoctorName;
    @BindView(R.id.textDoctorPosition)
    TextView textDoctorPosition;
    @BindView(R.id.textHospital)
    TextView textHospital;
    @BindView(R.id.textDoctorBeGood)
    TextView textDoctorBeGood;
    @BindView(R.id.expandText)
    ExpandableTextView expandText;
    @BindView(R.id.editPaintName)
    EditText editPaintName;
    @BindView(R.id.editPaintPhone)
    EditText editPaintPhone;
    @BindView(R.id.editPaintPro)
    EditText editPaintPro;
    @BindView(R.id.editPaintAddress)
    EditText editPaintAddress;
    @BindView(R.id.editPaintExpectData)
    EditText editPaintExpectData;
    @BindView(R.id.textOrderDate)
    TextView textOrderDate;
    @BindView(R.id.textDoctorOrderState)
    TextView textDoctorOrderState;
    @BindView(R.id.textOrderIntegral)
    TextView textOrderIntegral;

    private DoctorOrderModel model;

    @Override
    protected int getContentView() {
        return R.layout.activity_doctor_detail;
    }

    @Override
    protected void initData() {
        title.setText("订单详情");
        model = (DoctorOrderModel) getIntent().getSerializableExtra("model");
        setData();
    }

    private void setData() {

        MyImageLoader.load(this, model.getImgUrl(), imgDoctorDetail);

        textDoctorName.setText(model.getDoctorNm());
        textDoctorPosition.setText(model.getExpert());
        textHospital.setText(model.getHospital());
        textDoctorBeGood.setText(model.getDoctorNm());
        expandText.setText(model.getRemarks());
        editPaintName.setEnabled(false);
        editPaintPhone.setEnabled(false);
        editPaintPro.setEnabled(false);
        editPaintAddress.setEnabled(false);
        editPaintExpectData.setEnabled(false);
        editPaintName.setText(model.getPatientNm());
        editPaintPhone.setText(model.getMobile());
        editPaintPro.setText(model.getItem());
        editPaintAddress.setText(model.getAddress());
        editPaintExpectData.setText(model.getStartDate());
        textOrderDate.setText(model.getCreateDate());
        textOrderIntegral.setText(model.getMemberHealthCurrency() + "");

        switch (model.getState()) {
            case 0:
                textDoctorOrderState.setText("待处理");
                break;
            case 1:
                textDoctorOrderState.setText("待确认");
                break;
            case 2:
                textDoctorOrderState.setText("已完成");
                break;
            case 3:
                textDoctorOrderState.setText("已取消");
                break;
        }
    }

    @Override
    public void onFailureCallback(int requestCode, String errorMsg) {

    }

    @Override
    public void onSuccessCallback(int requestCode, String response) {

    }
}
