package com.zty.therapist.ui.fragment.home;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.RequestParams;
import com.zty.therapist.R;
import com.zty.therapist.adapter.TestAdapter;
import com.zty.therapist.adapter.TheoryAdapter;
import com.zty.therapist.base.BaseFragment;
import com.zty.therapist.manager.LayoutManager;
import com.zty.therapist.model.ResultBean;
import com.zty.therapist.model.TestModel;
import com.zty.therapist.model.OptionModel;
import com.zty.therapist.recycler.NormalAdapter;
import com.zty.therapist.url.RequestManager;
import com.zty.therapist.url.Urls;
import com.zty.therapist.utils.ResultUtil;
import com.zty.therapist.widget.RecyclerViewDivider;

import java.util.List;

import butterknife.BindView;

/**
 * Created by zty on 2016/12/3.
 */

public class LearnFragment extends BaseFragment {

    private static final int CODE_KNOWLEDGE = 0;//康复知识
    private static final int CODE_TEST = 1;//康复试题

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private NormalAdapter adapter;

    private int type;//0：康复知识；1：康复试题

    public static LearnFragment newInstance(int type) {
        LearnFragment learnFragment = new LearnFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        learnFragment.setArguments(bundle);
        return learnFragment;
    }

    @Override
    public int getContentVew() {
        return R.layout.view_recycler_normal;
    }

    @Override
    public void initData() {
        type = getArguments().getInt("type");
        setAdapter();
        fetchData();
    }

    private void fetchData() {
        if (type == 0) {
            RequestParams params = new RequestParams();
            RequestManager.get(CODE_KNOWLEDGE, Urls.getDissessOption, params, this);
        } else if (type == 1) {
            RequestParams params = new RequestParams();
            RequestManager.get(CODE_TEST, Urls.getTestQuestionsList, params, this);
        }
    }

    @Override
    public void onFailureCallback(int requestCode, String errorMsg) {

    }

    @Override
    public void onSuccessCallback(int requestCode, String response) {
        ResultBean resultBean = ResultUtil.getResult(response);
        if (resultBean.isSuccess()) {
            switch (requestCode) {
                case CODE_KNOWLEDGE:
                    List<OptionModel> optionModels = new Gson().fromJson(resultBean.getResult(), new TypeToken<List<OptionModel>>() {
                    }.getType());
                    if (optionModels != null) {
                        adapter.notifyTopRefresh(optionModels);
                    }
                    break;
                case CODE_TEST:
                    List<TestModel> testModels = new Gson().fromJson(resultBean.getResult(), new TypeToken<List<TestModel>>() {
                    }.getType());
                    if (testModels != null) {
                        adapter.notifyTopRefresh(testModels);
                    }
                    break;
            }
        }
    }


    private void setAdapter() {
        if (type == 0) {
            adapter = new TheoryAdapter(context, 0);
        } else if (type == 1) {
            adapter = new TestAdapter(context);
        }
        recyclerView.setAdapter(adapter);
        LayoutManager.setVertical(context, recyclerView);
        recyclerView.addItemDecoration(new RecyclerViewDivider(context, LinearLayoutManager.HORIZONTAL));
    }

}