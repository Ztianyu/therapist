package com.zty.therapist.ui.fragment.monitor;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.RequestParams;
import com.zty.therapist.adapter.NoteAdapter;
import com.zty.therapist.base.BaseRefreshFragment;
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
 * Created by zty on 2017/1/14.
 */

public class NoteFragment extends BaseRefreshFragment {

    private int role;

    @Override
    protected void initReadyData() {
        role = TherapistApplication.getInstance().getRole();
    }

    @Override
    protected void setAdapter() {
        adapter = new NoteAdapter(context);
    }

    @Override
    protected boolean isHaveDivider() {
        return false;
    }

    @Override
    protected void fetchData() {
        RequestParams params = new RequestParams();
        params.put("role", role);
        params.put("pageNo", pageNo);
        RequestManager.get(-1, Urls.getRehabilitationBulletinList, params, this);
    }

    @Override
    public void onFailureCallback(int requestCode, String errorMsg) {

    }

    @Override
    public void onSuccessCallback(int requestCode, String response) {
        ResultBean resultBean = ResultUtil.getResult(response);
        if (resultBean.isSuccess()) {
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
        } else {
            ToastUtils.show(context, resultBean.getMsg());
        }
    }
}
