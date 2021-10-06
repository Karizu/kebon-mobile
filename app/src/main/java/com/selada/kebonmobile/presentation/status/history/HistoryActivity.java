package com.selada.kebonmobile.presentation.status.history;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.selada.kebonmobile.R;
import com.selada.kebonmobile.presentation.status.history.adapter.HistoryAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HistoryActivity extends AppCompatActivity {

    @BindView(R.id.rv_history)
    RecyclerView rv_history;
    @BindView(R.id.spinner_categories)
    Spinner spinner_categories;
    @BindView(R.id.spinner_tanggal)
    Spinner spinner_tanggal;

    @OnClick(R.id.btn_back)
    void onClickBack(){
        onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        ButterKnife.bind(this);

        initComponent();
    }

    private void initComponent() {
        setSpinner();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv_history.setLayoutManager(layoutManager);

        List<String> list = new ArrayList<>();
        list.add("Penanaman tanaman Kangkung");
        list.add("Tanaman Sawi gagal tanam");
        list.add("Panen tanaman Kangkung");

        HistoryAdapter historyAdapter = new HistoryAdapter(list, this, this);
        rv_history.setAdapter(historyAdapter);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    private void setSpinner(){
        String[] categories = {"Semua", "Berhasil Tanam", "Gagal Tanam", "Panen"};
        String[] tanggal = {"Tanggal", "Hari Ini", "Minggu Ini", "Bulan Ini", "Bulan Lalu dan Bulan ini", "2 Bulan Lalu Hingga Bulan Ini"};

        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.simple_spinner_item_categories, categories);
        adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item_categories);
        spinner_categories.setAdapter(adapter);

        ArrayAdapter adapter2 = new ArrayAdapter(this, R.layout.simple_spinner_item_categories, tanggal);
        adapter2.setDropDownViewResource(R.layout.simple_spinner_dropdown_item_categories);
        spinner_tanggal.setAdapter(adapter2);
    }
}