package com.fw.androidone.activity.paly;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.fw.androidone.R;
import com.fw.androidone.base.activity.BaseActivity;

import java.io.File;

/**
 * description :播放视频
 * author : apple
 * date : 2021/4/6 15:20
 */
public class PlayVideoActivity extends BaseActivity implements View.OnClickListener {

    private Button btnPaly, btnPause, btnStop;
    private VideoView videoView;

    @Override
    protected int getContentLayout() {
        return R.layout.activity_paly_video;
    }

    @Override
    protected void initView() {
        btnPaly = findViewById(R.id.btn_play_video);
        btnPause = findViewById(R.id.btn_pause_video);
        btnStop = findViewById(R.id.btn_rePlay_video);
        videoView = findViewById(R.id.video_view);
    }

    @Override
    protected void initAction() {
        btnPaly.setOnClickListener(this);
        btnPause.setOnClickListener(this);
        btnStop.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        if (ContextCompat.checkSelfPermission(PlayVideoActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(PlayVideoActivity.this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        } else {
            initVideoView();
        }
    }

    private void initVideoView() {
        File file = new File(Environment.getExternalStorageDirectory(), "VID_20190502_162620.mp4");
        videoView.setVideoPath(file.getPath());
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    initVideoView();
                } else {
                    Toast.makeText(PlayVideoActivity.this, "权限申请失败", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_play_video) {
            if (!videoView.isPlaying()) {
                videoView.start();
            }
        } else if (v.getId() == R.id.btn_pause_video) {
            if (videoView.isPlaying()) {
                videoView.pause();
            }
        } else if (v.getId() == R.id.btn_rePlay_video) {
            if (videoView.isPlaying()) {
                videoView.resume();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (videoView != null) {
            videoView.suspend();
        }
    }
}
