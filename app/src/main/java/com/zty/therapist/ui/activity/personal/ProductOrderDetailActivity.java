package com.zty.therapist.ui.activity.personal;

import android.view.View;
import android.widget.TextView;

import com.youth.banner.Banner;
import com.zty.therapist.R;
import com.zty.therapist.base.BaseActivity;
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

    @Override
    protected int getContentView() {
        return R.layout.activity_product_order_detail;
    }

    @Override
    protected void initData() {

        title.setText("订单详情");
        left.setBackgroundResource(R.mipmap.ic_back);
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        right.setVisibility(View.INVISIBLE);

        textAddAddress.setVisibility(View.GONE);

        initBanner();

    }

    @Override
    public void onFailureCallback(int requestCode, String errorMsg) {

    }

    @Override
    public void onSuccessCallback(int requestCode, String response) {

    }

    private void initBanner() {

        ViewAdaptionUtils.LinearLayoutAdaptation(bannerProduct, 300);

        List<String> images = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            images.add("http://img03.tooopen.com/images/20131102/sy_45238929299.jpg");
        }
        BannerUtils.initBanner(bannerProduct, images, 0, 0);
    }
}
