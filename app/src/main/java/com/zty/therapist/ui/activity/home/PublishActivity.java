package com.zty.therapist.ui.activity.home;

import android.content.Intent;
import android.widget.EditText;
import android.widget.GridView;

import com.lling.photopicker.PhotoPickerActivity;
import com.loopj.android.http.RequestParams;
import com.zty.therapist.R;
import com.zty.therapist.adapter.PublishImageAdapter;
import com.zty.therapist.base.BaseActivity;
import com.zty.therapist.inter.OnSelectListener;
import com.zty.therapist.model.ResultBean;
import com.zty.therapist.url.RequestManager;
import com.zty.therapist.url.Urls;
import com.zty.therapist.utils.PicUtils;
import com.zty.therapist.utils.ResourceUtil;
import com.zty.therapist.utils.ResultUtil;
import com.zty.therapist.utils.SelectPicUtils;
import com.zty.therapist.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 发帖
 * Created by zty on 2016/12/19.
 */

public class PublishActivity extends BaseActivity implements OnSelectListener {

    private static final int CODE_SEND_SUBMIT = 0;


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
        right.setBackgroundResource(R.mipmap.ic_send_info);

        adapter = new PublishImageAdapter(this, getSupportFragmentManager());

        gridPublish.setAdapter(adapter);

        setData();
    }

    private void setData() {
        adapter.addListAtEnd(ResourceUtil.resToStr(this, R.string.add));
    }

    private void submitCard() {
        RequestParams params = new RequestParams();
        params.put("content", editPublish.getText().toString());
        RequestManager.get(CODE_SEND_SUBMIT, Urls.submitForum, params, this);
    }

    @Override
    public void onFailureCallback(int requestCode, String errorMsg) {

    }

    @Override
    public void onSuccessCallback(int requestCode, String response) {
        ResultBean resultBean = ResultUtil.getResult(response);
        if (resultBean.isSuccess()) {
            switch (requestCode) {
                case CODE_SEND_SUBMIT:
                    ToastUtils.show(this, "发布成功");
                    break;
            }
        } else {
            ToastUtils.show(this, resultBean.getResult());
        }

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
    public void rightClick() {
        submitCard();
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
