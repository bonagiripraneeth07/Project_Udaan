package com.udaan.udaanapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SwitchActivity extends AppCompatActivity {
    Button register_loginBtn ;
    Button searchTutorBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch);
        register_loginBtn =  findViewById(R.id.register_loginBtn);
      searchTutorBtn = findViewById(R.id.searchTutorBtn);

        searchTutorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  i = new Intent(SwitchActivity.this,SearchTutorActivity.class);
                startActivity(i);
            }
        });
        register_loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SwitchActivity.this, LoginActivity.class);
            startActivity(i);
            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }
}