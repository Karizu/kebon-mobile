package com.selada.kebonmobile.presentation.home.tanam;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.selada.kebonmobile.IntroActivity;
import com.selada.kebonmobile.R;
import com.selada.kebonmobile.presentation.auth.RegisterActivity;
import com.selada.kebonmobile.util.Constant;
import com.selada.kebonmobile.util.MethodUtil;
import com.skydoves.elasticviews.ElasticButton;
import com.skydoves.elasticviews.ElasticImageView;
import com.skydoves.elasticviews.ElasticLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PilihMetodeActivity extends AppCompatActivity {

    @BindView(R.id.btn_chat)
    ElasticImageView btn_chat;
    @BindView(R.id.tv_title_bar)
    TextView tv_title_bar;
    @BindView(R.id.btn_auto_pilot)
    RelativeLayout btn_auto_pilot;
    @BindView(R.id.btn_konvensional)
    RelativeLayout btn_konvensional;
    @BindView(R.id.btn_pilih)
    ElasticButton btn_pilih;

    private int metodeType = 0;

    @OnClick(R.id.btn_back)
    void onClickBack(){
        onBackPressed();
    }

    @OnClick(R.id.btn_istilah)
    void onClickIstilah(){

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @OnClick(R.id.btn_auto_pilot)
    void onClickAutoPilot(){
        btn_auto_pilot.setForeground(getResources().getDrawable(R.drawable.bg_outer_orange));
        btn_konvensional.setForeground(getResources().getDrawable(R.drawable.bg_outer_bro));
        metodeType = Constant.AUTO_PILOT;
        btn_pilih.setEnabled(true);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @OnClick(R.id.btn_konvensional)
    void onClickKonvensional(){
        btn_auto_pilot.setForeground(getResources().getDrawable(R.drawable.bg_outer_bro));
        btn_konvensional.setForeground(getResources().getDrawable(R.drawable.bg_outer_orange));
        metodeType = Constant.KONVENSIONAL;
        btn_pilih.setEnabled(true);
    }

    @OnClick(R.id.btn_info_selengkapnya)
    void onClickInfoAuto(){
        Dialog dialog = MethodUtil.getDialogCart(R.layout.dialog_info_auto, this);
        TextView tv_metode_panen_online = dialog.findViewById(R.id.tv_metode_panen_online);
        TextView tv_pilihan_tanman = dialog.findViewById(R.id.tv_pilihan_tanman);
        TextView tv_jaminal_jual = dialog.findViewById(R.id.tv_jaminal_jual);
    }

    @OnClick(R.id.btn_info_selengkapnya_konvensional)
    void onClickInfoKonvensional(){
        Dialog dialog = MethodUtil.getDialogCart(R.layout.dialog_info_konvensional, this);
        TextView tv_metode_panen_online = dialog.findViewById(R.id.tv_metode_panen_online);
        TextView tv_pilihan_tanman = dialog.findViewById(R.id.tv_pilihan_tanman);
        TextView tv_jaminal_jual = dialog.findViewById(R.id.tv_jaminal_jual);
    }

    @OnClick(R.id.btn_pilih)
    void onClickPilih(){
        Intent intent = new Intent(PilihMetodeActivity.this, PilihTanamanActivity.class);
        startActivity(intent);
        PilihMetodeActivity.this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilih_metode);
        ButterKnife.bind(this);

        initComponent();
    }

    @SuppressLint("SetTextI18n")
    private void initComponent() {
        btn_pilih.setEnabled(false);
        btn_chat.setVisibility(View.GONE);
        tv_title_bar.setText("Pilih Metode");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
}