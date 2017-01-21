package com.zty.therapist.ui.fragment.main;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.youth.banner.Banner;
import com.zty.therapist.R;
import com.zty.therapist.base.BaseFragment;
import com.zty.therapist.model.ResultBean;
import com.zty.therapist.ui.activity.home.ClassRoomActivity;
import com.zty.therapist.ui.activity.home.CommunityActivity;
import com.zty.therapist.ui.activity.home.GroupActivity;
import com.zty.therapist.ui.activity.home.HelpActivity;
import com.zty.therapist.ui.activity.home.InfoActivity;
import com.zty.therapist.ui.activity.home.LearnActivity;
import com.zty.therapist.url.RequestManager;
import com.zty.therapist.url.Urls;
import com.zty.therapist.utils.BannerUtils;
import com.zty.therapist.utils.ResourceUtil;
import com.zty.therapist.utils.ResultUtil;
import com.zty.therapist.utils.ViewAdaptionUtils;
import com.zty.therapist.widget.LabView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
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
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.textToolTitle)
    TextView textToolTitle;

    @Override
    public int getContentVew() {
        return R.layout.fragment_home;
    }

    @Override
    public void initData() {
        textToolTitle.setText(ResourceUtil.resToStr(context, R.string.app_name));

        ViewAdaptionUtils.LinearLayoutAdaptation(bannerHome, 400);

        List<Integer> images = new ArrayList<>();

        images.add(R.drawable.pic_home1);
        images.add(R.drawable.pic_home2);
        images.add(R.drawable.pic_home3);
        images.add(R.drawable.pic_home4);
        images.add(R.drawable.pic_home5);
        images.add(R.drawable.pic_home6);
        images.add(R.drawable.pic_home7);

        BannerUtils.initBanner1(bannerHome, images, 1, 1);

        RequestManager.get(-1, Urls.getSystemBulletinList, null, this);

    }

    @Override
    public void onFailureCallback(int requestCode, String errorMsg) {

    }

    @Override
    public void onSuccessCallback(int requestCode, String response) {
        ResultBean resultBean = ResultUtil.getResult(response);
        if (resultBean.isSuccess()) {

        }
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
