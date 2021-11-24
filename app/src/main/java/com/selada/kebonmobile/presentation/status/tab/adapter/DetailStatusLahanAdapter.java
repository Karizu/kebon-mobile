package com.selada.kebonmobile.presentation.status.tab.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
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
import com.selada.kebonmobile.model.response.commoditymonitoring.CommodityMonitoringResponse;
import com.selada.kebonmobile.model.response.detailcommodities.DetailCommoditiesResponse;
import com.selada.kebonmobile.model.response.farmsummary.ActiveCommodity;
import com.selada.kebonmobile.network.ApiCore;
import com.selada.kebonmobile.presentation.status.tab.tanaman.DetailStatusTanamanActivity;
import com.selada.kebonmobile.presentation.status.tab.tanaman.DetailTanamanActivity;
import com.selada.kebonmobile.util.Loading;
import com.selada.kebonmobile.util.MethodUtil;
import com.selada.kebonmobile.util.PreferenceManager;
import com.skydoves.elasticviews.ElasticButton;

import java.util.List;
import java.util.Objects;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailStatusLahanAdapter extends RecyclerView.Adapter<DetailStatusLahanAdapter.ViewHolder> {
    private List<ActiveCommodity> transactionModels;
    private Context context;
    private Activity activity;

    public DetailStatusLahanAdapter(List<ActiveCommodity> transactionModels, Context context, Activity activity) {
        this.transactionModels = transactionModels;
        this.context = context;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_detail_status_lahan, parent, false);
        return new ViewHolder(v);
    }

    @SuppressLint({"SetTextI18n", "ResourceAsColor", "CheckResult"})
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ActiveCommodity activeCommodity = transactionModels.get(position);
        holder.tv_plant_name.setText(activeCommodity.getName());
        holder.tv_panen.setText(activeCommodity.getNearest_remaining_days_to_harvest() + " Hari");
        Glide.with(activity)
                .load(activeCommodity.getMainImage().getFullpath())
                .placeholder(R.drawable.img_plant)
                .into(holder.img_plant);
        holder.tv_total.setText(activeCommodity.getCount());
        holder.cvItem.setOnClickListener(view -> {
            getDetailTanaman(String.valueOf(activeCommodity.getId()), activeCommodity.getId());
        });
    }

    @Override
    public int getItemCount() {
        return transactionModels.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_plant_name, tv_site, tv_total, tv_panen;
        ConstraintLayout cvItem;
        ImageView img_plant;

        ViewHolder(View v) {
            super(v);
            tv_plant_name = v.findViewById(R.id.tv_plant_name);
            tv_site = v.findViewById(R.id.tv_site);
            tv_panen = v.findViewById(R.id.tv_panen);
            tv_total = v.findViewById(R.id.tv_total);
            img_plant = v.findViewById(R.id.img_plant);
            cvItem = v.findViewById(R.id.constraintLayout6);
        }
    }

    private void getDetailTanaman(String id, int siteId){
        Loading.show(activity);
        ApiCore.apiInterface().getMyFarmCommoditiesMonitoring(id, PreferenceManager.getSessionToken()).enqueue(new Callback<ApiResponse<CommodityMonitoringResponse>>() {
            @Override
            public void onResponse(Call<ApiResponse<CommodityMonitoringResponse>> call, Response<ApiResponse<CommodityMonitoringResponse>> response) {
                Loading.hide(activity);
                try {
                    if (response.isSuccessful()){
                        CommodityMonitoringResponse commoditiesResponse = Objects.requireNonNull(response.body()).getData();

//                        Intent intent = new Intent(activity, DetailTanamanActivity.class);
                        Intent intent = new Intent(activity, DetailStatusTanamanActivity.class);
                        intent.putExtra("json", new Gson().toJson(commoditiesResponse));
                        intent.putExtra("site_id", siteId);
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
            public void onFailure(Call<ApiResponse<CommodityMonitoringResponse>> call, Throwable t) {
                Loading.hide(activity);
                t.printStackTrace();
            }
        });
    }
}
