package com.selada.kebonmobile.presentation.home.lahan;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import com.selada.kebonmobile.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InformasiKeuntunganActivity extends AppCompatActivity {

    @BindView(R.id.tv_desc_keuntungan)
    TextView tv_desc_keuntungan;

    @OnClick(R.id.btn_kembali)
    void onClickKembali(){
        onBackPressed();
    }

    @OnClick(R.id.btn_back)
    void onClickBack(){
        onBackPressed();
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informasi_keuntungan);
        ButterKnife.bind(this);

        String farmName = getIntent().getStringExtra("farm_name");
        tv_desc_keuntungan.setText("Keuntungan menanam di \n" + farmName);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}