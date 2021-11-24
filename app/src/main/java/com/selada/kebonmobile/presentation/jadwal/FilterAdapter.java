package com.selada.kebonmobile.presentation.jadwal;

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
import com.selada.kebonmobile.model.response.filtercalendar.Site;
import com.selada.kebonmobile.presentation.home.tanam.PilihTanamanActivity;
import com.skydoves.elasticviews.ElasticImageView;
import com.skydoves.elasticviews.ElasticLayout;

import java.util.List;

public class FilterAdapter extends RecyclerView.Adapter<FilterAdapter.ViewHolder> {
    private List<Site> transactionModels;
    private Context context;
    private Activity activity;
    private int pos = -1;

    public FilterAdapter(List<Site> transactionModels, Context context, Activity activity) {
        this.transactionModels = transactionModels;
        this.context = context;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_filter_event, parent, false);
        return new ViewHolder(v);
    }

    @SuppressLint({"SetTextI18n", "ResourceAsColor", "CheckResult"})
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Site site = transactionModels.get(position);
        holder.tv_farm_name.setText(site.getSiteName());
        holder.item.setOnClickListener(view -> {
            if (context instanceof JadwalActivity) {
                ((JadwalActivity)context).setSiteId(site.getSiteId());
            }
            pos = position;
            notifyDataSetChanged();
        });

        if(pos==position){
            holder.item.setBackground(activity.getResources().getDrawable(R.drawable.bg_item_filter_event_selected));
        } else {
            holder.item.setBackground(activity.getResources().getDrawable(R.drawable.bg_item_filter_event));
        }
    }

    @Override
    public int getItemCount() {
        return transactionModels.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_farm_name;
        ElasticLayout item;

        ViewHolder(View v) {
            super(v);
            tv_farm_name = v.findViewById(R.id.tv_farm_name);
            item = v.findViewById(R.id.item);
        }
    }
}
