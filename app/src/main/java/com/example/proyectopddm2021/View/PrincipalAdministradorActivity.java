package com.example.proyectopddm2021.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.proyectopddm2021.DAO.LugarTuristicoDAO;
import com.example.proyectopddm2021.Presenter.LugarTuristicoPresenter;
import com.example.proyectopddm2021.R;
import com.google.android.material.navigation.NavigationView;

public class PrincipalAdministradorActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;
    TextView txtNombre, txtCorreo;
    Button btnPublicaciones, btnPerfil;

    //Context
    public static Context contextOfApplication;


    private LugarTuristicoPresenter presenter = new LugarTuristicoPresenter();
    private LugarTuristicoDAO dao = new LugarTuristicoDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal_administrador);

//
        contextOfApplication = getApplicationContext();

//

        txtNombre = (TextView) findViewById(R.id.txtNombreLugar);
        txtCorreo = (TextView) findViewById(R.id.txtCorreoLugar);
        btnPerfil = (Button) findViewById(R.id.btnPefil);
        btnPublicaciones = (Button) findViewById(R.id.btnPublicaciones);

        presenter.DatosPrincipal(txtNombre,txtCorreo);

        btnPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IrAPerfil();
            }
        });

        setUpToolbar();
        navigationView = (NavigationView) findViewById(R.id.navigation_menu);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId())
                {
                    case  R.id.nav_home:

                        Intent intent = new Intent(PrincipalAdministradorActivity.this, PrincipalAdministradorActivity.class);
                        startActivity(intent);
                        break;

                        /*case R.id.nav_drawer1:
                        Intent intent1 = new Intent(PrincipalAdministradorActivity.this, .class);
                        startActivity(intent1);

                        break;*/

                    /*case R.id.nav_drawer2:
                        Intent intent2 = new Intent(PrincipalAdminisradorActivity.this, .class);
                        startActivity(intent2);

                        break;*/

                    case R.id.nav_drawer3:

                        Intent intent3 = new Intent(PrincipalAdministradorActivity.this, InformacionAppActivity.class);
                        startActivity(intent3);
                        break;

                    case R.id.nav_drawer4:
                        dao.signOut();
                        Intent intent4 = new Intent(PrincipalAdministradorActivity.this, LoginUsuariosActivity.class);
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


    public void IrAPerfil(){
        startActivity(new Intent(this, PerfilLugarAdministradorActivity.class));
    }

    public static Context getContextOfApplication()
    {
        return contextOfApplication;
    }
}

