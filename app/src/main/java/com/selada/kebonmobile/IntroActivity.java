package com.selada.kebonmobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.selada.kebonmobile.presentation.auth.LoginActivity;
import com.selada.kebonmobile.presentation.auth.RegisterActivity;
import com.skydoves.elasticviews.ElasticImageView;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class IntroActivity extends AppCompatActivity {

    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.layoutDots)
    LinearLayout dotsLayout;
    @BindView(R.id.btn_next)
    ImageView btn_next;
    @BindView(R.id.btn_back)
    ImageView btn_back;
    @BindView(R.id.img_dots_1)
    ImageView img_dots_1;
    @BindView(R.id.img_dots_2)
    ImageView img_dots_2;
    @BindView(R.id.img_dots_3)
    ImageView img_dots_3;

    private MyViewPagerAdapter myViewPagerAdapter;
    private int[] layouts;
    private TextView[] dots;
    private Timer timer;

    @OnClick(R.id.btn_next)
    void onClickBtnNext(){
        int current = getItem(1, true);
        if (current < layouts.length) {
            viewPager.setCurrentItem(current, true);
        }
    }

    @OnClick(R.id.btn_back)
    void onClickBtnBack(){
        int current = getItem(1, false);
        viewPager.setCurrentItem(current, true);
    }

    @OnClick(R.id.btn_gabung)
    void onClickGabung(){
        Intent intent = new Intent(IntroActivity.this, LoginActivity.class);
        startActivity(intent);
        IntroActivity.this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    @OnClick(R.id.textView)
    void onClickLogin(){
        Intent intent = new Intent(IntroActivity.this, RegisterActivity.class);
        startActivity(intent);
        IntroActivity.this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        ButterKnife.bind(this);

        initComponent();
    }

    private void initComponent() {
        changeStatusBarColor();
        layouts = new int[]{
                R.layout.intro_slide_1,
                R.layout.intro_slide_2,
                R.layout.intro_slide_3
        };

        myViewPagerAdapter = new MyViewPagerAdapter();
        viewPager.setAdapter(myViewPagerAdapter);
        viewPager.addOnPageChangeListener(viewPagerPageChangeListener);
//        TimerTask timerTask = new TimerTask() {
//            @Override
//            public void run() {
//                viewPager.setCurrentItem((getItem(1, true))%layouts.length);
//            }
//        };
//        timer = new Timer();
//        timer.schedule(timerTask, 1000, 1000);
    }

    private int getItem(int i, boolean isNext) {
        if (isNext){
            return viewPager.getCurrentItem() + i;
        } else {
            return viewPager.getCurrentItem() - i;
        }
    }

    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {
            // changing the next button text 'NEXT' / 'GOT IT'
            switch (position){
                case 0:
                    img_dots_1.setImageDrawable(getResources().getDrawable(R.drawable.ic_dots_orange));
                    img_dots_2.setImageDrawable(getResources().getDrawable(R.drawable.ic_dots_grey));
                    img_dots_3.setImageDrawable(getResources().getDrawable(R.drawable.ic_dots_grey));
                    btn_back.setVisibility(View.GONE);
                    btn_next.setVisibility(View.VISIBLE);
                    break;
                case 1:
                    img_dots_1.setImageDrawable(getResources().getDrawable(R.drawable.ic_dots_grey));
                    img_dots_2.setImageDrawable(getResources().getDrawable(R.drawable.ic_dots_orange));
                    img_dots_3.setImageDrawable(getResources().getDrawable(R.drawable.ic_dots_grey));
                    btn_back.setVisibility(View.VISIBLE);
                    btn_next.setVisibility(View.VISIBLE);
                    break;
                case 2:
                    img_dots_1.setImageDrawable(getResources().getDrawable(R.drawable.ic_dots_grey));
                    img_dots_2.setImageDrawable(getResources().getDrawable(R.drawable.ic_dots_grey));
                    img_dots_3.setImageDrawable(getResources().getDrawable(R.drawable.ic_dots_orange));
                    btn_back.setVisibility(View.VISIBLE);
                    btn_next.setVisibility(View.GONE);
                    break;
            }
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }
    };

    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

    public class MyViewPagerAdapter extends PagerAdapter {
        private LayoutInflater layoutInflater;

        public MyViewPagerAdapter() {
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view = layoutInflater.inflate(layouts[position], container, false);
            container.addView(view);

            return view;
        }

        @Override
        public int getCount() {
            return layouts.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }
    }
}