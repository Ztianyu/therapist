package com.zty.therapist.ui.fragment.group;

import com.zty.therapist.adapter.HouseRentAdapter;
import com.zty.therapist.base.BaseRefreshFragment;
import com.zty.therapist.model.HouseRentModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tianyu on 2016/12/31.
 */

public class HouseRentFragment extends BaseRefreshFragment {
    @Override
    protected void initReadyData() {

    }

    @Override
    protected void setAdapter() {
        adapter = new HouseRentAdapter(context);

    }

    @Override
    protected boolean isHaveDivider() {
        return false;
    }

    @Override
    protected void fetchData() {
        List<HouseRentModel> models = new ArrayList<>();
        HouseRentModel model = new HouseRentModel();
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
