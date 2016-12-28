package com.zty.therapist.ui.fragment.personal;

import android.support.design.widget.TabLayout;

import com.zty.therapist.R;
import com.zty.therapist.adapter.ViewPagerAdapter;
import com.zty.therapist.base.BaseFragment;
import com.zty.therapist.widget.MyViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by zty on 2016/12/28.
 */

public class AccountRecordFragment extends BaseFragment {
    @BindView(R.id.tabLayoutTrain)
    TabLayout tabLayoutTrain;
    @BindView(R.id.viewPagerTrain)
    MyViewPager viewPagerTrain;

    private List<String> titles = new ArrayList<>();
    private List<BaseFragment> fragments = new ArrayList<>();
    private ViewPagerAdapter adapter;

    @Override
    public int getContentVew() {
        return R.layout.fragment_train;
    }

    @Override
    public void initData() {
        tabLayoutTrain.setTabMode(TabLayout.MODE_FIXED);
        tabLayoutTrain.setTabTextColors(getResources().getColor(R.color.gray), getResources().getColor(R.color.colorAccent));
        titles.clear();
        titles.add("保健品购买");
        titles.add("医生预定");

        fragments.clear();

        fragments.add(IntegralRecordFragment.newInstance(0));
        fragments.add(IntegralRecordFragment.newInstance(1));

        adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.setFragments(fragments);
        adapter.setTitles(titles);
        viewPagerTrain.setAdapter(adapter);

        tabLayoutTrain.setupWithViewPager(viewPagerTrain);

    }

    @Override
    public void onFailureCallback(int requestCode, String errorMsg) {

    }

    @Override
    public void onSuccessCallback(int requestCode, String response) {

    }

}
