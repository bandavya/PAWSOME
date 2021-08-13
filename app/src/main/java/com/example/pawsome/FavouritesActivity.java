package com.example.pawsome;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class FavouritesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);

        TextView title = (TextView) findViewById(R.id.favtv);
        title.setText("Activity fav");

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.fav);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.home:
                        startActivity(new Intent(FavouritesActivity.this,MainActivity.class));
                        finish();
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.fav:
                        return true;

                    case R.id.img_uld:
                        startActivity(new Intent(FavouritesActivity.this, ImgUplActivity.class));
                        finish();
                        overridePendingTransition(0,0);
                        return true;



                }
                return false;
            }
        });
    }
}