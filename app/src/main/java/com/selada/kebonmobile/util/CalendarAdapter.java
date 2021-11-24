package com.selada.kebonmobile.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.selada.kebonmobile.R;
import com.selada.kebonmobile.model.CalendarModel;
import com.selada.kebonmobile.model.response.calendardetail.DetailHarvestCalendarResponse;
import com.selada.kebonmobile.model.response.calendardetail.HarvestSchedule;
import com.selada.kebonmobile.network.ApiCore;
import com.selada.kebonmobile.presentation.home.tanam.DetailCartActivity;
import com.selada.kebonmobile.presentation.jadwal.JadwalActivity;
import com.selada.kebonmobile.presentation.status.history.DetailHistory;
import com.skydoves.elasticviews.ElasticLayout;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CalendarAdapter extends RecyclerView.Adapter<CalendarAdapter.ViewHolder> {
    private List<CalendarModel> transactionModels;
    private Context context;
    private Activity activity;

    public CalendarAdapter(List<CalendarModel> transactionModels, Context context, Activity activity) {
        this.transactionModels = transactionModels;
        this.context = context;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        new PreferenceManager(activity);
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_date, parent, false);

        return new ViewHolder(v);
    }

    @SuppressLint({"SetTextI18n", "ResourceAsColor", "CheckResult", "SimpleDateFormat"})
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CalendarModel calendarModel = transactionModels.get(position);
        holder.date.setText(String.valueOf(transactionModels.get(position).getDate()));

        if (calendarModel.getMonth() == calendarModel.getCalendarCompare().get(Calendar.MONTH) && calendarModel.getYear() == calendarModel.getCalendarCompare().get(Calendar.YEAR)){
            holder.item.setBackground(activity.getResources().getDrawable(R.drawable.bg_custom_calendar));
            holder.date.setTextColor(activity.getResources().getColor(R.color.colorDarkBlue));
        } else {
            holder.item.setBackground(activity.getResources().getDrawable(R.drawable.bg_custom_calendar_disabled));
            holder.date.setTextColor(activity.getResources().getColor(R.color.date_false));
        }

        if (transactionModels.get(position).getStatus()!=null){
            if (transactionModels.get(position).getStatus().equals("event")){
                holder.dot.setImageDrawable(activity.getResources().getDrawable(R.drawable.dot));
                holder.text_event.setVisibility(View.VISIBLE);
                holder.text_event.setText(calendarModel.getCalendars().getTotalObjectCount()+"");
            } else {
                holder.text_event.setVisibility(View.GONE);
            }
        } else {
            holder.text_event.setVisibility(View.GONE);
        }

        holder.item.setOnClickListener(view -> {
            if (calendarModel.getStatus()!=null){
                if (calendarModel.getStatus().equals("event")){
                    String strCurrentDate = calendarModel.getCalendars().getCalendarDate();
                    try {
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US);
                        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Etc/UTC"));
                        Date date = simpleDateFormat.parse(strCurrentDate);
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", new Locale("id", "ID"));
                        String sDate = dateFormat.format(Objects.requireNonNull(date));
                        int commodityId = 0;
                        int siteId = 0;
                        if (context instanceof JadwalActivity) {
                            commodityId = ((JadwalActivity)context).getCommodityId();
                            siteId = ((JadwalActivity)context).getSiteId();
                        }
                        if (commodityId == 0 && siteId == 0){
                            getDetailHarvestCalendar(sDate);
                        } else {
                            String siteIds = siteId==0?"":siteId+"";
                            String commodityIds = commodityId==0?"":commodityId+"";
                            getDetailHarvestCalendarFilter(siteIds, commodityIds, sDate);
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return transactionModels.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView date, text_event;
        ImageView dot;
        Context context;
        ElasticLayout item;

        ViewHolder(View v) {
            super(v);
            date = v.findViewById(R.id.date);
            dot = v.findViewById(R.id.dot);
            context = v.getContext();
            item = v.findViewById(R.id.item);
            text_event = v.findViewById(R.id.text_event);
        }
    }

    private void getDetailHarvestCalendar(String date){
        Loading.show(activity);
        ApiCore.apiInterface().getDetailHarvestCalendar(date, PreferenceManager.getSessionToken()).enqueue(new Callback<DetailHarvestCalendarResponse>() {
            @Override
            public void onResponse(Call<DetailHarvestCalendarResponse> call, Response<DetailHarvestCalendarResponse> response) {
                Loading.hide(activity);
                try {
                    if (response.isSuccessful()){
                        List<HarvestSchedule> harvestScheduleList = Objects.requireNonNull(response.body()).getHarvestSchedules();

                        CustomBottomSheetDialog bottomSheet = new CustomBottomSheetDialog(harvestScheduleList, context, activity);
                        bottomSheet.show(((AppCompatActivity)context).getSupportFragmentManager(), "exampleBottomSheet");
                    } else {
                        MethodUtil.getDialogWarning(response.errorBody(), activity);
                    }
                } catch (Exception e){
                    e.printStackTrace();
                    MethodUtil.getDialogWarningCatch(activity);
                }
            }

            @Override
            public void onFailure(Call<DetailHarvestCalendarResponse> call, Throwable t) {
                t.printStackTrace();
                Loading.hide(activity);
            }
        });
    }

    private void getDetailHarvestCalendarFilter(String site_id, String commodity_id, String date){
        Loading.show(activity);
        ApiCore.apiInterface().getDetailHarvestCalendar(site_id, commodity_id, date, PreferenceManager.getSessionToken()).enqueue(new Callback<DetailHarvestCalendarResponse>() {
            @Override
            public void onResponse(Call<DetailHarvestCalendarResponse> call, Response<DetailHarvestCalendarResponse> response) {
                Loading.hide(activity);
                try {
                    if (response.isSuccessful()){
                        List<HarvestSchedule> harvestScheduleList = Objects.requireNonNull(response.body()).getHarvestSchedules();

                        CustomBottomSheetDialog bottomSheet = new CustomBottomSheetDialog(harvestScheduleList, context, activity);
                        bottomSheet.show(((AppCompatActivity)context).getSupportFragmentManager(), "exampleBottomSheet");
                    } else {
                        MethodUtil.getDialogWarning(response.errorBody(), activity);
                    }
                } catch (Exception e){
                    e.printStackTrace();
                    MethodUtil.getDialogWarningCatch(activity);
                }
            }

            @Override
            public void onFailure(Call<DetailHarvestCalendarResponse> call, Throwable t) {
                Loading.hide(activity);
                t.printStackTrace();
            }
        });
    }

}
