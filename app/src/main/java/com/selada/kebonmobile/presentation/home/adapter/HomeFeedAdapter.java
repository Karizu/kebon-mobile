package com.selada.kebonmobile.presentation.home.adapter;

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

import com.bumptech.glide.Glide;
import com.selada.kebonmobile.R;
import com.selada.kebonmobile.model.response.SiteResponse;
import com.selada.kebonmobile.presentation.home.lahan.SewaLahanActivity;
import com.skydoves.elasticviews.ElasticCardView;

import java.util.List;

public class HomeFeedAdapter extends RecyclerView.Adapter<HomeFeedAdapter.ViewHolder> {
    private List<SiteResponse> transactionModels;
    private Context context;
    private Activity activity;

    public HomeFeedAdapter(List<SiteResponse> transactionModels, Context context, Activity activity) {
        this.transactionModels = transactionModels;
        this.context = context;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_home_list_1, parent, false);

        return new ViewHolder(v);
    }

    @SuppressLint({"SetTextI18n", "ResourceAsColor", "CheckResult"})
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SiteResponse siteResponse = transactionModels.get(position);

        holder.text_item.setText(siteResponse.getName());
        Glide.with(activity)
                .load(siteResponse.getLogo().getFullpath())
                .placeholder(R.drawable.img_plant)
                .into(holder.img_item);
        holder.cvItem.setOnClickListener(view -> {
            Intent intent = new Intent(activity, SewaLahanActivity.class);
            intent.putExtra("is_from_adapter", true);
            intent.putExtra("site_id", siteResponse.getId());
            activity.startActivity(intent);
            activity.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        });
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
