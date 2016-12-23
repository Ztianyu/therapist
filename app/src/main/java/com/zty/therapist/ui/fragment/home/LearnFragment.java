package com.zty.therapist.ui.fragment.home;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.zty.therapist.R;
import com.zty.therapist.adapter.CaseAdapter;
import com.zty.therapist.adapter.TheoryAdapter;
import com.zty.therapist.base.BaseFragment;
import com.zty.therapist.model.CaseModel;
import com.zty.therapist.model.TheoryModel;
import com.zty.therapist.utils.ResourceUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.droidlover.xrecyclerview.RecyclerAdapter;
import cn.droidlover.xrecyclerview.XRecyclerView;

/**
 * Created by zty on 2016/12/3.
 */

public class LearnFragment extends BaseFragment implements RadioGroup.OnCheckedChangeListener {

    @BindView(R.id.recyclerView)
    XRecyclerView recyclerView;

    RadioButton radioButtonTrain1;
    RadioButton radioButtonTrain2;
    RadioGroup radioGroupTrain;

    private RecyclerAdapter adapter;

    private View topView;

    private int type;//0：神经类；1：心血管类；2：骨科类；

    private int index = 0;//0：理论知识；1：案例分析


    public static LearnFragment newInstance(int type) {
        LearnFragment learnFragment = new LearnFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        learnFragment.setArguments(bundle);
        return learnFragment;
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
        radioGroupTrain.setOnCheckedChangeListener(this);

        setRadioButtonTrain();

        setAdapter();
        getData();
    }

    private void getData() {
        if (index == 0) {
            List<TheoryModel> theoryModels = new ArrayList<>();
            TheoryModel model = new TheoryModel();
            model.setTitle("神经类常见疾病");
            model.setType(1);
            theoryModels.add(model);
            model = new TheoryModel();
            model.setTitle("神经康复基础知识");
            model.setType(1);
            theoryModels.add(model);
            model = new TheoryModel();
            model.setTitle("中风康复教程");
            model.setType(0);
            theoryModels.add(model);
            adapter.setData(theoryModels);
        } else if (index == 1) {
            List<CaseModel> lectureModels = new ArrayList<>();
            CaseModel model = new CaseModel();
            model.setTitle("神经类常见疾病");
            model.setContent("中风患者经过医院救治幸存后，常遗留一些运动、感觉和语言等障碍，出院后家庭康复治疗是一种有效的好方法，此时，家属如能做到护理恰当，就可以消除或减轻患者的功能缺陷，可以最大限度恢复患者生活及工作能力。中风患者经过医院救治幸存后，常遗留一些运动、感觉和语言等障碍，出院后家庭康复治疗是一种有效的好方法，此时，家属如能做到护理恰当，就可以消除或减轻患者的功能缺陷，可以最大限度恢复患者生活及工作能力。");
            model.setTime("2016-12-18");
            lectureModels.add(model);
            lectureModels.add(model);
            lectureModels.add(model);
            lectureModels.add(model);
            lectureModels.add(model);
            adapter.setData(lectureModels);
        }

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
                index = 0;
                setAdapter();
                getData();
                break;
            case R.id.radioButtonTrain2:
                index = 1;
                setAdapter();
                getData();
                break;
        }
    }

    private void setRadioButtonTrain() {
        radioButtonTrain1.setText("理论知识");
        radioButtonTrain2.setText("案例分析");
    }


    private void setAdapter() {

        if (index == 0) {
            adapter = new TheoryAdapter(context);
            recyclerView.verticalLayoutManager(context);
            recyclerView.horizontalDivider(R.color.colorDivider, R.dimen.dividerHeight);
        } else if (index == 1) {
            adapter = new CaseAdapter(context);
            recyclerView.horizontalDivider(R.color.transparent, R.dimen.dividerHeight);
        }

        recyclerView.setAdapter(adapter);

        recyclerView.addHeaderView(topView);
    }
}