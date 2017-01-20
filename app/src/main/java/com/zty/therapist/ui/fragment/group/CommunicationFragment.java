package com.zty.therapist.ui.fragment.group;

import android.app.Activity;
import android.content.Intent;

import com.easemob.chat.EMGroup;
import com.zty.therapist.adapter.ChatListAdapter;
import com.zty.therapist.base.BaseNormalListFragment;
import com.zty.therapist.imlib.LibManger;
import com.zty.therapist.imlib.chat.ui.activity.ChatActivity;
import com.zty.therapist.imlib.utils.OnGroupCallback;
import com.zty.therapist.recycler.OnItemClickListener;
import com.zty.therapist.recycler.ViewHolder;

import java.util.List;

/**
 * 聊天列表
 * Created by tianyu on 2016/12/31.
 */

public class CommunicationFragment extends BaseNormalListFragment implements OnGroupCallback, OnItemClickListener {
    @Override
    protected void initReadyData() {

    }

    @Override
    protected void setAdapter() {
        adapter = new ChatListAdapter(context);

        adapter.setOnItemClickListener(this);
    }

    @Override
    protected boolean isHaveDivider() {
        return true;
    }

    @Override
    protected void fetchData() {
        LibManger.getGroupFromServer(this);
    }

    @Override
    public void onFailureCallback(int requestCode, String errorMsg) {

    }

    @Override
    public void onSuccessCallback(int requestCode, String response) {

    }

    @Override
    public void onGroupSuccess(final List<EMGroup> value) {

        ((Activity) context).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.notifyTopRefresh(value);
            }
        });

    }

    @Override
    public void onGroupError(String errorMsg) {

    }

    @Override
    public void onCommonItemClick(ViewHolder viewHolder, Object o, int position) {
        EMGroup emGroup = (EMGroup) o;
        startActivity(new Intent(context, ChatActivity.class).putExtra("groupId", emGroup.getGroupId()));
    }

    @Override
    public void onLoadItemClick() {

    }
}
