package com.selada.kebonmobile.presentation.status.tab.tanaman;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.gson.Gson;
import com.iambedant.text.OutlineTextView;
import com.madapps.liquid.LiquidRefreshLayout;
import com.selada.kebonmobile.R;
import com.selada.kebonmobile.model.response.ApiResponse;
import com.selada.kebonmobile.model.response.commoditymonitoring.CommodityMonitoringResponse;
import com.selada.kebonmobile.model.response.commoditymonitoring.ConnectedComponent;
import com.selada.kebonmobile.model.response.commoditymonitoring.CurrentDevelopmentDetail;
import com.selada.kebonmobile.model.response.commoditymonitoring.CurrentSite;
import com.selada.kebonmobile.model.response.commoditymonitoring.Summary;
import com.selada.kebonmobile.model.response.socket.SocketDataResponse;
import com.selada.kebonmobile.network.ApiCore;
import com.selada.kebonmobile.presentation.jadwal.JadwalActivity;
import com.selada.kebonmobile.presentation.status.tab.adapter.DetailStatusTanamanAdapter;
import com.selada.kebonmobile.presentation.status.tab.adapter.PlantActivityAdapter;
import com.selada.kebonmobile.presentation.status.tab.adapter.SensorMonitoringAdapter;
import com.selada.kebonmobile.util.Loading;
import com.selada.kebonmobile.util.MethodUtil;
import com.selada.kebonmobile.util.PreferenceManager;
import com.skydoves.elasticviews.ElasticButton;
import com.skydoves.elasticviews.ElasticImageView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.socket.client.IO;
import io.socket.client.Manager;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.selada.kebonmobile.util.MethodUtil.formatDateAndTime;

public class DetailStatusTanamanActivity extends AppCompatActivity {

    @BindView(R.id.text_title)
    TextView text_title;
    @BindView(R.id.img_plant)
    ElasticImageView img_plant;
    @BindView(R.id.tv_plant_name)
    OutlineTextView tv_plant_name;
    @BindView(R.id.rv_tanaman_site)
    RecyclerView rv_tanaman_site;
    @BindView(R.id.tv_jumlah_lubang)
    TextView tv_jumlah_lubang;
    @BindView(R.id.tv_usia_panen)
    TextView tv_usia_panen;
    @BindView(R.id.tv_panen_terdekat)
    TextView tv_panen_terdekat;
    @BindView(R.id.tv_site_name)
    TextView tv_site_name;

    @BindView(R.id.site)
    TextView label_site;
    @BindView(R.id.rv_monitoring)
    RecyclerView rv_monitoring;

    @BindView(R.id.rv_plant_activity)
    RecyclerView rv_plant_activity;
    @BindView(R.id.btn_reset_filter)
    ElasticButton btn_reset_filter;

    @BindView(R.id.refreshLayout)
    LiquidRefreshLayout refreshLayout;

    private int siteId;
    private Socket socket;
    private int RECONNECTION_ATTEMPT = 10;
    private long CONNECTION_TIMEOUT = 30000;
    private static final String[] TRANSPORTS = {
            "websocket"
    };
    private CommodityMonitoringResponse commoditiesResponse;
    private List<CurrentSite> currentSiteList;
    private Summary summary;
    private boolean isAlreadyOpenReminder = false;

    @OnClick(R.id.btn_reset_filter)
    void onClickResetFilter(){
        btn_reset_filter.setVisibility(View.GONE);
        setGeneralSummary();
    }

    @OnClick(R.id.btn_lihat_grafik)
    void onClickLihatGrafik(){

    }

    @OnClick(R.id.btn_jadwal)
    void onClickJadwal(){
        Intent intent = new Intent(this, JadwalActivity.class);
        startActivity(intent);
        this.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @OnClick(R.id.btn_share)
    void onClickShare(){
        showDialogShare();
    }

    @OnClick(R.id.btn_back)
    void onClickBack(){
        onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_status_tanaman);
        ButterKnife.bind(this);
        connectToSocket();

        initComponent();
    }

    @SuppressLint("SetTextI18n")
    private void initComponent() {
        String json = getIntent().getStringExtra("json");
        int siteId = getIntent().getIntExtra("site_id", 0);
        commoditiesResponse = new Gson().fromJson(json, CommodityMonitoringResponse.class);
        setGeneralSummary();

        if (siteId != 0){
            setSiteId(siteId);
        }

        refreshLayout.setOnRefreshListener(new LiquidRefreshLayout.OnRefreshListener() {
            @Override
            public void completeRefresh() {

            }

            @Override
            public void refreshing() {
                refreshLayout.finishRefreshing();
            }
        });
    }

    private void setCurrentSites(List<CurrentSite> currentSiteList){
        DetailStatusTanamanAdapter adapter = new DetailStatusTanamanAdapter(currentSiteList, this, this);
        rv_tanaman_site.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rv_tanaman_site.setAdapter(adapter);
    }

    private void setCurrentDevelopmentDetails(List<CurrentDevelopmentDetail> developmentDetailList){
        PlantActivityAdapter plantActivityAdapter = new PlantActivityAdapter(developmentDetailList, this, this);
        rv_plant_activity.setAdapter(plantActivityAdapter);
    }

    private void showDialogShare() {
        BottomSheetDialog dialog = new BottomSheetDialog(this);
        dialog.setContentView(R.layout.dialog_share);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(true);
        Objects.requireNonNull(dialog.getWindow()).getAttributes().windowAnimations = R.style.DialogThemes;
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawableResource(android.R.color.transparent);
        dialog.show();

        ElasticImageView btn_share_whatsapp = dialog.findViewById(R.id.btn_share_whatsapp);
        ElasticImageView btn_share_instagram = dialog.findViewById(R.id.btn_share_instagram);
        ElasticImageView btn_share_line = dialog.findViewById(R.id.btn_share_line);
        ElasticImageView btn_share_bluetooth = dialog.findViewById(R.id.btn_share_bluetooth);
        ElasticImageView btn_share_twitter = dialog.findViewById(R.id.btn_share_twitter);

        btn_share_whatsapp.setOnClickListener(view -> {
            Intent intent = new Intent(android.content.Intent.ACTION_SEND);
            String shareBody = "Here is the share content body";
            intent.setType("text/plain");
            intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "subject");
            intent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
            intent.setPackage("com.whatsapp");
            startActivity(Intent.createChooser(intent, "Share"));
        });

        btn_share_instagram.setOnClickListener(view -> {
            Intent intent = new Intent(android.content.Intent.ACTION_SEND);
            String shareBody = "Here is the share content body";
            intent.setType("text/plain");
            intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "subject");
            intent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
            intent.setPackage("com.instagram.android");
            startActivity(Intent.createChooser(intent, "Share"));
        });

        btn_share_line.setOnClickListener(view -> {
            Intent intent = new Intent(android.content.Intent.ACTION_SEND);
            String shareBody = "Here is the share content body";
            intent.setType("text/plain");
            intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "subject");
            intent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
            intent.setPackage("jp.naver.line.android");
            startActivity(Intent.createChooser(intent, "Share"));
        });

        btn_share_bluetooth.setOnClickListener(view -> {
            Intent intent = new Intent(android.content.Intent.ACTION_SEND);
            String shareBody = "Here is the share content body";
            intent.setType("text/plain");
            intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "subject");
            intent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
            intent.setPackage("com.android.bluetooth");
            startActivity(Intent.createChooser(intent, "Share"));
        });

        btn_share_twitter.setOnClickListener(view -> {
            Intent intent = new Intent(android.content.Intent.ACTION_SEND);
            String shareBody = "Here is the share content body";
            intent.setType("text/plain");
            intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "subject");
            intent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
            intent.setPackage("com.twitter.android");
            startActivity(Intent.createChooser(intent, "Share"));
        });
    }

    @SuppressLint("SetTextI18n")
    private void setGeneralSummary(){
        tv_plant_name.setText(commoditiesResponse.getCommodity().getName());
        Glide.with(this)
                .load(commoditiesResponse.getCommodity().getMainImage().getFullpath())
                .placeholder(R.drawable.img_plant)
                .into(img_plant);
        currentSiteList = commoditiesResponse.getCurrentSites();
        summary = commoditiesResponse.getSummary();
        setCurrentSites(commoditiesResponse.getCurrentSites());
        setCurrentDevelopmentDetails(commoditiesResponse.getCurrentDevelopmentDetails());
        setDataMonitoring(commoditiesResponse.getConnectedComponents());

        DetailStatusTanamanAdapter adapter = new DetailStatusTanamanAdapter(currentSiteList, this, this);
        rv_tanaman_site.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rv_tanaman_site.setAdapter(adapter);

        label_site.setVisibility(View.GONE);
        tv_site_name.setVisibility(View.GONE);

        //call api
        String[] dateTime = formatDateAndTime(summary.getNearestHarvestStartDate());
        tv_jumlah_lubang.setText(summary.getObjectCount() + " " + summary.getObject_type_label());
        String age;
        if (summary.getNewestAge().equals(summary.getOldestAge())) {
            age = summary.getNewestAge() + " Hari";
        } else {
            age = summary.getNewestAge() + " - " + summary.getOldestAge() + " Hari";
        }
        tv_usia_panen.setText(age);
        tv_panen_terdekat.setText(dateTime[0]);
    }

    private void setDataMonitoring(List<ConnectedComponent> connectedComponentList){
        SensorMonitoringAdapter adapter = new SensorMonitoringAdapter(connectedComponentList, this, this);
        rv_monitoring.setAdapter(adapter);
    }

    public void setSiteId(Integer siteId) {
        getCommoditiesMonitoringFilter(commoditiesResponse.getCommodity().getId()+"", siteId+"");
    }

    @SuppressLint("SetTextI18n")
    private void setResultDataFilter(Summary summary, List<CurrentSite> currentSiteList){
        btn_reset_filter.setVisibility(View.VISIBLE);
        label_site.setVisibility(View.VISIBLE);
        tv_site_name.setVisibility(View.VISIBLE);

        if(currentSiteList.size()>0){
            tv_site_name.setText(currentSiteList.get(0).getName());
        } else {
            tv_site_name.setText("-");
        }

        String[] dateTime = formatDateAndTime(summary.getNearestHarvestStartDate());
        tv_jumlah_lubang.setText(summary.getObjectCount() +  " " + summary.getObject_type_label());
        tv_usia_panen.setText(summary.getNewestAge() + " - " + summary.getOldestAge());
        tv_panen_terdekat.setText(dateTime[0]);
    }

    private void getCommoditiesMonitoringFilter(String id, String site_id){
        Loading.show(DetailStatusTanamanActivity.this);
        ApiCore.apiInterface().filterMyFarmCommoditiesMonitoring(id, site_id, PreferenceManager.getSessionToken()).enqueue(new Callback<ApiResponse<CommodityMonitoringResponse>>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<ApiResponse<CommodityMonitoringResponse>> call, Response<ApiResponse<CommodityMonitoringResponse>> response) {
                Loading.hide(DetailStatusTanamanActivity.this);
                try {
                    if (response.isSuccessful()){
                        CommodityMonitoringResponse commodityMonitoringResponse = Objects.requireNonNull(response.body()).getData();
                        List<CurrentSite> currentSiteList = commodityMonitoringResponse.getCurrentSites();
                        Summary summary = commodityMonitoringResponse.getSummary();
//                        setCurrentSites(commodityMonitoringResponse.getCurrentSites());
                        setCurrentDevelopmentDetails(commodityMonitoringResponse.getCurrentDevelopmentDetails());
                        setDataMonitoring(commodityMonitoringResponse.getConnectedComponents());
                        setResultDataFilter(summary, currentSiteList);

                        for (CurrentSite site: currentSiteList){
                            if (site.getSiteId() == Integer.parseInt(site_id)){
                                tv_site_name.setText(site.getName());
                            }
                        }

                        String[] dateTime = formatDateAndTime(summary.getNearestHarvestStartDate());
                        tv_jumlah_lubang.setText(summary.getObjectCount() + " " + summary.getObject_type_label());
                        String age;
                        if (summary.getNewestAge().equals(summary.getOldestAge())) {
                            age = summary.getNewestAge() + " Hari";
                        } else {
                            age = summary.getNewestAge() + " - " + summary.getOldestAge() + " Hari";
                        }
                        tv_usia_panen.setText(age);
                        tv_panen_terdekat.setText(dateTime[0]);
                    } else {
                        MethodUtil.getDialogWarning(response.errorBody(), DetailStatusTanamanActivity.this);
                    }
                } catch (Exception e){
                    e.printStackTrace();
                    MethodUtil.getDialogWarningCatch(DetailStatusTanamanActivity.this);
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<CommodityMonitoringResponse>> call, Throwable t) {
                t.printStackTrace();
                Loading.hide(DetailStatusTanamanActivity.this);
            }
        });
    }

    private void connectToSocket() {
        try {
            IO.Options opts = new IO.Options();
            opts.timeout = CONNECTION_TIMEOUT;
            opts.reconnection = true;
            opts.reconnectionAttempts = RECONNECTION_ATTEMPT;
            opts.reconnectionDelay = 1000;
            opts.transports = TRANSPORTS;
            opts.forceNew = true;
            socket = IO.socket("http://13.250.108.11:8081", opts);
            makeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void makeConnection() {
        if (socket != null) {
            socket.on("connect", onConnect);
            socket.connect();
            socket.on("message", args -> {
                try {
                    JSONObject messageJson = new JSONObject(args[0].toString());
                    Log.d("message", new Gson().toJson(messageJson));
                    String json = new Gson().toJson(messageJson);
                    JSONObject message = new JSONObject(json);
                    String msgJson = new Gson().toJson(message.get("nameValuePairs"));
                    if (messageJson.has("msg_ucode")){
                        SocketDataResponse socketDataResponse = new Gson().fromJson(msgJson, SocketDataResponse.class);
                        if (socketDataResponse.getMsgData().size()>0){
                            setDataMonitoring(new ArrayList<>());
                        }
                        Log.d("socketDataResponse", " - " + new Gson().toJson(socketDataResponse));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            });
            socket.on("roomData", args -> {
                try {
                    JSONObject messageJson = new JSONObject(args[0].toString());
                    Log.d("roomData", new Gson().toJson(messageJson));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            });
            socket.io().on(Manager.EVENT_TRANSPORT, args -> {
//                    Transport transport = (Transport) args[0];
//                    transport.on(Transport.EVENT_ERROR, new Emitter.Listener() {
//                        @Override
//                        public void call(Object... args) {
//                            Exception e = (Exception) args[0];
//                            Log.e("SOCKET", "Transport error " + e);
//                            e.printStackTrace();
//                            e.getCause().printStackTrace();
//                        }
//                    });
            });
        }
    }

    private Emitter.Listener onConnect = args -> {
        Log.d("Socket", socket.connected() ? "isConnected":"notConnect");
        if (socket.connected()){
            attemptSend();
        }
    };

    private void attemptSend() {
        String jsonString = "{username: " + "'" + "rizkyazhary@gmail.com" + "', " + "room: " + "'" + "depari_gateway_1" + "'" +"}";
        Log.d("join request", jsonString);
        try {
            JSONObject jsonData = new JSONObject(jsonString);
            socket.emit("join", jsonData);
        } catch (JSONException e){
            e.printStackTrace();
        }
    }

    public void setIsOpenReminder(boolean isAlreadyOpenReminder){
        this.isAlreadyOpenReminder = isAlreadyOpenReminder;
    }

    public boolean isAlreadyOpenReminder(){
        return isAlreadyOpenReminder;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}