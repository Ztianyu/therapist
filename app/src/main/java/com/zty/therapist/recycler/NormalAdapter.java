package com.zty.therapist.recycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zty on 2017/1/5.
 */

public abstract class NormalAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    protected Context mContext;
    protected int mLayoutId;
    protected List<T> mData;

    public NormalAdapter(Context context) {
        mContext = context;
        mLayoutId = getItemLayoutId();
        mData = new ArrayList<>();
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder viewHolder = ViewHolder.create(mContext, mLayoutId, parent);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        convert(holder, mData.get(position), position);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    protected abstract void convert(RecyclerView.ViewHolder holder, T t, int position);

    protected abstract int getItemLayoutId();

    /**
     * 更新顶部加载最新
     *
     * @param data
     */
    public void notifyBottomRefresh(List<T> data) {
        int size = mData.size();
        mData.addAll(data);
        notifyItemInserted(size);
    }

    /**
     * 更新顶部加载最新(一条)
     *
     * @param data
     */
    public void notifyBottomRefresh(T data) {
        int size = mData.size();
        mData.add(data);
        notifyItemInserted(size);
    }

    /**
     * 刷新数据
     */
    public void notifyTopRefresh(List<T> data) {
        mData.clear();
        mData = data;
        notifyDataSetChanged();
    }
}
