package com.zty.therapist.base;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.zty.therapist.R;
import com.zty.therapist.recycler.NormalAdapter;
import com.zty.therapist.widget.RecyclerViewDivider;

import butterknife.BindView;

/**
 * Created by zty on 2017/1/14.
 */

public abstract class BaseNormalListActivity extends BaseActivity {

    @BindView(R.id.recyclerView)
    public RecyclerView recyclerView;

    public NormalAdapter adapter;

    @Override
    protected int getContentView() {
        return R.layout.view_recycler_normal;
    }

    @Override
    protected void initData() {
        initReadyData();

        setAdapter();
        recyclerView.setAdapter(adapter);

        if (isHaveDivider()) {
            recyclerView.addItemDecoration(new RecyclerViewDivider(this, LinearLayoutManager.HORIZONTAL));
        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        fetchData();
    }

    protected abstract void initReadyData();

    protected abstract void setAdapter();

    protected abstract boolean isHaveDivider();

    protected abstract void fetchData();
}
