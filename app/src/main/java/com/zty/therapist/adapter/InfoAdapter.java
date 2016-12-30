package com.zty.therapist.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.zty.therapist.R;
import com.zty.therapist.model.InfoModel;
import com.zty.therapist.recycler.FooterRefreshAdapter;
import com.zty.therapist.recycler.ViewHolder;

/**
 * Created by zty on 2016/12/29.
 */

public class InfoAdapter extends FooterRefreshAdapter<InfoModel> {
    public InfoAdapter(Context context) {
        super(context);
    }

    @Override
    protected void convert(RecyclerView.ViewHolder holder, InfoModel infoModel) {

        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.setImage(mContext, R.id.imageViewInfo, "http://cdn.duitang.com/uploads/blog/201401/08/20140108163027_eQPQu.thumb.600_0.jpeg");

    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.item_info;
    }
}
