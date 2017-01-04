package com.zty.therapist.adapter;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.lling.photopicker.utils.ImageLoader;
import com.lling.photopicker.utils.OtherUtils;
import com.zty.therapist.R;
import com.zty.therapist.base.MyBaseAdapter;
import com.zty.therapist.utils.ResourceUtil;
import com.zty.therapist.utils.SelectPicUtils;

/**
 * Created by zty on 2016/12/19.
 */

public class PublishImageAdapter extends MyBaseAdapter<String, GridView> {

    private FragmentManager fm;

    private int mColumnWidth;

    public PublishImageAdapter(Context context, FragmentManager fm) {
        super(context);
        this.fm = fm;

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

        final String path = mData.get(position);

        if (!path.equals(ResourceUtil.resToStr(context, R.string.add))) {
            holder.imageView.setVisibility(View.VISIBLE);
            ImageLoader.getInstance().display(path, holder.imageView, mColumnWidth, mColumnWidth);
        } else {
            holder.imageView.setVisibility(View.INVISIBLE);
            holder.imageView.setBackground(ResourceUtil.resToDrawable(context, R.mipmap.ic_add_image));
        }

        holder.layoutAddImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (path.equals(ResourceUtil.resToStr(context, R.string.add))) {
                    SelectPicUtils.showDialog(fm);
                }
            }
        });
        return convertView;
    }

    static class Holder {
        LinearLayout layoutAddImg;
        ImageView imageView;
    }
}
