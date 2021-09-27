package com.selada.kebonmobile.presentation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.selada.kebonmobile.R;
import com.selada.kebonmobile.util.ViewPagerAdapter;
import com.skydoves.elasticviews.ElasticImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.bottomNavigation)
    BottomNavigationView bottomNavigation;
    @BindView(R.id.view_pager)
    ViewPager view_pager;

    @BindView(R.id.nav_beranda)
    ElasticImageView nav_beranda;
    @BindView(R.id.nav_status)
    ElasticImageView nav_status;
    @BindView(R.id.nav_notifikasi)
    ElasticImageView nav_notifikasi;
    @BindView(R.id.nav_akun)
    ElasticImageView nav_akun;

    @OnClick(R.id.nav_beranda)
    void onClickNavBeranda(){
        view_pager.setCurrentItem(0);
    }

    @OnClick(R.id.nav_status)
    void onClickNavStatus(){
        view_pager.setCurrentItem(1);
    }

    @OnClick(R.id.nav_notifikasi)
    void onClickNavNotifikasi(){
        view_pager.setCurrentItem(2);
    }

    @OnClick(R.id.nav_akun)
    void onClickNavAkun(){
        view_pager.setCurrentItem(3);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initComponent();
    }

    private void initComponent() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        view_pager.setAdapter(adapter);
        view_pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        nav_beranda.setImageDrawable(getResources().getDrawable(R.drawable.ic_beranda_pressed));
                        nav_status.setImageDrawable(getResources().getDrawable(R.drawable.ic_status));
                        nav_notifikasi.setImageDrawable(getResources().getDrawable(R.drawable.ic_notif));
                        nav_akun.setImageDrawable(getResources().getDrawable(R.drawable.ic_akun));
                        break;
                    case 1:
                        nav_beranda.setImageDrawable(getResources().getDrawable(R.drawable.ic_beranda));
                        nav_status.setImageDrawable(getResources().getDrawable(R.drawable.ic_status));
                        nav_notifikasi.setImageDrawable(getResources().getDrawable(R.drawable.ic_notif));
                        nav_akun.setImageDrawable(getResources().getDrawable(R.drawable.ic_akun));
                        break;
                    case 2:
                        nav_beranda.setImageDrawable(getResources().getDrawable(R.drawable.ic_beranda));
                        nav_status.setImageDrawable(getResources().getDrawable(R.drawable.ic_status));
                        nav_notifikasi.setImageDrawable(getResources().getDrawable(R.drawable.ic_notif_pressed));
                        nav_akun.setImageDrawable(getResources().getDrawable(R.drawable.ic_akun));
                        break;
                    case 3:
                        nav_beranda.setImageDrawable(getResources().getDrawable(R.drawable.ic_beranda));
                        nav_status.setImageDrawable(getResources().getDrawable(R.drawable.ic_status));
                        nav_notifikasi.setImageDrawable(getResources().getDrawable(R.drawable.ic_notif));
                        nav_akun.setImageDrawable(getResources().getDrawable(R.drawable.ic_akun_pressed));
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}