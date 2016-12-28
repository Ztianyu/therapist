package com.zty.therapist.ui.activity.personal;

import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.RadioGroup;

import com.zty.therapist.R;
import com.zty.therapist.base.BaseActivity;
import com.zty.therapist.base.BaseFragment;
import com.zty.therapist.ui.fragment.personal.AccountControlFragment;
import com.zty.therapist.ui.fragment.personal.AccountRecordFragment;

import butterknife.BindView;

/**
 * 我的账户
 * Created by zty on 2016/12/26.
 */

public class AccountActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {
    @BindView(R.id.radioGroupAccount)
    RadioGroup radioGroupAccount;

    BaseFragment currentFragment;
    private int currentPage = 1;

    private AccountRecordFragment recordFragment;
    private AccountControlFragment controlFragment;

    @Override
    protected int getContentView() {
        return R.layout.activity_account;
    }

    @Override
    protected void initData() {
        left.setBackgroundResource(R.mipmap.ic_back);
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        radioGroupAccount.setOnCheckedChangeListener(this);

        if (recordFragment == null) {
            recordFragment = new AccountRecordFragment();
        }

        if (!recordFragment.isAdded()) {
            getSupportFragmentManager().beginTransaction().add(R.id.fragmentAccount, recordFragment).commit();
            currentFragment = recordFragment;
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
            case R.id.radioButtonAccount1:
                currentPage = 1;
                if (recordFragment == null) {
                    recordFragment = new AccountRecordFragment();
                }
                addOrShowFragment(getSupportFragmentManager().beginTransaction(), recordFragment);
                break;
            case R.id.radioButtonAccount2:
                currentPage = 2;
                if (controlFragment == null) {
                    controlFragment = new AccountControlFragment();
                }
                addOrShowFragment(getSupportFragmentManager().beginTransaction(), controlFragment);
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
            transaction.hide(currentFragment).add(R.id.fragmentAccount, fragment).commit();
        } else {
            transaction.hide(currentFragment).show(fragment).commit();
        }
        currentFragment = fragment;
    }

    private void setTitle() {

        if (currentPage == 1) {
            title.setText("账户明细");
        } else {
            title.setText("账户管理");
        }

    }
}
