package com.zty.therapist.ui.fragment.train;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.zty.therapist.R;
import com.zty.therapist.adapter.CaseAdapter;
import com.zty.therapist.adapter.GuideAdapter;
import com.zty.therapist.adapter.LectureAdapter;
import com.zty.therapist.adapter.TestAdapter;
import com.zty.therapist.adapter.TheoryAdapter;
import com.zty.therapist.base.BaseFragment;
import com.zty.therapist.utils.ResourceUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.droidlover.xrecyclerview.RecyclerAdapter;
import cn.droidlover.xrecyclerview.XRecyclerView;

/**
 * Created by zty on 2016/12/3.
 */

public class BaseTrainFragment extends BaseFragment implements RadioGroup.OnCheckedChangeListener {

    @BindView(R.id.recyclerView)
    XRecyclerView recyclerView;

    RadioButton radioButtonTrain1;
    RadioButton radioButtonTrain2;
    RadioButton radioButtonTrain3;
    RadioGroup radioGroupTrain;

    private RecyclerAdapter adapter;

    private View topView;

    private int type;//1：理论；2：试题；3：讲座；4：案例；5：指南


    public static BaseTrainFragment newInstance(int type) {
        BaseTrainFragment baseTrainFragment = new BaseTrainFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        baseTrainFragment.setArguments(bundle);
        return baseTrainFragment;
    }

    @Override
    public int getContentVew() {
        return R.layout.view_recycler;
    }

    @Override
    public void initData() {
        type = getArguments().getInt("type");

        topView = ResourceUtil.inflate(context, R.layout.view_train_top);

        radioGroupTrain = (RadioGroup) topView.findViewById(R.id.radioGroupTrain);
        radioButtonTrain1 = (RadioButton) topView.findViewById(R.id.radioButtonTrain1);
        radioButtonTrain2 = (RadioButton) topView.findViewById(R.id.radioButtonTrain2);
        radioButtonTrain3 = (RadioButton) topView.findViewById(R.id.radioButtonTrain3);
        radioGroupTrain.setOnCheckedChangeListener(this);

        setRadioButtonTrain();

        setAdapter();

        recyclerView.verticalLayoutManager(context);
        recyclerView.setAdapter(adapter);

        recyclerView.addHeaderView(topView);

    }

    @Override
    public void onFailureCallback(int requestCode, String errorMsg) {

    }

    @Override
    public void onSuccessCallback(int requestCode, String response) {

    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (i) {
            case R.id.radioButtonTrain1:
                break;
            case R.id.radioButtonTrain2:
                break;
            case R.id.radioButtonTrain3:
                break;
        }
    }

    public void setRadioButtonTrain() {

        switch (type) {
            case 1:
            case 3:
            case 4:
            case 5:
                radioButtonTrain1.setText("神经类");
                radioButtonTrain2.setText("心脏类");
                radioButtonTrain3.setText("肌肉类");
                break;
            case 2:
                radioButtonTrain1.setText("初级");
                radioButtonTrain2.setText("中级");
                radioButtonTrain3.setText("高级");
                break;
        }
    }

    public void setAdapter() {
        switch (type) {
            case 1:
                adapter = new TheoryAdapter(context);
                break;
            case 2:
                adapter = new TestAdapter(context);
                break;
            case 3:
                adapter = new LectureAdapter(context);
                break;
            case 4:
                adapter = new CaseAdapter(context);
                break;
            case 5:
                adapter = new GuideAdapter(context);
                break;
        }

    }
}