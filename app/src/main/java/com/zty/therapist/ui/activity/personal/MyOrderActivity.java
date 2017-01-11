package com.zty.therapist.ui.activity.personal;

import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.RadioGroup;

import com.zty.therapist.R;
import com.zty.therapist.base.BaseActivity;
import com.zty.therapist.base.BaseFragment;
import com.zty.therapist.ui.fragment.personal.OrderFragment;

import butterknife.BindView;

/**
 * 我的订单
 * Created by zty on 2016/12/26.
 */

public class MyOrderActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {
    @BindView(R.id.radioGroupOrder)
    RadioGroup radioGroupOrder;

    BaseFragment currentFragment;
    private int currentPage = 1;

    private OrderFragment productFragment;
    private OrderFragment doctorFragment;

    @Override
    protected int getContentView() {
        return R.layout.activity_order;
    }

    @Override
    protected void initData() {

        radioGroupOrder.setOnCheckedChangeListener(this);

        if (productFragment == null) {
            productFragment = OrderFragment.newInstance(0);
        }

        if (!productFragment.isAdded()) {
            getSupportFragmentManager().beginTransaction().add(R.id.fragmentOrder, productFragment).commit();
            currentFragment = productFragment;
            currentPage = 1;
            setTitle();
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
            case R.id.radioButtonOrder1:
                currentPage = 1;
                if (productFragment == null) {
                    productFragment = OrderFragment.newInstance(0);
                }
                addOrShowFragment(getSupportFragmentManager().beginTransaction(), productFragment);
                break;
            case R.id.radioButtonOrder2:
                currentPage = 2;
                if (doctorFragment == null) {
                    doctorFragment = OrderFragment.newInstance(1);
                }
                addOrShowFragment(getSupportFragmentManager().beginTransaction(), doctorFragment);
                break;
        }
    }

    /**
     * 添加或者显示 fragment
     */
    private void addOrShowFragment(FragmentTransaction transaction, BaseFragment fragment) {
        if (currentFragment == fragment)
            return;

        setTitle();

        if (!fragment.isAdded()) { // 如果当前fragment未被添加，则添加到Fragment管理器中
            transaction.hide(currentFragment).add(R.id.fragmentOrder, fragment).commit();
        } else {
            transaction.hide(currentFragment).show(fragment).commit();
        }
        currentFragment = fragment;
    }

    private void setTitle() {

        if (currentPage == 1) {
            title.setText("保健品订单");
        } else {
            title.setText("医生订单");
        }

    }
}
