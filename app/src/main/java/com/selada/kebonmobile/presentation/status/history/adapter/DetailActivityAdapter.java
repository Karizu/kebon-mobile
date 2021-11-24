package com.selada.kebonmobile.presentation.status.history.adapter;

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
import com.selada.kebonmobile.model.response.commoditymonitoring.CurrentDevelopmentDetail;
import com.selada.kebonmobile.model.response.farmactivities.Result;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class DetailActivityAdapter extends RecyclerView.Adapter<DetailActivityAdapter.ViewHolder> {
    private List<Result> transactionModels;
    private Context context;
    private Activity activity;
    private String label;

    public DetailActivityAdapter(List<Result> transactionModels, Context context, Activity activity, String label) {
        this.transactionModels = transactionModels;
        this.context = context;
        this.activity = activity;
        this.label = label;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_activity_detail, parent, false);
        return new ViewHolder(v);
    }

    @SuppressLint({"SetTextI18n", "ResourceAsColor", "CheckResult"})
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Result result = transactionModels.get(position);
        try {
            holder.labelTanggal.setText("Tanggal " + label);
            holder.tv_status.setText(result.getActivityStatusName());
            holder.tv_desc.setText(result.getDescription());
            String[] createdDateTime = formatDateAndTime(result.getCreated_date());
            holder.tv_created_date.setText(createdDateTime[0] + " " + createdDateTime[1]);
            String[] dateTime = formatDateAndTime(result.getFinishedDate());
            holder.tv_date.setText(dateTime[0] + " " + dateTime[1]);
            holder.tv_jumlah_berhasil.setText(result.getTotalSuccessObjects() + "");
            holder.tv_jumlah_gagal.setText(result.getTotalFailedObjects() + "");
        } catch (Exception e){
            holder.tv_date.setText("-");
            holder.tv_jumlah_berhasil.setText("-");
            holder.tv_jumlah_gagal.setText("-");
        }
    }

    @Override
    public int getItemCount() {
        return transactionModels.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_date, tv_status, tv_jumlah_berhasil, tv_desc, labelTanggal, tv_jumlah_gagal, tv_created_date;

        ViewHolder(View v) {
            super(v);
            tv_created_date = v.findViewById(R.id.tv_created_date);
            tv_date = v.findViewById(R.id.tv_date);
            labelTanggal = v.findViewById(R.id.labelTanggal);
            tv_status = v.findViewById(R.id.tv_status);
            tv_jumlah_berhasil = v.findViewById(R.id.tv_jumlah_berhasil);
            tv_desc = v.findViewById(R.id.tv_desc);
            tv_jumlah_gagal = v.findViewById(R.id.tv_jumlah_gagal);
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
