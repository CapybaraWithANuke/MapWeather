package com.example.mapweatherapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {


    BottomNavigationView bottomNavigationView;

    MapsFragment mapsFragment = new MapsFragment();
    weatherFragment weatherFragment = new weatherFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        getSupportFragmentManager().beginTransaction().replace(R.id.container,mapsFragment).commit();
        getSupportFragmentManager().beginTransaction().replace(R.id.container,weatherFragment).commit();


        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.map:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,mapsFragment).commit();
                        return true;
                    case R.id.weather:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,weatherFragment).commit();
                        return true;
                }
                return false;
            }
        });
    }
}