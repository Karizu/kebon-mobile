package com.selada.kebonmobile.presentation.status.tab.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextSwitcher;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.selada.kebonmobile.R;
import com.selada.kebonmobile.model.response.commoditymonitoring.ConnectedComponent;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class SensorMonitoringAdapter extends RecyclerView.Adapter<SensorMonitoringAdapter.ViewHolder> {
    private List<ConnectedComponent> transactionModels;
    private Context context;
    private Activity activity;

    public SensorMonitoringAdapter(List<ConnectedComponent> transactionModels, Context context, Activity activity) {
        this.transactionModels = transactionModels;
        this.context = context;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_monitoring, parent, false);
        return new ViewHolder(v);
    }

    @SuppressLint({"SetTextI18n", "ResourceAsColor", "CheckResult"})
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ConnectedComponent component = transactionModels.get(position);
        holder.tv_value.setInAnimation(activity, android.R.anim.slide_in_left);
        holder.tv_value.setOutAnimation(activity, android.R.anim.slide_out_right);
        holder.label.setText(component.getLabel());
        holder.tv_value.setText(component.getLatestData().getValue());
    }

    @Override
    public int getItemCount() {
        return transactionModels.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextSwitcher tv_value;
        TextView label;

        ViewHolder(View v) {
            super(v);
            label = v.findViewById(R.id.label);
            tv_value = v.findViewById(R.id.tv_value);
        }
    }
}
