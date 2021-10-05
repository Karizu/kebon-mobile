package com.selada.kebonmobile.presentation.status.tab.tanaman.tab;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.selada.kebonmobile.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InformasiUmumFragment extends Fragment {

    @BindView(R.id.tv_lokasi_lahan)
    TextView tv_lokasi_lahan;
    @BindView(R.id.tv_mulai_tanam)
    TextView tv_mulai_tanam;
    @BindView(R.id.tv_waktu_panen)
    TextView tv_waktu_panen;
    @BindView(R.id.tv_jumlah_lubang)
    TextView tv_jumlah_lubang;
    @BindView(R.id.tv_usia_tanaman)
    TextView tv_usia_tanaman;
    @BindView(R.id.text_title)
    TextView text_title;

    @SuppressLint("InflateParams")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_status_informasi_umum, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        initComponent();
    }

    private void initComponent() {

    }
}
