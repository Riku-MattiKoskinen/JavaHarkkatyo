package com.example.harjoitustyo;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.harjoitustyo.fragments.FragmentFight;
import com.example.harjoitustyo.fragments.FragmentGym;
import com.example.harjoitustyo.fragments.FragmentHome;
import com.example.harjoitustyo.fragments.FragmentList;

public class TabPagerAdapter extends FragmentStateAdapter {

    public TabPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position)  {
        switch (position) {
            case 0:
                return new FragmentHome();
            case 1:
                return new FragmentGym();
            case 2:
                return new FragmentList();
            case 3:
                return new FragmentFight();
            default:
                return new FragmentHome();
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }

}
