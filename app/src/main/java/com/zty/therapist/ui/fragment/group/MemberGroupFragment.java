package com.zty.therapist.ui.fragment.group;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.RadioGroup;

import com.zty.therapist.R;
import com.zty.therapist.base.BaseFragment;
import com.zty.therapist.ui.activity.home.AddMemberActivity;
import com.zty.therapist.ui.activity.home.GroupActivity;
import com.zty.therapist.ui.activity.home.SendHouseRentActivity;
import com.zty.therapist.ui.activity.home.SendReplaceActivity;

import butterknife.BindView;

/**
 * Created by tianyu on 2016/12/31.
 */

public class MemberGroupFragment extends BaseFragment implements RadioGroup.OnCheckedChangeListener, View.OnClickListener {
    @BindView(R.id.radioGroupMember)
    RadioGroup radioGroupMember;

    MemberFragment memberFragment;
    ReplaceWorkFragment replaceWorkFragment;
    HouseRentFragment houseRentFragment;
    CommunicationFragment communicationFragment;

    BaseFragment currentFragment;

    private int currentPage = 1;

    private int type;//0:普通成员；1：组长；

    public static MemberGroupFragment newInstance(int type) {
        MemberGroupFragment fragment = new MemberGroupFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getContentVew() {
        return R.layout.fragment_member_group;
    }

    @Override
    public void initData() {
        type = getArguments().getInt("type");
        radioGroupMember.setOnCheckedChangeListener(this);

        if (memberFragment == null)
            memberFragment = new MemberFragment();

        if (!memberFragment.isAdded()) {
            getChildFragmentManager().beginTransaction().add(R.id.fragmentMemberGroup, memberFragment).commit();
            currentFragment = memberFragment;
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
            case R.id.radioButtonMember1:
                if (memberFragment == null)
                    memberFragment = new MemberFragment();
                currentPage = 1;
                addOrShowFragment(getChildFragmentManager().beginTransaction(), memberFragment);
                break;
            case R.id.radioButtonMember2:
                if (replaceWorkFragment == null)
                    replaceWorkFragment = new ReplaceWorkFragment();
                currentPage = 2;
                addOrShowFragment(getChildFragmentManager().beginTransaction(), replaceWorkFragment);
                break;
            case R.id.radioButtonMember3:
                if (houseRentFragment == null)
                    houseRentFragment = new HouseRentFragment();
                currentPage = 3;
                addOrShowFragment(getChildFragmentManager().beginTransaction(), houseRentFragment);
                break;
            case R.id.radioButtonMember4:
                if (communicationFragment == null)
                    communicationFragment = new CommunicationFragment();
                currentPage = 4;
                addOrShowFragment(getChildFragmentManager().beginTransaction(), communicationFragment);
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
            transaction.hide(currentFragment).add(R.id.fragmentMemberGroup, fragment).commit();
        } else {
            transaction.hide(currentFragment).show(fragment).commit();
        }
        currentFragment = fragment;
    }

    private void setTitle() {
        ((GroupActivity) context).right.setOnClickListener(this);
        switch (currentPage) {
            case 1:
                if (type == 0) {
                    ((GroupActivity) context).right.setVisibility(View.INVISIBLE);
                } else {
                    ((GroupActivity) context).right.setVisibility(View.VISIBLE);
                }

                ((GroupActivity) context).title.setText("成员列表");
                ((GroupActivity) context).setRight(R.mipmap.ic_add);
                break;
            case 2:
                ((GroupActivity) context).right.setVisibility(View.VISIBLE);
                ((GroupActivity) context).title.setText("替班");
                ((GroupActivity) context).setRight(R.mipmap.ic_publish);
                break;
            case 3:
                ((GroupActivity) context).right.setVisibility(View.VISIBLE);
                ((GroupActivity) context).title.setText("住所分享");
                ((GroupActivity) context).setRight(R.mipmap.ic_publish);
                break;
            case 4:
                ((GroupActivity) context).right.setVisibility(View.INVISIBLE);
                ((GroupActivity) context).title.setText("聊天室");
                break;
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.titleRight) {
            switch (currentPage) {
                case 1:
                    startActivity(new Intent(context, AddMemberActivity.class));
                    break;
                case 2:
                    startActivity(new Intent(context, SendReplaceActivity.class));
                    break;
                case 3:
                    startActivity(new Intent(context, SendHouseRentActivity.class));
                    break;
                case 4:
                    break;
            }
        }
    }
}
