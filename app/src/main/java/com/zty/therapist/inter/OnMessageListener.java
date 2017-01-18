package com.zty.therapist.inter;

/**
 * Created by zty on 2017/1/17.
 */

public interface OnMessageListener {

    void onAgree(int type, String id, int position);

    void onDisAgree(int type, String id, int position);
}
