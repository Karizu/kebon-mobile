package com.selada.kebonmobile.presentation.status.tab.lahan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.gson.Gson;
import com.google.gson.internal.$Gson$Preconditions;
import com.iambedant.text.OutlineTextView;
import com.madapps.liquid.LiquidRefreshLayout;
import com.selada.kebonmobile.R;
import com.selada.kebonmobile.model.response.farmsummary.ActiveCommodity;
import com.selada.kebonmobile.model.response.farmsummary.Farm;
import com.selada.kebonmobile.model.response.farmsummary.ObjectTypeSummary;
import com.selada.kebonmobile.presentation.jadwal.JadwalActivity;
import com.selada.kebonmobile.presentation.status.tab.adapter.DetailStatusLahanAdapter;
import com.selada.kebonmobile.util.MethodUtil;
import com.selada.kebonmobile.util.VerticalViewPager;
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
    VerticalViewPager viewPager;
    @BindView(R.id.tab_info)
    FrameLayout tab_info;
    @BindView(R.id.tab_riwayat)
    FrameLayout tab_riwayat;
    @BindView(R.id.tv_farm_name)
    OutlineTextView tv_farm_name;
    @BindView(R.id.videoView)
    WebView mWebView;
    @BindView(R.id.btn_play)
    ElasticImageView btn_play;
    @BindView(R.id.img_farm)
    ImageView img_farm;

    @BindView(R.id.tv_jumlah_lubang)
    TextView tv_jumlah_lubang;
    @BindView(R.id.tv_jumlah_lubang_kosong)
    TextView tv_jumlah_lubang_kosong;
    @BindView(R.id.tv_tanaman)
    TextView tv_tanaman;
    @BindView(R.id.tv_alamat)
    TextView tv_alamat;

    @BindView(R.id.textViewTanaman)
    TextView tv_label_jumlah_lubang;
    @BindView(R.id.textViewSite)
    TextView tv_label_jumlah_lubang_kosong;
    @BindView(R.id.tv_jumlah_lubang_active)
    TextView tv_jumlah_lubang_active;
    @BindView(R.id.textViewActive)
    TextView tv_label_jumlah_lubang_active;

    @BindView(R.id.refreshLayout)
    LiquidRefreshLayout refreshLayout;

    private Farm farm;
    private String latitude;
    private String longitude;
    private String url;


    @OnClick(R.id.btn_lihat_lokasi)
    void onClickLihat(){
        Uri gmmIntentUri = Uri.parse("geo:"+ latitude + "," + longitude);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        if (mapIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(mapIntent);
        }
    }

    @OnClick(R.id.btn_back)
    void onClickBack() {
        onBackPressed();
    }

    @OnClick(R.id.btn_share)
    void onClickhare() {
        showDialogShare();
    }

    @OnClick(R.id.btn_jadwal)
    void onClcikJadwal() {
        Intent intent = new Intent(this, JadwalActivity.class);
        startActivity(intent);
        this.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @OnClick(R.id.btn_play)
    void onClickPlay() {
        mWebView.setVisibility(View.VISIBLE);
        btn_play.setVisibility(View.GONE);
        mWebView.getSettings().setLoadWithOverviewMode(true);
        mWebView.getSettings().setUseWideViewPort(true);
        mWebView.getSettings().setSupportMultipleWindows(true);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        mWebView.loadUrl(url);
    }

    @OnClick(R.id.tab_info)
    void onClickTabInfo() {
        viewPager.setCurrentItem(0);
    }

    @OnClick(R.id.tab_riwayat)
    void onClickTabRiwayat() {
        viewPager.setCurrentItem(1);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_status_lahan);
        ButterKnife.bind(this);

        initComponent();
    }

    @SuppressLint("SetTextI18n")
    private void initComponent() {
        url = getIntent().getStringExtra("url");
        onClickPlay();
        String jsonFarm = getIntent().getStringExtra("json_farm");
        Farm farm = new Gson().fromJson(jsonFarm, Farm.class);
        String farm_name = farm.getName();
        tv_farm_name.setText(farm_name);
        Glide.with(DetailStatusLahanActivity.this)
                .load(farm.getImage().getFullpath())
                .placeholder(R.drawable.img_plant)
                .into(img_farm);

        latitude = farm.getAddress_latitude();
        longitude = farm.getAddress_longitude();

        ObjectTypeSummary objectTypeSummary = new ObjectTypeSummary();
        for (ObjectTypeSummary summary: farm.getObjectTypeSummary()){
            if (summary.getFarmableStatus()){
                objectTypeSummary = summary;
            }
        }

        tv_label_jumlah_lubang.setText("Jumlah " + objectTypeSummary.getLabel());
        tv_label_jumlah_lubang_kosong.setText("Jumlah " + objectTypeSummary.getLabel() + " kosong");
        tv_label_jumlah_lubang_active.setText("Jumlah " + objectTypeSummary.getLabel() + " ditanam");

        tv_jumlah_lubang.setText(objectTypeSummary.getTotal() + "");
        tv_jumlah_lubang_kosong.setText(objectTypeSummary.getTotalIdle() + "");
        tv_jumlah_lubang_active.setText(objectTypeSummary.getTotal_active() + "");
        tv_tanaman.setText(farm.getActiveCommodityStr());
        tv_alamat.setText(farm.getAddressStreet() + ", " + farm.getAddressSubdistrict() + ", " + farm.getAddressDistrict() + ", " + farm.getAddressCity());

        ViewPagerDetailStatusAdapter statusAdapter = new ViewPagerDetailStatusAdapter(getSupportFragmentManager(), farm);
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

        //setRecyclerView

        List<ActiveCommodity> activeCommodities = farm.getActiveCommodities();
        DetailStatusLahanAdapter adapter = new DetailStatusLahanAdapter(activeCommodities, this, this);
        rv_tanaman_site.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rv_tanaman_site.setAdapter(adapter);

        refreshLayout.setOnRefreshListener(new LiquidRefreshLayout.OnRefreshListener() {
            @Override
            public void completeRefresh() {

            }

            @Override
            public void refreshing() {
                refreshLayout.finishRefreshing();
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