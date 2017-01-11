package com.zty.therapist.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.zty.therapist.R;
import com.zty.therapist.base.MyBaseAdapter;
import com.zty.therapist.model.InfoPhotoModel;
import com.zty.therapist.utils.MyImageLoader;

/**
 * Created by zty on 2017/1/11.
 */

public class InfoPhotoListAdapter extends MyBaseAdapter<InfoPhotoModel.PicturesBean, ListView> {

    public InfoPhotoListAdapter(Context context) {
        super(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder = null;
        if (convertView == null) {
            holder = new Holder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_info_photo_child, null);
            holder.imgInfoPhoto = (ImageView) convertView.findViewById(R.id.imgInfoPhoto);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        MyImageLoader.load(context, mData.get(position).getPicture(), holder.imgInfoPhoto);
        return convertView;
    }

    static class Holder {
        ImageView imgInfoPhoto;
    }
}
