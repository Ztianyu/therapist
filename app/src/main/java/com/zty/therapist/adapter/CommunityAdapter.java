package com.zty.therapist.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.ViewGroup;

import com.zty.therapist.R;
import com.zty.therapist.model.CommunityModel;
import com.zty.therapist.recycler.ViewHolder;
import com.zty.therapist.ui.fragment.home.CommentFragment;
import com.zty.therapist.widget.MyRecyclerView;

import java.util.List;

import cn.droidlover.xrecyclerview.RecyclerAdapter;

/**
 * Created by zty on 2016/12/19.
 */

public class CommunityAdapter extends RecyclerAdapter<CommunityModel, ViewHolder> {

    private FragmentManager fm;

    public CommunityAdapter(Context context, FragmentManager fm) {
        super(context);
        this.fm = fm;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder viewHolder = ViewHolder.create(context, R.layout.item_community, parent);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        convert(holder, data.get(position));
    }

    private void convert(ViewHolder holder, CommunityModel communityModel) {

        holder.setOnClick(R.id.imgCommunityHandler, new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Fragment fragment = fm.findFragmentByTag("dialogComment");

                if (fragment != null)
                    fm.beginTransaction().remove(fragment);

                CommentFragment commentFragment = new CommentFragment(context);
                commentFragment.show(fm.beginTransaction(), "dialogComment");
            }
        });

        setGrid(holder, communityModel.getUrls());
    }

    private void setGrid(ViewHolder holder, List<String> urls) {
        MyRecyclerView gridCommunity = holder.getView(R.id.gridCommunity);

        gridCommunity.gridLayoutManager(context, 3);
        gridCommunity.horizontalDivider(R.color.transparent, R.dimen.gridSpace);

        GridViewAdapter adapter = new GridViewAdapter(context);
        gridCommunity.setAdapter(adapter);

        adapter.setData(urls);
    }
}
