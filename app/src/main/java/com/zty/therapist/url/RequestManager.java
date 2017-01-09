package com.zty.therapist.url;

import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;
import com.zty.therapist.base.TherapistApplication;

import cz.msebera.android.httpclient.Header;


/**
 * Created by zty on 2016/8/16.
 */
public class RequestManager {

    private static AsyncHttpClient httpClient;

    public static AsyncHttpClient getClient() {

        if (httpClient == null) {
            httpClient = new AsyncHttpClient();
            httpClient.setTimeout(15000);// 设置链接超时，如果不设置，默认为10s
        }
        return httpClient;
    }

    /**
     * get 请求
     */
    public static void get(final int code, String url, RequestParams params, final RequestCallback callback) {

        if (params == null) {
            params = new RequestParams();
        }

        if (!params.has("userId"))
            params.put("userId", TherapistApplication.getInstance().getUserId());
        params.put("tokenId", TherapistApplication.getInstance().getTokenId());

        Log.i("TherapistHttp--get", url + "?" + (params != null ? params.toString() : ""));

        getClient().get(url, params, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                callback.onFailureCallback(code, responseString);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                callback.onSuccessCallback(code, responseString);
            }
        });
    }

    /**
     * post 请求
     */
    public static void post(final int code, String url, RequestParams params, final RequestCallback callback) {

        if (params == null) {
            params = new RequestParams();
        }
        if (!params.has("userId"))
            params.put("userId", TherapistApplication.getInstance().getUserId());
        params.put("tokenId", TherapistApplication.getInstance().getTokenId());

        Log.i("TherapistHttp--post", url + "?" + (params != null ? params.toString() : ""));

        getClient().post(url, params, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                callback.onFailureCallback(code, responseString);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                callback.onSuccessCallback(code, responseString);
            }
        });
    }
}
