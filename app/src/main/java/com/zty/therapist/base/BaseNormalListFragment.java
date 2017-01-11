package com.zty.therapist.base;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.zty.therapist.R;
import com.zty.therapist.recycler.NormalAdapter;
import com.zty.therapist.widget.RecyclerViewDivider;

import butterknife.BindView;

/**
 * Created by zty on 2017/1/11.
 */

public abstract class BaseNormalListFragment extends BaseFragment {
    @BindView(R.id.recyclerView)
    public RecyclerView recyclerView;

    public NormalAdapter adapter;

    @Override
    public int getContentVew() {
        return R.layout.view_recycler_normal;
    }

    @Override
    public void initData() {
        initReadyData();

        setAdapter();
        recyclerView.setAdapter(adapter);

        if (isHaveDivider()) {
            recyclerView.addItemDecoration(new RecyclerViewDivider(context, LinearLayoutManager.HORIZONTAL));
        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        fetchData();

    }

    protected abstract void initReadyData();

    protected abstract void setAdapter();

    protected abstract boolean isHaveDivider();

    protected abstract void fetchData();

}
