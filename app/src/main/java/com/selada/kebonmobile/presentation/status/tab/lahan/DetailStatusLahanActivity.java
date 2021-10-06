package com.selada.kebonmobile.presentation.status.tab.lahan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.iambedant.text.OutlineTextView;
import com.selada.kebonmobile.R;
import com.selada.kebonmobile.presentation.jadwal.JadwalActivity;
import com.selada.kebonmobile.presentation.status.tab.adapter.DetailStatusLahanAdapter;
import com.selada.kebonmobile.util.MethodUtil;
import com.selada.kebonmobile.util.ViewPagerDetailStatusAdapter;
import com.selada.kebonmobile.util.ViewPagerStatusAdapter;
import com.skydoves.elasticviews.ElasticImageView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailStatusLahanActivity extends AppCompatActivity {

    @BindView(R.id.rv_tanaman_site)
    RecyclerView rv_tanaman_site;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.tab_info)
    FrameLayout tab_info;
    @BindView(R.id.tab_riwayat)
    FrameLayout tab_riwayat;
    @BindView(R.id.tv_farm_name)
    OutlineTextView tv_farm_name;

    private Context context;

    @OnClick(R.id.btn_back)
    void onClickBack(){
        onBackPressed();
    }

    @OnClick(R.id.btn_share)
    void onClickhare(){
        showDialogShare();
    }

    @OnClick(R.id.btn_jadwal)
    void onClcikJadwal(){
        Intent intent = new Intent(this, JadwalActivity.class);
        startActivity(intent);
        this.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @OnClick(R.id.btn_play)
    void onClickPlay(){

    }

    @OnClick(R.id.tab_info)
    void onClickTabInfo(){
        viewPager.setCurrentItem(0);
    }

    @OnClick(R.id.tab_riwayat)
    void onClickTabRiwayat(){
        viewPager.setCurrentItem(1);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_status_lahan);
        ButterKnife.bind(this);

        initComponent();
    }

    private void initComponent() {
        context = this;

        if (getIntent()!=null){
            String farm_name = getIntent().getStringExtra("tv_farm_name");
            tv_farm_name.setText(farm_name);
        }

        //setRecyclerView
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rv_tanaman_site.setLayoutManager(layoutManager);

        List<String> listBottom = new ArrayList<>();
        listBottom.add("Sawi");
        listBottom.add("Bayam");
        listBottom.add("Pakcoy");

        DetailStatusLahanAdapter adapter = new DetailStatusLahanAdapter(listBottom, this, this);
        rv_tanaman_site.setAdapter(adapter);

        //setViewPager
        ViewPagerDetailStatusAdapter statusAdapter = new ViewPagerDetailStatusAdapter(getSupportFragmentManager());
        viewPager.setAdapter(statusAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        tab_info.setBackground(getResources().getDrawable(R.drawable.bg_round_tab_status_on_presssed));
                        tab_riwayat.setBackground(getResources().getDrawable(R.drawable.bg_round_tab_status));
                        break;
                    case 1:
                        tab_info.setBackground(getResources().getDrawable(R.drawable.bg_round_tab_status));
                        tab_riwayat.setBackground(getResources().getDrawable(R.drawable.bg_round_tab_status_on_presssed));
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void showDialogShare() {
        BottomSheetDialog dialog = new BottomSheetDialog(this);
        dialog.setContentView(R.layout.dialog_share);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(true);
        Objects.requireNonNull(dialog.getWindow()).getAttributes().windowAnimations = R.style.DialogThemes;
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawableResource(android.R.color.transparent);
        dialog.show();

        ElasticImageView btn_share_whatsapp = dialog.findViewById(R.id.btn_share_whatsapp);
        ElasticImageView btn_share_instagram = dialog.findViewById(R.id.btn_share_instagram);
        ElasticImageView btn_share_line = dialog.findViewById(R.id.btn_share_line);
        ElasticImageView btn_share_bluetooth = dialog.findViewById(R.id.btn_share_bluetooth);
        ElasticImageView btn_share_twitter = dialog.findViewById(R.id.btn_share_twitter);

        btn_share_whatsapp.setOnClickListener(view -> {
            Intent intent = new Intent(android.content.Intent.ACTION_SEND);
            String shareBody = "Here is the share content body";
            intent.setType("text/plain");
            intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "subject");
            intent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
            intent.setPackage("com.whatsapp");
            startActivity(Intent.createChooser(intent, "Share"));
        });

        btn_share_instagram.setOnClickListener(view -> {
            Intent intent = new Intent(android.content.Intent.ACTION_SEND);
            String shareBody = "Here is the share content body";
            intent.setType("text/plain");
            intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "subject");
            intent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
            intent.setPackage("com.instagram.android");
            startActivity(Intent.createChooser(intent, "Share"));
        });

        btn_share_line.setOnClickListener(view -> {
            Intent intent = new Intent(android.content.Intent.ACTION_SEND);
            String shareBody = "Here is the share content body";
            intent.setType("text/plain");
            intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "subject");
            intent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
            intent.setPackage("jp.naver.line.android");
            startActivity(Intent.createChooser(intent, "Share"));
        });

        btn_share_bluetooth.setOnClickListener(view -> {
            Intent intent = new Intent(android.content.Intent.ACTION_SEND);
            String shareBody = "Here is the share content body";
            intent.setType("text/plain");
            intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "subject");
            intent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
            intent.setPackage("com.android.bluetooth");
            startActivity(Intent.createChooser(intent, "Share"));
        });

        btn_share_twitter.setOnClickListener(view -> {
            Intent intent = new Intent(android.content.Intent.ACTION_SEND);
            String shareBody = "Here is the share content body";
            intent.setType("text/plain");
            intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "subject");
            intent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
            intent.setPackage("com.twitter.android");
            startActivity(Intent.createChooser(intent, "Share"));
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        this.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
}