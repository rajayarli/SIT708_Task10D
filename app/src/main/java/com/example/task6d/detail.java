package com.example.task6d;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.task6d.model.Item;

public class detail extends AppCompatActivity {
    private TextView detail_receiver;
    private TextView detail_location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        detail_location = findViewById(R.id.detaillocation);
        detail_receiver = findViewById(R.id.detailtitle);

        Intent intent = getIntent();
        Item item = (Item) intent.getSerializableExtra("item");

        if (item != null) {
            String receiver = item.getReceiver();
            String location = item.getLocation();

            detail_receiver.setText(receiver);
            detail_location.setText(location);
        }
    }
}
