package com.zty.therapist.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.zty.therapist.R;
import com.zty.therapist.model.HealthProductModel;
import com.zty.therapist.recycler.FooterRefreshAdapter;

/**
 * Created by zty on 2016/12/22.
 */

public class HealthProductsAdapter extends FooterRefreshAdapter<HealthProductModel> {

    public HealthProductsAdapter(Context context) {
        super(context);
    }

    @Override
    protected void convert(RecyclerView.ViewHolder holder, HealthProductModel healthProductModel) {

    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.item_product;
    }
}
