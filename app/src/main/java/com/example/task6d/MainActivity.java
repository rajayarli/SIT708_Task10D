package com.example.task6d;


import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button signup,login;
    private EditText username1,password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signup = findViewById(R.id.signup);
        login = findViewById(R.id.login);
        username1 = findViewById(R.id.username1);
        password = findViewById(R.id.password);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SignUp.class);
                startActivity(intent);

            }
        });
        DatabaseHelper db = new DatabaseHelper(this);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = username1.getText().toString();
                String pass = password.getText().toString();

                boolean isValid = db.validateUser(username, pass);
                if (isValid) {
                    Intent intent = new Intent(MainActivity.this, HomePage.class);
                    startActivity(intent);
                    Toast.makeText(MainActivity.this, "Welcome Back "+username, Toast.LENGTH_SHORT).show();


                    // User credentials are valid; proceed with login
                } else {
                    Toast.makeText(MainActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();

                    // User credentials are invalid; show error message
                }
            }
        });





    }
}
