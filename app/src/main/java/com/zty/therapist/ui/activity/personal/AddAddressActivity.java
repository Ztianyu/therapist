package com.zty.therapist.ui.activity.personal;

import android.support.v7.widget.AppCompatSpinner;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.RequestParams;
import com.zty.therapist.R;
import com.zty.therapist.base.BaseActivity;
import com.zty.therapist.model.OptionModel;
import com.zty.therapist.model.ResultBean;
import com.zty.therapist.url.RequestManager;
import com.zty.therapist.url.Urls;
import com.zty.therapist.utils.ResultUtil;
import com.zty.therapist.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by zty on 2017/1/17.
 */

public class AddAddressActivity extends BaseActivity implements AdapterView.OnItemSelectedListener {

    private static final int CODE_PROVINCE = 0;
    private static final int CODE_CITY = 1;
    private static final int CODE_AREA = 2;
    private static final int CODE_SUBMIT = 3;

    @BindView(R.id.spinnerProvince)
    AppCompatSpinner spinnerProvince;
    @BindView(R.id.spinnerCity)
    AppCompatSpinner spinnerCity;
    @BindView(R.id.spinnerArea)
    AppCompatSpinner spinnerArea;
    @BindView(R.id.editAddress)
    EditText editAddress;
    @BindView(R.id.editTakeName)
    EditText editTakeName;
    @BindView(R.id.editTakeMobile)
    EditText editTakeMobile;

    private List<String> provinces = new ArrayList<>();
    private List<String> citys = new ArrayList<>();
    private List<String> areas = new ArrayList<>();

    private List<Integer> provinceCodes = new ArrayList<>();
    private List<Integer> cityCodes = new ArrayList<>();
    private List<Integer> areaCodes = new ArrayList<>();

    private int provinceId = 0;
    private int cityId = 0;
    private int areaId = 0;

    @Override
    protected int getContentView() {
        return R.layout.view_add_address;
    }

    @Override
    protected void initData() {
        title.setText("新增地址");
        right.setText("保存");

        spinnerProvince.setOnItemSelectedListener(this);
        spinnerCity.setOnItemSelectedListener(this);
        spinnerArea.setOnItemSelectedListener(this);

        getProvinceArea();
    }

    private void getProvinceArea() {
        RequestManager.get(CODE_PROVINCE, Urls.getProvinceArea, null, this);
    }

    private void getCityArea() {
        RequestParams params = new RequestParams();
        params.put("provinceId", provinceId);
        RequestManager.get(CODE_CITY, Urls.getCityArea, params, this);
    }

    private void getDistrictArea() {
        RequestParams params = new RequestParams();
        params.put("cityId", cityId);
        RequestManager.get(CODE_AREA, Urls.getDistrictArea, params, this);
    }

    private void submitAddress() {
        RequestParams params = new RequestParams();
        params.put("province", provinceId);
        params.put("city", cityId);
        params.put("area", areaId);
        params.put("address", editAddress.getText().toString());
        params.put("takeName", editTakeName.getText().toString());
        params.put("mobile", editTakeMobile.getText().toString());
        RequestManager.post(CODE_SUBMIT, Urls.submitAddress, params, this);
    }

    @Override
    public void rightClick() {
        if (checkData())
            submitAddress();
    }

    private boolean checkData() {
        if (cityId == 0) {
            ToastUtils.show(this, "请选择城市");
            return false;
        }
        if (areaId == 0) {
            ToastUtils.show(this, "请选择地区");
            return false;
        }
        if (TextUtils.isEmpty(editAddress.getText().toString())) {
            ToastUtils.show(this, "请填写地址");
            return false;
        }
        if (TextUtils.isEmpty(editTakeName.getText().toString())) {
            ToastUtils.show(this, "请填写收货人");
            return false;
        }
        if (TextUtils.isEmpty(editTakeMobile.getText().toString())) {
            ToastUtils.show(this, "请填写收货人手机号码");
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
                case CODE_PROVINCE:
                    List<OptionModel> provinceModels = new Gson().fromJson(resultBean.getResult(), new TypeToken<List<OptionModel>>() {
                    }.getType());
                    if (provinceModels != null && provinceModels.size() > 0) {
                        provinces.clear();
                        provinceCodes.clear();
                        for (OptionModel optionModel : provinceModels) {
                            provinces.add(optionModel.getValue());
                            provinceCodes.add(optionModel.getKey());
                        }
                        spinnerProvince.setAdapter(new ArrayAdapter(this, R.layout.item_spinner, R.id.textSpinner, provinces));
                    }
                    break;
                case CODE_CITY:
                    List<OptionModel> cityModels = new Gson().fromJson(resultBean.getResult(), new TypeToken<List<OptionModel>>() {
                    }.getType());
                    if (cityModels != null && cityModels.size() > 0) {
                        citys.clear();
                        cityCodes.clear();
                        for (OptionModel optionModel : cityModels) {
                            citys.add(optionModel.getValue());
                            cityCodes.add(optionModel.getKey());
                        }
                        spinnerCity.setAdapter(new ArrayAdapter(this, R.layout.item_spinner, R.id.textSpinner, citys));
                    }
                    break;
                case CODE_AREA:
                    List<OptionModel> areaModels = new Gson().fromJson(resultBean.getResult(), new TypeToken<List<OptionModel>>() {
                    }.getType());
                    if (areaModels != null && areaModels.size() > 0) {
                        areas.clear();
                        areaCodes.clear();
                        for (OptionModel optionModel : areaModels) {
                            areas.add(optionModel.getValue());
                            areaCodes.add(optionModel.getKey());
                        }
                        spinnerArea.setAdapter(new ArrayAdapter(this, R.layout.item_spinner, R.id.textSpinner, areas));
                    }
                    break;
                case CODE_SUBMIT:
                    ToastUtils.show(this, "添加成功");
                    finish();
                    break;
            }
        } else {
            ToastUtils.show(this, resultBean.getMsg());
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        switch (adapterView.getId()) {
            case R.id.spinnerProvince:
                provinceId = provinceCodes.get(i);
                getCityArea();
                break;
            case R.id.spinnerCity:
                cityId = cityCodes.get(i);
                getDistrictArea();
                break;
            case R.id.spinnerArea:
                areaId = areaCodes.get(i);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

}
