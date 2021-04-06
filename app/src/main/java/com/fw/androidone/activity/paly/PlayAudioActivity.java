package com.fw.androidone.activity.paly;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.fw.androidone.R;
import com.fw.androidone.base.activity.BaseActivity;

import java.io.File;
import java.io.IOException;

/**
 * description :播放音频
 * author : apple
 * date : 2021/4/6 15:20
 */
public class PlayAudioActivity extends BaseActivity implements View.OnClickListener {

    private Button btnPaly, btnPause, btnStop;
    private MediaPlayer mediaPlayer = new MediaPlayer();

    @Override
    protected int getContentLayout() {
        return R.layout.activity_paly_audio;
    }

    @Override
    protected void initView() {
        btnPaly = findViewById(R.id.btn_play);
        btnPause = findViewById(R.id.btn_pause);
        btnStop = findViewById(R.id.btn_stop);
    }

    @Override
    protected void initAction() {
        btnPaly.setOnClickListener(this);
        btnPause.setOnClickListener(this);
        btnStop.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        if (ContextCompat.checkSelfPermission(PlayAudioActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(PlayAudioActivity.this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        } else {
            initMediaPalyer();
        }
    }

    private void initMediaPalyer() {
        try {
            File file = new File(Environment.getExternalStorageDirectory(), "Music/car.wav");
            mediaPlayer.setDataSource(file.getPath());//设置资源路径
            mediaPlayer.prepare();//准备状态
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    initMediaPalyer();
                } else {
                    Toast.makeText(PlayAudioActivity.this, "权限申请失败", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_play) {
            if (!mediaPlayer.isPlaying()) {
                mediaPlayer.start();
            }
        } else if (v.getId() == R.id.btn_pause) {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
            }
        } else if (v.getId() == R.id.btn_stop) {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.reset();
                initMediaPalyer();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    }
}
