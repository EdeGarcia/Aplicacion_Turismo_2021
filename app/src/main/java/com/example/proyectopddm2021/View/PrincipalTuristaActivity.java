package com.example.proyectopddm2021.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Layout;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.proyectopddm2021.DAO.LugarTuristicoDAO;
import com.example.proyectopddm2021.R;
import com.google.android.material.navigation.NavigationView;

public class PrincipalTuristaActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;
    TextView tvVolcanes, tvSitios, tvMuseos,  tvPueblos, tvLagos, tvPlayas;
    private LugarTuristicoDAO dao = new LugarTuristicoDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal_turista);
        setUpToolbar();
        tvVolcanes = findViewById(R.id.tvVolcanes);
        tvSitios = findViewById(R.id.tvSitios);
        tvMuseos = findViewById(R.id.tvMuseos);
        tvPueblos = findViewById(R.id.tvMuseos);
        tvLagos = findViewById(R.id.tvLagos);
        tvPlayas = findViewById(R.id.tvPlayas);

        navigationView = (NavigationView) findViewById(R.id.navigation_menu);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId())
                {
                    case  R.id.nav_home:

                        Intent intent = new Intent(PrincipalTuristaActivity.this, PrincipalTuristaActivity.class);
                        startActivity(intent);
                        break;

                    /*case R.id.nav_drawer1:
                        Intent intent1 = new Intent(PrincipalTuristaActivity.this, .class);
                        startActivity(intent1);

                        break;*/

                    /*case R.id.nav_drawer2:
                        Intent intent2 = new Intent(PrincipalTuristaActivity.this, .class);
                        startActivity(intent2);

                        break;*/

                    case R.id.nav_drawer3:
                        Intent intent3 = new Intent(PrincipalTuristaActivity.this, InformacionAppActivity.class);
                        startActivity(intent3);
                        break;

                    case R.id.nav_drawer4:
                        dao.signOut();
                        Intent intent4 = new Intent(PrincipalTuristaActivity.this, TipoUsuarioActivity.class);
                        startActivity(intent4);
                        break;




//Paste your privacy policy link

//                    case  R.id.nav_Policy:{
//
//                        Intent browserIntent  = new Intent(Intent.ACTION_VIEW , Uri.parse(""));
//                        startActivity(browserIntent);
//
//                    }
                    //       break;
                    case  R.id.nav_share:{

                        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                        sharingIntent.setType("text/plain");
                        String shareBody =  "http://play.google.com/store/apps/detail?id=" + getPackageName();
                        String shareSub = "Try now";
                        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, shareSub);
                        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                        startActivity(Intent.createChooser(sharingIntent, "Share using"));

                    }
                    break;
                }
                return false;
            }

        });
    }
    public void setUpToolbar() {
        drawerLayout = findViewById(R.id.drawerLayout);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }

    public void CargarlistaCategoria1(View view){
        String volcanes;
        volcanes = tvVolcanes.getText().toString();
        Intent intent = new Intent(PrincipalTuristaActivity.this, CategoriaTuristaActivity.class);
        intent.putExtra("Categoria", volcanes);
        startActivity(intent);
    }

    public void CargarlistaCategoria2(View view){
        String sitios;
        sitios = tvSitios.getText().toString();
        Intent intent = new Intent(PrincipalTuristaActivity.this, CategoriaTuristaActivity.class);
        intent.putExtra("Categoria", sitios);
        startActivity(intent);
    }
    public void CargarlistaCategoria3(View view){
        String museos;
        museos = tvMuseos.getText().toString();
        Intent intent = new Intent(PrincipalTuristaActivity.this, CategoriaTuristaActivity.class);
        intent.putExtra("Categoria", museos);
        startActivity(intent);
    }

    public void CargarlistaCategoria4(View view){
        String pueblos;
        pueblos = tvPueblos.getText().toString();
        Intent intent = new Intent(PrincipalTuristaActivity.this, CategoriaTuristaActivity.class);
        intent.putExtra("Categoria", pueblos);
        startActivity(intent);
    }

    public void CargarlistaCategoria5(View view){
        String lagos;
        lagos = tvLagos.getText().toString();
        Intent intent = new Intent(PrincipalTuristaActivity.this, CategoriaTuristaActivity.class);
        intent.putExtra("Categoria", lagos);
        startActivity(intent);
    }

    public void CargarlistaCategoria6(View view){
        String playas;
        playas = tvPlayas.getText().toString();
        Intent intent = new Intent(PrincipalTuristaActivity.this, CategoriaTuristaActivity.class);
        intent.putExtra("Categoria", playas);
        startActivity(intent);
    }

}