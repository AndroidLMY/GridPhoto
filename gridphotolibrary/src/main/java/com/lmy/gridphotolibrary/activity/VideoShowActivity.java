package com.lmy.gridphotolibrary.activity;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;

import androidx.appcompat.app.AppCompatActivity;

import com.lmy.gridphotolibrary.R;
import com.lmy.gridphotolibrary.utils.title_utils.StatusBarUtil;
import com.lmy.gridphotolibrary.view.CustomerVideoView;


/**
 * @功能: 展示视频的详情界面
 * @Creat 2020/3/18 15:43
 * @User Lmy
 * @Compony JinAnChang
 */
public class VideoShowActivity extends AppCompatActivity implements View.OnClickListener {
    private static String video_url;
    private CustomerVideoView videoplayer;
    private ImageView ivBack;

    public static void show(Context context, String url) {
        context.startActivity(new Intent(context, VideoShowActivity.class));
        VideoShowActivity.video_url = url;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_show);
        StatusBarUtil.setRootViewFitsSystemWindows(this, false);
        StatusBarUtil.setTranslucentStatus(this);
        initView();
    }

    private void initView() {
        videoplayer = findViewById(R.id.videoplayer);
        ivBack = findViewById(R.id.iv_back);
        ivBack.setOnClickListener(this);
        initVideoView();
    }

    private void initVideoView() {
        MediaController mc = new MediaController(this);//Video是我类名，是你当前的类
//        mVideoView.setMediaController(mc);//设置VedioView与MediaController相关联 设置进度条
        videoplayer.start();
        videoplayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                //视频加载完成,准备好播放视频的回调

            }
        });
        videoplayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                //视频播放完成后的回调
                videoplayer.start();
            }
        });
        videoplayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                //异常回调
                return false;//如果方法处理了错误，则为true；否则为false。返回false或根本没有OnErrorListener，将导致调用OnCompletionListener。
            }
        });
        videoplayer.setOnInfoListener(new MediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(MediaPlayer mp, int what, int extra) {
                //信息回调
//                what 对应返回的值如下
//                public static final int MEDIA_INFO_UNKNOWN = 1;  媒体信息未知
//                public static final int MEDIA_INFO_VIDEO_TRACK_LAGGING = 700; 媒体信息视频跟踪滞后
//                public static final int MEDIA_INFO_VIDEO_RENDERING_START = 3; 媒体信息\视频渲染\开始
//                public static final int MEDIA_INFO_BUFFERING_START = 701; 媒体信息缓冲启动
//                public static final int MEDIA_INFO_BUFFERING_END = 702; 媒体信息缓冲结束
//                public static final int MEDIA_INFO_NETWORK_BANDWIDTH = 703; 媒体信息网络带宽（703）
//                public static final int MEDIA_INFO_BAD_INTERLEAVING = 800; 媒体-信息-坏-交错
//                public static final int MEDIA_INFO_NOT_SEEKABLE = 801; 媒体信息找不到
//                public static final int MEDIA_INFO_METADATA_UPDATE = 802; 媒体信息元数据更新
//                public static final int MEDIA_INFO_UNSUPPORTED_SUBTITLE = 901; 媒体信息不支持字幕
//                public static final int MEDIA_INFO_SUBTITLE_TIMED_OUT = 902; 媒体信息字幕超时
                return false; //如果方法处理了信息，则为true；如果没有，则为false。返回false或根本没有OnInfoListener，将导致丢弃该信息。
            }
        });
    }


    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_back) {
            finish();
        }
    }
}
