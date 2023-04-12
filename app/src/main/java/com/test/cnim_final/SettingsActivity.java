package com.test.cnim_final;

import static androidx.preference.PreferenceManager.getDefaultSharedPreferences;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.view.GravityCompat;
import androidx.preference.EditTextPreference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.content.ContextWrapper;

import java.nio.charset.StandardCharsets;

public class SettingsActivity extends AppCompatActivity {

    private Button button_arrowb;
    private SwitchCompat switchNot;
    private SwitchCompat switchAut;
    private SwitchCompat switchRed;
    private SwitchCompat switchLight;
    private SwitchCompat switchDark;
    private boolean checkTheme;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        button_arrowb = findViewById(R.id.button_arrow);
        switchNot = findViewById(R.id.switch_notify);
        switchAut = findViewById(R.id.switch_aut);
        switchRed = findViewById(R.id.switch_red);
        switchLight = findViewById(R.id.switch_theme_light);
        switchDark = findViewById(R.id.switch_theme_dark);


        SharedPreferences sharedPreferences = getSharedPreferences("saveData", MODE_PRIVATE);
        switchNot.setChecked(sharedPreferences.getBoolean("valueNot", false));
        switchAut.setChecked(sharedPreferences.getBoolean("valueAut", false));
        switchRed.setChecked(sharedPreferences.getBoolean("valueRed", false));
        switchLight.setChecked(sharedPreferences.getBoolean("valueLight", true));
        switchDark.setChecked(sharedPreferences.getBoolean("valueDark", false));
        checkTheme = sharedPreferences.getBoolean("valueTheme", true);
        if (checkTheme) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }

        switchLight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (switchLight.isChecked()) {
                    SharedPreferences.Editor editor = getSharedPreferences("saveData", MODE_PRIVATE).edit();
                    editor.putBoolean("valueLight", true);
                    editor.putBoolean("valueDark", false);
                    editor.putBoolean("valueTheme", true);
                    editor.apply();
                    switchLight.setChecked(true);
//                    switchLight.setEnabled(false);
                    switchDark.setChecked(false);
//                    switchDark.setEnabled(true);
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                } else {
                    SharedPreferences.Editor editor = getSharedPreferences("saveData", MODE_PRIVATE).edit();
                    editor.putBoolean("valueLight", false);
                    editor.putBoolean("valueTheme", false);
                    editor.apply();
                    switchLight.setChecked(false);
//                    switchLight.setEnabled(true);
                    switchDark.setChecked(true);
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                }
            }
        });

        switchDark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (switchDark.isChecked()) {
                    SharedPreferences.Editor editor = getSharedPreferences("saveData", MODE_PRIVATE).edit();
                    editor.putBoolean("valueDark", true);
                    editor.putBoolean("valueLight", false);
                    editor.putBoolean("valueTheme", false);
                    editor.apply();
                    switchDark.setChecked(true);
//                    switchDark.setEnabled(false);
                    switchLight.setChecked(false);
//                    switchLight.setEnabled(true);
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                } else {
                    SharedPreferences.Editor editor = getSharedPreferences("saveData", MODE_PRIVATE).edit();
                    editor.putBoolean("valueDark", false);
                    editor.putBoolean("valueTheme", true);
                    editor.apply();
                    switchDark.setChecked(false);
//                    switchDark.setEnabled(true);
                    switchLight.setChecked(true);
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
            }
        });

        switchNot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (switchNot.isChecked()) {
                    SharedPreferences.Editor editor = getSharedPreferences("saveData", MODE_PRIVATE).edit();
                    editor.putBoolean("valueNot", true);
                    editor.apply();
                    switchNot.setChecked(true);
                } else {
                    SharedPreferences.Editor editor = getSharedPreferences("saveData", MODE_PRIVATE).edit();
                    editor.putBoolean("valueNot", false);
                    editor.apply();
                    switchNot.setChecked(false);
                }
            }
        });

        switchAut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (switchAut.isChecked()) {
                    SharedPreferences.Editor editor = getSharedPreferences("saveData", MODE_PRIVATE).edit();
                    editor.putBoolean("valueAut", true);
                    editor.apply();
                    switchAut.setChecked(true);
                } else {
                    SharedPreferences.Editor editor = getSharedPreferences("saveData", MODE_PRIVATE).edit();
                    editor.putBoolean("valueAut", false);
                    editor.apply();
                    switchAut.setChecked(false);
                }
            }
        });

        switchRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (switchRed.isChecked()) {
                    SharedPreferences.Editor editor = getSharedPreferences("saveData", MODE_PRIVATE).edit();
                    editor.putBoolean("valueRed", true);
                    editor.apply();
                    switchRed.setChecked(true);
                } else {
                    SharedPreferences.Editor editor = getSharedPreferences("saveData", MODE_PRIVATE).edit();
                    editor.putBoolean("valueRed", false);
                    editor.apply();
                    switchRed.setChecked(false);
                }
            }
        });

        button_arrowb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingsActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(SettingsActivity.this, HomeActivity.class);
        startActivity(intent);
    }
}