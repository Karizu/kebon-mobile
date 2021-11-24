package com.selada.kebonmobile.presentation.akun;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.selada.kebonmobile.R;
import com.selada.kebonmobile.SplashActivity;
import com.selada.kebonmobile.model.response.commoditymonitoring.Summary;
import com.selada.kebonmobile.model.response.farmsummary.FarmSummaryResponse;
import com.selada.kebonmobile.model.response.farmsummary.ObjectTypeSummary;
import com.selada.kebonmobile.network.ApiCore;
import com.selada.kebonmobile.presentation.MainActivity;
import com.selada.kebonmobile.presentation.akun.history.InvoiceHistoryActivity;
import com.selada.kebonmobile.presentation.auth.LoginActivity;
import com.selada.kebonmobile.presentation.home.content.ContentActivity;
import com.selada.kebonmobile.presentation.home.lahan.SewaLahanActivity;
import com.selada.kebonmobile.util.Loading;
import com.selada.kebonmobile.util.MethodUtil;
import com.selada.kebonmobile.util.PreferenceManager;
import com.skydoves.elasticviews.ElasticButton;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AkunFragment extends Fragment {

    @BindView(R.id.btn_back)
    ImageView btnBack;
    @BindView(R.id.btn_chat)
    ImageView btnChat;
    @BindView(R.id.tv_title_bar)
    TextView tvTitleBar;
    @BindView(R.id.tv_pengguna)
    TextView tv_pengguna;
    @BindView(R.id.tv_email)
    TextView tv_email;
    @BindView(R.id.tv_phone)
    TextView tv_phone;

    @BindView(R.id.tv_jumlah_lahan)
    TextView tv_jumlah_lahan;
    @BindView(R.id.tv_jumlah_slot)
    TextView tv_jumlah_slot;
    @BindView(R.id.tv_jumlah_slot_kosong)
    TextView tv_jumlah_slot_kosong;
    @BindView(R.id.tv_masa_sewa)
    TextView tv_masa_sewa;
    @BindView(R.id.jumlah_slot)
    TextView jumlah_slot;
    @BindView(R.id.jumlah_slot_kosong)
    TextView jumlah_slot_kosong;


    @OnClick(R.id.btn_tambah_lahan)
    void onClickTambahLahan(){
        Intent intent = new Intent(requireActivity(), SewaLahanActivity.class);
        startActivity(intent);
        requireActivity().overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
    @OnClick(R.id.btn_history)
    void onClickHistory(){
        Intent intent = new Intent(requireActivity(), InvoiceHistoryActivity.class);
        startActivity(intent);
        requireActivity().overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
    @OnClick(R.id.btn_faq)
    void onClickFaq(){
        Intent intent = new Intent(requireActivity(), ContentActivity.class);
        intent.putExtra("url", "http://kebon.id/faq-mobile/");
        startActivity(intent);
        requireActivity().overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @OnClick(R.id.btn_cs)
    void onClickCs(){
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(MethodUtil.getUrlCsData()));
        startActivity(i);
    }

    @OnClick(R.id.btn_about)
    void onClickAbout(){
        Intent intent = new Intent(requireActivity(), ContentActivity.class);
        intent.putExtra("url", "http://kebon.id/tentang-kami-mobile/");
        startActivity(intent);
        requireActivity().overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
    @OnClick(R.id.btn_keluar)
    void onClickKeluar(){
        Dialog dialog = MethodUtil.getDialogCart(R.layout.dialog_logout, requireActivity());
        ElasticButton buttonTidak = dialog.findViewById(R.id.btn_tidak);
        ElasticButton buttonYa = dialog.findViewById(R.id.btn_ya);

        buttonTidak.setOnClickListener(view -> {
            dialog.dismiss();
        });

        buttonYa.setOnClickListener(view -> {
            PreferenceManager.logOut();
            Intent intent = new Intent(requireActivity(), SplashActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            requireActivity().finish();
            requireActivity().overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        });
    }

    @OnClick(R.id.frame_profile)
    void onClickProfile(){
//        Intent intent = new Intent(requireActivity(), DetailAkunActivity.class);
//        startActivity(intent);
//        requireActivity().overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @SuppressLint("InflateParams")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_akun, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        new PreferenceManager(requireActivity());
        initComponent();
    }

    private void initComponent() {
        getSummaryProfile();
        tvTitleBar.setText("Akun");
        btnChat.setVisibility(View.GONE);
        btnBack.setVisibility(View.GONE);

        tv_pengguna.setText(PreferenceManager.getLoginResponse().getUser().getProfile().getFullName());
        tv_email.setText(PreferenceManager.getLoginResponse().getUser().getUsername());
        tv_phone.setText(PreferenceManager.getLoginResponse().getUser().getProfile().getPrimaryContactPhone());
    }

    private void getSummaryProfile(){
        Loading.show(requireActivity());
        ApiCore.apiInterface().getMyFarmSummaryProfile(PreferenceManager.getSessionToken()).enqueue(new Callback<FarmSummaryResponse>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<FarmSummaryResponse> call, Response<FarmSummaryResponse> response) {
                Loading.hide(requireActivity());
                try {
                    if (response.isSuccessful()){
                        Summary summary = Objects.requireNonNull(response.body()).getSummary();
                        List<ObjectTypeSummary> objectTypeSummaryList = response.body().getSummary().getObject_type_summary();

                        String label = "";
                        int objCount = 0;
                        for (ObjectTypeSummary typeSummary: objectTypeSummaryList){
                            if (typeSummary.getFarmableStatus()){
                                label = typeSummary.getLabel();
                                objCount = typeSummary.getTotal();
                            }
                        }

                        tv_jumlah_lahan.setText(summary.getSiteCount() + "");
                        jumlah_slot.setText("Jumlah " + label + " :");
                        tv_jumlah_slot.setText(objCount + " " + label);
                        tv_masa_sewa.setText(summary.getContract_expiry_dates());

                    } else {
                        MethodUtil.getDialogWarning(response.errorBody(), requireActivity());
                    }
                } catch (Exception e){
                    e.printStackTrace();
//                    MethodUtil.getDialogWarningCatch(requireActivity());
                }
            }

            @Override
            public void onFailure(Call<FarmSummaryResponse> call, Throwable t) {
                Loading.hide(requireActivity());
                t.printStackTrace();
            }
        });
    }
}
