package com.selada.kebonmobile.presentation.status.tab.tanaman.panen;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;
import com.selada.kebonmobile.R;
import com.selada.kebonmobile.model.request.HarvestRequest;
import com.selada.kebonmobile.model.response.harvest.ActivityMethod;
import com.selada.kebonmobile.model.response.harvest.HarvestInquiryResponse;
import com.selada.kebonmobile.network.ApiCore;
import com.selada.kebonmobile.presentation.status.tab.tanaman.MenanamContinueActivity;
import com.selada.kebonmobile.util.Loading;
import com.selada.kebonmobile.util.MethodUtil;
import com.selada.kebonmobile.util.PreferenceManager;

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

public class KonfirmasiPanenOfflineActivity extends AppCompatActivity {

    @BindView(R.id.tv_desc)
    TextView tv_desc;

    private String methodId;
    private Integer siteId = 0;
    private Integer commodityId = 0;
    private Integer totalObjects = 0;
    private String json = "";
    private String title = "Tanaman anda akan segera dipanen, silahkan datang ke farm sesuai dengan waktu yang ditentukan";

    @OnClick(R.id.btn_konfirmai)
    void onClickKonfirmasi(){
        doHarvestCommodity(siteId, commodityId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_konfirmasi_panen_offline);
        ButterKnife.bind(this);

        initComponent();
    }

    @SuppressLint("SetTextI18n")
    private void initComponent() {
        methodId = getIntent().getStringExtra("method_id");
        json = getIntent().getStringExtra("json");
        HarvestInquiryResponse monitoringResponse = new Gson().fromJson(json, HarvestInquiryResponse.class);
        methodId = getIntent().getStringExtra("method_id");
        String sDate = "";
        if (monitoringResponse.getHarvests().size() > 0) {
            siteId = monitoringResponse.getHarvests().get(0).getSiteId();
            commodityId = monitoringResponse.getHarvests().get(0).getCommodityId();
            totalObjects = monitoringResponse.getHarvests().get(0).getObjectCount();
        }

        for (ActivityMethod method: monitoringResponse.getActivityMethods()){
            if(method.getCode().contains("offline")){
                tv_desc.setText(method.getHarvest_description());
                title = method.getHarvest_description();
            }
        }
    }


    private void doHarvestCommodity(int siteId, int commodityId){
        HarvestRequest harvestRequest = new HarvestRequest();
        harvestRequest.setCommodity_id(String.valueOf(commodityId));
        harvestRequest.setActivity_method_code("offline_harvest");
        harvestRequest.setActivity_type_code("harvest_activity");
        harvestRequest.setTotal_objects(String.valueOf(totalObjects));

        Loading.show(KonfirmasiPanenOfflineActivity.this);
        ApiCore.apiInterface().doHarvest(String.valueOf(siteId), harvestRequest, PreferenceManager.getSessionToken()).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Loading.hide(KonfirmasiPanenOfflineActivity.this);
                try {
                    if (response.isSuccessful()){
                        Intent intent = new Intent(KonfirmasiPanenOfflineActivity.this, MenanamContinueActivity.class);
                        intent.putExtra("json", json);
                        intent.putExtra("title", title);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        KonfirmasiPanenOfflineActivity.this.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    } else {
                        MethodUtil.getDialogWarning(response.errorBody(), KonfirmasiPanenOfflineActivity.this);
                    }
                } catch (Exception e){
                    e.printStackTrace();
                    MethodUtil.getDialogWarningCatch(KonfirmasiPanenOfflineActivity.this);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
                Loading.hide(KonfirmasiPanenOfflineActivity.this);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
    }
}