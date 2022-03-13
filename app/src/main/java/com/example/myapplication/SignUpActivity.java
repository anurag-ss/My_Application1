package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignUpActivity extends AppCompatActivity {

    private EditText emailSignUp , usernameSignUp , passwordSignUp;
    private Button signUpButton;
    private SQLiteHelper myDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        emailSignUp = findViewById(R.id.email);
        usernameSignUp = findViewById(R.id.userName);
        passwordSignUp = findViewById(R.id.password);

        signUpButton = findViewById(R.id.submitbutton);

        myDB = new SQLiteHelper(this);
        insertUser();
    }

    private void insertUser(){
        signUpButton.setOnClickListener(v -> {
            boolean var = myDB.registerUser(usernameSignUp.getText().toString() , emailSignUp.getText().toString() , passwordSignUp.getText().toString());
            if(var){
                Toast.makeText(SignUpActivity.this, "User Registered Successfully !!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(SignUpActivity.this, MainActivity.class));
            }
            else
                Toast.makeText(SignUpActivity.this, "Registration Error !!", Toast.LENGTH_SHORT).show();
        });
    }
}