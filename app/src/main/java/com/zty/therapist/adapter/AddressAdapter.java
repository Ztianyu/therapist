package com.zty.therapist.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.zty.therapist.R;
import com.zty.therapist.model.AddressModel;
import com.zty.therapist.recycler.NormalAdapter;
import com.zty.therapist.recycler.ViewHolder;

/**
 * Created by zty on 2017/1/18.
 */

public class AddressAdapter extends NormalAdapter<AddressModel> {

    public AddressAdapter(Context context) {
        super(context);
    }

    @Override
    protected void convert(RecyclerView.ViewHolder holder, AddressModel addressModel, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.setText(R.id.textAddressName, addressModel.getTakeName());
        viewHolder.setText(R.id.textAddressPhone, addressModel.getMobile());
        viewHolder.setText(R.id.textAddressValue, addressModel.getAddress());
    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.item_address;
    }
}
