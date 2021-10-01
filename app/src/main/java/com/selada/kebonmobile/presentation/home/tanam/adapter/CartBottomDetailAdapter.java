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

import com.selada.kebonmobile.R;
import com.skydoves.elasticviews.ElasticButton;

import java.util.List;

public class CartBottomDetailAdapter extends RecyclerView.Adapter<CartBottomDetailAdapter.ViewHolder> {
    private List<String> transactionModels;
    private Context context;
    private Activity activity;

    public CartBottomDetailAdapter(List<String> transactionModels, Context context, Activity activity) {
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
        holder.tv_plant_name.setText(transactionModels.get(position));
        holder.btn_tambah_keranjang.setOnClickListener(view -> {

        });
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
