package com.zty.therapist.ui.fragment.main;

import android.support.design.widget.TabLayout;

import com.zty.therapist.R;
import com.zty.therapist.adapter.ViewPagerAdapter;
import com.zty.therapist.base.BaseFragment;
import com.zty.therapist.ui.fragment.home.LearnFragment;
import com.zty.therapist.widget.MyViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by zty on 2016/11/25.
 */

public class DoctorFragment extends BaseFragment {
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
        titles.add("理论");
        titles.add("试题");
        titles.add("讲座");
        titles.add("案例");
        titles.add("指南");

        fragments.clear();

        fragments.add(LearnFragment.newInstance(0));
        fragments.add(LearnFragment.newInstance(1));
        fragments.add(LearnFragment.newInstance(2));
        fragments.add(LearnFragment.newInstance(3));
        fragments.add(LearnFragment.newInstance(4));

        adapter = new ViewPagerAdapter(getFragmentManager());
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
