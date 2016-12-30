package com.zty.therapist.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zty.therapist.R;
import com.zty.therapist.model.CommunityModel;
import com.zty.therapist.recycler.FooterRefreshAdapter;
import com.zty.therapist.recycler.ViewHolder;
import com.zty.therapist.ui.fragment.home.CommentFragment;
import com.zty.therapist.widget.MyRecyclerView;
import com.zty.therapist.widget.SpacesItemDecoration;

import java.util.List;


/**
 * Created by zty on 2016/12/19.
 */

public class CommunityAdapter extends FooterRefreshAdapter<CommunityModel> {

    private FragmentManager fm;

    private SpacesItemDecoration itemDecoration;

    public CommunityAdapter(Context context, FragmentManager fm) {
        super(context);
        this.fm = fm;
        itemDecoration = new SpacesItemDecoration(8);
    }

    @Override
    protected void convert(RecyclerView.ViewHolder holder, CommunityModel communityModel) {
        ViewHolder viewHolder = (ViewHolder) holder;

        viewHolder.setImage(mContext, R.id.imgCommunity, "http://p1.qqyou.com/touxiang/uploadpic/2013-3/10/2013031010000358494.jpg");
        viewHolder.setOnClick(R.id.imgCommunityHandler, new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Fragment fragment = fm.findFragmentByTag("dialogComment");

                if (fragment != null)
                    fm.beginTransaction().remove(fragment);

                CommentFragment commentFragment = new CommentFragment(mContext);
                commentFragment.show(fm.beginTransaction(), "dialogComment");
            }
        });

        setGrid(viewHolder, communityModel.getUrls());

    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.item_community;
    }

    private void setGrid(ViewHolder holder, List<String> urls) {
        MyRecyclerView gridCommunity = holder.getView(R.id.gridCommunity);

        GridLayoutManager manager = new GridLayoutManager(mContext, 3);
        gridCommunity.setLayoutManager(manager);

        gridCommunity.removeItemDecoration(itemDecoration);
        gridCommunity.addItemDecoration(itemDecoration);

        GridViewAdapter adapter = new GridViewAdapter(mContext);
        gridCommunity.setAdapter(adapter);

        adapter.setData(urls);
    }
}
