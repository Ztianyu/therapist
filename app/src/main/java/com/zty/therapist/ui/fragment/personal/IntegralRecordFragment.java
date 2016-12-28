package com.zty.therapist.ui.fragment.personal;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.zty.therapist.R;
import com.zty.therapist.adapter.DoctorIntegralAdapter;
import com.zty.therapist.adapter.ProductIntegralAdapter;
import com.zty.therapist.base.BaseFragment;
import com.zty.therapist.model.DoctorRecordModel;
import com.zty.therapist.model.IntegralRecordModel;
import com.zty.therapist.recycler.FooterRefreshAdapter;
import com.zty.therapist.utils.ResourceUtil;
import com.zty.therapist.widget.RecyclerViewDivider;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by zty on 2016/12/28.
 */

public class IntegralRecordFragment extends BaseFragment {

    @BindView(R.id.recyclerViewIntegral)
    RecyclerView recyclerViewIntegral;
    @BindView(R.id.textIntegralCount)
    TextView textIntegralCount;

    private FooterRefreshAdapter adapter;

    private int mLastVisibleItemPosition;
    private int pageNo = 1;
    private int mTempPageCount = 2;
    private boolean isLoadMore;


    private int type;//0：保健品购买；1：医生预约

    public static IntegralRecordFragment newInstance(int type) {
        IntegralRecordFragment fragment = new IntegralRecordFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public int getContentVew() {
        return R.layout.view_integral_record;
    }

    @Override
    public void initData() {
        type = getArguments().getInt("type");

        if (type == 0) {
            adapter = new ProductIntegralAdapter(context);
        } else {
            adapter = new DoctorIntegralAdapter(context);
        }
        recyclerViewIntegral.addItemDecoration(new RecyclerViewDivider(context, LinearLayoutManager.HORIZONTAL));
        recyclerViewIntegral.setAdapter(adapter);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewIntegral.setLayoutManager(layoutManager);

        recyclerViewIntegral.addOnScrollListener(new RecyclerView.OnScrollListener() {
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

        textIntegralCount.setText(String.format(ResourceUtil.resToStr(context, R.string.integralCount), "5000"));

    }

    private void fetchData() {

        List<Object> models;
        if (type == 0) {
            models = new ArrayList<>();
            IntegralRecordModel model;
            for (int i = 0; i < 10; i++) {
                model = new IntegralRecordModel();
                if (pageNo == 1 && i == 0) {
                    model.setTop(true);
                }
                models.add(model);
            }
        } else {
            models = new ArrayList<>();
            DoctorRecordModel model;
            for (int i = 0; i < 10; i++) {
                model = new DoctorRecordModel();
                if (pageNo == 1 && i == 0) {
                    model.setTop(true);
                }
                models.add(model);
            }
        }

        if (isLoadMore) {
            adapter.notifyBottomRefresh(models);
            adapter.updateRefreshState(FooterRefreshAdapter.STATE_FINISH);
            mTempPageCount++;
        } else {
            adapter.notifyTopRefresh(models);
        }
    }

    @Override
    public void onFailureCallback(int requestCode, String errorMsg) {

    }

    @Override
    public void onSuccessCallback(int requestCode, String response) {

    }
}
