package com.selada.kebonmobile.presentation.home;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.gson.Gson;
import com.google.gson.internal.$Gson$Preconditions;
import com.madapps.liquid.LiquidRefreshLayout;
import com.selada.kebonmobile.BuildConfig;
import com.selada.kebonmobile.R;
import com.selada.kebonmobile.model.response.ApiResponse;
import com.selada.kebonmobile.model.response.FeedBottomHome;
import com.selada.kebonmobile.model.response.SiteResponse;
import com.selada.kebonmobile.model.response.errormsg.ErrorResponse;
import com.selada.kebonmobile.model.response.farmsummary.Farm;
import com.selada.kebonmobile.model.response.farmsummary.FarmSummaryResponse;
import com.selada.kebonmobile.model.response.farmsummary.ObjectTypeSummary;
import com.selada.kebonmobile.model.response.homecontent.HomeContent;
import com.selada.kebonmobile.model.response.socket.SocketDataResponse;
import com.selada.kebonmobile.network.ApiContent;
import com.selada.kebonmobile.network.ApiCore;
import com.selada.kebonmobile.presentation.home.adapter.HomeFeedAdapter;
import com.selada.kebonmobile.presentation.home.adapter.HomeFeedBottomAdapter;
import com.selada.kebonmobile.presentation.home.adapter.HomeLahanAdapter;
import com.selada.kebonmobile.presentation.home.lahan.SewaLahanActivity;
import com.selada.kebonmobile.presentation.home.tanam.PilihTanamanActivity;
import com.selada.kebonmobile.presentation.jadwal.JadwalActivity;
import com.selada.kebonmobile.presentation.panen.DaftarPanenActivity;
import com.selada.kebonmobile.presentation.status.history.HistoryActivity;
import com.selada.kebonmobile.presentation.status.tab.tanaman.panen.PanenTanamanActivity;
import com.selada.kebonmobile.util.Constant;
import com.selada.kebonmobile.util.Loading;
import com.selada.kebonmobile.util.MethodUtil;
import com.selada.kebonmobile.util.PreferenceManager;
import com.skydoves.elasticviews.ElasticButton;
import com.skydoves.elasticviews.ElasticImageView;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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

public class HomeFragment extends Fragment {

    @BindView(R.id.rv_home_1)
    RecyclerView rv_home_1;
    @BindView(R.id.rv_home_2)
    RecyclerView rv_home_2;
    @BindView(R.id.rv_home_lahan)
    RecyclerView rv_home_lahan;
    @BindView(R.id.img_title)
    ElasticImageView img_title;
    @BindView(R.id.layout_header_already_plant_site)
    LinearLayout layout_header_already_plant_site;
    @BindView(R.id.layoutInvestor)
    LinearLayout layoutInvestor;
    @BindView(R.id.layout)
    LinearLayout layout;
    @BindView(R.id.layout_already_plant_site)
    LinearLayout layout_already_plant_site;
    @BindView(R.id.nestedScrollView)
    HorizontalScrollView nestedScrollView;
    @BindView(R.id.refreshLayout)
    com.madapps.liquid.LiquidRefreshLayout refreshLayout;
    @BindView(R.id.tv_panen_terdekat)
    TextView tv_panen_terdekat;

    private int[] draList = {
            R.drawable.ic_keuntungan,
            R.drawable.ic_tips_trick,
            R.drawable.ic_cara_menanam
    };

    private String[] titleList = {
            "KEUNTUNGAN BERTANI DI KEBON",
            "CARA MENYEWA LAHAN BARU",
            "CARA MEMILIH TANAMAN"
    };

    private Socket socket;
    private int RECONNECTION_ATTEMPT = 10;
    private long CONNECTION_TIMEOUT = 30000;
    private static final String[] TRANSPORTS = {
            "websocket"
    };

    @OnClick(R.id.cv_share)
    void onClickShare(){
        showDialogShare();
    }

    @OnClick(R.id.cv_panen)
    void onClickPanen(){
        Intent intent = new Intent(requireActivity(), DaftarPanenActivity.class);
        startActivity(intent);
        requireActivity().overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
    }

    @OnClick(R.id.cv_history)
    void onClickHistory(){
        Intent intent = new Intent(requireActivity(), HistoryActivity.class);
        startActivity(intent);
        requireActivity().overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
    }

    @OnClick(R.id.cv_jadwal)
    void onClickJadwal(){
        Intent intent = new Intent(requireActivity(), JadwalActivity.class);
        startActivity(intent);
        requireActivity().overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
    }

    @OnClick(R.id.cv_tanam_baru)
    void onClickTanamBaru(){
        Intent intent = new Intent(requireActivity(), SewaLahanActivity.class);
        startActivity(intent);
        requireActivity().overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
    }

    @OnClick(R.id.imageView10)
    void onClickBtnChat(){
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(MethodUtil.getUrlCsData()));
        startActivity(i);
    }

    public void connectToSocket() {
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
        Log.d("Socket", socket.connected()?"isConnected":"notConnect");
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        connectToSocket();
    }

    @SuppressLint("InflateParams")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        new PreferenceManager(requireActivity());
        getMyFarmSummary(false);
    }

    private void initComponent() {
        getHomeContent();

        refreshLayout.setOnRefreshListener(new LiquidRefreshLayout.OnRefreshListener() {
            @Override
            public void completeRefresh() {

            }

            @Override
            public void refreshing() {
                getMyFarmSummary(true);
            }
        });


        if (PreferenceManager.isFirstOpen()){
            nestedScrollView.setVisibility(View.VISIBLE);
            layoutInvestor.setVisibility(View.GONE);
            img_title.setVisibility(View.VISIBLE);
            img_title.setImageDrawable(getResources().getDrawable(R.drawable.img_title_lahan));
            layout_header_already_plant_site.setVisibility(View.GONE);
            getListSite();
        } else {
            switch (PreferenceManager.getUserStatus()) {
                case Constant.ON_HOLD:
                    img_title.setVisibility(View.VISIBLE);
                    img_title.setImageDrawable(getResources().getDrawable(R.drawable.img_tanam_sekarang));
                    layoutInvestor.setVisibility(View.VISIBLE);
                    nestedScrollView.setVisibility(View.GONE);
                    layout_header_already_plant_site.setVisibility(View.GONE);
                    break;
                case Constant.ALREADY_PLANT_SITE:
                    img_title.setVisibility(View.INVISIBLE);
                    img_title.setImageDrawable(getResources().getDrawable(R.drawable.img_tanam_sekarang));
                    layoutInvestor.setVisibility(View.VISIBLE);
                    nestedScrollView.setVisibility(View.GONE);
                    layout.setBackgroundColor(0x00000000);
                    layout_already_plant_site.setVisibility(View.VISIBLE);
                    layout_header_already_plant_site.setVisibility(View.VISIBLE);
                    break;
            }
        }
    }

    private void getListSite(){
        Loading.show(requireActivity());
        ApiCore.apiInterface().getListSite(PreferenceManager.getSessionToken()).enqueue(new Callback<ApiResponse<List<SiteResponse>>>() {
            @Override
            public void onResponse(Call<ApiResponse<List<SiteResponse>>> call, Response<ApiResponse<List<SiteResponse>>> response) {
                Loading.hide(requireActivity());
                try {
                    if (response.isSuccessful()) {
                        List<SiteResponse> responses = Objects.requireNonNull(response.body()).getData();
                        HomeFeedAdapter adapter = new HomeFeedAdapter(responses, requireContext(), requireActivity());
                        rv_home_1.setLayoutManager(new LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false));
                        rv_home_1.setAdapter(adapter);
                    } else {
                        Gson gson = new Gson();
                        ErrorResponse message = gson.fromJson(Objects.requireNonNull(response.errorBody()).charStream(),ErrorResponse.class);
                        String errorMessage = message.getData().getMessage();
                        Dialog dialog = MethodUtil.getDialogCart(R.layout.dialog_warning, requireActivity());
                        TextView desc = dialog.findViewById(R.id.tv_desc);
                        desc.setText(errorMessage);
                        ImageView btn_close = dialog.findViewById(R.id.btn_close);
                        btn_close.setOnClickListener(view -> {
                            dialog.dismiss();
                        });
                    }
                } catch (Exception e){
                    e.printStackTrace();
                    String errorMessage = "Terjadi kesalahan (Response Code: On Catch)";
                    Dialog dialog = MethodUtil.getDialogCart(R.layout.dialog_warning, requireActivity());
                    TextView desc = dialog.findViewById(R.id.tv_desc);
                    desc.setText(errorMessage);
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<List<SiteResponse>>> call, Throwable t) {
                t.printStackTrace();
                Loading.hide(requireActivity());
            }
        });
    }

    private void getMyFarmSummary(boolean isRefresh){
        Loading.show(requireActivity());
        ApiCore.apiInterface().getMyFarmSummary(PreferenceManager.getSessionToken()).enqueue(new Callback<FarmSummaryResponse>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<FarmSummaryResponse> call, Response<FarmSummaryResponse> response) {
                if (isRefresh) refreshLayout.finishRefreshing();
                Loading.hide(requireActivity());
                try {
                    if (response.isSuccessful()){
                        List<Farm> farmList = Objects.requireNonNull(response.body()).getFarms();
                        if (farmList.size() > 0) {
                            PreferenceManager.setIsFirstOpen(false);
                            HomeLahanAdapter adapter3 = new HomeLahanAdapter(farmList, requireActivity(), requireActivity());
                            rv_home_lahan.setLayoutManager(new LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false));
                            rv_home_lahan.setAdapter(adapter3);

                            String nearestHarvest = response.body().getDays_to_nearest_harvest()!=null?response.body().getDays_to_nearest_harvest():"0";

                            if (response.body().getDays_to_nearest_harvest()!=null){
                                if (Integer.parseInt(nearestHarvest) < 1){
                                    tv_panen_terdekat.setText("Siap Panen");
                                    Dialog dialog = MethodUtil.getDialogCart(R.layout.dialog_reminder_harvest, requireActivity());
                                    ElasticButton btn_check = dialog.findViewById(R.id.btn_check);
                                    btn_check.setOnClickListener(view -> {
                                        Intent intent = new Intent(requireActivity(), DaftarPanenActivity.class);
                                        startActivity(intent);
                                        requireActivity().overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                                    });
                                } else {
                                    tv_panen_terdekat.setText( nearestHarvest + " Hari");
                                }
                            }

                            for(Farm farm : farmList){
                                for (ObjectTypeSummary summary: farm.getObjectTypeSummary()) {
                                    if (summary.getTotalPending()>0){
                                        PreferenceManager.setUserStatus(Constant.ON_HOLD);
                                    }
                                    if (summary.getTotal_active()>0){
                                        PreferenceManager.setUserStatus(Constant.ALREADY_PLANT_SITE);
                                    }
                                }
                            }
                        } else {
                            PreferenceManager.setIsFirstOpen(true);
                        }

                        initComponent();

                    } else {
                        Gson gson = new Gson();
                        ErrorResponse message = gson.fromJson(Objects.requireNonNull(response.errorBody()).charStream(),ErrorResponse.class);
                        String errorMessage = message.getData().getMessage();
                        Dialog dialog = MethodUtil.getDialogCart(R.layout.dialog_warning, requireActivity());
                        TextView desc = dialog.findViewById(R.id.tv_desc);
                        desc.setText(errorMessage);
                        ImageView btn_close = dialog.findViewById(R.id.btn_close);
                        btn_close.setOnClickListener(view -> {
                            dialog.dismiss();
                        });
                    }

                } catch (Exception e){
                    e.printStackTrace();
                    String errorMessage = "Terjadi kesalahan (Response Code: On Catch)";
                    Dialog dialog = MethodUtil.getDialogCart(R.layout.dialog_warning, requireActivity());
                    TextView desc = dialog.findViewById(R.id.tv_desc);
                    desc.setText(errorMessage);
                    ImageView btn_close = dialog.findViewById(R.id.btn_close);
                    btn_close.setOnClickListener(view -> {
                        dialog.dismiss();
                    });
                }
            }

            @Override
            public void onFailure(Call<FarmSummaryResponse> call, Throwable t) {
                if (isRefresh) refreshLayout.finishRefreshing();
                t.printStackTrace();
                Loading.hide(requireActivity());
            }
        });
    }

    private void getHomeContent(){
        Loading.show(requireActivity());
        ApiContent.apiInterface().getContentHome().enqueue(new Callback<List<HomeContent>>() {
            @Override
            public void onResponse(Call<List<HomeContent>> call, Response<List<HomeContent>> response) {
                Loading.hide(requireActivity());
                try {
                    if (response.isSuccessful()){

                        List<FeedBottomHome> feedBottomHomes = new ArrayList<>();
                        int size =  Objects.requireNonNull(response.body()).size() > 3 ? 3 : response.body().size();
                        for (int i = 0; i < size; i++){
                            HomeContent homeContent = response.body().get(i);
                            FeedBottomHome bottomHome = new FeedBottomHome();
                            bottomHome.setDra(draList[i]);
                            bottomHome.setTitle(homeContent.getTitle().getRendered());
                            bottomHome.setLink(homeContent.getLink());
                            feedBottomHomes.add(bottomHome);
                        }

                        rv_home_2.setLayoutManager(new LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false));
                        HomeFeedBottomAdapter adapter2 = new HomeFeedBottomAdapter(requireContext(), requireActivity(), feedBottomHomes);
                        rv_home_2.setAdapter(adapter2);
                    } else {
                        MethodUtil.getDialogWarning(response.errorBody(), requireActivity());
                    }
                } catch (Exception e){
                    e.printStackTrace();
                    MethodUtil.getDialogWarningCatch(requireActivity());
                }
            }

            @Override
            public void onFailure(Call<List<HomeContent>> call, Throwable t) {
                Loading.hide(requireActivity());
                t.printStackTrace();
            }
        });
    }

    private void showDialogShare() {
        BottomSheetDialog dialog = new BottomSheetDialog(requireActivity());
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
            String shareBody = "\nKemudahan bertani online\n\n" + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID +"\n\n";;
            intent.setType("text/plain");
            intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Kebon Mobile");
            intent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
            intent.setPackage("com.whatsapp");
//            shareItem("", intent);
            startActivity(Intent.createChooser(intent, "Share"));
        });

        btn_share_instagram.setOnClickListener(view -> {
            Intent intent = new Intent(android.content.Intent.ACTION_SEND);
            String shareBody = "\nKemudahan bertani online\n\n" + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID +"\n\n";;
            intent.setType("text/plain");
            intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Kebon Mobile");
            intent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
            intent.setPackage("com.instagram.android");
//            shareItem("", intent);
            startActivity(Intent.createChooser(intent, "Share"));
        });

        btn_share_line.setOnClickListener(view -> {
            Intent intent = new Intent(android.content.Intent.ACTION_SEND);
            String shareBody = "\nKemudahan bertani online\n\n" + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID +"\n\n";;
            intent.setType("text/plain");
            intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Kebon Mobile");
            intent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
            intent.setPackage("jp.naver.line.android");
//            shareItem("", intent);
            startActivity(Intent.createChooser(intent, "Share"));
        });

        btn_share_bluetooth.setOnClickListener(view -> {
            Intent intent = new Intent(android.content.Intent.ACTION_SEND);
            String shareBody = "\nKemudahan bertani online\n\n" + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID +"\n\n";;
            intent.setType("text/plain");
            intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Kebon Mobile");
            intent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
            intent.setPackage("com.android.bluetooth");
//            shareItem("", intent);
            startActivity(Intent.createChooser(intent, "Share"));
        });

        btn_share_twitter.setOnClickListener(view -> {
            Intent intent = new Intent(android.content.Intent.ACTION_SEND);
            String shareBody = "\nKemudahan bertani online\n\n" + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID +"\n\n";;
            intent.setType("text/plain");
            intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Kebon Mobile");
            intent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody );
            intent.setPackage("com.twitter.android");
//            shareItem("", intent);
            startActivity(Intent.createChooser(intent, "Share"));
        });
    }

    public void shareItem(String url, Intent i) {
        url = "http://18.142.123.254:8082/media/depari-farm-kebon-site.jpg";
        Picasso.get().load(url).into(new Target() {
            @Override public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                i.setType("image/*");
                i.putExtra(Intent.EXTRA_STREAM, getLocalBitmapUri(bitmap));
                startActivity(Intent.createChooser(i, "Share Image"));
            }

            @Override
            public void onBitmapFailed(Exception e, Drawable errorDrawable) {

            }
            @Override public void onPrepareLoad(Drawable placeHolderDrawable) { }
        });
    }

    public Uri getLocalBitmapUri(Bitmap bmp) {
        Uri bmpUri = null;
        try {
            File file =  new File(requireActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES), "share_image_" + System.currentTimeMillis() + ".png");
            FileOutputStream out = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.close();
            bmpUri = Uri.fromFile(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bmpUri;
    }

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
}
