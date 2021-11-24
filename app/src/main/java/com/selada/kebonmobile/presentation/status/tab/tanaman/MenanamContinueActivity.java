package com.selada.kebonmobile.presentation.status.tab.tanaman;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;
import com.selada.kebonmobile.R;
import com.selada.kebonmobile.model.request.DeliveryAddress;
import com.selada.kebonmobile.model.response.harvest.HarvestInquiryResponse;
import com.selada.kebonmobile.presentation.MainActivity;
import com.selada.kebonmobile.presentation.home.tanam.PilihMetodeActivity;
import com.selada.kebonmobile.presentation.status.tab.tanaman.panen.KonfirmasiPanenOfflineActivity;
import com.selada.kebonmobile.util.MethodUtil;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MenanamContinueActivity extends AppCompatActivity {

    @BindView(R.id.tv_desc)
    TextView tv_desc;

    private String plantName = "-";
    private String farmName = "-";
    private String age = "-";
    private String objCount = "-";
    private String objLabel = "-";

    @OnClick(R.id.btn_beranda)
    void onClickTidak(){
        onBackPressed();
    }

    @OnClick(R.id.btn_konfirmasi_wa)
    void onClickYa(){
        String number = MethodUtil.getPhoneCs();
        String msg = "Hallo Kebon \nSaya ingin konfirmasi panen saya dengan detail berikut: " + "\n";
        String order = "Panen " + plantName + "\n";
        String farm = "Nama Lahan : " + farmName + "\n";
        String total = "Jumlah : " + objCount + " " + objLabel + "\n";
        String lama = "Usia Tanaman : " + age + " Hari" + "\n";
        String url = "https://api.whatsapp.com/send?phone=" + number;
        try {
            url = "https://api.whatsapp.com/send?phone=" + number + "&text=" + URLEncoder.encode(
                    msg + order + farm + total + lama, "UTF-8"
            );
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menanam_continue);
        ButterKnife.bind(this);

        initComponent();
    }

    private void initComponent() {
        String json = getIntent().getStringExtra("json");
        String title = getIntent().getStringExtra("title");
        tv_desc.setText(title);
        HarvestInquiryResponse monitoringResponse = new Gson().fromJson(json, HarvestInquiryResponse.class);
        if (monitoringResponse.getHarvests().size() > 0) {
            plantName = monitoringResponse.getHarvests().get(0).getCommodityName();
            farmName = monitoringResponse.getHarvests().get(0).getSiteName();
            age = monitoringResponse.getHarvests().get(0).getAge();
            objCount = monitoringResponse.getHarvests().get(0).getObjectCount().toString();
            objLabel = monitoringResponse.getHarvests().get(0).getObjectTypeLabel();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        this.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
}