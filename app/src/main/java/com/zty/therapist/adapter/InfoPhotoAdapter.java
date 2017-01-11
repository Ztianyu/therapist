package com.zty.therapist.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.zty.therapist.R;
import com.zty.therapist.base.MyBaseAdapter;
import com.zty.therapist.model.InfoPhotoModel;
import com.zty.therapist.widget.MyListView;

import java.util.List;

/**
 * Created by zty on 2017/1/11.
 */

public class InfoPhotoAdapter extends MyBaseAdapter<InfoPhotoModel, ListView> {

    public InfoPhotoAdapter(Context context) {
        super(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder = null;
        if (convertView == null) {
            holder = new Holder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_info_photo, null);
            holder.listViewInfoPhoto = (MyListView) convertView.findViewById(R.id.listViewInfoPhoto);
            holder.textInfoSendName = (TextView) convertView.findViewById(R.id.textInfoSendName);
            holder.textInfoSendTime = (TextView) convertView.findViewById(R.id.textInfoSendTime);
            holder.textInfoSendContent = (TextView) convertView.findViewById(R.id.textInfoSendContent);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        holder.textInfoSendName.setText("上传人：" + mData.get(position).getTeacherNm());
        holder.textInfoSendTime.setText(mData.get(position).getCreateDate());
        holder.textInfoSendContent.setText(mData.get(position).getRemarks());
        setPhotos(holder.listViewInfoPhoto, mData.get(position).getPictures());
        return convertView;
    }

    private void setPhotos(MyListView listView, List<InfoPhotoModel.PicturesBean> picturesBeans) {
        InfoPhotoListAdapter adapter = new InfoPhotoListAdapter(context);
        listView.setAdapter(adapter);
        adapter.setData(picturesBeans);
    }

    static class Holder {
        MyListView listViewInfoPhoto;
        TextView textInfoSendName;
        TextView textInfoSendTime;
        TextView textInfoSendContent;
    }
}
