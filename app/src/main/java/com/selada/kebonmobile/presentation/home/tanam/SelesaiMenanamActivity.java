package com.selada.kebonmobile.presentation.home.tanam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.selada.kebonmobile.R;
import com.selada.kebonmobile.presentation.MainActivity;
import com.selada.kebonmobile.presentation.home.tanam.adapter.CartBottomDetailAdapter;
import com.selada.kebonmobile.presentation.home.tanam.adapter.CartDetailAdapter;
import com.selada.kebonmobile.presentation.home.tanam.adapter.ItemCompleteAdapter;
import com.selada.kebonmobile.util.Constant;
import com.selada.kebonmobile.util.PreferenceManager;

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
        PreferenceManager.setUserStatus(Constant.ALREADY_PLANT_SITE);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv_selesai_menanam.setLayoutManager(layoutManager);

        List<String> list = new ArrayList<>();
        list.add("Kangkung");
        list.add("Brokoli");

        ItemCompleteAdapter adapter = new ItemCompleteAdapter(list, this, this);
        rv_selesai_menanam.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}