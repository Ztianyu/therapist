package com.zty.therapist.ui.activity.order;

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
import com.zty.therapist.url.RequestManager;
import com.zty.therapist.url.Urls;
import com.zty.therapist.utils.MyImageLoader;
import com.zty.therapist.utils.ResultUtil;

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
                    break;
            }
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

    @OnClick(R.id.btnBookDoctor)
    public void onClick() {
    }
}
