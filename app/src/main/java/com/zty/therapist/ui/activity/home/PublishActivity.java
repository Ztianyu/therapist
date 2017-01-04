package com.zty.therapist.ui.activity.home;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;

import com.lling.photopicker.PhotoPickerActivity;
import com.zty.therapist.R;
import com.zty.therapist.adapter.PublishImageAdapter;
import com.zty.therapist.base.BaseActivity;
import com.zty.therapist.inter.OnSelectListener;
import com.zty.therapist.utils.PicUtils;
import com.zty.therapist.utils.ResourceUtil;
import com.zty.therapist.utils.SelectPicUtils;
import com.zty.therapist.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 发帖
 * Created by zty on 2016/12/19.
 */

public class PublishActivity extends BaseActivity implements View.OnClickListener, OnSelectListener {

    @BindView(R.id.editPublish)
    EditText editPublish;
    @BindView(R.id.gridPublish)
    GridView gridPublish;

    private PublishImageAdapter adapter;

    private List<String> mResults;

    private int picCount = 0;

    @Override
    protected int getContentView() {
        return R.layout.activity_publish;
    }

    @Override
    protected void initData() {

        title.setText("发 帖");
        left.setBackgroundResource(R.mipmap.ic_back);
        left.setOnClickListener(this);
        right.setBackgroundResource(R.mipmap.ic_setting);
        right.setOnClickListener(this);

        adapter = new PublishImageAdapter(this, getSupportFragmentManager());

        gridPublish.setAdapter(adapter);

        setData();
    }

    private void setData() {
        adapter.addListAtEnd(ResourceUtil.resToStr(this, R.string.add));
    }

    @Override
    public void onFailureCallback(int requestCode, String errorMsg) {

    }

    @Override
    public void onSuccessCallback(int requestCode, String response) {

    }

    @Override
    public void onTakePhoto() {
        SelectPicUtils.hidePicFragment(getSupportFragmentManager());
        SelectPicUtils.takePhoto(this);
    }

    @Override
    public void onPickPic() {
        SelectPicUtils.hidePicFragment(getSupportFragmentManager());

        if (picCount == 9) {
            ToastUtils.show(this, "最多只能选择9张照片");
        } else {
            SelectPicUtils.pickPic(this, PhotoPickerActivity.DEFAULT_NUM - picCount);
        }
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SelectPicUtils.PICK_PHOTO) {
            if (resultCode == RESULT_OK) {
                ArrayList<String> result = data.getStringArrayListExtra(PhotoPickerActivity.KEY_RESULT);
                showResult(result);
            }
        } else if (requestCode == SelectPicUtils.TAKE_PHOTO) {
            if (resultCode == RESULT_OK) {
                ArrayList<String> result = new ArrayList<>();
                result.add(PicUtils.photoPath.getPath());
                showResult(result);
            }
        }
    }

    private void showResult(ArrayList<String> result) {
        if (mResults == null) {
            mResults = new ArrayList<>();
        }
        mResults.remove(ResourceUtil.resToStr(this, R.string.add));
        mResults.addAll(result);

        if (mResults.size() < 9) {
            mResults.add(ResourceUtil.resToStr(this, R.string.add));
            picCount = mResults.size() - 1;
        } else {
            picCount = mResults.size();
        }

        adapter.setData(mResults);
    }
}
