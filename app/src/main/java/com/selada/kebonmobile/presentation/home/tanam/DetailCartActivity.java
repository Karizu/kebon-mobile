package com.selada.kebonmobile.presentation.home.tanam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.selada.kebonmobile.R;
import com.selada.kebonmobile.presentation.home.tanam.adapter.CartBottomDetailAdapter;
import com.selada.kebonmobile.presentation.home.tanam.adapter.CartDetailAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailCartActivity extends AppCompatActivity {

    @BindView(R.id.rv_detail_cart)
    RecyclerView rv_detail_cart;
    @BindView(R.id.rv_detail_footer)
    RecyclerView rv_detail_footer;

    private CartDetailAdapter adapter;

    @OnClick(R.id.cbFarmName)
    void onClickCheckBox(){
        adapter.setCheckedItem();
    }

    @OnClick(R.id.btn_back)
    void onClickBack(){
        onBackPressed();
    }

    @OnClick(R.id.btn_proses)
    void onClickProses(){
        Intent intent = new Intent(this, SelesaiMenanamActivity.class);
        startActivity(intent);
        this.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_cart);
        ButterKnife.bind(this);

        initComponent();

    }

    private void initComponent() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv_detail_cart.setLayoutManager(layoutManager);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rv_detail_footer.setLayoutManager(layoutManager2);


        List<String> list = new ArrayList<>();
        list.add("Kangkung");
        list.add("Brokoli");

        List<String> listBottom = new ArrayList<>();
        listBottom.add("Sawi");
        listBottom.add("Bayam");
        listBottom.add("Pakcoy");

        adapter = new CartDetailAdapter(list, this, this);
        rv_detail_cart.setAdapter(adapter);
        CartBottomDetailAdapter bottomDetailAdapter = new CartBottomDetailAdapter(listBottom, this, this);
        rv_detail_footer.setAdapter(bottomDetailAdapter);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        this.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
}