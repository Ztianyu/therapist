package com.zty.therapist.ui.activity.home;

import android.content.Intent;

import com.zty.therapist.R;
import com.zty.therapist.adapter.CommunityAdapter;
import com.zty.therapist.base.BaseRefreshActivity;
import com.zty.therapist.model.CommunityModel;

import java.util.ArrayList;
import java.util.List;

/**
 * 感情天地
 * Created by zty on 2016/12/17.
 */

public class CommunityActivity extends BaseRefreshActivity {
    @Override
    protected void initReadyData() {
        title.setText("感情天地");
        right.setBackgroundResource(R.mipmap.ic_publish);
    }

    @Override
    protected void setAdapter() {
        adapter = new CommunityAdapter(this, getSupportFragmentManager());
    }

    @Override
    protected boolean isHaveDivider() {
        return true;
    }

    @Override
    protected void fetchData() {
        List<CommunityModel> models = new ArrayList<>();
        CommunityModel model = new CommunityModel();

        List<String> urls = new ArrayList<>();
        urls.add("https://gss0.baidu.com/7Po3dSag_xI4khGko9WTAnF6hhy/zhidao/wh%3D600%2C800/sign=d53f032478f40ad115b1cfe5671c3de7/962bd40735fae6cda4cd9f7d0cb30f2442a70fb4.jpg");
        urls.add("http://img1.imgtn.bdimg.com/it/u=2822571390,539988323&fm=214&gp=0.jpg");
        urls.add("http://img3.imgtn.bdimg.com/it/u=1104528977,2768410894&fm=21&gp=0.jpg");
        urls.add("http://img22.mtime.cn/up/2011/01/05/133717.71056963_500.jpg");
        urls.add("https://gss0.baidu.com/7Po3dSag_xI4khGko9WTAnF6hhy/zhidao/wh%3D600%2C800/sign=d53f032478f40ad115b1cfe5671c3de7/962bd40735fae6cda4cd9f7d0cb30f2442a70fb4.jpg");
        urls.add("http://img1.imgtn.bdimg.com/it/u=2822571390,539988323&fm=214&gp=0.jpg");
        urls.add("http://img3.imgtn.bdimg.com/it/u=1104528977,2768410894&fm=21&gp=0.jpg");
        urls.add("http://img22.mtime.cn/up/2011/01/05/133717.71056963_500.jpg");
        model.setUrls(urls);
        models.add(model);
        models.add(model);
        models.add(model);
        models.add(model);
        models.add(model);

        if (isLoadMore) {
            adapter.notifyBottomRefresh(models);
            mTempPageCount++;
        } else {
            adapter.notifyTopRefresh(models);
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void onFailureCallback(int requestCode, String errorMsg) {

    }

    @Override
    public void onSuccessCallback(int requestCode, String response) {

    }

    @Override
    public void rightClick() {
        startActivity(new Intent(CommunityActivity.this, PublishActivity.class));
    }
}
