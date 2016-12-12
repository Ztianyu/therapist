package com.zty.therapist.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.zty.therapist.R;
import com.zty.therapist.model.TheoryModel;
import com.zty.therapist.recycler.ViewHolder;

import cn.droidlover.xrecyclerview.RecyclerAdapter;

/**
 * Created by zty on 2016/12/3.
 */

public class TheoryAdapter extends RecyclerAdapter<TheoryModel, ViewHolder> {

    public TheoryAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder viewHolder = ViewHolder.create(context, R.layout.item_theory, parent);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }
}
