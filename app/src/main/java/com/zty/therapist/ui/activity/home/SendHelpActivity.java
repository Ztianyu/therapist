package com.zty.therapist.ui.activity.home;

import android.view.View;
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
 * Created by zty on 2017/1/4.
 */

public class SendHelpActivity extends BaseActivity implements View.OnClickListener {
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
        title.setText("发布信息");
        right.setText("发布");

//        editSendHelpTitle.setText("找工作");
//        editSendHelpContent.setText("硕士生、博士生想找个好工作?机会来了!昨日，记者从河南省大中专学生就业服务中心获悉，12月25日，省教育厅、省发改委、省工信委、省人社厅、省卫计委及省国资委6部门将联合开展“河南省2017届高校毕业研究生就业专场双选会”，为全省27所高等院校、科研院所的硕士、博士毕业生找“东家”。这一类型的招聘会在河南省属首次，在全国也不多见。");
//        editSendHelpName.setText("张海林");
//        editSendHelpPhone.setText("13578654126");
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
        RequestManager.post(-1, Urls.submitMutualHelp, params, this);
    }
}
