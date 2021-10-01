package com.selada.kebonmobile.presentation.home.tanam;

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

public class CartDetailAdapter extends RecyclerView.Adapter<CartDetailAdapter.ViewHolder> {
    private List<String> transactionModels;
    private Context context;
    private Activity activity;
    private boolean isCheckedAll = false;

    public CartDetailAdapter(List<String> transactionModels, Context context, Activity activity) {
        this.transactionModels = transactionModels;
        this.context = context;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_detail_cart, parent, false);
        return new ViewHolder(v);
    }

    @SuppressLint({"SetTextI18n", "ResourceAsColor", "CheckResult"})
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tv_plant_name.setText(transactionModels.get(position));
        if (isCheckedAll){
            holder.cb_item.setChecked(true);
        }
        holder.btn_add.setOnClickListener(view -> {

        });
        holder.btn_min.setOnClickListener(view -> {

        });
        holder.btn_delete.setOnClickListener(view -> {

        });
    }

    @Override
    public int getItemCount() {
        return transactionModels.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_plant_name, tv_price, tv_quantity;
        ElasticImageView btn_delete;
        ElasticButton btn_min, btn_add;
        ImageView img_plant;
        CheckBox cb_item;

        ViewHolder(View v) {
            super(v);
            tv_plant_name = v.findViewById(R.id.textView3);
            tv_price = v.findViewById(R.id.textView4);
            tv_quantity = v.findViewById(R.id.tv_quantity);
            btn_delete = v.findViewById(R.id.btn_delete);
            img_plant = v.findViewById(R.id.img_plant);
            btn_add = v.findViewById(R.id.btn_add);
            btn_min = v.findViewById(R.id.btn_min);
            cb_item = v.findViewById(R.id.cb_item);
        }
    }

    public void setCheckedItem(){
        isCheckedAll = true;
        notifyDataSetChanged();
    }
}
