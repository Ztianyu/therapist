package com.zty.therapist.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.zty.therapist.R;
import com.zty.therapist.model.TestModel;
import com.zty.therapist.recycler.NormalAdapter;
import com.zty.therapist.recycler.ViewHolder;
import com.zty.therapist.utils.DownloadUtils;
import com.zty.therapist.utils.MyTextUtils;


/**
 * 试题列表
 * Created by zty on 2016/12/3.
 */

public class TestAdapter extends NormalAdapter<TestModel> {

    public TestAdapter(Context context) {
        super(context);
    }

    @Override
    protected void convert(RecyclerView.ViewHolder holder, final TestModel testModel, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.setText(R.id.textTrainTestTitle, MyTextUtils.isEmpty(testModel.getTitle()));
        viewHolder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DownloadUtils.downPdf(mContext, testModel.getQuestionUrl(), testModel.getTitle());
            }
        });
        viewHolder.setText(R.id.textTestNum, (position + 1) + "");
    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.item_test_title;
    }

}
