package com.selada.kebonmobile.presentation.panen;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.selada.kebonmobile.R;
import com.selada.kebonmobile.model.response.SchemeResponse;
import com.selada.kebonmobile.model.response.harvest.ActivityMethod;
import com.selada.kebonmobile.presentation.home.tanam.PilihMetodeActivity;
import com.selada.kebonmobile.presentation.status.tab.tanaman.panen.KonfirmasiAddressActivity;
import com.selada.kebonmobile.presentation.status.tab.tanaman.panen.KonfirmasiPanenOfflineActivity;
import com.selada.kebonmobile.util.MethodUtil;
import com.skydoves.elasticviews.ElasticButton;

import java.util.List;

public class HarvestMethodAdapter extends RecyclerView.Adapter<HarvestMethodAdapter.ViewHolder> {
    private List<ActivityMethod> transactionModels;
    private Context context;
    private Activity activity;
    private int pos = -1;
    private String json;

    public HarvestMethodAdapter(List<ActivityMethod> transactionModels, Context context, Activity activity, String json) {
        this.transactionModels = transactionModels;
        this.context = context;
        this.activity = activity;
        this.json = json;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_pilih_metode, parent, false);
        return new ViewHolder(v);
    }

    @SuppressLint({"SetTextI18n", "ResourceAsColor", "CheckResult"})
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ActivityMethod activityMethod = transactionModels.get(position);

        holder.tv_title.setText(activityMethod.getName());
        holder.tv_desc.setText(activityMethod.getShortDescription());
        holder.btn_info_selengkapnya.setOnClickListener(view -> {
            Dialog dialog = MethodUtil.getDialogCart(R.layout.dialog_info_konvensional, activity);
            TextView tv_title = dialog.findViewById(R.id.tv_title);
            TextView tv_desc = dialog.findViewById(R.id.tv_desc);

            tv_title.setText("Metode " + activityMethod.getName());
            tv_desc.setText(activityMethod.getDescription());
        });
        holder.item.setOnClickListener(view -> {
            if (activityMethod.getCode().equals("offline_harvest")){
                Intent intent = new Intent(activity, KonfirmasiPanenOfflineActivity.class);
                intent.putExtra("json", json);
                intent.putExtra("method_id", activityMethod.getCode());
                activity.startActivity(intent);
                activity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            } else {
                Intent intent = new Intent(activity, KonfirmasiAddressActivity.class);
                intent.putExtra("json", json);
                intent.putExtra("method_id", activityMethod.getCode());
                activity.startActivity(intent);
                activity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });

        Glide.with(activity)
                .load(activityMethod.getImage().getFullpath())
                .placeholder(R.drawable.img_plant)
                .into(holder.img_scheme);
    }

    @Override
    public int getItemCount() {
        return transactionModels.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_title;
        TextView tv_desc;
        ElasticButton btn_info_selengkapnya;
        RelativeLayout item;
        ImageView img_scheme;
        CardView cv_pilih;

        ViewHolder(View v) {
            super(v);
            cv_pilih = v.findViewById(R.id.cv_pilih);
            tv_title = v.findViewById(R.id.text1);
            tv_desc = v.findViewById(R.id.textView4);
            btn_info_selengkapnya = v.findViewById(R.id.btn_info_selengkapnya);
            item = v.findViewById(R.id.item);
            img_scheme = v.findViewById(R.id.img_scheme);
        }
    }
}
