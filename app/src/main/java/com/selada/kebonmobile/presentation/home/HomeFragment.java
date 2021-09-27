package com.selada.kebonmobile.presentation.home;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.selada.kebonmobile.R;
import com.selada.kebonmobile.util.Constant;
import com.selada.kebonmobile.util.PreferenceManager;
import com.skydoves.elasticviews.ElasticImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeFragment extends Fragment {

    @BindView(R.id.rv_home_1)
    RecyclerView rv_home_1;
    @BindView(R.id.rv_home_2)
    RecyclerView rv_home_2;
    @BindView(R.id.rv_home_lahan)
    RecyclerView rv_home_lahan;
    @BindView(R.id.img_title)
    ElasticImageView img_title;
    @BindView(R.id.layoutInvestor)
    LinearLayout layoutInvestor;
    @BindView(R.id.nestedScrollView)
    HorizontalScrollView nestedScrollView;

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
        initComponent();
    }

    private void initComponent() {

        switch (PreferenceManager.getUserStatus()) {
            case Constant.GUEST:
                nestedScrollView.setVisibility(View.VISIBLE);
                layoutInvestor.setVisibility(View.GONE);
                img_title.setImageDrawable(getResources().getDrawable(R.drawable.img_title_lahan));
                break;
            case Constant.ON_HOLD:
                nestedScrollView.setVisibility(View.GONE);
                layoutInvestor.setVisibility(View.VISIBLE);
                img_title.setImageDrawable(getResources().getDrawable(R.drawable.img_tanam_sekarang));
                break;
            case Constant.READY_PLANT:
                img_title.setImageDrawable(getResources().getDrawable(R.drawable.img_tanam_sekarang));
                layoutInvestor.setVisibility(View.VISIBLE);
                nestedScrollView.setVisibility(View.GONE);
                break;
        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager layoutManager3 = new LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false);

        List<String> list = new ArrayList<>();
        for (int i = 0; i < 5; i++){
            list.add(""+i);
        }

        HomeFeedAdapter adapter = new HomeFeedAdapter(list, requireContext(), requireActivity(), 1);
        rv_home_1.setLayoutManager(layoutManager);
        rv_home_1.setAdapter(adapter);

        HomeFeedAdapter adapter2 = new HomeFeedAdapter(list, requireContext(), requireActivity(), 2);
        rv_home_2.setLayoutManager(layoutManager2);
        rv_home_2.setAdapter(adapter2);

        HomeLahanAdapter adapter3 = new HomeLahanAdapter(list, requireContext(), requireActivity());
        rv_home_lahan.setLayoutManager(layoutManager3);
        rv_home_lahan.setAdapter(adapter3);
    }
}
