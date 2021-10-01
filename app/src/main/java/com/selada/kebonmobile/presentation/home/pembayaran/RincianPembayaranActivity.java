package com.selada.kebonmobile.presentation.home.pembayaran;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.selada.kebonmobile.R;
import com.selada.kebonmobile.presentation.MainActivity;
import com.selada.kebonmobile.presentation.auth.Register2Activity;
import com.selada.kebonmobile.presentation.auth.Register3Activity;
import com.selada.kebonmobile.util.Constant;
import com.selada.kebonmobile.util.PreferenceManager;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class RincianPembayaranActivity extends AppCompatActivity {

    @OnClick(R.id.btn_beranda)
    void onClickBeranda(){
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    @OnClick(R.id.btn_batalkan)
    void onClickBatalkan(){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rincian_pembayaran);
        ButterKnife.bind(this);

        //ganti status sementara
        PreferenceManager.setUserStatus(Constant.READY_PLANT);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}