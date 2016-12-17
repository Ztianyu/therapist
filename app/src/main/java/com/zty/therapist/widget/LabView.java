package com.zty.therapist.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zty.therapist.R;


/**
 * Created by zty on 2016/12/17.
 */

public class LabView extends RelativeLayout {
    ImageView imgLab;
    TextView textLab;

    public LabView(Context context) {
        super(context, null);
    }

    public LabView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        View view = View.inflate(context, R.layout.view_lab, this);

        imgLab = (ImageView) view.findViewById(R.id.imgLab);
        textLab = (TextView) view.findViewById(R.id.textLab);

        TypedArray arr = context.obtainStyledAttributes(attrs, R.styleable.LabView);

        imgLab.setImageDrawable(arr.getDrawable(R.styleable.LabView_lab_img));
        textLab.setText(arr.getString(R.styleable.LabView_lab_text));
        textLab.setTextSize(TypedValue.COMPLEX_UNIT_SP, arr.getInt(R.styleable.LabView_lab_size, 15));
        textLab.setTextColor(arr.getColor(R.styleable.LabView_lab_color, ContextCompat.getColor(context, R.color.black)));

    }

}
