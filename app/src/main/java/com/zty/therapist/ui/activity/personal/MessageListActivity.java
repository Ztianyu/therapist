package com.zty.therapist.ui.activity.personal;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.RequestParams;
import com.zty.therapist.adapter.MessageAdapter;
import com.zty.therapist.adapter.NoteAdapter;
import com.zty.therapist.base.BaseRefreshActivity;
import com.zty.therapist.base.TherapistApplication;
import com.zty.therapist.inter.DialogListener;
import com.zty.therapist.inter.OnMessageListener;
import com.zty.therapist.model.MessageModel;
import com.zty.therapist.model.NoteModel;
import com.zty.therapist.model.ResultBean;
import com.zty.therapist.recycler.FooterRefreshAdapter;
import com.zty.therapist.url.RequestManager;
import com.zty.therapist.url.Urls;
import com.zty.therapist.utils.DialogUtils;
import com.zty.therapist.utils.ResultUtil;
import com.zty.therapist.utils.ToastUtils;

import java.util.List;

/**
 * Created by tianyu on 2017/1/15.
 */

public class MessageListActivity extends BaseRefreshActivity implements OnMessageListener, DialogListener {

    private static final int CODE_NOTE = 0;
    private static final int CODE_MESSAGE = 1;
    private static final int CODE_UPDATE_STATE = 2;
    private static final int CODE_GET_DETAIL = 3;

    private int role;
    private int type;

    private int handleType = -1;
    private int handlePosition = -1;
    private String inviteId;
    private boolean isAgree = false;

    @Override
    protected void initReadyData() {
        if (type == 0) {
            title.setText("通知公告");
        } else {
            title.setText("班组通知");
        }
        type = getIntent().getIntExtra("type", 0);
        role = TherapistApplication.getInstance().getRole();
    }

    @Override
    protected void setAdapter() {
        if (type == 0) {
            adapter = new NoteAdapter(this);
        } else {
            adapter = new MessageAdapter(this, this);
        }
    }

    @Override
    protected boolean isHaveDivider() {
        return false;
    }

    @Override
    protected void fetchData() {
        if (type == 0) {
            RequestParams params = new RequestParams();
            params.put("pageNo", pageNo);
            RequestManager.get(CODE_NOTE, Urls.getRehabilitationBulletinList, params, this);
        } else {
            RequestParams params = new RequestParams();
            params.put("pageNo", pageNo);
            RequestManager.get(CODE_MESSAGE, Urls.getInviteInformList, params, this);
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
                case CODE_NOTE:
                    List<NoteModel> models = new Gson().fromJson(resultBean.getResult(), new TypeToken<List<NoteModel>>() {
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
                case CODE_MESSAGE:
                    List<MessageModel> messageModels = new Gson().fromJson(resultBean.getResult(), new TypeToken<List<MessageModel>>() {
                    }.getType());

                    swipeRefreshLayout.setRefreshing(false);
                    if (messageModels != null && messageModels.size() > 0) {
                        if (isLoadMore) {
                            if (messageModels.size() == 0) {
                                adapter.updateRefreshState(FooterRefreshAdapter.STATE_FINISH);
                            } else if (messageModels.size() < 10) {
                                adapter.notifyBottomRefresh(messageModels);
                                adapter.updateRefreshState(FooterRefreshAdapter.STATE_FINISH);
                            } else {
                                adapter.notifyBottomRefresh(messageModels);
                                isRefresh = false;
                                mTempPageCount++;
                            }
                        } else {
                            adapter.notifyTopRefresh(messageModels);
                        }
                    } else {
                        if (pageNo == 1) {
                            adapter.clearData();
                        }
                        adapter.updateRefreshState(FooterRefreshAdapter.STATE_FINISH);
                    }
                    break;
                case CODE_UPDATE_STATE:
                    if (isAgree) {
                        ToastUtils.show(this, "已同意");
                    } else {
                        ToastUtils.show(this, "已拒绝");
                    }
                    getInviteInform();
                    break;
                case CODE_GET_DETAIL:
                    MessageModel messageModel = new Gson().fromJson(resultBean.getResult(), MessageModel.class);

                    if (messageModel != null) {
                        adapter.setData(messageModel, handlePosition);
                    }
                    break;
            }
        } else {
            ToastUtils.show(this, resultBean.getMsg());
        }

    }

    @Override
    public void onAgree(int type, String id, int position) {
        handleType = type;
        handlePosition = position;
        inviteId = id;
        isAgree = true;
        DialogUtils.show(this, "是否同意加入？", this);
    }

    @Override
    public void onDisAgree(int type, String id, int position) {
        handleType = type;
        handlePosition = position;
        inviteId = id;
        isAgree = false;
        DialogUtils.show(this, "是否拒绝加入？", this);
    }

    private void updateState() {
        RequestParams params = new RequestParams();
        params.put("inviteId", inviteId);
        if (isAgree) {
            params.put("state", 1);
        } else {
            params.put("state", 2);
        }
        RequestManager.post(CODE_UPDATE_STATE, Urls.updateState, params, this);
    }

    private void getInviteInform() {
        RequestParams params = new RequestParams();
        params.put("inviteId", inviteId);
        RequestManager.get(CODE_GET_DETAIL, Urls.getInviteInform, params, this);
    }

    @Override
    public void onSure() {
        updateState();
    }
}
