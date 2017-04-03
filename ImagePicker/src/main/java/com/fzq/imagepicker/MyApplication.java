package com.fzq.imagepicker;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by fzq on 2017/3/28.
 */

public class MyApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();

        Fresco.initialize(this);
    }
}
