package com.zty.therapist.utils;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zty.therapist.R;
import com.zty.therapist.adapter.AllotAdapter;
import com.zty.therapist.inter.DialogListener;
import com.zty.therapist.inter.OnConfirmListener;
import com.zty.therapist.inter.OnDistributeRelay;
import com.zty.therapist.inter.OnSureListener;
import com.zty.therapist.model.AllotModel;
import com.zty.therapist.model.ResultBean;
import com.zty.therapist.url.RequestCallback;
import com.zty.therapist.url.RequestManager;
import com.zty.therapist.url.Urls;

import java.util.List;

import butterknife.BindView;


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

    private static String lastUserId;

    private static AllotAdapter adapter;

    public static void select(final Context context, final OnDistributeRelay onDistributeRelay) {

        final View customLayout = LayoutInflater.from(context).inflate(R.layout.view_allot, null);
        final Dialog dialog = new AlertDialog.Builder(context).create();

        if (context != null)
            dialog.show();

        dialog.getWindow().setContentView(customLayout);

        ImageButton btnClose = (ImageButton) dialog.findViewById(R.id.btnClose);
        GridView gridViewAllot = (GridView) dialog.findViewById(R.id.gridViewAllot);
        TextView btnAllot = (TextView) dialog.findViewById(R.id.btnAllot);

        adapter = new AllotAdapter(context);
        gridViewAllot.setAdapter(adapter);

        gridViewAllot.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                adapter.setSelection(i);
                lastUserId = adapter.getLastUserId(i);
            }
        });

        // 确定按钮
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        btnAllot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!TextUtils.isEmpty(lastUserId)) {
                    dialog.dismiss();
                    onDistributeRelay.onDistribute(lastUserId);
                } else {
                    ToastUtils.show(context, "请选择处理班长");
                }
            }
        });

        RequestManager.get(-1, Urls.getRehabilitationTeamList, null, new RequestCallback() {
            @Override
            public void onFailureCallback(int requestCode, String errorMsg) {

            }

            @Override
            public void onSuccessCallback(int requestCode, String response) {
                ResultBean resultBean = ResultUtil.getResult(response);
                if (resultBean.isSuccess()) {
                    List<AllotModel> allotModels2 = new Gson().fromJson(resultBean.getResult(), new TypeToken<List<AllotModel>>() {
                    }.getType());
                    if (allotModels2 != null && allotModels2.size() > 0)
                        adapter.setData(allotModels2);

                } else {
                    ToastUtils.show(context, resultBean.getMsg());
                }
            }
        });
    }
}