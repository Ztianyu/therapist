package com.zty.therapist.imlib.utils;

import com.easemob.chat.EMGroup;

import java.util.List;

/**
 * Created by zty on 2017/1/20.
 */

public interface OnGroupCallback {

    void onGroupSuccess(List<EMGroup> value);

    void onGroupError(String errorMsg);
}
