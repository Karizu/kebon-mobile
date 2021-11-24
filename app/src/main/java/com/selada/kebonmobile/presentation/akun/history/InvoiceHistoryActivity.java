package com.selada.kebonmobile.presentation.akun.history;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.madapps.liquid.LiquidRefreshLayout;
import com.selada.kebonmobile.R;
import com.selada.kebonmobile.model.response.ApiResponse;
import com.selada.kebonmobile.model.response.errormsg.ErrorResponse;
import com.selada.kebonmobile.model.response.invoice.InvoiceDetailResponse;
import com.selada.kebonmobile.model.response.invoice.history.InvoiceHistoryResponse;
import com.selada.kebonmobile.model.response.invoice.history.Invoices;
import com.selada.kebonmobile.network.ApiCore;
import com.selada.kebonmobile.presentation.akun.history.adapter.InvoiceHistoryAdapter;
import com.selada.kebonmobile.presentation.home.pembayaran.RincianPembayaranActivity;
import com.selada.kebonmobile.util.Loading;
import com.selada.kebonmobile.util.MethodUtil;
import com.selada.kebonmobile.util.PreferenceManager;

import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InvoiceHistoryActivity extends AppCompatActivity {

    @BindView(R.id.rv_invoice_history)
    RecyclerView rv_invoice_history;
    @BindView(R.id.layout_no_data)
    RelativeLayout tv_no_data;
    @BindView(R.id.refreshLayout)
    LiquidRefreshLayout refreshLayout;

    @OnClick(R.id.btn_back)
    void onClickBack(){
        onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice_history);
        ButterKnife.bind(this);
        new PreferenceManager(this);
        initComponent();
    }

    private void initComponent() {
        boolean isFromNotif = getIntent().getBooleanExtra("is_from_notif", false);
        getListInvoiceHistory(false);
        refreshLayout.setOnRefreshListener(new LiquidRefreshLayout.OnRefreshListener() {
            @Override
            public void completeRefresh() {

            }

            @Override
            public void refreshing() {
                getListInvoiceHistory(true);
            }
        });
        if (isFromNotif) {
            String invoiceId = getIntent().getStringExtra("invoice_id");
            getInvoiceDetail(invoiceId);
        }
    }

    private void getListInvoiceHistory(boolean isRefresh){
        Loading.show(this);
        ApiCore.apiInterface().getInvoiceHistory(PreferenceManager.getSessionToken()).enqueue(new Callback<ApiResponse<InvoiceHistoryResponse>>() {
            @Override
            public void onResponse(Call<ApiResponse<InvoiceHistoryResponse>> call, Response<ApiResponse<InvoiceHistoryResponse>> response) {
                Loading.hide(InvoiceHistoryActivity.this);
                if (isRefresh) refreshLayout.finishRefreshing();
                try {
                    if (response.isSuccessful()){
                        List<Invoices> detailResponse = Objects.requireNonNull(response.body()).getData().getInvoices();
                        if (detailResponse.size()>0){
                            tv_no_data.setVisibility(View.GONE);
                            rv_invoice_history.setVisibility(View.VISIBLE);
                            rv_invoice_history.setLayoutManager(new LinearLayoutManager(InvoiceHistoryActivity.this, LinearLayoutManager.VERTICAL, false));
                            InvoiceHistoryAdapter adapter = new InvoiceHistoryAdapter(detailResponse, InvoiceHistoryActivity.this, InvoiceHistoryActivity.this);
                            rv_invoice_history.setAdapter(adapter);
                        } else {
                            tv_no_data.setVisibility(View.VISIBLE);
                            rv_invoice_history.setVisibility(View.GONE);
                        }
                    } else {
                        MethodUtil.getDialogWarning(response.errorBody(), InvoiceHistoryActivity.this);
                    }
                } catch (Exception e){
                    e.printStackTrace();
                    MethodUtil.getDialogWarningCatch(InvoiceHistoryActivity.this);
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<InvoiceHistoryResponse>> call, Throwable t) {
                t.printStackTrace();
                if (isRefresh) refreshLayout.finishRefreshing();
                Loading.hide(InvoiceHistoryActivity.this);
            }
        });
    }

    private void getInvoiceDetail(String invoice_id){
        Loading.show(InvoiceHistoryActivity.this);
        ApiCore.apiInterface().getInvoiceDetail(invoice_id, PreferenceManager.getSessionToken()).enqueue(new Callback<ApiResponse<InvoiceDetailResponse>>() {
            @Override
            public void onResponse(Call<ApiResponse<InvoiceDetailResponse>> call, Response<ApiResponse<InvoiceDetailResponse>> response) {
                Loading.hide(InvoiceHistoryActivity.this);
                try {
                    if (response.isSuccessful()){
                        InvoiceDetailResponse invoiceDetailResponse = Objects.requireNonNull(response.body()).getData();
                        String jsonResponse = new Gson().toJson(invoiceDetailResponse);

                        Intent intent = new Intent(InvoiceHistoryActivity.this, RincianPembayaranActivity.class);
                        intent.putExtra("is_from_adapter", true);
                        intent.putExtra("invoice_detail_response", jsonResponse);
                        InvoiceHistoryActivity.this.startActivity(intent);
                        InvoiceHistoryActivity.this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    } else {
                        Gson gson = new Gson();
                        ErrorResponse message = gson.fromJson(Objects.requireNonNull(response.errorBody()).charStream(),ErrorResponse.class);
                        String errorMessage = message.getData().getMessage();
                        Dialog dialog = MethodUtil.getDialogCart(R.layout.dialog_warning, InvoiceHistoryActivity.this);
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
                    Dialog dialog = MethodUtil.getDialogCart(R.layout.dialog_warning, InvoiceHistoryActivity.this);
                    TextView desc = dialog.findViewById(R.id.tv_desc);
                    desc.setText(errorMessage);
                    ImageView btn_close = dialog.findViewById(R.id.btn_close);
                    btn_close.setOnClickListener(view -> {
                        dialog.dismiss();
                    });
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<InvoiceDetailResponse>> call, Throwable t) {
                t.printStackTrace();
                Loading.hide(InvoiceHistoryActivity.this);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
}