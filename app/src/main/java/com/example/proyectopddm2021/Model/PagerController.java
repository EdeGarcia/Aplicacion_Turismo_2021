package com.example.proyectopddm2021.Model;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.proyectopddm2021.Fragments.GaleriaLugarTuristaFragment;
import com.example.proyectopddm2021.Fragments.InformacionLugarTuristaFragment;
import com.example.proyectopddm2021.Fragments.PublicacionesLugarTuristaFragment;

public class PagerController extends FragmentPagerAdapter {

    int NumTabs;

    public PagerController(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        this.NumTabs = behavior;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new InformacionLugarTuristaFragment();
            case 1:
                return new GaleriaLugarTuristaFragment();
            case 2:
                return new PublicacionesLugarTuristaFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return NumTabs;
    }
}

