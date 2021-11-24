package com.selada.kebonmobile.presentation.home.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.selada.kebonmobile.R;
import com.selada.kebonmobile.model.response.farmsummary.ObjectTypeSummary;
import com.skydoves.elasticviews.ElasticLayout;

import java.util.List;

public class SummarySiteAdapter extends RecyclerView.Adapter<SummarySiteAdapter.ViewHolder> {
    private List<ObjectTypeSummary> transactionModels;
    private Context context;
    private Activity activity;

    public SummarySiteAdapter(List<ObjectTypeSummary> transactionModels, Context context, Activity activity) {
        this.transactionModels = transactionModels;
        this.context = context;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_home_summary, parent, false);
        return new ViewHolder(v);
    }

    @SuppressLint({"SetTextI18n", "ResourceAsColor", "CheckResult"})
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ObjectTypeSummary summary = transactionModels.get(position);
        holder.tv_rincian_title.setText("Rincian " + summary.getLabel());
        holder.tv_total.setText("Total " + summary.getLabel() + ": " + summary.getTotal());
        holder.tv_total_idle.setText("Siap tanam: " + summary.getTotalIdle() + " " + summary.getLabel());
        holder.tv_total_pending.setText("Pending: " + summary.getTotalPending() + " " + summary.getLabel());
        holder.tv_active.setText("Sudah tertanam: " + summary.getTotal_active() + " " + summary.getLabel());
    }

    @Override
    public int getItemCount() {
        return transactionModels.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_rincian_title, tv_total, tv_total_pending, tv_total_idle, tv_active;
        ElasticLayout item;

        ViewHolder(View v) {
            super(v);
            tv_active = v.findViewById(R.id.tv_active);
            tv_rincian_title = v.findViewById(R.id.tv_rincian_title);
            tv_total = v.findViewById(R.id.tv_total);
            tv_total_pending = v.findViewById(R.id.tv_total_pending);
            tv_total_idle = v.findViewById(R.id.tv_total_idle);

            item = v.findViewById(R.id.item);
        }
    }
}
