package com.fzq.imagepicker;


import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.linchaolong.android.imagepicker.ImagePicker;
import com.linchaolong.android.imagepicker.cropper.CropImageView;

/**
 * Created by fzq on 2017/3/28.
 */

public class TestFragment extends Fragment {

    private ImagePicker imagePicker = new ImagePicker();
    private ImageView imageView;
    private CropImageView cropImageView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_test, null);

        imageView = (ImageView) rootView.findViewById(R.id.imageView);
        cropImageView = (CropImageView) rootView.findViewById(R.id.cropImageView);
        cropImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startCameraOrGallery();
            }
        });

        return rootView;
    }

    private void startCameraOrGallery() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("设置头像");
        builder.setItems(new String[]{"从相册中选取图片", "拍照"}, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                ImagePicker.Callback callback = new ImagePicker.Callback() {
                    @Override
                    public void onPickImage(Uri imageUri) {

                    }

                    @Override
                    public void onCropImage(Uri imageUri) {
                        imageView.setImageURI(imageUri);
                        cropImageView.setImageUriAsync(imageUri);
                    }
                };

                if(which == 0){
                    //从相册中选择
                    imagePicker.startGallery(TestFragment.this, callback);
                    //拍照
                    imagePicker.startCamera(TestFragment.this, callback);
                }

            }
        });

        builder.show().getWindow().setGravity(Gravity.BOTTOM);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        imagePicker.onActivityResult(this, requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        imagePicker.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }
}
