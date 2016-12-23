package com.zty.therapist.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.ViewGroup;

import com.zty.therapist.R;
import com.zty.therapist.recycler.ViewHolder;
import com.zty.therapist.ui.fragment.SelectPicFragment;
import com.zty.therapist.utils.ResourceUtil;

import cn.droidlover.xrecyclerview.RecyclerAdapter;

/**
 * Created by zty on 2016/12/19.
 */

public class PublishImageAdapter extends RecyclerAdapter<String, ViewHolder> {

    private FragmentManager fm;

    public PublishImageAdapter(Context context, FragmentManager fm) {
        super(context);
        this.fm = fm;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder viewHolder = ViewHolder.create(context, R.layout.item_image_small, parent);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        convert(holder, data.get(position));
    }

    private void convert(ViewHolder holder, final String s) {

        if (s.equals(ResourceUtil.resToStr(context, R.string.add))) {
            holder.setBgRes(R.id.imageViewSmall, R.mipmap.bg_home_user);
        }

        holder.setOnClick(R.id.imageViewSmall, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (s.equals(ResourceUtil.resToStr(context, R.string.add))) {
                    Fragment fragment = fm.findFragmentByTag("selectPicFragment");

                    if (fragment != null)
                        fm.beginTransaction().remove(fragment);

                    SelectPicFragment commentFragment = new SelectPicFragment();
                    commentFragment.show(fm.beginTransaction(), "selectPicFragment");
                }
            }
        });

    }
}
