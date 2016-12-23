package com.zty.therapist.ui.fragment.main;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.zty.therapist.R;
import com.zty.therapist.adapter.TaskAdapter;
import com.zty.therapist.base.BaseFragment;
import com.zty.therapist.manager.LayoutManager;
import com.zty.therapist.model.TaskModel;
import com.zty.therapist.recycler.OnItemClickListener;
import com.zty.therapist.recycler.ViewHolder;
import com.zty.therapist.ui.activity.task.TaskDetailsActivity;
import com.zty.therapist.utils.ImageLoader;
import com.zty.therapist.widget.CircleImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by zty on 2016/11/25.
 */

public class HomeFragment extends BaseFragment implements OnItemClickListener<TaskModel> {
    @BindView(R.id.imgHomeUser)
    CircleImageView imgHomeUser;
    @BindView(R.id.textHomeName)
    TextView textHomeName;
    @BindView(R.id.textHomeAge)
    TextView textHomeAge;
    @BindView(R.id.textHomeTitle)
    TextView textHomeTitle;
    @BindView(R.id.listCurrentTask)
    RecyclerView listCurrentTask;

    TaskAdapter adapter;

    List<TaskModel> models = new ArrayList<>();

    @Override
    public int getContentVew() {
        return R.layout.fragment_main;
    }

    @Override
    public void initData() {

        LayoutManager.setVertical(context, listCurrentTask);

        for (int i = 0; i < 10; i++) {
            models.add(new TaskModel());
        }
        adapter = new TaskAdapter(context);

        listCurrentTask.setAdapter(adapter);

        adapter.setOnItemClickListener(this);

        ImageLoader.load(context, "http://img03.tooopen.com/images/20131102/sy_45238929299.jpg", imgHomeUser);
    }

    @Override
    public void onFailureCallback(int requestCode, String errorMsg) {

    }

    @Override
    public void onSuccessCallback(int requestCode, String response) {

    }

    @OnClick(R.id.imgHomeUser)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgHomeUser:
                Toast.makeText(context, "aaaaa", Toast.LENGTH_SHORT).show();
                break;
        }

    }

    @Override
    public void onCommonItemClick(ViewHolder viewHolder, TaskModel taskModel, int position) {
        startActivity(new Intent(context, TaskDetailsActivity.class).putExtra("model", taskModel));
    }

    @Override
    public void onLoadItemClick() {

    }
}
