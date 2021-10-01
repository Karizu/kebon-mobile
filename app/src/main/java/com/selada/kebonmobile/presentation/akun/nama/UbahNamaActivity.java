package com.selada.kebonmobile.presentation.akun.nama;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.selada.kebonmobile.R;
import com.selada.kebonmobile.presentation.akun.DetailAkunActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UbahNamaActivity extends AppCompatActivity {

    @OnClick(R.id.save_name)
    void onClickSaveName(){
        finish();
        this.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        Toast.makeText(getApplicationContext(), "Successfully saved", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.btn_back)
    void onClickBack(){
        onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah_nama);
        ButterKnife.bind(this);
    }

    public void saveName(View view) {
        Intent intent = new Intent(UbahNamaActivity.this, DetailAkunActivity.class);
        startActivity(intent);
    }

    public void back(View view) {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        this.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
}