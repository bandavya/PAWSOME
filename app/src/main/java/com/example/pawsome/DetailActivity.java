package com.example.pawsome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import static com.example.pawsome.MainActivity.E_imageUrl;
import static com.example.pawsome.MainActivity.E_Name;
import static com.example.pawsome.MainActivity.E_Life_span;
import static com.example.pawsome.MainActivity.E_Weight;
import static com.example.pawsome.MainActivity.E_Height;
import static com.example.pawsome.MainActivity.E_Temp;


public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        String imageUrl = intent.getStringExtra(E_imageUrl);
        String Name = intent.getStringExtra(E_Name);
        String Life_span = intent.getStringExtra(E_Life_span);
        String Height = intent.getStringExtra(E_Height);
        String Weight = intent.getStringExtra(E_Weight);
        String Temp = intent.getStringExtra(E_Temp);

        ImageView imageView = findViewById(R.id.image_view_detail);
        TextView TVName = findViewById(R.id.text_view_breed_title);
        TextView TVDetails = findViewById(R.id.text_view_breed_detail);

        Picasso.with(this).load(imageUrl).fit().centerInside().into(imageView);
        TVName.setText(Name);
        TVDetails.setText(Temp + "\n" + Height + " cms\n" +Weight + " kgs\n" + Life_span + " average life span");





    }
}