package com.example.proyectopddm2021.Model;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.proyectopddm2021.Fragments.GaleriaLugarAdminFragment;
import com.example.proyectopddm2021.Fragments.GaleriaLugarTuristaFragment;
import com.example.proyectopddm2021.Fragments.InformacionLugarAdminFragment;
import com.example.proyectopddm2021.Fragments.InformacionLugarTuristaFragment;
import com.example.proyectopddm2021.Fragments.PublicacionesLugarAdminFragment;
import com.example.proyectopddm2021.Fragments.PublicacionesLugarTuristaFragment;

public class PagerControllerTurista extends FragmentPagerAdapter{
    int NumTabs;

    public PagerControllerTurista(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        this.NumTabs = behavior;
    }
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
