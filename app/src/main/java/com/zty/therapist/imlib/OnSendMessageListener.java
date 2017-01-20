package com.zty.therapist.imlib;

import com.zty.therapist.imlib.chat.enity.MessageInfo;

/**
 * Created by zty on 2017/1/20.
 */

public interface OnSendMessageListener {

    void onSuccess(MessageInfo messageInfo);

    void onError(MessageInfo messageInfo, String error);
}
