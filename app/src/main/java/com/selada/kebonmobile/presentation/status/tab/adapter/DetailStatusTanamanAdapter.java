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
import com.selada.kebonmobile.R;
import com.selada.kebonmobile.model.response.commoditymonitoring.CurrentSite;
import com.selada.kebonmobile.model.response.detailcommodities.ActiveSite;
import com.selada.kebonmobile.presentation.home.tanam.PilihTanamanActivity;
import com.selada.kebonmobile.presentation.status.tab.lahan.DetailStatusLahanActivity;
import com.selada.kebonmobile.presentation.status.tab.tanaman.DetailStatusTanamanActivity;

import java.util.List;

public class DetailStatusTanamanAdapter extends RecyclerView.Adapter<DetailStatusTanamanAdapter.ViewHolder> {
    private List<CurrentSite> transactionModels;
    private Context context;
    private Activity activity;
    private int pos = -1;

    public DetailStatusTanamanAdapter(List<CurrentSite> transactionModels, Context context, Activity activity) {
        this.transactionModels = transactionModels;
        this.context = context;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_detail_status_tanaman, parent, false);
        return new ViewHolder(v);
    }

    @SuppressLint({"SetTextI18n", "ResourceAsColor", "CheckResult"})
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CurrentSite currentSite = transactionModels.get(position);
        holder.tv_farm_name.setText(currentSite.getName());
        holder.tv_alamat.setText(currentSite.getAddressStreet() + ", " + currentSite.getAddressSubdistrict() + ", " + currentSite.getAddressCity());
        holder.cvItem.setOnClickListener(view -> {
            if (context instanceof DetailStatusTanamanActivity) {
                ((DetailStatusTanamanActivity)context).setSiteId(currentSite.getSiteId());
            }
            pos = position;
            notifyDataSetChanged();
        });
        Glide.with(activity)
                .load(currentSite.getLogo().getFullpath())
                .placeholder(R.drawable.img_plant)
                .into(holder.img_plant);

        if(pos==position){
            holder.cvItem.setBackground(activity.getResources().getDrawable(R.drawable.bg_outer_pilih_tanaman));
        } else {
            holder.cvItem.setBackground(activity.getResources().getDrawable(R.drawable.bg_round_detail_pesanan));
        }
    }

    @Override
    public int getItemCount() {
        return transactionModels.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_farm_name, tv_alamat;
        ConstraintLayout cvItem;
        ImageView img_plant;

        ViewHolder(View v) {
            super(v);
            tv_farm_name = v.findViewById(R.id.tv_farm_name);
            tv_alamat = v.findViewById(R.id.tv_alamat);
            img_plant = v.findViewById(R.id.img_plant);
            cvItem = v.findViewById(R.id.constraintLayout6);
        }
    }
}
