package com.zty.therapist.recycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
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

    private OnItemClickListener<T> mOnItemClickListener;

    public NormalAdapter(Context context) {
        mContext = context;
        mLayoutId = getItemLayoutId();
        mData = new ArrayList<>();
    }

    public void setOnItemClickListener(OnItemClickListener<T> onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder viewHolder = ViewHolder.create(mContext, mLayoutId, parent);
        setCommonListener(viewHolder);
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

    protected void setCommonListener(final ViewHolder viewHolder) {

        viewHolder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    int position = getPosition(viewHolder);
                    mOnItemClickListener.onCommonItemClick(viewHolder, mData.get(position), position);
                }
            }
        });
    }

    private int getPosition(RecyclerView.ViewHolder viewHolder) {
        return viewHolder.getAdapterPosition();
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
     * 移除指定位置的数据
     */
    public void remove(int position) {
        mData.remove(position);
        notifyItemRemoved(position);
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
