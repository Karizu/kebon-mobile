package com.selada.kebonmobile.presentation.akun;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.selada.kebonmobile.R;
import com.selada.kebonmobile.presentation.akun.alamat.DaftarAlamatActivity;
import com.selada.kebonmobile.presentation.akun.email.UbahEmailActivity;
import com.selada.kebonmobile.presentation.akun.nama.UbahNamaActivity;
import com.selada.kebonmobile.presentation.akun.password.UbahPasswordActivity;
import com.selada.kebonmobile.presentation.akun.telepon.UbahTeleponActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailAkunActivity extends AppCompatActivity {

    @OnClick(R.id.layout_name)
    void onClickEditName(){
        Intent intent = new Intent(DetailAkunActivity.this, UbahNamaActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.layout_phone)
    void onClickEditPhone(){
        Intent intent = new Intent(DetailAkunActivity.this, UbahTeleponActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.layout_email)
    void onClickEditEmail(){
        Intent intent = new Intent(DetailAkunActivity.this, UbahEmailActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.layout_address)
    void onClickEditAddress(){
        Intent intent = new Intent(DetailAkunActivity.this, DaftarAlamatActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.layout_change_pass)
    void onClickEditPass(){
        Intent intent = new Intent(DetailAkunActivity.this, UbahPasswordActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_back)
    void onClickBack(){
        onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_akun);
        ButterKnife.bind(this);


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        this.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
}