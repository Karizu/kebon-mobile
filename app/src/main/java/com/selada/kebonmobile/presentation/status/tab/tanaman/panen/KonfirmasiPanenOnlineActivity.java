package com.selada.kebonmobile.presentation.status.tab.tanaman.panen;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;
import com.selada.kebonmobile.R;
import com.selada.kebonmobile.model.request.DeliveryAddress;
import com.selada.kebonmobile.model.request.HarvestRequest;
import com.selada.kebonmobile.model.response.harvest.ActivityMethod;
import com.selada.kebonmobile.model.response.harvest.HarvestInquiryResponse;
import com.selada.kebonmobile.network.ApiCore;
import com.selada.kebonmobile.presentation.status.tab.tanaman.MenanamContinueActivity;
import com.selada.kebonmobile.util.Loading;
import com.selada.kebonmobile.util.MethodUtil;
import com.selada.kebonmobile.util.PreferenceManager;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KonfirmasiPanenOnlineActivity extends AppCompatActivity {

    @BindView(R.id.tv_address)
    TextView tv_address;
    @BindView(R.id.tv_metode_pengiriman)
    TextView tv_metode_pengiriman;
    @BindView(R.id.tv_desc)
    TextView tv_desc;

    private int commodityId;
    private int totalObjects;
    private String methodId;
    private boolean isConfirm = false;
    private String plantName = "-";
    private String farmName = "-";
    private String age = "-";
    private String objCount = "-";
    private String objLabel = "-";
    private Integer siteId = 0;
    private DeliveryAddress deliveryAddress;
    private String json = "";
    private String title = "Tanaman anda akan segera dipanen silahkan tunggu notifikasi selanjutnya";

    @OnClick(R.id.btn_konfirmasi)
    void onClickKonfirmasiPengiriman() {
        isConfirm = true;

        String number = MethodUtil.getPhoneCs();
        String msg = "Hallo Kebon \nSaya ingin konfirmasi pengiriman panen saya dengan detail berikut: " + "\n";
        String order = "Panen " + plantName + "\n";
        String farm = "Nama Lahan : " + farmName + "\n";
        String total = "Jumlah : " + objCount + " " + objLabel + "\n";
        String lama = "Usia Tanaman : " + age + " Hari" + "\n";
        String alamat = "Ke Alamat : " + tv_address.getText().toString();
        String url = "https://api.whatsapp.com/send?phone=" + number;
        try {
            url = "https://api.whatsapp.com/send?phone=" + number + "&text=" + URLEncoder.encode(
                    msg + order + farm + total + lama + alamat, "UTF-8"
            );
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    @OnClick(R.id.btn_konfirmai)
    void onClickKonfirmasi() {
        doHarvestCommodity(siteId, commodityId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_konfirmasi_panen_online);
        ButterKnife.bind(this);

        initComponent();
    }

    @SuppressLint("SetTextI18n")
    private void initComponent() {
        json = getIntent().getStringExtra("json");
        String deliveryAddressJson = getIntent().getStringExtra("delivery_address");
        HarvestInquiryResponse monitoringResponse = new Gson().fromJson(json, HarvestInquiryResponse.class);
        deliveryAddress = new Gson().fromJson(deliveryAddressJson, DeliveryAddress.class);
        methodId = getIntent().getStringExtra("method_id");
        String sDate = "";
        if (monitoringResponse.getHarvests().size() > 0) {
            totalObjects = monitoringResponse.getHarvests().get(0).getObjectCount();
            siteId = monitoringResponse.getHarvests().get(0).getSiteId();
            commodityId = monitoringResponse.getHarvests().get(0).getCommodityId();
            plantName = monitoringResponse.getHarvests().get(0).getCommodityName();
            farmName = monitoringResponse.getHarvests().get(0).getSiteName();
            age = monitoringResponse.getHarvests().get(0).getAge();
            objCount = monitoringResponse.getHarvests().get(0).getObjectCount().toString();
            objLabel = monitoringResponse.getHarvests().get(0).getObjectTypeLabel();
            sDate = monitoringResponse.getHarvests().get(0).getRefEstimatedHarvestEndDate();
        }

        commodityId = getIntent().getIntExtra("commodity_id", 0);
        totalObjects = getIntent().getIntExtra("site_id", 0);
        methodId = getIntent().getStringExtra("method_id");
        String address = getIntent().getStringExtra("address");
        tv_address.setText(address);

        for (ActivityMethod method: monitoringResponse.getActivityMethods()){
            if(method.getCode().contains("online")){
                tv_desc.setText(method.getHarvest_description());
            }
        }
    }

    private void doHarvestCommodity(int siteId, int commodityId) {
        HarvestRequest harvestRequest = new HarvestRequest();
        harvestRequest.setCommodity_id(String.valueOf(commodityId));
        harvestRequest.setActivity_method_code("online_harvest");
        harvestRequest.setActivity_type_code("harvest_activity");
        harvestRequest.setTotal_objects(String.valueOf(totalObjects));
        harvestRequest.setDelivery_address(deliveryAddress);

        Loading.show(KonfirmasiPanenOnlineActivity.this);
        ApiCore.apiInterface().doHarvest(String.valueOf(siteId), harvestRequest, PreferenceManager.getSessionToken()).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Loading.hide(KonfirmasiPanenOnlineActivity.this);
                try {
                    if (response.isSuccessful()) {
                        Intent intent = new Intent(KonfirmasiPanenOnlineActivity.this, MenanamContinueActivity.class);
                        intent.putExtra("json", json);
                        intent.putExtra("title", title);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        KonfirmasiPanenOnlineActivity.this.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    } else {
                        MethodUtil.getDialogWarning(response.errorBody(), KonfirmasiPanenOnlineActivity.this);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    MethodUtil.getDialogWarningCatch(KonfirmasiPanenOnlineActivity.this);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
                Loading.hide(KonfirmasiPanenOnlineActivity.this);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
}