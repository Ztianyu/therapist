package com.zty.therapist.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zty.therapist.R;
import com.zty.therapist.model.DoctorModel;
import com.zty.therapist.recycler.FooterRefreshAdapter;
import com.zty.therapist.recycler.ViewHolder;
import com.zty.therapist.ui.activity.doctor.OrderDoctorActivity;

/**
 * Created by zty on 2016/12/23.
 */

public class DoctorAdapter extends FooterRefreshAdapter<DoctorModel> {


    public DoctorAdapter(Context context) {
        super(context);
    }

    @Override
    protected void convert(RecyclerView.ViewHolder holder, DoctorModel doctorModel) {
        ViewHolder viewHolder = (ViewHolder) holder;

        viewHolder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mContext.startActivity(new Intent(mContext, OrderDoctorActivity.class));
            }
        });

    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.item_doctor;
    }
}
