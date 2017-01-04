package com.zty.therapist.utils;

import android.os.Environment;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zty on 2017/1/3.
 */

public class PicUtils {

    public static final File PHOTO_DIR = new File(Environment.getExternalStorageDirectory() + "/DCIM/Camera");// 存储图片地址


    public static File photoPath;

    //获取图片路径
    public static File getFilePath() {
        photoPath = new File(PHOTO_DIR, getPhotoFileName());
        return photoPath;
    }

    // 使用系统当前日期加以调整作为照片的名称
    public static String getPhotoFileName() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("'IMG'yyyy_MMdd_HHmmss");
        return dateFormat.format(date) + ".jpg";
    }
}
