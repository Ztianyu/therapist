package com.zty.therapist.ui.activity.personal;


import com.zty.therapist.adapter.MessageAdapter;
import com.zty.therapist.base.BaseRefreshActivity;
import com.zty.therapist.model.MessageModel;

import java.util.ArrayList;
import java.util.List;

/**
 * 消息中心
 * Created by zty on 2016/12/26.
 */

public class MessageActivity extends BaseRefreshActivity {

    @Override
    protected void initReadyData() {
        title.setText("我的消息");
    }

    @Override
    protected void setAdapter() {
        adapter = new MessageAdapter(this);
    }

    @Override
    protected boolean isHaveDivider() {
        return false;
    }

    @Override
    protected void fetchData() {
        List<MessageModel> models = new ArrayList<>();

        MessageModel model = new MessageModel();
        model.setType(0);
        model.setTitle("公告通知");
        model.setContent("我公司现已经与北京、上海、天津、西安、成都、广州的三甲医院知名专家建立了战略合作，需要名医手术的患者，可通过我们的平台预定，比在大医院做手术便宜很多。");
        models.add(model);
        model = new MessageModel();
        model.setType(1);
        model.setTitle("加入组员通知");
        model.setContent("组长王丽邀请您加入到她的组里，加入组后系统赠送您积分，并可以与其他组员进行互动。");
        models.add(model);
        model = new MessageModel();
        model.setType(1);
        model.setTitle("升级组长通知");
        model.setContent("系统管理员提升您为组长，提升为组长后，系统每月给您100积分，积分可用来替班使用，另外优先购买集体社保。");
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
}
