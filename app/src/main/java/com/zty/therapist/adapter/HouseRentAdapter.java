package com.zty.therapist.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.zty.therapist.R;
import com.zty.therapist.model.HouseRentModel;
import com.zty.therapist.recycler.FooterRefreshAdapter;
import com.zty.therapist.recycler.ViewHolder;

/**
 * Created by tianyu on 2017/1/2.
 */

public class HouseRentAdapter extends FooterRefreshAdapter<HouseRentModel> {
    public HouseRentAdapter(Context context) {
        super(context);
    }

    @Override
    protected void convert(RecyclerView.ViewHolder holder, HouseRentModel houseRentModel) {
        ViewHolder viewHolder = (ViewHolder) holder;

    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.item_house_rent;
    }
}
