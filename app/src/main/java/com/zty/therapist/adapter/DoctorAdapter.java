package com.zty.therapist.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zty.therapist.R;
import com.zty.therapist.model.DoctorModel;
import com.zty.therapist.recycler.FooterRefreshAdapter;
import com.zty.therapist.recycler.ViewHolder;
import com.zty.therapist.ui.activity.order.OrderDoctorActivity;

/**
 * Created by zty on 2016/12/23.
 */

public class DoctorAdapter extends FooterRefreshAdapter<DoctorModel> {


    public DoctorAdapter(Context context) {
        super(context);
    }

    @Override
    protected void convert(RecyclerView.ViewHolder holder, final DoctorModel doctorModel) {
        ViewHolder viewHolder = (ViewHolder) holder;

        viewHolder.setImage(mContext, R.id.imgDoctor, doctorModel.getImgUrl());
        viewHolder.setText(R.id.textDoctorName, doctorModel.getDoctorNm());
        viewHolder.setText(R.id.textDoctorPosition, doctorModel.getTypeNm());
        viewHolder.setText(R.id.textDoctorBeGood, doctorModel.getExpert());
        viewHolder.setText(R.id.textHospital, doctorModel.getHospital());
        viewHolder.setText(R.id.textIntegral, doctorModel.getMemberHealthCurrency() + "健康币");

        viewHolder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mContext.startActivity(new Intent(mContext, OrderDoctorActivity.class).putExtra("doctorId", doctorModel.getId()));
            }
        });

    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.item_doctor;
    }
}
