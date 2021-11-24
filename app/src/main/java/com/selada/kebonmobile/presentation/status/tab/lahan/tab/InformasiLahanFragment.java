package com.selada.kebonmobile.presentation.status.tab.lahan.tab;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.selada.kebonmobile.R;
import com.selada.kebonmobile.model.response.farmsummary.Farm;
import com.selada.kebonmobile.model.response.farmsummary.ObjectTypeSummary;
import com.selada.kebonmobile.presentation.status.tab.adapter.StatusLahanAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InformasiLahanFragment extends Fragment {

    @BindView(R.id.tv_jumlah_lubang)
    TextView tv_jumlah_lubang;
    @BindView(R.id.tv_jumlah_lubang_kosong)
    TextView tv_jumlah_lubang_kosong;
    @BindView(R.id.tv_tanaman)
    TextView tv_tanaman;
    @BindView(R.id.tv_alamat)
    TextView tv_alamat;

    @BindView(R.id.textViewTanaman)
    TextView tv_label_jumlah_lubang;
    @BindView(R.id.textViewSite)
    TextView tv_label_jumlah_lubang_kosong;
    @BindView(R.id.tv_jumlah_lubang_active)
    TextView tv_jumlah_lubang_active;
    @BindView(R.id.textViewActive)
    TextView tv_label_jumlah_lubang_active;

    private Farm farm;
    private String latitude;
    private String longitude;

    @OnClick(R.id.btn_lihat_lokasi)
    void onClickLihat(){
        Uri gmmIntentUri = Uri.parse("geo:"+ latitude + "," + longitude);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        if (mapIntent.resolveActivity(requireActivity().getPackageManager()) != null) {
            startActivity(mapIntent);
        }
    }

    public InformasiLahanFragment(Farm farm) {
        this.farm = farm;
    }

    @SuppressLint("InflateParams")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_status_informasi_lahan, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        initComponent();
    }

    @SuppressLint("SetTextI18n")
    private void initComponent() {
        latitude = farm.getAddress_latitude();
        longitude = farm.getAddress_longitude();

        ObjectTypeSummary objectTypeSummary = new ObjectTypeSummary();
        for (ObjectTypeSummary summary: farm.getObjectTypeSummary()){
            if (summary.getFarmableStatus()){
                objectTypeSummary = summary;
            }
        }

        tv_label_jumlah_lubang.setText("Jumlah " + objectTypeSummary.getLabel());
        tv_label_jumlah_lubang_kosong.setText("Jumlah " + objectTypeSummary.getLabel() + " kosong");
        tv_label_jumlah_lubang_active.setText("Jumlah " + objectTypeSummary.getLabel() + " ditanam");

        tv_jumlah_lubang.setText(objectTypeSummary.getTotal() + "");
        tv_jumlah_lubang_kosong.setText(objectTypeSummary.getTotalIdle() + "");
        tv_jumlah_lubang_active.setText(objectTypeSummary.getTotal_active() + "");
        tv_tanaman.setText(farm.getActiveCommodityStr());
        tv_alamat.setText(farm.getAddressStreet() + ", " + farm.getAddressSubdistrict() + ", " + farm.getAddressDistrict() + ", " + farm.getAddressCity());
    }
}
