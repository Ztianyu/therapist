package com.zty.therapist.url;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.lzy.okhttputils.callback.FileCallback;
import com.zty.therapist.ui.activity.PdfActivity;
import com.zty.therapist.utils.ToastUtils;

import java.io.File;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 下载文件 回调
 * Created by zty on 2017/1/6.
 */

public class DownloadFileCallBack extends FileCallback {

    private Context context;

    public DownloadFileCallBack(Context context, @NonNull String destFileDir, @NonNull String destFileName) {
        super(destFileDir, destFileName);
        this.context = context;
    }

    @Override
    public void onResponse(boolean isFromCache, File file, Request request, @Nullable Response response) {
        Log.v("download", "-----2:" + file.getName());
        context.startActivity(new Intent(context, PdfActivity.class).putExtra("pdfName", file.getName()));
    }

    @Override
    public void downloadProgress(long currentSize, long totalSize, float progress, long networkSpeed) {
        super.downloadProgress(currentSize, totalSize, progress, networkSpeed);

        Log.v("download", "-----4:" + "downloadProgress -- " + totalSize + "  " + currentSize + "  " + progress + "  " + networkSpeed);
    }

    @Override
    public void onError(boolean isFromCache, Call call, @Nullable Response response, @Nullable Exception e) {
        super.onError(isFromCache, call, response, e);
        Log.v("download", "-----3:");
        ToastUtils.show(context, "下载出错!");
    }
}
