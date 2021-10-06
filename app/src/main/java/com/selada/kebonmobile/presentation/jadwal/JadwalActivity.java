package com.selada.kebonmobile.presentation.jadwal;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.dewinjm.monthyearpicker.MonthYearPickerDialog;
import com.github.dewinjm.monthyearpicker.MonthYearPickerDialogFragment;
import com.riontech.calendar.CustomCalendar;
import com.riontech.calendar.dao.EventData;
import com.riontech.calendar.dao.dataAboutDate;
import com.selada.kebonmobile.R;
import com.selada.kebonmobile.model.CalendarModel;
import com.selada.kebonmobile.util.CalendarAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class JadwalActivity extends AppCompatActivity {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.month)
    TextView month;
    @BindView(R.id.year)
    TextView year;

    private CustomCalendar customCalendar;
    private int DAYS_COUNT = 42;
    private List<CalendarModel> calendarList;
    private Calendar calendar;
    private int tahun = -1;
    private int monthOfYear = -1;
    private CalendarAdapter calendarAdapter;

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

    }

    @OnClick(R.id.btn_filter)
    void onClickFilter(){

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
        loadCalendar();
    }

    @SuppressLint("SetTextI18n")
    private void loadCalendar() {
        List<CalendarModel> cells = new ArrayList<>();
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

        Calendar calendarCompare = Calendar.getInstance();
        calendarCompare.set(Calendar.MONTH, monthOfYear);
        calendarCompare.set(Calendar.YEAR, tahun);

        calendar.set(Calendar.DAY_OF_MONTH, 1);
        int monthBeginningCell = calendar.get(Calendar.DAY_OF_WEEK) - 1;

        calendar.add(Calendar.DAY_OF_MONTH,-monthBeginningCell);

        sdf = new SimpleDateFormat("dd-MM-yyyy", new Locale("in", "ID"));

        while (cells.size() < DAYS_COUNT) {
            if(sdf.format(calendar.getTime()).equals("06-10-2021")){
                cells.add(new CalendarModel(
                        calendar.get(Calendar.DATE),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.YEAR),
                        calendarCompare,
                        "event"
                ));
            } else {
                cells.add( new CalendarModel(
                        calendar.get(Calendar.DATE),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.YEAR),
                        calendarCompare,
                        null
                ));
            }
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }

        calendarList.clear();
        calendarList.addAll(cells);
        calendarAdapter.notifyDataSetChanged();
    }

    private void setPreviousMonth(){
        tahun = calendar.get(Calendar.YEAR);
        monthOfYear = calendar.get(Calendar.MONTH)-2;
        Log.d("tahun", String.valueOf(tahun));
        Log.d("monthOfYear", String.valueOf(monthOfYear));
        loadCalendar();
    }

    private void setNextMonth(){
        tahun = calendar.get(Calendar.YEAR);
        monthOfYear = calendar.get(Calendar.MONTH);
        loadCalendar();
    }

    private final void showMonthYearPicker() {
        MonthYearPickerDialogFragment dialogFragment = MonthYearPickerDialogFragment.getInstance(this.calendar.get(2), this.calendar.get(1));
        dialogFragment.show(this.getSupportFragmentManager(), (String)null);
        dialogFragment.setOnDateSetListener((MonthYearPickerDialog.OnDateSetListener)((year, month) -> {
            tahun = year;
            monthOfYear = month;
            loadCalendar();
        }));
    }

    private void initComponent() {
        calendarList = new ArrayList<>();
        calendar = Calendar.getInstance();
        recyclerView.setLayoutManager(new GridLayoutManager(this, 7));
        calendarAdapter = new CalendarAdapter(calendarList, this, this);
        recyclerView.setAdapter(calendarAdapter);

//        customCalendar = (CustomCalendar) findViewById(R.id.customCalendar);
//
//        String[] arr = {"2021-10-10", "2021-10-11", "2021-10-15", "2021-10-16", "2021-10-25"};
//        for (int i = 0; i < 5; i++) {
//            int eventCount = 3;
//            customCalendar.addAnEvent(arr[i], eventCount, getEventDataList(eventCount));
//        }
    }

    private ArrayList<EventData> getEventDataList(int eventCount) {
        ArrayList<EventData> eventData = new ArrayList<>();
        EventData data = new EventData();

        ArrayList<dataAboutDate> dataAboutDateList = new ArrayList<>();
        dataAboutDate aboutDate = new dataAboutDate();
        aboutDate.setTitle("Panen");
        aboutDate.setSubject("Tanaman anda sudah panen hari ini");
        dataAboutDateList.add(aboutDate);

        data.setSection("Panen Section");
        data.setData(dataAboutDateList);
        eventData.add(data);

        return eventData;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
}