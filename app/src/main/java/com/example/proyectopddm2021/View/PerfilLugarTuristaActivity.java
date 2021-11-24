package com.example.proyectopddm2021.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.proyectopddm2021.Model.PagerControllerAdmin;
import com.example.proyectopddm2021.Model.PagerControllerTurista;
import com.example.proyectopddm2021.Presenter.LugarTuristicoPresenter;
import com.example.proyectopddm2021.R;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class PerfilLugarTuristaActivity extends AppCompatActivity {
    private TabLayout tabLayoutT;
    private ViewPager viewPagerT;
    private TabItem tab1T, tab2T, tab3T;
    private PagerControllerTurista pagerAdapter;
    private ImageView portadaT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_lugar_turista);

        String id = getIntent().getStringExtra("idLugar");
        tabLayoutT = findViewById(R.id.TabLayoutTuristaT);
        viewPagerT = findViewById(R.id.ViewPagerTuristaT);
        portadaT = findViewById(R.id.imgFotoPerfilT);

        tab1T = findViewById(R.id.TabInformacionTuristaT);
        tab2T = findViewById(R.id.TabGaleriaTuristaT);
        tab3T = findViewById(R.id.TabPublicacionesTuristaT);


        pagerAdapter = new PagerControllerTurista(getSupportFragmentManager(),tabLayoutT.getTabCount());
        viewPagerT.setAdapter(pagerAdapter);


        tabLayoutT.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPagerT.setCurrentItem(tab.getPosition());

                if (tab.getPosition() == 0){
                    portadaT.setVisibility(View.VISIBLE);
                    pagerAdapter.notifyDataSetChanged();
                }
                if (tab.getPosition() == 1){
                    portadaT.setVisibility(View.VISIBLE);
                    pagerAdapter.notifyDataSetChanged();
                }
                if (tab.getPosition() == 2){
                    portadaT.setVisibility(View.GONE);
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
        viewPagerT.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayoutT));
    }
}