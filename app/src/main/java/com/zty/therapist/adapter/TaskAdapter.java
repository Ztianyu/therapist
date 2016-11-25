package com.zty.therapist.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.zty.therapist.R;
import com.zty.therapist.model.TaskModel;
import com.zty.therapist.recycler.FooterRefreshAdapter;
import com.zty.therapist.recycler.ViewHolder;

import java.util.List;

/**
 * Created by zty on 2016/11/25.
 */

public class TaskAdapter extends FooterRefreshAdapter<TaskModel> {

    public TaskAdapter(Context context, List<TaskModel> data) {
        super(context, data);
    }

    @Override
    protected void convert(RecyclerView.ViewHolder holder, TaskModel taskModel) {

        ViewHolder viewHolder = (ViewHolder) holder;

    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.item_task;
    }
}
