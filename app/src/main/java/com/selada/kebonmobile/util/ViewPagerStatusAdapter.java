package com.selada.kebonmobile.util;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.selada.kebonmobile.presentation.akun.AkunFragment;
import com.selada.kebonmobile.presentation.home.HomeFragment;
import com.selada.kebonmobile.presentation.notification.NotificationFragment;
import com.selada.kebonmobile.presentation.status.StatusFragment;
import com.selada.kebonmobile.presentation.status.tab.StatusLahanFragment;
import com.selada.kebonmobile.presentation.status.tab.StatusTanamanFragment;

public class ViewPagerStatusAdapter extends FragmentStatePagerAdapter {

    public ViewPagerStatusAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new StatusLahanFragment();
            case 1:
                return new StatusTanamanFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
