package com.fw.androidone.activity.photo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;

import com.fw.androidone.R;
import com.fw.androidone.base.activity.BaseActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * description :调用摄像头拍照
 * author : apple
 * date : 2021/3/29 16:12
 */
public class TakePhotoActivity extends BaseActivity {
    private Button button;
    private ImageView imageView;

    public static final int TAKE_PHOTO = 1;
    private Uri imgUri;

    @Override
    protected int getContentLayout() {
        return R.layout.activity_take_photo;
    }

    @Override
    protected void initView() {
        button = findViewById(R.id.btn_take_photo);
        imageView = findViewById(R.id.img_photo);
    }

    @Override
    protected void initAction() {

    }

    @Override
    protected void initData() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //创建File对象，用于存存储拍照后的图片
                File file = new File(getExternalCacheDir(), "out_put.jpg");
                try {
                    if (file.exists()) {
                        file.delete();
                    }
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //大于等于android7，使用FileProvider对数据进行保护
                if (Build.VERSION.SDK_INT >= 24) {
                    imgUri = FileProvider.getUriForFile(TakePhotoActivity.this,
                            "com.fw.androidone.fileprovider", file);
                } else {
                    imgUri = Uri.fromFile(file);
                }
                //启动相机程序
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imgUri);
                startActivityForResult(intent, TAKE_PHOTO);
            }
        });
    }

    @SuppressLint("MissingSuperCall")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode) {
            case TAKE_PHOTO:
                if (resultCode == RESULT_OK) {
                    try {
                        //显示图片
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imgUri));
                        imageView.setImageBitmap(bitmap);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;
            default:
                break;
        }
    }
}
