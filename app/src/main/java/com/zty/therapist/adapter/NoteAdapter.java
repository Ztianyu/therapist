package com.zty.therapist.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.zty.therapist.R;
import com.zty.therapist.model.NoteModel;
import com.zty.therapist.recycler.FooterRefreshAdapter;
import com.zty.therapist.recycler.ViewHolder;

/**
 * Created by zty on 2017/1/14.
 */

public class NoteAdapter extends FooterRefreshAdapter<NoteModel> {

    public NoteAdapter(Context context) {
        super(context);
    }

    @Override
    protected void convert(RecyclerView.ViewHolder holder, NoteModel noteModel) {
        ViewHolder viewHolder = (ViewHolder) holder;

        viewHolder.setText(R.id.textHelpTitle, noteModel.getTitle());
        viewHolder.setText(R.id.textHelpTime, noteModel.getCreateDate());
        viewHolder.setText(R.id.textHelpContent, noteModel.getContent());
    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.item_note;
    }
}
