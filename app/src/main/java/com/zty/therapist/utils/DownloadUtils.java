package com.zty.therapist.utils;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.lzy.okhttputils.OkHttpUtils;
import com.zty.therapist.url.DownloadFileCallBack;

import java.io.File;

/**
 * Created by zty on 2017/1/6.
 */

public class DownloadUtils {

    public static File file = Environment.getExternalStorageDirectory();
    public static String assent = "/therapist/pdf";

    public static void downPdf(Context context, String url, String fileName) {
        OkHttpUtils.get(url)//
                .tag(context)//
                .execute(new DownloadFileCallBack(context, file + assent, fileName + ".pdf"));//保存到sd卡
    }
}
