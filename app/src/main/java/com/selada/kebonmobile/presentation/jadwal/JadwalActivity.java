package com.selada.kebonmobile.presentation.jadwal;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.dewinjm.monthyearpicker.MonthYearPickerDialog;
import com.github.dewinjm.monthyearpicker.MonthYearPickerDialogFragment;
import com.selada.kebonmobile.R;
import com.selada.kebonmobile.model.CalendarModel;
import com.selada.kebonmobile.model.response.calendar.Calendars;
import com.selada.kebonmobile.model.response.calendar.HarverstCalendarResponse;
import com.selada.kebonmobile.model.response.filtercalendar.Commodity;
import com.selada.kebonmobile.model.response.filtercalendar.CommodityNSitesResponse;
import com.selada.kebonmobile.model.response.filtercalendar.Site;
import com.selada.kebonmobile.network.ApiCore;
import com.selada.kebonmobile.util.CalendarAdapter;
import com.selada.kebonmobile.util.CustomBottomSheetDialog;
import com.selada.kebonmobile.util.Loading;
import com.selada.kebonmobile.util.MethodUtil;
import com.selada.kebonmobile.util.PreferenceManager;
import com.skydoves.elasticviews.ElasticButton;

import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JadwalActivity extends AppCompatActivity implements CustomBottomSheetDialog.BottomSheetListener{

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.month)
    TextView month;
    @BindView(R.id.year)
    TextView year;

    private int DAYS_COUNT = 42;
    private List<CalendarModel> calendarList;
    private Calendar calendar;
    private int tahun = -1;
    private int monthOfYear = -1;
    private CalendarAdapter calendarAdapter;
    private Calendar calendarCompare;
    private Integer siteId = 0;
    private Integer commodityId = 0;
    private String startDate = "";
    private String endDate = "";
    private Dialog dialog;
    private LinearLayout layout;

    @OnClick(R.id.month)
    void onClickMonth(){
        showMonthYearPicker();
    }

    @OnClick(R.id.year)
    void onClickYear(){
        showMonthYearPicker();
    }

    @OnClick(R.id.img_previous)
    void onClickPrevious(){
        setPreviousMonth();
    }

    @OnClick(R.id.img_next)
    void onClickNext(){
        setNextMonth();
    }

    @OnClick(R.id.btn_filter_today)
    void onClickToday(){
        monthOfYear = -1;
        tahun = -1;
        initComponent();
    }

    @OnClick(R.id.btn_filter)
    void onClickFilter(){
        if (dialog!=null){
            dialog.show();
        } else {
            showDialogFilter();
        }
    }

    private void showDialogFilter() {
        Loading.show(JadwalActivity.this);

        dialog = MethodUtil.getDialogFilter(R.layout.dialog_filter_event, this);
        RecyclerView rvFilterLahan = dialog.findViewById(R.id.rv_filter_lahan);
        RecyclerView rvFilterTanaman = dialog.findViewById(R.id.rv_filter_tanaman);
        layout = dialog.findViewById(R.id.dialog);
        ElasticButton btn_atur_ulang = dialog.findViewById(R.id.btn_atur_ulang);
        ElasticButton btn_pakai = dialog.findViewById(R.id.btn_pakai);

        layout.setOnClickListener(view -> dialog.hide());
        rvFilterLahan.setLayoutManager(new GridLayoutManager(this, 2));
        rvFilterTanaman.setLayoutManager(new GridLayoutManager(this, 2));

        btn_atur_ulang.setOnClickListener(view -> {
            if (siteId==0 && commodityId==0){

            } else {
                siteId = 0;
                commodityId = 0;
                initComponent();
                dialog.dismiss();
            }
        });

        btn_pakai.setOnClickListener(view -> {
            if (siteId==0 && commodityId==0){
                Toast.makeText(JadwalActivity.this, "Silahkan pilih item untuk filter data", Toast.LENGTH_SHORT).show();
            } else {
                loadCalendar(true);
                dialog.hide();
            }
        });

        setListFilterData(rvFilterLahan, rvFilterTanaman);
    }

    @OnClick(R.id.btn_back)
    void onClickBack(){
        onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwal);
        ButterKnife.bind(this);

        initComponent();
    }

    @SuppressLint("SetTextI18n")
    private void loadCalendar(boolean isFromFilter) {
        if (this.tahun != -1 && this.monthOfYear != -1) {
            this.calendar.set(2, this.monthOfYear);
            this.calendar.set(1, this.tahun);
        } else {
            this.tahun = this.calendar.get(1);
            this.monthOfYear = this.calendar.get(2);
        }

        SimpleDateFormat sdf = new SimpleDateFormat("MMMM,yyyy", new Locale("in", "ID"));
        String[] dateToday = sdf.format(calendar.getTime()).split(",");
        month.setText(dateToday[0]);
        year.setText(dateToday[0] + " " +dateToday[1]);

        calendarCompare = Calendar.getInstance();
        calendarCompare.set(Calendar.MONTH, monthOfYear);
        calendarCompare.set(Calendar.YEAR, tahun);

        calendar.set(Calendar.DAY_OF_MONTH, 1);
        int monthBeginningCell = calendar.get(Calendar.DAY_OF_WEEK) - 1;

        calendar.add(Calendar.DAY_OF_MONTH,-monthBeginningCell);

        Log.d("date", calendarCompare.getFirstDayOfWeek()+"");

        try {
            String date = calendar.get(Calendar.DATE) + "";
            String month = String.valueOf(calendar.get(Calendar.MONTH) + 1);
            String year = calendar.get(Calendar.YEAR) + "";

            date = date.length() == 1 ? "0" + date : date;
            month = month.length() == 1 ? "0" + month : month;
            startDate = year+"-"+month+"-"+date;

            Date dt;
            dt = new SimpleDateFormat("yyyy-MM-dd", new Locale("id")).parse(startDate);
            Calendar c = Calendar.getInstance();
            c.setTime(Objects.requireNonNull(dt));
            c.add(Calendar.DATE, DAYS_COUNT-1);
            dt = c.getTime();

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", new Locale("id", "ID"));
            endDate = dateFormat.format(dt);

            Log.d("rangeDate", startDate + " - " + endDate);

            if (isFromFilter){
                filterHarvestCalendar();
            } else {
                getHarvestCalendar(startDate, endDate);
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void setPreviousMonth(){
        tahun = calendar.get(Calendar.YEAR);
        monthOfYear = calendar.get(Calendar.MONTH)-2;
        Log.d("tahun", String.valueOf(tahun));
        Log.d("monthOfYear", String.valueOf(monthOfYear));
        loadCalendar(false);
    }

    private void setNextMonth(){
        tahun = calendar.get(Calendar.YEAR);
        monthOfYear = calendar.get(Calendar.MONTH);
        loadCalendar(false);
    }

    private final void showMonthYearPicker() {
        MonthYearPickerDialogFragment dialogFragment = MonthYearPickerDialogFragment.getInstance(this.calendar.get(2), this.calendar.get(1));
        dialogFragment.show(this.getSupportFragmentManager(), (String)null);
        dialogFragment.setOnDateSetListener((MonthYearPickerDialog.OnDateSetListener)((year, month) -> {
            tahun = year;
            monthOfYear = month;
            loadCalendar(false);
        }));
    }

    private void initComponent() {
        calendarList = new ArrayList<>();
        calendar = Calendar.getInstance();
        recyclerView.setLayoutManager(new GridLayoutManager(this, 7));
        calendarAdapter = new CalendarAdapter(calendarList, this, this);
        recyclerView.setAdapter(calendarAdapter);

        loadCalendar(false);
    }

    private void getHarvestCalendar(String startDate, String endDate){
        Loading.show(JadwalActivity.this);
        ApiCore.apiInterface().getHarvestCalendar(startDate, endDate, PreferenceManager.getSessionToken()).enqueue(new Callback<HarverstCalendarResponse>() {
            @Override
            public void onResponse(Call<HarverstCalendarResponse> call, Response<HarverstCalendarResponse> response) {
                Loading.hide(JadwalActivity.this);
                try {
                    if (response.isSuccessful()){
                        List<Calendars> calendarsList = response.body().getCalendars();
                        List<CalendarModel> cells = new ArrayList<>();
                        for (Calendars calendars: calendarsList){
                            if(!calendars.getCommodityIds().equals("")){
                                cells.add(new CalendarModel(
                                        calendar.get(Calendar.DATE),
                                        calendar.get(Calendar.MONTH),
                                        calendar.get(Calendar.YEAR),
                                        calendarCompare,
                                        "event",
                                        calendars
                                ));
                            } else {
                                cells.add( new CalendarModel(
                                        calendar.get(Calendar.DATE),
                                        calendar.get(Calendar.MONTH),
                                        calendar.get(Calendar.YEAR),
                                        calendarCompare,
                                        null,
                                        null
                                ));
                            }
                            calendar.add(Calendar.DAY_OF_MONTH, 1);
                        }

                        calendarList.clear();
                        calendarList.addAll(cells);
                        calendarAdapter.notifyDataSetChanged();
                    } else {
                        MethodUtil.getDialogWarning(response.errorBody(), JadwalActivity.this);
                    }
                } catch (Exception e){
                    e.printStackTrace();
                    MethodUtil.getDialogWarningCatch(JadwalActivity.this);
                }
            }

            @Override
            public void onFailure(Call<HarverstCalendarResponse> call, Throwable t) {

            }
        });
    }

    private void setListFilterData(RecyclerView rvFilterLahan, RecyclerView rvFilterTanaman) {
        ApiCore.apiInterface().getCommodityNSitesResponse(PreferenceManager.getSessionToken()).enqueue(new Callback<CommodityNSitesResponse>() {
            @Override
            public void onResponse(Call<CommodityNSitesResponse> call, Response<CommodityNSitesResponse> response) {
                Loading.hide(JadwalActivity.this);
                try {
                    if (response.isSuccessful()){
                        List<Site> siteList = Objects.requireNonNull(response.body()).getSites();
                        List<Commodity> commodityList = response.body().getCommodities();
                        FilterAdapter adapter = new FilterAdapter(siteList, JadwalActivity.this, JadwalActivity.this);
                        rvFilterLahan.setAdapter(adapter);

                        FilterCommodityAdapter adapter2 = new FilterCommodityAdapter(commodityList, JadwalActivity.this, JadwalActivity.this);
                        rvFilterTanaman.setAdapter(adapter2);
                    } else {
                        MethodUtil.getDialogWarning(response.errorBody(), JadwalActivity.this);
                    }
                } catch (Exception e){
                    e.printStackTrace();
                    MethodUtil.getDialogWarningCatch(JadwalActivity.this);
                }
            }

            @Override
            public void onFailure(Call<CommodityNSitesResponse> call, Throwable t) {
                t.printStackTrace();
                Loading.hide(JadwalActivity.this);
            }
        });
    }

    private void filterHarvestCalendar() {
        String siteId = this.siteId==0?"":this.siteId+"";
        String commodityId = this.commodityId==0?"":this.commodityId+"";

        Loading.show(JadwalActivity.this);
        ApiCore.apiInterface().filterHarvestCalendar(siteId, commodityId, startDate, endDate, PreferenceManager.getSessionToken()).enqueue(new Callback<HarverstCalendarResponse>() {
            @Override
            public void onResponse(Call<HarverstCalendarResponse> call, Response<HarverstCalendarResponse> response) {
                Loading.hide(JadwalActivity.this);
                try {
                    if (response.isSuccessful()){
                        List<Calendars> calendarsList = Objects.requireNonNull(response.body()).getCalendars();
                        List<CalendarModel> cells = new ArrayList<>();
                        for (Calendars calendars: calendarsList){
                            if(!calendars.getCommodityIds().equals("")){
                                cells.add(new CalendarModel(
                                        calendar.get(Calendar.DATE),
                                        calendar.get(Calendar.MONTH),
                                        calendar.get(Calendar.YEAR),
                                        calendarCompare,
                                        "event",
                                        calendars
                                ));
                            } else {
                                cells.add( new CalendarModel(
                                        calendar.get(Calendar.DATE),
                                        calendar.get(Calendar.MONTH),
                                        calendar.get(Calendar.YEAR),
                                        calendarCompare,
                                        null,
                                        null
                                ));
                            }
                            calendar.add(Calendar.DAY_OF_MONTH, 1);
                        }

                        calendarList.clear();
                        calendarList.addAll(cells);
                        calendarAdapter.notifyDataSetChanged();
                    } else {
                        MethodUtil.getDialogWarning(response.errorBody(), JadwalActivity.this);
                    }
                } catch (Exception e){
                    e.printStackTrace();
                    MethodUtil.getDialogWarningCatch(JadwalActivity.this);
                }
            }

            @Override
            public void onFailure(Call<HarverstCalendarResponse> call, Throwable t) {
                Loading.hide(JadwalActivity.this);
                t.printStackTrace();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @Override
    public void onButtonClicked(String text) {

    }

    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }

    public void setCommodityId(Integer commodityId) {
        this.commodityId = commodityId;
    }

    public Integer getSiteId() {
        return siteId;
    }

    public Integer getCommodityId() {
        return commodityId;
    }
}