package com.selada.kebonmobile.util;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.selada.kebonmobile.presentation.akun.AkunFragment;
import com.selada.kebonmobile.presentation.home.HomeFragment;
import com.selada.kebonmobile.presentation.notification.NotificationFragment;
import com.selada.kebonmobile.presentation.notification.NotificationPlantFragment;
import com.selada.kebonmobile.presentation.status.StatusFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new HomeFragment();
            case 1:
                return new StatusFragment();
            case 2:
                return new NotificationFragment();
            case 3:
                return new AkunFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 4;
    }
}
