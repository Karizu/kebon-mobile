package com.selada.kebonmobile.presentation.status.history.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.selada.kebonmobile.R;
import com.selada.kebonmobile.presentation.status.history.DetailHistory;
import com.selada.kebonmobile.presentation.status.tab.lahan.DetailStatusLahanActivity;
import com.skydoves.elasticviews.ElasticLayout;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {
    private List<String> transactionModels;
    private Context context;
    private Activity activity;

    public HistoryAdapter(List<String> transactionModels, Context context, Activity activity) {
        this.transactionModels = transactionModels;
        this.context = context;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_history, parent, false);

        return new ViewHolder(v);
    }

    @SuppressLint({"SetTextI18n", "ResourceAsColor", "CheckResult"})
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tv_title.setText(transactionModels.get(position));
        holder.cvItem.setOnClickListener(view -> {
            Intent intent = new Intent(activity, DetailHistory.class);
            intent.putExtra("title", transactionModels.get(position));
            activity.startActivity(intent);
            activity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        });
    }

    @Override
    public int getItemCount() {
        return transactionModels.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_title, tv_message, tv_date;
        ElasticLayout cvItem;

        ViewHolder(View v) {
            super(v);
            tv_title = v.findViewById(R.id.tv_title);
            tv_message = v.findViewById(R.id.tv_message);
            tv_date = v.findViewById(R.id.tv_date);
            cvItem = v.findViewById(R.id.cvItem);
        }
    }
}
