package com.zty.therapist.ui.activity.order;

import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.youth.banner.Banner;
import com.zty.therapist.R;
import com.zty.therapist.base.BaseActivity;
import com.zty.therapist.inter.OnAddAndSubtract;
import com.zty.therapist.utils.BannerUtils;
import com.zty.therapist.utils.ViewAdaptionUtils;
import com.zty.therapist.widget.AddAndSubtractView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by zty on 2016/12/26.
 */

public class BuyProductActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener, OnAddAndSubtract {
    @BindView(R.id.bannerProduct)
    Banner bannerProduct;
    @BindView(R.id.textProductDetailName)
    TextView textProductDetailName;
    @BindView(R.id.textProductDetailNote)
    TextView textProductDetailNote;
    @BindView(R.id.textProductDetailPrise)
    TextView textProductDetailPrise;
    @BindView(R.id.textProductDetailIntegral)
    TextView textProductDetailIntegral;
    @BindView(R.id.countView)
    AddAndSubtractView countView;
    @BindView(R.id.textTotalPrise)
    TextView textTotalPrise;
    @BindView(R.id.radioGroupPay)
    RadioGroup radioGroupPay;
    @BindView(R.id.btnPayProduct)
    Button btnPayProduct;

    private int count = 1;

    @Override
    protected int getContentView() {
        return R.layout.activity_buy_product;
    }

    @Override
    protected void initData() {

        title.setText("购 买");
        left.setBackgroundResource(R.mipmap.ic_back);
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        right.setVisibility(View.INVISIBLE);

        radioGroupPay.setOnCheckedChangeListener(this);
        countView.setListener(this);

        initBanner();
    }

    @Override
    public void onFailureCallback(int requestCode, String errorMsg) {

    }

    @Override
    public void onSuccessCallback(int requestCode, String response) {

    }

    @OnClick(R.id.btnPayProduct)
    public void onClick() {
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (i) {
            case R.id.radioButtonPay1:
                break;
            case R.id.radioButtonPay2:
                break;
            case R.id.radioButtonPay3:
                break;
        }

    }

    private void initBanner() {

        ViewAdaptionUtils.LinearLayoutAdaptation(bannerProduct, 300);

        List<String> images = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            images.add("http://img03.tooopen.com/images/20131102/sy_45238929299.jpg");
        }
        BannerUtils.initBanner(bannerProduct, images, 0, 0);
    }

    @Override
    public void onAdd(TextView textView) {
        count++;
        textView.setText(count + "");

    }

    @Override
    public void onSubtract(TextView textView) {
        count--;
        if (count < 0)
            count = 0;
        textView.setText(count + "");

    }
}
