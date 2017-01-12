package com.zty.therapist.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.zty.therapist.R;
import com.zty.therapist.base.MyBaseAdapter;
import com.zty.therapist.model.AllotModel;
import com.zty.therapist.utils.ResourceUtil;

/**
 * Created by zty on 2017/1/4.
 */

public class AllotAdapter extends MyBaseAdapter<AllotModel, GridView> {

    private int lastPosition = -1;

    public AllotAdapter(Context context) {
        super(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder = null;
        if (convertView == null) {
            holder = new Holder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_allot, null);
            holder.imgMemberSelect = (ImageView) convertView.findViewById(R.id.imgMemberSelect);
            holder.textMemberSelectName = (TextView) convertView.findViewById(R.id.textMemberSelectName);

            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        holder.textMemberSelectName.setText(mData.get(position).getTeacherNm());

        if (lastPosition == position) {
            holder.imgMemberSelect.setBackground(ResourceUtil.resToDrawable(context, R.drawable.ic_check_select));
            holder.textMemberSelectName.setTextColor(ResourceUtil.resToColor(context, R.color.checkSelect));
        } else {
            holder.imgMemberSelect.setBackground(ResourceUtil.resToDrawable(context, R.drawable.ic_check_normal));
            holder.textMemberSelectName.setTextColor(ResourceUtil.resToColor(context, R.color.gray));
        }
        return convertView;
    }

    public void setSelection(int position) {
        lastPosition = position;
        notifyDataSetChanged();
    }

    public String getLastUserId(int position) {
        return mData.get(position).getUserId();
    }

    static class Holder {
        ImageView imgMemberSelect;
        TextView textMemberSelectName;
    }
}
