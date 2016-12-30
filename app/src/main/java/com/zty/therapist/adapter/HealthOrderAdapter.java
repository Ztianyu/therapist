package com.zty.therapist.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zty.therapist.R;
import com.zty.therapist.model.ProductOrderModel;
import com.zty.therapist.recycler.FooterRefreshAdapter;
import com.zty.therapist.recycler.ViewHolder;
import com.zty.therapist.ui.activity.personal.ProductOrderDetailActivity;

/**
 * Created by zty on 2016/12/27.
 */

public class HealthOrderAdapter extends FooterRefreshAdapter<ProductOrderModel> {

    public HealthOrderAdapter(Context context) {
        super(context);
    }


    @Override
    protected void convert(RecyclerView.ViewHolder holder, final ProductOrderModel productOrderModel) {
        ViewHolder viewHolder = (ViewHolder)holder;
        viewHolder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mContext.startActivity(new Intent(mContext, ProductOrderDetailActivity.class).putExtra("model",productOrderModel));
            }
        });

    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.item_product_order;
    }
}
