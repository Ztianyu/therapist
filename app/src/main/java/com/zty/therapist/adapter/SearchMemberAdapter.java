package com.zty.therapist.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zty.therapist.R;
import com.zty.therapist.inter.OnAddToGroupListener;
import com.zty.therapist.model.MemberModel;
import com.zty.therapist.recycler.NormalAdapter;
import com.zty.therapist.recycler.ViewHolder;
import com.zty.therapist.utils.TimeUtils;

/**
 * Created by zty on 2017/1/3.
 */

public class SearchMemberAdapter extends NormalAdapter<MemberModel> {

    private OnAddToGroupListener listener;

    public SearchMemberAdapter(Context context, OnAddToGroupListener listener) {
        super(context);
        this.listener = listener;
    }

    @Override
    protected void convert(RecyclerView.ViewHolder holder, final MemberModel memberModel, final int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.setImage(mContext, R.id.imgSearchMember, memberModel.getPhoto());
        viewHolder.setText(R.id.textSearchMemberName, memberModel.getTeacherNm());
        viewHolder.setText(R.id.textSearchMemberPhone, memberModel.getMobile());
        viewHolder.setText(R.id.textSearchMemberAge, TimeUtils.getAge(memberModel.getBirthDate()));
        viewHolder.setText(R.id.textSearchMemberAddress, memberModel.getAddress());

        viewHolder.getView(R.id.btnAddToGroup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onAdd(memberModel.getUserId(),position);
            }
        });
    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.item_search_member;
    }
}
