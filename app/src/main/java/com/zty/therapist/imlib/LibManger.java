package com.zty.therapist.imlib;

import android.text.TextUtils;

import com.easemob.EMCallBack;
import com.easemob.EMValueCallBack;
import com.easemob.chat.EMChatManager;
import com.easemob.chat.EMConversation;
import com.easemob.chat.EMGroup;
import com.easemob.chat.EMGroupManager;
import com.easemob.chat.EMMessage;
import com.easemob.chat.ImageMessageBody;
import com.easemob.chat.TextMessageBody;
import com.easemob.chat.VoiceMessageBody;
import com.zty.therapist.imlib.chat.enity.MessageInfo;
import com.zty.therapist.imlib.chat.util.Constants;
import com.zty.therapist.imlib.utils.OnGroupCallback;

import java.io.File;
import java.util.List;

/**
 * Created by zty on 2016/6/14.
 */
public class LibManger {

    public static void login(String easemobName, String password, final LoginCallback callback) {

        if (!TextUtils.isEmpty(easemobName) && !TextUtils.isEmpty(password))
            EMChatManager.getInstance().login(easemobName, password, new EMCallBack() {
                @Override
                public void onSuccess() {
                    //以下两个方法是为了保证进入主页面后本地会话和群组都 load 完毕
                    EMGroupManager.getInstance().loadAllGroups();
                    EMChatManager.getInstance().loadAllConversations();

                    if (callback != null)
                        callback.onLoginSuccess();
                }

                @Override
                public void onError(int i, final String s) {
                }

                @Override
                public void onProgress(int i, String s) {
                }
            });
    }

    public static void exit() {
        //此方法为异步方法
        EMChatManager.getInstance().logout(new EMCallBack() {
            @Override
            public void onSuccess() {
            }

            @Override
            public void onProgress(int progress, String status) {
            }

            @Override
            public void onError(int code, String message) {

            }
        });
    }

    /**
     * 从服务器获取自己加入的和创建的群聊列表，此API获取的群组SDK会自动保存到内存和db。
     */
    public static void getGroupFromServer(final OnGroupCallback callback) {

        EMGroupManager.getInstance().asyncGetGroupsFromServer(new EMValueCallBack<List<EMGroup>>() {

            @Override
            public void onSuccess(List<EMGroup> value) {
                callback.onGroupSuccess(value);
            }

            @Override
            public void onError(int error, String errorMsg) {
                callback.onGroupError(errorMsg);
            }
        });
    }

    public static void sendMessage(String userName, final MessageInfo messageInfo, final OnSendMessageListener listener) {

        EMConversation conversation = EMChatManager.getInstance().getConversation(userName);
        EMMessage message = null;
        //文字消息
        if (!TextUtils.isEmpty(messageInfo.getContent())) {
            EMMessage.createSendMessage(EMMessage.Type.TXT);
            message.setChatType(EMMessage.ChatType.GroupChat);
            TextMessageBody txtBody = new TextMessageBody(messageInfo.getContent());
            message.addBody(txtBody);
            message.setReceipt(userName);
        }
        //图片消息
        if (!TextUtils.isEmpty(messageInfo.getImageUrl())) {
            message = EMMessage.createSendMessage(EMMessage.Type.IMAGE);
            message.setChatType(EMMessage.ChatType.GroupChat);
            ImageMessageBody body = new ImageMessageBody(new File(messageInfo.getImageUrl()));
            message.addBody(body);
            message.setReceipt(userName);
            conversation.addMessage(message);
        }

        //语音消息
        if (!TextUtils.isEmpty(messageInfo.getFilepath())) {
            message = EMMessage.createSendMessage(EMMessage.Type.VOICE);
            message.setChatType(EMMessage.ChatType.GroupChat);
            VoiceMessageBody body = new VoiceMessageBody(new File(messageInfo.getFilepath()), (int) (messageInfo.getVoiceTime() / 1000));
            message.addBody(body);
            message.setReceipt(userName);
            conversation.addMessage(message);
        }

        //发送消息
        EMChatManager.getInstance().sendMessage(message, new EMCallBack() {
            @Override
            public void onSuccess() {
                listener.onSuccess(messageInfo);
            }

            @Override
            public void onError(int i, String s) {
                listener.onError(messageInfo, s);
            }

            @Override
            public void onProgress(int i, String s) {

            }
        });
    }
}
