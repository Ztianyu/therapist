package com.zty.therapist.ui.activity.home;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.RequestParams;
import com.zty.therapist.R;
import com.zty.therapist.adapter.LectureAdapter;
import com.zty.therapist.base.BaseActivity;
import com.zty.therapist.model.LectureModel;
import com.zty.therapist.model.ResultBean;
import com.zty.therapist.model.OptionModel;
import com.zty.therapist.url.RequestManager;
import com.zty.therapist.url.Urls;
import com.zty.therapist.utils.ResultUtil;

import java.util.List;

import butterknife.BindView;
import cn.droidlover.xrecyclerview.XRecyclerView;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

/**
 * Created by zty on 2017/1/5.
 */

public class ClassRoomGridActivity extends BaseActivity {
    @BindView(R.id.recyclerView)
    XRecyclerView recyclerView;

    LectureAdapter adapter;

    OptionModel model;

    @Override
    protected int getContentView() {
        return R.layout.view_recycler;
    }

    @Override
    protected void initData() {
        model = (OptionModel) getIntent().getSerializableExtra("model");

        title.setText(model.getValue());
        adapter = new LectureAdapter(this);
        recyclerView.gridLayoutManager(this, 2);
        recyclerView.setAdapter(adapter);

        fetchData();
    }

    private void fetchData() {
        RequestParams params = new RequestParams();
        params.put("type", model.getKey());
        RequestManager.get(-1, Urls.getHealthLectureList, params, this);
    }

    @Override
    public void onFailureCallback(int requestCode, String errorMsg) {

    }

    @Override
    public void onSuccessCallback(int requestCode, String response) {
        ResultBean resultBean = ResultUtil.getResult(response);
        if (resultBean.isSuccess()) {
            List<LectureModel> lectureModels = new Gson().fromJson(resultBean.getResult(), new TypeToken<List<LectureModel>>() {
            }.getType());

            if (lectureModels != null) {
                adapter.setData(lectureModels);
            }
        }
    }

    @Override
    public void onBackPressed() {
        if (JCVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }
}
