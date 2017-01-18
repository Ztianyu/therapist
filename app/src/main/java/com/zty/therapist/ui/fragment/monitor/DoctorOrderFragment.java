package com.zty.therapist.ui.fragment.monitor;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.RequestParams;
import com.zty.therapist.adapter.DoctorHandleAdapter;
import com.zty.therapist.base.BaseRefreshFragment;
import com.zty.therapist.inter.OnConfirmListener;
import com.zty.therapist.inter.OnHandleListener;
import com.zty.therapist.inter.OnSureListener;
import com.zty.therapist.model.DoctorOrderModel;
import com.zty.therapist.model.ResultBean;
import com.zty.therapist.recycler.FooterRefreshAdapter;
import com.zty.therapist.url.RequestManager;
import com.zty.therapist.url.Urls;
import com.zty.therapist.utils.DialogUtils;
import com.zty.therapist.utils.ResultUtil;
import com.zty.therapist.utils.ToastUtils;

import java.util.List;

/**
 * Created by zty on 2017/1/14.
 */

public class DoctorOrderFragment extends BaseRefreshFragment implements OnHandleListener {

    private static final int CODE_CONFIRM = 0;
    private static final int CODE_SURE = 1;
    private static final int CODE_SET = 2;
    private static final int CODE_GET_DETAIL = 3;
    private static final int CODE_GET_LIST = 4;

    private String id;
    private int position;

    @Override
    protected void initReadyData() {

    }

    @Override
    protected void setAdapter() {
        adapter = new DoctorHandleAdapter(context, this);
    }

    @Override
    protected boolean isHaveDivider() {
        return false;
    }

    @Override
    protected void fetchData() {
        RequestParams params = new RequestParams();
        params.put("pageNo", pageNo);
        RequestManager.get(CODE_GET_LIST, Urls.getDoctorOrderList, params, this);
    }

    private void getDoctorOrder() {
        RequestParams params = new RequestParams();
        params.put("orderId", id);
        RequestManager.get(CODE_GET_DETAIL, Urls.getDoctorOrder, params, this);
    }

    @Override
    public void onFailureCallback(int requestCode, String errorMsg) {

    }

    @Override
    public void onSuccessCallback(int requestCode, String response) {
        ResultBean resultBean = ResultUtil.getResult(response);
        if (resultBean.isSuccess()) {
            switch (requestCode) {
                case CODE_GET_LIST:
                    List<DoctorOrderModel> models = new Gson().fromJson(resultBean.getResult(), new TypeToken<List<DoctorOrderModel>>() {
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
                    break;
                case CODE_CONFIRM:
                case CODE_SURE:
                    ToastUtils.show(context, "处理成功");
                    getDoctorOrder();
                    break;
                case CODE_GET_DETAIL:
                    DoctorOrderModel model = new Gson().fromJson(resultBean.getResult(), DoctorOrderModel.class);
                    if (model != null) {
                        adapter.setData(model, position);
                    }
                    break;
            }
        } else {
            ToastUtils.show(context, resultBean.getMsg());
        }

    }

    @Override
    public void onHandleConfirm(final String id, int position) {
        this.id = id;
        this.position = position;
        DialogUtils.confirm(context, new OnConfirmListener() {
            @Override
            public void onConfirmSure(String content) {
                RequestParams params = new RequestParams();
                params.put("id", id);
                params.put("transactorMethod", content);
                RequestManager.post(CODE_CONFIRM, Urls.processOrder, params, DoctorOrderFragment.this);
            }
        });
    }

    @Override
    public void onHandleSure(final String id, int position) {
        this.id = id;
        this.position = position;
        DialogUtils.sure(context, new OnSureListener() {
            @Override
            public void onSure(int code, String content) {
                RequestParams params = new RequestParams();
                params.put("state", id);
                params.put("id", code);
                params.put("confirmExplain", content);
                RequestManager.post(CODE_SURE, Urls.processOrder, params, DoctorOrderFragment.this);
            }
        });

    }

    @Override
    public void onSetTransactor(String id, int position) {
        this.id = id;
        this.position = position;

    }
}
