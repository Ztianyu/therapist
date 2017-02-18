package com.zty.therapist.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;

import com.zty.therapist.R;
import com.zty.therapist.widget.CircleImageView;

/**
 * Created by zty on 2017/2/18.
 */

public class BitmapUtils {


    //位置头像的view
    public static View createLocationView(Context context, Bitmap bitmap) {

        View view = View.inflate(context, R.layout.view_location_header, null);

        CircleImageView imgLocationImage = (CircleImageView) view.findViewById(R.id.imgLocationImage);

        imgLocationImage.setImageBitmap(bitmap);

        return view;
    }

    //view 转bitmap
    public static Bitmap convertViewToBitmap(View view) {
        view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        view.buildDrawingCache();
        Bitmap bitmap = view.getDrawingCache();
        return bitmap;
    }
}
