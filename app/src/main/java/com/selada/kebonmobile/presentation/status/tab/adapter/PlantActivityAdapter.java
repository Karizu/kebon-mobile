package com.selada.kebonmobile.presentation.status.tab.adapter;

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

import com.selada.kebonmobile.R;
import com.selada.kebonmobile.model.response.commoditymonitoring.CurrentDevelopmentDetail;
import com.selada.kebonmobile.model.response.commoditymonitoring.PhaseTimeline;
import com.selada.kebonmobile.presentation.home.tanam.DetailCartActivity;
import com.selada.kebonmobile.presentation.jadwal.JadwalActivity;
import com.selada.kebonmobile.presentation.panen.DaftarPanenActivity;
import com.selada.kebonmobile.presentation.status.tab.lahan.DetailStatusLahanActivity;
import com.selada.kebonmobile.presentation.status.tab.tanaman.DetailStatusTanamanActivity;
import com.selada.kebonmobile.presentation.status.tab.tanaman.RincianFaseAdapter;
import com.selada.kebonmobile.util.MethodUtil;
import com.selada.kebonmobile.util.PreferenceManager;
import com.skydoves.elasticviews.ElasticButton;
import com.skydoves.elasticviews.ElasticImageView;
import com.skydoves.elasticviews.ElasticLayout;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class PlantActivityAdapter extends RecyclerView.Adapter<PlantActivityAdapter.ViewHolder> {
    private List<CurrentDevelopmentDetail> transactionModels;
    private Context context;
    private Activity activity;

    public PlantActivityAdapter(List<CurrentDevelopmentDetail> transactionModels, Context context, Activity activity) {
        this.transactionModels = transactionModels;
        this.context = context;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_plant_activity, parent, false);
        return new ViewHolder(v);
    }

    @SuppressLint({"SetTextI18n", "ResourceAsColor", "CheckResult"})
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CurrentDevelopmentDetail developmentDetail = transactionModels.get(position);
        String[] dateTime = formatDateAndTime(developmentDetail.getStartDate());
        String[] dateTimeHarvestStart = formatDateAndTime(developmentDetail.getHarvestStartDate());
        String[] dateTimeHarvestEnd = formatDateAndTime(developmentDetail.getHarvestEndDate());
        String status = developmentDetail.getCurrent_status_name();

        holder.tv_date.setText(dateTime[0] + " " + dateTime[1]);
        holder.tv_jumlah.setText(developmentDetail.getObjectCount() + " " + developmentDetail.getObject_type_label());
        holder.tv_harvest_date.setText(dateTimeHarvestStart[0] + " - " + dateTimeHarvestEnd[0]);
        holder.tv_status.setText(developmentDetail.getCurrent_status_name());

        if (Integer.parseInt(developmentDetail.getRemainingDaysToHarvest()) < 1){
            if (status.equals("Harvesting")) {
                holder.tv_remaining_days.setText("Sedang Panen");
            } else {
                holder.tv_remaining_days.setText("Siap Panen");
                if (context instanceof DetailStatusTanamanActivity) {
                    if (!((DetailStatusTanamanActivity)context).isAlreadyOpenReminder()) {
                        ((DetailStatusTanamanActivity)context).setIsOpenReminder(true);
                        Dialog dialog = MethodUtil.getDialogCart(R.layout.dialog_reminder_harvest, activity);
                        ElasticButton btn_check = dialog.findViewById(R.id.btn_check);
                        btn_check.setOnClickListener(view -> {
                            Intent intent = new Intent(activity, DaftarPanenActivity.class);
                            activity.startActivity(intent);
                            activity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                        });
                    }
                }
            }
        } else {
            holder.tv_remaining_days.setText(developmentDetail.getRemainingDaysToHarvest() + " Hari Menuju Panen");
        }

        holder.tv_age.setText(developmentDetail.getAge() + " Hari");

        holder.btn_istilah.setOnClickListener(view -> {
            Dialog dialog = MethodUtil.getDialogCart(R.layout.dialog_fase, activity);
            RecyclerView rv_fase = dialog.findViewById(R.id.rv_fase);
            RincianFaseAdapter adapter = new RincianFaseAdapter(developmentDetail.getPhaseTimeline(), activity, activity);
            rv_fase.setAdapter(adapter);

        });

        holder.tv_fase.setText(developmentDetail.getCurrentPhaseName());

        for (PhaseTimeline phaseTimeline: developmentDetail.getPhaseTimeline()){
            if (phaseTimeline.getIsCurrent()){
                if (developmentDetail.getCurrentPhaseId() == phaseTimeline.getId()) {
                    holder.item.setBackgroundColor(activity.getResources().getColor(R.color.very_light_pink));
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return transactionModels.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_date, tv_jumlah, tv_harvest_date, tv_fase, tv_remaining_days, tv_status, tv_age;
        LinearLayout item;
        ElasticImageView btn_istilah;

        ViewHolder(View v) {
            super(v);
            btn_istilah = v.findViewById(R.id.btn_istilah);
            tv_status = v.findViewById(R.id.tv_status);
            tv_date = v.findViewById(R.id.tv_date);
            tv_age = v.findViewById(R.id.tv_age);
            tv_jumlah = v.findViewById(R.id.tv_jumlah);
            tv_harvest_date = v.findViewById(R.id.tv_harvest_date);
            tv_fase = v.findViewById(R.id.tv_fase);
            tv_remaining_days = v.findViewById(R.id.tv_remaining_days);
            item = v.findViewById(R.id.item);
        }
    }

    private static String[] formatDateAndTime(String dateTime) {
        String[] tempDateTime = new String[2];
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US);
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Etc/UTC"));
            Date date = simpleDateFormat.parse(dateTime);
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy", new Locale("id", "ID"));
            @SuppressLint("SimpleDateFormat") SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
            tempDateTime[0] = dateFormat.format(date);
            tempDateTime[1] = timeFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return tempDateTime;
    }
}
