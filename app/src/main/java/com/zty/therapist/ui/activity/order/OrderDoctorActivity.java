package com.zty.therapist.ui.activity.order;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.ms.square.android.expandabletextview.ExpandableTextView;
import com.zty.therapist.R;
import com.zty.therapist.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 预约专家
 * Created by zty on 2016/12/23.
 */

public class OrderDoctorActivity extends BaseActivity {
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

    @Override
    protected int getContentView() {
        return R.layout.activity_order_doctor;
    }

    @Override
    protected void initData() {
        title.setText("专家预约");
        left.setBackgroundResource(R.mipmap.ic_back);
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        right.setVisibility(View.INVISIBLE);

        expandText.setText("\u3000\u3000" + "以下城市的医生为与我方合作的全过知名专家，通过我们平台预定的医生可提供如下服务：\n" +
                "1、由指定医生门诊服务\n" +
                "2、由指定医生主刀手术\n" +
                "3、手术费大概只有大医院的1/2\n" +
                "4、手术医院为与我方合作的医院\n" +
                "5、由您介绍的患者，术后可获得健康币\n" +
                "6、健康币可以兑换提现（比率1:1）");

    }

    @Override
    public void onFailureCallback(int requestCode, String errorMsg) {

    }

    @Override
    public void onSuccessCallback(int requestCode, String response) {

    }

    @OnClick(R.id.btnBookDoctor)
    public void onClick() {
    }
}
