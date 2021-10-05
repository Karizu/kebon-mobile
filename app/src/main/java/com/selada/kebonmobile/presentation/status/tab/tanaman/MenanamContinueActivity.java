package com.selada.kebonmobile.presentation.status.tab.tanaman;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.selada.kebonmobile.R;
import com.selada.kebonmobile.presentation.MainActivity;
import com.selada.kebonmobile.presentation.home.tanam.PilihMetodeActivity;
import com.selada.kebonmobile.presentation.status.tab.tanaman.panen.KonfirmasiPanenOfflineActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MenanamContinueActivity extends AppCompatActivity {

    @OnClick(R.id.btn_tidak)
    void onClickTidak(){
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        this.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @OnClick(R.id.btn_ya)
    void onClickYa(){
        Intent intent = new Intent(this, PilihMetodeActivity.class);
        startActivity(intent);
        this.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menanam_continue);
        ButterKnife.bind(this);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        this.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
}