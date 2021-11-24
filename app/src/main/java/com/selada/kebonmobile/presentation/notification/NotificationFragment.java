package com.selada.kebonmobile.presentation.notification;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.madapps.liquid.LiquidRefreshLayout;
import com.selada.kebonmobile.R;
import com.selada.kebonmobile.model.response.ApiResponse;
import com.selada.kebonmobile.model.response.notification.NotificationResponse;
import com.selada.kebonmobile.network.ApiCore;
import com.selada.kebonmobile.presentation.home.tanam.PilihMetodeActivity;
import com.selada.kebonmobile.presentation.notification.adapter.NotificationAdapter;
import com.selada.kebonmobile.presentation.notification.model.NotificationModel;
import com.selada.kebonmobile.util.Loading;
import com.selada.kebonmobile.util.MethodUtil;
import com.selada.kebonmobile.util.PreferenceManager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationFragment extends Fragment {

    @BindView(R.id.layout_no_data)
    LinearLayout layout_no_data;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    LiquidRefreshLayout refreshLayout;

    private NotificationAdapter notifAdapter;
    private ArrayList<NotificationModel> notifArrayList;

    @OnClick(R.id.btn_tanam)
    void onClickTanam(){
        Intent intent = new Intent(requireActivity(), PilihMetodeActivity.class);
        startActivity(intent);
        requireActivity().overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    @SuppressLint("InflateParams")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notification, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        getListNotification(false);
        refreshLayout.setOnRefreshListener(new LiquidRefreshLayout.OnRefreshListener() {
            @Override
            public void completeRefresh() {

            }

            @Override
            public void refreshing() {
                getListNotification(true);
            }
        });

    }

    private void getListNotification(boolean isRefresh){
        Loading.show(requireActivity());
        ApiCore.apiInterface().getListNotification(PreferenceManager.getSessionToken()).enqueue(new Callback<ApiResponse<List<NotificationResponse>>>() {
            @Override
            public void onResponse(Call<ApiResponse<List<NotificationResponse>>> call, Response<ApiResponse<List<NotificationResponse>>> response) {
                if (isRefresh) refreshLayout.finishRefreshing();
                Loading.hide(requireContext());
                try {
                    if (response.isSuccessful()){
                        List<NotificationResponse> notificationResponseList = Objects.requireNonNull(response.body()).getData();
                        if (notificationResponseList.size()>0){
                            recyclerView.setVisibility(View.VISIBLE);
                            layout_no_data.setVisibility(View.GONE);
                            notifArrayList = new ArrayList<>();
                            for (NotificationResponse notificationResponse: notificationResponseList){
                                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US);
                                simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Etc/UTC"));
                                Date date = simpleDateFormat.parse(notificationResponse.getCreatedDate());
                                SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy", new Locale("id", "ID"));
                                notifArrayList.add(new NotificationModel(notificationResponse.getTitle(),
                                        notificationResponse.getBodyMessage(),
                                        dateFormat.format(Objects.requireNonNull(date)),
                                        notificationResponse.getIconImage().getFullpath(),
                                        notificationResponse.getCode(),
                                        notificationResponse.getDataObject()
                                ));
                            }

                            recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false));
                            notifAdapter = new NotificationAdapter(notifArrayList, requireActivity());
                            recyclerView.setAdapter(notifAdapter);
                        } else {
                            recyclerView.setVisibility(View.GONE);
                            layout_no_data.setVisibility(View.VISIBLE);
                        }
                    } else {
                        MethodUtil.getDialogWarning(response.errorBody(), requireActivity());
                    }
                } catch (Exception e){
                    e.printStackTrace();
                    MethodUtil.getDialogWarningCatch(requireActivity());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<List<NotificationResponse>>> call, Throwable t) {
                if (isRefresh) refreshLayout.finishRefreshing();
                t.printStackTrace();
                Loading.hide(requireContext());
            }
        });
    }
}
