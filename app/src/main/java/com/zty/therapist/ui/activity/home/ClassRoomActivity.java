package com.zty.therapist.ui.activity.home;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.RequestParams;
import com.zty.therapist.R;
import com.zty.therapist.adapter.TheoryAdapter;
import com.zty.therapist.base.BaseActivity;
import com.zty.therapist.manager.LayoutManager;
import com.zty.therapist.model.ResultBean;
import com.zty.therapist.model.OptionModel;
import com.zty.therapist.url.RequestManager;
import com.zty.therapist.url.Urls;
import com.zty.therapist.utils.ResultUtil;
import com.zty.therapist.widget.RecyclerViewDivider;

import java.util.List;

import butterknife.BindView;

/**
 * Created by zty on 2016/12/17.
 */

public class ClassRoomActivity extends BaseActivity {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    TheoryAdapter adapter;

    @Override
    protected int getContentView() {
        return R.layout.view_recycler_normal;
    }

    @Override
    protected void initData() {
        title.setText("微课堂");

        adapter = new TheoryAdapter(this, 1);
        recyclerView.setAdapter(adapter);
        LayoutManager.setVertical(this, recyclerView);
        recyclerView.addItemDecoration(new RecyclerViewDivider(this, LinearLayoutManager.HORIZONTAL));

        fetchData();
    }

    private void fetchData() {
        RequestParams params = new RequestParams();
        RequestManager.get(-1, Urls.getVideoTypeOption, params, this);
    }

    @Override
    public void onFailureCallback(int requestCode, String errorMsg) {

    }

    @Override
    public void onSuccessCallback(int requestCode, String response) {

        ResultBean resultBean = ResultUtil.getResult(response);
        if (resultBean.isSuccess()) {
            List<OptionModel> optionModels = new Gson().fromJson(resultBean.getResult(), new TypeToken<List<OptionModel>>() {
            }.getType());
            if (optionModels != null) {
                adapter.notifyTopRefresh(optionModels);
            }
        }
    }

}
