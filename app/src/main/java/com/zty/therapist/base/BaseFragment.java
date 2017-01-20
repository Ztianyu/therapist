package com.zty.therapist.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zty.therapist.url.RequestCallback;

import butterknife.ButterKnife;

/**
 * Created by zty on 2016/8/16.
 */
public abstract class BaseFragment extends Fragment implements RequestCallback {

    public Context context = null;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(getContentVew(), null);
        ButterKnife.bind(this, view);
        return view;
    }

    public abstract int getContentVew();

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    public abstract void initData();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.context = getActivity();
    }
}
