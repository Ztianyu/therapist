package com.zty.therapist.ui.fragment.personal;

import android.os.Bundle;

import com.zty.therapist.adapter.DoctorOrderAdapter;
import com.zty.therapist.adapter.HealthOrderAdapter;
import com.zty.therapist.base.BaseRefreshFragment;
import com.zty.therapist.model.DoctorOrderModel;
import com.zty.therapist.model.ProductOrderModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zty on 2016/12/27.
 */

public class OrderFragment extends BaseRefreshFragment {

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
        List<Object> data = new ArrayList<>();
        if (type == 0) {
            ProductOrderModel model = new ProductOrderModel();
            for (int i = 0; i < 10; i++) {
                data.add(model);
            }
        } else {
            DoctorOrderModel model = new DoctorOrderModel();
            for (int i = 0; i < 10; i++) {
                data.add(model);
            }
        }

        if (isLoadMore) {
            adapter.notifyBottomRefresh(data);
            mTempPageCount++;
        } else {
            adapter.notifyTopRefresh(data);
            swipeRefreshLayout.setRefreshing(false);
        }
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

    }
}
