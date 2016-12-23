package com.zty.therapist.ui.fragment.home;

import android.os.Bundle;

import com.zty.therapist.R;
import com.zty.therapist.adapter.LectureAdapter;
import com.zty.therapist.base.BaseFragment;
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

    private int type;//0：神经类；1：心血管类；2：骨科类；

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
        type = getArguments().getInt("type");

        adapter = new LectureAdapter(context);

        recyclerView.gridLayoutManager(context, 3);

        recyclerView.setAdapter(adapter);

        getData();
    }

    private void getData() {

        List<LectureModel> models = new ArrayList<>();
        LectureModel model = new LectureModel();
        model.setDoctorNm("张毅主任");
        model.setTitle("心脑血管康复指南");
        models.add(model);
        models.add(model);
        models.add(model);
        models.add(model);
        models.add(model);
        adapter.setData(models);
    }

    @Override
    public void onFailureCallback(int requestCode, String errorMsg) {

    }

    @Override
    public void onSuccessCallback(int requestCode, String response) {

    }

}
