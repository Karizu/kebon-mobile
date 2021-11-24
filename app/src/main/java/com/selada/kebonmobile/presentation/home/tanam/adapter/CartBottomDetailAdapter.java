package com.selada.kebonmobile.presentation.home.tanam.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.selada.kebonmobile.R;
import com.selada.kebonmobile.model.response.commodity.AvailableCommodity;
import com.selada.kebonmobile.presentation.home.tanam.DetailCartActivity;
import com.selada.kebonmobile.presentation.home.tanam.PilihTanamanActivity;
import com.skydoves.elasticviews.ElasticButton;

import java.util.List;

public class CartBottomDetailAdapter extends RecyclerView.Adapter<CartBottomDetailAdapter.ViewHolder> {
    private List<AvailableCommodity> transactionModels;
    private Context context;
    private Activity activity;

    public CartBottomDetailAdapter(List<AvailableCommodity> transactionModels, Context context, Activity activity) {
        this.transactionModels = transactionModels;
        this.context = context;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_bottom_detail_cart, parent, false);
        return new ViewHolder(v);
    }

    @SuppressLint({"SetTextI18n", "ResourceAsColor", "CheckResult"})
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AvailableCommodity commodity = transactionModels.get(position);
        holder.tv_plant_name.setText(commodity.getName());
        Glide.with(activity)
                .load(commodity.getMainImage().getFullpath())
                .placeholder(R.drawable.img_plant)
                .into(holder.img_plant);
        holder.btn_tambah_keranjang.setOnClickListener(view -> {
            if (context instanceof DetailCartActivity) {
                ((DetailCartActivity)context).addToCart(commodity, position);
            }
        });
    }

    public void updateItem(int position){
        transactionModels.remove(position); //Remove the current content from the array
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return transactionModels.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_plant_name, tv_harga_jual, tv_panen;
        ElasticButton btn_tambah_keranjang;
        ImageView img_plant;

        ViewHolder(View v) {
            super(v);
            tv_plant_name = v.findViewById(R.id.tv_plant_name);
            tv_panen = v.findViewById(R.id.tv_panen);
            tv_harga_jual = v.findViewById(R.id.tv_harga_jual);
            img_plant = v.findViewById(R.id.img_plant);
            btn_tambah_keranjang = v.findViewById(R.id.btn_tambah_keranjang);
        }
    }
}
