package com.zty.therapist.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zty.therapist.R;
import com.zty.therapist.base.TherapistApplication;
import com.zty.therapist.model.MemberModel;
import com.zty.therapist.recycler.NormalAdapter;
import com.zty.therapist.recycler.ViewHolder;
import com.zty.therapist.ui.activity.home.MemberDetailActivity;
import com.zty.therapist.utils.TimeUtils;

/**
 * Created by tianyu on 2017/1/1.
 */

public class MemberAdapter extends NormalAdapter<MemberModel> {

    private int role;

    public MemberAdapter(Context context) {
        super(context);
        role = TherapistApplication.getInstance().getRole();
    }

    @Override
    protected void convert(RecyclerView.ViewHolder holder, final MemberModel memberModel, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.setImage(mContext, R.id.imgMember, memberModel.getPhoto());
        viewHolder.setText(R.id.textMemberName, memberModel.getTeacherNm());
        viewHolder.setText(R.id.textMemberPhone, memberModel.getMobile());
        viewHolder.setText(R.id.textMemberSex, memberModel.getSex());
        viewHolder.setText(R.id.textMemberAge, TimeUtils.getAge(memberModel.getBirthDate()));
        viewHolder.setText(R.id.textMemberAddress, memberModel.getAddress());
        viewHolder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (role == 1 || role == 0)
                    mContext.startActivity(new Intent(mContext, MemberDetailActivity.class).putExtra("userId", memberModel.getUserId()));
            }
        });
    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.item_member;
    }
}
