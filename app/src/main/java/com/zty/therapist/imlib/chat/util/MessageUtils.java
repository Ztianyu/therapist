package com.zty.therapist.imlib.chat.util;


import com.easemob.chat.EMMessage;
import com.easemob.chat.ImageMessageBody;
import com.easemob.chat.TextMessageBody;
import com.easemob.chat.VoiceMessageBody;
import com.zty.therapist.base.TherapistApplication;
import com.zty.therapist.imlib.chat.enity.MessageInfo;
import com.zty.therapist.imlib.utils.ImageUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zty on 2017/1/21.
 */

public class MessageUtils {

    public static List<MessageInfo> changeData(List<EMMessage> messages) {
        List<MessageInfo> messageInfoList = new ArrayList<>();

        if (messages != null && messages.size() > 0) {
            for (EMMessage emMessage : messages) {
                MessageInfo messageInfo = new MessageInfo();
                if (emMessage.getFrom().equals(TherapistApplication.getInstance().getUserId())) {
                    messageInfo.setType(Constants.CHAT_ITEM_TYPE_RIGHT);
                    messageInfo.setHeader(TherapistApplication.getInstance().getUserModel().getPhoto());
                } else {
                    messageInfo.setType(Constants.CHAT_ITEM_TYPE_LEFT);
                }

                messageInfo.setSendState(Constants.CHAT_ITEM_SEND_SUCCESS);
                messageInfo.setMsgId(emMessage.getMsgId());
                messageInfo.setFrom(emMessage.getFrom());
                if (emMessage.getType() == EMMessage.Type.TXT)
                    messageInfo.setContent(((TextMessageBody) emMessage.getBody()).getMessage());
                if (emMessage.getType() == EMMessage.Type.IMAGE) {

                    String imageLocalUrl = ((ImageMessageBody) emMessage.getBody()).getLocalUrl();
                    String remotePath = ((ImageMessageBody) emMessage.getBody()).getRemoteUrl();
                    String filePath = ImageUtils.getImagePath(remotePath);

                    String thumbRemoteUrl = ((ImageMessageBody) emMessage.getBody()).getThumbnailUrl();
                    String secret = ((ImageMessageBody) emMessage.getBody()).getSecret();

                    messageInfo.setImageLocalUrl(imageLocalUrl);
                    messageInfo.setImageSecret(secret);
                    messageInfo.setImageLocalPath(filePath);
                    messageInfo.setImageUrl(thumbRemoteUrl);
                }
                if (emMessage.getType() == EMMessage.Type.VOICE) {
                    messageInfo.setFilepath(((VoiceMessageBody) emMessage.getBody()).getRemoteUrl());
                    messageInfo.setVoiceTime(((VoiceMessageBody) emMessage.getBody()).getLength() * 1000);
                }

                messageInfoList.add(messageInfo);
            }
        }
        return messageInfoList;
    }

    public static MessageInfo changeData(EMMessage emMessage) {
        MessageInfo messageInfo = new MessageInfo();

        if (emMessage != null) {
            if (emMessage.getFrom().equals(TherapistApplication.getInstance().getUserId())) {
                messageInfo.setType(Constants.CHAT_ITEM_TYPE_RIGHT);
                messageInfo.setHeader(TherapistApplication.getInstance().getUserModel().getPhoto());
            } else {
                messageInfo.setType(Constants.CHAT_ITEM_TYPE_LEFT);
            }

            messageInfo.setSendState(Constants.CHAT_ITEM_SEND_SUCCESS);
            messageInfo.setMsgId(emMessage.getMsgId());
            messageInfo.setFrom(emMessage.getFrom());
            if (emMessage.getType() == EMMessage.Type.TXT)
                messageInfo.setContent(((TextMessageBody) emMessage.getBody()).getMessage());
            if (emMessage.getType() == EMMessage.Type.IMAGE) {

                String imageLocalUrl = ((ImageMessageBody) emMessage.getBody()).getLocalUrl();
                String remotePath = ((ImageMessageBody) emMessage.getBody()).getRemoteUrl();
                String filePath = ImageUtils.getImagePath(remotePath);

                String thumbRemoteUrl = ((ImageMessageBody) emMessage.getBody()).getThumbnailUrl();
                String secret = ((ImageMessageBody) emMessage.getBody()).getSecret();

                messageInfo.setImageLocalUrl(imageLocalUrl);
                messageInfo.setImageSecret(secret);
                messageInfo.setImageLocalPath(filePath);
                messageInfo.setImageUrl(thumbRemoteUrl);
            }
            if (emMessage.getType() == EMMessage.Type.VOICE) {
                messageInfo.setFilepath(((VoiceMessageBody) emMessage.getBody()).getRemoteUrl());
                messageInfo.setVoiceTime(((VoiceMessageBody) emMessage.getBody()).getLength() * 1000);
            }
        }
        return messageInfo;
    }
}
