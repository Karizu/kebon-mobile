package com.selada.kebonmobile.presentation.home;

import android.annotation.SuppressLint;
import android.content.Intent;
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
import com.selada.kebonmobile.model.response.FeedBottomHome;
import com.selada.kebonmobile.presentation.home.adapter.HomeFeedAdapter;
import com.selada.kebonmobile.presentation.home.adapter.HomeFeedBottomAdapter;
import com.selada.kebonmobile.presentation.home.adapter.HomeLahanAdapter;
import com.selada.kebonmobile.presentation.home.tanam.PilihTanamanActivity;
import com.selada.kebonmobile.util.Constant;
import com.selada.kebonmobile.util.PreferenceManager;
import com.skydoves.elasticviews.ElasticCardView;
import com.skydoves.elasticviews.ElasticImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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

    private int[] draList = {
            R.drawable.img_going_up,
            R.drawable.img_sewa_lahan,
            R.drawable.img_memilih_tanaman
    };

    private String[] titleList = {
            "KEUNTUNGAN BERTANI DI KEBON",
            "CARA MENYEWA LAHAN BARU",
            "CARA MEMILIH TANAMAN"
    };

    @OnClick(R.id.cv_jadwal)
    void onClickJadwal(){
//        Intent intent = new Intent(requireActivity(), PilihTanamanActivity.class);
//        startActivity(intent);
//        requireActivity().overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
    }

    @OnClick(R.id.cv_tanam_baru)
    void onClickTanamBaru(){
        Intent intent = new Intent(requireActivity(), PilihTanamanActivity.class);
        startActivity(intent);
        requireActivity().overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
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
        initComponent();
    }

    private void initComponent() {

        switch (PreferenceManager.getUserStatus()) {
            case Constant.GUEST:
                nestedScrollView.setVisibility(View.VISIBLE);
                layoutInvestor.setVisibility(View.GONE);
                img_title.setVisibility(View.VISIBLE);
                img_title.setImageDrawable(getResources().getDrawable(R.drawable.img_title_lahan));
                layout_header_already_plant_site.setVisibility(View.GONE);
                break;
            case Constant.ON_HOLD:
                nestedScrollView.setVisibility(View.GONE);
                layoutInvestor.setVisibility(View.VISIBLE);
                img_title.setVisibility(View.VISIBLE);
                img_title.setImageDrawable(getResources().getDrawable(R.drawable.img_tanam_sekarang));
                layout_header_already_plant_site.setVisibility(View.GONE);
                break;
            case Constant.READY_PLANT:
                img_title.setVisibility(View.VISIBLE);
                img_title.setImageDrawable(getResources().getDrawable(R.drawable.img_tanam_sekarang));
                layoutInvestor.setVisibility(View.VISIBLE);
                nestedScrollView.setVisibility(View.GONE);
                layout_header_already_plant_site.setVisibility(View.GONE);
                break;
            case Constant.ALREADY_PLANT_SITE:
                layout_header_already_plant_site.setVisibility(View.VISIBLE);
                img_title.setVisibility(View.INVISIBLE);
                img_title.setImageDrawable(getResources().getDrawable(R.drawable.img_tanam_sekarang));
                nestedScrollView.setVisibility(View.GONE);
                layoutInvestor.setVisibility(View.VISIBLE);
                layout.setBackgroundColor(0x00000000);
                layout_already_plant_site.setVisibility(View.VISIBLE);
                break;
        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager layoutManager3 = new LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false);

        List<String> list = new ArrayList<>();
        list.add("Depari Farm");
        list.add("Green HOS");

        List<FeedBottomHome> feedBottomHomes = new ArrayList<>();
        for (int i=0; i<titleList.length; i++){
            FeedBottomHome bottomHome = new FeedBottomHome();
            bottomHome.setDra(draList[i]);
            bottomHome.setTitle(titleList[i]);
            feedBottomHomes.add(bottomHome);
        }

        HomeFeedAdapter adapter = new HomeFeedAdapter(list, requireContext(), requireActivity());
        rv_home_1.setLayoutManager(layoutManager);
        rv_home_1.setAdapter(adapter);

        HomeFeedBottomAdapter adapter2 = new HomeFeedBottomAdapter(requireContext(), requireActivity(), feedBottomHomes);
        rv_home_2.setLayoutManager(layoutManager2);
        rv_home_2.setAdapter(adapter2);

        HomeLahanAdapter adapter3 = new HomeLahanAdapter(list, requireContext(), requireActivity());
        rv_home_lahan.setLayoutManager(layoutManager3);
        rv_home_lahan.setAdapter(adapter3);
    }
}
