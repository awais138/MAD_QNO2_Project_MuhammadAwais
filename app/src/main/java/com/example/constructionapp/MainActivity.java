package com.example.constructionapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.Button_to_term);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        ImageView buttonOne = findViewById(R.id.Button_to_term);
        buttonOne.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), term_and_Conditions.class);
                startActivity(intent);




            }


        });
    }
}