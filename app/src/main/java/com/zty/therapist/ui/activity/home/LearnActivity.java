package com.zty.therapist.ui.activity.home;

import android.support.design.widget.TabLayout;
import android.view.View;

import com.zty.therapist.R;
import com.zty.therapist.adapter.ViewPagerAdapter;
import com.zty.therapist.base.BaseActivity;
import com.zty.therapist.base.BaseFragment;
import com.zty.therapist.manager.LearnFragmentManager;
import com.zty.therapist.ui.fragment.home.LearnFragment;
import com.zty.therapist.widget.MyViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by zty on 2016/12/16.
 */

public class LearnActivity extends BaseActivity {
    @BindView(R.id.tabLayoutTrain)
    TabLayout tabLayoutTrain;
    @BindView(R.id.viewPagerTrain)
    MyViewPager viewPagerTrain;

    private List<String> titles = new ArrayList<>();

    private List<BaseFragment> fragments = new ArrayList<>();

    private ViewPagerAdapter adapter;

    @Override
    protected int getContentView() {
        return R.layout.fragment_train;
    }

    @Override
    protected void initData() {

        title.setText("康复技能训练");

        tabLayoutTrain.setTabMode(TabLayout.MODE_FIXED);
        tabLayoutTrain.setTabTextColors(getResources().getColor(R.color.gray), getResources().getColor(R.color.colorAccent));
        titles.clear();
        titles.add("康复知识");
        titles.add("康复试题");

        fragments.clear();

        fragments.add(LearnFragment.newInstance(0));
        fragments.add(LearnFragment.newInstance(1));

        adapter = new ViewPagerAdapter(getSupportFragmentManager());
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
