package com.zty.therapist.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zty.therapist.R;

/**
 * Created by zty on 2016/11/24.
 */

public class MyStripMenuView extends RelativeLayout {
    private ImageView imgSign;
    private TextView tvName;
    private TextView tvAddition;
    private String name;
    private String addition;

    public MyStripMenuView(Context context) {
        super(context);
    }

    public MyStripMenuView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // 将自定义组合控件的布局渲染成View
        View view = View.inflate(context, R.layout.view_strip, this);
        imgSign = (ImageView) view.findViewById(R.id.imgStrip);
        tvName = (TextView) view.findViewById(R.id.textStrip);
        tvAddition = (TextView) view.findViewById(R.id.textStripAddition);
        // 加载自定义的属性并设置
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MyStripMenuView);
        name = a.getString(R.styleable.MyStripMenuView_textName);
        tvName.setText(name);
        tvName.setTextColor(a.getColor(R.styleable.MyStripMenuView_textNameColor, ContextCompat.getColor(context, R.color.black)));
        Drawable drawable = a.getDrawable(R.styleable.MyStripMenuView_imgSrc);
        imgSign.setImageDrawable(drawable);
        addition = a.getString(R.styleable.MyStripMenuView_textAddition);
        tvAddition.setText(addition);
        tvAddition.setTextColor(a.getColor(R.styleable.MyStripMenuView_textAdditionColor, ContextCompat.getColor(context, R.color.black)));

    }

    // 设置附加的文字内容
    public void setAdditionText(String addition) {
        tvAddition.setText(addition);
    }
}
