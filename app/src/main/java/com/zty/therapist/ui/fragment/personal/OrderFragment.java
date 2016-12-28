package com.zty.therapist.ui.fragment.personal;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.zty.therapist.R;
import com.zty.therapist.adapter.DoctorOrderAdapter;
import com.zty.therapist.adapter.HealthOrderAdapter;
import com.zty.therapist.adapter.MessageAdapter;
import com.zty.therapist.base.BaseFragment;
import com.zty.therapist.model.DoctorOrderModel;
import com.zty.therapist.model.ProductOrderModel;
import com.zty.therapist.recycler.FooterRefreshAdapter;
import com.zty.therapist.widget.RecyclerViewDivider;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by zty on 2016/12/27.
 */

public class OrderFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.recyclerViewRefresh)
    RecyclerView recyclerViewRefresh;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    private int mLastVisibleItemPosition;
    private int pageNo = 1;
    private int mTempPageCount = 2;
    private boolean isLoadMore;

    private FooterRefreshAdapter adapter;

    private int type;//0：保健品订单；1：医生订单

    public static OrderFragment newInstance(int type) {
        OrderFragment fragment = new OrderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getContentVew() {
        return R.layout.view_recycler_refresh;
    }

    @Override
    public void initData() {
        type = getArguments().getInt("type");

        swipeRefreshLayout.setColorSchemeResources(R.color.refresh1, R.color.refresh2, R.color.refresh3);
        swipeRefreshLayout.setOnRefreshListener(this);

        if (type == 0) {
            adapter = new HealthOrderAdapter(context);
        } else {
            adapter = new DoctorOrderAdapter(context);
        }

        recyclerViewRefresh.addItemDecoration(new RecyclerViewDivider(context, LinearLayoutManager.HORIZONTAL));
        recyclerViewRefresh.setAdapter(adapter);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewRefresh.setLayoutManager(layoutManager);

        recyclerViewRefresh.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    mLastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
                    //防止重复请求
                    if (pageNo == mTempPageCount) {
                        return;
                    }
                    if (mLastVisibleItemPosition > 0 && mLastVisibleItemPosition + 1 == adapter.getItemCount()) {
                        //已到达底部，开始加载更多
                        isLoadMore = true;
                        adapter.updateRefreshState(FooterRefreshAdapter.STATE_START);
                        pageNo = mTempPageCount;
                        fetchData();
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                mLastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
            }
        });
        fetchData();
        swipeRefreshLayout.setRefreshing(false);
    }

    private void fetchData() {

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
    public void onFailureCallback(int requestCode, String errorMsg) {

    }

    @Override
    public void onSuccessCallback(int requestCode, String response) {

    }

    @Override
    public void onRefresh() {
        isLoadMore = false;
        pageNo = 1;
        fetchData();
    }
}
