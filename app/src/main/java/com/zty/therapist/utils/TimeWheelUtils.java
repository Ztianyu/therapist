package com.zty.therapist.utils;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import com.zty.therapist.R;
import com.zty.therapist.base.BaseActivity;
import com.zty.therapist.widget.time.JudgeDate;
import com.zty.therapist.widget.time.WheelMain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by zty on 2017/1/10.
 */

public class TimeWheelUtils {

    public static void show(Context context, final EditText editText) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        LayoutInflater inflater = LayoutInflater.from(context);
        final View timepickerview = inflater.inflate(R.layout.timepicker, null);
        final WheelMain wheelMain = new WheelMain(timepickerview, true);
        wheelMain.screenheight = BaseActivity.screenHeight;
        String time = editText.getText().toString();
        Calendar calendar = Calendar.getInstance();
        if (JudgeDate.isDate(time, "yyyy-MM-dd HH:mm")) {
            try {
                calendar.setTime(dateFormat.parse(time));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int houre = calendar.get(Calendar.HOUR_OF_DAY);
        int min = calendar.get(Calendar.MINUTE);
        wheelMain.initDateTimePicker(year, month, day, houre, min);
        new AlertDialog.Builder(context)
                .setTitle("选择时间")
                .setView(timepickerview)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        editText.setText(wheelMain.getTime());
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .show();
    }

    public static void showDataSelect(Context context, final EditText editText) {
        Calendar c = Calendar.getInstance();
        new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                StringBuffer sb = new StringBuffer();
                sb.append(year)
                        .append("-")
                        .append((monthOfYear + 1) < 10 ? "0" + (monthOfYear + 1) : (monthOfYear + 1))
                        .append("-")
                        .append(dayOfMonth < 10 ? "0" + dayOfMonth : dayOfMonth);
                editText.setText(sb.toString());
            }
        }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)).show();
    }
}
