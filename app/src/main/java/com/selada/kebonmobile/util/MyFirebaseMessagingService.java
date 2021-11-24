package com.selada.kebonmobile.util;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Vibrator;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.selada.kebonmobile.R;
import com.selada.kebonmobile.presentation.MainActivity;
import com.selada.kebonmobile.presentation.akun.history.InvoiceHistoryActivity;
import com.selada.kebonmobile.presentation.jadwal.JadwalActivity;
import com.selada.kebonmobile.presentation.status.StatusActivity;
import com.selada.kebonmobile.presentation.status.history.HistoryActivity;

import org.json.JSONObject;

import java.util.Map;
import java.util.Objects;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);
        Log.e("newToken", s);
        getSharedPreferences("_", MODE_PRIVATE).edit().putString("fb", s).apply();
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        System.out.println("Data Notif: " + remoteMessage.getData());

        Intent intent = null;
        try
        {
            Map<String, String> params = remoteMessage.getData();
            JSONObject object = new JSONObject(params);
            Log.e("JSON OBJECT", object.toString());
            String typeCode = object.getString("type_code");
            switch (typeCode) {
                case "billing":
                    String invoiceId = object.getString("invoice_id");
                    Log.d("invoiceId: ", invoiceId);
                    intent = new Intent(this, InvoiceHistoryActivity.class);
                    intent.putExtra("is_from_notif", true);
                    intent.putExtra("invoice_id", invoiceId);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    break;
                case "farm":
                    String siteId = object.getString("site_id");
                    String commodityId = object.getString("commodity_id");
                    Log.d("siteId: ", siteId);
                    intent = new Intent(this, StatusActivity.class);
                    intent.putExtra("is_from_notif", true);
                    intent.putExtra("site_id", siteId);
                    intent.putExtra("commodity_id", commodityId);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    break;
                case "reminder":
                    Log.d("Type Code: ", typeCode);
                    intent = new Intent(this, JadwalActivity.class);
                    intent.putExtra("is_from_notif", true);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    break;
                case "activity":
                    String activityId = object.getString("activity_id");
                    Log.d("activityId: ", activityId);

                    intent = new Intent(this, HistoryActivity.class);
                    intent.putExtra("is_from_notif", true);
                    intent.putExtra("activity_id", activityId);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    break;
                case "general":
                    intent = new Intent(this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    break;
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        Vibrator v = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(500);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
        String channelId = "Default";
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder builder = new  NotificationCompat.Builder(this, channelId)
                .setSmallIcon(R.drawable.icon_notif_kebon)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),
                        R.drawable.icon_notif_kebon))
                .setSound(alarmSound)
                .setLights(Color.RED, 3000, 3000)
                .setVibrate(new long[] { 1000, 1000, 1000, 1000, 1000 })
                .setContentTitle(Objects.requireNonNull(remoteMessage.getNotification()).getTitle())
                .setContentText(remoteMessage.getNotification().getBody())
                .setAutoCancel(true)
                .setContentIntent(pendingIntent);

        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelId, "Default channel", NotificationManager.IMPORTANCE_DEFAULT);
            Objects.requireNonNull(manager).createNotificationChannel(channel);
        }
        Objects.requireNonNull(manager).notify(0, builder.build());
    }

    public static String getToken(Context context) {
        return context.getSharedPreferences("_", MODE_PRIVATE).getString("fb", "empty");
    }
}
