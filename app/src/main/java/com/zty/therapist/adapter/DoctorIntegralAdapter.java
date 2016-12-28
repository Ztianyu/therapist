package com.zty.therapist.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.zty.therapist.R;
import com.zty.therapist.model.DoctorRecordModel;
import com.zty.therapist.recycler.FooterRefreshAdapter;
import com.zty.therapist.recycler.ViewHolder;
import com.zty.therapist.utils.ResourceUtil;

/**
 * Created by zty on 2016/12/28.
 */

public class DoctorIntegralAdapter extends FooterRefreshAdapter<DoctorRecordModel> {

    public DoctorIntegralAdapter(Context context) {
        super(context);
    }

    @Override
    protected void convert(RecyclerView.ViewHolder holder, DoctorRecordModel doctorRecordModel) {
        ViewHolder viewHolder = (ViewHolder) holder;

        if (doctorRecordModel.isTop()) {
            viewHolder.setText(R.id.textAccountItem1, "医生");
            viewHolder.setText(R.id.textAccountItem2, "患者");
            viewHolder.setText(R.id.textAccountItem3, "医疗项目");
            viewHolder.setText(R.id.textAccountItem4, "健康币");
            viewHolder.setText(R.id.textAccountItem5, "时间");

            ((TextView) viewHolder.getView(R.id.textAccountItem1)).setTextColor(ResourceUtil.resToColor(mContext, R.color.colorContent1));
            ((TextView) viewHolder.getView(R.id.textAccountItem2)).setTextColor(ResourceUtil.resToColor(mContext, R.color.colorContent1));
            ((TextView) viewHolder.getView(R.id.textAccountItem3)).setTextColor(ResourceUtil.resToColor(mContext, R.color.colorContent1));
            ((TextView) viewHolder.getView(R.id.textAccountItem4)).setTextColor(ResourceUtil.resToColor(mContext, R.color.colorContent1));
            ((TextView) viewHolder.getView(R.id.textAccountItem5)).setTextColor(ResourceUtil.resToColor(mContext, R.color.colorContent1));

        } else {
            viewHolder.setText(R.id.textAccountItem1, "王强");
            viewHolder.setText(R.id.textAccountItem2, "张志");
            viewHolder.setText(R.id.textAccountItem3, "脑癌手术");
            viewHolder.setText(R.id.textAccountItem4, "2000");
            viewHolder.setText(R.id.textAccountItem5, "2017-03-05");
        }

    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.item_account;
    }
}
