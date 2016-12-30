package com.zty.therapist.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
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
import com.zty.therapist.ui.fragment.SelectPicFragment;
import com.zty.therapist.utils.ResourceUtil;

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
        ImageView imageView;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_image_small, null);
            imageView = (ImageView) convertView.findViewById(R.id.imageViewSmall);
            convertView.setTag(imageView);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(mColumnWidth, mColumnWidth);
            imageView.setLayoutParams(params);
        } else {
            imageView = (ImageView) convertView.getTag();
        }

        final String path = mData.get(position);

        if (path.equals(ResourceUtil.resToStr(context, R.string.add))) {
            imageView.setBackgroundResource(R.mipmap.ic_add_image);
        } else {
            ImageLoader.getInstance().display(path, imageView, mColumnWidth, mColumnWidth);
        }

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (path.equals(ResourceUtil.resToStr(context, R.string.add))) {
                    Fragment fragment = fm.findFragmentByTag("selectPicFragment");

                    if (fragment != null)
                        fm.beginTransaction().remove(fragment);

                    SelectPicFragment commentFragment = new SelectPicFragment();
                    commentFragment.show(fm.beginTransaction(), "selectPicFragment");
                }
            }
        });
        return convertView;
    }
}
