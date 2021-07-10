package com.example.constructionapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Customer_info_dash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_info_dash);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        TextView textview_review_activity, Contact, Descripiton;
        textview_review_activity = findViewById(R.id.review_name);
        Descripiton = findViewById(R.id.Descriptioin_PR);
        Contact= findViewById(R.id.contact_PR);
        ImageView whatsapp= findViewById(R.id.image_whatsapp);
        ImageView chat_togo =findViewById(R.id.chat_togo);
        TextView map = findViewById(R.id.map_info);


        String name= getIntent().getStringExtra("Name");
        String description= getIntent().getStringExtra("Description");
        String contact= getIntent().getStringExtra("Contact");
        String map1= getIntent().getStringExtra("Map");
        textview_review_activity.setText(name);
        Descripiton.setText(description);
        Contact.setText(contact);
        map.setText(map1);



        chat_togo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Customer_info_dash.this, "Under Construction", Toast.LENGTH_SHORT).show();


            }
        });

        whatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotourl("https://Wa.me/+92"+contact);

            }

            private void gotourl(String s) {

                Uri uri = Uri.parse(s);
                startActivity(new Intent(Intent.ACTION_VIEW, uri));
            }
        });




    }
}