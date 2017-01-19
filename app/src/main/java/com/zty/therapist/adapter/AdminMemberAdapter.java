package com.zty.therapist.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zty.therapist.R;
import com.zty.therapist.inter.OnAdminHandleListener;
import com.zty.therapist.model.MemberModel;
import com.zty.therapist.recycler.FooterRefreshAdapter;
import com.zty.therapist.recycler.ViewHolder;
import com.zty.therapist.utils.MyTextUtils;
import com.zty.therapist.utils.TimeUtils;

/**
 * Created by zty on 2017/1/19.
 */

public class AdminMemberAdapter extends FooterRefreshAdapter<MemberModel> {

    private OnAdminHandleListener listener;

    public AdminMemberAdapter(Context context, OnAdminHandleListener listener) {
        super(context);
        this.listener = listener;
    }

    @Override
    protected void convert(final RecyclerView.ViewHolder holder, final MemberModel memberModel) {
        final ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.setImage(mContext, R.id.imgSearchMember, MyTextUtils.isEmpty(memberModel.getPhoto()));
        viewHolder.setText(R.id.textSearchMemberName, MyTextUtils.isEmpty(memberModel.getTeacherNm()));
        viewHolder.setText(R.id.textSearchMemberPhone, MyTextUtils.isEmpty(memberModel.getMobile()));
        viewHolder.setText(R.id.textSearchMemberAge, TimeUtils.getAge(memberModel.getBirthDate()));
        viewHolder.setText(R.id.textSearchMemberAddress, MyTextUtils.isEmpty(memberModel.getAddress()));

        viewHolder.setOnClick(R.id.btnSetClass, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onSetClass(memberModel, viewHolder.getLayoutPosition());
            }
        });
        viewHolder.setOnClick(R.id.btnSetGroup, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onSetGroup(memberModel, viewHolder.getLayoutPosition());
            }
        });
    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.item_admin_member;
    }
}
