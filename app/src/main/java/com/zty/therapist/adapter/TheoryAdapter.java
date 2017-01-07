package com.zty.therapist.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zty.therapist.R;
import com.zty.therapist.model.OptionModel;
import com.zty.therapist.recycler.NormalAdapter;
import com.zty.therapist.recycler.ViewHolder;
import com.zty.therapist.ui.activity.home.ClassRoomGridActivity;
import com.zty.therapist.ui.activity.home.TheoryListActivity;
import com.zty.therapist.utils.MyTextUtils;

/**
 * 康复知识分类、健康视频分类adapter
 * Created by zty on 2016/12/3.
 */

public class TheoryAdapter extends NormalAdapter<OptionModel> {

    private int type;//0：康复知识；1：健康视频

    public TheoryAdapter(Context context, int type) {
        super(context);
        this.type = type;
    }

    @Override
    protected void convert(RecyclerView.ViewHolder holder, final OptionModel model, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;

        if (type == 0) {
            if (model.getKey().equals("1")) {
                viewHolder.setBgRes(R.id.imgTrainLeft, R.mipmap.ic_theory1);
            } else if (model.getKey().equals("2")) {
                viewHolder.setBgRes(R.id.imgTrainLeft, R.mipmap.ic_theory2);
            } else if (model.getKey().equals("3")) {
                viewHolder.setBgRes(R.id.imgTrainLeft, R.mipmap.ic_theory3);
            } else if (model.getKey().equals("4")) {
                viewHolder.setBgRes(R.id.imgTrainLeft, R.mipmap.ic_theory4);
            } else if (model.getKey().equals("5")) {
                viewHolder.setBgRes(R.id.imgTrainLeft, R.mipmap.ic_theory5);
            }
        } else {
            viewHolder.setBgRes(R.id.imgTrainLeft, R.mipmap.ic_train_radio);
        }


        viewHolder.setText(R.id.textTrainTitle, MyTextUtils.isEmpty(model.getValue()));

        viewHolder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (type == 0) {
                    mContext.startActivity(new Intent(mContext, TheoryListActivity.class).putExtra("model", model));
                } else {
                    mContext.startActivity(new Intent(mContext, ClassRoomGridActivity.class).putExtra("model", model));
                }
            }
        });
    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.item_theory;
    }

}
