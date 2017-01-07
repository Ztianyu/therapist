package com.zty.therapist.utils;


import android.util.Log;

import com.zty.therapist.model.ResultBean;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 网络数据处理类
 */
public class ResultUtil {

    /**
     */
    public static synchronized ResultBean getResult(String json) {
        Log.i("result", json);
        ResultBean resultBean = new ResultBean();
        if (json == null) {
            return resultBean;
        }
        try {
            JSONObject jsonObject = new JSONObject(json);
            if (jsonObject.has("head")) {
                JSONObject headObject = new JSONObject(jsonObject.getString("head"));
                if (headObject.has("ret")) {
                    resultBean.setCode(headObject.getInt("ret"));
                }
                if (headObject.has("ret") && headObject.getInt("ret") == 0) {
                    resultBean.setSuccess(true);

                }
                if (headObject.has("msg")) {
                    resultBean.setMsg(headObject.getString("msg"));
                }
            }
            if (jsonObject.has("data")) {
                resultBean.setResult(jsonObject.getString("data"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return resultBean;
    }

}
