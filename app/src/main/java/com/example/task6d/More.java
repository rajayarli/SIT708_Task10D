package com.example.task6d;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.task6d.model.Item;

public class More extends AppCompatActivity {
    Button order;
    Item item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.moredetails);
        order = findViewById(R.id.button3);
        item = (Item) getIntent().getSerializableExtra("item");

        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(More.this, "Successfully ordered!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(More.this, HomePage.class);
                intent.putExtra("item", item);
                startActivity(intent);
            }
        });
    }
}