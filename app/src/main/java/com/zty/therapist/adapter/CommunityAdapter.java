package com.zty.therapist.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.zty.therapist.R;
import com.zty.therapist.inter.SendReplayListener;
import com.zty.therapist.model.CommunityModel;
import com.zty.therapist.recycler.FooterRefreshAdapter;
import com.zty.therapist.recycler.ViewHolder;
import com.zty.therapist.ui.fragment.home.CommentFragment;
import com.zty.therapist.utils.TimeUtils;
import com.zty.therapist.widget.MyGridView;
import com.zty.therapist.widget.MyListView;
import com.zty.therapist.widget.MyRecyclerView;
import com.zty.therapist.widget.SpacesItemDecoration;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by zty on 2016/12/19.
 */

public class CommunityAdapter extends FooterRefreshAdapter<CommunityModel> {

    private FragmentManager fm;

    private SpacesItemDecoration itemDecoration;

    SendReplayListener listener;

    SimpleDateFormat simple;

    public CommunityAdapter(Context context, FragmentManager fm, SendReplayListener listener) {
        super(context);
        this.fm = fm;
        this.listener = listener;
        itemDecoration = new SpacesItemDecoration(8);

        simple = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    }

    @Override
    protected void convert(RecyclerView.ViewHolder holder, final CommunityModel communityModel) {
        final ViewHolder viewHolder = (ViewHolder) holder;

        viewHolder.setImage(mContext, R.id.imgCommunity, communityModel.getPostedPhoto());
        viewHolder.setText(R.id.textCommunityName, communityModel.getNickName() + "");
        viewHolder.setText(R.id.textCommunityContent, communityModel.getContent() + "");
        try {
            viewHolder.setText(R.id.textCommunityTime, TimeUtils.fromToday(simple.parse(communityModel.getCreateDate())) + "");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        viewHolder.setOnClick(R.id.imgCommunityHandler, new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Fragment fragment = fm.findFragmentByTag("dialogComment");

                if (fragment != null)
                    fm.beginTransaction().remove(fragment);

                CommentFragment commentFragment = new CommentFragment(mContext, communityModel.getId(), communityModel.getUserId(), viewHolder.getLayoutPosition(), listener);
                commentFragment.show(fm.beginTransaction(), "dialogComment");
            }
        });

        setGrid(viewHolder, communityModel.getPictures());

        setRemarks(viewHolder, communityModel.getReplays());

    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.item_community;
    }

    private void setGrid(ViewHolder holder, List<CommunityModel.PicturesBean> urls) {
        MyGridView gridCommunity = holder.getView(R.id.gridCommunity);

        GridViewAdapter adapter = new GridViewAdapter(mContext);
        gridCommunity.setAdapter(adapter);

        adapter.setData(urls);
    }

    private void setRemarks(ViewHolder holder, List<CommunityModel.ReplaysBean> replaysBeanList) {
        MyListView recyclerView = holder.getView(R.id.recyclerCommunity);

        RemarksAdapter adapter = new RemarksAdapter(mContext);
        recyclerView.setAdapter(adapter);

        adapter.setData(replaysBeanList);
    }
}
