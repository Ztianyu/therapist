package com.zty.therapist.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zty.therapist.R;
import com.zty.therapist.model.InfoModel;
import com.zty.therapist.recycler.FooterRefreshAdapter;
import com.zty.therapist.recycler.ViewHolder;
import com.zty.therapist.ui.activity.home.InfoDetailActivity;

/**
 * Created by zty on 2016/12/29.
 */

public class InfoAdapter extends FooterRefreshAdapter<InfoModel> {
    public InfoAdapter(Context context) {
        super(context);
    }

    @Override
    protected void convert(RecyclerView.ViewHolder holder, final InfoModel infoModel) {

        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.setImage(mContext, R.id.imageViewInfo, infoModel.getPictures().get(0).getPicture());
        viewHolder.setText(R.id.textInfoTitle, infoModel.getActivityNm());
        viewHolder.setText(R.id.textInfoTime, infoModel.getStartTime());
        viewHolder.setText(R.id.textAddress, infoModel.getActiveSite());
        viewHolder.setText(R.id.textSignUpEndTime, infoModel.getDeadlineTime());

        viewHolder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mContext.startActivity(new Intent(mContext, InfoDetailActivity.class).putExtra("activityId", infoModel.getId()));
            }
        });
    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.item_info;
    }
}
