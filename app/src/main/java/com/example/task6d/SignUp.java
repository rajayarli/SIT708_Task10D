package com.example.task6d;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.task6d.model.User;

public class SignUp extends AppCompatActivity {
    private static final int REQUEST_CODE = 1;
    private Button createAccountButton;
    private ImageButton imagebutton;
    private EditText username, fullname;
    private EditText password, confirmPass;
    private EditText no;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        imagebutton = findViewById(R.id.imageButton);
        username = findViewById(R.id.editTextText);
        fullname = findViewById(R.id.editTextText2);
        password = findViewById(R.id.editTextTextPassword);
        confirmPass = findViewById(R.id.editTextTextPassword2);
        no = findViewById(R.id.editTextPhone);
        createAccountButton = findViewById(R.id.button);
        dbHelper = new DatabaseHelper(this);
    }

    public void uploadImage(View view) {
        pickImageFromMediaStore();
    }

    private void pickImageFromMediaStore() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();
            imagebutton.setImageURI(uri);
        }
    }

    public void validateEmptyInput(View view) {
        String uname = username.getText().toString().trim();
        if (TextUtils.isEmpty(uname)) {
            Toast.makeText(this, "Please enter username", Toast.LENGTH_SHORT).show();
            return;
        } else {
            String fname = fullname.getText().toString().trim();
            if (TextUtils.isEmpty(fname)) {
                Toast.makeText(this, "Please enter full name", Toast.LENGTH_SHORT).show();
                return;
            } else {
                String pass = password.getText().toString().trim();
                if (TextUtils.isEmpty(pass)) {
                    Toast.makeText(this, "Enter a valid password", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    String cpass = confirmPass.getText().toString().trim();
                    if (TextUtils.isEmpty(cpass)) {
                        Toast.makeText(this, "Enter a valid password", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        if (!pass.equals(cpass)) {
                            Toast.makeText(this, "Password should be same as above password", Toast.LENGTH_SHORT).show();
                            return;
                        } else {

                            String phone = no.getText().toString().trim();
                            if (TextUtils.isEmpty(phone)) {
                                Toast.makeText(this, "Enter a valid phone number", Toast.LENGTH_SHORT).show();
                                return;
                            }

                        }
                        // Insert user data into the database

                        User user = new User(username.getText().toString(), fullname.getText().toString(), password.getText().toString(), no.getText().toString());

                        int result = dbHelper.insertUser(user);

                        if (!(result == 1)) {
                            Toast.makeText(this, "Error occurred while inserting data!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(this, "Data inserted successfully", Toast.LENGTH_SHORT).show();
                            // Redirect the user to the main activity
                            Intent intent = new Intent(this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }

                    }
                }
            }
        }
    }
}
