package com.zty.therapist.ui.fragment.home;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.RequestParams;
import com.zty.therapist.R;
import com.zty.therapist.adapter.AllotAdapter;
import com.zty.therapist.inter.OnDistributeRelay;
import com.zty.therapist.model.AllotModel;
import com.zty.therapist.model.ResultBean;
import com.zty.therapist.url.RequestCallback;
import com.zty.therapist.url.RequestManager;
import com.zty.therapist.url.Urls;
import com.zty.therapist.utils.ResultUtil;
import com.zty.therapist.utils.ToastUtils;

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
    TextView btnAllot;

    AllotAdapter adapter;

    private String lastUserId;

    private OnDistributeRelay onDistributeRelay;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.view_allot, null);

        ButterKnife.bind(this, view);

        gridViewAllot.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                adapter.setSelection(i);
                lastUserId = adapter.getLastUserId(i);
            }
        });

        adapter = new AllotAdapter(getActivity());
        gridViewAllot.setAdapter(adapter);

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
                if (!TextUtils.isEmpty(lastUserId)) {
                    dismiss();
                    onDistributeRelay.onDistribute(lastUserId);
                } else {
                    ToastUtils.show(getContext(), "请选择替班人员");
                }
                break;
        }
    }

    private void getMember() {
        RequestParams params = new RequestParams();
        RequestManager.get(-1, Urls.getGroupMemberList, params, this);
    }

    @Override
    public void onFailureCallback(int requestCode, String errorMsg) {

    }

    @Override
    public void onSuccessCallback(int requestCode, String response) {
        ResultBean resultBean = ResultUtil.getResult(response);
        if (resultBean.isSuccess()) {
            List<AllotModel> memberModels = new Gson().fromJson(resultBean.getResult(), new TypeToken<List<AllotModel>>() {
            }.getType());
            if (memberModels != null && memberModels.size() > 0)
                adapter.setData(memberModels);
        } else {
            ToastUtils.show(getActivity(), resultBean.getMsg());
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            onDistributeRelay = (OnDistributeRelay) context;
        } catch (ClassCastException e) {
            dismiss();
        }
    }
}
