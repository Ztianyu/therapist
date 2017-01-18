package com.zty.therapist.inter;

/**
 * Created by zty on 2017/1/18.
 */

public interface OnHandleListener {

    void onHandleConfirm(String id, int position);

    void onHandleSure(String id, int position);

    void onSetTransactor(String id, int position);
}
