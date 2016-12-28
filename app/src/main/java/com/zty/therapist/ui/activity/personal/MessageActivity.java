package com.zty.therapist.ui.activity.personal;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zty.therapist.R;
import com.zty.therapist.adapter.MessageAdapter;
import com.zty.therapist.base.BaseActivity;
import com.zty.therapist.model.MessageModel;
import com.zty.therapist.recycler.FooterRefreshAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 消息中心
 * Created by zty on 2016/12/26.
 */

public class MessageActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener, View.OnClickListener {

    @BindView(R.id.recyclerViewRefresh)
    RecyclerView recyclerViewRefresh;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    private int mLastVisibleItemPosition;
    private int pageNo = 1;
    private int mTempPageCount = 2;
    private boolean isLoadMore;

    MessageAdapter adapter;

    @Override
    protected int getContentView() {
        return R.layout.view_recycler_refresh;
    }

    @Override
    protected void initData() {
        title.setText("我的消息");
        left.setBackgroundResource(R.mipmap.ic_back);
        left.setOnClickListener(this);
        right.setVisibility(View.INVISIBLE);

        swipeRefreshLayout.setColorSchemeResources(R.color.refresh1, R.color.refresh2, R.color.refresh3);
        swipeRefreshLayout.setOnRefreshListener(this);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewRefresh.setLayoutManager(layoutManager);

        adapter = new MessageAdapter(this);

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
        List<MessageModel> models = new ArrayList<>();

        MessageModel model = new MessageModel();
        model.setType(0);
        model.setTitle("公告通知");
        model.setContent("我公司现已经与北京、上海、天津、西安、成都、广州的三甲医院知名专家建立了战略合作，需要名医手术的患者，可通过我们的平台预定，比在大医院做手术便宜很多。");
        models.add(model);
        model = new MessageModel();
        model.setType(1);
        model.setTitle("加入组员通知");
        model.setContent("组长王丽邀请您加入到她的组里，加入组后系统赠送您积分，并可以与其他组员进行互动。");
        models.add(model);
        model = new MessageModel();
        model.setType(1);
        model.setTitle("升级组长通知");
        model.setContent("系统管理员提升您为组长，提升为组长后，系统每月给您100积分，积分可用来替班使用，另外优先购买集体社保。");
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
    public void onRefresh() {
        isLoadMore = false;
        pageNo = 1;
        fetchData();
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.titleLeft:
                finish();
                break;
        }

    }
}
