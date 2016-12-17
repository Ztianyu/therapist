package com.zty.therapist.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.zty.therapist.R;
import com.zty.therapist.model.CaseModel;
import com.zty.therapist.recycler.ViewHolder;
import com.zty.therapist.utils.MyTextUtils;

import cn.droidlover.xrecyclerview.RecyclerAdapter;

/**
 * 案例
 * Created by zty on 2016/12/3.
 */

public class CaseAdapter extends RecyclerAdapter<CaseModel, ViewHolder> {
    public CaseAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder viewHolder = ViewHolder.create(context, R.layout.item_case, parent);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        convert(holder, data.get(position));
    }

    private void convert(ViewHolder holder, CaseModel caseModel) {
        holder.setText(R.id.textCaseTitle, MyTextUtils.isEmpty(caseModel.getTitle()));
        holder.setText(R.id.textCaseTime, MyTextUtils.isEmpty(caseModel.getTime()));
        holder.setText(R.id.textCaseContent, MyTextUtils.isEmpty(caseModel.getContent()));
    }
}
