package com.zty.therapist.ui.activity.home;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;

import com.zty.therapist.R;
import com.zty.therapist.adapter.SearchMemberAdapter;
import com.zty.therapist.base.BaseActivity;
import com.zty.therapist.model.MemberModel;
import com.zty.therapist.recycler.FooterRefreshAdapter;
import com.zty.therapist.widget.RecyclerViewDivider;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by zty on 2017/1/3.
 */

public class AddMemberActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.editSearch)
    EditText editSearch;
    @BindView(R.id.imgSearch)
    ImageView imgSearch;
    @BindView(R.id.recyclerViewSearchMember)
    RecyclerView recyclerViewSearchMember;

    SearchMemberAdapter adapter;

    private int mLastVisibleItemPosition;
    private int pageNo = 1;
    private int mTempPageCount = 2;
    private boolean isLoadMore;

    @Override
    protected int getContentView() {
        return R.layout.activity_add_member;
    }

    @Override
    protected void initData() {

        title.setText("添加成员");
        left.setBackgroundResource(R.mipmap.ic_back);
        left.setOnClickListener(this);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewSearchMember.setLayoutManager(layoutManager);

        recyclerViewSearchMember.addItemDecoration(new RecyclerViewDivider(this, LinearLayoutManager.HORIZONTAL));
        adapter = new SearchMemberAdapter(this);
        recyclerViewSearchMember.setAdapter(adapter);

        recyclerViewSearchMember.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
        hideInput();

    }

    private void fetchData() {

        List<MemberModel> models = new ArrayList<>();
        MemberModel model = new MemberModel();
        models.add(model);
        models.add(model);
        models.add(model);
        models.add(model);

        if (isLoadMore) {
            adapter.notifyBottomRefresh(models);
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

    @OnClick(R.id.imgSearch)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.titleLeft:
                finish();
                break;
            case R.id.imgSearch:
                isLoadMore = false;
                hideInput();
                break;
        }
    }

    private void hideInput() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive()) {
            imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}
