package com.zty.therapist.ui.fragment.group;


import com.zty.therapist.adapter.ReplaceRecordAdapter;
import com.zty.therapist.base.BaseRefreshFragment;
import com.zty.therapist.model.ReplaceRecordModel;

import java.util.ArrayList;
import java.util.List;

/**
 * 替班
 * Created by tianyu on 2016/12/31.
 */

public class ReplaceWorkFragment extends BaseRefreshFragment {
    @Override
    protected void initReadyData() {

    }

    @Override
    protected void setAdapter() {
        adapter = new ReplaceRecordAdapter(context);

    }

    @Override
    protected boolean isHaveDivider() {
        return true;
    }

    @Override
    protected void fetchData() {
        List<ReplaceRecordModel> models = new ArrayList<>();
        ReplaceRecordModel model = new ReplaceRecordModel();
        for (int i = 0; i < 5; i++) {
            models.add(model);
        }
        if (isLoadMore) {
            adapter.notifyBottomRefresh(models);
            mTempPageCount++;
        } else {
            adapter.notifyTopRefresh(models);
            swipeRefreshLayout.setRefreshing(false);
        }

    }

    @Override
    public void onFailureCallback(int requestCode, String errorMsg) {

    }

    @Override
    public void onSuccessCallback(int requestCode, String response) {

    }
}
