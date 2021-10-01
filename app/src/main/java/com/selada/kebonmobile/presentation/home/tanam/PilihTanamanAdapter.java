package com.selada.kebonmobile.presentation.home.tanam;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.selada.kebonmobile.R;
import com.selada.kebonmobile.presentation.home.pembayaran.RincianPembayaranActivity;
import com.skydoves.elasticviews.ElasticButton;

import java.util.List;

public class PilihTanamanAdapter extends RecyclerView.Adapter<PilihTanamanAdapter.ViewHolder> {
    private List<String> transactionModels;
    private Context context;
    private Activity activity;
    private int pos = -1;

    public PilihTanamanAdapter(List<String> transactionModels, Context context, Activity activity) {
        this.transactionModels = transactionModels;
        this.context = context;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_pilih_tanaman, parent, false);
        return new ViewHolder(v);
    }

    @SuppressLint({"SetTextI18n", "ResourceAsColor", "CheckResult"})
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String plantName = transactionModels.get(position);
        int dayHarvest = 20 + position;
        int price = 5000 + (position * 2);
        String img = "";

        holder.tv_plant_name.setText(transactionModels.get(position));
        holder.frame_item.setOnClickListener(view -> {
            if (context instanceof PilihTanamanActivity) {
                ((PilihTanamanActivity)context).setDataTanaman(plantName, String.valueOf(dayHarvest), String.valueOf(price), img);
                ((PilihTanamanActivity)context).setEnableComponent();
            }
            pos = position;
            notifyDataSetChanged();
        });

        if(pos==position){
            holder.frame_item.setBackground(activity.getResources().getDrawable(R.drawable.bg_outer_pilih_tanaman));
        } else {
            holder.frame_item.setBackground(activity.getResources().getDrawable(R.drawable.bg_solid_pilih_tanaman));
        }
    }

    @Override
    public int getItemCount() {
        return transactionModels.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img_plant;
        TextView tv_plant_name;
        FrameLayout frame_item;

        ViewHolder(View v) {
            super(v);
            tv_plant_name = v.findViewById(R.id.tv_plant_name);
            img_plant = v.findViewById(R.id.img_plant);
            frame_item = v.findViewById(R.id.frame_item);
        }
    }
}
