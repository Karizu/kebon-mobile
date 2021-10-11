package com.selada.kebonmobile.presentation.home.pembayaran;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.TextView;

import com.selada.kebonmobile.R;
import com.selada.kebonmobile.model.SewaLahanModel;
import com.selada.kebonmobile.presentation.auth.Register3Activity;
import com.selada.kebonmobile.presentation.auth.VerificationEmailActivity;
import com.selada.kebonmobile.presentation.home.lahan.SewaLahanActivity;
import com.selada.kebonmobile.util.MethodUtil;
import com.selada.kebonmobile.util.PreferenceManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PembayaranActivity extends AppCompatActivity {

    @BindView(R.id.tv_title_bar)
    TextView tv_title_bar;
    @BindView(R.id.checkBoxTransferBank)
    CheckBox checkBoxTransferBank;
    @BindView(R.id.checkBoxQris)
    CheckBox checkBoxQris;
    @BindView(R.id.tv_farm_name)
    TextView tv_farm_name;
    @BindView(R.id.tv_jumlah_kavling)
    TextView tv_jumlah_kavling;
    @BindView(R.id.tv_lama_sewa)
    TextView tv_lama_sewa;
    @BindView(R.id.tv_harga)
    TextView tv_harga;
    @BindView(R.id.tv_sub_total)
    TextView tv_sub_total;
    @BindView(R.id.tv_total_bayar)
    TextView tv_total_bayar;

    private String paymentMethod = "";

    @OnClick(R.id.checkBoxTransferBank)
    void onClickTransfer(){
        paymentMethod = "transfer";
        checkBoxTransferBank.setChecked(true);
        checkBoxQris.setChecked(false);
    }

    @OnClick(R.id.checkBoxQris)
    void onClickQris(){
        paymentMethod = "qris";
        checkBoxTransferBank.setChecked(false);
        checkBoxQris.setChecked(true);
    }

    @OnClick(R.id.btn_bayar)
    void onClickBtnBayar(){
        Intent intent = new Intent(PembayaranActivity.this, KonfirmasiPembayaranActivity.class);
        startActivity(intent);
        PembayaranActivity.this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    @OnClick(R.id.btn_back)
    void onClickBack(){
        onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pembayaran);
        ButterKnife.bind(this);

        initComponent();
    }

    @SuppressLint("SetTextI18n")
    private void initComponent() {
        tv_title_bar.setText("Pembayaran");

        SewaLahanModel lahanModel = PreferenceManager.getSewaLahan();
        tv_farm_name.setText(lahanModel.getNamaLahan());
        tv_jumlah_kavling.setText(lahanModel.getJumlahKavling());
        tv_lama_sewa.setText(lahanModel.getLamaSewa());
        tv_harga.setText("Rp. " + MethodUtil.toCurrencyNumber(Integer.parseInt(lahanModel.getHarga())));
        tv_sub_total.setText("Rp. " + MethodUtil.toCurrencyNumber(Integer.parseInt(lahanModel.getHarga())));
        tv_total_bayar.setText("Rp. " + MethodUtil.toCurrencyNumber(Integer.parseInt(lahanModel.getTotalHarga())));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        PembayaranActivity.this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}