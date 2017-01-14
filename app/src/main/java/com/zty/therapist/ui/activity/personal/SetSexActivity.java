package com.zty.therapist.ui.activity.personal;

import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.loopj.android.http.RequestParams;
import com.zty.therapist.R;
import com.zty.therapist.base.BaseActivity;
import com.zty.therapist.base.TherapistApplication;
import com.zty.therapist.model.ResultBean;
import com.zty.therapist.url.RequestManager;
import com.zty.therapist.url.Urls;
import com.zty.therapist.utils.ResultUtil;
import com.zty.therapist.utils.ToastUtils;
import com.zty.therapist.utils.UserUtils;

import butterknife.BindView;

/**
 * Created by zty on 2017/1/14.
 */

public class SetSexActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {

    @BindView(R.id.radioButtonMan)
    RadioButton radioButtonMan;
    @BindView(R.id.radioButtonWoman)
    RadioButton radioButtonWoman;
    @BindView(R.id.radioGroupSex)
    RadioGroup radioGroupSex;

    private String strSex;

    @Override
    protected int getContentView() {
        return R.layout.activity_set_sex;
    }

    @Override
    protected void initData() {
        title.setText("性 别");
        right.setText("保存");
        strSex = getIntent().getStringExtra("sex");

        if (strSex.equals("男")) {
            radioButtonMan.setChecked(true);
        } else {
            radioButtonWoman.setChecked(true);
        }
        radioGroupSex.setOnCheckedChangeListener(this);
    }

    @Override
    public void onFailureCallback(int requestCode, String errorMsg) {

    }

    @Override
    public void onSuccessCallback(int requestCode, String response) {
        ResultBean resultBean = ResultUtil.getResult(response);
        if (resultBean.isSuccess()) {
            if (requestCode == UserUtils.CODE_GET_MESSAGE) {
                TherapistApplication.getInstance().setCurrentUser(resultBean.getResult());
                finish();
            } else {
                ToastUtils.show(this, "修改成功");
                UserUtils.getUserMessage("", this);
            }
        } else {
            ToastUtils.show(this, resultBean.getResult());
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (i) {
            case R.id.radioButtonMan:
                strSex = "男";
                break;
            case R.id.radioButtonWoman:
                strSex = "女";
                break;
        }
    }

    @Override
    public void rightClick() {
        RequestParams params = new RequestParams();
        params.put("sex", strSex);
        RequestManager.post(-1, Urls.update, params, this);
    }
}
