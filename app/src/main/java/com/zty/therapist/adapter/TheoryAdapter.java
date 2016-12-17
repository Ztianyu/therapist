package com.zty.therapist.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.zty.therapist.R;
import com.zty.therapist.model.TheoryModel;
import com.zty.therapist.recycler.ViewHolder;
import com.zty.therapist.utils.MyTextUtils;

import cn.droidlover.xrecyclerview.RecyclerAdapter;

/**
 * 培训  理论adapter
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
        convert(holder, data.get(position));
    }

    private void convert(ViewHolder holder, TheoryModel model) {
        if (model.getType() == 0) {
            holder.setBgRes(R.id.imgTrainLeft, R.mipmap.ic_train_radio);
        } else if (model.getType() == 1) {
            holder.setBgRes(R.id.imgTrainLeft, R.mipmap.ic_train_text);
        }

        holder.setText(R.id.textTrainTitle, MyTextUtils.isEmpty(model.getTitle()));

        holder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
