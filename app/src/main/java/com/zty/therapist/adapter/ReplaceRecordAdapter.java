package com.zty.therapist.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.zty.therapist.R;
import com.zty.therapist.base.TherapistApplication;
import com.zty.therapist.inter.OnReplaceListener;
import com.zty.therapist.model.ReplaceRecordModel;
import com.zty.therapist.recycler.FooterRefreshAdapter;
import com.zty.therapist.recycler.ViewHolder;
import com.zty.therapist.ui.activity.home.ReplaceDetailActivity;
import com.zty.therapist.utils.ResourceUtil;

/**
 * Created by tianyu on 2017/1/2.
 */

public class ReplaceRecordAdapter extends FooterRefreshAdapter<ReplaceRecordModel> {

    private String userId;
    private OnReplaceListener listener;

    public ReplaceRecordAdapter(Context context, OnReplaceListener listener) {
        super(context);
        this.listener = listener;
        userId = TherapistApplication.getInstance().getUserId();
    }

    @Override
    protected void convert(RecyclerView.ViewHolder holder, final ReplaceRecordModel replaceRecordModel) {
        final ViewHolder viewHolder = (ViewHolder) holder;

        viewHolder.setText(R.id.textApplyName, replaceRecordModel.getTeacherNm());
        viewHolder.setText(R.id.textReplaceName, replaceRecordModel.getRelayNm());
        viewHolder.setText(R.id.textReplaceTime, replaceRecordModel.getStartDate() + " ~ " + replaceRecordModel.getEndDate());
        viewHolder.setText(R.id.textReplaceIntegral, replaceRecordModel.getIntegral() + "");

        int state = replaceRecordModel.getState();

        if (userId.equals(replaceRecordModel.getRelay())) {
            viewHolder.getView(R.id.btnAgree).setVisibility(View.VISIBLE);
            viewHolder.getView(R.id.btnNoAgree).setVisibility(View.VISIBLE);
            viewHolder.getView(R.id.btnCancelReplace).setVisibility(View.INVISIBLE);
        } else {
            viewHolder.getView(R.id.btnAgree).setVisibility(View.INVISIBLE);
            viewHolder.getView(R.id.btnNoAgree).setVisibility(View.INVISIBLE);
        }

        if (state == 0) {
            viewHolder.setText(R.id.textReplaceState, ReplaceRecordModel.strState1);
            ((TextView) viewHolder.getView(R.id.textReplaceState)).setTextColor(ResourceUtil.resToColor(mContext, R.color.colorForgetPw));
            if (userId.equals(replaceRecordModel.getUserId())) {
                viewHolder.getView(R.id.btnCancelReplace).setVisibility(View.VISIBLE);
            } else {
                viewHolder.getView(R.id.btnCancelReplace).setVisibility(View.INVISIBLE);
            }
        } else if (state == 1) {
            viewHolder.setText(R.id.textReplaceState, ReplaceRecordModel.strState2);
            ((TextView) viewHolder.getView(R.id.textReplaceState)).setTextColor(ResourceUtil.resToColor(mContext, R.color.colorForgetPw));
            if (userId.equals(replaceRecordModel.getUserId())) {
                viewHolder.getView(R.id.btnCancelReplace).setVisibility(View.VISIBLE);
            } else {
                viewHolder.getView(R.id.btnCancelReplace).setVisibility(View.INVISIBLE);
            }
        } else if (state == 2) {
            viewHolder.setText(R.id.textReplaceState, ReplaceRecordModel.strState3);
            ((TextView) viewHolder.getView(R.id.textReplaceState)).setTextColor(ResourceUtil.resToColor(mContext, R.color.canSend));
            viewHolder.getView(R.id.btnCancelReplace).setVisibility(View.INVISIBLE);
        } else if (state == 3) {
            viewHolder.setText(R.id.textReplaceState, ReplaceRecordModel.strState4);
            ((TextView) viewHolder.getView(R.id.textReplaceState)).setTextColor(ResourceUtil.resToColor(mContext, R.color.gray));
            viewHolder.getView(R.id.btnAgree).setVisibility(View.INVISIBLE);
            viewHolder.getView(R.id.btnNoAgree).setVisibility(View.INVISIBLE);
            viewHolder.getView(R.id.btnCancelReplace).setVisibility(View.INVISIBLE);
        } else if (state == 4) {
            viewHolder.setText(R.id.textReplaceState, ReplaceRecordModel.strState5);
            ((TextView) viewHolder.getView(R.id.textReplaceState)).setTextColor(ResourceUtil.resToColor(mContext, R.color.gray));
            viewHolder.getView(R.id.btnCancelReplace).setVisibility(View.INVISIBLE);
        }

        viewHolder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mContext.startActivity(new Intent(mContext, ReplaceDetailActivity.class).putExtra("shiftId", replaceRecordModel.getId()));
            }
        });

        viewHolder.getView(R.id.btnAgree).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onAgree(replaceRecordModel.getId(), viewHolder.getLayoutPosition());
            }
        });
        viewHolder.getView(R.id.btnNoAgree).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onDisagree(replaceRecordModel.getId(), viewHolder.getLayoutPosition());
            }
        });
        viewHolder.getView(R.id.btnCancelReplace).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onCancel(replaceRecordModel.getId(), viewHolder.getLayoutPosition());
            }
        });
    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.item_replace;
    }
}
