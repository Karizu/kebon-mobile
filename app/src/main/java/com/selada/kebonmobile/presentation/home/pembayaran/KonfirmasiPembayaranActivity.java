package com.selada.kebonmobile.presentation.home.pembayaran;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ProgressBar;

import com.selada.kebonmobile.R;
import com.selada.kebonmobile.presentation.MainActivity;
import com.selada.kebonmobile.util.Constant;
import com.selada.kebonmobile.util.PreferenceManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class KonfirmasiPembayaranActivity extends AppCompatActivity {

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @OnClick(R.id.btn_beranda)
    void onClickBeranda(){
        Intent intent = new Intent(KonfirmasiPembayaranActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @OnClick(R.id.btn_batalkan)
    void onClickBatalkan(){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_konfirmasi_pembayaran);
        ButterKnife.bind(this);
        new PreferenceManager(this);
        initComponent();

    }

    private void initComponent() {
        PreferenceManager.setUserStatus(Constant.ON_HOLD);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}