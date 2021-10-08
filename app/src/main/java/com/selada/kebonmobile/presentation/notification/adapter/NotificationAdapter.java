package com.selada.kebonmobile.presentation.notification.adapter;

import android.app.Notification;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.selada.kebonmobile.R;
import com.selada.kebonmobile.presentation.notification.model.NotificationModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder> {

    private ArrayList<NotificationModel> notifList;

    public NotificationAdapter(ArrayList<NotificationModel> notifList) {
        this.notifList = notifList;
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

        holder.notifTitle.setText(notifList.get(position).getNotifTitle());
        holder.notifText.setText(notifList.get(position).getNotifText());
        holder.notifDate.setText(notifList.get(position).getDate());

    }

    @Override
    public int getItemCount() {
        return (notifList != null) ? notifList.size() : 0;
    }

    public class NotificationViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgNotif;
        private TextView notifTitle, notifText, notifDate;

        public NotificationViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            notifTitle = itemView.findViewById(R.id.notif_title);
            notifText = itemView.findViewById(R.id.notif_text);
            notifDate = itemView.findViewById(R.id.notif_date);
        }
    }
}
