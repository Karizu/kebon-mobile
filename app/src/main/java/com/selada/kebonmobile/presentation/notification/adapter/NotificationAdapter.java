package com.selada.kebonmobile.presentation.notification.adapter;

import android.app.Activity;
import android.app.Notification;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.selada.kebonmobile.R;
import com.selada.kebonmobile.presentation.MainActivity;
import com.selada.kebonmobile.presentation.akun.history.InvoiceHistoryActivity;
import com.selada.kebonmobile.presentation.feedback.FeedbackActivity;
import com.selada.kebonmobile.presentation.jadwal.JadwalActivity;
import com.selada.kebonmobile.presentation.notification.model.NotificationModel;
import com.selada.kebonmobile.presentation.status.StatusActivity;
import com.selada.kebonmobile.presentation.status.history.HistoryActivity;
import com.skydoves.elasticviews.ElasticLayout;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder> {

    private ArrayList<NotificationModel> notifList;
    private Activity activity;

    public NotificationAdapter(ArrayList<NotificationModel> notifList, Activity activity) {
        this.notifList = notifList;
        this.activity = activity;
    }

    @NonNull
    @NotNull
    @Override
    public NotificationViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.notification_view_holder, parent, false);
        return new NotificationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull NotificationViewHolder holder, int position) {
        NotificationModel notificationModel = notifList.get(position);
        String typeCode = notificationModel.getTypeCode();
        holder.notifTitle.setText(notificationModel.getNotifTitle());
        holder.notifText.setText(notificationModel.getNotifText());
        holder.notifDate.setText(notificationModel.getDate());
        Glide.with(activity)
                .load(notificationModel.getFullpath())
                .placeholder(R.drawable.img_plant)
                .into(holder.imgNotif);
        holder.item.setOnClickListener(view -> {
            try {
                Intent intent = null;
                JSONObject object = new JSONObject(notificationModel.getDataObject());
                switch (typeCode) {
                    case "billing":
                        String invoiceId = object.getString("invoice_id");
                        Log.d("invoiceId: ", invoiceId);
                        intent = new Intent(activity, InvoiceHistoryActivity.class);
                        intent.putExtra("is_from_notif", true);
                        intent.putExtra("invoice_id", invoiceId);
                        break;
                    case "farm":
                        String siteId = object.getString("site_id");
                        String commodityId = object.getString("commodity_id");
                        Log.d("siteId: ", siteId);
                        intent = new Intent(activity, StatusActivity.class);
                        intent.putExtra("is_from_notif", true);
                        intent.putExtra("site_id", siteId);
                        intent.putExtra("commodity_id", commodityId);
                        break;
                    case "reminder":
                        Log.d("Type Code: ", typeCode);
                        intent = new Intent(activity, JadwalActivity.class);
                        intent.putExtra("is_from_notif", true);
                        break;
                    case "activity":
                        String activityId = object.getString("activity_id");
                        Log.d("activityId: ", activityId);
                        intent = new Intent(activity, HistoryActivity.class);
                        intent.putExtra("is_from_notif", true);
                        intent.putExtra("activity_id", activityId);
                        break;
                    case "general":
                        intent = new Intent(activity, MainActivity.class);
                        break;
                }

                activity.startActivity(intent);
            } catch (Exception e){
                e.printStackTrace();
            }
        });
    }

    @Override
    public int getItemCount() {
        return (notifList != null) ? notifList.size() : 0;
    }


    public class NotificationViewHolder extends RecyclerView.ViewHolder {

        private CircleImageView imgNotif;
        private TextView notifTitle, notifText, notifDate;
        private ElasticLayout item;

        public NotificationViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            imgNotif = itemView.findViewById(R.id.img_notif);
            notifTitle = itemView.findViewById(R.id.notif_title);
            notifText = itemView.findViewById(R.id.notif_text);
            notifDate = itemView.findViewById(R.id.notif_date);
            item = itemView.findViewById(R.id.item);

        }

    }
}
