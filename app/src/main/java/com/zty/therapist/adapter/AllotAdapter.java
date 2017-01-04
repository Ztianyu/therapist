package com.zty.therapist.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.GridView;

import com.zty.therapist.R;
import com.zty.therapist.base.MyBaseAdapter;
import com.zty.therapist.model.AllotModel;

/**
 * Created by zty on 2017/1/4.
 */

public class AllotAdapter extends MyBaseAdapter<AllotModel, GridView> {

    private int clickStatus = -1;

    public AllotAdapter(Context context) {
        super(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder = null;
        if (convertView == null) {
            holder = new Holder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_allot, null);
            holder.checkboxAllot = (CheckBox) convertView.findViewById(R.id.checkboxAllot);

            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        if (clickStatus == position) {
            holder.checkboxAllot.setChecked(true);
        } else {
            holder.checkboxAllot.setChecked(false);
        }

        return convertView;
    }

    public void setSelection(int position) {
        clickStatus = position;

        mData.get(position).setCheck(true);
        notifyDataSetChanged();
    }

    static class Holder {
        CheckBox checkboxAllot;
    }
}
