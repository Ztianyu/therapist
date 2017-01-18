package com.zty.therapist.utils;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.zty.therapist.R;
import com.zty.therapist.inter.DialogListener;
import com.zty.therapist.inter.OnConfirmListener;
import com.zty.therapist.inter.OnSureListener;


/**
 * Created by zty on 2016/5/19.
 */
public class DialogUtils {

    public static void show(Context context, String message, final DialogListener listener) {

        final View customLayout = LayoutInflater.from(context).inflate(R.layout.dialog_exit, null);
        final Dialog dialog = new AlertDialog.Builder(context).create();

        if (context != null)
            dialog.show();

        dialog.getWindow().setContentView(customLayout);

        // 取消按钮
        Button btnCancel = (Button) customLayout.findViewById(R.id.dialog_btn_cancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        // 确定按钮
        Button btnSure = (Button) customLayout.findViewById(R.id.dialog_btn_sure);
        btnSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                listener.onSure();
            }
        });

        TextView text = (TextView) customLayout.findViewById(R.id.text_dialog_cancel_order);
        text.setText(message);
    }

    public static void confirm(Context context, final OnConfirmListener listener) {

        final View customLayout = LayoutInflater.from(context).inflate(R.layout.dialog_confirm, null);
        final Dialog dialog = new AlertDialog.Builder(context).create();

        if (context != null)
            dialog.show();

        dialog.getWindow().setContentView(customLayout);

        final EditText editConfirm = (EditText) customLayout.findViewById(R.id.editConfirm);
        // 确定按钮
        Button btnSure = (Button) customLayout.findViewById(R.id.dialog_btn_sure);
        btnSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                listener.onConfirmSure(editConfirm.getText().toString());
            }
        });

        ImageButton btnClose = (ImageButton) customLayout.findViewById(R.id.btnClose);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }


    private static int sureCode;

    public static void sure(Context context, final OnSureListener listener) {

        final View customLayout = LayoutInflater.from(context).inflate(R.layout.dialog_sure, null);
        final Dialog dialog = new AlertDialog.Builder(context).create();

        if (context != null)
            dialog.show();

        dialog.getWindow().setContentView(customLayout);

        final EditText editConfirm = (EditText) customLayout.findViewById(R.id.editConfirm);
        // 确定按钮
        Button btnSure = (Button) customLayout.findViewById(R.id.dialog_btn_sure);
        btnSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                listener.onSure(sureCode, editConfirm.getText().toString());
            }
        });

        ImageButton btnClose = (ImageButton) customLayout.findViewById(R.id.btnClose);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        RadioGroup radioGroupSure = (RadioGroup) customLayout.findViewById(R.id.radioGroupSure);
        radioGroupSure.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.radioButtonSure1:
                        sureCode = 2;
                        break;
                    case R.id.radioButtonSure2:
                        sureCode = 3;
                        break;
                }
            }
        });
    }
}