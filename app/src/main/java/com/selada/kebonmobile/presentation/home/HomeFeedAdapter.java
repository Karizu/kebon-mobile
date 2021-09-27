package com.selada.kebonmobile.presentation.home;

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
import androidx.recyclerview.widget.RecyclerView;
import com.selada.kebonmobile.R;
import com.selada.kebonmobile.presentation.home.lahan.SewaLahanActivity;
import com.skydoves.elasticviews.ElasticCardView;

import java.util.List;

public class HomeFeedAdapter extends RecyclerView.Adapter<HomeFeedAdapter.ViewHolder> {
    private List<String> transactionModels;
    private Context context;
    private Activity activity;
    private int feedType;

    public HomeFeedAdapter(List<String> transactionModels, Context context, Activity activity, int feedType) {
        this.transactionModels = transactionModels;
        this.context = context;
        this.activity = activity;
        this.feedType = feedType;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        if (feedType == 1){
            v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_home_list_1, parent, false);
        } else {
            v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_home_list_2, parent, false);
        }
        return new ViewHolder(v);
    }

    @SuppressLint({"SetTextI18n", "ResourceAsColor", "CheckResult"})
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (feedType == 1){
            holder.cvItem.setOnClickListener(view -> {
                Intent intent = new Intent(activity, SewaLahanActivity.class);
                activity.startActivity(intent);
                activity.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            });
        } else {
            holder.cvItem.setOnClickListener(view -> {
                Intent intent = new Intent(activity, SewaLahanActivity.class);
                activity.startActivity(intent);
                activity.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            });
        }
    }

    @Override
    public int getItemCount() {
        return transactionModels.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img_item;
        TextView text_item;
        ElasticCardView cvItem;

        ViewHolder(View v) {
            super(v);
            img_item = v.findViewById(R.id.img_item);
            text_item = v.findViewById(R.id.text_item);
            cvItem = v.findViewById(R.id.cvItem);
        }
    }
}
