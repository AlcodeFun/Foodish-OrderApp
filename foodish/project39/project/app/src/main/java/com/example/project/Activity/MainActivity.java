package com.example.project.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.project.Adapter.CategoryAdapter;
import com.example.project.Adapter.PopularAdapter;
import com.example.project.Domain.CategoryDomain;
import com.example.project.Domain.FoodDomain;
import com.example.project.Interface.ClickListener;
import com.example.project.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RequestQueue requestQueue;

    private RecyclerView.Adapter adapter, adapter2;
    private RecyclerView recyclerViewCategoryList, recyclerViewPopularList;
    ArrayList<CategoryDomain> categoryList = new ArrayList<>();
    ArrayList<FoodDomain> foodlist = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestQueue= Volley.newRequestQueue(MainActivity.this);



        recyclerViewCategory();
        recyclerViewPopular();
        bottomNavigation();
    }
    private void getDatabyCategory(String category){
        String url="https://asia-south1.gcp.data.mongodb-api.com/app/foodish-olalj/endpoint/getByCategory?category__id="+category;
        JsonArrayRequest jsonRequest=new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    System.out.println(category);
                    foodlist.clear();
                    for (int i=0; i< response.length(); i++){

                        JSONObject jsonData = response.getJSONObject(i);
                        FoodDomain newFood = new FoodDomain(
                                jsonData.getString("nama"),
                                jsonData.getString("gambar"),
                                "",
                                jsonData.getDouble("harga"));

                        foodlist.add(newFood);
                    }
                    adapter2.notifyDataSetChanged();

                }catch (JSONException e){

                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(requestQueue.add(jsonRequest));
    }
    private void bottomNavigation() {
        FloatingActionButton floatingActionButton = findViewById(R.id.card_btn);
        LinearLayout homeBtn = findViewById(R.id.homeBtn);
        LinearLayout settingbtn = findViewById(R.id.settingbtn);
        LinearLayout profileBtn = findViewById(R.id.profileBtn);
        LinearLayout chatBtn = findViewById(R.id.chatBtn);
        ImageView imageViewProfile = findViewById(R.id.imageViewProfile);



        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CartListActivity.class));
            }
        });

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MainActivity.class));
            }
        });

        settingbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SettingsActivity.class));
            }
        });

        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ProfileActivity.class));
            }
        });
        imageViewProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ProfileActivity.class));
            }
        });

        chatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ChatActivity.class));
            }
        });
    }

    private void recyclerViewPopular() {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewPopularList = findViewById(R.id.recyclerView2);
        recyclerViewPopularList.setLayoutManager(linearLayoutManager);

        getDatabyCategory("pza");
//
//        foodlist.add(new FoodDomain("Pepperoni pizza", "pizza1", "slices pepperoni ,mozzarella cheese, fresh oregano,  ground black pepper, pizza sauce", 155.000));
//        foodlist.add(new FoodDomain("Cheese Burger", "burger", "beef, Gouda Cheese, Special sauce, Lettuce, tomato ", 30.000));
//        foodlist.add(new FoodDomain("Vegetable pizza", "pizza2", " olive oil, Vegetable oil, pitted Kalamata, cherry tomatoes, fresh oregano, basil", 200.000));

        adapter2 = new PopularAdapter(foodlist);
        recyclerViewPopularList.setAdapter(adapter2);

    }

    private void recyclerViewCategory() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewCategoryList = findViewById(R.id.recyclerView);
        recyclerViewCategoryList.setLayoutManager(linearLayoutManager);

        categoryList.add(new CategoryDomain("Pizza", "pizzacat", "pza"));
        categoryList.add(new CategoryDomain("Burger", "burgercat", "bgr"));
        categoryList.add(new CategoryDomain("Hotdog", "hotdogcat", "htd"));
        categoryList.add(new CategoryDomain("Drink", "drinkcat", "drk"));
        categoryList.add(new CategoryDomain("Donut", "donutcat", "dnt"));

        adapter = new CategoryAdapter(categoryList, position -> {
            System.out.println("DICLICK");
            String categoryId = categoryList.get(position).getId();
            getDatabyCategory(categoryId);
        });
        recyclerViewCategoryList.setAdapter(adapter);
    }
}