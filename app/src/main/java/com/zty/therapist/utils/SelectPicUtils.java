package com.zty.therapist.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.lling.photopicker.PhotoPickerActivity;
import com.zty.therapist.ui.fragment.SelectPicFragment;

/**
 * Created by zty on 2017/1/4.
 */

public class SelectPicUtils {

    public static final int PICK_PHOTO = 1;
    public static final int TAKE_PHOTO = 2;

    public static void showDialog(FragmentManager fm) {
        Fragment fragment = fm.findFragmentByTag("selectPicFragment");

        if (fragment != null)
            fm.beginTransaction().remove(fragment);

        SelectPicFragment commentFragment = new SelectPicFragment();
        commentFragment.show(fm.beginTransaction(), "selectPicFragment");
    }

    public static void hidePicFragment(FragmentManager fm) {
        DialogFragment fragment = (DialogFragment) fm.findFragmentByTag("selectPicFragment");
        if (fragment != null)
            fragment.dismiss();
    }

    public static void takePhoto(Context context) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE, null);
        // 指定调用相机拍照后照片的储存路径
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(PicUtils.getFilePath()));
        ((Activity) context).startActivityForResult(intent, TAKE_PHOTO);
    }

    public static void pickPic(Context context, int picCount) {
        Intent intent = new Intent(context, PhotoPickerActivity.class);
        intent.putExtra(PhotoPickerActivity.EXTRA_SHOW_CAMERA, false);
        intent.putExtra(PhotoPickerActivity.EXTRA_SELECT_MODE, PhotoPickerActivity.MODE_MULTI);
        intent.putExtra(PhotoPickerActivity.EXTRA_MAX_MUN, picCount);
        ((Activity) context).startActivityForResult(intent, PICK_PHOTO);
    }
}
