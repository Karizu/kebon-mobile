package com.selada.kebonmobile.presentation.status.tab.adapter;

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
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.selada.kebonmobile.R;
import com.selada.kebonmobile.model.response.errormsg.ErrorResponse;
import com.selada.kebonmobile.model.response.farmsummary.Farm;
import com.selada.kebonmobile.model.response.farmsummary.FarmSummaryResponse;
import com.selada.kebonmobile.model.response.farmsummary.SiteComponent;
import com.selada.kebonmobile.network.ApiCore;
import com.selada.kebonmobile.presentation.home.lahan.SewaLahanActivity;
import com.selada.kebonmobile.presentation.status.tab.lahan.DetailStatusLahanActivity;
import com.selada.kebonmobile.util.Loading;
import com.selada.kebonmobile.util.MethodUtil;
import com.selada.kebonmobile.util.PreferenceManager;
import com.skydoves.elasticviews.ElasticCardView;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StatusLahanAdapter extends RecyclerView.Adapter<StatusLahanAdapter.ViewHolder> {
    private List<Farm> transactionModels;
    private Context context;
    private Activity activity;

    public StatusLahanAdapter(List<Farm> transactionModels, Context context, Activity activity) {
        this.transactionModels = transactionModels;
        this.context = context;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_status_lahan, parent, false);

        return new ViewHolder(v);
    }

    @SuppressLint({"SetTextI18n", "ResourceAsColor", "CheckResult"})
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Farm farm = transactionModels.get(position);
        int pos =  position+1;
        String totalPlant = farm.getActiveCommodityCount()!=null?farm.getActiveCommodityCount()+"":"0";
        Glide.with(activity)
                .load(farm.getLogo().getFullpath())
                .placeholder(R.drawable.img_plant)
                .into(holder.img_plant);
        holder.tv_farm_name.setText(farm.getName());
        holder.tv_total_plant.setText("Total tanaman: " + totalPlant);
        holder.tv_Lahan_no.setText("Lahan #" +pos);
        holder.cvItem.setOnClickListener(view -> {
            getMonitoringMyFarm(farm.getSite_id());
        });
    }

    @Override
    public int getItemCount() {
        return transactionModels.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img_plant;
        TextView tv_farm_name, tv_Lahan_no, tv_total_plant;
        ConstraintLayout cvItem;

        ViewHolder(View v) {
            super(v);
            img_plant = v.findViewById(R.id.img_plant);
            tv_farm_name = v.findViewById(R.id.tv_farm_name);
            tv_Lahan_no = v.findViewById(R.id.tv_Lahan_no);
            tv_total_plant = v.findViewById(R.id.tv_total_plant);
            cvItem = v.findViewById(R.id.constraintLayout6);
        }
    }

    private void getMonitoringMyFarm(int site_id){
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
                        ErrorResponse message = gson.fromJson(Objects.requireNonNull(response.errorBody()).charStream(), ErrorResponse.class);
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
}
