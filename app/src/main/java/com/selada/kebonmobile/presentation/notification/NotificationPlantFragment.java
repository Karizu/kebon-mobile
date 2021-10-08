package com.selada.kebonmobile.presentation.notification;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.selada.kebonmobile.R;
import com.selada.kebonmobile.presentation.feedback.FeedbackActivity;
import com.selada.kebonmobile.presentation.notification.adapter.NotificationAdapter;
import com.selada.kebonmobile.presentation.notification.model.NotificationModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class NotificationPlantFragment extends Fragment {

    private RecyclerView recyclerView;
    private NotificationAdapter notifAdapter;
    private ArrayList<NotificationModel> notifArrayList;


//    @OnClick(R.id.notif1)
//    void onClickFeedbackNotification() {
//        Intent intent = new Intent(requireActivity(), FeedbackActivity.class);
//        startActivity(intent);
//        requireActivity().overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notification_plant, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        addData();
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        notifAdapter = new NotificationAdapter(notifArrayList);
        recyclerView.setAdapter(notifAdapter);
    }

    private void addData() {
        notifArrayList = new ArrayList<>();
        notifArrayList.add(new NotificationModel("Penilaian","Ceritakan pengalaman Anda pada saat berkebun di KEBON", "29 Sept"));
        notifArrayList.add(new NotificationModel("Informasi","Panen sekarang Sawi Anda di Lahan Depari Farm", "29 Sept"));
        notifArrayList.add(new NotificationModel("Pembayaran","Pembayaran sewa lahan anda telah diterima", "23 Sept"));
        notifArrayList.add(new NotificationModel("Pembayaran","Segera melakukan pembayaran sewa lahan sebelum tanggal 24 Agustus 2021", "24 Sept"));
    }
}