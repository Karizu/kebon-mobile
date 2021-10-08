package com.selada.kebonmobile.presentation.status.tab;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.selada.kebonmobile.R;
import com.selada.kebonmobile.presentation.status.tab.adapter.StatusLahanAdapter;
import com.selada.kebonmobile.presentation.status.tab.adapter.StatusTanamanAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StatusTanamanFragment extends Fragment {

    @BindView(R.id.spinner_filter_tanaman)
    Spinner spinner_filter_tanaman;
    @BindView(R.id.rv_status_tanaman)
    RecyclerView rv_status_tanaman;

    @OnClick(R.id.layout_spinner)
    void onClickLayoutSpinner(){
        spinner_filter_tanaman.performClick();
    }

    @SuppressLint("InflateParams")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_status_tanaman, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        initComponent();
    }

    private void initComponent() {
        setSpinnerAdapter();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(requireActivity(), 2);
        rv_status_tanaman.setLayoutManager(gridLayoutManager);

        List<String> list = new ArrayList<>();
        list.add("Selada Hijau");
        list.add("Kangkung");
        list.add("Brokoli");
        list.add("Pakcoy");

        StatusTanamanAdapter adapter = new StatusTanamanAdapter(list, requireActivity(), requireActivity());
        rv_status_tanaman.setAdapter(adapter);
    }

    private void setSpinnerAdapter() {
        String[] country = { "Pilih Lokasi Lahan", "Depari Farm", "Berastagi Farm", "Green HOS"};
        ArrayAdapter aa = new ArrayAdapter(requireActivity(), R.layout.custom_spinner_item, country) {
            @Override
            public boolean isEnabled(int position) {
                if (position == 0) {
                    return false;
                } else {
                    return true;
                }
            }
        };

        aa.setDropDownViewResource(R.layout.custom_spinner_drop_item);
        spinner_filter_tanaman.setAdapter(aa);

        spinner_filter_tanaman.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(requireActivity(), spinner_filter_tanaman.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}
