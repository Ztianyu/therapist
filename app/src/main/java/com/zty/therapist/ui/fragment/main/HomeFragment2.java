package com.zty.therapist.ui.fragment.main;

import android.content.Intent;
import android.view.View;

import com.youth.banner.Banner;
import com.zty.therapist.R;
import com.zty.therapist.base.BaseFragment;
import com.zty.therapist.ui.activity.home.ClassRoomActivity;
import com.zty.therapist.ui.activity.home.CommunityActivity;
import com.zty.therapist.ui.activity.home.GroupActivity;
import com.zty.therapist.ui.activity.home.HelpActivity;
import com.zty.therapist.ui.activity.home.InfoActivity;
import com.zty.therapist.ui.activity.home.LearnActivity;
import com.zty.therapist.utils.BannerUtils;
import com.zty.therapist.utils.ViewAdaptionUtils;
import com.zty.therapist.widget.LabView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by zty on 2016/12/17.
 */

public class HomeFragment2 extends BaseFragment {
    @BindView(R.id.homeLab1)
    LabView homeLab1;
    @BindView(R.id.homeLab2)
    LabView homeLab2;
    @BindView(R.id.homeLab3)
    LabView homeLab3;
    @BindView(R.id.homeLab4)
    LabView homeLab4;
    @BindView(R.id.homeLab5)
    LabView homeLab5;
    @BindView(R.id.homeLab6)
    LabView homeLab6;
    @BindView(R.id.bannerHome)
    Banner bannerHome;

    @Override
    public int getContentVew() {
        return R.layout.fragment_home;
    }

    @Override
    public void initData() {

        ViewAdaptionUtils.LinearLayoutAdaptation(bannerHome, 300);

        List<String> images = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            images.add("http://img03.tooopen.com/images/20131102/sy_45238929299.jpg");
        }
        BannerUtils.initBanner(bannerHome, images, 1, 1);

    }

    @Override
    public void onFailureCallback(int requestCode, String errorMsg) {

    }

    @Override
    public void onSuccessCallback(int requestCode, String response) {

    }

    @OnClick({R.id.homeLab1, R.id.homeLab2, R.id.homeLab3, R.id.homeLab4, R.id.homeLab5, R.id.homeLab6})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.homeLab1:
                startActivity(new Intent(context, LearnActivity.class));
                break;
            case R.id.homeLab2:
                startActivity(new Intent(context, ClassRoomActivity.class));
                break;
            case R.id.homeLab3:
                startActivity(new Intent(context, GroupActivity.class));
                break;
            case R.id.homeLab4:
                startActivity(new Intent(context, CommunityActivity.class));
                break;
            case R.id.homeLab5:
                startActivity(new Intent(context, InfoActivity.class));
                break;
            case R.id.homeLab6:
                startActivity(new Intent(context, HelpActivity.class));
                break;
        }
    }

}
