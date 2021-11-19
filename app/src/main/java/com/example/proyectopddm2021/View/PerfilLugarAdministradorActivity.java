package com.example.proyectopddm2021.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.proyectopddm2021.Model.PagerControllerAdmin;
import com.example.proyectopddm2021.Presenter.LugarTuristicoPresenter;
import com.example.proyectopddm2021.R;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class PerfilLugarAdministradorActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    TabItem tab1, tab2, tab3;
    PagerControllerAdmin pagerAdapter;
    ImageView portada;
    private LugarTuristicoPresenter presenter = new LugarTuristicoPresenter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_lugar_administrador);

        tabLayout = findViewById(R.id.TabLayoutTurista);
        viewPager = findViewById(R.id.ViewPagerTurista);
        portada = findViewById(R.id.imgFotoPerfil);

        tab1 = findViewById(R.id.TabInformacionTurista);
        tab2 = findViewById(R.id.TabGaleriaTurista);
        tab3 = findViewById(R.id.TabPublicacionesTurista);


        pagerAdapter = new PagerControllerAdmin(getSupportFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapter);


        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());

                if (tab.getPosition() == 0){
                    portada.setVisibility(View.VISIBLE);
                    pagerAdapter.notifyDataSetChanged();
                }
                if (tab.getPosition() == 1){
                    portada.setVisibility(View.VISIBLE);
                    pagerAdapter.notifyDataSetChanged();
                }
                if (tab.getPosition() == 2){
                    portada.setVisibility(View.GONE);
                    pagerAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }




}

