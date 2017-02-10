package com.zty.therapist.imlib;

import android.content.Context;
import android.content.Intent;

import com.easemob.chat.EMMessage;
import com.zty.therapist.R;
import com.zty.therapist.imlib.chat.ui.activity.ChatActivity;
import com.zty.therapist.imlib.model.HXNotifier;

/**
 * Created by zty on 2017/2/10.
 */

public class ChatNotification {

    public static void init(final Context context) {

        DemoHXSDKHelper sdkHelper = (DemoHXSDKHelper) DemoHXSDKHelper.getInstance();

        HXNotifier mHXNotifier = sdkHelper.getNotifier();
        mHXNotifier.init(context);

        mHXNotifier.setNotificationInfoProvider(new HXNotifier.HXNotificationInfoProvider() {
            @Override
            public String getDisplayedText(EMMessage message) {
                return message.getBody().toString();
            }

            @Override
            public String getLatestText(EMMessage message, int fromUsersNum, int messageNum) {
                return message.getBody().toString().replace("txt:", "");
            }

            @Override
            public String getTitle(EMMessage message) {
                return "有新消息了";
            }

            @Override
            public int getSmallIcon(EMMessage message) {
                return R.mipmap.ic_launcher;
            }

            @Override
            public Intent getLaunchIntent(EMMessage message) {
                Intent intent = new Intent();
                intent.putExtra("groupId", message.getTo());
                intent.setClass(context, ChatActivity.class);
                return intent;
            }
        });
    }
}
