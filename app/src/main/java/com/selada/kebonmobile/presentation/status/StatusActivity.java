package com.selada.kebonmobile.presentation.status;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.selada.kebonmobile.R;
import com.selada.kebonmobile.util.ViewPagerAdapter;
import com.selada.kebonmobile.util.ViewPagerStatusAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StatusActivity extends AppCompatActivity {

    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.tab_lahan)
    FrameLayout tab_lahan;
    @BindView(R.id.tab_tanaman)
    FrameLayout tab_tanaman;

    @OnClick(R.id.btn_back)
    void onClickBack(){
        onBackPressed();
    }

    @OnClick(R.id.btn_history)
    void onClickHistory(){

    }

    @OnClick(R.id.tab_lahan)
    void onClickTabLahan(){
        viewPager.setCurrentItem(0);
    }

    @OnClick(R.id.tab_tanaman)
    void onClickTabTanaman(){
        viewPager.setCurrentItem(1);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);
        ButterKnife.bind(this);

        initComponent();
    }

    private void initComponent() {
        ViewPagerStatusAdapter adapter = new ViewPagerStatusAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        tab_lahan.setBackground(getResources().getDrawable(R.drawable.bg_round_tab_status_on_presssed));
                        tab_tanaman.setBackground(getResources().getDrawable(R.drawable.bg_round_tab_status));
                        break;
                    case 1:
                        tab_lahan.setBackground(getResources().getDrawable(R.drawable.bg_round_tab_status));
                        tab_tanaman.setBackground(getResources().getDrawable(R.drawable.bg_round_tab_status_on_presssed));
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        this.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
}