package com.selada.kebonmobile.presentation.status.tab.adapter;

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
import com.selada.kebonmobile.model.response.commoditymonitoring.CommodityMonitoringResponse;
import com.selada.kebonmobile.model.response.detailcommodities.DetailCommoditiesResponse;
import com.selada.kebonmobile.model.response.mycommodities.Commodity;
import com.selada.kebonmobile.network.ApiCore;
import com.selada.kebonmobile.presentation.status.tab.tanaman.DetailStatusTanamanActivity;
import com.selada.kebonmobile.presentation.status.tab.tanaman.DetailTanamanActivity;
import com.selada.kebonmobile.util.Loading;
import com.selada.kebonmobile.util.MethodUtil;
import com.selada.kebonmobile.util.PreferenceManager;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StatusTanamanAdapter extends RecyclerView.Adapter<StatusTanamanAdapter.ViewHolder> {
    private List<Commodity> transactionModels;
    private Context context;
    private Activity activity;

    public StatusTanamanAdapter(List<Commodity> transactionModels, Context context, Activity activity) {
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
        Commodity commodity = transactionModels.get(position);
        String url = commodity.getMainImage()!=null ? commodity.getMainImage().getFullpath():"";
        int pos = position+1;
        holder.tv_farm_name.setText(commodity.getName());
        holder.tv_total_location.setText(commodity.getSiteCount() + " Lokasi");
        Glide.with(activity)
                .load(url)
                .placeholder(R.drawable.img_plant)
                .into(holder.img_plant);
        holder.tv_tanaman_no.setText("Tanaman #" + pos);
        holder.cvItem.setOnClickListener(view -> {
            getDetailTanaman(commodity.getRef_current_commodity_id()+"");
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

    private void getDetailTanaman(String id){
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
