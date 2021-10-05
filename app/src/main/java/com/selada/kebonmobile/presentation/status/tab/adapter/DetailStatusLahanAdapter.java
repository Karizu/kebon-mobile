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

import com.selada.kebonmobile.R;
import com.selada.kebonmobile.presentation.status.tab.tanaman.DetailTanamanActivity;
import com.skydoves.elasticviews.ElasticButton;

import java.util.List;

public class DetailStatusLahanAdapter extends RecyclerView.Adapter<DetailStatusLahanAdapter.ViewHolder> {
    private List<String> transactionModels;
    private Context context;
    private Activity activity;

    public DetailStatusLahanAdapter(List<String> transactionModels, Context context, Activity activity) {
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
        holder.tv_plant_name.setText(transactionModels.get(position));
        holder.cvItem.setOnClickListener(view -> {
            Intent intent = new Intent(activity, DetailTanamanActivity.class);
            intent.putExtra("plant_name", transactionModels.get(position));
            activity.startActivity(intent);
            activity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        });
    }

    @Override
    public int getItemCount() {
        return transactionModels.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_plant_name, tv_site, tv_harga_jual, tv_panen;
        ConstraintLayout cvItem;
        ImageView img_plant;

        ViewHolder(View v) {
            super(v);
            tv_plant_name = v.findViewById(R.id.tv_plant_name);
            tv_site = v.findViewById(R.id.tv_site);
            tv_panen = v.findViewById(R.id.tv_panen);
            tv_harga_jual = v.findViewById(R.id.tv_harga_jual);
            img_plant = v.findViewById(R.id.img_plant);
            cvItem = v.findViewById(R.id.constraintLayout6);
        }
    }
}
