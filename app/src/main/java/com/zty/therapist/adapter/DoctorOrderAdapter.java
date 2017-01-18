package com.zty.therapist.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zty.therapist.R;
import com.zty.therapist.model.DoctorOrderModel;
import com.zty.therapist.recycler.FooterRefreshAdapter;
import com.zty.therapist.recycler.ViewHolder;
import com.zty.therapist.ui.activity.personal.DoctorOrderDetailActivity;

/**
 * Created by zty on 2016/12/27.
 */

public class DoctorOrderAdapter extends FooterRefreshAdapter<DoctorOrderModel> {

    public DoctorOrderAdapter(Context context) {
        super(context);
    }


    @Override
    protected void convert(RecyclerView.ViewHolder holder, final DoctorOrderModel doctorOrderModel) {
        ViewHolder viewHolder = (ViewHolder) holder;

        viewHolder.setImage(mContext, R.id.imgDoctor, doctorOrderModel.getImgUrl());
        viewHolder.setText(R.id.textDoctorName, doctorOrderModel.getDoctorNm());
        viewHolder.setText(R.id.textDoctorPosition, doctorOrderModel.getItem());
        viewHolder.setText(R.id.textDoctorHospital, doctorOrderModel.getHospital());
        viewHolder.setText(R.id.textOrderData, doctorOrderModel.getCreateDate());
        viewHolder.setText(R.id.textIllName, doctorOrderModel.getPatientNm());
        viewHolder.setText(R.id.textDoctorIntegral, doctorOrderModel.getMemberHealthCurrency() + "");

        switch (doctorOrderModel.getState()) {
            case 0:
                viewHolder.setText(R.id.textDoctorIntegralState, "(待定)");
                viewHolder.setText(R.id.textDoctorOrderState, "待处理");
                break;
            case 1:
                viewHolder.setText(R.id.textDoctorIntegralState, "");
                viewHolder.setText(R.id.textDoctorOrderState, "待确认");
                break;
            case 2:
                viewHolder.setText(R.id.textDoctorIntegralState, ")");
                viewHolder.setText(R.id.textDoctorOrderState, "已完成");
                break;
            case 3:
                viewHolder.setText(R.id.textDoctorIntegralState, "");
                viewHolder.setText(R.id.textDoctorOrderState, "已取消");
                break;
        }


        viewHolder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mContext.startActivity(new Intent(mContext, DoctorOrderDetailActivity.class).putExtra("model", doctorOrderModel));
            }
        });

    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.item_doctor_order;
    }
}
