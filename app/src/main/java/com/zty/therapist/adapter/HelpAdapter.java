package com.zty.therapist.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.zty.therapist.R;
import com.zty.therapist.model.HelpModel;
import com.zty.therapist.recycler.FooterRefreshAdapter;


/**
 * Created by zty on 2016/12/20.
 */

public class HelpAdapter extends FooterRefreshAdapter<HelpModel> {


    public HelpAdapter(Context context) {
        super(context);
    }

    @Override
    protected void convert(RecyclerView.ViewHolder holder, HelpModel helpModel) {

    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.item_help;
    }
}
