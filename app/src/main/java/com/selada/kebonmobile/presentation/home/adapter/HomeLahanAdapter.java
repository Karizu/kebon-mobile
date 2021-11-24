package com.selada.kebonmobile.presentation.home.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.selada.kebonmobile.R;
import com.selada.kebonmobile.model.response.ApiResponse;
import com.selada.kebonmobile.model.response.errormsg.ErrorResponse;
import com.selada.kebonmobile.model.response.farmsummary.Farm;
import com.selada.kebonmobile.model.response.farmsummary.FarmSummaryResponse;
import com.selada.kebonmobile.model.response.farmsummary.ObjectTypeSummary;
import com.selada.kebonmobile.model.response.farmsummary.SiteComponent;
import com.selada.kebonmobile.model.response.invoice.InvoiceDetailResponse;
import com.selada.kebonmobile.network.ApiCore;
import com.selada.kebonmobile.presentation.home.pembayaran.RincianPembayaranActivity;
import com.selada.kebonmobile.presentation.home.tanam.PilihMetodeActivity;
import com.selada.kebonmobile.presentation.status.tab.lahan.DetailStatusLahanActivity;
import com.selada.kebonmobile.util.Loading;
import com.selada.kebonmobile.util.MethodUtil;
import com.selada.kebonmobile.util.PreferenceManager;
import com.skydoves.elasticviews.ElasticButton;
import com.skydoves.elasticviews.ElasticCardView;
import com.skydoves.elasticviews.ElasticImageView;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeLahanAdapter extends RecyclerView.Adapter<HomeLahanAdapter.ViewHolder> {
    private List<Farm> transactionModels;
    private Context context;
    private Activity activity;

    public HomeLahanAdapter(List<Farm> transactionModels, Context context, Activity activity) {
        this.transactionModels = transactionModels;
        this.context = context;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_home_lahan, parent, false);
        return new ViewHolder(v);
    }

    @SuppressLint({"SetTextI18n", "ResourceAsColor", "CheckResult"})
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Farm farm = transactionModels.get(position);
        int id = farm.getSite_id();
        String plantStatus = "Siap Ditanam";
        String buttonItemText;

        if (farm.getObjectTypeSummary().size()>0){
            for (ObjectTypeSummary summary: farm.getObjectTypeSummary()){
                if (summary.getFarmableStatus()){
                    if (summary.getTotalIdle()>0){
                        plantStatus = "Status: Siap Ditanam";
                        buttonItemText = "Mulai Tanam";
                        holder.btn_siap_tanam.setText(buttonItemText);
                        holder.btn_siap_tanam.setVisibility(View.VISIBLE);
                        holder.btn_siap_tanam.setOnClickListener(view -> {
                            Intent intent = new Intent(activity, PilihMetodeActivity.class);
                            intent.putExtra("id", id);
                            activity.startActivity(intent);
                            activity.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                        });
                    }

                    String pendingDesc = summary.getTotalPending()>0?" (Menunggu Pembayaran)" : "";
                    String summaryText = "Total: " + summary.getTotal() + " " + summary.getLabel() + "\n" +
                            "Siap tanam: " + summary.getTotalIdle() + " " + summary.getLabel();
                    holder.tv_summary_home.setText(summaryText);
                }
            }
        }

        holder.tv_farm_name.setText(farm.getName());
        holder.layout_already_plant_site.setVisibility(View.GONE);
        holder.layout_hold_and_plant.setVisibility(View.VISIBLE);

        if (farm.getUnpaidInvoices().size() > 0) {
            holder.btn_rincian.setVisibility(View.VISIBLE);
            plantStatus = "Status: Menunggu Pembayaran";
            buttonItemText = "Lihat Rincian Pembayaran";
            holder.btn_rincian.setText(buttonItemText);
            holder.btn_rincian.setOnClickListener(view -> {
                getInvoiceDetail("" + farm.getUnpaidInvoices().get(0).getInvoiceId());
            });
        } else {
            holder.btn_rincian.setVisibility(View.GONE);
        }

        holder.tv_lihat_selengkapnya.setOnClickListener(view -> {
//            Dialog dialog = MethodUtil.getDialogCart(R.layout.dialog_home_summary, activity);
//            RecyclerView recyclerView = dialog.findViewById(R.id.rv_home_summary);
//            SummarySiteAdapter adapter = new SummarySiteAdapter(farm.getObjectTypeSummary(), context, activity);
//            recyclerView.setAdapter(adapter);
            holder.cv_item.performClick();
        });

        holder.tv_status_farm.setText(plantStatus);

//        if (farm.getSiteComponents().size()>0){
//            for (SiteComponent component: farm.getSiteComponents()){
//                if (component.getCode().contains("camera")){
//                    url = component.getAppUrlAddress();
//                }
//            }
//        }
//
//        String finalUrl = url;
        Glide.with(activity)
                .load(farm.getImage().getFullpath())
                .placeholder(R.drawable.img_plant)
                .into(holder.img_farm);
        holder.img_farm.setOnClickListener(view -> holder.cv_item.performClick());
        holder.cv_item.setOnClickListener(view -> {
            getListSite(farm.getSite_id());
        });
    }

    @Override
    public int getItemCount() {
        return transactionModels.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img_farm;
        TextView tv_farm_name, tv_status_farm, tv_item_info, tv_lihat_selengkapnya, tv_summary_home;
        ElasticButton btn_rincian;
        LinearLayout layout_already_plant_site, layout_hold_and_plant;
        ElasticButton btn_siap_tanam;
        ElasticImageView btn_play;
        ElasticCardView cv_item;

        ViewHolder(View v) {
            super(v);
            tv_farm_name = v.findViewById(R.id.tv_farm_name);
            tv_status_farm = v.findViewById(R.id.tv_status_farm);
            tv_summary_home = v.findViewById(R.id.tv_summary_home);
            tv_lihat_selengkapnya = v.findViewById(R.id.tv_lihat_selengkapnya);
            tv_item_info = v.findViewById(R.id.tv_item_info);
            img_farm = v.findViewById(R.id.img_farm);
            btn_siap_tanam = v.findViewById(R.id.btn_siap_tanam);
            btn_rincian = v.findViewById(R.id.btn_rincian);
            layout_already_plant_site = v.findViewById(R.id.layout_already_plant_site);
            layout_hold_and_plant = v.findViewById(R.id.layout_ready_plant);
            btn_play = v.findViewById(R.id.btn_play);
            cv_item = v.findViewById(R.id.cv_item);
        }
    }

    private void getListSite(int site_id){
        Loading.show(activity);
        ApiCore.apiInterface().getMonitoringMyFarm(String.valueOf(site_id), PreferenceManager.getSessionToken()).enqueue(new Callback<FarmSummaryResponse>() {
            @Override
            public void onResponse(Call<FarmSummaryResponse> call, Response<FarmSummaryResponse> response) {
                Loading.hide(activity);
                try {
                    if (response.isSuccessful()) {
                        Farm responses = Objects.requireNonNull(response.body()).getFarm();
                        String url = "";
                        for (SiteComponent siteComponent: responses.getSiteComponents()){
                            if (siteComponent.getCode().contains("camera")) {
                                url = siteComponent.getAppUrlAddress();
                            }
                        }

                        Intent intent = new Intent(activity, DetailStatusLahanActivity.class);
                        intent.putExtra("json_farm", new Gson().toJson(responses));
                        intent.putExtra("url", url);
                        activity.startActivity(intent);
                        activity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
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
                }
            }

            @Override
            public void onFailure(Call<FarmSummaryResponse> call, Throwable t) {
                t.printStackTrace();
                Loading.hide(activity);
            }
        });
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
