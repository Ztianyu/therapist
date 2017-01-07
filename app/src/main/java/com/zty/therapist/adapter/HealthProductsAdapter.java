package com.zty.therapist.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zty.therapist.R;
import com.zty.therapist.model.HealthProductModel;
import com.zty.therapist.recycler.FooterRefreshAdapter;
import com.zty.therapist.recycler.ViewHolder;
import com.zty.therapist.ui.activity.order.BuyProductActivity;

/**
 * Created by zty on 2016/12/22.
 */

public class HealthProductsAdapter extends FooterRefreshAdapter<HealthProductModel> {

    public HealthProductsAdapter(Context context) {
        super(context);
    }

    @Override
    protected void convert(RecyclerView.ViewHolder holder, final HealthProductModel healthProductModel) {

        ViewHolder viewHolder = (ViewHolder) holder;

        viewHolder.setImage(mContext, R.id.imgProduct, healthProductModel.getImgUrl());
        viewHolder.setText(R.id.textProductName, healthProductModel.getProductNm());
        viewHolder.setText(R.id.textProductNote, healthProductModel.getRemarks());
        viewHolder.setText(R.id.textProductPrise, "价格：" + healthProductModel.getPrice() + "元");
        viewHolder.setText(R.id.textProductIntegral, "健康币：" + healthProductModel.getHealthCurrency());

        viewHolder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mContext.startActivity(new Intent(mContext, BuyProductActivity.class).putExtra("model", healthProductModel));
            }
        });

    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.item_product;
    }
}
