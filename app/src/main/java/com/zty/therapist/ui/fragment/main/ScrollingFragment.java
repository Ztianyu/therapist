package com.zty.therapist.ui.fragment.main;

import android.os.Bundle;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.RequestParams;
import com.zty.therapist.R;
import com.zty.therapist.adapter.DoctorAdapter;
import com.zty.therapist.adapter.HealthProductsAdapter;
import com.zty.therapist.base.BaseFragment;
import com.zty.therapist.model.DoctorModel;
import com.zty.therapist.model.HealthProductModel;
import com.zty.therapist.model.OptionModel;
import com.zty.therapist.model.ResultBean;
import com.zty.therapist.recycler.FooterRefreshAdapter;
import com.zty.therapist.url.RequestManager;
import com.zty.therapist.url.Urls;
import com.zty.therapist.utils.ResultUtil;
import com.zty.therapist.widget.RecyclerViewDivider;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by zty on 2016/11/25.
 */

public class ScrollingFragment extends BaseFragment implements AdapterView.OnItemSelectedListener {

    private static final int CODE_GET_DOCTOR = 0;
    private static final int CODE_GET_PRODUCT = 1;
    private static final int CODE_GET_PRODUCT_TYPE = 2;
    private static final int CODE_GET_CITY_TYPE = 3;

    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.textNote)
    TextView textNote;
    @BindView(R.id.textSpinnerName)
    TextView textSpinnerName;
    @BindView(R.id.spinnerTop)
    AppCompatSpinner spinnerTop;
    @BindView(R.id.scrollingHeader)
    RelativeLayout scrollingHeader;
    @BindView(R.id.edit_search)
    LinearLayout editSearch;

    private FooterRefreshAdapter adapter;

    private ArrayAdapter<String> spinnerAdapter;

    private List<OptionModel> optionModelList = new ArrayList<>();
    private List<String> category = new ArrayList<>();

    private int mLastVisibleItemPosition;
    private int pageNo = 1;
    private int mTempPageCount = 2;
    private boolean isLoadMore;

    private int type;//0：医生；1：保健品

    private String key = "";

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
                    "1、由指定医生门诊服务、由指定医生主刀手术\n" +
                    "2、手术费大概只有大医院的1/2\n" +
                    "3、手术医院为与我方合作的医院\n" +
                    "4、由您介绍的患者，术后可获得健康币\n" +
                    "5、健康币可以兑换提现（比率1:1）";

            adapter = new DoctorAdapter(context);
        } else {
            strSpinner = "类别：";
            strNote = "\u3000\u3000" + "本公司提供各种进口保障品，质量和产地都可以在网上查询，保证没有假货。您可以在您的服务人群中推广每次成交您都可以获得健康币的奖励。\n"
                    + "\u3000\u3000" + "健康币可以兑换提现（比率1:1）";

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

//        fetchData();
        fetchType();
    }

    private void fetchType() {
        if (type == 0) {
            RequestParams params = new RequestParams();
            RequestManager.get(CODE_GET_CITY_TYPE, Urls.getDoctorCityOption, params, this);
        } else {
            RequestParams params = new RequestParams();
            RequestManager.get(CODE_GET_PRODUCT_TYPE, Urls.getProductTypeOption, params, this);
        }
    }

    private void fetchData() {
        if (type == 0) {
            RequestParams params = new RequestParams();
            params.put("pageNo", pageNo);
            params.put("city", key);
            RequestManager.get(CODE_GET_DOCTOR, Urls.getDoctorList, params, this);
        } else {
            RequestParams params = new RequestParams();
            params.put("pageNo", pageNo);
            params.put("type", key);
            RequestManager.get(CODE_GET_PRODUCT, Urls.getHealthProductsList, params, this);
        }
    }

    @Override
    public void onFailureCallback(int requestCode, String errorMsg) {

    }

    @Override
    public void onSuccessCallback(int requestCode, String response) {
        ResultBean resultBean = ResultUtil.getResult(response);

        if (resultBean.isSuccess()) {
            switch (requestCode) {
                case CODE_GET_DOCTOR:
                    List<DoctorModel> doctorModels = new Gson().fromJson(resultBean.getResult(), new TypeToken<List<DoctorModel>>() {
                    }.getType());

                    if (doctorModels != null) {
                        setDoctor(doctorModels);
                    } else {
                        if (pageNo == 1) {
                            adapter.clearData();
                        }
                        adapter.updateRefreshState(FooterRefreshAdapter.STATE_FINISH);

                    }
                    break;
                case CODE_GET_PRODUCT:
                    List<HealthProductModel> productModels = new Gson().fromJson(resultBean.getResult(), new TypeToken<List<HealthProductModel>>() {
                    }.getType());
                    if (productModels != null) {
                        setProduct(productModels);
                    } else {
                        if (pageNo == 1) {
                            adapter.clearData();
                        }
                        adapter.updateRefreshState(FooterRefreshAdapter.STATE_FINISH);

                    }
                    break;
                case CODE_GET_CITY_TYPE:
                case CODE_GET_PRODUCT_TYPE:
                    List<OptionModel> optionModels = new Gson().fromJson(resultBean.getResult(), new TypeToken<List<OptionModel>>() {
                    }.getType());
                    if (optionModels != null) {
                        optionModelList.clear();
                        optionModelList.addAll(optionModels);
                        for (int i = 0; i < optionModelList.size(); i++) {
                            category.add(optionModelList.get(i).getValue());
                        }
                        spinnerAdapter.notifyDataSetChanged();
                    }
                    break;
            }
        }

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if (optionModelList.size() > 0) {
            key = optionModelList.get(i).getKey();
            isLoadMore = false;
            pageNo = 1;
            fetchData();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private void setDoctor(List<DoctorModel> data) {
        if (isLoadMore) {
            if (data.size() == 0) {
                adapter.updateRefreshState(FooterRefreshAdapter.STATE_FINISH);
            } else {
                adapter.notifyBottomRefresh(data);
                mTempPageCount++;
            }
        } else {
            adapter.notifyTopRefresh(data);
        }
    }

    private void setProduct(List<HealthProductModel> data) {
        if (isLoadMore) {
            if (data.size() == 0) {
                adapter.updateRefreshState(FooterRefreshAdapter.STATE_FINISH);
            } else {
                adapter.notifyBottomRefresh(data);
                mTempPageCount++;
            }
        } else {
            adapter.notifyTopRefresh(data);
        }
    }
}
