package com.example.constructionapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class contractor_login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contractor_login);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        Button login_btn = findViewById(R.id.Login_btn);
        login_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Singin_contrector.class);
                startActivity(intent);




            }


        });
        Button Register_btn = findViewById(R.id.Register_contractor);
        Register_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Register_contrector.class);
                startActivity(intent);




            }


        });



    }
}