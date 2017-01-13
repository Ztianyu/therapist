package com.zty.therapist.ui.activity.home;

import android.widget.EditText;

import com.loopj.android.http.RequestParams;
import com.zty.therapist.R;
import com.zty.therapist.base.BaseActivity;
import com.zty.therapist.model.ResultBean;
import com.zty.therapist.url.RequestManager;
import com.zty.therapist.url.Urls;
import com.zty.therapist.utils.ResultUtil;
import com.zty.therapist.utils.ToastUtils;

import butterknife.BindView;

/**
 * Created by tianyu on 2017/1/2.
 */

public class SendHouseRentActivity extends BaseActivity {
    @BindView(R.id.editSendHelpTitle)
    EditText editSendHelpTitle;
    @BindView(R.id.editSendHelpContent)
    EditText editSendHelpContent;
    @BindView(R.id.editSendHelpName)
    EditText editSendHelpName;
    @BindView(R.id.editSendHelpPhone)
    EditText editSendHelpPhone;

    @Override
    protected int getContentView() {
        return R.layout.activity_send_help;
    }

    @Override
    protected void initData() {
        title.setText("房屋租赁");
        right.setText("发布");
    }

    @Override
    public void onFailureCallback(int requestCode, String errorMsg) {

    }

    @Override
    public void onSuccessCallback(int requestCode, String response) {
        ResultBean resultBean = ResultUtil.getResult(response);
        if (resultBean.isSuccess()) {
            ToastUtils.show(this, "发布成功");
            finish();
        } else {
            ToastUtils.show(this, resultBean.getMsg());
        }
    }

    @Override
    public void rightClick() {
        RequestParams params = new RequestParams();
        params.put("title", editSendHelpTitle.getText().toString());
        params.put("content", editSendHelpContent.getText().toString());
        params.put("contacts", editSendHelpName.getText().toString());
        params.put("contactsNumber", editSendHelpPhone.getText().toString());
        RequestManager.post(-1, Urls.submitHouseRent, params, this);
    }
}
