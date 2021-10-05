package com.selada.kebonmobile.presentation.status.tab.tanaman.panen;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.gson.internal.$Gson$Preconditions;
import com.iambedant.text.OutlineTextView;
import com.selada.kebonmobile.R;
import com.skydoves.elasticviews.ElasticImageView;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PanenTanamanActivity extends AppCompatActivity {

    @BindView(R.id.tv_plant_name)
    OutlineTextView tv_plant_name;
    @BindView(R.id.text_title)
    TextView text_title;
    @BindView(R.id.tv_lokasi_lahan)
    TextView tv_lokasi_lahan;
    @BindView(R.id.tv_usia_tanaman)
    TextView tv_usia_tanaman;
    @BindView(R.id.tv_jumlah_lubang)
    TextView tv_jumlah_lubang;


    @OnClick(R.id.btn_online)
    void onClickOnline(){
        Intent intent = new Intent(this, KonfirmasiAddressActivity.class);
        startActivity(intent);
        this.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @OnClick(R.id.btn_offline)
    void onClickOffline(){
        Intent intent = new Intent(this, KonfirmasiPanenOfflineActivity.class);
        startActivity(intent);
        this.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @OnClick(R.id.btn_info_selengkapnya_online)
    void onClickSelengkapnyaOnline(){

    }

    @OnClick(R.id.btn_info_selengkapnya)
    void onClickSelengkapnyaOffline(){

    }

    @OnClick(R.id.btn_share)
    void onClickShare(){
        showDialogShare();
    }

    @OnClick(R.id.btn_back)
    void onClickBack(){
        onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panen_tanaman);
        ButterKnife.bind(this);

        initComponent();
    }

    @SuppressLint("SetTextI18n")
    private void initComponent() {
        if (getIntent()!=null){
            String plantName = getIntent().getStringExtra("plant_name");
            tv_plant_name.setText("Panen "+plantName);
            text_title.setText("Data Tanaman "+plantName);

        }
    }

    private void showDialogShare() {
        BottomSheetDialog dialog = new BottomSheetDialog(this);
        dialog.setContentView(R.layout.dialog_share);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(true);
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