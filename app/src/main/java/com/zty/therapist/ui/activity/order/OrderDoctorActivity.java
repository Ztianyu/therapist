package com.zty.therapist.ui.activity.order;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.ms.square.android.expandabletextview.ExpandableTextView;
import com.zty.therapist.R;
import com.zty.therapist.base.BaseActivity;
import com.zty.therapist.model.DoctorModel;
import com.zty.therapist.model.ResultBean;
import com.zty.therapist.ui.activity.personal.MyOrderActivity;
import com.zty.therapist.url.RequestManager;
import com.zty.therapist.url.Urls;
import com.zty.therapist.utils.MyImageLoader;
import com.zty.therapist.utils.ResultUtil;
import com.zty.therapist.utils.TimeUtils;
import com.zty.therapist.utils.TimeWheelUtils;
import com.zty.therapist.utils.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 预约专家
 * Created by zty on 2016/12/23.
 */

public class OrderDoctorActivity extends BaseActivity {

    private static final int CODE_GET_DOCTOR_DETAIL = 0;
    private static final int CODE_SUBMIT = 1;

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
    @BindView(R.id.expandable_text)
    TextView expandableText;
    @BindView(R.id.expand_collapse)
    ImageButton expandCollapse;
    @BindView(R.id.expandText)
    ExpandableTextView expandText;
    @BindView(R.id.editPaintName)
    EditText editPaintName;
    @BindView(R.id.editPaintPhone)
    EditText editPaintPhone;
    @BindView(R.id.editPaintAddress)
    EditText editPaintAddress;
    @BindView(R.id.editPaintExpectData)
    EditText editPaintExpectData;
    @BindView(R.id.textOrderIntegral)
    TextView textOrderIntegral;
    @BindView(R.id.btnBookDoctor)
    Button btnBookDoctor;
    @BindView(R.id.btnChoseDate)
    Button btnChoseDate;
    @BindView(R.id.editPaintPro)
    EditText editPaintPro;

    private String doctorId;

    @Override
    protected int getContentView() {
        return R.layout.activity_order_doctor;
    }

    @Override
    protected void initData() {
        title.setText("专家预约");

        doctorId = getIntent().getStringExtra("doctorId");

        fetchData();
    }

    private void fetchData() {
        RequestParams params = new RequestParams();
        params.put("doctorId", doctorId);
        RequestManager.get(CODE_GET_DOCTOR_DETAIL, Urls.getDoctorDetail, params, this);
    }

    @Override
    public void onFailureCallback(int requestCode, String errorMsg) {

    }

    @Override
    public void onSuccessCallback(int requestCode, String response) {
        ResultBean resultBean = ResultUtil.getResult(response);
        if (resultBean.isSuccess()) {
            switch (requestCode) {
                case CODE_GET_DOCTOR_DETAIL:
                    try {
                        JSONObject object = new JSONObject(resultBean.getResult());
                        if (object.has("doctor")) {
                            DoctorModel model = new Gson().fromJson(object.getString("doctor"), DoctorModel.class);
                            if (model != null)
                                setData(model);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
                case CODE_SUBMIT:
                    ToastUtils.show(this, "预订成功");
                    MyOrderActivity.currentPage = 2;
                    startActivity(new Intent(this, MyOrderActivity.class));
                    finish();
                    break;
            }
        } else {
            ToastUtils.show(this, resultBean.getMsg());
        }
    }

    private void setData(DoctorModel model) {
        MyImageLoader.load(this, model.getImgUrl(), imgDoctorDetail);
        textDoctorName.setText(model.getDoctorNm());
        textDoctorPosition.setText(model.getTypeNm());
        textHospital.setText(model.getHospital());
        textDoctorBeGood.setText(model.getExpert());
        expandText.setText(model.getRemarks());
        textOrderIntegral.setText(model.getMemberHealthCurrency() + "");
    }


    private void submitDoctorOrder() {
        RequestParams params = new RequestParams();
        params.put("doctorId", doctorId);
        params.put("patientNm", editPaintName.getText().toString());
        params.put("contactsTel", editPaintPhone.getText().toString());
        params.put("address", editPaintAddress.getText().toString());
        params.put("startDate", editPaintExpectData.getText().toString());
        params.put("endDate", TimeUtils.getPeriodDate(editPaintExpectData.getText().toString(), 0, -10));
        params.put("item", editPaintPro.getText().toString());
        RequestManager.post(CODE_SUBMIT, Urls.submitDoctorOrder, params, this);
    }

    @OnClick({R.id.btnChoseDate, R.id.btnBookDoctor})
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.btnChoseDate:
                TimeWheelUtils.showDataSelect(this, editPaintExpectData);
                break;
            case R.id.btnBookDoctor:
                if (checkData())
                    submitDoctorOrder();
                break;
        }
    }

    private boolean checkData() {
        if (TextUtils.isEmpty(editPaintName.getText().toString())) {
            ToastUtils.show(this, "请输入患者姓名");
            return false;
        }
        if (TextUtils.isEmpty(editPaintPhone.getText().toString())) {
            ToastUtils.show(this, "请输入患者联系电话");
            return false;
        }
        if (TextUtils.isEmpty(editPaintAddress.getText().toString())) {
            ToastUtils.show(this, "请输入患者地址");
            return false;
        }
        if (TextUtils.isEmpty(editPaintPro.getText().toString())) {
            ToastUtils.show(this, "请输入医疗项目");
            return false;
        }
        if (TextUtils.isEmpty(editPaintName.getText().toString())) {
            ToastUtils.show(this, "请输入患者姓名");
            return false;
        }
        if (TextUtils.isEmpty(editPaintExpectData.getText().toString())) {
            ToastUtils.show(this, "请选择期望时间");
            return false;
        }
        return true;
    }
}
