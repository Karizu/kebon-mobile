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
import com.selada.kebonmobile.model.response.farmactivities.CostDetail;
import com.selada.kebonmobile.model.response.farmactivities.Result;
import com.selada.kebonmobile.util.MethodUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CostActivityAdapter extends RecyclerView.Adapter<CostActivityAdapter.ViewHolder> {
    private List<CostDetail> transactionModels;
    private Context context;
    private Activity activity;
    private String label;

    public CostActivityAdapter(List<CostDetail> transactionModels, Context context, Activity activity, String label) {
        this.transactionModels = transactionModels;
        this.context = context;
        this.activity = activity;
        this.label = label;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_activity_cost, parent, false);
        return new ViewHolder(v);
    }

    @SuppressLint({"SetTextI18n", "ResourceAsColor", "CheckResult"})
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CostDetail costDetail = transactionModels.get(position);

        holder.label.setText(costDetail.getLabel());
        holder.tv_cost.setText("Rp " + MethodUtil.toCurrencyNumber(costDetail.getCost()));
    }

    @Override
    public int getItemCount() {
        return transactionModels.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView label, tv_cost;

        ViewHolder(View v) {
            super(v);
            label = v.findViewById(R.id.label);
            tv_cost = v.findViewById(R.id.tv_cost);
        }
    }

    private static String[] formatDateAndTime(String dateTime) {
        String[] tempDateTime = new String[2];
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", new Locale("id")).parse(dateTime);
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
