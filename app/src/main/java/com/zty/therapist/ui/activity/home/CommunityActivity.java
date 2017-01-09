package com.zty.therapist.ui.activity.home;

import android.content.Intent;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.RequestParams;
import com.zty.therapist.R;
import com.zty.therapist.adapter.CommunityAdapter;
import com.zty.therapist.base.BaseRefreshActivity;
import com.zty.therapist.inter.SendReplayListener;
import com.zty.therapist.model.CommunityModel;
import com.zty.therapist.model.ResultBean;
import com.zty.therapist.recycler.FooterRefreshAdapter;
import com.zty.therapist.url.RequestManager;
import com.zty.therapist.url.Urls;
import com.zty.therapist.utils.ResultUtil;
import com.zty.therapist.utils.ToastUtils;

import java.util.List;

/**
 * 感情天地
 * Created by zty on 2016/12/17.
 */

public class CommunityActivity extends BaseRefreshActivity implements SendReplayListener {

    private static final int CODE_GET_LIST = 0;
    private static final int CODE_REPLAY = 1;

    @Override
    protected void initReadyData() {
        title.setText("感情天地");
        right.setBackgroundResource(R.mipmap.ic_publish);
    }

    @Override
    protected void setAdapter() {
        adapter = new CommunityAdapter(this, getSupportFragmentManager(), this);
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
        RequestManager.get(CODE_GET_LIST, Urls.getHealthForumList, params, this);
    }

    @Override
    public void onFailureCallback(int requestCode, String errorMsg) {

    }

    @Override
    public void onSuccessCallback(int requestCode, String response) {
        ResultBean resultBean = ResultUtil.getResult(response);
        if (resultBean.isSuccess()) {
            switch (requestCode) {
                case CODE_GET_LIST:
                    List<CommunityModel> models = new Gson().fromJson(resultBean.getResult(), new TypeToken<List<CommunityModel>>() {
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
                    break;
                case CODE_REPLAY:
                    break;
            }
        } else {
            ToastUtils.show(this, resultBean.getMsg());
        }

    }

    @Override
    public void rightClick() {
        startActivity(new Intent(CommunityActivity.this, PublishActivity.class));
    }

    @Override
    public void onSend(String forumId, String userId, int position, String message) {
        RequestParams params = new RequestParams();
        params.put("forumId", forumId);
        params.put("commentObject", userId);
        params.put("commentObject", userId);
        params.put("content", message);
        RequestManager.get(CODE_REPLAY, Urls.submitForumReplay, params, this);
    }
}
