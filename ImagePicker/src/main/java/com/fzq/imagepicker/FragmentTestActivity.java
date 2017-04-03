package com.fzq.imagepicker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by fzq on 2017/3/28.
 *
 * 用整个Activity来装一个Fragment
 */

public class FragmentTestActivity extends AppCompatActivity{

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fragment_test);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, new TestFragment())
                .commit();
    }
}
