package com.zty.therapist.ui.fragment.home;

import android.os.Bundle;

import com.zty.therapist.R;
import com.zty.therapist.adapter.LectureAdapter;
import com.zty.therapist.base.BaseFragment;
import com.zty.therapist.manager.LayoutManager;
import com.zty.therapist.model.LectureModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.droidlover.xrecyclerview.XRecyclerView;

/**
 * Created by zty on 2016/12/19.
 */

public class ClassRoomFragment extends BaseFragment {
    @BindView(R.id.recyclerView)
    XRecyclerView recyclerView;

    private LectureAdapter adapter;

    public static ClassRoomFragment newInstance(int type) {
        ClassRoomFragment fragment = new ClassRoomFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getContentVew() {
        return R.layout.view_recycler;
    }

    @Override
    public void initData() {
        adapter = new LectureAdapter(context);

        recyclerView.gridLayoutManager(context, 3);

        recyclerView.setAdapter(adapter);

        getData();
    }

    private void getData() {

    }

    @Override
    public void onFailureCallback(int requestCode, String errorMsg) {

    }

    @Override
    public void onSuccessCallback(int requestCode, String response) {

    }

}
