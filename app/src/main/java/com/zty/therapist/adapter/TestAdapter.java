package com.zty.therapist.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.zty.therapist.R;
import com.zty.therapist.model.TestModel;
import com.zty.therapist.recycler.ViewHolder;
import com.zty.therapist.utils.MyTextUtils;

import cn.droidlover.xrecyclerview.RecyclerAdapter;

/**
 * Created by zty on 2016/12/3.
 */

public class TestAdapter extends RecyclerAdapter<TestModel, ViewHolder> {

    private static final int TYPE_ITEM = 1;
    private static final int TYPE_TITLE = 2;

    public TestAdapter(Context context) {
        super(context);
    }

    @Override
    public int getItemViewType(int position) {
        if (data.get(position).getType() == 0) {
            return TYPE_TITLE;
        }
        return TYPE_ITEM;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            ViewHolder holder = ViewHolder.create(context, R.layout.item_theory, parent);
            return holder;
        } else {
            ViewHolder holder = ViewHolder.create(context, R.layout.item_test_title, parent);
            return holder;
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        convert(holder, data.get(position));
    }

    private void convert(ViewHolder holder, TestModel model) {
        if (model.getType() == 0) {
            holder.setText(R.id.textTrainTestTitle, MyTextUtils.isEmpty(model.getTitle()));
            holder.getConvertView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        } else {
            holder.setText(R.id.textTrainTitle, MyTextUtils.isEmpty(model.getTitle()));
        }

    }

}
