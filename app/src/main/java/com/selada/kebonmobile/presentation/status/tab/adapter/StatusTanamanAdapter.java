package com.selada.kebonmobile.presentation.status.tab.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.selada.kebonmobile.R;

import java.util.List;

public class StatusTanamanAdapter extends RecyclerView.Adapter<StatusTanamanAdapter.ViewHolder> {
    private List<String> transactionModels;
    private Context context;
    private Activity activity;

    public StatusTanamanAdapter(List<String> transactionModels, Context context, Activity activity) {
        this.transactionModels = transactionModels;
        this.context = context;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_status_tanaman, parent, false);

        return new ViewHolder(v);
    }

    @SuppressLint({"SetTextI18n", "ResourceAsColor", "CheckResult"})
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tv_farm_name.setText(transactionModels.get(position));
        holder.cvItem.setOnClickListener(view -> {
//            Intent intent = new Intent(activity, SewaLahanActivity.class);
//            activity.startActivity(intent);
//            activity.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        });
    }

    @Override
    public int getItemCount() {
        return transactionModels.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img_plant;
        TextView tv_farm_name, tv_tanaman_no, tv_total_location;
        ConstraintLayout cvItem;

        ViewHolder(View v) {
            super(v);
            img_plant = v.findViewById(R.id.img_plant);
            tv_farm_name = v.findViewById(R.id.tv_farm_name);
            tv_tanaman_no = v.findViewById(R.id.tv_tanaman_no);
            tv_total_location = v.findViewById(R.id.tv_total_location);
            cvItem = v.findViewById(R.id.constraintLayout6);
        }
    }
}
