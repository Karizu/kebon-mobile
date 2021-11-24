package com.selada.kebonmobile.presentation.home.lahan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.selada.kebonmobile.R;
import com.selada.kebonmobile.model.SewaLahanModel;
import com.selada.kebonmobile.model.response.ApiResponse;
import com.selada.kebonmobile.model.response.SiteLeasableResponse;
import com.selada.kebonmobile.network.ApiCore;
import com.selada.kebonmobile.presentation.home.lahan.adapter.HomeSewaLahanAdapter;
import com.selada.kebonmobile.presentation.home.pembayaran.PembayaranActivity;
import com.selada.kebonmobile.util.CustomLayoutManager;
import com.selada.kebonmobile.util.Loading;
import com.selada.kebonmobile.util.MethodUtil;
import com.selada.kebonmobile.util.PreferenceManager;
import com.skydoves.elasticviews.ElasticImageView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SewaLahanActivity extends AppCompatActivity {

//    @BindView(R.id.tv_quantity_kavling)
//    TextView tv_quantity_kavling;
//    @BindView(R.id.btn_min_kavling)
//    ElasticImageView btn_min_kavling;
//    @BindView(R.id.tv_quantity_sewa)
//    TextView tv_quantity_sewa;
//    @BindView(R.id.btn_min_sewa)
//    ElasticImageView btn_min_sewa;
    @BindView(R.id.rv_pilih_lahan)
    RecyclerView rv_pilih_lahan;
    @BindView(R.id.btn_chat)
    ElasticImageView btn_chat;

    @BindView(R.id.tv_title_bar)
    TextView tv_title_bar;

    private CustomLayoutManager linearLayoutManager;
    private int qtyKavling = 0;
    private int qtySewa = 0;
    private Context context;
    private HomeSewaLahanAdapter adapter;
    private List<SiteLeasableResponse> responseList;
    private List<SiteLeasableResponse> responses;
    private boolean isFromAdapter;

    @OnClick(R.id.btn_back)
    void onClickBtnBack(){
        onBackPressed();
    }

    @OnClick(R.id.btn_previous)
    void onClickPrevious(){
        if (linearLayoutManager.findLastCompletelyVisibleItemPosition() <= (adapter.getItemCount() - 1)) {
            linearLayoutManager.scrollToPosition(linearLayoutManager.findLastCompletelyVisibleItemPosition() - 1);
            if (isFromAdapter){
                adapter = new HomeSewaLahanAdapter(responseList, SewaLahanActivity.this, SewaLahanActivity.this);
            } else {
                adapter = new HomeSewaLahanAdapter(responses, SewaLahanActivity.this, SewaLahanActivity.this);
            }
            rv_pilih_lahan.setAdapter(adapter);
        }
    }

    @OnClick(R.id.btn_next)
    void onClickNext(){
        if (linearLayoutManager.findLastCompletelyVisibleItemPosition() < (adapter.getItemCount() - 1)) {
            linearLayoutManager.scrollToPosition(linearLayoutManager.findLastCompletelyVisibleItemPosition() + 1);
            if (isFromAdapter){
                adapter = new HomeSewaLahanAdapter(responseList, SewaLahanActivity.this, SewaLahanActivity.this);
            } else {
                adapter = new HomeSewaLahanAdapter(responses, SewaLahanActivity.this, SewaLahanActivity.this);
            }
            rv_pilih_lahan.setAdapter(adapter);
        }
    }

//    @OnClick(R.id.btn_min_kavling)
//    void onClickBtnMinKavling(){
//        qtyKavling--;
//        if (qtyKavling == 0){
//            btn_min_kavling.setEnabled(false);
//            tv_quantity_kavling.setText("0");
//        } else {
//            tv_quantity_kavling.setText(String.valueOf(qtyKavling));
//        }
//    }

//    @OnClick(R.id.btn_add_kavling)
//    void onClickBtnAddKavling(){
//        qtyKavling++;
//        btn_min_kavling.setEnabled(true);
//        tv_quantity_kavling.setText(String.valueOf(qtyKavling));
//    }

//    @OnClick(R.id.btn_min_sewa)
//    void onClickBtnMinSewa(){
//        qtySewa--;
//        if (qtySewa == 0){
//            btn_min_sewa.setEnabled(false);
//            tv_quantity_sewa.setText("0");
//        } else {
//            tv_quantity_sewa.setText(String.valueOf(qtySewa));
//        }
//    }

//    @OnClick(R.id.btn_add_sewa)
//    void onClickBtnAddSewa(){
//        qtySewa++;
//        btn_min_sewa.setEnabled(true);
//        tv_quantity_sewa.setText(String.valueOf(qtySewa));
//    }

    @OnClick(R.id.btn_istilah)
    void onClickBtnIstilah(){
        Dialog dialog = MethodUtil.getDialogCart(R.layout.dialog_istilah, context);
        ImageView btnClose = dialog.findViewById(R.id.btn_close);
        btnClose.setOnClickListener(view -> dialog.dismiss());
    }

    @OnClick(R.id.btn_pilih)
    void onClickPilih(){
        if (adapter.isSewaLahanAvailable()){
            SewaLahanModel lahanModel = PreferenceManager.getSewaLahan();
            if (lahanModel.getJumlahKavling().equals("0") || lahanModel.getLamaSewa().equals("0")){
                Toast.makeText(context, "Tentukan jumlah kavling dan lama sewa lahan", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(SewaLahanActivity.this, PembayaranActivity.class);
                startActivity(intent);
                SewaLahanActivity.this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sewa_lahan);
        ButterKnife.bind(this);
        new PreferenceManager(this);
        context = this;
        initComponent();
    }

    @SuppressLint("SetTextI18n")
    private void initComponent() {
        isFromAdapter = getIntent().getBooleanExtra("is_from_adapter", false);
        int siteId = getIntent().getIntExtra("site_id", 0);
        btn_chat.setVisibility(View.GONE);
        tv_title_bar.setText("Sewa Lahan");
        linearLayoutManager = new CustomLayoutManager(context) {
            @Override
            public boolean canScrollHorizontally() {
                return false;
            }

            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };

        rv_pilih_lahan.setLayoutManager(linearLayoutManager);
        getListSiteLeasable(isFromAdapter, siteId);
    }

    private void getListSiteLeasable(boolean isFromAdapter, int siteId) {
        Loading.show(this);
        ApiCore.apiInterface().getListSiteLeasable(PreferenceManager.getSessionToken()).enqueue(new Callback<ApiResponse<List<SiteLeasableResponse>>>() {
            @Override
            public void onResponse(Call<ApiResponse<List<SiteLeasableResponse>>> call, Response<ApiResponse<List<SiteLeasableResponse>>> response) {
                Loading.hide(SewaLahanActivity.this);
                try {
                    responses = Objects.requireNonNull(response.body()).getData();

                    if (isFromAdapter){
                        responseList = new ArrayList<>();
                        for (SiteLeasableResponse leasableResponse: responses){
                            if (leasableResponse.getId() == siteId){
                                responseList.add(leasableResponse);
                            }
                        }

                        for (SiteLeasableResponse leasableResponse: responses){
                            if (leasableResponse.getId() != siteId){
                                responseList.add(leasableResponse);
                            }
                        }

                        adapter = new HomeSewaLahanAdapter(responseList, SewaLahanActivity.this, SewaLahanActivity.this);
                        rv_pilih_lahan.setAdapter(adapter);
                    } else {
                        adapter = new HomeSewaLahanAdapter(responses, SewaLahanActivity.this, SewaLahanActivity.this);
                        rv_pilih_lahan.setAdapter(adapter);
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<List<SiteLeasableResponse>>> call, Throwable t) {
                t.printStackTrace();
                Loading.hide(SewaLahanActivity.this);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}