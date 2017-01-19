package com.zty.therapist.ui.activity.personal;

import android.view.View;
import android.widget.TextView;

import com.youth.banner.Banner;
import com.zty.therapist.R;
import com.zty.therapist.base.BaseActivity;
import com.zty.therapist.model.ProductOrderModel;
import com.zty.therapist.utils.BannerUtils;
import com.zty.therapist.utils.ViewAdaptionUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by zty on 2016/12/29.
 */

public class ProductOrderDetailActivity extends BaseActivity {

    @BindView(R.id.bannerProduct)
    Banner bannerProduct;
    @BindView(R.id.textProductDetailName)
    TextView textProductDetailName;
    @BindView(R.id.textProductDetailNote)
    TextView textProductDetailNote;
    @BindView(R.id.textProductDetailPrise)
    TextView textProductDetailPrise;
    @BindView(R.id.textProductDetailCount)
    TextView textProductDetailCount;
    @BindView(R.id.textDoctorProductState)
    TextView textDoctorProductState;
    @BindView(R.id.textTotalPrise)
    TextView textTotalPrise;
    @BindView(R.id.textUserAddress)
    TextView textUserAddress;
    @BindView(R.id.textOrderIntegral)
    TextView textOrderIntegral;
    @BindView(R.id.textAddAddress)
    TextView textAddAddress;

    private ProductOrderModel model;

    @Override
    protected int getContentView() {
        return R.layout.activity_product_order_detail;
    }

    @Override
    protected void initData() {
        title.setText("订单详情");
        textAddAddress.setVisibility(View.GONE);
        initBanner();
        setText();
    }

    @Override
    public void onFailureCallback(int requestCode, String errorMsg) {

    }

    @Override
    public void onSuccessCallback(int requestCode, String response) {

    }

    private void setText() {
        model = (ProductOrderModel) getIntent().getSerializableExtra("model");
        textProductDetailName.setText(model.getProductNm());
        textProductDetailNote.setText(model.getRemarks());
        textProductDetailPrise.setText(model.getPrice());
        textProductDetailCount.setText(model.getQuantity() + "");
        textTotalPrise.setText(model.getTotalPrice());
        textUserAddress.setText(model.getAddress());
        textOrderIntegral.setText(model.getHealthCurrency() + "");

        switch (model.getState()) {
            case 0:
                textDoctorProductState.setText("待发货");
                break;
            case 1:
                textDoctorProductState.setText("已发货");
                break;
            case 2:
                textDoctorProductState.setText("已完成");
                break;
        }
    }

    private void initBanner() {
        ViewAdaptionUtils.LinearLayoutAdaptation(bannerProduct, 400);
        List<Integer> images = new ArrayList<>();
        images.add(R.drawable.product1);
        images.add(R.drawable.product2);
        images.add(R.drawable.product3);
        images.add(R.drawable.product4);
        images.add(R.drawable.product5);
        images.add(R.drawable.product6);
        images.add(R.drawable.product7);
        images.add(R.drawable.product8);
        images.add(R.drawable.product9);
        BannerUtils.initBanner1(bannerProduct, images, 0, 0);
    }
}
