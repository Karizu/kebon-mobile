package com.selada.kebonmobile.presentation.home.tanam.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.selada.kebonmobile.R;
import com.skydoves.elasticviews.ElasticButton;
import com.skydoves.elasticviews.ElasticImageView;

import java.util.List;

public class ItemCompleteAdapter extends RecyclerView.Adapter<ItemCompleteAdapter.ViewHolder> {
    private List<String> transactionModels;
    private Context context;
    private Activity activity;
    private boolean isCheckedAll = false;

    public ItemCompleteAdapter(List<String> transactionModels, Context context, Activity activity) {
        this.transactionModels = transactionModels;
        this.context = context;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_selesai_menanam, parent, false);
        return new ViewHolder(v);
    }

    @SuppressLint({"SetTextI18n", "ResourceAsColor", "CheckResult"})
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tv_plant_name.setText(transactionModels.get(position));
    }

    @Override
    public int getItemCount() {
        return transactionModels.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_plant_name,  tv_site, tv_jumlah, tv_panen, tv_harga_jual;
        ImageView img_plant;

        ViewHolder(View v) {
            super(v);
            tv_plant_name = v.findViewById(R.id.tv_plant_name);
            tv_site = v.findViewById(R.id.tv_site);
            tv_jumlah = v.findViewById(R.id.tv_jumlah);
            tv_panen = v.findViewById(R.id.tv_panen);
            tv_harga_jual = v.findViewById(R.id.tv_harga_jual);
            img_plant = v.findViewById(R.id.img_plant);
        }
    }

    public void setCheckedItem(){
        isCheckedAll = true;
        notifyDataSetChanged();
    }
}
