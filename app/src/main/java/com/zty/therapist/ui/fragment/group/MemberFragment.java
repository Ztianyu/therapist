package com.zty.therapist.ui.fragment.group;

import com.zty.therapist.adapter.MemberAdapter;
import com.zty.therapist.base.BaseRefreshFragment;
import com.zty.therapist.model.MemberModel;

import java.util.ArrayList;
import java.util.List;

/**
 * 成员列表
 * Created by tianyu on 2016/12/31.
 */

public class MemberFragment extends BaseRefreshFragment {

    @Override
    protected void initReadyData() {

    }

    @Override
    protected void setAdapter() {
        adapter = new MemberAdapter(context);
    }

    @Override
    protected boolean isHaveDivider() {
        return true;
    }

    @Override
    protected void fetchData() {
        List<MemberModel> models = new ArrayList<>();
        MemberModel memberModel = new MemberModel();
        for (int i = 0; i < 5; i++) {
            models.add(memberModel);
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
