package com.selada.kebonmobile.presentation.status.tab;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.selada.kebonmobile.R;
import com.selada.kebonmobile.presentation.status.tab.adapter.StatusLahanAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StatusLahanFragment extends Fragment {

    @BindView(R.id.rv_status_lahan)
    RecyclerView rv_status_lahan;

    @SuppressLint("InflateParams")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_status_lahan, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        initComponent();
    }

    private void initComponent() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(requireActivity(), 2);
        rv_status_lahan.setLayoutManager(gridLayoutManager);

        List<String> list = new ArrayList<>();
        list.add("Depari Farm");
        list.add("Green HOS");
        list.add("SeladaSegar");

        StatusLahanAdapter adapter = new StatusLahanAdapter(list, requireActivity(), requireActivity());
        rv_status_lahan.setAdapter(adapter);
    }
}
