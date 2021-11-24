package com.selada.kebonmobile.presentation.status;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.selada.kebonmobile.R;
import com.selada.kebonmobile.model.response.ApiResponse;
import com.selada.kebonmobile.model.response.commoditymonitoring.CommodityMonitoringResponse;
import com.selada.kebonmobile.model.response.errormsg.ErrorResponse;
import com.selada.kebonmobile.model.response.farmsummary.Farm;
import com.selada.kebonmobile.model.response.farmsummary.FarmSummaryResponse;
import com.selada.kebonmobile.model.response.farmsummary.SiteComponent;
import com.selada.kebonmobile.network.ApiCore;
import com.selada.kebonmobile.presentation.status.history.HistoryActivity;
import com.selada.kebonmobile.presentation.status.tab.lahan.DetailStatusLahanActivity;
import com.selada.kebonmobile.presentation.status.tab.tanaman.DetailStatusTanamanActivity;
import com.selada.kebonmobile.util.Loading;
import com.selada.kebonmobile.util.MethodUtil;
import com.selada.kebonmobile.util.PreferenceManager;
import com.selada.kebonmobile.util.SocketClient;
import com.selada.kebonmobile.util.ViewPagerAdapter;
import com.selada.kebonmobile.util.ViewPagerStatusAdapter;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StatusActivity extends AppCompatActivity {

    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.tab_lahan)
    FrameLayout tab_lahan;
    @BindView(R.id.tab_tanaman)
    FrameLayout tab_tanaman;

    @OnClick(R.id.btn_back)
    void onClickBack(){
        onBackPressed();
    }

    @OnClick(R.id.btn_history)
    void onClickHistory(){
        Intent intent = new Intent(this, HistoryActivity.class);
        startActivity(intent);
        this.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @OnClick(R.id.tab_lahan)
    void onClickTabLahan(){
        viewPager.setCurrentItem(0);
    }

    @OnClick(R.id.tab_tanaman)
    void onClickTabTanaman(){
        viewPager.setCurrentItem(1);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);
        ButterKnife.bind(this);

        initComponent();
    }

    private void initComponent() {
        boolean isFromNotif = getIntent().getBooleanExtra("is_from_notif", false);
        if (isFromNotif){
            ViewPagerStatusAdapter adapter = new ViewPagerStatusAdapter(getSupportFragmentManager());
            viewPager.setAdapter(adapter);
            viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    switch (position){
                        case 0:
                            tab_lahan.setBackground(getResources().getDrawable(R.drawable.bg_round_tab_status_on_presssed));
                            tab_tanaman.setBackground(getResources().getDrawable(R.drawable.bg_round_tab_status));
                            break;
                        case 1:
                            tab_lahan.setBackground(getResources().getDrawable(R.drawable.bg_round_tab_status));
                            tab_tanaman.setBackground(getResources().getDrawable(R.drawable.bg_round_tab_status_on_presssed));
                            break;
                    }
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });

            String siteId = getIntent().getStringExtra("site_id");
            String commodityId = getIntent().getStringExtra("commodity_id");

            if (Objects.equals(commodityId, "")){
                getMonitoringMyFarm(Integer.parseInt(siteId));
            } else {
                getDetailTanaman(commodityId, siteId);
            }
        } else {
            ViewPagerStatusAdapter adapter = new ViewPagerStatusAdapter(getSupportFragmentManager());
            viewPager.setAdapter(adapter);
            viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    switch (position){
                        case 0:
                            tab_lahan.setBackground(getResources().getDrawable(R.drawable.bg_round_tab_status_on_presssed));
                            tab_tanaman.setBackground(getResources().getDrawable(R.drawable.bg_round_tab_status));
                            break;
                        case 1:
                            tab_lahan.setBackground(getResources().getDrawable(R.drawable.bg_round_tab_status));
                            tab_tanaman.setBackground(getResources().getDrawable(R.drawable.bg_round_tab_status_on_presssed));
                            break;
                    }
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
        }
    }


    private void getDetailTanaman(String id, String siteId){
        Loading.show(StatusActivity.this);
        ApiCore.apiInterface().getMyFarmCommoditiesMonitoring(id, PreferenceManager.getSessionToken()).enqueue(new Callback<ApiResponse<CommodityMonitoringResponse>>() {
            @Override
            public void onResponse(Call<ApiResponse<CommodityMonitoringResponse>> call, Response<ApiResponse<CommodityMonitoringResponse>> response) {
                Loading.hide(StatusActivity.this);
                try {
                    if (response.isSuccessful()){
                        CommodityMonitoringResponse commoditiesResponse = Objects.requireNonNull(response.body()).getData();

                        Intent intent = new Intent(StatusActivity.this, DetailStatusTanamanActivity.class);
                        intent.putExtra("site_id", Integer.parseInt(siteId));
                        intent.putExtra("json", new Gson().toJson(commoditiesResponse));
                        startActivity(intent);
                        StatusActivity.this.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    } else {
                        MethodUtil.getDialogWarning(response.errorBody(), StatusActivity.this);
                    }
                } catch (Exception e){
                    e.printStackTrace();
                    MethodUtil.getDialogWarningCatch(StatusActivity.this);
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<CommodityMonitoringResponse>> call, Throwable t) {
                Loading.hide(StatusActivity.this);
                t.printStackTrace();
            }
        });
    }

    private void getMonitoringMyFarm(int site_id){
        Loading.show(StatusActivity.this);
        ApiCore.apiInterface().getMonitoringMyFarm(String.valueOf(site_id), PreferenceManager.getSessionToken()).enqueue(new Callback<FarmSummaryResponse>() {
            @Override
            public void onResponse(Call<FarmSummaryResponse> call, Response<FarmSummaryResponse> response) {
                Loading.hide(StatusActivity.this);
                try {
                    if (response.isSuccessful()) {
                        Farm responses = Objects.requireNonNull(response.body()).getFarm();
                        String url = "";
                        for (SiteComponent siteComponent: responses.getSiteComponents()){
                            if (siteComponent.getCode().contains("camera")) {
                                url = siteComponent.getAppUrlAddress();
                            }
                        }

                        Intent intent = new Intent(StatusActivity.this, DetailStatusLahanActivity.class);
                        intent.putExtra("json_farm", new Gson().toJson(responses));
                        intent.putExtra("url", url);
                        StatusActivity.this.startActivity(intent);
                        StatusActivity.this.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    } else {
                        Gson gson = new Gson();
                        ErrorResponse message = gson.fromJson(Objects.requireNonNull(response.errorBody()).charStream(), ErrorResponse.class);
                        String errorMessage = message.getData().getMessage();
                        Dialog dialog = MethodUtil.getDialogCart(R.layout.dialog_warning, StatusActivity.this);
                        TextView desc = dialog.findViewById(R.id.tv_desc);
                        desc.setText(errorMessage);
                        ImageView btn_close = dialog.findViewById(R.id.btn_close);
                        btn_close.setOnClickListener(view -> {
                            dialog.dismiss();
                        });
                    }
                } catch (Exception e){
                    e.printStackTrace();
                    String errorMessage = "Terjadi kesalahan (Response Code: On Catch)";
                    Dialog dialog = MethodUtil.getDialogCart(R.layout.dialog_warning, StatusActivity.this);
                    TextView desc = dialog.findViewById(R.id.tv_desc);
                    desc.setText(errorMessage);
                }
            }

            @Override
            public void onFailure(Call<FarmSummaryResponse> call, Throwable t) {
                t.printStackTrace();
                Loading.hide(StatusActivity.this);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        this.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
}