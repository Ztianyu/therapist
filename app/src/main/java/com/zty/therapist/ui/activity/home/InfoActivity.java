package com.zty.therapist.ui.activity.home;

import android.content.Intent;

import com.zty.therapist.R;
import com.zty.therapist.adapter.InfoAdapter;
import com.zty.therapist.base.BaseRefreshActivity;
import com.zty.therapist.model.InfoModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zty on 2016/12/17.
 */

public class InfoActivity extends BaseRefreshActivity {


    @Override
    protected void initReadyData() {
        title.setText("同城活动");
        right.setBackgroundResource(R.mipmap.ic_send_info);
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
    protected void fetchData() {
        List<InfoModel> models = new ArrayList<>();
        InfoModel model = new InfoModel();
        models.add(model);
        models.add(model);
        models.add(model);
        models.add(model);

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

    @Override
    public void rightClick() {
        startActivity(new Intent(this, SendInfoActivity.class));
    }
}
