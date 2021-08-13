package com.example.pawsome;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebStorage;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements DogAdapter.OnItemClickListener {
    public static final String E_imageUrl = "imageUrl";
    public static final String E_Name = "Name";
    public static final String E_Life_span = "Life_span";
    public static final String E_Weight = "Weight";
    public static final String E_Height = "Height";
    public static final String E_Temp = "Temp";



    private RecyclerView dogsRV;
    private DogAdapter dAdapter;
    private ArrayList<DogItem> dList;
    private RequestQueue dRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dogsRV = findViewById(R.id.DogsRecyclerView);
        dogsRV.setHasFixedSize(true);
        dogsRV.setLayoutManager(new LinearLayoutManager(this));
        dList = new ArrayList<>();
        dRequestQueue = Volley.newRequestQueue(this);
        parseJSON();

        //EditText DogNameText = findViewById(R.id.searchdogs);
        //Button SearchBtn = findViewById(R.id.searchbtn);

        //NAVIGATION CODE HERE
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.home);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){

                    case R.id.home:
                        return true;

                    case R.id.fav:
                        startActivity(new Intent(getApplicationContext(), FavouritesActivity.class));
                        finish();
                    overridePendingTransition(0,0);
                    return true;

                    case R.id.img_uld:
                        startActivity(new Intent(MainActivity.this, ImgUplActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                }
                return false;
            }
        });

        //SEARCH CODE HERE
        /*SearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Breed = DogNameText.toString();

                SeachDogs(Breed);
            }
        });
        */




    }

    private void parseJSON() {
        String url = "https://api.thedogapi.com/v1/breeds";
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
        //JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray JSONArray) {
                        try{
                        for (int i = 0; i < JSONArray.length(); i++) {
                            JSONObject d = JSONArray.getJSONObject(i);
                            String Name = d.getString("name");
                            JSONObject images = d.getJSONObject("image");
                            String imageUrl = images.getString("url");
                            String Life_span = d.getString("life_span");
                            JSONObject w = d.getJSONObject("weight");
                            String Weight = w.getString("metric");
                            JSONObject h = d.getJSONObject("height");
                            String Height = h.getString("metric");
                            String Temp;

                            if(!d.isNull("temperament"))
                            {
                                Temp = d.getString("temperament");
                            }
                            else
                            {
                                Temp = "No information provided";

                            }


                            /*

                            String Bred_for = d.getString("bred_for");
                            String Breed_grp = d.getString("breed_group");

                            String Origin = d.getString("origin");


                             */


                            //dList.add(new DogItem(imageUrl, Name, ID, Bred_for, Breed_grp, Life_span, Temp, Origin, Weight, Height));
                            dList.add(new DogItem(imageUrl, Name, Life_span, Weight, Height, Temp));


                        }
                            dAdapter = new DogAdapter(MainActivity.this, dList);
                            dogsRV.setAdapter(dAdapter);
                            dAdapter.setOnItemClickListener(MainActivity.this);


                        }catch (JSONException e) {
                                e.printStackTrace();
                            }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        dRequestQueue.add(request);
    }

    private void SeachDogs(String breed) {
    }

    @Override
    public void onItemClick(int position) {
        Intent DI = new Intent(this, DetailActivity.class);
        DogItem C_Item = dList.get(position);

        DI.putExtra(E_Name, C_Item.getBreedName());
        DI.putExtra(E_imageUrl, C_Item.getImageUrl());
        DI.putExtra(E_Life_span, C_Item.getLife_span());
        DI.putExtra(E_Weight, C_Item.getWeight());
        DI.putExtra(E_Height, C_Item.getHeight());
        DI.putExtra(E_Temp, C_Item.getTemp());



        startActivity(DI);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {




        getMenuInflater().inflate(R.menu.search_menu, menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                dAdapter.getFilter().filter(newText);

                return false;
            }
        });



    return true;
    }
}