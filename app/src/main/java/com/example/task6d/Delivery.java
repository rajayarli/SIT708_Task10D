package com.example.task6d;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.task6d.model.Item;

public class Delivery extends AppCompatActivity {

    EditText receiverNameTextView;
    EditText pickupDateTextView;
    EditText pickupTimeTextView;
    EditText pickupLocationTextView;
    Button nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delivery);

        receiverNameTextView = findViewById(R.id.editTextText4);
        pickupDateTextView = findViewById(R.id.editTextDate2);
        pickupTimeTextView = findViewById(R.id.editTextTime);
        pickupLocationTextView = findViewById(R.id.editTextTextPostalAddress);
        nextButton = findViewById(R.id.button2);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String receiver = receiverNameTextView.getText().toString().trim();
                String location = pickupLocationTextView.getText().toString().trim();
                Item item = new Item(receiver, location);
                Intent intent = new Intent(Delivery.this, More.class);
                intent.putExtra("item", item);
                intent.putExtra("receiver",receiver);
                intent.putExtra("location",location);

                startActivity(intent);
            }
        });
    }
}