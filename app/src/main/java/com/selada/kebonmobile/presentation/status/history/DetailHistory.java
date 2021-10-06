package com.selada.kebonmobile.presentation.status.history;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.selada.kebonmobile.R;
import com.skydoves.elasticviews.ElasticImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailHistory extends AppCompatActivity {

    @BindView(R.id.tv_lokasi_lahan)
    TextView tv_lokasi_lahan;
    @BindView(R.id.tv_tgl_panen)
    TextView tv_tgl_panen;
    @BindView(R.id.tv_usia_tanaman)
    TextView tv_usia_tanaman;
    @BindView(R.id.tv_jumlah_panen)
    TextView tv_jumlah_panen;
    @BindView(R.id.tv_metode_panen)
    TextView tv_metode_panen;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.img_plant)
    ElasticImageView img_plant;

    @OnClick(R.id.btn_back)
    void onClickBack(){
        onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_history);
        ButterKnife.bind(this);

        initComponent();
    }

    private void initComponent() {
        if (getIntent()!=null){
            String title = getIntent().getStringExtra("title");
            tv_title.setText(title);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
}