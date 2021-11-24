package com.selada.kebonmobile.presentation.status.history;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.leavjenn.smoothdaterangepicker.date.SmoothDateRangePickerFragment;
import com.madapps.liquid.LiquidRefreshLayout;
import com.selada.kebonmobile.R;
import com.selada.kebonmobile.model.response.ApiResponse;
import com.selada.kebonmobile.model.response.Status;
import com.selada.kebonmobile.model.response.farmactivities.Activity;
import com.selada.kebonmobile.model.response.farmactivities.DetailFarmActivityResponse;
import com.selada.kebonmobile.model.response.farmactivities.FarmActivitiesResponse;
import com.selada.kebonmobile.network.ApiCore;
import com.selada.kebonmobile.presentation.status.history.adapter.HistoryAdapter;
import com.selada.kebonmobile.util.Loading;
import com.selada.kebonmobile.util.MethodUtil;
import com.selada.kebonmobile.util.PreferenceManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryActivity extends AppCompatActivity {

    @BindView(R.id.rv_history)
    RecyclerView rv_history;
    @BindView(R.id.spinner_categories)
    Spinner spinner_categories;
    @BindView(R.id.date_picker)
    TextView date_picker;
    @BindView(R.id.layout_no_data)
    RelativeLayout layout_no_data;
    @BindView(R.id.refreshLayout)
    LiquidRefreshLayout refreshLayout;

    private String start_date = "";
    private String end_date = "";
    private String codeStatusSelected = "";

    @OnClick(R.id.date_picker)
    void onClickDatePicker(){
        SmoothDateRangePickerFragment smoothDateRangePickerFragment = SmoothDateRangePickerFragment.newInstance(
                (view, yearStart, monthStart, dayStart, yearEnd, monthEnd, dayEnd) -> {
                    // grab the date range, do what you want

                    monthStart = monthStart+1;
                    monthEnd = monthEnd+1;

                    Log.d("startDate", ""+yearStart+"-"+monthStart+"-"+dayStart);
                    Log.d("endDate", ""+yearEnd+"-"+monthEnd+"-"+dayEnd);

                    String date = String.valueOf(dayStart).length() == 1 ? "0" + dayStart : String.valueOf(dayStart);
                    String month = String.valueOf(monthStart).length() == 1 ? "0" + monthStart : String.valueOf(monthStart);
                    start_date = yearStart+"-"+month+"-"+date;

                    String dateEnd = String.valueOf(dayEnd).length() == 1 ? "0" + dayEnd : String.valueOf(dayEnd);
                    String monthEnds = String.valueOf(monthEnd).length() == 1 ? "0" + monthEnd : String.valueOf(monthEnd);
                    end_date = yearEnd+"-"+monthEnds+"-"+dateEnd;

                    getFilterActivityHistory();
                });

        smoothDateRangePickerFragment.show(getFragmentManager(), "smoothDateRangePicker");
    }

    @OnClick(R.id.btn_back)
    void onClickBack(){
        onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        ButterKnife.bind(this);
        new PreferenceManager(this);
        initComponent();
    }

    private void initComponent() {
        boolean isFromNotif = getIntent().getBooleanExtra("is_from_notif", false);
        getFilterStatus(false);
        refreshLayout.setOnRefreshListener(new LiquidRefreshLayout.OnRefreshListener() {
            @Override
            public void completeRefresh() {

            }

            @Override
            public void refreshing() {
                getFilterStatus(true);
            }
        });

        if (isFromNotif) {
            String activityId = getIntent().getStringExtra("activity_id");
            getDetailHistoryActivity(activityId);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    private void getActivityHistory(){
        Loading.show(HistoryActivity.this);
        ApiCore.apiInterface().getHistoryActivity(PreferenceManager.getSessionToken()).enqueue(new Callback<FarmActivitiesResponse>() {
            @Override
            public void onResponse(Call<FarmActivitiesResponse> call, Response<FarmActivitiesResponse> response) {
                Loading.hide(HistoryActivity.this);
                try {
                    if (response.isSuccessful()){
                        List<Activity> activityList = Objects.requireNonNull(response.body()).getActivities();

                        rv_history.setLayoutManager(new LinearLayoutManager(HistoryActivity.this, LinearLayoutManager.VERTICAL, false));
                        HistoryAdapter historyAdapter = new HistoryAdapter(activityList, HistoryActivity.this, HistoryActivity.this);
                        rv_history.setAdapter(historyAdapter);
                    } else {
                        MethodUtil.getDialogWarning(response.errorBody(), HistoryActivity.this);
                    }
                } catch (Exception e){
                    e.printStackTrace();
                    MethodUtil.getDialogWarningCatch(HistoryActivity.this);
                }
            }

            @Override
            public void onFailure(Call<FarmActivitiesResponse> call, Throwable t) {
                Loading.hide(HistoryActivity.this);
                t.printStackTrace();
            }
        });
    }

    private void getFilterStatus(boolean isEmpty){
        Loading.show(HistoryActivity.this);
        ApiCore.apiInterface().getStatus(PreferenceManager.getSessionToken()).enqueue(new Callback<ApiResponse<List<Status>>>() {
            @Override
            public void onResponse(Call<ApiResponse<List<Status>>> call, Response<ApiResponse<List<Status>>> response) {
                if (isEmpty) refreshLayout.finishRefreshing();
                Loading.hide(HistoryActivity.this);
                try {
                    if (response.isSuccessful()){
                        List<Status> statusList = Objects.requireNonNull(response.body()).getData();
                        List<String> stringListName = new ArrayList<>();
                        List<String> stringListId = new ArrayList<>();

                        stringListName.add("Semua");
                        stringListId.add("");
                        for (Status status: statusList) {
                            stringListName.add(status.getName());
                            stringListId.add(status.getCode());
                        }

                        ArrayAdapter adapter = new ArrayAdapter(HistoryActivity.this, R.layout.simple_spinner_item_categories, stringListName);
                        adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item_categories);
                        spinner_categories.setAdapter(adapter);

                        spinner_categories.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                codeStatusSelected = stringListId.get(i);
                                getFilterActivityHistory();
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {

                            }
                        });
                    } else {
                        MethodUtil.getDialogWarning(response.errorBody(), HistoryActivity.this);
                    }
                } catch (Exception e){
                    e.printStackTrace();
                    MethodUtil.getDialogWarningCatch(HistoryActivity.this);
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<List<Status>>> call, Throwable t) {
                if (isEmpty) refreshLayout.finishRefreshing();
                Loading.hide(HistoryActivity.this);
                t.printStackTrace();
            }
        });
    }

    private void getFilterActivityHistory() {
        Loading.show(HistoryActivity.this);
        ApiCore.apiInterface().filterHistoryActivity(codeStatusSelected, start_date, end_date, PreferenceManager.getSessionToken()).enqueue(new Callback<FarmActivitiesResponse>() {
            @Override
            public void onResponse(Call<FarmActivitiesResponse> call, Response<FarmActivitiesResponse> response) {
                Loading.hide(HistoryActivity.this);
                try {
                    if (response.isSuccessful()){
                        List<Activity> activityList = Objects.requireNonNull(response.body()).getActivities();

                        if (activityList.size()>0){
                            layout_no_data.setVisibility(View.GONE);
                            rv_history.setVisibility(View.VISIBLE);
                            rv_history.setLayoutManager(new LinearLayoutManager(HistoryActivity.this, LinearLayoutManager.VERTICAL, false));
                            HistoryAdapter historyAdapter = new HistoryAdapter(activityList, HistoryActivity.this, HistoryActivity.this);
                            rv_history.setAdapter(historyAdapter);
                        } else {
                            layout_no_data.setVisibility(View.VISIBLE);
                            rv_history.setVisibility(View.GONE);
                        }
                    } else {
                        MethodUtil.getDialogWarning(response.errorBody(), HistoryActivity.this);
                    }
                } catch (Exception e){
                    e.printStackTrace();
                    MethodUtil.getDialogWarningCatch(HistoryActivity.this);
                }
            }

            @Override
            public void onFailure(Call<FarmActivitiesResponse> call, Throwable t) {
                t.printStackTrace();
                Loading.hide(HistoryActivity.this);
            }
        });
    }

    private void getDetailHistoryActivity(String activityId){
        Loading.show(HistoryActivity.this);

        ApiCore.apiInterface().getDetailHistoryActivity(activityId, PreferenceManager.getSessionToken()).enqueue(new Callback<DetailFarmActivityResponse>() {
            @Override
            public void onResponse(Call<DetailFarmActivityResponse> call, Response<DetailFarmActivityResponse> response) {
                Loading.hide(HistoryActivity.this);
                try {
                    if (response.isSuccessful()){
                        Activity act = Objects.requireNonNull(response.body()).getActivity();
                        if (act!=null){
                            Intent intent = new Intent(HistoryActivity.this, DetailHistory.class);
                            intent.putExtra("json", new Gson().toJson(act));
                            startActivity(intent);
                            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                        } else {
                            onBackPressed();
                            Toast.makeText(HistoryActivity.this, "Response Code: Is Empty", Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        MethodUtil.getDialogWarning(response.errorBody(), HistoryActivity.this);
                    }
                } catch (Exception e){
                    e.printStackTrace();
                    MethodUtil.getDialogWarningCatch(HistoryActivity.this);
                }
            }

            @Override
            public void onFailure(Call<DetailFarmActivityResponse> call, Throwable t) {

            }
        });
    }
}