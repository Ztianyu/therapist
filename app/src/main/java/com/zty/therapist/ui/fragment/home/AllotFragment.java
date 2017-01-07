package com.zty.therapist.ui.fragment.home;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;

import com.zty.therapist.R;
import com.zty.therapist.adapter.AllotAdapter;
import com.zty.therapist.model.AllotModel;
import com.zty.therapist.url.RequestCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zty on 2017/1/4.
 */

public class AllotFragment extends DialogFragment implements RequestCallback {

    @BindView(R.id.btnClose)
    ImageButton btnClose;
    @BindView(R.id.gridViewAllot)
    GridView gridViewAllot;
    @BindView(R.id.btnAllot)
    Button btnAllot;

    AllotAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.view_allot, null);

        ButterKnife.bind(this, view);

        gridViewAllot.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                adapter.setSelection(i);
            }
        });

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        getMember();
    }

    @OnClick({R.id.btnClose, R.id.btnAllot})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnClose:
                dismiss();
                break;
            case R.id.btnAllot:
                dismiss();
                break;
        }
    }

    private void getMember() {
        adapter = new AllotAdapter(getActivity());
        gridViewAllot.setAdapter(adapter);

        List<AllotModel> models = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            models.add(new AllotModel());
        }
        adapter.setData(models);

    }

    @Override
    public void onFailureCallback(int requestCode, String errorMsg) {

    }

    @Override
    public void onSuccessCallback(int requestCode, String response) {

    }
}
