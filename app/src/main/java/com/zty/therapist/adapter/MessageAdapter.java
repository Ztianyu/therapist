package com.zty.therapist.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.zty.therapist.R;
import com.zty.therapist.base.TherapistApplication;
import com.zty.therapist.inter.OnMessageListener;
import com.zty.therapist.model.MessageModel;
import com.zty.therapist.recycler.FooterRefreshAdapter;
import com.zty.therapist.recycler.ViewHolder;
import com.zty.therapist.utils.MyTextUtils;

/**
 * Created by zty on 2016/12/27.
 */

public class MessageAdapter extends FooterRefreshAdapter<MessageModel> {

    private static final int TYPE_ITEM1 = 0;
    private static final int TYPE_ITEM2 = 1;
    private static final int TYPE_FOOTER = 2;

    OnMessageListener listener;

    private int role;

    public MessageAdapter(Context context, OnMessageListener listener) {
        super(context);
        this.listener = listener;
        role = TherapistApplication.getInstance().getRole();
    }

    @Override
    public int getItemViewType(int position) {

        if (position == mData.size()) {
            return TYPE_FOOTER;
        } else {
            if (mData.get(position).getState() == 0) {
                return TYPE_ITEM2;
            } else {
                return TYPE_ITEM1;
            }
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM1) {
            ViewHolder viewHolder = ViewHolder.create(mContext, R.layout.item_message1, parent);
            return viewHolder;
        } else if (viewType == TYPE_ITEM2) {
            ViewHolder viewHolder = ViewHolder.create(mContext, R.layout.item_message2, parent);
            return viewHolder;
        } else {
            mFooterViewHolder = ViewHolder.create(mContext, R.layout.footer_loading, parent);
            return mFooterViewHolder;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        int viewType = getItemViewType(position);
        if (viewType == TYPE_ITEM1) {
            ViewHolder viewHolder1 = (ViewHolder) holder;
            viewHolder1.setText(R.id.textMessageTitle1, mData.get(position).getTitle());
            viewHolder1.setText(R.id.textMessageContent1, MyTextUtils.isEmpty(mData.get(position).getTeacherNm()) + "邀请您加入，" + mData.get(position).getContent());

            int state = mData.get(position).getState();
            if (state == 1) {
                viewHolder1.setText(R.id.textMessageState, "已同意");
            } else if (state == 2) {
                viewHolder1.setText(R.id.textMessageState, "未同意");
            } else if (state == 3) {
                viewHolder1.setText(R.id.textMessageState, "已退出");
            }
        } else if (viewType == TYPE_ITEM2) {
            ViewHolder viewHolder2 = (ViewHolder) holder;
            viewHolder2.setText(R.id.textMessageTitle2, mData.get(position).getTitle());
            viewHolder2.setText(R.id.textMessageContent2, mData.get(position).getContent());

            if (role != 3) {
                if (mData.get(position).getType() == 3) {
                    viewHolder2.getView(R.id.btnAgree).setVisibility(View.INVISIBLE);
                    viewHolder2.getView(R.id.btnNoAgree).setVisibility(View.INVISIBLE);
                }
            } else {
                viewHolder2.getView(R.id.btnAgree).setVisibility(View.VISIBLE);
                viewHolder2.getView(R.id.btnNoAgree).setVisibility(View.VISIBLE);
            }

            viewHolder2.setOnClick(R.id.btnAgree, new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onAgree(mData.get(position).getType(), mData.get(position).getId(), position);
                }
            });
            viewHolder2.setOnClick(R.id.btnNoAgree, new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onDisAgree(mData.get(position).getType(), mData.get(position).getId(), position);
                }
            });
        } else {
            return;
        }
    }

    @Override
    protected void convert(RecyclerView.ViewHolder holder, MessageModel messageModel) {

    }

    @Override
    protected int getItemLayoutId() {
        return 0;
    }
}
