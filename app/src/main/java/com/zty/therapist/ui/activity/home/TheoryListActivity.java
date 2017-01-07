package com.zty.therapist.ui.activity.home;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.RequestParams;
import com.zty.therapist.R;
import com.zty.therapist.adapter.TheoryListAdapter;
import com.zty.therapist.base.BaseActivity;
import com.zty.therapist.manager.LayoutManager;
import com.zty.therapist.model.ResultBean;
import com.zty.therapist.model.TestModel;
import com.zty.therapist.model.OptionModel;
import com.zty.therapist.url.RequestManager;
import com.zty.therapist.url.Urls;
import com.zty.therapist.utils.ResultUtil;
import com.zty.therapist.widget.RecyclerViewDivider;

import java.util.List;

import butterknife.BindView;

/**
 * Created by zty on 2017/1/5.
 */

public class TheoryListActivity extends BaseActivity {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    OptionModel model;
    TheoryListAdapter adapter;

    @Override
    protected int getContentView() {
        return R.layout.view_recycler_normal;
    }

    @Override
    protected void initData() {

        model = (OptionModel) getIntent().getSerializableExtra("model");
        title.setText(model.getValue());

        adapter = new TheoryListAdapter(this);
        recyclerView.setAdapter(adapter);
        LayoutManager.setVertical(this, recyclerView);
        recyclerView.addItemDecoration(new RecyclerViewDivider(this, LinearLayoutManager.HORIZONTAL));

        fetchData();
    }


    private void fetchData() {
        RequestParams params = new RequestParams();
        params.put("type", model.getKey());
        RequestManager.get(-1, Urls.getHealthLoreList, params, this);
    }

    @Override
    public void onFailureCallback(int requestCode, String errorMsg) {

    }

    @Override
    public void onSuccessCallback(int requestCode, String response) {
        ResultBean resultBean = ResultUtil.getResult(response);
        if (resultBean.isSuccess()) {
            List<TestModel> testModels = new Gson().fromJson(resultBean.getResult(), new TypeToken<List<TestModel>>() {
            }.getType());
            if (testModels != null)
                adapter.notifyBottomRefresh(testModels);
        }

    }


}
