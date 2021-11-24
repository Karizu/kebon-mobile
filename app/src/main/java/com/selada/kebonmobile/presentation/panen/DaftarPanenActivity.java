package com.selada.kebonmobile.presentation.panen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.selada.kebonmobile.R;
import com.selada.kebonmobile.model.response.calendardetail.DetailHarvestCalendarResponse;
import com.selada.kebonmobile.model.response.calendardetail.HarvestSchedule;
import com.selada.kebonmobile.model.response.farmsummary.Farm;
import com.selada.kebonmobile.model.response.farmsummary.FarmSummaryResponse;
import com.selada.kebonmobile.model.response.mycommodities.Commodity;
import com.selada.kebonmobile.model.response.mycommodities.MyFarmCommoditiesResponse;
import com.selada.kebonmobile.network.ApiCore;
import com.selada.kebonmobile.presentation.jadwal.JadwalActivity;
import com.selada.kebonmobile.presentation.status.tab.adapter.StatusTanamanAdapter;
import com.selada.kebonmobile.util.CustomBottomSheetDialog;
import com.selada.kebonmobile.util.Loading;
import com.selada.kebonmobile.util.MethodUtil;
import com.selada.kebonmobile.util.PreferenceManager;
import com.skydoves.elasticviews.ElasticLayout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DaftarPanenActivity extends AppCompatActivity {

    @BindView(R.id.spinner_filter_tanaman)
    Spinner spinner_filter_tanaman;
    @BindView(R.id.rv_status_tanaman)
    RecyclerView rv_status_tanaman;
    @BindView(R.id.layout_no_data)
    RelativeLayout layout_no_data;
    @BindView(R.id.layout_spinner)
    ElasticLayout layout_spinner;

    private Activity activity;
    private DaftarPanenAdapter adapter;
    private String currentDate = "";

    @OnClick(R.id.btn_lihat_jadwal)
    void onClickLihatJadwal(){
        startActivity(new Intent(DaftarPanenActivity.this, JadwalActivity.class));
        this.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
    
    @OnClick(R.id.layout_spinner)
    void onClickSpinner(){
        spinner_filter_tanaman.performClick();
    }

    @OnClick(R.id.btn_jadwal)
    void onClickJadwal(){
        startActivity(new Intent(DaftarPanenActivity.this, JadwalActivity.class));
        this.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @OnClick(R.id.btn_back)
    void onClickBack(){
        onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_panen);
        ButterKnife.bind(this);
        activity = this;

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", new Locale("id", "ID"));
        currentDate = dateFormat.format(new Date());

        getMyFarmSummary();
    }

    private void getDetailHarvestCalendarFilter(String site_id, String date){
        Loading.show(activity);
        ApiCore.apiInterface().getDetailHarvestCalendar(site_id, "", date, PreferenceManager.getSessionToken()).enqueue(new Callback<DetailHarvestCalendarResponse>() {
            @Override
            public void onResponse(Call<DetailHarvestCalendarResponse> call, Response<DetailHarvestCalendarResponse> response) {
                Loading.hide(activity);
                try {
                    if (response.isSuccessful()){
                        List<HarvestSchedule> harvestScheduleList = Objects.requireNonNull(response.body()).getHarvestSchedules();
                        if (harvestScheduleList.size()>0){
                            layout_no_data.setVisibility(View.GONE);
                            rv_status_tanaman.setVisibility(View.VISIBLE);
                            adapter = new DaftarPanenAdapter(harvestScheduleList, activity, activity);
                            rv_status_tanaman.setLayoutManager(new GridLayoutManager(activity, 2));
                            rv_status_tanaman.setAdapter(adapter);
                        } else {
                            layout_no_data.setVisibility(View.VISIBLE);
                            rv_status_tanaman.setVisibility(View.GONE);
                        }
                    } else {
                        MethodUtil.getDialogWarning(response.errorBody(), activity);
                    }
                } catch (Exception e){
                    e.printStackTrace();
                    MethodUtil.getDialogWarningCatch(activity);
                }
            }

            @Override
            public void onFailure(Call<DetailHarvestCalendarResponse> call, Throwable t) {
                Loading.hide(activity);
                t.printStackTrace();
            }
        });
    }

    private void getMyFarmSummary() {
        List<String> farmNameList = new ArrayList<>();
        List<String> farmNameIdList = new ArrayList<>();

        Loading.show(activity);
        ApiCore.apiInterface().getMyFarmSummary(PreferenceManager.getSessionToken()).enqueue(new Callback<FarmSummaryResponse>() {
            @Override
            public void onResponse(Call<FarmSummaryResponse> call, Response<FarmSummaryResponse> response) {
                Loading.hide(activity);
                try {
                    if (response.isSuccessful()) {
                        List<Farm> farmList = Objects.requireNonNull(response.body()).getFarms();
                        for (Farm farm : farmList) {
                            farmNameList.add(farm.getName());
                            farmNameIdList.add(farm.getSite_id() + "");
                        }

                        ArrayAdapter aa = new ArrayAdapter(activity, R.layout.custom_spinner_item, farmNameList);

                        aa.setDropDownViewResource(R.layout.custom_spinner_drop_item);
                        spinner_filter_tanaman.setAdapter(aa);

                        spinner_filter_tanaman.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                getDetailHarvestCalendarFilter(farmNameIdList.get(i), currentDate);
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {

                            }
                        });

                    } else {
                        MethodUtil.getDialogWarning(response.errorBody(), activity);
                    }

                } catch (Exception e) {
                    MethodUtil.getDialogWarningCatch(activity);
                }
            }

            @Override
            public void onFailure(Call<FarmSummaryResponse> call, Throwable t) {
                t.printStackTrace();
                Loading.hide(activity);
            }
        });
    }
}