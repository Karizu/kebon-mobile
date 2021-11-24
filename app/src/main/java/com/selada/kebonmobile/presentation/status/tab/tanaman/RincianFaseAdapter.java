package com.selada.kebonmobile.presentation.status.tab.tanaman;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.selada.kebonmobile.R;
import com.selada.kebonmobile.model.response.commoditymonitoring.PhaseTimeline;
import com.selada.kebonmobile.model.response.detailcommodities.ActiveSite;
import com.selada.kebonmobile.model.response.errormsg.ErrorResponse;
import com.selada.kebonmobile.model.response.farmsummary.Farm;
import com.selada.kebonmobile.model.response.farmsummary.FarmSummaryResponse;
import com.selada.kebonmobile.model.response.farmsummary.SiteComponent;
import com.selada.kebonmobile.network.ApiCore;
import com.selada.kebonmobile.presentation.status.tab.lahan.DetailStatusLahanActivity;
import com.selada.kebonmobile.util.Loading;
import com.selada.kebonmobile.util.MethodUtil;
import com.selada.kebonmobile.util.PreferenceManager;
import com.skydoves.elasticviews.ElasticButton;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RincianFaseAdapter extends RecyclerView.Adapter<RincianFaseAdapter.ViewHolder> {
    private List<PhaseTimeline> transactionModels;
    private Context context;
    private Activity activity;

    public RincianFaseAdapter(List<PhaseTimeline> transactionModels, Context context, Activity activity) {
        this.transactionModels = transactionModels;
        this.context = context;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_fase, parent, false);

        return new ViewHolder(v);
    }

    @SuppressLint({"SetTextI18n", "ResourceAsColor", "CheckResult"})
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PhaseTimeline phaseTimeline = transactionModels.get(position);
        holder.tv_title.setText(phaseTimeline.getPhaseName());
        if (phaseTimeline.getIsCurrent()){
            holder.img_icon_1.setBackgroundColor(activity.getResources().getColor(R.color.red_text));
            Glide.with(activity)
                    .load(R.drawable.ic_baseline_donut_large_red_24)
                    .placeholder(R.drawable.ic_baseline_donut_large_red_24)
                    .into(holder.img_icon_2);
            holder.img_icon_3.setBackgroundColor(activity.getResources().getColor(R.color.red_text));
            holder.tv_title.setTextColor(activity.getResources().getColor(R.color.red_text));
        } else {
            holder.img_icon_1.setBackgroundColor(activity.getResources().getColor(R.color.NormalColorText));
            Glide.with(activity)
                    .load(R.drawable.ic_baseline_donut_large_normal_24)
                    .placeholder(R.drawable.ic_baseline_donut_large_normal_24)
                    .into(holder.img_icon_2);
            holder.img_icon_3.setBackgroundColor(activity.getResources().getColor(R.color.NormalColorText));
            holder.tv_title.setTextColor(activity.getResources().getColor(R.color.NormalColorText));
        }
    }

    @Override
    public int getItemCount() {
        return transactionModels.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img_icon_1, img_icon_2, img_icon_3;
        TextView tv_title;
        LinearLayout item;

        ViewHolder(View v) {
            super(v);
            img_icon_1 = v.findViewById(R.id.img_icon_1);
            img_icon_2 = v.findViewById(R.id.img_icon_2);
            img_icon_3 = v.findViewById(R.id.img_icon_3);
            item = v.findViewById(R.id.item);
            tv_title = v.findViewById(R.id.tv_title);
        }
    }
}
