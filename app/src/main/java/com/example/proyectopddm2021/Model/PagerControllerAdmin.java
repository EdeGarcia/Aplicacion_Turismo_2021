package com.example.proyectopddm2021.Model;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.proyectopddm2021.Fragments.GaleriaLugarAdminFragment;
import com.example.proyectopddm2021.Fragments.InformacionLugarAdminFragment;
import com.example.proyectopddm2021.Fragments.PublicacionesLugarAdminFragment;

public class PagerControllerAdmin extends FragmentPagerAdapter {

    int NumTabs;

    public PagerControllerAdmin(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        this.NumTabs = behavior;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new InformacionLugarAdminFragment();
            case 1:
                return new GaleriaLugarAdminFragment();
            case 2:
                return new PublicacionesLugarAdminFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return NumTabs;
    }
}

