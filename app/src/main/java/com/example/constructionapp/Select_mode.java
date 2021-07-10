package com.example.constructionapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Select_mode extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_mode);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        Button contractor_btn = findViewById(R.id.contractor_btn);
        Button customer_btn = findViewById(R.id.customer_btn);
        contractor_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), contractor_login.class);
                startActivity(intent);
                finish();


            }


        });

        customer_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Customer_login.class);
                startActivity(intent);
                finish();

            }
        });


    }

}