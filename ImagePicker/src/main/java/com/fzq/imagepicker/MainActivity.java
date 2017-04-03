package com.fzq.imagepicker;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;
import com.linchaolong.android.imagepicker.ImagePicker;
import com.linchaolong.android.imagepicker.cropper.CropImage;
import com.linchaolong.android.imagepicker.cropper.CropImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImagePicker imagePicker = new ImagePicker();
    private SimpleDraweeView draweeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*
        在魅族M5的手机运行时报错如下：   主要原因还是activity_main.xml有问题.
        android.view.InflateException: Error inflating class com.facebook.drawee.view.SimpleDraweeView
        原因是没有AndroidManifest.xml在添加MyApplication类，
        */
        System.out.println("===MainActivity.java  onCreate() : start ");
        imagePicker.setTitle("设置头像");
        //设置是否裁剪图片
        imagePicker.setCropImage(true);
        System.out.println("===MainActivity.java  onCreate() : 11111");
        findViewById(R.id.fragmentTest).setOnClickListener(this);
        draweeView = (SimpleDraweeView) findViewById(R.id.draweeView);
        draweeView.setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        imagePicker.onActivityResult(this, resultCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        imagePicker.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.draweeView:
                startChooser();
                break;
            case R.id.fragmentTest:
                Intent intent = new Intent(this, FragmentTestActivity.class);
                startActivity(intent);
                break;

        }
    }

    private void startChooser() {
        imagePicker.startChooser(this, new ImagePicker.Callback() {
            //选择图片的回调
            @Override
            public void onPickImage(Uri imageUri) {

            }

            //裁剪图片的回调

            @Override
            public void onCropImage(Uri imageUri) {
                draweeView.setImageURI(imageUri);
                draweeView.getHierarchy().setRoundingParams(RoundingParams.asCircle());
            }


            //自定义裁剪配置

            @Override
            public void cropConfig(CropImage.ActivityBuilder builder) {
                builder
                        // 是否启动多点触摸
                        .setMultiTouchEnabled(false)
                        // 设置网格显示模式
                        .setGuidelines(CropImageView.Guidelines.OFF)
                        // 圆形/矩形
                        .setCropShape(CropImageView.CropShape.RECTANGLE)
                        // 调整裁剪后的图片最终大小
                        .setRequestedSize(960, 540)
                        // 宽高比
                        .setAspectRatio(16, 9);
            }


            //用户拒绝授权回调

            @Override
            public void onPermissionDenied(int requestCode, String[] permissions, int[] grantResults) {
                super.onPermissionDenied(requestCode, permissions, grantResults);
            }
        });
    }
}
