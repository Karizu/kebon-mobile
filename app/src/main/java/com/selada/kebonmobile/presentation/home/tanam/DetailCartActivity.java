package com.selada.kebonmobile.presentation.home.tanam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.selada.kebonmobile.R;
import com.selada.kebonmobile.model.CommodityCart;
import com.selada.kebonmobile.model.request.Activities;
import com.selada.kebonmobile.model.request.CommodityBulkRequest;
import com.selada.kebonmobile.model.request.CommodityRequest;
import com.selada.kebonmobile.model.request.Data;
import com.selada.kebonmobile.model.response.commodity.AvailableCommodity;
import com.selada.kebonmobile.model.response.farmsummary.Farm;
import com.selada.kebonmobile.model.response.farmsummary.FarmSummaryInSite;
import com.selada.kebonmobile.model.response.farmsummary.ObjectTypeSummary;
import com.selada.kebonmobile.network.ApiCore;
import com.selada.kebonmobile.presentation.home.tanam.adapter.CartBottomDetailAdapter;
import com.selada.kebonmobile.presentation.home.tanam.adapter.CartDetailAdapter;
import com.selada.kebonmobile.presentation.home.tanam.adapter.PilihTanamanAdapter;
import com.selada.kebonmobile.util.Constant;
import com.selada.kebonmobile.util.Loading;
import com.selada.kebonmobile.util.MethodUtil;
import com.selada.kebonmobile.util.PreferenceManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailCartActivity extends AppCompatActivity {

    @BindView(R.id.rv_detail_cart)
    RecyclerView rv_detail_cart;
    @BindView(R.id.rv_detail_footer)
    RecyclerView rv_detail_footer;
    @BindView(R.id.tvFarmName)
    TextView tvFarmName;
    @BindView(R.id.tv_sisa_lubang)
    TextView tv_sisa_lubang;
    @BindView(R.id.layout_no_data)
    RelativeLayout layout_no_data;

    private CartDetailAdapter adapter;
    private int id;
    private String farmName;
    private int totalAvailable;
    private int qty = 0;
    private int minimumQty;
    private int schemeId;
    private String objectLabel = "Netpot";
    private boolean isSlotAvailable = true;
    private CartBottomDetailAdapter bottomDetailAdapter;

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
        doPostActivity();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_cart);
        ButterKnife.bind(this);
        new PreferenceManager(this);
        initComponent();

    }

    @SuppressLint("SetTextI18n")
    private void initComponent() {
        id = getIntent().getIntExtra("id", 0);
        farmName = getIntent().getStringExtra("farm_name");
        totalAvailable = getIntent().getIntExtra("total_available", 0);
        schemeId = getIntent().getIntExtra("scheme_id", 0);

        tvFarmName.setText(farmName);
        getListCommodity();

        List<CommodityCart> commodityCartList = PreferenceManager.getCommodityCart();
        rv_detail_cart.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        adapter = new CartDetailAdapter(commodityCartList, this, this);
        rv_detail_cart.setAdapter(adapter);
        tv_sisa_lubang.setText("Sisa " + objectLabel + " : " + totalAvailable);
    }

    @SuppressLint("SetTextI18n")
    public void calculateAvailableTotal(){
        int total = 0;
        List<CommodityCart> commodityCartList = PreferenceManager.getCommodityCart();
        for (CommodityCart cart: commodityCartList){
            total+=cart.getTotal_objects();
        }

        totalAvailable = totalAvailable - total;
        tv_sisa_lubang.setText("Sisa " + objectLabel + " : " + totalAvailable);
    }

    @SuppressLint("SetTextI18n")
    public void updateAvailableSlot(boolean isFromAdd, int qty){
        if (isFromAdd){
            totalAvailable = totalAvailable - qty;
        } else {
            totalAvailable = totalAvailable + qty;
        }

        tv_sisa_lubang.setText("Sisa " + objectLabel +  " : " + totalAvailable);
    }

    public int availableSlot(){
        return totalAvailable;
    }

    private void doPostActivity() {
        List<CommodityCart> commodityCartList = PreferenceManager.getCommodityCart();
        if (commodityCartList.size()>0){
            if (commodityCartList.size()>1){
                CommodityBulkRequest commodityBulkRequest = new CommodityBulkRequest();
                Data data = new Data();
                data.setActivity_type_code("initiation_activity");
                data.setPreffered_payment_account_id(1);

                List<Activities> activitiesList = new ArrayList<>();
                for (CommodityCart cart: commodityCartList) {
                    Activities activities = new Activities();
                    activities.setCommodity_id(cart.getCommodity_id());
                    activities.setScheme_id(cart.getScheme_id());
                    activities.setTotal_objects(cart.getTotal_objects());
                    activitiesList.add(activities);
                }

                commodityBulkRequest.setData(data);
                commodityBulkRequest.setActivities(activitiesList);
                postBulkActivity(commodityBulkRequest);

            } else {
                CommodityRequest commodityRequest = new CommodityRequest();
                commodityRequest.setActivity_type_code("initiation_activity");
                commodityRequest.setPreffered_payment_account_id(1);
                for (CommodityCart cart: commodityCartList) {
                    commodityRequest.setCommodity_id(cart.getCommodity_id());
                    commodityRequest.setScheme_id(cart.getScheme_id());
                    commodityRequest.setTotal_objects(cart.getTotal_objects());
                }

                postActivity(commodityRequest);
            }
        }
    }

    private void postActivity(CommodityRequest commodityRequest) {
        Loading.show(DetailCartActivity.this);
        ApiCore.apiInterface().postActivity(String.valueOf(id), commodityRequest, PreferenceManager.getSessionToken()).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Loading.hide(DetailCartActivity.this);
                try {
                    if (response.isSuccessful()){
                        String json = new Gson().toJson(PreferenceManager.getCommodityCart());
                        PreferenceManager.setCommodityCart(null);
                        PreferenceManager.setUserStatus(Constant.ALREADY_PLANT_SITE);
                        Intent intent = new Intent(DetailCartActivity.this, SelesaiMenanamActivity.class);
                        intent.putExtra("json", json);
                        startActivity(intent);
                        DetailCartActivity.this.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

                    } else {
                        MethodUtil.getDialogWarning(response.errorBody(), DetailCartActivity.this);
                    }
                } catch (Exception e){
                    e.printStackTrace();
                    MethodUtil.getDialogWarningCatch(DetailCartActivity.this);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
                Loading.hide(DetailCartActivity.this);
            }
        });
    }

    private void postBulkActivity(CommodityBulkRequest commodityBulkRequest) {
        Loading.show(DetailCartActivity.this);
        ApiCore.apiInterface().postBulkActivity(String.valueOf(id), commodityBulkRequest, PreferenceManager.getSessionToken()).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Loading.hide(DetailCartActivity.this);
                try {
                    if (response.isSuccessful()){
                        String json = new Gson().toJson(PreferenceManager.getCommodityCart());
                        PreferenceManager.setCommodityCart(null);
                        PreferenceManager.setUserStatus(Constant.ALREADY_PLANT_SITE);
                        Intent intent = new Intent(DetailCartActivity.this, SelesaiMenanamActivity.class);
                        intent.putExtra("json", json);
                        startActivity(intent);
                        DetailCartActivity.this.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

                    } else {
                        MethodUtil.getDialogWarning(response.errorBody(), DetailCartActivity.this);
                    }
                } catch (Exception e){
                    e.printStackTrace();
                    MethodUtil.getDialogWarningCatch(DetailCartActivity.this);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
                Loading.hide(DetailCartActivity.this);
            }
        });
    }

    private void getListCommodity(){
        Loading.show(DetailCartActivity.this);
        ApiCore.apiInterface().getListCommodity(String.valueOf(id), "initiation_inquiry", String.valueOf(schemeId), PreferenceManager.getSessionToken()).enqueue(new Callback<FarmSummaryInSite>() {
            @Override
            public void onResponse(Call<FarmSummaryInSite> call, Response<FarmSummaryInSite> response) {
                Loading.hide(DetailCartActivity.this);
                try {
                    if (response.isSuccessful()){
                        Farm farm = Objects.requireNonNull(response.body()).getFarm();
                        if (farm!=null){
                            if (farm.getActivitySiteRules().size()>0){
                                minimumQty = Integer.parseInt(farm.getActivitySiteRules().get(0).getRuleString().substring(0, 4));
                            } else {
                                minimumQty = 1;
                            }

                            for (ObjectTypeSummary summary: farm.getObjectTypeSummary()){
                                if (summary.getFarmableStatus()){
                                    objectLabel = summary.getLabel();
                                }
                            }

                            List<AvailableCommodity> acs = new ArrayList<>();
                            List<AvailableCommodity> availableList = farm.getAvailableCommodities();
                            List<CommodityCart> availableCommodityList = PreferenceManager.getCommodityCart();
                            for (AvailableCommodity availableCommodity: farm.getAvailableCommodities()) {
                                for (CommodityCart cart : availableCommodityList) {
                                    if (availableCommodity.getId() == cart.getCommodity_id()) {
                                        acs.add(availableCommodity);
                                        break;
                                    }
                                }
                            }

                            availableList.removeAll(acs);

                            rv_detail_footer.setLayoutManager(new LinearLayoutManager(DetailCartActivity.this, LinearLayoutManager.HORIZONTAL, false));
                            bottomDetailAdapter = new CartBottomDetailAdapter(availableList, DetailCartActivity.this, DetailCartActivity.this);
                            rv_detail_footer.setAdapter(bottomDetailAdapter);
                        }
                    }
                } catch (Exception e){
                    e.printStackTrace();
                    MethodUtil.getDialogWarningCatch(DetailCartActivity.this);
                }
            }

            @Override
            public void onFailure(Call<FarmSummaryInSite> call, Throwable t) {
                Loading.hide(DetailCartActivity.this);
                t.printStackTrace();
            }
        });
    }

    public int getMinimumQty(){
        return minimumQty;
    }

    public void addToCart(AvailableCommodity availableCommodity, int position) {
        if (PreferenceManager.getCommodityCart()!=null){
            if (minimumQty <= totalAvailable){
                if (PreferenceManager.getCommodityCart().size()>0){
                    List<CommodityCart> availableCommodityList = PreferenceManager.getCommodityCart();
                    CommodityCart cc = availableCommodityList.get(0);

                    CommodityCart commodityCart = new CommodityCart();
                    commodityCart.setCommodity_id(availableCommodity.getId());
                    commodityCart.setName(availableCommodity.getName());
                    commodityCart.setTotal_objects(minimumQty);
                    commodityCart.setScheme_id(cc.getScheme_id());
                    commodityCart.setImage(availableCommodity.getMainImage().getFullpath());
                    commodityCart.setFarm_name(farmName);
                    availableCommodityList.add(commodityCart);

                    PreferenceManager.setCommodityCart(availableCommodityList);
                    adapter.updateItem(availableCommodityList);

                    updateAvailableSlot(true, minimumQty);
                } else {
                    List<CommodityCart> availableCommodityList = new ArrayList<>();

                    CommodityCart commodityCart = new CommodityCart();
                    commodityCart.setCommodity_id(availableCommodity.getId());
                    commodityCart.setName(availableCommodity.getName());
                    commodityCart.setTotal_objects(minimumQty);
                    commodityCart.setScheme_id(schemeId);
                    commodityCart.setImage(availableCommodity.getMainImage().getFullpath());
                    commodityCart.setFarm_name(farmName);
                    availableCommodityList.add(commodityCart);

                    PreferenceManager.setCommodityCart(availableCommodityList);
                    adapter.updateItem(availableCommodityList);

                    updateAvailableSlot(true, minimumQty);
                }

                bottomDetailAdapter.updateItem(position);
            } else {
                Toast.makeText(this, "Slot yang tersisa tidak mencukupi", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void setEmptyCart(boolean isEmpty){
        if (isEmpty){
            layout_no_data.setVisibility(View.VISIBLE);
            rv_detail_cart.setVisibility(View.GONE);
        } else {
            layout_no_data.setVisibility(View.GONE);
            rv_detail_cart.setVisibility(View.VISIBLE);
        }
    }

    public boolean isSlotAvailable(){
        isSlotAvailable = minimumQty <= totalAvailable;
        return isSlotAvailable;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
}