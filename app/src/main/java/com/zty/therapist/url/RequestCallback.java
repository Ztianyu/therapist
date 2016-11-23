package com.zty.therapist.url;

/**
 * @Description 网络请求回调接口
 * <p>
 * Created by zty on 2016/8/16.
 */
public interface RequestCallback {

    void onFailureCallback(int requestCode, String errorMsg);

    void onSuccessCallback(int requestCode, String response);
}
