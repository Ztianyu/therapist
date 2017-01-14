package com.zty.therapist.ui.activity.home;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.RequestParams;
import com.zty.therapist.adapter.MemberAdapter;
import com.zty.therapist.base.BaseNormalListActivity;
import com.zty.therapist.model.MemberModel;
import com.zty.therapist.model.ResultBean;
import com.zty.therapist.url.RequestManager;
import com.zty.therapist.url.Urls;
import com.zty.therapist.utils.ResultUtil;
import com.zty.therapist.utils.ToastUtils;

import java.util.List;


/**
 * Created by zty on 2017/1/14.
 */

public class MemberListActivity extends BaseNormalListActivity {

    private String userId;

    @Override
    protected void initReadyData() {
        title.setText("成员列表");
        userId = getIntent().getStringExtra("userId");
    }

    @Override
    protected void setAdapter() {
        adapter = new MemberAdapter(this, 1);
    }

    @Override
    protected boolean isHaveDivider() {
        return true;
    }

    @Override
    protected void fetchData() {
        RequestParams params = new RequestParams();
        params.put("userId", userId);
        RequestManager.get(-1, Urls.getGroupMemberList, params, this);
    }

    @Override
    public void onFailureCallback(int requestCode, String errorMsg) {

    }

    @Override
    public void onSuccessCallback(int requestCode, String response) {
        ResultBean resultBean = ResultUtil.getResult(response);
        if (resultBean.isSuccess()) {
            List<MemberModel> memberModels = new Gson().fromJson(resultBean.getResult(), new TypeToken<List<MemberModel>>() {
            }.getType());

            if (memberModels != null)
                adapter.notifyTopRefresh(memberModels);
        } else {
            ToastUtils.show(this, resultBean.getMsg());
        }
    }

}
