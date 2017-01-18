package com.zty.therapist.ui.fragment.personal;

import android.os.Bundle;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.RequestParams;
import com.zty.therapist.adapter.DoctorOrderAdapter;
import com.zty.therapist.adapter.HealthOrderAdapter;
import com.zty.therapist.base.BaseRefreshFragment;
import com.zty.therapist.model.DoctorOrderModel;
import com.zty.therapist.model.NoteModel;
import com.zty.therapist.model.ProductOrderModel;
import com.zty.therapist.model.ResultBean;
import com.zty.therapist.recycler.FooterRefreshAdapter;
import com.zty.therapist.url.RequestManager;
import com.zty.therapist.url.Urls;
import com.zty.therapist.utils.ResultUtil;
import com.zty.therapist.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zty on 2016/12/27.
 */

public class OrderFragment extends BaseRefreshFragment {

    private static final int CODE_DOCTOR_LIST = 0;
    private static final int CODE_PRODUCT_LIST = 1;

    private int type;//0：保健品订单；1：医生订单

    public static OrderFragment newInstance(int type) {
        OrderFragment fragment = new OrderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    protected void initReadyData() {
        type = getArguments().getInt("type");
    }

    @Override
    protected void fetchData() {
        if (type == 0) {
            getProductsOrderList();
        } else {
            getDoctorOrderList();
        }
    }

    private void getDoctorOrderList() {
        RequestParams params = new RequestParams();
        params.put("pageNo", pageNo);
        RequestManager.get(CODE_DOCTOR_LIST, Urls.getDoctorOrderList, params, this);
    }

    private void getProductsOrderList() {
        RequestParams params = new RequestParams();
        params.put("pageNo", pageNo);
        RequestManager.get(CODE_PRODUCT_LIST, Urls.getProductsOrderList, params, this);
    }


    @Override
    protected void setAdapter() {
        if (type == 0) {
            adapter = new HealthOrderAdapter(context);
        } else {
            adapter = new DoctorOrderAdapter(context);
        }
    }

    @Override
    protected boolean isHaveDivider() {
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
                case CODE_DOCTOR_LIST:
                    List<DoctorOrderModel> models = new Gson().fromJson(resultBean.getResult(), new TypeToken<List<DoctorOrderModel>>() {
                    }.getType());

                    swipeRefreshLayout.setRefreshing(false);
                    if (models != null && models.size() > 0) {
                        if (isLoadMore) {
                            if (models.size() == 0) {
                                adapter.updateRefreshState(FooterRefreshAdapter.STATE_FINISH);
                            } else if (models.size() < 10) {
                                adapter.notifyBottomRefresh(models);
                                adapter.updateRefreshState(FooterRefreshAdapter.STATE_FINISH);
                            } else {
                                adapter.notifyBottomRefresh(models);
                                isRefresh = false;
                                mTempPageCount++;
                            }
                        } else {
                            adapter.notifyTopRefresh(models);
                        }
                    } else {
                        if (pageNo == 1) {
                            adapter.clearData();
                        }
                        adapter.updateRefreshState(FooterRefreshAdapter.STATE_FINISH);
                    }
                    break;
                case CODE_PRODUCT_LIST:
                    List<ProductOrderModel> productOrderModels = new Gson().fromJson(resultBean.getResult(), new TypeToken<List<ProductOrderModel>>() {
                    }.getType());

                    swipeRefreshLayout.setRefreshing(false);
                    if (productOrderModels != null && productOrderModels.size() > 0) {
                        if (isLoadMore) {
                            if (productOrderModels.size() == 0) {
                                adapter.updateRefreshState(FooterRefreshAdapter.STATE_FINISH);
                            } else if (productOrderModels.size() < 10) {
                                adapter.notifyBottomRefresh(productOrderModels);
                                adapter.updateRefreshState(FooterRefreshAdapter.STATE_FINISH);
                            } else {
                                adapter.notifyBottomRefresh(productOrderModels);
                                isRefresh = false;
                                mTempPageCount++;
                            }
                        } else {
                            adapter.notifyTopRefresh(productOrderModels);
                        }
                    } else {
                        if (pageNo == 1) {
                            adapter.clearData();
                        }
                        adapter.updateRefreshState(FooterRefreshAdapter.STATE_FINISH);
                    }
                    break;
            }
        } else {
            ToastUtils.show(context, resultBean.getMsg());
        }

    }
}
