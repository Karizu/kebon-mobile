package com.selada.kebonmobile.presentation.akun.alamat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.selada.kebonmobile.R;
import com.selada.kebonmobile.presentation.akun.DetailAkunActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class TambahAlamatActivity extends AppCompatActivity {

    @OnClick(R.id.save_addAddress)
    void onClickAddAddress() {
        finish();
        this.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        Toast.makeText(getApplicationContext(), "Successfully added", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.btn_back)
    void onClickBack(){
        onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_alamat);
        ButterKnife.bind(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        this.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
}