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
import com.selada.kebonmobile.presentation.status.history.DetailHistory;
import com.skydoves.elasticviews.ElasticLayout;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_date, parent, false);

        return new ViewHolder(v);
    }

    @SuppressLint({"SetTextI18n", "ResourceAsColor", "CheckResult"})
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.date.setText(String.valueOf(transactionModels.get(position).getDate()));

        if (transactionModels.get(position).getMonth() == transactionModels.get(position).getCalendarCompare().get(Calendar.MONTH) && transactionModels.get(position).getYear() == transactionModels.get(position).getCalendarCompare().get(Calendar.YEAR)){
            holder.item.setBackground(activity.getResources().getDrawable(R.drawable.bg_custom_calendar));
            holder.date.setTextColor(activity.getResources().getColor(R.color.colorDarkBlue));
        } else {
            holder.item.setBackground(activity.getResources().getDrawable(R.drawable.bg_custom_calendar_disabled));
            holder.date.setTextColor(activity.getResources().getColor(R.color.date_false));
        }

        if (transactionModels.get(position).getStatus()!=null){
            if (transactionModels.get(position).getStatus().equals("event")){
                holder.dot.setImageDrawable(activity.getResources().getDrawable(R.drawable.dot));
                holder.item.setBackground(activity.getResources().getDrawable(R.drawable.bg_custom_calendar_event));
                holder.text_event.setVisibility(View.VISIBLE);
                holder.text_event.setText("2");
            } else {
                holder.text_event.setVisibility(View.GONE);
            }
        } else {
            holder.text_event.setVisibility(View.GONE);
        }

        holder.item.setOnClickListener((View.OnClickListener) view -> {
            if (transactionModels.get(position).getStatus()!=null){
                if (transactionModels.get(position).getStatus().equals("event")){
                    List<String> list = new ArrayList<>();
                    list.add("Kangkung");
                    list.add("Bayam");
                    CustomBottomSheetDialog bottomSheet = new CustomBottomSheetDialog(list, context, activity);
                    bottomSheet.show(((AppCompatActivity)context).getSupportFragmentManager(), "exampleBottomSheet");
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

    public String dateBuilder(int tanggal, int bulan, int tahun){
//        String tgl = String.valueOf(tanggal).length() == 1?"tanggal":""+tanggal;
//        String bln = String.valueOf(bulan).length() == 1?"0$bulan":""+bulan;
        String tgl = "Tanggal "+tanggal;
        String bln = "Bulan " + bulan;


        return tgl+ " " + bln + " " + tahun;
    }
}
