package com.selada.kebonmobile.presentation.home.tanam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.selada.kebonmobile.R;
import com.skydoves.elasticviews.ElasticImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PilihTanamanActivity extends AppCompatActivity {

    @BindView(R.id.btn_chat)
    ElasticImageView btn_chat;
    @BindView(R.id.tv_title_bar)
    TextView tv_title_bar;
    @BindView(R.id.rv_tanaman)
    RecyclerView rv_tanaman;

    @OnClick(R.id.btn_back)
    void onClickBack(){
        onBackPressed();
    }

    @OnClick(R.id.btn_filter)
    void onClickFilter(){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilih_tanaman_activity);
        ButterKnife.bind(this);

        initComponent();

    }

    @SuppressLint("SetTextI18n")
    private void initComponent() {
        btn_chat.setImageDrawable(getResources().getDrawable(R.drawable.ic_search));
        tv_title_bar.setText("Pilih Tanaman");

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv_tanaman.setLayoutManager(layoutManager);

        List<String> list = new ArrayList<>();
        for (int i = 0; i<5; i++){
            list.add(""+i);
        }

        PilihTanamanAdapter adapter = new PilihTanamanAdapter(list, this, this);
        rv_tanaman.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        this.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
}