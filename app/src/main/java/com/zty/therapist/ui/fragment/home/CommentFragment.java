package com.zty.therapist.ui.fragment.home;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.zty.therapist.R;
import com.zty.therapist.inter.SendReplayListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zty on 2016/12/19.
 */

public class CommentFragment extends DialogFragment {

    @BindView(R.id.editComment)
    EditText editComment;
    @BindView(R.id.btnSend)
    Button btnSend;
    private Context context;

    private SendReplayListener listener;
    private String forumId;
    private String userId;
    private int position;

    public CommentFragment(Context context, String forumId, String userId, int position, SendReplayListener listener) {
        this.context = context;
        this.listener = listener;
        this.forumId = forumId;
        this.userId = userId;
        this.position = position;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // 使用不带Theme的构造器, 获得的dialog边框距离屏幕仍有几毫米的缝隙。
        Dialog dialog = new Dialog(context, R.style.BottomDialog);

        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // 设置Content前设定
        dialog.setContentView(R.layout.view_comment);
        ButterKnife.bind(this, dialog);
        dialog.setCanceledOnTouchOutside(true); // 外部点击取消

        // 设置宽度为屏宽, 靠近屏幕底部。
        Window window = dialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.gravity = Gravity.BOTTOM; // 紧贴底部
        lp.width = WindowManager.LayoutParams.MATCH_PARENT; // 宽度持平
        window.setAttributes(lp);

        editComment.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!TextUtils.isEmpty(editable.toString())) {
                    btnSend.setBackgroundResource(R.drawable.btn_can_send);
                    btnSend.setTextColor(getResources().getColor(R.color.white));
                    btnSend.setClickable(true);
                } else {
                    btnSend.setBackgroundResource(R.drawable.btn_can_not_send);
                    btnSend.setTextColor(getResources().getColor(R.color.gray));
                    btnSend.setClickable(false);
                }
            }
        });


        return dialog;
    }

    @OnClick(R.id.btnSend)
    public void onClick() {
        dismiss();
        listener.onSend(forumId, userId, position, editComment.getText().toString());
    }
}
