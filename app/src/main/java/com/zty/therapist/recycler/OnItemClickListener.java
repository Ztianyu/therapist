package com.zty.therapist.recycler;

/**
 * Author: Othershe
 * Time: 2016/8/18 15:42
 */
public interface OnItemClickListener<T> {
    void onCommonItemClick(ViewHolder viewHolder, T t, int position);
    void onLoadItemClick();
}

