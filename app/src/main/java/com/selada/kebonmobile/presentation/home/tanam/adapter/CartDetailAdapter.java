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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.selada.kebonmobile.R;
import com.selada.kebonmobile.model.CommodityCart;
import com.selada.kebonmobile.presentation.home.tanam.DetailCartActivity;
import com.selada.kebonmobile.presentation.home.tanam.PilihTanamanActivity;
import com.selada.kebonmobile.util.PreferenceManager;
import com.skydoves.elasticviews.ElasticButton;
import com.skydoves.elasticviews.ElasticImageView;

import java.util.List;

public class CartDetailAdapter extends RecyclerView.Adapter<CartDetailAdapter.ViewHolder> {
    private List<CommodityCart> transactionModels;
    private Context context;
    private Activity activity;
    private boolean isCheckedAll = false;

    public CartDetailAdapter(List<CommodityCart> transactionModels, Context context, Activity activity) {
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
        CommodityCart cart = transactionModels.get(position);
        int id = cart.getCommodity_id();
        int quantity = cart.getTotal_objects();

        holder.tv_plant_name.setText(cart.getName());
        holder.tv_price.setText("-");
        holder.tv_quantity.setText(String.valueOf(quantity));
        if (isCheckedAll){
            holder.cb_item.setChecked(true);
        }
        Glide.with(activity)
                .load(cart.getImage())
                .placeholder(R.drawable.img_plant)
                .into(holder.img_plant);
        holder.btn_add.setOnClickListener(view -> {
            updateCart(plusButton(quantity, holder), id, position);
        });
        holder.btn_min.setOnClickListener(view -> {
            updateCart(minButton(quantity, holder), id, position);
        });
        holder.btn_delete.setOnClickListener(view -> {
            deleteItemCart(id, position);
        });
    }

    private int minButton(int qty, ViewHolder holder) {
        int minimumQty = ((DetailCartActivity)context).getMinimumQty();
        qty = qty - minimumQty;
        if (qty == 0){
            holder.tv_quantity.setText("0");
        } else {
            holder.tv_quantity.setText(String.valueOf(qty));
            ((DetailCartActivity)context).updateAvailableSlot(false, minimumQty);
        }

        return qty;
    }

    private int plusButton(int qty, ViewHolder holder){
        int minimumQty = ((DetailCartActivity)context).getMinimumQty();
        int finalQty = qty + minimumQty;
        if (minimumQty <= ((DetailCartActivity)context).availableSlot()) {
            holder.btn_min.setEnabled(true);
            holder.tv_quantity.setText(String.valueOf(qty));
            ((DetailCartActivity)context).updateAvailableSlot(true, minimumQty);
        } else {
            Toast.makeText(context, "Slot yang tersisa tidak mencukupi", Toast.LENGTH_SHORT).show();
            return qty;
        }

        return finalQty;
    }

    private void updateCart(int qty, int id, int posHolder){
        if (qty == 0){
            deleteItemCart(id, posHolder);
            return;
        }

        List<CommodityCart> commodityCartList = PreferenceManager.getCommodityCart();
        for (CommodityCart cart: commodityCartList){
            if (cart.getCommodity_id() == id){
                cart.setTotal_objects(qty);
            }
        }

        PreferenceManager.setCommodityCart(commodityCartList);
        updateItem(commodityCartList);
    }

    private void deleteItemCart(int id, int posHolder){
        int totalObjects = 0;
        List<CommodityCart> commodityCartList = PreferenceManager.getCommodityCart();
        for (int i = 0; i<commodityCartList.size(); i++){
            CommodityCart cart = commodityCartList.get(i);
            if (cart.getCommodity_id() == id){
                totalObjects = cart.getTotal_objects();
            }
        }

        transactionModels.remove(posHolder);
        PreferenceManager.setCommodityCart(transactionModels);
        ((DetailCartActivity)context).updateAvailableSlot(false, totalObjects);
        if (transactionModels.size()>0){
            ((DetailCartActivity)context).setEmptyCart(false);
        } else {
            ((DetailCartActivity)context).setEmptyCart(true);
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return transactionModels.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_plant_name, tv_price, tv_quantity, tv_jumlah_tanaman;
        ElasticImageView btn_delete;
        ElasticButton btn_min, btn_add;
        ImageView img_plant;
        CheckBox cb_item;

        ViewHolder(View v) {
            super(v);
            tv_plant_name = v.findViewById(R.id.textView3);
            tv_price = v.findViewById(R.id.textView4);
            tv_quantity = v.findViewById(R.id.tv_quantity);
            tv_jumlah_tanaman = v.findViewById(R.id.tv_jumlah_tanaman);
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

    public void updateItem(List<CommodityCart> commodityCartList){
        if (commodityCartList.size()>0){
            ((DetailCartActivity)context).setEmptyCart(false);
        } else {
            ((DetailCartActivity)context).setEmptyCart(true);
        }
        transactionModels = commodityCartList;
        notifyDataSetChanged();
    }
}
