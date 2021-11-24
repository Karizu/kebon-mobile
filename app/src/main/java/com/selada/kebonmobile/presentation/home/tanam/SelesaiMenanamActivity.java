package com.selada.kebonmobile.presentation.home.tanam;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.selada.kebonmobile.R;
import com.selada.kebonmobile.model.CommodityCart;
import com.selada.kebonmobile.presentation.MainActivity;
import com.selada.kebonmobile.presentation.home.tanam.adapter.ItemCompleteAdapter;
import com.selada.kebonmobile.util.PreferenceManager;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SelesaiMenanamActivity extends AppCompatActivity {

    @BindView(R.id.rv_selesai_menanam)
    RecyclerView rv_selesai_menanam;

    @OnClick(R.id.btn_selesai)
    void onClickSelesai(){
        onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selesai_menanam);
        ButterKnife.bind(this);
        new PreferenceManager(this);
        initComponent();
    }

    private void initComponent() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv_selesai_menanam.setLayoutManager(layoutManager);

        String json = getIntent().getStringExtra("json");
        Type listType = new TypeToken<ArrayList<CommodityCart>>(){}.getType();
        List<CommodityCart> commodityCartList = new Gson().fromJson(json, listType);

        ItemCompleteAdapter adapter = new ItemCompleteAdapter(commodityCartList, this, this);
        rv_selesai_menanam.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(SelesaiMenanamActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}