package com.udaan.udaanapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.udaan.udaanapplication.model.TutorWrapper;
import com.udaan.udaanapplication.model.Tutoring;
import com.udaan.udaanapplication.model.TutoringRecycle;
import com.udaan.udaanapplication.retrofit.RetrofitService;
import com.udaan.udaanapplication.retrofit.TutorController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Home extends AppCompatActivity {
    TextView location,userInfo,yourTutoringsHeading;
    LinearLayout fieldsContainer;
    ImageView userImage;
    ImageView profileImageView ;
    private MyAdapter adapter;
    RecyclerView recyclerView;
    List<TutoringRecycle> tutoringRecycle ;
Button deleteTutor,addTutoringButton,logoutButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        RetrofitService retrofitService = new RetrofitService();
        TutorController tutorController = retrofitService.getRetrofit().create(TutorController.class);
        SharedPreferences sharedPreferences = getSharedPreferences("userCredentials",MODE_PRIVATE);
        String userName = sharedPreferences.getString("userName",null);
        int  userId = sharedPreferences.getInt("userId",0);
        location = findViewById(R.id.locationTextView);
        //userInfo = findViewById(R.id.userInfo);
        fieldsContainer = findViewById(R.id.fieldsContainer);
        profileImageView = findViewById(R.id.profileImageView);
        recyclerView = findViewById(R.id.recyclerview);
        tutoringRecycle = new ArrayList<>();
        adapter = new MyAdapter(this, tutoringRecycle);
        recyclerView.setAdapter(adapter);
        addTutoringButton = findViewById(R.id.addTutoringButton);
        yourTutoringsHeading =findViewById(R.id.yourTutoringsHeading);
        logoutButton= findViewById(R.id.logoutButton);
        SharedPreferences tutorSharedPreferences = getSharedPreferences("tutorIdShared",MODE_PRIVATE);

        fieldsContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Home.this, "opening tutor details", Toast.LENGTH_SHORT).show();
                SharedPreferences.Editor editor = tutorSharedPreferences.edit();
                editor.putInt("userId",userId);
                editor.apply();
                Intent i = new Intent(Home.this,TutorDetailOwn.class);
                startActivity(i);
            }
        });
            addTutoringButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(Home.this,AddTutoring.class);
                    startActivity(i);
                }
            });
        if(userId!=0){
            tutorController.getTutor(userId)
                    .enqueue(new Callback<TutorWrapper>() {
                        @Override
                        public void onResponse(Call<TutorWrapper> call, Response<TutorWrapper> response) {
                            TutorWrapper tutorWrapper = response.body();
                            String userName = tutorWrapper.getName();
                            String education =tutorWrapper.getEducation();
                            Long mobileNumber = tutorWrapper.getPhoneNumber();
                            String address = tutorWrapper.getAddress();
                            String base64String = tutorWrapper.getImageData();
                            String email= tutorWrapper.getEmail();
                            byte[] decodedString = Base64.decode(base64String, Base64.DEFAULT);
                            System.out.println("user id "+ userId);

                            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                           if(tutorWrapper.getTutorings().size()==0){
                               yourTutoringsHeading.setText("No Tutorings found ");
                           }else {
                               for (Tutoring t : tutorWrapper.getTutorings()) {

                                   List<String> subjects = t.getSubject();
                                   String preferredTime = "Preferred Time : " + t.getPreferredTime();
                                   String postedDate = "Posted Date : " + t.getPostedDate();
                                   int tutoringId = t.getId();
                                   System.out.println(subjects);
                                   System.out.println(postedDate);
                                   System.out.println(preferredTime);
                                   runOnUiThread(() -> {
                                       addItem(subjects, preferredTime, postedDate, tutoringId);
                                   });

                               }
                           }
                            profileImageView.setImageBitmap(decodedByte);
                            Toast.makeText(Home.this, "name"+ tutorWrapper.getName(), Toast.LENGTH_SHORT).show();
                            location.setText(tutorWrapper.getLocation());
//
                            Map<String, String> userData = new HashMap<>();
                            userData.put("Email",email);
                            userData.put("Phone Number", String.valueOf(mobileNumber));
                            userData.put("Education",education);
                            userData.put("Address",address);
                            userData.put("Name",userName);

                            for (Map.Entry<String, String> entry : userData.entrySet()) {
                                addField(entry.getKey(), entry.getValue());
                            }

                        }

                        @Override
                        public void onFailure(Call<TutorWrapper> call, Throwable t) {
                            Toast.makeText(Home.this,t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        }else{
            Toast.makeText(this, "somehting went wrong", Toast.LENGTH_SHORT).show();
        }
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor preferencesEditor =  sharedPreferences.edit();
                preferencesEditor.clear();
                preferencesEditor.apply();
                Toast.makeText(Home.this, "logging out!", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(Home.this,LoginActivity.class);
                startActivity(i);
            }
        });

    }
    private void addField(String title, String value) {
        View fieldView = getLayoutInflater().inflate(R.layout.item_field, fieldsContainer, false);

        TextView titleTextView = fieldView.findViewById(R.id.titleTextView);
        TextView valueTextView = fieldView.findViewById(R.id.valueTextView);

        titleTextView.setText(title);
        valueTextView.setText(value);

        fieldsContainer.addView(fieldView);
    }
    private void addItem(List<String> subjects, String preferredTime, String postedDate,int tutoringId) {
        tutoringRecycle.add(new TutoringRecycle(subjects, preferredTime, postedDate,tutoringId));
        adapter.notifyItemInserted(tutoringRecycle.size() - 1);
        System.out.println(subjects + preferredTime + postedDate );

        System.out.println(subjects + preferredTime + postedDate);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }


}