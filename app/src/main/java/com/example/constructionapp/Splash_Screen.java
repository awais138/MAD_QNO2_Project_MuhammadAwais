package com.example.constructionapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;

import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.DoubleBounce;

public class Splash_Screen extends AppCompatActivity {

        String prevStarted = "yes";
        @Override
        protected void onResume() {
            super.onResume();
            SharedPreferences sharedpreferences = getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE);
            if (!sharedpreferences.getBoolean(prevStarted, false)) {
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putBoolean(prevStarted, Boolean.TRUE);
                editor.apply();

                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        ProgressBar progressBar = (ProgressBar)findViewById(R.id.spin_kit);
                        Sprite doubleBounce = new DoubleBounce();
                        progressBar.setIndeterminateDrawable(doubleBounce);

                        Intent mInHome = new Intent(Splash_Screen.this, MainActivity.class);
                        Splash_Screen.this.startActivity(mInHome);
                        Splash_Screen.this.finish();
                    }
                }, 6000);


            } else {
                moveToSecondary();
            }
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_splash__screen);
        }

        public void moveToSecondary() {
            // use an intent to travel from one activity to another.
            Intent intent = new Intent(this,Select_mode.class);
            startActivity(intent);
        }
    }

