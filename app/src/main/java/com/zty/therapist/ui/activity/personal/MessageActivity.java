package com.zty.therapist.ui.activity.personal;


import android.content.Intent;
import android.view.View;

import com.zty.therapist.R;
import com.zty.therapist.base.BaseActivity;
import com.zty.therapist.widget.StripViewNoImg;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * 消息中心
 * Created by zty on 2016/12/26.
 */

public class MessageActivity extends BaseActivity {

    @BindView(R.id.textStripMessage1)
    StripViewNoImg textStripMessage1;
    @BindView(R.id.textStripMessage2)
    StripViewNoImg textStripMessage2;

    @Override
    protected int getContentView() {
        return R.layout.activity_message;
    }

    @Override
    protected void initData() {
        title.setText("消 息");

    }

    @Override
    public void onFailureCallback(int requestCode, String errorMsg) {

    }

    @Override
    public void onSuccessCallback(int requestCode, String response) {

    }

    @OnClick({R.id.textStripMessage1, R.id.textStripMessage2})
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.textStripMessage1:
                startActivity(new Intent(this, MessageListActivity.class).putExtra("type", 0));
                break;
            case R.id.textStripMessage2:
                startActivity(new Intent(this, MessageListActivity.class).putExtra("type", 1));
                break;
        }
    }
}
