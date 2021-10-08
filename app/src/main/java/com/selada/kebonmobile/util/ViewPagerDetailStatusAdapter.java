package com.selada.kebonmobile.util;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.selada.kebonmobile.presentation.status.tab.StatusLahanFragment;
import com.selada.kebonmobile.presentation.status.tab.StatusTanamanFragment;
import com.selada.kebonmobile.presentation.status.tab.lahan.tab.InformasiLahanFragment;
import com.selada.kebonmobile.presentation.status.tab.lahan.tab.RiwayatFragment;

public class ViewPagerDetailStatusAdapter extends FragmentStatePagerAdapter {

    public ViewPagerDetailStatusAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new InformasiLahanFragment();
            case 1:
                return new RiwayatFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
