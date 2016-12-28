package com.zty.therapist.ui.activity.home;

import android.view.View;
import android.widget.EditText;

import com.zty.therapist.R;
import com.zty.therapist.adapter.PublishImageAdapter;
import com.zty.therapist.base.BaseActivity;
import com.zty.therapist.inter.OnSelectListener;
import com.zty.therapist.utils.ResourceUtil;
import com.zty.therapist.utils.ToastUtils;
import com.zty.therapist.widget.MyRecyclerView;

import butterknife.BindView;

/**
 * 发帖
 * Created by zty on 2016/12/19.
 */

public class PublishActivity extends BaseActivity implements View.OnClickListener, OnSelectListener {
    @BindView(R.id.editPublish)
    EditText editPublish;
    @BindView(R.id.gridPublish)
    MyRecyclerView gridPublish;

    private PublishImageAdapter adapter;

    @Override
    protected int getContentView() {
        return R.layout.activity_publish;
    }

    @Override
    protected void initData() {

        title.setText("发 帖");
        left.setBackgroundResource(R.mipmap.ic_back);
        left.setOnClickListener(this);
        right.setBackgroundResource(R.mipmap.ic_publish);
        right.setOnClickListener(this);

        adapter = new PublishImageAdapter(this, getSupportFragmentManager());
        gridPublish.gridLayoutManager(this, 4);
        gridPublish.horizontalDivider(R.color.transparent, R.dimen.gridSpace);

        gridPublish.setAdapter(adapter);

        setData();
    }

    private void setData() {

        adapter.addElement(ResourceUtil.resToStr(this, R.string.add));

    }

    @Override
    public void onFailureCallback(int requestCode, String errorMsg) {

    }

    @Override
    public void onSuccessCallback(int requestCode, String response) {

    }

    @Override
    public void onTakePhoto() {
        ToastUtils.show(this, "takePhoto");
    }

    @Override
    public void onPickPic() {
        ToastUtils.show(this, "pickPic");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.titleLeft:
                finish();
                break;
            case R.id.titleRight:
                break;
        }
    }
}
