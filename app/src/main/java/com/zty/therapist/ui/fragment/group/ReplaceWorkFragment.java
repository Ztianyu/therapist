package com.zty.therapist.ui.fragment.group;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.RequestParams;
import com.zty.therapist.adapter.ReplaceRecordAdapter;
import com.zty.therapist.base.BaseRefreshFragment;
import com.zty.therapist.base.TherapistApplication;
import com.zty.therapist.inter.OnReplaceListener;
import com.zty.therapist.model.ReplaceRecordModel;
import com.zty.therapist.model.ResultBean;
import com.zty.therapist.recycler.FooterRefreshAdapter;
import com.zty.therapist.url.RequestManager;
import com.zty.therapist.url.Urls;
import com.zty.therapist.utils.ResultUtil;
import com.zty.therapist.utils.ToastUtils;

import java.util.List;

/**
 * 替班
 * Created by tianyu on 2016/12/31.
 */

public class ReplaceWorkFragment extends BaseRefreshFragment implements OnReplaceListener {

    private static final int CODE_GET_SHIFT_RECORD = 0;
    private static final int CODE_GET_AUDIT_SHIFT_RECORD = 1;
    private static final int CODE_CANCEL = 2;
    private static final int CODE_AGREE = 3;
    private static final int CODE_DISAGREE = 4;
    private static final int CODE_GET_DETAIL = 5;

    private int role;
    private int position = -1;
    private String currentId = "";

    @Override
    protected void initReadyData() {
        role = TherapistApplication.getInstance().getRole();
    }

    @Override
    protected void setAdapter() {
        adapter = new ReplaceRecordAdapter(context, this);
    }

    @Override
    protected boolean isHaveDivider() {
        return true;
    }

    @Override
    public void onResume() {
        super.onResume();
        onRefresh();
    }

    private void getShiftRecordList() {
        RequestParams params = new RequestParams();
        params.put("pageNo", pageNo);
        RequestManager.get(CODE_GET_SHIFT_RECORD, Urls.getShiftRecordList, params, this);
    }

    private void getAuditShiftRecordList() {
        RequestParams params = new RequestParams();
        params.put("pageNo", pageNo);
        RequestManager.get(CODE_GET_AUDIT_SHIFT_RECORD, Urls.getAuditShiftRecordList, params, this);
    }

    private void confirmOrder(int code, int state, String id) {
        RequestParams params = new RequestParams();
        params.put("shiftId", id);
        params.put("confirmState", state);
        RequestManager.post(code, Urls.confirmOrder, params, this);
    }

    private void getDetail() {
        RequestParams params = new RequestParams();
        params.put("shiftId", currentId);
        RequestManager.get(CODE_GET_DETAIL, Urls.getShiftRecord, params, this);
    }

    @Override
    protected void fetchData() {
        if (role == 0) {
            getShiftRecordList();
        } else if (role == 1) {
            getAuditShiftRecordList();
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
                case CODE_GET_SHIFT_RECORD:
                case CODE_GET_AUDIT_SHIFT_RECORD:
                    List<ReplaceRecordModel> replaceRecordModels = new Gson().fromJson(resultBean.getResult(), new TypeToken<List<ReplaceRecordModel>>() {
                    }.getType());
                    swipeRefreshLayout.setRefreshing(false);
                    if (replaceRecordModels != null && replaceRecordModels.size() > 0) {
                        if (isLoadMore) {
                            if (replaceRecordModels.size() == 0) {
                                adapter.updateRefreshState(FooterRefreshAdapter.STATE_FINISH);
                            } else if (replaceRecordModels.size() < 10) {
                                adapter.notifyBottomRefresh(replaceRecordModels);
                                adapter.updateRefreshState(FooterRefreshAdapter.STATE_FINISH);
                            } else {
                                adapter.notifyBottomRefresh(replaceRecordModels);
                                isRefresh = false;
                                mTempPageCount++;
                            }
                        } else {
                            adapter.notifyTopRefresh(replaceRecordModels);
                        }
                    } else {
                        if (pageNo == 1) {
                            adapter.clearData();
                        }
                        adapter.updateRefreshState(FooterRefreshAdapter.STATE_FINISH);
                    }
                    break;
                case CODE_AGREE:
                case CODE_CANCEL:
                    getDetail();
                    break;
                case CODE_DISAGREE:
                    adapter.remove(position);
                    break;
                case CODE_GET_DETAIL:
                    ReplaceRecordModel model = new Gson().fromJson(resultBean.getResult(), ReplaceRecordModel.class);
                    if (model != null)
                        adapter.setData(model, position);
                    break;
            }
        } else {
            ToastUtils.show(context, resultBean.getMsg());
        }
    }

    @Override
    public void onAgree(String id, int position) {
        this.position = position;
        this.currentId = id;
        confirmOrder(CODE_AGREE, 1, id);
    }

    @Override
    public void onDisagree(String id, int position) {
        this.position = position;
        this.currentId = id;
        confirmOrder(CODE_DISAGREE, 0, id);
    }

    @Override
    public void onCancel(String id, int position) {
        this.position = position;
        this.currentId = id;
        RequestParams params = new RequestParams();
        params.put("shiftId", id);
        RequestManager.post(CODE_CANCEL, Urls.cancelRelay, params, this);
    }
}
