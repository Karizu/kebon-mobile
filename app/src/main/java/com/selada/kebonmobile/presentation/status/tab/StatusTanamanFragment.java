package com.selada.kebonmobile.presentation.status.tab;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.madapps.liquid.LiquidRefreshLayout;
import com.selada.kebonmobile.R;
import com.selada.kebonmobile.model.response.commodity.MainImage;
import com.selada.kebonmobile.model.response.errormsg.ErrorResponse;
import com.selada.kebonmobile.model.response.farmsummary.ActiveCommodity;
import com.selada.kebonmobile.model.response.farmsummary.Farm;
import com.selada.kebonmobile.model.response.farmsummary.FarmSummaryResponse;
import com.selada.kebonmobile.model.response.mycommodities.Commodity;
import com.selada.kebonmobile.model.response.mycommodities.MyFarmCommoditiesResponse;
import com.selada.kebonmobile.network.ApiCore;
import com.selada.kebonmobile.presentation.home.adapter.HomeLahanAdapter;
import com.selada.kebonmobile.presentation.status.tab.adapter.StatusLahanAdapter;
import com.selada.kebonmobile.presentation.status.tab.adapter.StatusTanamanAdapter;
import com.selada.kebonmobile.util.Loading;
import com.selada.kebonmobile.util.MethodUtil;
import com.selada.kebonmobile.util.PreferenceManager;
import com.skydoves.elasticviews.ElasticLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StatusTanamanFragment extends Fragment {

    @BindView(R.id.spinner_filter_tanaman)
    Spinner spinner_filter_tanaman;
    @BindView(R.id.rv_status_tanaman)
    RecyclerView rv_status_tanaman;
    @BindView(R.id.layout_no_data)
    RelativeLayout layout_no_data;
    @BindView(R.id.layout_spinner)
    ElasticLayout layout_spinner;

    @BindView(R.id.refreshLayout)
    LiquidRefreshLayout refreshLayout;

    private String farmNameIdSelectedItem;
    private StatusTanamanAdapter adapter;

    @OnClick(R.id.layout_spinner)
    void onClickLayoutSpinner() {
        spinner_filter_tanaman.performClick();
    }

    @SuppressLint("InflateParams")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_status_tanaman, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        initComponent();
    }

    private void initComponent() {
        getMyFarmSummary(false);
        getMyFarmCommodities(false);

        refreshLayout.setOnRefreshListener(new LiquidRefreshLayout.OnRefreshListener() {
            @Override
            public void completeRefresh() {

            }

            @Override
            public void refreshing() {
                getMyFarmSummary(true);
                getMyFarmCommodities(true);
            }
        });
    }

    private void getMyFarmCommodities(boolean isRefresh) {
        Loading.show(requireActivity());
        ApiCore.apiInterface().getMyFarmCommodities(PreferenceManager.getSessionToken()).enqueue(new Callback<MyFarmCommoditiesResponse>() {
            @Override
            public void onResponse(Call<MyFarmCommoditiesResponse> call, Response<MyFarmCommoditiesResponse> response) {
                if (isRefresh) refreshLayout.finishRefreshing();
                Loading.hide(requireActivity());
                try {
                    if (response.isSuccessful()) {
                        List<Commodity> commodityList = Objects.requireNonNull(response.body()).getCommodities();
                        if (commodityList.size()>0){
                            layout_no_data.setVisibility(View.GONE);
                            layout_spinner.setVisibility(View.VISIBLE);
                            rv_status_tanaman.setVisibility(View.VISIBLE);
                            adapter = new StatusTanamanAdapter(commodityList, requireActivity(), requireActivity());
                            rv_status_tanaman.setLayoutManager(new GridLayoutManager(requireActivity(), 2));
                            rv_status_tanaman.setAdapter(adapter);
                        } else {
                            layout_spinner.setVisibility(View.GONE);
                            layout_no_data.setVisibility(View.VISIBLE);
                            rv_status_tanaman.setVisibility(View.GONE);
                        }
                    } else {
                        MethodUtil.getDialogWarning(response.errorBody(), requireActivity());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    MethodUtil.getDialogWarningCatch(requireActivity());
                }
            }

            @Override
            public void onFailure(Call<MyFarmCommoditiesResponse> call, Throwable t) {
                if (isRefresh) refreshLayout.finishRefreshing();
                t.printStackTrace();
                Loading.hide(requireActivity());
            }
        });
    }

    private void getMyFarmSummary(boolean isRefresh) {
        List<String> farmNameList = new ArrayList<>();
        List<String> farmNameIdList = new ArrayList<>();

        Loading.show(requireActivity());
        ApiCore.apiInterface().getMyFarmSummary(PreferenceManager.getSessionToken()).enqueue(new Callback<FarmSummaryResponse>() {
            @Override
            public void onResponse(Call<FarmSummaryResponse> call, Response<FarmSummaryResponse> response) {
                if (isRefresh) refreshLayout.finishRefreshing();
                Loading.hide(requireActivity());
                try {
                    if (response.isSuccessful()) {
                        farmNameList.add("Pilih Lokasi Lahan");
                        farmNameIdList.add("");

                        List<Farm> farmList = Objects.requireNonNull(response.body()).getFarms();
                        for (Farm farm : farmList) {
                            farmNameList.add(farm.getName());
                            farmNameIdList.add(farm.getSite_id() + "");
                        }

                        ArrayAdapter aa = new ArrayAdapter(requireActivity(), R.layout.custom_spinner_item, farmNameList) {
                            @Override
                            public boolean isEnabled(int position) {
                                if (position == 0) {
                                    return false;
                                } else {
                                    return true;
                                }
                            }
                        };

                        aa.setDropDownViewResource(R.layout.custom_spinner_drop_item);
                        spinner_filter_tanaman.setAdapter(aa);

                        spinner_filter_tanaman.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                if (i != 0) {
                                    farmNameIdSelectedItem = farmNameIdList.get(i);
                                    getMyFarmMonitoringBySite(farmNameIdList.get(i));
                                    adapter.notifyDataSetChanged();
                                }
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {

                            }
                        });

                    } else {
                        MethodUtil.getDialogWarning(response.errorBody(), requireActivity());
                    }

                } catch (Exception e) {
                    MethodUtil.getDialogWarningCatch(requireActivity());
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

    private void getMyFarmMonitoringBySite(String site_id) {
        Loading.show(requireActivity());

        ApiCore.apiInterface().getFilterMyFarmCommodities(site_id, PreferenceManager.getSessionToken()).enqueue(new Callback<MyFarmCommoditiesResponse>() {
            @Override
            public void onResponse(Call<MyFarmCommoditiesResponse> call, Response<MyFarmCommoditiesResponse> response) {
                Loading.hide(requireActivity());
                try {
                    if (response.isSuccessful()) {
                        MyFarmCommoditiesResponse summaryResponse = response.body();
                        List<Commodity> commodityList = Objects.requireNonNull(summaryResponse).getCommodities();
                        adapter = new StatusTanamanAdapter(commodityList, requireActivity(), requireActivity());
                        rv_status_tanaman.setLayoutManager(new GridLayoutManager(requireActivity(), 2));
                        rv_status_tanaman.setAdapter(adapter);
                    } else {
                        MethodUtil.getDialogWarning(response.errorBody(), requireActivity());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    MethodUtil.getDialogWarningCatch(requireActivity());
                }
            }

            @Override
            public void onFailure(Call<MyFarmCommoditiesResponse> call, Throwable t) {
                Loading.hide(requireActivity());
                t.printStackTrace();
            }
        });
    }
}
