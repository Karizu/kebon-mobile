package com.selada.kebonmobile.presentation.akun;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
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
import com.selada.kebonmobile.presentation.MainActivity;
import com.selada.kebonmobile.presentation.auth.LoginActivity;
import com.selada.kebonmobile.util.MethodUtil;
import com.selada.kebonmobile.util.PreferenceManager;
import com.skydoves.elasticviews.ElasticButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AkunFragment extends Fragment {

    @BindView(R.id.btn_back)
    ImageView btnBack;
    @BindView(R.id.btn_chat)
    ImageView btnChat;
    @BindView(R.id.tv_title_bar)
    TextView tvTitleBar;

    @OnClick(R.id.btn_tambah_lahan)
    void onClickTambahLahan(){

    }
    @OnClick(R.id.btn_history)
    void onClickHistory(){

    }
    @OnClick(R.id.btn_faq)
    void onClickFaq(){

    }
    @OnClick(R.id.btn_cs)
    void onClickCs(){

    }
    @OnClick(R.id.btn_about)
    void onClickAbout(){

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
        Intent intent = new Intent(requireActivity(), DetailAkunActivity.class);
        startActivity(intent);
        requireActivity().overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
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
        initComponent();
    }

    private void initComponent() {
        tvTitleBar.setText("Akun");
        btnChat.setVisibility(View.GONE);
        btnBack.setVisibility(View.GONE);
    }
}
