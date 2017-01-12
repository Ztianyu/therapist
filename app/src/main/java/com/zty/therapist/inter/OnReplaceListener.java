package com.zty.therapist.inter;

/**
 * Created by zty on 2017/1/12.
 */

public interface OnReplaceListener {

    void onAgree(String id, int position);

    void onDisagree(String id, int position);

    void onCancel(String id, int position);
}
