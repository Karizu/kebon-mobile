package com.selada.kebonmobile.presentation.status.tab.tanaman;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.gson.Gson;
import com.selada.kebonmobile.R;
import com.selada.kebonmobile.model.response.detailcommodities.DetailCommoditiesResponse;
import com.selada.kebonmobile.presentation.jadwal.JadwalActivity;
import com.selada.kebonmobile.presentation.status.tab.adapter.ActiveLahanAdapter;
import com.selada.kebonmobile.presentation.status.tab.tanaman.panen.PanenTanamanActivity;
import com.selada.kebonmobile.util.ViewPagerDetailStatusAdapter;
import com.selada.kebonmobile.util.ViewPagerDetailTanamanAdapter;
import com.skydoves.elasticviews.ElasticImageView;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailTanamanActivity extends AppCompatActivity {

    @BindView(R.id.tv_day)
    TextView tv_day;
    @BindView(R.id.tv_plant_name)
    TextView tv_plant_name;
    @BindView(R.id.img_plant)
    ElasticImageView img_plant;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.tab_info)
    FrameLayout tab_info;
    @BindView(R.id.tab_grafik)
    FrameLayout tab_grafik;
    @BindView(R.id.rv_active_sites)
    RecyclerView rv_active_sites;
    @BindView(R.id.tv_fase_tanaman)
    TextView tv_fase_tanaman;

    @OnClick(R.id.btn_jadwal)
    void onClickJadwal() {
        Intent intent = new Intent(this, JadwalActivity.class);
        startActivity(intent);
        this.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

//    @OnClick(R.id.btn_panen)
//    void onClickPanen() {
//        Intent intent = new Intent(this, PanenTanamanActivity.class);
//        intent.putExtra("plant_name", "plantName");
//        startActivity(intent);
//        this.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
//    }

    @OnClick(R.id.btn_share)
    void onClickShare() {
        showDialogShare();
    }

    @OnClick(R.id.btn_reminder)
    void onClickReminder() {

    }

    @OnClick(R.id.tab_info)
    void onClickTabInfo() {
        viewPager.setCurrentItem(0);
    }

    @OnClick(R.id.tab_grafik)
    void onClickTabRiwayat() {
        viewPager.setCurrentItem(1);
    }

    @OnClick(R.id.btn_back)
    void onClickBack() {
        onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_tanaman);
        ButterKnife.bind(this);

        initComponent();
    }

    @SuppressLint("SetTextI18n")
    private void initComponent() {
        String json = getIntent().getStringExtra("json");
        DetailCommoditiesResponse commoditiesResponse = new Gson().fromJson(json, DetailCommoditiesResponse.class);

        tv_plant_name.setText(commoditiesResponse.getCommodity().getName());
        Glide.with(DetailTanamanActivity.this)
                .load(commoditiesResponse.getCommodity().getMainImage().getFullpath())
                .placeholder(R.drawable.img_plant)
                .into(img_plant);
        tv_day.setText(commoditiesResponse.getActiveSites().get(0).getDevelopmentSummaries().get(0).getAge() + " Hari");
        tv_fase_tanaman.setText(commoditiesResponse.getActiveSites().get(0).getDevelopmentSummaries().get(0).getPhaseName());

        ActiveLahanAdapter adapter = new ActiveLahanAdapter(commoditiesResponse.getActiveSites(), this, this);
        rv_active_sites.setAdapter(adapter);

        //setViewPager
        ViewPagerDetailTanamanAdapter statusAdapter = new ViewPagerDetailTanamanAdapter(getSupportFragmentManager(), commoditiesResponse);
        viewPager.setAdapter(statusAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        tab_info.setBackground(getResources().getDrawable(R.drawable.bg_round_tab_status_on_presssed));
                        tab_grafik.setBackground(getResources().getDrawable(R.drawable.bg_round_tab_status));
                        break;
                    case 1:
                        tab_info.setBackground(getResources().getDrawable(R.drawable.bg_round_tab_status));
                        tab_grafik.setBackground(getResources().getDrawable(R.drawable.bg_round_tab_status_on_presssed));
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
        this.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
}