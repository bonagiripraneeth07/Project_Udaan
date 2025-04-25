package com.udaan.udaanapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.udaan.udaanapplication.model.Tutoring;
import com.udaan.udaanapplication.retrofit.RetrofitService;
import com.udaan.udaanapplication.retrofit.TutorController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddTutoring extends AppCompatActivity {
EditText subjectEditText,sheduleEdittext;
Button sheduleBtn ;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tutoring);
        subjectEditText = findViewById(R.id.subjectEditText);
        sheduleEdittext = findViewById(R.id.sheduleEditext);
        sheduleBtn = findViewById(R.id.sheduleBtn);

        sheduleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String > subjects =  new ArrayList<>();
                LocalDate postedDateString = LocalDate.now();
                String postedDate = postedDateString.toString();
                String sheduleTiming = sheduleEdittext.getText().toString() ;
                subjects.add(subjectEditText.getText().toString());
                    SharedPreferences sharedPreferences = getSharedPreferences("tutorId",MODE_PRIVATE);
                    int id = sharedPreferences.getInt("id",0);
                    System.out.println( "id"+ id);
                SharedPreferences sharedPreferences2 = getSharedPreferences("userCredentials",MODE_PRIVATE);


                String username = sharedPreferences2.getString("userName", null);
                int userId = sharedPreferences2.getInt("userId",0);
                Intent i2;

                    RetrofitService retrofitService = new RetrofitService();
                    TutorController tutorController = retrofitService.getRetrofit().create(TutorController.class);
                    Tutoring tutoring = new Tutoring();
                    System.out.println(userId + " from 65");
                    if(!sheduleTiming.isEmpty() && !subjects.isEmpty() && id!=0 || userId!=0){
                        // post data through API
                        System.out.println(sheduleTiming);
                        System.out.println(postedDate);
                        System.out.println(subjects);
                        if (userId!=0){
                            tutoring.setTutorId(userId);
                        }else{
                            tutoring.setTutorId(id);
                        }

                        tutoring.setPostedDate( postedDate);
                        tutoring.setSubject(subjects);
                        tutoring.setPreferredTime(sheduleTiming);

                        tutorController.addTutoring(tutoring)
                                .enqueue(new Callback<ResponseBody>() {
                                    @Override
                                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                        System.out.println(response.body().toString());
                                        Toast.makeText(AddTutoring.this, "Tutoring added successfully", Toast.LENGTH_LONG).show();
//                                        Intent i = new Intent(AddTutoring.this, LoginActivity.class);
//                                        startActivity(i);
                                        Intent i;
                                        if (userId!=0) {

                                            i=new Intent(AddTutoring.this, Home.class);

                                        } else {

                                            i=new Intent(AddTutoring.this,LoginActivity.class);
                                        }

                                        startActivity(i);
                                    }

                                    @Override
                                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                                        System.out.println(t.getMessage());
                                        Toast.makeText(AddTutoring.this, "somthing went wrong", Toast.LENGTH_SHORT).show();
                                    }
                                });
                    }else{
                    Toast.makeText(AddTutoring.this, "Please Fill-in the details", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }
}