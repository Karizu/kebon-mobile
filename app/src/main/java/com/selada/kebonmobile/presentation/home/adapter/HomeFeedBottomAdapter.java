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
import com.selada.kebonmobile.model.response.FeedBottomHome;
import com.selada.kebonmobile.presentation.home.content.ContentActivity;
import com.selada.kebonmobile.presentation.home.lahan.SewaLahanActivity;
import com.skydoves.elasticviews.ElasticCardView;

import java.util.List;

public class HomeFeedBottomAdapter extends RecyclerView.Adapter<HomeFeedBottomAdapter.ViewHolder> {
    private List<FeedBottomHome> feedBottomHomes;
    private Context context;
    private Activity activity;

    public HomeFeedBottomAdapter(Context context, Activity activity, List<FeedBottomHome> feedBottomHomes) {
        this.context = context;
        this.activity = activity;
        this.feedBottomHomes = feedBottomHomes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_home_list_2, parent, false);
        return new ViewHolder(v);
    }

    @SuppressLint({"SetTextI18n", "ResourceAsColor", "CheckResult"})
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FeedBottomHome feedBottomHome = feedBottomHomes.get(position);
        holder.cvItem.setOnClickListener(view -> {
            Intent intent = new Intent(activity, ContentActivity.class);
            intent.putExtra("url", feedBottomHome.getLink());
            activity.startActivity(intent);
            activity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        });
        holder.text_item.setText(feedBottomHome.getTitle());
        Glide.with(activity)
                .load(feedBottomHome.getDra())
                .into(holder.img_item);
    }

    @Override
    public int getItemCount() {
        return feedBottomHomes.size();
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
