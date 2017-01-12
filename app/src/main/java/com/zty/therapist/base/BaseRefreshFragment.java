package com.zty.therapist.base;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.zty.therapist.R;
import com.zty.therapist.recycler.FooterRefreshAdapter;
import com.zty.therapist.widget.RecyclerViewDivider;

import butterknife.BindView;

/**
 * Created by tianyu on 2017/1/1.
 */

public abstract class BaseRefreshFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.recyclerViewRefresh)
    public RecyclerView recyclerViewRefresh;
    @BindView(R.id.swipeRefreshLayout)
    public SwipeRefreshLayout swipeRefreshLayout;

    public int mLastVisibleItemPosition;
    public int pageNo = 1;
    public int mTempPageCount = 2;
    public boolean isLoadMore;
    public boolean isRefresh = true;

    public FooterRefreshAdapter adapter;

    @Override
    public int getContentVew() {
        return R.layout.view_recycler_refresh;
    }

    @Override
    public void initData() {

        initReadyData();
        swipeRefreshLayout.setColorSchemeResources(R.color.refresh1, R.color.refresh2, R.color.refresh3);
        swipeRefreshLayout.setOnRefreshListener(this);

        setAdapter();
        recyclerViewRefresh.setAdapter(adapter);

        if (isHaveDivider()) {
            recyclerViewRefresh.addItemDecoration(new RecyclerViewDivider(context, LinearLayoutManager.HORIZONTAL));
        }

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
                        if (isRefresh) {
                            adapter.updateRefreshState(FooterRefreshAdapter.STATE_FINISH);
                        } else {
                            adapter.updateRefreshState(FooterRefreshAdapter.STATE_START);
                        }
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

    @Override
    public void onRefresh() {
        isLoadMore = false;
        isRefresh = true;
        pageNo = 1;
        fetchData();
    }

    protected abstract void initReadyData();

    protected abstract void setAdapter();

    protected abstract boolean isHaveDivider();

    protected abstract void fetchData();

}
