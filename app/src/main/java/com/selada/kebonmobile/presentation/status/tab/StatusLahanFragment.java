package com.selada.kebonmobile.presentation.status.tab;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.madapps.liquid.LiquidRefreshLayout;
import com.selada.kebonmobile.R;
import com.selada.kebonmobile.model.response.errormsg.ErrorResponse;
import com.selada.kebonmobile.model.response.farmsummary.Farm;
import com.selada.kebonmobile.model.response.farmsummary.FarmSummaryResponse;
import com.selada.kebonmobile.network.ApiCore;
import com.selada.kebonmobile.presentation.status.tab.adapter.StatusLahanAdapter;
import com.selada.kebonmobile.util.Loading;
import com.selada.kebonmobile.util.MethodUtil;
import com.selada.kebonmobile.util.PreferenceManager;

import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StatusLahanFragment extends Fragment {

    @BindView(R.id.rv_status_lahan)
    RecyclerView rv_status_lahan;
    @BindView(R.id.layout_no_data)
    RelativeLayout layout_no_data;
    @BindView(R.id.refreshLayout)
    LiquidRefreshLayout refreshLayout;

    @SuppressLint("InflateParams")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_status_lahan, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        new PreferenceManager(requireActivity());
        initComponent();
    }

    private void initComponent() {
        getMyFarm(false);

        refreshLayout.setOnRefreshListener(new LiquidRefreshLayout.OnRefreshListener() {
            @Override
            public void completeRefresh() {

            }

            @Override
            public void refreshing() {
                getMyFarm(true);
            }
        });
    }

    private void getMyFarm(boolean isRefresh){
        Loading.show(requireActivity());
        ApiCore.apiInterface().getMyFarmSummary(PreferenceManager.getSessionToken()).enqueue(new Callback<FarmSummaryResponse>() {
            @Override
            public void onResponse(Call<FarmSummaryResponse> call, Response<FarmSummaryResponse> response) {
                if (isRefresh) refreshLayout.finishRefreshing();
                Loading.hide(requireActivity());
                try {
                    if (response.isSuccessful()) {
                        List<Farm> responses = Objects.requireNonNull(response.body()).getFarms();
                        if (responses.size()>0){
                            rv_status_lahan.setVisibility(View.VISIBLE);
                            layout_no_data.setVisibility(View.GONE);
                            rv_status_lahan.setLayoutManager(new GridLayoutManager(requireActivity(), 2));
                            StatusLahanAdapter adapter = new StatusLahanAdapter(responses, requireActivity(), requireActivity());
                            rv_status_lahan.setAdapter(adapter);
                        } else {
                            rv_status_lahan.setVisibility(View.GONE);
                            layout_no_data.setVisibility(View.VISIBLE);
                        }
                    } else {
                        Gson gson = new Gson();
                        ErrorResponse message = gson.fromJson(Objects.requireNonNull(response.errorBody()).charStream(),ErrorResponse.class);
                        String errorMessage = message.getData().getMessage();
                        Dialog dialog = MethodUtil.getDialogCart(R.layout.dialog_warning, requireActivity());
                        TextView desc = dialog.findViewById(R.id.tv_desc);
                        desc.setText(errorMessage);
                        ImageView btn_close = dialog.findViewById(R.id.btn_close);
                        btn_close.setOnClickListener(view -> {
                            dialog.dismiss();
                        });
                    }
                } catch (Exception e){
                    e.printStackTrace();
                    String errorMessage = "Terjadi kesalahan (Response Code: On Catch)";
                    Dialog dialog = MethodUtil.getDialogCart(R.layout.dialog_warning, requireActivity());
                    TextView desc = dialog.findViewById(R.id.tv_desc);
                    desc.setText(errorMessage);
                }
            }

            @Override
            public void onFailure(Call<FarmSummaryResponse> call, Throwable t) {
                if (isRefresh) refreshLayout.finishRefreshing();
                t.printStackTrace();
                Loading.hide(requireActivity());
            }
        });
    }
}
