package com.zty.therapist.ui.fragment.group;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.RequestParams;
import com.zty.therapist.adapter.MemberAdapter;
import com.zty.therapist.base.BaseNormalListFragment;
import com.zty.therapist.model.MemberModel;
import com.zty.therapist.model.ResultBean;
import com.zty.therapist.url.RequestManager;
import com.zty.therapist.url.Urls;
import com.zty.therapist.utils.ResultUtil;
import com.zty.therapist.utils.ToastUtils;

import java.util.List;

/**
 * 成员列表
 * Created by tianyu on 2016/12/31.
 */

public class MemberFragment extends BaseNormalListFragment {

    @Override
    public void onResume() {
        super.onResume();
        getGroupMemberList();
    }

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
    }

    private void getGroupMemberList() {
        RequestParams params = new RequestParams();
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
            ToastUtils.show(context, resultBean.getMsg());
        }

    }
}
