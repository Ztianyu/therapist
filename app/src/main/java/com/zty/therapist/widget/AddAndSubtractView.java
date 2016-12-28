package com.zty.therapist.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zty.therapist.R;
import com.zty.therapist.inter.OnAddAndSubtract;


/**
 * Created by zty on 2016/12/26.
 */

public class AddAndSubtractView extends LinearLayout {
    Button btnMinus;
    TextView textCount;
    Button btnPlus;

    OnAddAndSubtract listener;

    public AddAndSubtractView(Context context) {
        super(context, null);
    }

    public AddAndSubtractView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        View view = View.inflate(context, R.layout.view_count, this);

        btnMinus = (Button) view.findViewById(R.id.btnMinus);
        textCount = (TextView) view.findViewById(R.id.textCount);
        btnPlus = (Button) view.findViewById(R.id.btnPlus);
    }

    public void setListener(final OnAddAndSubtract listener) {
        this.listener = listener;

        btnMinus.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onSubtract(textCount);
            }
        });

        btnPlus.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onAdd( textCount);
            }
        });
    }
}
