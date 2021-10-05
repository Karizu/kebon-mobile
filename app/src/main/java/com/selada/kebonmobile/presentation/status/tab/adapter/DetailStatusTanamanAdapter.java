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
import com.selada.kebonmobile.presentation.status.tab.lahan.DetailStatusLahanActivity;

import java.util.List;

public class DetailStatusTanamanAdapter extends RecyclerView.Adapter<DetailStatusTanamanAdapter.ViewHolder> {
    private List<String> transactionModels;
    private Context context;
    private Activity activity;

    public DetailStatusTanamanAdapter(List<String> transactionModels, Context context, Activity activity) {
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
        holder.tv_farm_name.setText(transactionModels.get(position));
        holder.cvItem.setOnClickListener(view -> {
            Intent intent = new Intent(activity, DetailStatusLahanActivity.class);
            intent.putExtra("tv_farm_name", transactionModels.get(position));
            activity.startActivity(intent);
            activity.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        });
    }

    @Override
    public int getItemCount() {
        return transactionModels.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_farm_name, tv_jumlah_lubang, tv_jumlah_lubang_kosong, tv_tanaman;
        ConstraintLayout cvItem;
        ImageView img_plant;

        ViewHolder(View v) {
            super(v);
            tv_farm_name = v.findViewById(R.id.tv_farm_name);
            tv_jumlah_lubang = v.findViewById(R.id.tv_jumlah_lubang);
            tv_tanaman = v.findViewById(R.id.tv_tanaman);
            tv_jumlah_lubang_kosong = v.findViewById(R.id.tv_jumlah_lubang_kosong);
            img_plant = v.findViewById(R.id.img_plant);
            cvItem = v.findViewById(R.id.constraintLayout6);
        }
    }
}
