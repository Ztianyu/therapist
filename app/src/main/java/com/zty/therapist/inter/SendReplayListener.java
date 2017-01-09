package com.zty.therapist.inter;

/**
 * Created by zty on 2017/1/9.
 */

public interface SendReplayListener {
    void onSend(String forumId, String userId, int position, String message);
}
