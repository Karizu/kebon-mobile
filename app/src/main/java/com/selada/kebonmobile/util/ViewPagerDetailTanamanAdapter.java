package com.selada.kebonmobile.util;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.selada.kebonmobile.model.response.detailcommodities.DetailCommoditiesResponse;
import com.selada.kebonmobile.presentation.status.tab.lahan.tab.InformasiLahanFragment;
import com.selada.kebonmobile.presentation.status.tab.lahan.tab.RiwayatFragment;
import com.selada.kebonmobile.presentation.status.tab.tanaman.tab.GrafikFragment;
import com.selada.kebonmobile.presentation.status.tab.tanaman.tab.InformasiUmumFragment;

public class ViewPagerDetailTanamanAdapter extends FragmentStatePagerAdapter {

    private DetailCommoditiesResponse commoditiesResponse;

    public ViewPagerDetailTanamanAdapter(FragmentManager fm, DetailCommoditiesResponse commoditiesResponse) {
        super(fm);
        this.commoditiesResponse = commoditiesResponse;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new InformasiUmumFragment(commoditiesResponse);
            case 1:
                return new GrafikFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
