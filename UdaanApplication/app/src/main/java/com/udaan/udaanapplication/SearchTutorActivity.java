package com.udaan.udaanapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

public class SearchTutorActivity extends AppCompatActivity {
     AutoCompleteTextView placeSearch;
     EditText usernameEditText,pincodeEdittext;
     Button searchTutorBtn;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_tutor);
        placeSearch = findViewById(R.id.locationEdittext);
        usernameEditText = findViewById(R.id.usernameEditText);
        pincodeEdittext = findViewById(R.id.pincodeEdittext);
        searchTutorBtn = findViewById(R.id.searchTutorBtn);

        placeSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence query, int start, int before, int count) {
                if (query.length() > 1) {
                    fetchSuggestions(query.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        SharedPreferences sharedPreferences = getSharedPreferences("tempUser",MODE_PRIVATE);

        searchTutorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i ;
               String   userName= usernameEditText.getText().toString();
                 String location   =placeSearch.getText().toString();
                 String pincodeString =pincodeEdittext.getText().toString();

                if(!userName.isEmpty() && !location.isEmpty() && !pincodeString.isEmpty() && pincodeString.length()==6){
                    System.out.println(pincodeString.length());

                    int pincode = Integer.parseInt(pincodeString);

                    System.out.println(userName + "username");
                    System.out.println(location + "location");
                    System.out.println(pincode + " pincode");
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("userName",userName);
                    editor.putInt("pincode",pincode);
                    editor.putString("location",location);
                    editor.apply();
                    i= new Intent(SearchTutorActivity.this, Home2.class);
                    startActivity(i);
                 }else if(pincodeString.length()!=6){
                    Toast.makeText(SearchTutorActivity.this, "Please check pincode", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(SearchTutorActivity.this, "Fill in the details", Toast.LENGTH_SHORT).show();
                }

            }
        });



    }
    private void fetchSuggestions(String query) {
        new Thread(() -> {
            try {
                String encodedQuery = URLEncoder.encode(query, "UTF-8");
                String apiUrl = "https://nominatim.openstreetmap.org/search?q=" + encodedQuery + "&format=json&addressdetails=1";

                URL url = new URL(apiUrl);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setRequestProperty("User-Agent", "Mozilla/5.0");

                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder result = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }
                reader.close();

                JSONArray jsonArray = new JSONArray(result.toString());
                ArrayList<String> suggestions = new ArrayList<>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject obj = jsonArray.getJSONObject(i);
                    suggestions.add(obj.getString("display_name"));
                }

                runOnUiThread(() -> {
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(SearchTutorActivity.this,
                            android.R.layout.simple_dropdown_item_1line, suggestions);
                    placeSearch.setAdapter(adapter);
                    placeSearch.showDropDown();
                });

            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

    }
}