package com.zty.therapist.ui.activity.home;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.RequestParams;
import com.zty.therapist.adapter.MemberAdapter;
import com.zty.therapist.base.BaseNormalListActivity;
import com.zty.therapist.base.TherapistApplication;
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

    private static final int CODE_GROUP_MEMBER = 0;
    private static final int CODE_MEMBER_LIST = 1;
    private static final int CODE_GROUPS = 2;

    private int type;//0：组长的成员列表；10：未分组成员列表
    private int role;

    private String userId;

    @Override
    protected void initReadyData() {
        role = TherapistApplication.getInstance().getRole();
        type = getIntent().getIntExtra("type", 0);

        if (role == 3) {
            if (type == 1) {
                title.setText("组长列表");
                userId = getIntent().getStringExtra("userId");
            } else if (type == 2) {
                title.setText("成员列表");
                userId = getIntent().getStringExtra("userId");
            } else if (type == 10) {
                title.setText("未分组成员列表");
            }
        } else {
            title.setText("成员列表");
            userId = getIntent().getStringExtra("userId");
        }
    }

    @Override
    protected void setAdapter() {
        if (role == 3) {
            if (type == 1) {
                adapter = new MemberAdapter(this, 1);
            } else if (type == 2) {
                adapter = new MemberAdapter(this, 2);
            } else if (type == 10) {
                adapter = new MemberAdapter(this, 10);
            }
        } else {
            adapter = new MemberAdapter(this, 1);
        }
    }

    @Override
    protected boolean isHaveDivider() {
        return true;
    }

    @Override
    protected void fetchData() {
        if (role == 3) {
            if (type == 1) {
                RequestParams params = new RequestParams();
                params.put("userId", userId);
                RequestManager.get(CODE_GROUPS, Urls.getTeamGroupList, params, this);
            } else if (type == 2) {
                RequestParams params = new RequestParams();
                params.put("userId", userId);
                RequestManager.get(CODE_MEMBER_LIST, Urls.getGroupMemberList, params, this);
            } else if (type == 10) {
                RequestManager.get(CODE_MEMBER_LIST, Urls.getMemberList, null, this);
            }
        } else {
            RequestParams params = new RequestParams();
            params.put("userId", userId);
            RequestManager.get(CODE_GROUP_MEMBER, Urls.getGroupMemberList, params, this);
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
                case CODE_GROUP_MEMBER:
                case CODE_MEMBER_LIST:
                case CODE_GROUPS:
                    List<MemberModel> memberModels = new Gson().fromJson(resultBean.getResult(), new TypeToken<List<MemberModel>>() {
                    }.getType());

                    if (memberModels != null)
                        adapter.notifyTopRefresh(memberModels);
                    break;
            }
        } else {
            ToastUtils.show(this, resultBean.getMsg());
        }
    }

}
