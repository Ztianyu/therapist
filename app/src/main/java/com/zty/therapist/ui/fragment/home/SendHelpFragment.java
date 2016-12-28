package com.zty.therapist.ui.fragment.home;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.zty.therapist.R;
import com.zty.therapist.inter.OnSendHelp;
import com.zty.therapist.model.SendHelpModel;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * 发布帮助
 * Created by zty on 2016/12/20.
 */

public class SendHelpFragment extends DialogFragment {

    @BindView(R.id.editSendHelpTitle)
    EditText editSendHelpTitle;
    @BindView(R.id.editSendHelpContent)
    EditText editSendHelpContent;
    @BindView(R.id.editSendHelpName)
    EditText editSendHelpName;
    @BindView(R.id.editSendHelpPhone)
    EditText editSendHelpPhone;
    @BindView(R.id.btnSendHelp)
    Button btnSendHelp;

    private OnSendHelp OnSendHelp;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Dialog dialog = new Dialog(getActivity());

        dialog.setContentView(R.layout.activity_send_help);

        ButterKnife.bind(this, dialog);


        // 设置宽度为屏宽, 靠近屏幕底部。
        Window window = dialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT; // 宽度持平
        window.setAttributes(lp);

        btnSendHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OnSendHelp.onSend(SendHelpFragment.this, getHelpModel());
            }
        });

        return dialog;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            OnSendHelp = (OnSendHelp) context;
        } catch (ClassCastException e) {
            dismiss();
        }
    }

    private SendHelpModel getHelpModel() {

        SendHelpModel model = new SendHelpModel();

        return model;
    }
}
