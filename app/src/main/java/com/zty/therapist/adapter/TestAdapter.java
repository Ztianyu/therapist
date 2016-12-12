package com.zty.therapist.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.zty.therapist.model.TestModel;
import com.zty.therapist.recycler.ViewHolder;

import cn.droidlover.xrecyclerview.RecyclerAdapter;

/**
 * Created by zty on 2016/12/3.
 */

public class TestAdapter extends RecyclerAdapter<TestModel,ViewHolder> {
    public TestAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }
}
