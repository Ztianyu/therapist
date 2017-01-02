package com.zty.therapist.ui.fragment.monitor;

import android.graphics.drawable.Drawable;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.zty.therapist.R;
import com.zty.therapist.base.BaseFragment;
import com.zty.therapist.ui.activity.home.GroupActivity;
import com.zty.therapist.ui.fragment.group.CommunicationFragment;
import com.zty.therapist.ui.fragment.group.HouseRentFragment;
import com.zty.therapist.ui.fragment.group.MemberFragment;
import com.zty.therapist.ui.fragment.group.ReplaceWorkFragment;
import com.zty.therapist.utils.ResourceUtil;

import butterknife.BindView;

/**
 * Created by tianyu on 2016/12/31.
 */

public class MonitorGroupFragment extends BaseFragment implements RadioGroup.OnCheckedChangeListener, View.OnClickListener {
    @BindView(R.id.radioGroupMember)
    RadioGroup radioGroupMember;
    BaseFragment currentFragment;
    @BindView(R.id.radioButtonMember1)
    RadioButton radioButtonMember1;
    @BindView(R.id.radioButtonMember2)
    RadioButton radioButtonMember2;
    @BindView(R.id.radioButtonMember3)
    RadioButton radioButtonMember3;
    @BindView(R.id.radioButtonMember4)
    RadioButton radioButtonMember4;

    MemberFragment memberFragment;
    ReplaceWorkFragment replaceWorkFragment;
    HouseRentFragment houseRentFragment;
    CommunicationFragment communicationFragment;


    private int currentPage = 1;

    @Override
    public int getContentVew() {
        return R.layout.fragment_member_group;
    }

    @Override
    public void initData() {
        radioButtonMember1.setText(ResourceUtil.resToStr(context, R.string.monitorTip1));
        radioButtonMember2.setText(ResourceUtil.resToStr(context, R.string.monitorTip2));
        radioButtonMember3.setText(ResourceUtil.resToStr(context, R.string.monitorTip3));
        radioButtonMember4.setText(ResourceUtil.resToStr(context, R.string.monitorTip4));

        Drawable drawable1 = ResourceUtil.resToDrawable(context, R.drawable.tab_groups_selector);
        drawable1.setBounds(0, 0, drawable1.getMinimumWidth(), drawable1.getMinimumHeight());
        Drawable drawable2 = ResourceUtil.resToDrawable(context, R.drawable.tab_doctors_selector);
        drawable2.setBounds(0, 0, drawable2.getMinimumWidth(), drawable2.getMinimumHeight());
        Drawable drawable3 = ResourceUtil.resToDrawable(context, R.drawable.tab_notices_selector);
        drawable3.setBounds(0, 0, drawable3.getMinimumWidth(), drawable3.getMinimumHeight());
        Drawable drawable4 = ResourceUtil.resToDrawable(context, R.drawable.tab_communication_selector);
        drawable4.setBounds(0, 0, drawable4.getMinimumWidth(), drawable4.getMinimumHeight());

        radioButtonMember1.setCompoundDrawables(null, drawable1, null, null);
        radioButtonMember2.setCompoundDrawables(null, drawable2, null, null);
        radioButtonMember3.setCompoundDrawables(null, drawable3, null, null);
        radioButtonMember4.setCompoundDrawables(null, drawable4, null, null);

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
                ((GroupActivity) context).right.setVisibility(View.VISIBLE);
                ((GroupActivity) context).title.setText("组长列表");
                ((GroupActivity) context).right.setBackgroundResource(R.mipmap.ic_add);
                break;
            case 2:
                ((GroupActivity) context).right.setVisibility(View.INVISIBLE);
                ((GroupActivity) context).title.setText("医生对接");
                break;
            case 3:
                ((GroupActivity) context).right.setVisibility(View.VISIBLE);
                ((GroupActivity) context).title.setText("通知公告");
                ((GroupActivity) context).right.setBackgroundResource(R.mipmap.ic_publish);
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
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
            }
        }
    }
}
