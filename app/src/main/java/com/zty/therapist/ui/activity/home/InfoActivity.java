package com.zty.therapist.ui.activity.home;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zty.therapist.R;
import com.zty.therapist.adapter.InfoAdapter;
import com.zty.therapist.base.BaseActivity;
import com.zty.therapist.model.InfoModel;
import com.zty.therapist.recycler.FooterRefreshAdapter;
import com.zty.therapist.widget.RecyclerViewDivider;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by zty on 2016/12/17.
 */

public class InfoActivity extends BaseActivity implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.recyclerViewRefresh)
    RecyclerView recyclerViewRefresh;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    InfoAdapter adapter;

    private int mLastVisibleItemPosition;
    private int pageNo = 1;
    private int mTempPageCount = 2;
    private boolean isLoadMore;

    @Override
    protected int getContentView() {
        return R.layout.view_recycler_refresh;
    }

    @Override
    protected void initData() {

        title.setText("同城活动");
        left.setBackgroundResource(R.mipmap.ic_back);
        left.setOnClickListener(this);
        right.setBackgroundResource(R.mipmap.ic_send_info);
        right.setOnClickListener(this);

        swipeRefreshLayout.setColorSchemeResources(R.color.refresh1, R.color.refresh2, R.color.refresh3);
        swipeRefreshLayout.setOnRefreshListener(this);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewRefresh.setLayoutManager(layoutManager);

        recyclerViewRefresh.addItemDecoration(new RecyclerViewDivider(this, LinearLayoutManager.HORIZONTAL));
        adapter = new InfoAdapter(this);

        recyclerViewRefresh.setAdapter(adapter);

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

        List<InfoModel> models = new ArrayList<>();
        InfoModel model = new InfoModel();
        models.add(model);
        models.add(model);
        models.add(model);
        models.add(model);

        if (isLoadMore) {
            adapter.notifyBottomRefresh(models);
            mTempPageCount++;
        } else {
            adapter.notifyTopRefresh(models);
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
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.titleLeft:
                finish();
                break;
            case R.id.titleRight:
                startActivity(new Intent(this, SendInfoActivity.class));
                break;
        }
    }

    @Override
    public void onRefresh() {
        isLoadMore = false;
        pageNo = 1;
        fetchData();
    }


}
