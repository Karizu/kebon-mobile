package com.selada.kebonmobile.presentation.home.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
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

import com.selada.kebonmobile.R;
import com.selada.kebonmobile.presentation.home.pembayaran.RincianPembayaranActivity;
import com.selada.kebonmobile.presentation.home.tanam.PilihMetodeActivity;
import com.selada.kebonmobile.util.Constant;
import com.selada.kebonmobile.util.PreferenceManager;
import com.skydoves.elasticviews.ElasticButton;
import com.skydoves.elasticviews.ElasticImageView;

import java.util.List;

public class HomeLahanAdapter extends RecyclerView.Adapter<HomeLahanAdapter.ViewHolder> {
    private List<String> transactionModels;
    private Context context;
    private Activity activity;

    public HomeLahanAdapter(List<String> transactionModels, Context context, Activity activity) {
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

        String siteName = transactionModels.get(position);
        String plantStatus = "Dalam Pembibitan";
        String slot = "200";
        String slotKosong = "100";

        switch (PreferenceManager.getUserStatus()){
            case Constant.ON_HOLD:
                holder.tv_farm_name.setText(siteName);
                holder.layout_already_plant_site.setVisibility(View.GONE);
                holder.layout_hold_and_plant.setVisibility(View.VISIBLE);
                holder.btn_rincian.setOnClickListener(view -> {
                    Intent intent = new Intent(activity, RincianPembayaranActivity.class);
                    activity.startActivity(intent);
                    activity.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                });
                break;
            case Constant.READY_PLANT:
                holder.tv_farm_name.setText(siteName);
                holder.layout_already_plant_site.setVisibility(View.GONE);
                holder.layout_hold_and_plant.setVisibility(View.VISIBLE);
                holder.btn_rincian.setText("Mulai Tanam");
                holder.tv_status_farm.setText("Siap Ditanam");
                holder.btn_rincian.setOnClickListener(view -> {
                    Intent intent = new Intent(activity, PilihMetodeActivity.class);
                    activity.startActivity(intent);
                    activity.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                });
                break;
            case Constant.ALREADY_PLANT_SITE:
                holder.tv_farm_name.setText(siteName);
                holder.layout_already_plant_site.setVisibility(View.VISIBLE);
                holder.layout_hold_and_plant.setVisibility(View.GONE);
                holder.tv_item_info.setText("Status : "+ plantStatus + "\nJumlah Slot : "+ slot + "\nJumlah Slot Kosong : " + slotKosong);
                break;
        }

        holder.btn_play.setOnClickListener(view -> {

        });
    }

    @Override
    public int getItemCount() {
        return transactionModels.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img_farm;
        TextView tv_farm_name, tv_status_farm, tv_item_info;
        ElasticButton btn_rincian;
        LinearLayout layout_already_plant_site, layout_hold_and_plant;
        ElasticImageView btn_play;

        ViewHolder(View v) {
            super(v);
            tv_farm_name = v.findViewById(R.id.tv_farm_name);
            tv_status_farm = v.findViewById(R.id.tv_status_farm);
            tv_item_info = v.findViewById(R.id.tv_item_info);
            img_farm = v.findViewById(R.id.img_farm);
            btn_rincian = v.findViewById(R.id.btn_rincian);
            layout_already_plant_site = v.findViewById(R.id.layout_already_plant_site);
            layout_hold_and_plant = v.findViewById(R.id.layout_ready_plant);
            btn_play = v.findViewById(R.id.btn_play);
        }
    }
}
