package com.selada.kebonmobile.presentation.home.tanam;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.selada.kebonmobile.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class KeuntunganTanamanActivity extends AppCompatActivity {

    @BindView(R.id.tv_percentage)
    TextView tv_percentage;
    @BindView(R.id.tv_desc)
    TextView tv_desc;

    @OnClick(R.id.btn_tambah_keranjang)
    void onClickTambahKeranjang(){
        onBackPressed();
    }

    @OnClick(R.id.btn_keuntungan_lain)
    void onClickKeuntunganLain(){
        onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keuntungan_tanaman);
        ButterKnife.bind(this);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
}