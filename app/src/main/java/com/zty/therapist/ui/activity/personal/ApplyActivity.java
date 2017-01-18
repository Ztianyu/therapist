package com.zty.therapist.ui.activity.personal;

import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.zty.therapist.R;
import com.zty.therapist.base.BaseActivity;
import com.zty.therapist.config.Config;
import com.zty.therapist.model.MessageModel;
import com.zty.therapist.model.NoteModel;
import com.zty.therapist.model.ResultBean;
import com.zty.therapist.service.InviteInformUtils;
import com.zty.therapist.url.RequestManager;
import com.zty.therapist.url.Urls;
import com.zty.therapist.utils.ResultUtil;
import com.zty.therapist.utils.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by zty on 2016/12/27.
 */

public class ApplyActivity extends BaseActivity implements View.OnClickListener {
    private static final int CODE_GET_APPLY = 0;
    private static final int CODE_GET_CONTENT = 1;
    @BindView(R.id.textApplyNote)
    TextView textApplyNote;
    @BindView(R.id.btnApply)
    TextView btnApply;
    @BindView(R.id.textApplyResult)
    TextView textApplyResult;

    @Override
    protected int getContentView() {
        return R.layout.activity_apply;
    }

    @Override
    protected void initData() {
        title.setText("组长申请");
        getInviteContent();
        getInviteInformGroup();
    }

    @Override
    public void onFailureCallback(int requestCode, String errorMsg) {

    }

    @Override
    public void onSuccessCallback(int requestCode, String response) {
        ResultBean resultBean = ResultUtil.getResult(response);
        if (resultBean.isSuccess()) {
            switch (requestCode) {
                case CODE_GET_APPLY:
                    MessageModel messageModel = new Gson().fromJson(resultBean.getResult(), MessageModel.class);
                    if (messageModel != null) {
                        if (messageModel.getState() == 0) {
                            textApplyResult.setText("审批中，请等待……");
                            btnApply.setVisibility(View.INVISIBLE);
                        } else if (messageModel.getState() == 1) {
                            textApplyResult.setText("您已申请通过，现在可以管理您的组员了。");
                            btnApply.setVisibility(View.INVISIBLE);
                        } else if (messageModel.getState() == 2) {
                            textApplyResult.setText("您的申请已被拒绝");
                            btnApply.setVisibility(View.VISIBLE);
                        }
                    }
                    break;
                case Config.CODE_SUBMIT_INCITE_INFORM_3:
                    ToastUtils.show(this, "申请成功");
                    getInviteInformGroup();
                    break;
                case CODE_GET_CONTENT:
                    NoteModel noteModel = new Gson().fromJson(resultBean.getResult(), NoteModel.class);
                    if (noteModel != null)
                        textApplyNote.setText("　　" + noteModel.getContent());
                    break;
            }
        } else {
            ToastUtils.show(this, resultBean.getMsg());
        }
    }

    private void getInviteContent() {
        RequestParams params = new RequestParams();
        params.put("type", 3);
        RequestManager.get(CODE_GET_CONTENT, Urls.getInviteContent, params, this);
    }

    private void getInviteInformGroup() {
        RequestParams params = new RequestParams();
        RequestManager.get(CODE_GET_APPLY, Urls.getInviteInformGroup, params, this);
    }

    private void apply() {
        InviteInformUtils.submitInviteInform(Config.CODE_SUBMIT_INCITE_INFORM_3, 3, "", "", 0, this);
    }

    @OnClick(R.id.btnApply)
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.btnApply:
                apply();
                break;
        }
    }
}
