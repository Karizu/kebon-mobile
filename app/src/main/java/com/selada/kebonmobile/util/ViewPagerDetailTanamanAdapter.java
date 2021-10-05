package com.selada.kebonmobile.util;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.selada.kebonmobile.presentation.status.tab.lahan.tab.InformasiLahanFragment;
import com.selada.kebonmobile.presentation.status.tab.lahan.tab.RiwayatFragment;
import com.selada.kebonmobile.presentation.status.tab.tanaman.tab.GrafikFragment;
import com.selada.kebonmobile.presentation.status.tab.tanaman.tab.InformasiUmumFragment;

public class ViewPagerDetailTanamanAdapter extends FragmentStatePagerAdapter {

    public ViewPagerDetailTanamanAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new InformasiUmumFragment();
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
