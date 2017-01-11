package com.zty.therapist.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.zty.therapist.R;
import com.zty.therapist.base.MyBaseAdapter;
import com.zty.therapist.model.CommunityModel;

/**
 * Created by zty on 2017/1/10.
 */

public class RemarksAdapter extends MyBaseAdapter<CommunityModel.ReplaysBean, ListView> {

    public RemarksAdapter(Context context) {
        super(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Holder holder = null;
        if (convertView == null) {
            holder = new Holder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_remarks, null);
            holder.textRemarksName = (TextView) convertView.findViewById(R.id.textRemarksName);
            holder.textRemarksContent = (TextView) convertView.findViewById(R.id.textRemarksContent);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        holder.textRemarksContent.setText(mData.get(position).getContent());
        holder.textRemarksName.setText(mData.get(position).getNickName() + "：");
        return convertView;
    }

    static class Holder {
        TextView textRemarksName;
        TextView textRemarksContent;
    }
}
