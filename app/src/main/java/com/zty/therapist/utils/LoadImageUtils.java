package com.zty.therapist.utils;

import com.loopj.android.http.RequestParams;
import com.zty.therapist.url.RequestCallback;
import com.zty.therapist.url.RequestManager;
import com.zty.therapist.url.Urls;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Created by zty on 2017/1/9.
 */

public class LoadImageUtils {

    //头像
    public static final String rehabilitationTeacher = "rehabilitationTeacher";
    //感情天地
    public static final String healthForum = "healthForum";

    public static void load(int code, String type, File file, RequestCallback callback) {
        try {
            RequestParams params = new RequestParams();
            params.put("uploadPath", type);
            params.put("attachment", file);
            RequestManager.post(code, Urls.uploadAndroidServlet, params, callback);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
