package com.udaan.udaanapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //SharedPreferences pref =getSharedPreferences("name",MODE_PRIVATE);
                //Boolean check = pref.getBoolean("flag",false);
                //Intent in;
                // Get the SharedPreferences object
                SharedPreferences sharedPreferences = getSharedPreferences("userCredentials",MODE_PRIVATE);


                String username = sharedPreferences.getString("userName", null);
                int userId = sharedPreferences.getInt("userId",0);
                Intent i;
                if (userId!=0) {

                    i=new Intent(MainActivity.this,Home.class);


                } else {

                    i=new Intent(MainActivity.this, SwitchActivity.class);
                }
                startActivity(i);

            }
        }, 2000);
    }

}