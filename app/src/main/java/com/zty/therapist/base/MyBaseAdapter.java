package com.zty.therapist.base;

import android.content.Context;
import android.view.View;
import android.widget.BaseAdapter;

import java.util.List;

public abstract class MyBaseAdapter<T, Q> extends BaseAdapter {


    public Context context;

    public View q;

    public List<T> mData;


    public MyBaseAdapter(Context context, List<T> mData) {
        super();
        this.context = context;
        this.mData = mData;
    }

    public MyBaseAdapter(Context context, View q, List<T> mData) {
        super();
        this.context = context;
        this.q = q;
        this.mData = mData;
    }

    public MyBaseAdapter() {
        super();
    }

    @Override
    public int getCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData == null ? null : mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


}
