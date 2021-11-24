package com.selada.kebonmobile.presentation.home.pembayaran.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.selada.kebonmobile.R;
import com.selada.kebonmobile.model.response.SiteResponse;
import com.selada.kebonmobile.model.response.leasesite.Account;
import com.selada.kebonmobile.presentation.home.lahan.SewaLahanActivity;
import com.skydoves.elasticviews.ElasticCardView;

import java.util.List;

public class RekeningAdapter extends RecyclerView.Adapter<RekeningAdapter.ViewHolder> {
    private List<Account> transactionModels;
    private Context context;
    private Activity activity;

    public RekeningAdapter(List<Account> transactionModels, Context context, Activity activity) {
        this.transactionModels = transactionModels;
        this.context = context;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_bank, parent, false);

        return new ViewHolder(v);
    }

    @SuppressLint({"SetTextI18n", "ResourceAsColor", "CheckResult"})
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Account siteResponse = transactionModels.get(position);
        String accountNo = siteResponse.getAccountNumber();
        String image = siteResponse.getImage().getFullpath();

        holder.tv_bank_name.setText(siteResponse.getAccountIssuerInstitutionName());
        holder.tv_no_rekening.setText(accountNo);
        holder.tv_issuer_name.setText(siteResponse.getAccountName());
        Glide.with(activity)
                .load(image)
                .into(holder.img_bank);
        holder.tv_salin.setOnClickListener(view -> {
            ClipboardManager clipboard = (ClipboardManager) activity.getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("No Rekening telah disalin", accountNo);
            clipboard.setPrimaryClip(clip);
            Toast.makeText(context, "No Rekening telah disalin", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return transactionModels.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_bank_name, tv_no_rekening, tv_salin, tv_issuer_name;
        ImageView img_bank;

        ViewHolder(View v) {
            super(v);
            tv_bank_name = v.findViewById(R.id.tv_bank_name);
            tv_no_rekening = v.findViewById(R.id.tv_no_rekening);
            tv_salin = v.findViewById(R.id.tv_salin);
            tv_issuer_name = v.findViewById(R.id.tv_issuer_name);
            img_bank = v.findViewById(R.id.img_bank);
        }
    }
}
