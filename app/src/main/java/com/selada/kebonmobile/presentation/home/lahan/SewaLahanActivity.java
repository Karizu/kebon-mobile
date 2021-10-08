package com.selada.kebonmobile.presentation.home.lahan;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.selada.kebonmobile.IntroActivity;
import com.selada.kebonmobile.R;
import com.selada.kebonmobile.presentation.auth.RegisterActivity;
import com.selada.kebonmobile.presentation.home.pembayaran.PembayaranActivity;
import com.selada.kebonmobile.util.MethodUtil;
import com.skydoves.elasticviews.ElasticImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SewaLahanActivity extends AppCompatActivity {

    @BindView(R.id.tv_quantity_kavling)
    TextView tv_quantity_kavling;
    @BindView(R.id.btn_min_kavling)
    ElasticImageView btn_min_kavling;
    @BindView(R.id.tv_quantity_sewa)
    TextView tv_quantity_sewa;
    @BindView(R.id.btn_min_sewa)
    ElasticImageView btn_min_sewa;

    @BindView(R.id.tv_title_bar)
    TextView tv_title_bar;

    private int qtyKavling = 0;
    private int qtySewa = 0;
    private Context context;

    @OnClick(R.id.btn_back)
    void onClickBtnBack(){
        onBackPressed();
    }

    @OnClick(R.id.btn_min_kavling)
    void onClickBtnMinKavling(){
        qtyKavling--;
        if (qtyKavling == 0){
            btn_min_kavling.setEnabled(false);
            tv_quantity_kavling.setText("0");
        } else {
            tv_quantity_kavling.setText(String.valueOf(qtyKavling));
        }
    }

    @OnClick(R.id.btn_add_kavling)
    void onClickBtnAddKavling(){
        qtyKavling++;
        btn_min_kavling.setEnabled(true);
        tv_quantity_kavling.setText(String.valueOf(qtyKavling));
    }

    @OnClick(R.id.btn_min_sewa)
    void onClickBtnMinSewa(){
        qtySewa--;
        if (qtySewa == 0){
            btn_min_sewa.setEnabled(false);
            tv_quantity_sewa.setText("0");
        } else {
            tv_quantity_sewa.setText(String.valueOf(qtySewa));
        }
    }

    @OnClick(R.id.btn_add_sewa)
    void onClickBtnAddSewa(){
        qtySewa++;
        btn_min_sewa.setEnabled(true);
        tv_quantity_sewa.setText(String.valueOf(qtySewa));
    }

    @OnClick(R.id.btn_istilah)
    void onClickBtnIstilah(){
        Dialog dialog = MethodUtil.getDialogCart(R.layout.dialog_istilah, context);
        ImageView btnClose = dialog.findViewById(R.id.btn_close);
        btnClose.setOnClickListener(view -> dialog.dismiss());
    }

    @OnClick(R.id.btn_pilih)
    void onClickPilih(){
        Intent intent = new Intent(SewaLahanActivity.this, PembayaranActivity.class);
        startActivity(intent);
        SewaLahanActivity.this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    @OnClick(R.id.btn_informasi)
    void onClickBtnInformasiKeuntungan(){
        Intent intent = new Intent(SewaLahanActivity.this, InformasiKeuntunganActivity.class);
        startActivity(intent);
        SewaLahanActivity.this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    @OnClick(R.id.img_lahan)
    void onClickImgLahan(){
        Intent intent = new Intent(SewaLahanActivity.this, GaleriLahanActivity.class);
        startActivity(intent);
        SewaLahanActivity.this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sewa_lahan);
        ButterKnife.bind(this);
        context = this;
        initComponent();
    }

    @SuppressLint("SetTextI18n")
    private void initComponent() {
        tv_title_bar.setText("Sewa Lahan");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}