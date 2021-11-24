package com.selada.kebonmobile.presentation.panen;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.selada.kebonmobile.R;
import com.selada.kebonmobile.model.response.ApiResponse;
import com.selada.kebonmobile.model.response.calendardetail.HarvestSchedule;
import com.selada.kebonmobile.model.response.commoditymonitoring.CommodityMonitoringResponse;
import com.selada.kebonmobile.model.response.farmsummary.FarmSummaryInSite;
import com.selada.kebonmobile.model.response.harvest.HarvestInquiryResponse;
import com.selada.kebonmobile.model.response.mycommodities.Commodity;
import com.selada.kebonmobile.network.ApiCore;
import com.selada.kebonmobile.presentation.status.tab.tanaman.DetailStatusTanamanActivity;
import com.selada.kebonmobile.presentation.status.tab.tanaman.panen.PanenTanamanActivity;
import com.selada.kebonmobile.util.Loading;
import com.selada.kebonmobile.util.MethodUtil;
import com.selada.kebonmobile.util.PreferenceManager;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DaftarPanenAdapter extends RecyclerView.Adapter<DaftarPanenAdapter.ViewHolder> {
    private List<HarvestSchedule> transactionModels;
    private Context context;
    private Activity activity;

    public DaftarPanenAdapter(List<HarvestSchedule> transactionModels, Context context, Activity activity) {
        this.transactionModels = transactionModels;
        this.context = context;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_status_tanaman, parent, false);

        return new ViewHolder(v);
    }

    @SuppressLint({"SetTextI18n", "ResourceAsColor", "CheckResult"})
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HarvestSchedule harvestSchedule = transactionModels.get(position);
        String url = harvestSchedule.getMainImage()!=null ? harvestSchedule.getMainImage().getFullpath():"";
        int pos = position+1;
        holder.tv_farm_name.setText(harvestSchedule.getCommodityName());
        holder.tv_total_location.setText(harvestSchedule.getSiteName());
        Glide.with(activity)
                .load(url)
                .placeholder(R.drawable.img_plant)
                .into(holder.img_plant);
        holder.tv_tanaman_no.setText("");
        holder.cvItem.setOnClickListener(view -> {
            getDetailTanaman(harvestSchedule.getCommodityId()+"", String.valueOf(harvestSchedule.getSiteId()));
        });
    }

    @Override
    public int getItemCount() {
        return transactionModels.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img_plant;
        TextView tv_farm_name, tv_tanaman_no, tv_total_location;
        ConstraintLayout cvItem;

        ViewHolder(View v) {
            super(v);
            img_plant = v.findViewById(R.id.img_plant);
            tv_farm_name = v.findViewById(R.id.tv_farm_name);
            tv_tanaman_no = v.findViewById(R.id.tv_tanaman_no);
            tv_total_location = v.findViewById(R.id.tv_total_location);
            cvItem = v.findViewById(R.id.constraintLayout6);
        }
    }

    private void getDetailTanaman(String commodityId, String siteId){
        Loading.show(activity);

        ApiCore.apiInterface().getInquiryHarvest(siteId, "harvest_inquiry", commodityId, PreferenceManager.getSessionToken()).enqueue(new Callback<HarvestInquiryResponse>() {
            @Override
            public void onResponse(Call<HarvestInquiryResponse> call, Response<HarvestInquiryResponse> response) {
                Loading.hide(activity);
                try {
                    if (response.isSuccessful()){
                        String json = new Gson().toJson(response.body());
                        Intent intent = new Intent(activity, PanenTanamanActivity.class);
                        intent.putExtra("json", json);
                        activity.startActivity(intent);
                        activity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    } else {
                        MethodUtil.getDialogWarning(response.errorBody(), activity);
                    }
                } catch (Exception e){
                    e.printStackTrace();
                    MethodUtil.getDialogWarningCatch(activity);
                }
            }

            @Override
            public void onFailure(Call<HarvestInquiryResponse> call, Throwable t) {
                t.printStackTrace();
                Loading.hide(activity);
            }
        });
    }
}
