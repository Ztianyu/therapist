package com.zty.therapist.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zty.therapist.R;
import com.zty.therapist.model.ReplaceRecordModel;
import com.zty.therapist.recycler.FooterRefreshAdapter;
import com.zty.therapist.recycler.ViewHolder;

/**
 * Created by tianyu on 2017/1/2.
 */

public class ReplaceRecordAdapter extends FooterRefreshAdapter<ReplaceRecordModel> {
    public ReplaceRecordAdapter(Context context) {
        super(context);
    }

    @Override
    protected void convert(RecyclerView.ViewHolder holder, ReplaceRecordModel replaceRecordModel) {
        ViewHolder viewHolder = (ViewHolder) holder;

        viewHolder.getView(R.id.btnAgree).setVisibility(View.INVISIBLE);
        viewHolder.getView(R.id.btnNoAgree).setVisibility(View.INVISIBLE);

    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.item_replace;
    }
}
