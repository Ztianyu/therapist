package com.zty.therapist.imlib.chat.ui.activity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.easemob.chat.EMMessage;
import com.easemob.chat.ImageMessageBody;
import com.easemob.util.EMLog;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.zty.therapist.R;
import com.zty.therapist.base.BaseActivity;
import com.zty.therapist.base.TherapistApplication;
import com.zty.therapist.imlib.LibManger;
import com.zty.therapist.imlib.OnSendMessageListener;
import com.zty.therapist.imlib.ShowBigImage;
import com.zty.therapist.imlib.chat.adapter.ChatAdapter;
import com.zty.therapist.imlib.chat.adapter.CommonFragmentPagerAdapter;
import com.zty.therapist.imlib.chat.enity.FullImageInfo;
import com.zty.therapist.imlib.chat.enity.MessageInfo;
import com.zty.therapist.imlib.chat.ui.fragment.ChatEmotionFragment;
import com.zty.therapist.imlib.chat.ui.fragment.ChatFunctionFragment;
import com.zty.therapist.imlib.chat.util.Constants;
import com.zty.therapist.imlib.chat.util.GlobalOnItemClickManagerUtils;
import com.zty.therapist.imlib.chat.util.MediaManager;
import com.zty.therapist.imlib.chat.util.MessageUtils;
import com.zty.therapist.imlib.chat.widget.EmotionInputDetector;
import com.zty.therapist.imlib.chat.widget.NoScrollViewPager;
import com.zty.therapist.imlib.chat.widget.StateButton;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：Rance on 2016/11/29 10:47
 * 邮箱：rance935@163.com
 */
public class ChatActivity extends BaseActivity implements OnSendMessageListener {

    @BindView(R.id.chat_list)
    EasyRecyclerView chatList;
    @BindView(R.id.emotion_voice)
    ImageView emotionVoice;
    @BindView(R.id.edit_text)
    EditText editText;
    @BindView(R.id.voice_text)
    TextView voiceText;
    @BindView(R.id.emotion_button)
    ImageView emotionButton;
    @BindView(R.id.emotion_add)
    ImageView emotionAdd;
    @BindView(R.id.emotion_send)
    StateButton emotionSend;
    @BindView(R.id.viewpager)
    NoScrollViewPager viewpager;
    @BindView(R.id.emotion_layout)
    RelativeLayout emotionLayout;

    private EmotionInputDetector mDetector;
    private ArrayList<Fragment> fragments;
    private ChatEmotionFragment chatEmotionFragment;
    private ChatFunctionFragment chatFunctionFragment;
    private CommonFragmentPagerAdapter adapter;

    private ChatAdapter chatAdapter;
    private LinearLayoutManager layoutManager;
    private List<MessageInfo> messageInfos;
    //录音相关
    int animationRes = 0;
    int res = 0;
    AnimationDrawable animationDrawable = null;
    private ImageView animView;

    private String groupId;

    private String userHeader;

    @Override
    protected int getContentView() {
        return R.layout.activity_chat;
    }

    @Override
    protected void initData() {
        title.setText("聊天室");

        userHeader = TherapistApplication.getInstance().getUserModel().getPhoto();

        groupId = getIntent().getStringExtra("groupId");
        EventBus.getDefault().register(this);
        initWidget();
    }

    private void initWidget() {
        fragments = new ArrayList<>();
        chatEmotionFragment = new ChatEmotionFragment();
        fragments.add(chatEmotionFragment);
        chatFunctionFragment = new ChatFunctionFragment();
        fragments.add(chatFunctionFragment);
        adapter = new CommonFragmentPagerAdapter(getSupportFragmentManager(), fragments);
        viewpager.setAdapter(adapter);
        viewpager.setCurrentItem(0);

        mDetector = EmotionInputDetector.with(this)
                .setEmotionView(emotionLayout)
                .setViewPager(viewpager)
                .bindToContent(chatList)
                .bindToEditText(editText)
                .bindToEmotionButton(emotionButton)
                .bindToAddButton(emotionAdd)
                .bindToSendButton(emotionSend)
                .bindToVoiceButton(emotionVoice)
                .bindToVoiceText(voiceText)
                .build();

        GlobalOnItemClickManagerUtils globalOnItemClickListener = GlobalOnItemClickManagerUtils.getInstance(this);
        globalOnItemClickListener.attachToEditText(editText);

        chatAdapter = new ChatAdapter(this);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        chatList.setLayoutManager(layoutManager);
        chatList.setAdapter(chatAdapter);
        chatList.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                switch (newState) {
                    case RecyclerView.SCROLL_STATE_IDLE:
                        chatAdapter.handler.removeCallbacksAndMessages(null);
                        chatAdapter.notifyDataSetChanged();
                        break;
                    case RecyclerView.SCROLL_STATE_DRAGGING:
                        chatAdapter.handler.removeCallbacksAndMessages(null);
                        mDetector.hideEmotionLayout(false);
                        mDetector.hideSoftInput();
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
        chatAdapter.addItemClickListener(itemClickListener);

        messageInfos = new ArrayList<>();
        chatAdapter.addAll(messageInfos);

        LoadData();
    }

    /**
     * item点击事件
     */
    private ChatAdapter.onItemClickListener itemClickListener = new ChatAdapter.onItemClickListener() {
        @Override
        public void onHeaderClick(int position) {
            Toast.makeText(ChatActivity.this, "onHeaderClick", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onImageClick(View view, int position) {

            Intent intent = new Intent(ChatActivity.this, ShowBigImage.class);
            File file = new File(messageInfos.get(position).getImageLocalPath());
            if (file.exists()) {
                Uri uri = Uri.fromFile(file);
                intent.putExtra("uri", uri);
            } else {
                intent.putExtra("secret", messageInfos.get(position).getImageSecret());
                intent.putExtra("remotepath", messageInfos.get(position).getImageUrl());
            }
            startActivity(intent);
        }

        @Override
        public void onVoiceClick(final ImageView imageView, final int position) {
            if (animView != null) {
                animView.setImageResource(res);
                animView = null;
            }
            switch (messageInfos.get(position).getType()) {
                case 1:
                    animationRes = R.drawable.voice_left;
                    res = R.mipmap.icon_voice_left3;
                    break;
                case 2:
                    animationRes = R.drawable.voice_right;
                    res = R.mipmap.icon_voice_right3;
                    break;
            }
            animView = imageView;
            animView.setImageResource(animationRes);
            animationDrawable = (AnimationDrawable) imageView.getDrawable();
            animationDrawable.start();
            MediaManager.playSound(messageInfos.get(position).getFilepath(), new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    animView.setImageResource(res);
                }
            });
        }
    };

    /**
     * 构造聊天数据
     */
    private void LoadData() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                final List<EMMessage> emMessageList = LibManger.getMessages(groupId);
                messageInfos.clear();
                messageInfos.addAll(MessageUtils.changeData(emMessageList));

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        chatAdapter.clear();
                        chatAdapter.addAll(messageInfos);
                        adapter.notifyDataSetChanged();
                        chatList.scrollToPosition(chatAdapter.getCount() - 1);
                    }
                });
            }
        }).start();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void MessageEventBus(final MessageInfo messageInfo) {

        messageInfo.setHeader(userHeader);
        messageInfo.setType(Constants.CHAT_ITEM_TYPE_RIGHT);
        messageInfo.setSendState(Constants.CHAT_ITEM_SENDING);
        LibManger.sendMessage(groupId, messageInfo, this);
//        messageInfos.add(messageInfo);
//        chatAdapter.add(messageInfo);
//        chatList.scrollToPosition(chatAdapter.getCount() - 1);
    }

    @Override
    public void onBackPressed() {
        if (!mDetector.interceptBackPress()) {
            super.onBackPressed();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().removeStickyEvent(this);
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onFailureCallback(int requestCode, String errorMsg) {

    }

    @Override
    public void onSuccessCallback(int requestCode, String response) {

    }

    @Override
    public void onSuccess(final MessageInfo messageInfo) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                LoadData();
//                messageInfo.setSendState(Constants.CHAT_ITEM_SEND_SUCCESS);
//                chatAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onError(final MessageInfo messageInfo, String error) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                LoadData();
//                messageInfo.setSendState(Constants.CHAT_ITEM_SEND_ERROR);
//                chatAdapter.notifyDataSetChanged();
            }
        });
    }
}
