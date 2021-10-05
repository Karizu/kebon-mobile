package com.selada.kebonmobile.presentation.home.lahan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.selada.kebonmobile.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class GaleriLahanActivity extends AppCompatActivity {

    @OnClick(R.id.btn_pilih)
    void onClickPilih(){
        onBackPressed();
    }

    @OnClick(R.id.btn_back)
    void onClickBack(){
        onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galeri_lahan);
        ButterKnife.bind(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}