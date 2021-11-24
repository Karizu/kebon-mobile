package com.selada.kebonmobile.presentation.jadwal;

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
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.selada.kebonmobile.R;
import com.selada.kebonmobile.model.response.ApiResponse;
import com.selada.kebonmobile.model.response.calendardetail.HarvestSchedule;
import com.selada.kebonmobile.model.response.commoditymonitoring.CommodityMonitoringResponse;
import com.selada.kebonmobile.network.ApiCore;
import com.selada.kebonmobile.presentation.status.tab.tanaman.DetailStatusTanamanActivity;
import com.selada.kebonmobile.util.Loading;
import com.selada.kebonmobile.util.MethodUtil;
import com.selada.kebonmobile.util.PreferenceManager;
import com.skydoves.elasticviews.ElasticLayout;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BottomSheetEventAdapter extends RecyclerView.Adapter<BottomSheetEventAdapter.ViewHolder> {
    private List<HarvestSchedule> transactionModels;
    private Context context;
    private Activity activity;

    public BottomSheetEventAdapter(List<HarvestSchedule> transactionModels, Context context, Activity activity) {
        this.transactionModels = transactionModels;
        this.context = context;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_event, parent, false);
        return new ViewHolder(v);
    }

    @SuppressLint({"SetTextI18n", "ResourceAsColor", "CheckResult"})
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HarvestSchedule harvestSchedule = transactionModels.get(position);
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US);
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Etc/UTC"));
            Date date = simpleDateFormat.parse(harvestSchedule.getStartDate());
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy HH:mm", new Locale("id", "ID"));
            holder.tv_date.setText(dateFormat.format(date));

            Glide.with(activity)
                    .load(harvestSchedule.getMainImage().getFullpath())
                    .placeholder(R.drawable.img_plant)
                    .into(holder.img_plant);
        } catch (Exception e){}

        holder.tv_plant_name.setText(harvestSchedule.getCommodityName());
        holder.tv_jumlah_panen.setText(harvestSchedule.getObjectCount() + " " + harvestSchedule.getObject_type_label());
        holder.tv_farm_name.setText(harvestSchedule.getSiteName());
        holder.item.setOnClickListener(view -> {
            getDetailTanaman(harvestSchedule.getCommodityId()+"", harvestSchedule.getSiteId());
        });
    }

    @Override
    public int getItemCount() {
        return transactionModels.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_plant_name, tv_jumlah_panen, tv_farm_name, tv_date;
        ImageView img_plant;
        ElasticLayout item;

        ViewHolder(View v) {
            super(v);
            tv_plant_name = v.findViewById(R.id.tv_plant_name);
            tv_jumlah_panen = v.findViewById(R.id.tv_jumlah_panen);
            tv_farm_name = v.findViewById(R.id.tv_farm_name);
            tv_date = v.findViewById(R.id.tv_date);
            img_plant = v.findViewById(R.id.img_plant);
            item = v.findViewById(R.id.item);
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
