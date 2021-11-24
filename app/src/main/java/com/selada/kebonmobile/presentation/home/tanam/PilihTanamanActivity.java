package com.selada.kebonmobile.presentation.home.tanam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.iambedant.text.OutlineTextView;
import com.selada.kebonmobile.R;
import com.selada.kebonmobile.model.CommodityCart;
import com.selada.kebonmobile.model.response.commodity.AvailableCommodity;
import com.selada.kebonmobile.model.response.errormsg.ErrorResponse;
import com.selada.kebonmobile.model.response.farmsummary.Farm;
import com.selada.kebonmobile.model.response.farmsummary.FarmSummaryInSite;
import com.selada.kebonmobile.model.response.farmsummary.FarmSummaryResponse;
import com.selada.kebonmobile.model.response.farmsummary.ObjectTypeSummary;
import com.selada.kebonmobile.network.ApiCore;
import com.selada.kebonmobile.presentation.home.tanam.adapter.CartAdapter;
import com.selada.kebonmobile.presentation.home.tanam.adapter.PilihTanamanAdapter;
import com.selada.kebonmobile.util.Loading;
import com.selada.kebonmobile.util.MethodUtil;
import com.selada.kebonmobile.util.PreferenceManager;
import com.skydoves.elasticviews.ElasticButton;
import com.skydoves.elasticviews.ElasticImageView;
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
    @BindView(R.id.tv_plant_qty)
    TextView tv_plant_qty;
    @BindView(R.id.tv_sisa_lubang)
    TextView tv_sisa_lubang;

    private int qty = 0;
    private int minimumQty = 0;
    private int id;
    private int schemeId;
    private Context appContext;
    private AvailableCommodity availableCommodity;
    private CommodityCart commodityCart;
    private PilihTanamanAdapter adapter;
    private CartAdapter cartAdapter;
    private RecyclerView rv_dialog_cart;
    private Dialog dialog;
    private TextView tv_total_jenis_tanaman;
    private TextView tv_total_tanaman;
    private String farmName;
    private Integer totalAvailable;
    private boolean isItemSelected = false;
    private String objectLabel = "Netpot";
    private Integer constantTotalAvailable = 0;

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
        if (!isItemSelected) {
            Toast.makeText(appContext, "Silahkan pilih tanaman terlebih dahulu", Toast.LENGTH_SHORT).show();
            return;
        }

        if (availableCommodity == null){
            Toast.makeText(appContext, "Silahkan tambahkan tanaman ke keranjang anda", Toast.LENGTH_SHORT).show();
            return;
        }


        qty += minimumQty;
        btn_min.setEnabled(true);
        tv_quantity.setText(String.valueOf(qty));
    }

    @OnClick(R.id.btn_min)
    void onClickMin(){
        if (!isItemSelected) {
            Toast.makeText(appContext, "Silahkan pilih tanaman terlebih dahulu", Toast.LENGTH_SHORT).show();
            return;
        }

        if (availableCommodity == null){
            Toast.makeText(appContext, "Silahkan tambahkan tanaman ke keranjang anda", Toast.LENGTH_SHORT).show();
            return;
        }

        qty -= minimumQty;
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
        if (!isItemSelected) {
            Toast.makeText(appContext, "Silahkan pilih tanaman terlebih dahulu", Toast.LENGTH_SHORT).show();
            return;
        }

        if (qty == 0){
            Toast.makeText(this, "Tambahkan jumlah terlebih dahulu", Toast.LENGTH_SHORT).show();
            return;
        }

        addToCart();
    }

    @OnClick(R.id.btn_cart_inq)
    void onClickCartInq(){
        setDialogCart();
     }

    @OnClick(R.id.btn_cart)
    void onClickCart(){
        if (PreferenceManager.getCommodityCart() == null || PreferenceManager.getCommodityCart().size() == 0){
            Toast.makeText(appContext, "Silahkan tambahkan tanaman ke keranjang anda", Toast.LENGTH_SHORT).show();
            return;
        }

        if (btn_cart_inq.getVisibility() == View.VISIBLE){
            btn_cart_inq.setVisibility(View.GONE);
        } else {
            btn_cart_inq.setVisibility(View.VISIBLE);

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilih_tanaman_activity);
        ButterKnife.bind(this);
        appContext = this;
        new PreferenceManager(this);
        PreferenceManager.setCommodityCart(null);
        initComponent();
    }

    @SuppressLint("SetTextI18n")
    private void initComponent() {
        id = getIntent().getIntExtra("id", 0);
        schemeId = getIntent().getIntExtra("scheme_id", 0);

        getListCommodity();

        setFilterTanaman();
        tv_plant_desc.setInAnimation(this, android.R.anim.slide_in_left);
        tv_plant_desc.setOutAnimation(this, android.R.anim.slide_out_right);

        btn_chat.setVisibility(View.GONE);
        btn_chat.setImageDrawable(getResources().getDrawable(R.drawable.ic_search));
        tv_title_bar.setText("Pilih Tanaman");

        //check cart available
        checkCartAvailable();
    }

    private void checkCartAvailable() {
        try {
            if (PreferenceManager.getCommodityCart() != null && PreferenceManager.getCommodityCart().size() > 0) {
                String name = PreferenceManager.getCommodityCart().get(0).getFarm_name();
                if (!farmName.equals(name)) {
                    PreferenceManager.setCommodityCart(null);
                } else {
                    btn_cart_inq.setVisibility(View.VISIBLE);
                    int totalObject = PreferenceManager.getCommodityCart().size();
                    tv_plant_qty.setText(String.valueOf(totalObject));
                }
            }

            populateCartData();
        } catch (Exception e){
            e.printStackTrace();
        }

        populateCartData();
    }

    @Override
    protected void onStart() {
        super.onStart();
        checkCartAvailable();
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkCartAvailable();
    }

    @SuppressLint("SetTextI18n")
    public void setDataTanaman(AvailableCommodity availableCommodity){
        this.availableCommodity = availableCommodity;
        int dayHarvest = 20;
        int price = 5000;
        String img = availableCommodity.getMainImage().getFullpath();
        tv_plant_name.setText(availableCommodity.getName());
        tv_plant_desc.setVisibility(View.INVISIBLE);
        tv_plant_desc.setText("Panen dalam : " + dayHarvest + " hari\n" +
                "Harga jual : " + MethodUtil.toCurrencyNumber(price) + "/kg");

        Glide.with(this)
                .load(img)
                .placeholder(R.drawable.img_plant)
                .into(img_plant);

        qty = 0;
        tv_quantity.setText(String.valueOf(qty));
    }

    @SuppressLint("SetTextI18n")
    private void populateCartData() {
        if (PreferenceManager.getCommodityCart()!=null){
            int totalObjects = PreferenceManager.getCommodityCart().size();
            int totalPotObjects = 0;
            for (CommodityCart cart: PreferenceManager.getCommodityCart()){
                totalPotObjects += cart.getTotal_objects();
            }

            tv_plant_qty.setText(String.valueOf(totalObjects));
            tv_total_jenis_tanaman.setText(String.valueOf(totalObjects));
            tv_total_tanaman.setText(String.valueOf(totalPotObjects));
            totalAvailable = constantTotalAvailable - totalPotObjects;
            tv_sisa_lubang.setText("Sisa " + objectLabel + " : " + totalAvailable);

            if (totalObjects == 0){
                btn_cart_inq.setVisibility(View.GONE);
            }
        }
    }

    private void addToCart() {
        if (qty<=totalAvailable){
            updateAvailableSlot(true, qty);
            if (PreferenceManager.getCommodityCart()!=null){
                List<CommodityCart> availableCommodityList = PreferenceManager.getCommodityCart();
                boolean isSame = false;
                int finalQuantity = qty;
                for (CommodityCart cart: availableCommodityList){
                    if (cart.getCommodity_id() == availableCommodity.getId()){
                        isSame = true;
                        finalQuantity += cart.getTotal_objects();
                        cart.setTotal_objects(finalQuantity);
                    }
                }

                if (!isSame){
                    commodityCart = new CommodityCart();
                    commodityCart.setCommodity_id(availableCommodity.getId());
                    commodityCart.setName(availableCommodity.getName());
                    commodityCart.setTotal_objects(qty);
                    commodityCart.setScheme_id(schemeId);
                    commodityCart.setImage(availableCommodity.getMainImage().getFullpath());
                    commodityCart.setFarm_name(farmName);
                    availableCommodityList.add(commodityCart);
                }

                PreferenceManager.setCommodityCart(availableCommodityList);

            } else {
                List<CommodityCart> availableCommodityList = new ArrayList<>();
                commodityCart = new CommodityCart();
                commodityCart.setCommodity_id(availableCommodity.getId());
                commodityCart.setName(availableCommodity.getName());
                commodityCart.setTotal_objects(qty);
                commodityCart.setScheme_id(schemeId);
                commodityCart.setImage(availableCommodity.getMainImage().getFullpath());
                commodityCart.setFarm_name(farmName);
                availableCommodityList.add(commodityCart);

                PreferenceManager.setCommodityCart(availableCommodityList);
            }

            btn_cart_inq.setVisibility(View.VISIBLE);
            int totalObject = PreferenceManager.getCommodityCart().size();
            tv_plant_qty.setText(String.valueOf(totalObject));

        } else {
            Toast.makeText(appContext, "Slot yang tersedia tidak mencukupi", Toast.LENGTH_SHORT).show();
        }
    }

    private void setDialogCart() {
        dialog = MethodUtil.getDialogCart(R.layout.dialog_cart, this);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(true);
        tv_total_jenis_tanaman = dialog.findViewById(R.id.tv_total_jenis_tanaman);
        tv_total_tanaman = dialog.findViewById(R.id.tv_total_tanaman);
        ElasticLayout layout_spinner = dialog.findViewById(R.id.layout_spinner);
        ElasticButton btn_lanjut = dialog.findViewById(R.id.btn_lanjut);
        LinearLayout item = dialog.findViewById(R.id.item);
        Spinner tv_farm_name = dialog.findViewById(R.id.tv_farm_name);
        rv_dialog_cart = dialog.findViewById(R.id.rv_dialog_cart);
        setListMenu(tv_farm_name);
        setRecyclerCart(rv_dialog_cart);
        layout_spinner.setOnClickListener(view -> {
            tv_farm_name.performClick();
        });
        item.setOnClickListener(view -> dialog.dismiss());
        btn_lanjut.setOnClickListener(view -> {
            dialog.dismiss();
            Log.d("TOTAL", totalAvailable+"");
            Intent intent = new Intent(this, DetailCartActivity.class);
            intent.putExtra("id", id);
            intent.putExtra("farm_name", farmName);
            intent.putExtra("scheme_id", schemeId);
            intent.putExtra("total_available", totalAvailable);
            startActivity(intent);
            this.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        });

        populateCartData();
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
        isItemSelected = true;
    }

    private void setRecyclerCart(RecyclerView rv_dialog_cart) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv_dialog_cart.setLayoutManager(layoutManager);

        List<CommodityCart> commodityCartList = PreferenceManager.getCommodityCart();

        CartAdapter cartAdapter = new CartAdapter(commodityCartList, this, this);
        rv_dialog_cart.setAdapter(cartAdapter);

    }

    private void setListMenu(Spinner spinner) {
        List<String> farmNameList = new ArrayList<>();
        List<Integer> farmNameIdList = new ArrayList<>();

        Loading.show(appContext);
        ApiCore.apiInterface().getMyFarmSummary(PreferenceManager.getSessionToken()).enqueue(new Callback<FarmSummaryResponse>() {
            @Override
            public void onResponse(Call<FarmSummaryResponse> call, Response<FarmSummaryResponse> response) {
                Loading.hide(appContext);
                try {
                    if (response.isSuccessful()){
                        List<Farm> summaryResponse = Objects.requireNonNull(response.body()).getFarms();
                        for (Farm farms: summaryResponse){
                            if (farms.getSite_id() == id) {
                                farmNameList.add(farms.getName());
                                farmNameIdList.add(farms.getSite_id());
                            }
                        }

                        ArrayAdapter aa = new ArrayAdapter(appContext, R.layout.custom_spinner_item, farmNameList);
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
                } catch (Exception e){
                    e.printStackTrace();
                    MethodUtil.getDialogWarningCatch(PilihTanamanActivity.this);
                }
            }

            @Override
            public void onFailure(Call<FarmSummaryResponse> call, Throwable t) {

            }
        });
    }

    private void getListCommodity(){
        Loading.show(appContext);
        ApiCore.apiInterface().getListCommodity(String.valueOf(id), "initiation_inquiry", String.valueOf(schemeId), PreferenceManager.getSessionToken()).enqueue(new Callback<FarmSummaryInSite>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<FarmSummaryInSite> call, Response<FarmSummaryInSite> response) {
                Loading.hide(appContext);
                try {
                    if (response.isSuccessful()){
                        Farm farm = Objects.requireNonNull(response.body()).getFarm();
                        if (farm.getActivitySiteRules().size()>0){
                            minimumQty = Integer.parseInt(farm.getActivitySiteRules().get(0).getRuleString().substring(0, 4));
                            minimumQty = minimumQty==0?1:minimumQty;
                        } else {
                            minimumQty = 1;
                        }

                        for (ObjectTypeSummary summary: farm.getObjectTypeSummary()){
                            if (summary.getFarmableStatus()){
                                objectLabel = summary.getLabel();
                            }
                        }

                        farmName = farm.getName();
                        for (ObjectTypeSummary summary: farm.getObjectTypeSummary()){
                            if (summary.getFarmableStatus()){
                                totalAvailable = summary.getTotalIdle();
                                constantTotalAvailable = summary.getTotalIdle();
                            }
                        }

                        tv_sisa_lubang.setText("Sisa " + objectLabel + " : " + totalAvailable);
                        adapter = new PilihTanamanAdapter(farm.getAvailableCommodities(), appContext, PilihTanamanActivity.this);
                        rv_tanaman.setLayoutManager(new LinearLayoutManager(appContext, LinearLayoutManager.VERTICAL, false));
                        rv_tanaman.setAdapter(adapter);
                    } else {
                        Gson gson = new Gson();
                        ErrorResponse message = gson.fromJson(Objects.requireNonNull(response.errorBody()).charStream(),ErrorResponse.class);
                        String errorMessage = message.getData().getMessage();
                        Dialog dialog = MethodUtil.getDialogCart(R.layout.dialog_warning, PilihTanamanActivity.this);
                        dialog.setCancelable(false);
                        dialog.setCanceledOnTouchOutside(false);
                        TextView desc = dialog.findViewById(R.id.tv_desc);
                        desc.setText(errorMessage);
                        ImageView btn_close = dialog.findViewById(R.id.btn_close);
                        btn_close.setOnClickListener(view -> {
                            dialog.dismiss();
                            onBackPressed();
                        });
                    }
                } catch (Exception e){
                    e.printStackTrace();
                    MethodUtil.getDialogWarningCatch(PilihTanamanActivity.this);
                }
            }

            @Override
            public void onFailure(Call<FarmSummaryInSite> call, Throwable t) {
                Loading.hide(appContext);
                t.printStackTrace();
            }
        });
    }

    public int onAdapterClickAdd(int id){
        btn_min.setEnabled(true);
        List<CommodityCart> commodityCartList = PreferenceManager.getCommodityCart();
        for (CommodityCart cart: commodityCartList){
            if (cart.getCommodity_id() == id) {
                qty = cart.getTotal_objects();
                qty+=minimumQty;
                if (minimumQty <= totalAvailable) {
                    tv_quantity.setText(String.valueOf(qty));
                    cart.setTotal_objects(qty);
                    updateAvailableSlot(true, minimumQty);
                } else {
                    Toast.makeText(appContext, "Slot yang tersisa tidak mencukupi", Toast.LENGTH_SHORT).show();
                }
            }
        }

        PreferenceManager.setCommodityCart(commodityCartList);

        return qty;
    }

    public int onAdapterClickMin(int id){
        List<CommodityCart> commodityCartList = PreferenceManager.getCommodityCart();
        boolean commodityIsEmpty = false;
        int position = 0;
        for (int i = 0; i < commodityCartList.size(); i++){
            CommodityCart cart = commodityCartList.get(i);
            if (cart.getCommodity_id() == id) {
                qty = cart.getTotal_objects();
                qty-=minimumQty;
                if (qty == 0){
                    btn_min.setEnabled(false);
                    tv_quantity.setText("0");
                    commodityIsEmpty = true;
                    position = i;
                } else {
                    tv_quantity.setText(String.valueOf(qty));
                    cart.setTotal_objects(qty);
                }
                updateAvailableSlot(false, minimumQty);
            }
        }

        // remove item cart
        if (commodityIsEmpty){
            commodityCartList.remove(position);
            cartAdapter = new CartAdapter(commodityCartList, this, this);
            rv_dialog_cart.setAdapter(cartAdapter);
            cartAdapter.notifyDataSetChanged();

            if (PreferenceManager.getCommodityCart()!=null) {
                int totalObjects = commodityCartList.size();
                int totalPotObjects = 0;
                for (CommodityCart cart : commodityCartList) {
                    totalPotObjects += cart.getTotal_objects();
                }

                tv_plant_qty.setText(String.valueOf(totalObjects));
                tv_total_jenis_tanaman.setText(String.valueOf(totalObjects));
                tv_total_tanaman.setText(String.valueOf(totalPotObjects));

                if (totalObjects == 0){
                    btn_cart_inq.setVisibility(View.GONE);
                }
            }
        }

        // hide cart layout
        PreferenceManager.setCommodityCart(commodityCartList);
        if (PreferenceManager.getCommodityCart().size() == 0){
            btn_cart_inq.setVisibility(View.GONE);
            if (dialog!=null) dialog.dismiss();
        }

        return qty;
    }

    @SuppressLint("SetTextI18n")
    public void updateAvailableSlot(boolean isFromAdd, int qty){
        if (isFromAdd){
            totalAvailable = totalAvailable - qty;
        } else {
            totalAvailable = totalAvailable + qty;
        }

        tv_sisa_lubang.setText("Sisa " + objectLabel + " : " + totalAvailable);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        PreferenceManager.setCommodityCart(null);
        this.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
}