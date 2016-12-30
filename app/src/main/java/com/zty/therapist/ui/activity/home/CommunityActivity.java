package com.zty.therapist.ui.activity.home;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zty.therapist.R;
import com.zty.therapist.adapter.CommunityAdapter;
import com.zty.therapist.base.BaseActivity;
import com.zty.therapist.model.CommunityModel;
import com.zty.therapist.recycler.FooterRefreshAdapter;
import com.zty.therapist.widget.RecyclerViewDivider;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 感情天地
 * Created by zty on 2016/12/17.
 */

public class CommunityActivity extends BaseActivity implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.recyclerViewRefresh)
    RecyclerView recyclerViewRefresh;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    CommunityAdapter adapter;

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
        title.setText("感情天地");
        left.setBackgroundResource(R.mipmap.ic_back);
        left.setOnClickListener(this);
        right.setBackgroundResource(R.mipmap.ic_publish);
        right.setOnClickListener(this);


        swipeRefreshLayout.setColorSchemeResources(R.color.refresh1, R.color.refresh2, R.color.refresh3);
        swipeRefreshLayout.setOnRefreshListener(this);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewRefresh.setLayoutManager(layoutManager);

        adapter = new CommunityAdapter(this, getSupportFragmentManager());

        recyclerViewRefresh.addItemDecoration(new RecyclerViewDivider(this, LinearLayoutManager.HORIZONTAL));
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

        List<CommunityModel> models = new ArrayList<>();
        CommunityModel model = new CommunityModel();

        List<String> urls = new ArrayList<>();
        urls.add("https://gss0.baidu.com/7Po3dSag_xI4khGko9WTAnF6hhy/zhidao/wh%3D600%2C800/sign=d53f032478f40ad115b1cfe5671c3de7/962bd40735fae6cda4cd9f7d0cb30f2442a70fb4.jpg");
        urls.add("http://img1.imgtn.bdimg.com/it/u=2822571390,539988323&fm=214&gp=0.jpg");
        urls.add("http://img3.imgtn.bdimg.com/it/u=1104528977,2768410894&fm=21&gp=0.jpg");
        urls.add("http://img22.mtime.cn/up/2011/01/05/133717.71056963_500.jpg");
        urls.add("https://gss0.baidu.com/7Po3dSag_xI4khGko9WTAnF6hhy/zhidao/wh%3D600%2C800/sign=d53f032478f40ad115b1cfe5671c3de7/962bd40735fae6cda4cd9f7d0cb30f2442a70fb4.jpg");
        urls.add("http://img1.imgtn.bdimg.com/it/u=2822571390,539988323&fm=214&gp=0.jpg");
        urls.add("http://img3.imgtn.bdimg.com/it/u=1104528977,2768410894&fm=21&gp=0.jpg");
        urls.add("http://img22.mtime.cn/up/2011/01/05/133717.71056963_500.jpg");
        model.setUrls(urls);
        models.add(model);
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
                startActivity(new Intent(CommunityActivity.this, PublishActivity.class));
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
