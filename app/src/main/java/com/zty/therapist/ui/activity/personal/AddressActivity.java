package com.zty.therapist.ui.activity.personal;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zty.therapist.R;
import com.zty.therapist.adapter.AddressAdapter;
import com.zty.therapist.base.BaseActivity;
import com.zty.therapist.model.AddressModel;
import com.zty.therapist.model.ResultBean;
import com.zty.therapist.recycler.OnItemClickListener;
import com.zty.therapist.recycler.ViewHolder;
import com.zty.therapist.url.RequestManager;
import com.zty.therapist.url.Urls;
import com.zty.therapist.utils.ResultUtil;
import com.zty.therapist.utils.ToastUtils;
import com.zty.therapist.widget.RecyclerViewDivider;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by zty on 2017/1/17.
 */

public class AddressActivity extends BaseActivity implements OnItemClickListener<AddressModel> {
    @BindView(R.id.listAddress)
    RecyclerView listAddress;
    @BindView(R.id.layoutAdd)
    RelativeLayout layoutAdd;

    private AddressAdapter adapter;

    @Override
    protected int getContentView() {
        return R.layout.activity_address;
    }

    @Override
    protected void initData() {
        title.setText("收货地址");

        adapter = new AddressAdapter(this);
        listAddress.addItemDecoration(new RecyclerViewDivider(this, LinearLayoutManager.HORIZONTAL));
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        listAddress.setLayoutManager(layoutManager);
        listAddress.setAdapter(adapter);
        adapter.setOnItemClickListener(this);

        getAddressList();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        getAddressList();
    }

    private void getAddressList() {
        RequestManager.get(-1, Urls.getAddressList, null, this);
    }

    @Override
    public void onFailureCallback(int requestCode, String errorMsg) {

    }

    @Override
    public void onSuccessCallback(int requestCode, String response) {
        ResultBean resultBean = ResultUtil.getResult(response);
        if (resultBean.isSuccess()) {
            List<AddressModel> models = new Gson().fromJson(resultBean.getResult(), new TypeToken<List<AddressModel>>() {
            }.getType());

            if (models != null && models.size() > 0) {
                adapter.notifyTopRefresh(models);
            }
        } else {
            ToastUtils.show(this, resultBean.getMsg());
        }
    }


    @OnClick(R.id.layoutAdd)
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.layoutAdd:
                startActivity(new Intent(this, AddAddressActivity.class));
                break;
        }
    }

    @Override
    public void onCommonItemClick(ViewHolder viewHolder, AddressModel addressModel, int position) {
        Intent intent = new Intent();
        intent.putExtra("addressId", addressModel.getId());
        intent.putExtra("address", addressModel.getAddress());
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void onLoadItemClick() {

    }
}
