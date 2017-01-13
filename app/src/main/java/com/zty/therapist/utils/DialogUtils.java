package com.zty.therapist.utils;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.zty.therapist.R;
import com.zty.therapist.inter.DialogListener;


/**
 * Created by zty on 2016/5/19.
 */
public class DialogUtils {

    public static Dialog dialog;
    public static Button btnCancel;
    public static Button btnSure;
    public static TextView text;

    public static void show(Context context, String message, final DialogListener listener) {

        // 为了能在下面的OnClickListener中获取布局上组件的数据，必须定义为final类型.
        final View customLayout = LayoutInflater.from(context).inflate(R.layout.dialog_exit, null);

        // 创建对话框
        dialog = new AlertDialog.Builder(context).create();

        if (context != null)
            dialog.show();

        dialog.getWindow().setContentView(customLayout);

        // 取消按钮
        btnCancel = (Button) customLayout.findViewById(R.id.dialog_btn_cancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        // 确定按钮
        btnSure = (Button) customLayout.findViewById(R.id.dialog_btn_sure);
        btnSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onSure();
            }
        });

        text = (TextView) customLayout.findViewById(R.id.text_dialog_cancel_order);
        text.setText(message);
    }
}