package com.zty.therapist.service;

import com.loopj.android.http.RequestParams;
import com.zty.therapist.url.RequestCallback;
import com.zty.therapist.url.RequestManager;
import com.zty.therapist.url.Urls;

/**
 * Created by zty on 2017/1/14.
 */

public class InviteInformUtils {

    public static void submitInviteInform(int code, int type, String operator, String operation, int state, RequestCallback callback) {
        RequestParams params = new RequestParams();
        params.put("type", type);
        params.put("operator", operator);
        params.put("operation", operation);
        params.put("state", state);
        RequestManager.post(code, Urls.submitInviteInform, params, callback);
    }
}
