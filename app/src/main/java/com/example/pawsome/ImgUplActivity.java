package com.example.pawsome;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ImgUplActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_img_upl);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.img_uld);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){

                    case R.id.home:
                        startActivity(new Intent(ImgUplActivity.this, MainActivity.class));
                        finish();
                        overridePendingTransition(0,0);
                        return true;


                    case R.id.fav:
                        startActivity(new Intent(ImgUplActivity.this, FavouritesActivity.class));
                        finish();
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.img_uld:
                        return true;

                }
                return false;
            }
        });
    }
}