package com.fzq.myapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity implements View.OnClickListener {

    private ViewPager viewPager;
    private List<Fragment> viewList;
    private FragmentManager fragmentManager;
    private ViewPagerAdapter adapter;


    private RelativeLayout layout_01, layout_02, layout_03;
    private TextView text_01, text_02, text_03;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        fragmentManager = getSupportFragmentManager();

        initViews();
        initData();



        adapter = new ViewPagerAdapter(fragmentManager, viewList);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(0);
        text_01.setTextColor(Color.BLUE);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                System.out.println("onPageScrolled");
            }

            @Override
            public void onPageSelected(int position) {
                System.out.println("onPageSelected");

                setTextColor(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                System.out.println("onPageScrollStateChanged");

            }
        });

    }

    private void initViews(){
        layout_01 = (RelativeLayout) findViewById(R.id.layout_01);
        layout_02 = (RelativeLayout) findViewById(R.id.layout_02);
        layout_03 = (RelativeLayout) findViewById(R.id.layout_03);
        text_01 = (TextView) findViewById(R.id.text_01);
        text_02 = (TextView) findViewById(R.id.text_02);
        text_03 = (TextView) findViewById(R.id.text_03);

        layout_01.setOnClickListener(this);
                layout_02.setOnClickListener(this);
        layout_03.setOnClickListener(this);

    }
    private void initData() {
        FragmentA fa = new FragmentA();
        FragmentB fb = new FragmentB();
        FragmentC fc = new FragmentC();

        viewList = new ArrayList<Fragment>();
        viewList.add(fa);
        viewList.add(fb);
        viewList.add(fc);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.layout_01:
                viewPager.setCurrentItem(0);
                setTextColor(0);
                break;
            case R.id.layout_02:
                viewPager.setCurrentItem(1);
                setTextColor(1);
                break;
            case R.id.layout_03:
                viewPager.setCurrentItem(2);
                setTextColor(2);
                break;
        }
    }


    private void setTextColor(int position) {

        if(position == 0){
            text_01.setTextColor(Color.BLUE);
            text_02.setTextColor(Color.BLACK);
            text_03.setTextColor(Color.BLACK);
        }else if(position == 1){
            text_02.setTextColor(Color.BLUE);
            text_01.setTextColor(Color.BLACK);
            text_03.setTextColor(Color.BLACK);
        }else if(position == 2){
            text_03.setTextColor(Color.BLUE);
            text_02.setTextColor(Color.BLACK);
            text_01.setTextColor(Color.BLACK);
        }
    }


}
