package com.example.task6d;

import androidx.appcompat.app.AppCompatActivity;
import com.example.task6d.model.Item;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DetailActivity extends AppCompatActivity {
    Button estimate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        Item item = (Item) intent.getSerializableExtra("item");
        estimate = findViewById(R.id.button4);

        estimate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailActivity.this, MapActivity.class);
                startActivity(intent);
            }
        });
    }
}
