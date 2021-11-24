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

import com.bumptech.glide.Glide;
import com.selada.kebonmobile.R;
import com.selada.kebonmobile.model.CommodityCart;
import com.skydoves.elasticviews.ElasticButton;
import com.skydoves.elasticviews.ElasticImageView;

import java.util.List;

public class ItemCompleteAdapter extends RecyclerView.Adapter<ItemCompleteAdapter.ViewHolder> {
    private List<CommodityCart> transactionModels;
    private Context context;
    private Activity activity;
    private boolean isCheckedAll = false;

    public  ItemCompleteAdapter(List<CommodityCart> transactionModels, Context context, Activity activity) {
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
        CommodityCart cart = transactionModels.get(position);
        holder.tv_plant_name.setText(cart.getName());
        holder.tv_jumlah.setText(String.valueOf(cart.getTotal_objects()));
        Glide.with(activity)
                .load(cart.getImage())
                .placeholder(R.drawable.img_plant)
                .into(holder.img_plant);
        holder.tv_site.setText(cart.getFarm_name());
        holder.tv_panen.setText("-");
        holder.tv_harga_jual.setText("-");
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
