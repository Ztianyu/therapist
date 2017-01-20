package com.zty.therapist.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.easemob.chat.EMGroup;
import com.zty.therapist.R;
import com.zty.therapist.recycler.NormalAdapter;
import com.zty.therapist.recycler.ViewHolder;

/**
 * Created by zty on 2017/1/20.
 */

public class ChatListAdapter extends NormalAdapter<EMGroup> {
    public ChatListAdapter(Context context) {
        super(context);
    }

    @Override
    protected void convert(RecyclerView.ViewHolder holder, EMGroup emGroup, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;

        viewHolder.setText(R.id.textStrip, emGroup.getGroupName());

    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.item_chat_list;
    }
}
