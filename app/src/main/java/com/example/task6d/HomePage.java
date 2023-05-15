package com.example.task6d;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.task6d.model.Item;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class HomePage extends AppCompatActivity {
    ImageButton imageButton;
    Item item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);
        imageButton = findViewById(R.id.imageButton2);
        ActionBar actionBar = getSupportActionBar();
        item = (Item) getIntent().getSerializableExtra("item");

        // Create an ArrayList to store the items
        ArrayList<Item> itemList = new ArrayList<>();
        // Add the item to the list
        itemList.add(item);

        AA_recyclerViewAdapter adapter;
        if (item != null) {
            adapter = new AA_recyclerViewAdapter(this, itemList);
        } else {
            adapter = new AA_recyclerViewAdapter(this, new ArrayList<>()); // Provide an empty list if item is null
        }

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.home) {
                    Toast.makeText(HomePage.this, "Home Page", Toast.LENGTH_SHORT).show();
                } else if (id == R.id.account) {
                    Toast.makeText(HomePage.this, "Account Page", Toast.LENGTH_SHORT).show();
                } else if (id == R.id.myorders) {
                    Toast.makeText(HomePage.this, "Orders Page", Toast.LENGTH_SHORT).show();
                }

                return true;
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomePage.this, Delivery.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.home) { // Adjusted menu item ID
            Toast.makeText(this, "Home Clicked", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.account) { // Adjusted menu item ID
            Toast.makeText(this, "Account Clicked", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.myorders) { // Adjusted menu item ID
            Toast.makeText(this, "My Orders Clicked", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }
}
