package com.zty.therapist.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.zty.therapist.R;
import com.zty.therapist.model.IntegralRecordModel;
import com.zty.therapist.recycler.FooterRefreshAdapter;
import com.zty.therapist.recycler.ViewHolder;
import com.zty.therapist.utils.ResourceUtil;

/**
 * Created by zty on 2016/12/28.
 */

public class ProductIntegralAdapter extends FooterRefreshAdapter<IntegralRecordModel> {

    public ProductIntegralAdapter(Context context) {
        super(context);
    }

    @Override
    protected void convert(RecyclerView.ViewHolder holder, IntegralRecordModel integralRecordModel) {
        ViewHolder viewHolder = (ViewHolder) holder;

        if (integralRecordModel.isTop()) {
            viewHolder.setText(R.id.textAccountItem1, "名称");
            viewHolder.setText(R.id.textAccountItem2, "价格(元)");
            viewHolder.setText(R.id.textAccountItem3, "数量");
            viewHolder.setText(R.id.textAccountItem4, "健康币");
            viewHolder.setText(R.id.textAccountItem5, "时间");

            ((TextView) viewHolder.getView(R.id.textAccountItem1)).setTextColor(ResourceUtil.resToColor(mContext, R.color.colorContent1));
            ((TextView) viewHolder.getView(R.id.textAccountItem2)).setTextColor(ResourceUtil.resToColor(mContext, R.color.colorContent1));
            ((TextView) viewHolder.getView(R.id.textAccountItem3)).setTextColor(ResourceUtil.resToColor(mContext, R.color.colorContent1));
            ((TextView) viewHolder.getView(R.id.textAccountItem4)).setTextColor(ResourceUtil.resToColor(mContext, R.color.colorContent1));
            ((TextView) viewHolder.getView(R.id.textAccountItem5)).setTextColor(ResourceUtil.resToColor(mContext, R.color.colorContent1));

        } else {
            viewHolder.setText(R.id.textAccountItem1, "澳洲奶粉");
            viewHolder.setText(R.id.textAccountItem2, "350");
            viewHolder.setText(R.id.textAccountItem3, "2");
            viewHolder.setText(R.id.textAccountItem4, "100");
            viewHolder.setText(R.id.textAccountItem5, "2017-03-05");
        }

    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.item_account;
    }
}
