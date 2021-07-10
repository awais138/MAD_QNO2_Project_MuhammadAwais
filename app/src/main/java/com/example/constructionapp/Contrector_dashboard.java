package com.example.constructionapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.security.Key;

public class Contrector_dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contrector_dashboard);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        /////////////////////////////hooks////////////////////////////////////////////////////

        TextView welcome_box;
        FirebaseUser user;
        DatabaseReference ref, ref2;
        EditText fullname_post,Contact_post,City_post,price_post, Description;
        ProgressBar process;

        ///////////////////////////////////////////////////////////////////////
        fullname_post= findViewById(R.id.fullname_post);
        Contact_post= findViewById(R.id.contact_post);
        City_post= findViewById(R.id.city_post);
        price_post= findViewById(R.id.price_post);
        process= findViewById(R.id.progressBar3);
        Description= findViewById(R.id.Description_1);






        String User_Id;

        user = FirebaseAuth.getInstance().getCurrentUser();
        ref= FirebaseDatabase.getInstance().getReference("Users");
        User_Id= user.getUid();
        ref2 =FirebaseDatabase.getInstance().getReference("orders");



        welcome_box= findViewById(R.id.Welcome_box);




        //////////////////////////////////////////////Firebase/////////////////////////////////


        ref.child(User_Id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                user userprofile = snapshot.getValue(user.class);

                if (userprofile != null){

                    String user_name = userprofile.username;

                    welcome_box.setText("Welcome "+ user_name);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Contrector_dashboard.this, "SomeThing Went Wrong", Toast.LENGTH_LONG).show();


            }
        });


        ////////////////////////////////////////////////////////welcom box btn////////////



        Button save_btn = findViewById(R.id.savebtn_to_contrectorinfo);
        save_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                process.setVisibility(View.VISIBLE);

                String full_name = fullname_post.getText().toString().trim();
                String contact_info = Contact_post.getText().toString().trim();
                String city_info = City_post.getText().toString().trim();
                String price_info = price_post.getText().toString().trim();
                String description_info= Description.getText().toString().trim();


                if (full_name.isEmpty())
                {
                    fullname_post.setError("Enter your fullname");
                    fullname_post.requestFocus();
                    return;

                }



                post_request post = new post_request(description_info, full_name, contact_info,city_info,price_info );
             ref2.child(FirebaseAuth.getInstance()
                     .getCurrentUser().getUid()).setValue(post).addOnCompleteListener(new OnCompleteListener<Void>() {
                 @Override
                 public void onComplete(@NonNull Task<Void> task) {

                     if (task.isSuccessful())
                     {
                         Toast.makeText(Contrector_dashboard.this, "Request Posted Sucessfully", Toast.LENGTH_LONG).show();
                         Intent intent = new Intent(getApplicationContext(), Contrector_info.class);
                         startActivity(intent);

                     }

                 }
             });







            }


        });




    }
}