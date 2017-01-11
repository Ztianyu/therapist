package com.zty.therapist.ui.activity.home;

import android.content.Intent;

import com.zty.therapist.R;
import com.zty.therapist.adapter.HelpAdapter;
import com.zty.therapist.base.BaseRefreshActivity;
import com.zty.therapist.model.HelpModel;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by zty on 2016/12/17.
 */

public class HelpActivity extends BaseRefreshActivity {


    @Override
    protected void initReadyData() {
        title.setText("互帮互助");
        setRight(R.mipmap.ic_publish);

    }

    @Override
    protected void setAdapter() {
        adapter = new HelpAdapter(this);
    }

    @Override
    protected boolean isHaveDivider() {
        return true;
    }

    @Override
    protected void fetchData() {
        List<HelpModel> models = new ArrayList<>();
        HelpModel model = new HelpModel();
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
        startActivity(new Intent(this, SendHelpActivity.class));
    }
}
