package com.selada.kebonmobile.presentation.home.tanam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.iambedant.text.OutlineTextView;
import com.selada.kebonmobile.R;
import com.selada.kebonmobile.presentation.akun.DetailAkunActivity;
import com.selada.kebonmobile.util.MethodUtil;
import com.skydoves.elasticviews.ElasticButton;
import com.skydoves.elasticviews.ElasticImageView;
import com.skydoves.elasticviews.ElasticLayout;

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
    @BindView(R.id.spinner_filter_tanaman)
    Spinner spinner_filter_tanaman;
    @BindView(R.id.btn_add)
    ElasticButton btn_add;
    @BindView(R.id.btn_min)
    ElasticButton btn_min;
    @BindView(R.id.tv_quantity)
    TextView tv_quantity;
    @BindView(R.id.tv_plant_desc)
    TextSwitcher tv_plant_desc;
    @BindView(R.id.img_plant)
    ImageView img_plant;
    @BindView(R.id.tv_plant_name)
    OutlineTextView tv_plant_name;
    @BindView(R.id.btn_lihat_keuntungan)
    ElasticButton btn_lihat_keuntungan;
    @BindView(R.id.btn_tambah_keranjang)
    ElasticButton btn_tambah_keranjang;
    @BindView(R.id.btn_cart_inq)
    ElasticLayout btn_cart_inq;

    private int qty = 0;

    @OnClick(R.id.btn_back)
    void onClickBack(){
        onBackPressed();
    }

    @OnClick(R.id.btn_filter)
    void onClickFilter(){
        spinner_filter_tanaman.performClick();
    }

    @OnClick(R.id.btn_add)
    void onClickAdd(){
        qty++;
        btn_min.setEnabled(true);
        tv_quantity.setText(String.valueOf(qty));
    }

    @OnClick(R.id.btn_min)
    void onClickMin(){
        qty--;
        if (qty == 0){
            btn_min.setEnabled(false);
            tv_quantity.setText("0");
        } else {
            tv_quantity.setText(String.valueOf(qty));
        }
    }

    @OnClick(R.id.btn_lihat_keuntungan)
    void onClickLihatKeuntungan(){
        Intent intent = new Intent(this, KeuntunganTanamanActivity.class);
        startActivity(intent);
        this.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @OnClick(R.id.btn_tambah_keranjang)
    void onClickTambahKeranjang(){
        if (qty == 0){
            Toast.makeText(this, "Tambahkan jumlah terlebih dahulu", Toast.LENGTH_SHORT).show();
        } else {
            btn_cart_inq.setVisibility(View.VISIBLE);
        }
    }

    @OnClick(R.id.btn_cart_inq)
    void onClickCartInq(){
        Dialog dialog = MethodUtil.getDialogCart(R.layout.dialog_cart, this);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(true);
        TextView tv_total_jenis_tanaman = dialog.findViewById(R.id.tv_total_jenis_tanaman);
        TextView tv_total_tanaman = dialog.findViewById(R.id.tv_total_tanaman);
        ElasticLayout layout_spinner = dialog.findViewById(R.id.layout_spinner);
        ElasticButton btn_lanjut = dialog.findViewById(R.id.btn_lanjut);
        Spinner tv_farm_name = dialog.findViewById(R.id.tv_farm_name);
        RecyclerView rv_dialog_cart = dialog.findViewById(R.id.rv_dialog_cart);
        setListMenu(tv_farm_name);
        setRecyclerCart(rv_dialog_cart);
        layout_spinner.setOnClickListener(view -> {
            tv_farm_name.performClick();
        });
        btn_lanjut.setOnClickListener(view -> {
            Intent intent = new Intent(this, DetailCartActivity.class);
            startActivity(intent);
            this.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        });
    }

    @OnClick(R.id.btn_cart)
    void onClickCart(){

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
        setFilterTanaman();
        tv_plant_desc.setInAnimation(this, android.R.anim.slide_in_left);
        tv_plant_desc.setOutAnimation(this, android.R.anim.slide_out_right);

        btn_chat.setImageDrawable(getResources().getDrawable(R.drawable.ic_search));
        tv_title_bar.setText("Pilih Tanaman");

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv_tanaman.setLayoutManager(layoutManager);

        List<String> list = new ArrayList<>();
        list.add("Kangkung");
        list.add("Bayam");
        list.add("Pakcoy");
        list.add("Brokoli");

        PilihTanamanAdapter adapter = new PilihTanamanAdapter(list, this, this);
        rv_tanaman.setAdapter(adapter);
    }

    @SuppressLint("SetTextI18n")
    public void setDataTanaman(String plantName, String dayHarvest, String price, String img){
        tv_plant_name.setText(plantName);
        tv_plant_desc.setText("Panen dalam : " + dayHarvest + " hari\n" +
                "Harga jual : " + MethodUtil.toCurrencyNumber(Integer.parseInt(price)) + "/kg");

        Glide.with(this)
                .load(img)
                .placeholder(R.drawable.img_plant)
                .into(img_plant);
    }

    private void setFilterTanaman() {
        String[] country = { "A ~ Z", "Z ~ A", "High Demand", "Low Demand", "Most Planted"};
        ArrayAdapter aa = new ArrayAdapter(this,R.layout.custom_spinner_filter_item, country);
        aa.setDropDownViewResource(R.layout.custom_spinner_drop_filter_item);
        spinner_filter_tanaman.setAdapter(aa);
    }

    public void setEnableComponent(){
        img_plant.setVisibility(View.VISIBLE);
        tv_plant_name.setVisibility(View.VISIBLE);
        btn_lihat_keuntungan.setEnabled(true);
        btn_tambah_keranjang.setEnabled(true);
        btn_add.setEnabled(true);
        btn_min.setEnabled(true);
    }

    private void setRecyclerCart(RecyclerView rv_dialog_cart) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv_dialog_cart.setLayoutManager(layoutManager);

        List<String> list = new ArrayList<>();
        list.add("Kangkung");
        list.add("Bayam");
        CartAdapter cartAdapter = new CartAdapter(list, this, this);
        rv_dialog_cart.setAdapter(cartAdapter);

    }

    private void setListMenu(Spinner spinner) {
        String[] country = { "Pilih Lahan", "Depari Farm", "Berastagi Farm", "Green HOS"};
        ArrayAdapter aa = new ArrayAdapter(this,R.layout.custom_spinner_item, country);
        aa.setDropDownViewResource(R.layout.custom_spinner_drop_item);
        spinner.setAdapter(aa);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i > 0){

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        this.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
}