package com.selada.kebonmobile.presentation.home.tanam.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.selada.kebonmobile.R;
import com.selada.kebonmobile.model.response.commodity.AvailableCommodity;
import com.selada.kebonmobile.presentation.home.tanam.PilihTanamanActivity;

import java.util.List;

public class PilihTanamanAdapter extends RecyclerView.Adapter<PilihTanamanAdapter.ViewHolder> {
    private List<AvailableCommodity> transactionModels;
    private Context context;
    private Activity activity;
    private int pos = -1;

    public PilihTanamanAdapter(List<AvailableCommodity> transactionModels, Context context, Activity activity) {
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
        AvailableCommodity availableCommodity = transactionModels.get(position);

        holder.tv_plant_name.setText(availableCommodity.getName());
        Glide.with(activity)
                .load(availableCommodity.getMainImage().getFullpath())
                .placeholder(R.drawable.img_plant)
                .into(holder.img_plant);
        holder.frame_item.setOnClickListener(view -> {
            if (context instanceof PilihTanamanActivity) {
                ((PilihTanamanActivity)context).setDataTanaman(availableCommodity);
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
