package com.udaan.udaanapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
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
import com.udaan.udaanapplication.retrofit.RetrofitService;
import com.udaan.udaanapplication.retrofit.TutorController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TutorDetails extends AppCompatActivity {
    LinearLayout fieldsContainer;
    ImageView profileImageView;
    Button  locationButton,contactButton;
    private String address=null;
    private long contactNumber=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutoring_details);
        fieldsContainer = findViewById(R.id.fieldsContainer);
        profileImageView = findViewById(R.id.profileImageView);
        locationButton = findViewById(R.id.locationButton);
        SharedPreferences tutorSharedPreferences = getSharedPreferences("tutorIdShared",MODE_PRIVATE);
        int tutorId = tutorSharedPreferences.getInt("userId",0);
        RetrofitService retrofitService = new RetrofitService();
        TutorController tutorController = retrofitService.getRetrofit().create(TutorController.class);


        tutorController.getTutor(tutorId)
                .enqueue(new Callback<TutorWrapper>() {
                    @Override
                    public void onResponse(Call<TutorWrapper> call, Response<TutorWrapper> response) {
                        TutorWrapper tutorWrapper = response.body();
                        Map<String, String> userData = new HashMap<>();
                        String base64String = tutorWrapper.getImageData();
                        byte[] decodedString = Base64.decode(base64String, Base64.DEFAULT);
                        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                        profileImageView.setImageBitmap(decodedByte);
                        userData.put("Tutor Name",tutorWrapper.getName());
                        userData.put("Location",tutorWrapper.getLocation());
                        userData.put("Gender",tutorWrapper.getGender());
                        userData.put("Education",tutorWrapper.getEducation());
                        userData.put("Address",tutorWrapper.getAddress());
                        address=tutorWrapper.getAddress();
                        userData.put("Contact Number", String.valueOf(tutorWrapper.getPhoneNumber()));
                        contactNumber=tutorWrapper.getPhoneNumber();
                        userData.put("Email",tutorWrapper.getEmail());

//                        for (Tutoring t : tutorWrapper.getTutorings()){
//                            Map<String,Tutoring> userSubjects = new HashMap<>();
//                            userSubjects.put("tutoring",t);
//
//
//                        }
                        for (Map.Entry<String, String> entry : userData.entrySet()) {
                            addField(entry.getKey(), entry.getValue());
                        }



                    }


                    @Override
                    public void onFailure(Call<TutorWrapper> call, Throwable t) {
                        Toast.makeText(TutorDetails.this,t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        locationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (address != null && !address.isEmpty()) {
                    Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + Uri.encode(address));
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                    mapIntent.setPackage("com.google.android.apps.maps");

                    if (mapIntent.resolveActivity(getPackageManager()) != null) {
                        startActivity(mapIntent);
                    } else {
                        String mapsUrl = "https://www.google.com/maps/search/?api=1&query=" + Uri.encode(address);
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mapsUrl));
                        startActivity(browserIntent);                    }
                } else {
                    Toast.makeText(TutorDetails.this, "Location not available", Toast.LENGTH_SHORT).show();
                }
            }
        });
        contactButton=findViewById(R.id.contactButton);
        contactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + contactNumber));
                startActivity(intent);
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
}