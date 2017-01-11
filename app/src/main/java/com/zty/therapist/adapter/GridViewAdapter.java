package com.zty.therapist.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.lling.photopicker.utils.OtherUtils;
import com.zty.therapist.R;
import com.zty.therapist.base.MyBaseAdapter;
import com.zty.therapist.model.CommunityModel;
import com.zty.therapist.recycler.ViewHolder;
import com.zty.therapist.utils.MyImageLoader;

/**
 * Created by zty on 2016/12/19.
 */

public class GridViewAdapter extends MyBaseAdapter<CommunityModel.PicturesBean, ViewHolder> {

    private int mColumnWidth;

    public GridViewAdapter(Context context) {
        super(context);
        int screenWidth = OtherUtils.getWidthInPx(context);
        mColumnWidth = (screenWidth - 2 * OtherUtils.dip2px(context, 16)) / 4;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Holder holder = null;
        if (convertView == null) {
            holder = new Holder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_image_small, null);
            holder.layoutAddImg = (LinearLayout) convertView.findViewById(R.id.layoutAddImg);
            holder.imageView = (ImageView) convertView.findViewById(R.id.imageViewSmall);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(mColumnWidth, mColumnWidth);
            holder.layoutAddImg.setLayoutParams(params);
            holder.imageView.setLayoutParams(params);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        holder.layoutAddImg.setBackgroundResource(R.drawable.shape_none);
        MyImageLoader.load(context, mData.get(position).getPicture(), holder.imageView);
        return convertView;
    }

    static class Holder {
        LinearLayout layoutAddImg;
        ImageView imageView;
    }
}
