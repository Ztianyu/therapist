package com.zty.therapist.ui.activity.order;

import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.youth.banner.Banner;
import com.zty.therapist.R;
import com.zty.therapist.base.BaseActivity;
import com.zty.therapist.inter.OnAddAndSubtract;
import com.zty.therapist.model.HealthProductModel;
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

    private HealthProductModel model;

    @Override
    protected int getContentView() {
        return R.layout.activity_buy_product;
    }

    @Override
    protected void initData() {

        title.setText("购 买");

        setText();

        radioGroupPay.setOnCheckedChangeListener(this);
        countView.setListener(this);

        initBanner();
    }

    private void setText(){
        model = (HealthProductModel) getIntent().getSerializableExtra("model");
        textProductDetailName.setText(model.getProductNm());
        textProductDetailNote.setMaxLines(100);
        textProductDetailNote.setText(model.getRemarks());
        textProductDetailPrise.setText(model.getPrice());
        textProductDetailIntegral.setText(model.getHealthCurrency()+"");
        textTotalPrise.setText(Double.parseDouble(model.getPrice())*count+"");
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

    @Override
    public void onAdd(TextView textView) {
        count++;
        textView.setText(count + "");

        textTotalPrise.setText(Double.parseDouble(model.getPrice())*count+"");

    }

    @Override
    public void onSubtract(TextView textView) {
        count--;
        if (count < 0)
            count = 0;
        textView.setText(count + "");

        textTotalPrise.setText(Double.parseDouble(model.getPrice())*count+"");

    }
}
