package com.test.cnim_final;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.navigation.NavigationView.OnNavigationItemSelectedListener;

public class HomeActivity extends AppCompatActivity implements OnNavigationItemSelectedListener {

    private Button button_sett, button_orar, button_not, button_notes, button_teze;
    private Button button_menu;
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        button_menu = findViewById(R.id.button_menu);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);

        navigationDrawer();

        button_sett = findViewById(R.id.button_settings);

        button_sett.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });
        button_teze = findViewById(R.id.button_teze);

        button_teze.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, MainExam.class);
                startActivity(intent);
            }
        });

        button_notes = findViewById(R.id.button_note);

        button_notes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, NotesActivity.class);
                startActivity(intent);
            }
        });
        button_not = findViewById(R.id.button_noutati);

        button_not.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://mesota.ro/nou/index.php"));
                startActivity(browserIntent);
            }
        });

        button_orar = findViewById(R.id.button_orar);

        button_orar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, WeekActivity.class);
                startActivity(intent);
            }
        });
    }

    private void navigationDrawer() {

        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_acasa);

        button_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(drawerLayout.isDrawerVisible(GravityCompat.START)){
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    drawerLayout.openDrawer(GravityCompat.START);
                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerVisible(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            HomeActivity.this.finishAffinity();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.custom_menu, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_acasa:
                break;
            case R.id.nav_note:
                Intent intent3 = new Intent(HomeActivity.this, NotesActivity.class);
                startActivity(intent3);
                return false;
            case R.id.nav_orar:
                Intent intent1 = new Intent(HomeActivity.this, WeekActivity.class);
                startActivity(intent1);
                return false;
            case R.id.nav_noutati:
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://mesota.ro/nou/index.php"));
                startActivity(browserIntent);
                return false;
            case R.id.nav_teze:
                Intent intent4 = new Intent(HomeActivity.this, MainExam.class);
                startActivity(intent4);
                return false;
            case R.id.nav_logout:
                HomeActivity.this.finishAffinity();
                return false;
            case R.id.nav_about:
                Toast.makeText(this, "Informatii despre aplicatie", Toast.LENGTH_SHORT).show();
                return false;
            case R.id.nav_settings:
                Intent intent2 = new Intent(HomeActivity.this, SettingsActivity.class);
                startActivity(intent2);
                return false;
        }

        return true;
    }
}