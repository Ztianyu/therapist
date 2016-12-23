package com.zty.therapist.ui.fragment.main;

import android.os.Bundle;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.zty.therapist.R;
import com.zty.therapist.adapter.DoctorAdapter;
import com.zty.therapist.adapter.HealthProductsAdapter;
import com.zty.therapist.base.BaseFragment;
import com.zty.therapist.model.DoctorModel;
import com.zty.therapist.model.HealthProductModel;
import com.zty.therapist.recycler.FooterRefreshAdapter;
import com.zty.therapist.widget.RecyclerViewDivider;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by zty on 2016/11/25.
 */

public class ScrollingFragment extends BaseFragment implements AdapterView.OnItemSelectedListener {

    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.textNote)
    TextView textNote;
    @BindView(R.id.textSpinnerName)
    TextView textSpinnerName;
    @BindView(R.id.spinnerTop)
    AppCompatSpinner spinnerTop;

    private FooterRefreshAdapter adapter;

    private ArrayAdapter<String> spinnerAdapter;

    private List<String> category = new ArrayList<>();

    private int mLastVisibleItemPosition;
    private int pageNo = 1;
    private int mTempPageCount = 2;
    private boolean isLoadMore;

    private int type;//0：医生；1：保健品

    private String strSpinner;
    private String strNote;

    public static ScrollingFragment newInstance(int type) {
        ScrollingFragment fragment = new ScrollingFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public int getContentVew() {
        return R.layout.view_scrolling;
    }

    @Override
    public void initData() {

        type = getArguments().getInt("type");
        spinnerTop.setOnItemSelectedListener(this);
        if (type == 0) {
            strSpinner = "城市：";
            strNote = "\u3000\u3000" + "以下城市的医生为与我方合作的全过知名专家，通过我们平台预定的医生可提供如下服务：\n" +
                    "1、由指定医生门诊服务\n" +
                    "2、由指定医生主刀手术\n" +
                    "3、手术费大概只有大医院的1/2\n" +
                    "4、手术医院为与我方合作的医院\n" +
                    "5、由您介绍的患者，术后可获得健康币\n" +
                    "6、健康币可以兑换提现（比率1:1）";

            category.clear();
            category.add("北京");
            category.add("天津");
            category.add("上海");
            category.add("成都");
            category.add("西安");
            category.add("广州");

            adapter = new DoctorAdapter(context);
        } else {
            strSpinner = "类别：";
            strNote = "\u3000\u3000" + "本公司提供各种进口保障品，质量和产地都可以在网上查询，保证没有假货。您可以在您的服务人群中推广每次成交您都可以获得健康币的奖励。\n"
                    + "\u3000\u3000" + "健康币可以兑换提现（比率1:1）";

            category.clear();
            category.add("保健类");
            category.add("医药类");
            category.add("老年奶粉");

            adapter = new HealthProductsAdapter(context);
        }
        textSpinnerName.setText(strSpinner);
        textNote.setText(strNote);

        rv.addItemDecoration(new RecyclerViewDivider(context, LinearLayoutManager.HORIZONTAL));
        rv.setAdapter(adapter);

        spinnerAdapter = new ArrayAdapter(context, R.layout.item_spinner, R.id.textSpinner, category);
        spinnerTop.setAdapter(spinnerAdapter);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(layoutManager);

        rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    mLastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
                    //防止重复请求
                    if (pageNo == mTempPageCount) {
                        return;
                    }

                    if (mLastVisibleItemPosition > 0 && mLastVisibleItemPosition + 1 == adapter.getItemCount()) {
                        //已到达底部，开始加载更多
                        isLoadMore = true;
                        adapter.updateRefreshState(FooterRefreshAdapter.STATE_START);
                        pageNo = mTempPageCount;
                        fetchData();
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                mLastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
            }
        });

        fetchData();
    }

    private void fetchData() {
        List<Object> models;

        if (type == 0) {
            models = new ArrayList<>();
            DoctorModel model = new DoctorModel();
            for (int i = 0; i < 10; i++) {
                models.add(model);
            }
        } else {
            models = new ArrayList<>();
            HealthProductModel model = new HealthProductModel();
            for (int i = 0; i < 10; i++) {
                models.add(model);
            }
        }


        if (mTempPageCount <= 5)
            if (isLoadMore) {
                adapter.notifyBottomRefresh(models);
                mTempPageCount++;
            } else {
                adapter.notifyTopRefresh(models);
            }
    }

    @Override
    public void onFailureCallback(int requestCode, String errorMsg) {

    }

    @Override
    public void onSuccessCallback(int requestCode, String response) {

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
