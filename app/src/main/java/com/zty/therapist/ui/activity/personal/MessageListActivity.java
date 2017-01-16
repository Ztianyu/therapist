package com.zty.therapist.ui.activity.personal;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.RequestParams;
import com.zty.therapist.adapter.NoteAdapter;
import com.zty.therapist.base.BaseRefreshActivity;
import com.zty.therapist.base.TherapistApplication;
import com.zty.therapist.model.NoteModel;
import com.zty.therapist.model.ResultBean;
import com.zty.therapist.recycler.FooterRefreshAdapter;
import com.zty.therapist.url.RequestManager;
import com.zty.therapist.url.Urls;
import com.zty.therapist.utils.ResultUtil;
import com.zty.therapist.utils.ToastUtils;

import java.util.List;

/**
 * Created by tianyu on 2017/1/15.
 */

public class MessageListActivity extends BaseRefreshActivity {

    private static final int CODE_NOTE = 0;
    private static final int CODE_MESSAGE = 1;

    private int role;
    private int type;

    @Override
    protected void initReadyData() {
        if (type == 0) {
            title.setText("通知公告");
        } else {
            title.setText("班组通知");
        }
        type = getIntent().getIntExtra("type", 0);
        role = TherapistApplication.getInstance().getRole();
    }

    @Override
    protected void setAdapter() {
        if (type == 0) {
            adapter = new NoteAdapter(this);
        } else {

        }

    }

    @Override
    protected boolean isHaveDivider() {
        return false;
    }

    @Override
    protected void fetchData() {
        if (type == 0) {
            RequestParams params = new RequestParams();
            params.put("role", role);
            params.put("pageNo", pageNo);
            RequestManager.get(CODE_NOTE, Urls.getInviteContent, params, this);
        } else {
            RequestParams params = new RequestParams();
            params.put("role", role);
            params.put("pageNo", pageNo);
            RequestManager.get(CODE_MESSAGE, Urls.getInviteInformList, params, this);
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
                case CODE_NOTE:
                    List<NoteModel> models = new Gson().fromJson(resultBean.getResult(), new TypeToken<List<NoteModel>>() {
                    }.getType());

                    swipeRefreshLayout.setRefreshing(false);
                    if (models != null && models.size() > 0) {
                        if (isLoadMore) {
                            if (models.size() == 0) {
                                adapter.updateRefreshState(FooterRefreshAdapter.STATE_FINISH);
                            } else if (models.size() < 10) {
                                adapter.notifyBottomRefresh(models);
                                adapter.updateRefreshState(FooterRefreshAdapter.STATE_FINISH);
                            } else {
                                adapter.notifyBottomRefresh(models);
                                isRefresh = false;
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
                case CODE_MESSAGE:
                    break;
            }
        } else {
            ToastUtils.show(this, resultBean.getMsg());
        }

    }
}
