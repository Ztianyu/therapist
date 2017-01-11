package com.zty.therapist.ui.activity.home;

import android.content.Intent;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.RequestParams;
import com.zty.therapist.R;
import com.zty.therapist.adapter.InfoAdapter;
import com.zty.therapist.base.BaseRefreshActivity;
import com.zty.therapist.model.InfoModel;
import com.zty.therapist.model.ResultBean;
import com.zty.therapist.recycler.FooterRefreshAdapter;
import com.zty.therapist.url.RequestManager;
import com.zty.therapist.url.Urls;
import com.zty.therapist.utils.ResultUtil;
import com.zty.therapist.utils.ToastUtils;

import java.util.List;

/**
 * Created by zty on 2016/12/17.
 */

public class InfoActivity extends BaseRefreshActivity {


    @Override
    protected void initReadyData() {
        title.setText("同城活动");
        setRight(R.mipmap.ic_send_info);
    }

    @Override
    protected void setAdapter() {
        adapter = new InfoAdapter(this);
    }

    @Override
    protected boolean isHaveDivider() {
        return true;
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        onRefresh();
    }

    @Override
    protected void fetchData() {
        RequestParams params = new RequestParams();
        params.put("pageNo", pageNo);
        RequestManager.get(-1, Urls.getCityActivityList, params, this);
    }

    @Override
    public void onFailureCallback(int requestCode, String errorMsg) {

    }

    @Override
    public void onSuccessCallback(int requestCode, String response) {
        ResultBean resultBean = ResultUtil.getResult(response);
        if (resultBean.isSuccess()) {
            List<InfoModel> models = new Gson().fromJson(resultBean.getResult(), new TypeToken<List<InfoModel>>() {
            }.getType());

            swipeRefreshLayout.setRefreshing(false);
            if (models != null && models.size() > 0) {
                if (isLoadMore) {
                    if (models.size() == 0) {
                        adapter.updateRefreshState(FooterRefreshAdapter.STATE_FINISH);
                    } else {
                        adapter.notifyBottomRefresh(models);
                        mTempPageCount++;
                    }
                } else {
                    adapter.notifyTopRefresh(models);
                }
            } else {
                if (pageNo == 1) {
                    adapter.clearData();
                }
                adapter.updateRefreshState(FooterRefreshAdapter.STATE_FINISH);
            }

        } else {
            ToastUtils.show(this, resultBean.getMsg());
        }
    }

    @Override
    public void rightClick() {
        startActivity(new Intent(this, SendInfoActivity.class));
    }
}
