package com.zty.therapist.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.zty.therapist.R;
import com.zty.therapist.model.MemberModel;
import com.zty.therapist.recycler.FooterRefreshAdapter;
import com.zty.therapist.recycler.ViewHolder;

/**
 * Created by zty on 2017/1/3.
 */

public class SearchMemberAdapter extends FooterRefreshAdapter<MemberModel> {
    public SearchMemberAdapter(Context context) {
        super(context);
    }

    @Override
    protected void convert(RecyclerView.ViewHolder holder, MemberModel memberModel) {
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.setImage(mContext, R.id.imgSearchMember, "http://p1.qqyou.com/touxiang/uploadpic/2013-3/10/2013031010000358494.jpg");

    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.item_search_member;
    }
}
