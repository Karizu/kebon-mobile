package com.selada.kebonmobile.presentation.akun.history.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.selada.kebonmobile.R;
import com.selada.kebonmobile.model.response.ApiResponse;
import com.selada.kebonmobile.model.response.errormsg.ErrorResponse;
import com.selada.kebonmobile.model.response.invoice.InvoiceDetailResponse;
import com.selada.kebonmobile.model.response.invoice.history.InvoiceHistoryResponse;
import com.selada.kebonmobile.model.response.invoice.history.Invoices;
import com.selada.kebonmobile.network.ApiCore;
import com.selada.kebonmobile.presentation.home.pembayaran.RincianPembayaranActivity;
import com.selada.kebonmobile.util.Loading;
import com.selada.kebonmobile.util.MethodUtil;
import com.selada.kebonmobile.util.PreferenceManager;
import com.skydoves.elasticviews.ElasticLayout;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InvoiceHistoryAdapter extends RecyclerView.Adapter<InvoiceHistoryAdapter.ViewHolder> {
    private List<Invoices> invoices;
    private Context context;
    private Activity activity;

    public InvoiceHistoryAdapter(List<Invoices> invoices, Context context, Activity activity) {
        this.invoices = invoices;
        this.context = context;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_invoice_history, parent, false);

        return new ViewHolder(v);
    }

    @SuppressLint({"SetTextI18n", "ResourceAsColor", "CheckResult"})
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Invoices response = invoices.get(position);
        boolean isPaid = response.isPaid_status();

        String id = response.getInvoiceId()+"";
        String invoiceNo = response.getCode();
        String desc = response.getBillingDescription();
        String date = isPaid?response.getPayment().getPaymentDate():response.getIssuedDate();
        date = date.substring(0, date.length()-4);
        String[] dateAndTime = MethodUtil.formatDateAndTime(date);
        String statusBayar = isPaid?"Berhasil":"Menunggu Pembayaran";
        holder.tv_title.setText(invoiceNo);
        holder.tv_message.setText(desc);
        holder.tv_date.setText(dateAndTime[0] + " " + dateAndTime[1]);
        if (isPaid) {
            holder.tv_status_bayar.setTextColor(activity.getResources().getColor(R.color.colorGreenText));
        } else {
            holder.tv_status_bayar.setTextColor(activity.getResources().getColor(R.color.colorTextOrange));
        }
        holder.tv_status_bayar.setText(statusBayar);
        holder.cvItem.setOnClickListener(view -> {
            getInvoiceDetail(id);
        });
    }

    @Override
    public int getItemCount() {
        return invoices.size();

    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_title, tv_message, tv_date, tv_status_bayar;
        ElasticLayout cvItem;

        ViewHolder(View v) {
            super(v);
            tv_title = v.findViewById(R.id.tv_title);
            tv_message = v.findViewById(R.id.tv_message);
            tv_date = v.findViewById(R.id.tv_date);
            tv_status_bayar = v.findViewById(R.id.tv_status_bayar);
            cvItem = v.findViewById(R.id.cvItem);
        }
    }

    private void getInvoiceDetail(String invoice_id){
        Loading.show(activity);
        ApiCore.apiInterface().getInvoiceDetail(invoice_id, PreferenceManager.getSessionToken()).enqueue(new Callback<ApiResponse<InvoiceDetailResponse>>() {
            @Override
            public void onResponse(Call<ApiResponse<InvoiceDetailResponse>> call, Response<ApiResponse<InvoiceDetailResponse>> response) {
                Loading.hide(activity);
                try {
                    if (response.isSuccessful()){
                        InvoiceDetailResponse invoiceDetailResponse = Objects.requireNonNull(response.body()).getData();
                        String jsonResponse = new Gson().toJson(invoiceDetailResponse);

                        Intent intent = new Intent(activity, RincianPembayaranActivity.class);
                        intent.putExtra("is_from_adapter", true);
                        intent.putExtra("invoice_detail_response", jsonResponse);
                        activity.startActivity(intent);
                        activity.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    } else {
                        Gson gson = new Gson();
                        ErrorResponse message = gson.fromJson(Objects.requireNonNull(response.errorBody()).charStream(),ErrorResponse.class);
                        String errorMessage = message.getData().getMessage();
                        Dialog dialog = MethodUtil.getDialogCart(R.layout.dialog_warning, activity);
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
                    Dialog dialog = MethodUtil.getDialogCart(R.layout.dialog_warning, activity);
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
                Loading.hide(activity);
            }
        });
    }
}
