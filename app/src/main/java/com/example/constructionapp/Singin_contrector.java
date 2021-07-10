package com.example.constructionapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

public class Singin_contrector extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singin_contrector);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();


        /////////////////////////hooks//////////////////////////////////////////////

        EditText email_login,password_login;
        Button login_btn;
        ProgressBar progress;
        FirebaseAuth mauth ;
        mauth = FirebaseAuth.getInstance();

        email_login= findViewById(R.id.Email_Login_box);
        password_login = findViewById(R.id.Password_login_box);
        login_btn= findViewById(R.id.Login_btn);
        progress= findViewById(R.id.progressBar2);
        TextView to_singup = findViewById(R.id.textview_sinup);


        //////////////////////////////////////////////////////////////////////////

        to_singup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Register_contrector.class);
                startActivity(intent);
            }
        });


        //////////////////////////////////////////////button/////////////////////////////




        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email_test = email_login.getText().toString().trim();
                String password_test = password_login.getText().toString().trim();

                if (email_test.isEmpty()) {
                    email_login.setError("Please Enter Email");
                    email_login.requestFocus();
                    return;
                }
                if (password_test.isEmpty())
                {
                    password_login.setError("Please Enter Password");
                    password_login.requestFocus();
                    return;
                }

              progress.setVisibility(view.VISIBLE);

              mauth.signInWithEmailAndPassword(email_test, password_test)
                      .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                          @Override
                          public void onComplete(@NonNull Task<AuthResult> task) {
                              if (task.isSuccessful())
                              {


                                  Intent intent = new Intent(getApplicationContext(), Contrector_dashboard.class);
                                  startActivity(intent);

                              }
                              else {

                                  Toast.makeText(Singin_contrector.this, "Login Failed Check email and Password", Toast.LENGTH_LONG).show();
                                  progress.setVisibility(View.GONE);
                              }


                          }
                      });



                }


        });



    }
}