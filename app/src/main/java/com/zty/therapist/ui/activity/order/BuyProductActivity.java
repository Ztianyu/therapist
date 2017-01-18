package com.zty.therapist.ui.activity.order;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.youth.banner.Banner;
import com.zty.therapist.R;
import com.zty.therapist.base.BaseActivity;
import com.zty.therapist.inter.OnAddAndSubtract;
import com.zty.therapist.model.AddressModel;
import com.zty.therapist.model.HealthProductModel;
import com.zty.therapist.model.ResultBean;
import com.zty.therapist.ui.activity.personal.AddressActivity;
import com.zty.therapist.ui.activity.personal.MyOrderActivity;
import com.zty.therapist.url.RequestManager;
import com.zty.therapist.url.Urls;
import com.zty.therapist.utils.BannerUtils;
import com.zty.therapist.utils.ResultUtil;
import com.zty.therapist.utils.ToastUtils;
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

    private static final int CODE_GET_DEFAULT_ADD = 0;
    private static final int CODE_SUBMIT = 1;
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
    @BindView(R.id.textUserAddress)
    TextView textUserAddress;
    @BindView(R.id.textAddAddress)
    TextView textAddAddress;

    private int count = 1;

    private HealthProductModel model;

    private String addressId;
    private String address;

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

    private void setText() {
        model = (HealthProductModel) getIntent().getSerializableExtra("model");
        textProductDetailName.setText(model.getProductNm());
        textProductDetailNote.setMaxLines(100);
        textProductDetailNote.setText(model.getRemarks());
        textProductDetailPrise.setText(model.getPrice());
        textProductDetailIntegral.setText(model.getHealthCurrency() + "");
        textTotalPrise.setText(Double.parseDouble(model.getPrice()) * count + "");
        getIsDefault();
    }

    private void submitProductsOrder() {
        RequestParams params = new RequestParams();
        params.put("price", model.getPrice());
        params.put("quantity", count);
        params.put("productId", model.getId());
        params.put("addressId", addressId);
        RequestManager.post(CODE_SUBMIT, Urls.submitProductsOrder, params, this);
    }

    private void getIsDefault() {
        RequestParams params = new RequestParams();
        RequestManager.get(CODE_GET_DEFAULT_ADD, Urls.getIsDefault, params, this);
    }

    private boolean checkData() {
        if (count <= 0) {
            ToastUtils.show(this, "请选择数量");
            return false;
        }
        if (TextUtils.isEmpty(addressId)) {
            ToastUtils.show(this, "请选择收货地址");
            return false;
        }
        return true;
    }

    @Override
    public void onFailureCallback(int requestCode, String errorMsg) {

    }

    @Override
    public void onSuccessCallback(int requestCode, String response) {
        ResultBean resultBean = ResultUtil.getResult(response);
        if (resultBean.isSuccess()) {
            switch (requestCode) {
                case CODE_GET_DEFAULT_ADD:
                    AddressModel addressModel = new Gson().fromJson(resultBean.getResult(), AddressModel.class);
                    if (addressModel != null) {
                        addressId = addressModel.getId();
                        address = addressModel.getAddress();
                        textUserAddress.setText(address);
                    }
                    break;
                case CODE_SUBMIT:
                    ToastUtils.show(this, "下单成功");
                    MyOrderActivity.currentPage = 1;
                    startActivity(new Intent(this, MyOrderActivity.class));
                    finish();
                    break;
            }
        } else {
            ToastUtils.show(this, resultBean.getMsg());
        }

    }

    @OnClick({R.id.btnPayProduct, R.id.textAddAddress})
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.btnPayProduct:
                if (checkData())
                    submitProductsOrder();
                break;
            case R.id.textAddAddress:
                startActivityForResult(new Intent(this, AddressActivity.class), 1);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (requestCode == RESULT_OK) {
                if (data != null) {
                    Bundle bundle = data.getExtras();
                    addressId = bundle.getString("addressId");
                    address = bundle.getString("address");
                    textUserAddress.setText(address);
                }
            }
        }
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
        textTotalPrise.setText(Double.parseDouble(model.getPrice()) * count + "");
    }

    @Override
    public void onSubtract(TextView textView) {
        count--;
        if (count < 0)
            count = 0;
        textView.setText(count + "");
        textTotalPrise.setText(Double.parseDouble(model.getPrice()) * count + "");
    }

}
