package com.zty.therapist.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.zty.therapist.R;
import com.zty.therapist.model.LectureModel;
import com.zty.therapist.recycler.ViewHolder;
import com.zty.therapist.utils.MyTextUtils;

import cn.droidlover.xrecyclerview.RecyclerAdapter;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * Created by zty on 2016/12/3.
 */

public class LectureAdapter extends RecyclerAdapter<LectureModel, ViewHolder> {
    public LectureAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder viewHolder = ViewHolder.create(context, R.layout.item_lecture, parent);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        convert(holder, data.get(position));
    }

    private void convert(ViewHolder holder, final LectureModel lectureModel) {

        holder.setText(R.id.textLectureName, MyTextUtils.isEmpty(lectureModel.getTitle()));
        holder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JCVideoPlayerStandard.startFullscreen(context, JCVideoPlayerStandard.class, lectureModel.getVideoUrl(), lectureModel.getTitle());
            }
        });

    }
}
