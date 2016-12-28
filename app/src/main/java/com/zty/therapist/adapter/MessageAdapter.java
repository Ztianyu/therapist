package com.zty.therapist.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.zty.therapist.R;
import com.zty.therapist.model.MessageModel;
import com.zty.therapist.recycler.FooterRefreshAdapter;
import com.zty.therapist.recycler.ViewHolder;

/**
 * Created by zty on 2016/12/27.
 */

public class MessageAdapter extends FooterRefreshAdapter<MessageModel> {

    private static final int TYPE_ITEM1 = 0;
    private static final int TYPE_ITEM2 = 1;
    private static final int TYPE_FOOTER = 2;


    public MessageAdapter(Context context) {
        super(context);
    }

    @Override
    public int getItemViewType(int position) {

        if (position == mData.size()) {
            return TYPE_FOOTER;
        } else {
            if (mData.get(position).getType() == 0) {
                return TYPE_ITEM1;
            } else {
                return TYPE_ITEM2;
            }
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM1) {
            ViewHolder viewHolder = ViewHolder.create(mContext, R.layout.item_message1, parent);
            setCommonListener(viewHolder);
            return viewHolder;
        } else if (viewType == TYPE_ITEM2) {
            ViewHolder viewHolder = ViewHolder.create(mContext, R.layout.item_message2, parent);
            setCommonListener(viewHolder);
            return viewHolder;
        } else {
            mFooterViewHolder = ViewHolder.create(mContext, R.layout.footer_loading, parent);
            setFooterListener(mFooterViewHolder);
            return mFooterViewHolder;
        }
    }

    @Override
    protected void convert(RecyclerView.ViewHolder holder, MessageModel messageModel) {

        if (messageModel.getType() == 0) {
            ViewHolder viewHolder1 = (ViewHolder) holder;
            viewHolder1.setText(R.id.textMessageTitle1, messageModel.getTitle());
            viewHolder1.setText(R.id.textMessageContent1, messageModel.getContent());
        } else {
            ViewHolder viewHolder2 = (ViewHolder) holder;
            viewHolder2.setText(R.id.textMessageTitle2, messageModel.getTitle());
            viewHolder2.setText(R.id.textMessageContent2, messageModel.getContent());
            viewHolder2.setOnClick(R.id.btnAgree, new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
            viewHolder2.setOnClick(R.id.btnNoAgree, new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }

    }

    @Override
    protected int getItemLayoutId() {
        return 0;
    }
}
