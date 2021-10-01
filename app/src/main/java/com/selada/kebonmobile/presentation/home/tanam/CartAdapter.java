package com.selada.kebonmobile.presentation.home.tanam;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.selada.kebonmobile.R;
import com.selada.kebonmobile.presentation.home.lahan.SewaLahanActivity;
import com.selada.kebonmobile.presentation.home.pembayaran.RincianPembayaranActivity;
import com.selada.kebonmobile.presentation.home.tanam.PilihMetodeActivity;
import com.skydoves.elasticviews.ElasticButton;
import com.skydoves.elasticviews.ElasticCardView;
import com.skydoves.elasticviews.ElasticImageView;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    private List<String> transactionModels;
    private Context context;
    private Activity activity;

    public CartAdapter(List<String> transactionModels, Context context, Activity activity) {
        this.transactionModels = transactionModels;
        this.context = context;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_cart, parent, false);
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
        TextView tv_plant_name, tv_quantity;
        ElasticImageView btn_min, btn_add;

        ViewHolder(View v) {
            super(v);
            tv_plant_name = v.findViewById(R.id.tv_plant_name);
            tv_quantity = v.findViewById(R.id.tv_quantity);
            btn_add = v.findViewById(R.id.btn_add);
            btn_min = v.findViewById(R.id.btn_min);
        }
    }
}
