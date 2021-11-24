package com.selada.kebonmobile.presentation.status.history.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.selada.kebonmobile.R;
import com.selada.kebonmobile.model.response.farmactivities.Activity;
import com.selada.kebonmobile.model.response.farmactivities.DetailFarmActivityResponse;
import com.selada.kebonmobile.network.ApiCore;
import com.selada.kebonmobile.presentation.status.history.DetailHistory;
import com.selada.kebonmobile.presentation.status.history.HistoryActivity;
import com.selada.kebonmobile.presentation.status.tab.lahan.DetailStatusLahanActivity;
import com.selada.kebonmobile.util.Loading;
import com.selada.kebonmobile.util.MethodUtil;
import com.selada.kebonmobile.util.PreferenceManager;
import com.skydoves.elasticviews.ElasticLayout;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {
    private List<Activity> transactionModels;
    private Context context;
    private android.app.Activity activity;

    public HistoryAdapter(List<Activity> transactionModels, Context context, android.app.Activity activity) {
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
        Activity act = transactionModels.get(position);
        String title = act.getActivityType().getLabel();
        String msg = act.getActivityType().getLabel() + " " + act.getCommodity().getName() + " sebanyak " + act.getTotalObjects() + " dilakukan " + act.getActivityMethod().getName();
        String[] date = MethodUtil.formatDateAndTime(act.getCreatedDate());
        holder.tv_date.setText(date[0] + " " + date[1]);

        holder.tv_title.setText(title);
        holder.tv_message.setText(msg);
        holder.cvItem.setOnClickListener(view -> {
            getDetailHistoryActivity(String.valueOf(act.getId()));
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

    private void getDetailHistoryActivity(String activityId){
        Loading.show(activity);

        ApiCore.apiInterface().getDetailHistoryActivity(activityId, PreferenceManager.getSessionToken()).enqueue(new Callback<DetailFarmActivityResponse>() {
            @Override
            public void onResponse(Call<DetailFarmActivityResponse> call, Response<DetailFarmActivityResponse> response) {
                Loading.hide(activity);
                try {
                    if (response.isSuccessful()){
                        Activity act = Objects.requireNonNull(response.body()).getActivity();
                        if (act!=null){
                            Intent intent = new Intent(activity, DetailHistory.class);
                            intent.putExtra("json", new Gson().toJson(act));
                            activity.startActivity(intent);
                            activity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                        } else {
                            Toast.makeText(activity, "Response Code: Is Empty", Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        MethodUtil.getDialogWarning(response.errorBody(), activity);
                    }
                } catch (Exception e){
                    e.printStackTrace();
                    MethodUtil.getDialogWarningCatch(activity);
                }
            }

            @Override
            public void onFailure(Call<DetailFarmActivityResponse> call, Throwable t) {
                Loading.hide(activity);
                t.printStackTrace();
            }
        });
    }
}
