package com.udaan.udaanapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.udaan.udaanapplication.model.TutorWrapper;
import com.udaan.udaanapplication.model.Tutoring;
import com.udaan.udaanapplication.retrofit.RetrofitService;
import com.udaan.udaanapplication.retrofit.TutorController;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Home2 extends AppCompatActivity {
ImageView noTutorsFoundIMage;
TextView tutoringsNotFound,yourTutoringsHeading,setLocationTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);
        SharedPreferences sharedPreferences = getSharedPreferences("tempUser",MODE_PRIVATE);
        String userName = sharedPreferences.getString("userName",null);
        String location = sharedPreferences.getString("location",null);
        System.out.println(location +" fromhome2");
        System.out.println(userName + " from home 2");
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        tutoringsNotFound=findViewById(R.id.tutoringNotFoundTextView);
        noTutorsFoundIMage = findViewById(R.id.tutorNotFoundImage);
        yourTutoringsHeading=findViewById(R.id.yourTutoringsHeading);
        setLocationTextView = findViewById(R.id.locationTextView);
        int pincode = sharedPreferences.getInt("pincode",0);
        setLocationTextView.setText(location);
         System.out.println(pincode +" from home 2");
//       fetchTutors(location,pincode);
        recyclerView.setLayoutManager(new LinearLayoutManager(Home2.this));
        SharedPreferences tutorSharedPreferences = getSharedPreferences("tutorIdShared",MODE_PRIVATE);


        RetrofitService retrofitService = new RetrofitService();
        TutorController tutorController = retrofitService.getRetrofit().create(TutorController.class);
        tutorController.getTutors(location,pincode)
                .enqueue(new Callback<List<TutorWrapper>>() {
                    @Override
                    public void onResponse(Call<List<TutorWrapper>> call, Response<List<TutorWrapper>> response) {
                        if (response.isSuccessful()) {
                            List<TutorWrapper> tutorList = response.body();
                            String userName ;
                            List<String > subjects;
                            String preferredTime;
                            String date;
                            int userId ;
                            // Now you have your tutors
//                            List<Tutoring> tutorings = new ArrayList<>();
//                            for (TutorWrapper tutor : tutorList) {
//                                System.out.println(tutor.getName() + " from tutor");
//                                userName= tutor.getName();
//                                 userId = tutor.getTutorId();
//                                 subjects = findSubject(tutor.getTutorings());
//                                 date =findDate( tutor.getTutorings());
//                                 preferredTime =
//                                System.out.println(userName + " tutorname");
//                                System.out.println(userId + " tutorId");
//                                System.out.println(preferredTime +" prefer");
//
//                            }

                            List<TutoringItem> tutoringItemList = new ArrayList<>();

                            for (TutorWrapper tutor : tutorList) {
                                if (tutor.getTutorings() != null) {
                                    for (Tutoring tutoring : tutor.getTutorings()) {
                                        TutoringItem item = new TutoringItem(
                                                tutor.getName(),
                                                tutor.getTutorId(),
                                                tutoring.getPreferredTime(),
                                                tutoring.getPostedDate(),
                                                tutoring.getSubject()
                                        );
                                        tutoringItemList.add(item);
                                    }
                                }
                            }

                            System.out.println("Tutoring Items Size: " + tutoringItemList.size());
                            if(tutoringItemList.size() ==0){
                                tutoringsNotFound.setVisibility(View.VISIBLE);
                                noTutorsFoundIMage.setVisibility(View.VISIBLE);
                                yourTutoringsHeading.setVisibility(View.GONE);
                            }

                            TutoringAdapter adapter = new TutoringAdapter(tutoringItemList, item -> {
                                SharedPreferences.Editor editor = tutorSharedPreferences.edit();
                                editor.putInt("userId",item.getTutorId());
                                editor.apply();
                                Toast.makeText(Home2.this, "Clicked on Tutor ID: " + item.getTutorId(), Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(Home2.this, TutorDetails.class);
                                startActivity(i);
                            });

                            recyclerView.setAdapter(adapter);

                            // 🔥 Very important:
                            adapter.notifyDataSetChanged(); // tell RecyclerView to refresh all!




                        } else {
                            Log.e("Error", "Server returned error: " + response.code());
                            Toast.makeText(Home2.this, response.code(), Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<List<TutorWrapper>> call, Throwable t) {
                        Toast.makeText(Home2.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                        System.out.println(t.getMessage() + " error");

                    }
                });

    }

    public void  fetchTutors(String location, int pincode){


    }

    private List<String> findSubject(List<Tutoring> tutoring){
        List<String >subjects = new ArrayList<>() ;
        for (Tutoring t : tutoring){
            subjects.add(t.getSubject().toString());
        }
        return subjects;
    }
    private  String findDate(List<Tutoring>tutoring){
        String date=null;
        for(Tutoring t : tutoring){
            date= t.getPostedDate();
        }
        return date;
    }


}