package com.zty.therapist.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.zty.therapist.R;
import com.zty.therapist.model.HelpModel;
import com.zty.therapist.recycler.FooterRefreshAdapter;
import com.zty.therapist.recycler.ViewHolder;


/**
 * Created by zty on 2016/12/20.
 */

public class HelpAdapter extends FooterRefreshAdapter<HelpModel> {


    public HelpAdapter(Context context) {
        super(context);
    }

    @Override
    protected void convert(RecyclerView.ViewHolder holder, HelpModel helpModel) {
        ViewHolder viewHolder = (ViewHolder) holder;

        viewHolder.setText(R.id.textHelpTitle, helpModel.getTitle());
        viewHolder.setText(R.id.textHelpTime, helpModel.getUpdateDate());
        viewHolder.setText(R.id.textHelpContent, helpModel.getContent());
        viewHolder.setText(R.id.textHelpName, helpModel.getContacts());
        viewHolder.setText(R.id.textHelpPhone, helpModel.getContactsNumber());
    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.item_help;
    }
}
