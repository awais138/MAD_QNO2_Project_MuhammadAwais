package com.example.constructionapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.DoubleBounce;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Customer_login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_login);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();



        ////////////////////////////////////////animated progress bar//////////

        ProgressBar progressBar = (ProgressBar)findViewById(R.id.spin_kit);


        ////////////////////////////////firebase////////////////////////////

        DatabaseReference ref;
        ref =FirebaseDatabase.getInstance().getReference().child("orders");
        FirebaseRecyclerOptions<model_class_to_post> options;
        FirebaseRecyclerAdapter<model_class_to_post, Myviewholder> adapter;
        RecyclerView recyclerView;

        recyclerView= findViewById(R.id.recycler_view_message);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        RecyclerView view = findViewById(R.id.recycler_view_message);
        view.setHasFixedSize(true);
        view.setLayoutManager(new LinearLayoutManager(this));



        options= new FirebaseRecyclerOptions.Builder<model_class_to_post>().setQuery(ref,model_class_to_post.class).build();
        adapter= new FirebaseRecyclerAdapter<model_class_to_post, Myviewholder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull Myviewholder holder, int position, @NonNull model_class_to_post model) {

                final String name=model.getFullname();
                final String Description= model.getDescription();
                final String contact = model.getContactno();
                final String map = model.getCity();

                holder.view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent= new Intent(getApplicationContext(),Customer_info_dash.class);
                        intent.putExtra("Name",name);
                        intent.putExtra("Description",Description);
                        intent.putExtra("Contact",contact);
                        intent.putExtra("Map",map);
                        startActivity(intent);

                    }
                });


                holder.name_id.setText("posted By:  "+ model.getFullname());

                holder.textview_detail.setText(" Contact No: " +model.getContactno() + " Price: "+ model.getPrice());



            }

            @NonNull
            @Override
            public Myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

              View vs = LayoutInflater.from(parent.getContext()).inflate(R.layout.items,parent,false);
                return new Myviewholder(vs);
            }
        };

        adapter.startListening();
        Sprite doubleBounce = new DoubleBounce();
        progressBar.setIndeterminateDrawable(doubleBounce);


        recyclerView.setAdapter(adapter);






    }
}