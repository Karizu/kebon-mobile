package com.selada.kebonmobile.presentation.akun.alamat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.selada.kebonmobile.R;
import com.selada.kebonmobile.presentation.akun.DetailAkunActivity;
import com.selada.kebonmobile.presentation.akun.nama.UbahNamaActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class DaftarAlamatActivity extends AppCompatActivity {

    @OnClick(R.id.btn_ubahAlamat)
    void onClickUbahAlamat() {
        Intent intent = new Intent(DaftarAlamatActivity.this, UbahAlamatActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_ubahAlamat2)
    void onClickUbahAlamat2() {
        Intent intent = new Intent(DaftarAlamatActivity.this, UbahAlamatActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_tambahAlamat)
    void onClickTambahAlamat() {
        Intent intent = new Intent(DaftarAlamatActivity.this, TambahAlamatActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_back)
    void onClickBack(){
        onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_alamat);
        ButterKnife.bind(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        this.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
}