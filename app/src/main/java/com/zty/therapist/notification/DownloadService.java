package com.zty.therapist.notification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.lzy.okhttputils.OkHttpUtils;
import com.lzy.okhttputils.callback.FileCallback;
import com.zty.therapist.R;
import com.zty.therapist.ui.activity.PdfActivity;

import java.io.File;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

import static com.zty.therapist.utils.DownloadUtils.assent;
import static com.zty.therapist.utils.DownloadUtils.file;

/**
 * Created by zty on 2017/1/3.
 */

public class DownloadService extends Service {

    private static final int DOWN_OK = 1; // 下载完成
    private static final int DOWN_ERROR = 0;

    private String downUrl;
    private String fileName;

    // 点击查看
    private Intent intent = null;
    private PendingIntent pendingIntent = null;

    // 通知栏消息
    private int notificationID = 1000;
    private NotificationCompat.Builder builder;
    private NotificationManager messageNotificationManager = null;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null) {
            downUrl = intent.getStringExtra("downUrl");
            fileName = intent.getStringExtra("fileName");
            OkHttpUtils.get(downUrl)
                    .tag(this)
                    .execute(new DownloadFileCallBack(file + assent, fileName + ".pdf"));//保存到sd卡
            // 初始化
            builder = new NotificationCompat.Builder(this)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle("下载")
                    .setContentText("正在下载")
                    .setAutoCancel(true);

            messageNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            showNotification();
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private void showNotification() {
        intent = new Intent();
        pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        builder.setContentIntent(pendingIntent);
        messageNotificationManager.notify(notificationID, builder.build());
    }

    /***
     * 更新UI
     */
    Handler handler = new Handler() {
        @SuppressWarnings("deprecation")
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case DOWN_OK:
                    intent = new Intent(DownloadService.this, PdfActivity.class).putExtra("pdfName", fileName);
                    builder.setContentTitle("下载完成");
                    builder.setContentText("完成");
                    builder.setProgress(100, 100, false);
                    messageNotificationManager.notify(notificationID, builder.build());
                    Intent pdfIntent = new Intent(DownloadService.this, PdfActivity.class);
                    pdfIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    pdfIntent.putExtra("pdfName", fileName);
                    startActivity(pdfIntent);
                    stopService(intent);
                    break;
                case DOWN_ERROR:
                    builder.setContentTitle("下载失败");
                    builder.setContentText("失败");
                    messageNotificationManager.notify(notificationID, builder.build());
                    stopService(intent);
                    break;
                default:
                    stopService(intent);
                    messageNotificationManager.cancel(notificationID);
                    break;
            }
        }
    };

    public class DownloadFileCallBack extends FileCallback {

        public DownloadFileCallBack(@NonNull String destFileDir, @NonNull String destFileName) {
            super(destFileDir, destFileName);
        }

        @Override
        public void onResponse(boolean isFromCache, File file, Request request, @Nullable Response response) {
            Message message = handler.obtainMessage();
            message.what = DOWN_OK;
            handler.sendMessage(message);
        }

        @Override
        public void downloadProgress(long currentSize, long totalSize, float progress, long networkSpeed) {
            super.downloadProgress(currentSize, totalSize, progress, networkSpeed);
            Message message = progressHandler.obtainMessage();
            message.arg1 = (int) (100 * currentSize / totalSize);
            progressHandler.sendMessage(message);
        }

        @Override
        public void onError(boolean isFromCache, Call call, @Nullable Response response, @Nullable Exception e) {
            super.onError(isFromCache, call, response, e);
            Message message = handler.obtainMessage();
            message.what = DOWN_ERROR;
            handler.sendMessage(message);
        }
    }

    Handler progressHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int progress = msg.arg1;
            builder.setProgress(100, progress, false);
            messageNotificationManager.notify(notificationID, builder.build());
            builder.setContentText(progress + "%");
            Log.i("download", "  " + progress);
        }
    };
}
