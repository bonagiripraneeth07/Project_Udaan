package com.udaan.udaanapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.libraries.places.widget.Autocomplete;
import com.google.gson.Gson;
import com.udaan.udaanapplication.model.ContactDetails;
import com.udaan.udaanapplication.model.Tutor;
import com.udaan.udaanapplication.retrofit.RetrofitService;
import com.udaan.udaanapplication.retrofit.TutorController;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterTutor extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST = 1;
    private Uri imageUri;

    EditText usernameEditText, passwordEditText, confirmPasswordEditText, pincodeEditText, educationEditText, addressEditText, emailEditText, mobileNumberEditText;
    AutoCompleteTextView locationEditText;
    Button registerBtn, chooseImageBtn;
    RadioGroup radioGroup;
    ImageView imageView2;
    RadioButton maleButton, femaleButton;
    MultipartBody.Part imagePart ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_tutor);
        imageView2 = findViewById(R.id.imageview2);
        radioGroup = findViewById(R.id.genderGroup);
        radioGroup.clearCheck();
        registerBtn = findViewById(R.id.registerBtn);
        maleButton = findViewById(R.id.radioMale);
        femaleButton = findViewById(R.id.radioFemale);
        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        confirmPasswordEditText = findViewById(R.id.confirmpassword);
        locationEditText = findViewById(R.id.locationEdittext);
        pincodeEditText = findViewById(R.id.pincodeEdittext);
        educationEditText = findViewById(R.id.education);
        chooseImageBtn = findViewById(R.id.chooseImage);
        addressEditText = findViewById(R.id.addressEdittext);
        emailEditText = findViewById(R.id.email);
        mobileNumberEditText = findViewById(R.id.phonenumber);

         locationEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence query, int start, int before, int count) {
                if (query.length() > 1) {
                    fetchSuggestions(query.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        chooseImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openImageChooser();
            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName;
                String password;
                String confirmPassword;
                String place;
                int pincode;
                String gender;
                String education;
                String address;
                String email;
                Long mobileNumber;
                userName = usernameEditText.getText().toString();
                password = passwordEditText.getText().toString();
                confirmPassword = confirmPasswordEditText.getText().toString();
                place = locationEditText.getText().toString();
                String pincodeString = pincodeEditText.getText().toString();

                education = educationEditText.getText().toString();
                address = addressEditText.getText().toString();
                email = emailEditText.getText().toString();
               String mobileNumberString = mobileNumberEditText.getText().toString();
                if (femaleButton.isChecked()) {
                    gender = "Female";
                } else if(maleButton.isChecked()) {
                    gender = "Male";
                } else{
                    gender ="";
                }

//                Toast.makeText(RegisterTutor.this, gender, Toast.LENGTH_SHORT).show();
                RetrofitService retrofitService = new RetrofitService();
                TutorController tutorController = retrofitService.getRetrofit().create(TutorController.class);
                Tutor tutor = new Tutor();
                ContactDetails contactDetails = new ContactDetails();
                if (!userName.isEmpty() && !password.isEmpty() && !confirmPassword.isEmpty() && !place.isEmpty() && !pincodeString.isEmpty() && !education.isEmpty() && !address.isEmpty() && !email.isEmpty() && !mobileNumberString.isEmpty() && !gender.isEmpty()) {
                    //register user through API

                    if (pincodeString.length()!=6){
                        Toast.makeText(RegisterTutor.this, "check your pincode", Toast.LENGTH_SHORT).show();
                    }else if(!password.equals(confirmPassword)){
                        Toast.makeText(RegisterTutor.this,"check your password",Toast.LENGTH_LONG).show();
                    }else{
                        pincode = Integer.parseInt(pincodeString);
                        mobileNumber = Long.parseLong(mobileNumberString);

                        System.out.println(userName );
                        System.out.println(password );
                        System.out.println(confirmPassword );
                        System.out.println(place );
                        System.out.println(pincode );
                        System.out.println(education );
                        System.out.println(address);
                        System.out.println(email );
                        System.out.println(mobileNumber );
                        System.out.println(gender );

                        String response = "CREATED";

                        tutor.setName(userName);
                        tutor.setPassword(password);
                        tutor.setLocation(place);
                        tutor.setPincode(pincode);
                        tutor.setEducation(education);
                        tutor.setGender(gender);
                         contactDetails.setAddress(address);
                         contactDetails.setEmail(email);
                         contactDetails.setPhoneNumber(mobileNumber);
                         tutor.setContactDetails(contactDetails);
                        SharedPreferences sharedPreferences = getSharedPreferences("tutorId",MODE_PRIVATE);
                        @SuppressLint("CommitPrefEdits") SharedPreferences.Editor editor = sharedPreferences.edit();
                        Gson gson = new Gson();
                        String tutorJsonString = gson.toJson(tutor); // your Tutor object
                        RequestBody tutorRequestBody = RequestBody.create(MediaType.parse("application/json"), tutorJsonString);
                        tutorController.registerTutor(imagePart,tutorRequestBody)
                                .enqueue(new Callback<Tutor>() {
                                    @Override
                                    public void onResponse(Call<Tutor> call, Response<Tutor> response) {
                                        Tutor tutor1 = response.body();
                                        System.out.println("success" + tutor1.getId());
                                        System.out.println("name" + tutor1.getName());
                                        int tutorId =tutor1.getId();

                                        editor.putInt("id",tutorId);
                                        editor.apply();
                                        System.out.println();
                                        Intent i = new Intent(RegisterTutor.this, AddTutoring.class);
                                        startActivity(i);
                                    }

                                    @Override
                                    public void onFailure(Call<Tutor> call, Throwable t) {
                                        System.out.println(t.getMessage());
                                    }
                                });

                        // TO BE CHANGED
//                        if(response.equals("CREATED")){
//                            int tutorId = 19;                   //to be changed
//                            SharedPreferences sharedPreferences = getSharedPreferences("tutorId",MODE_PRIVATE);
//                            SharedPreferences.Editor editor = sharedPreferences.edit();
//                            editor.putInt("tutorId",tutorId);
//                            Intent i = new Intent(RegisterTutor.this, AddTutoring.class);
//                            startActivity(i);
//                        }else{
//                            Toast.makeText(RegisterTutor.this, "something went wrong ", Toast.LENGTH_SHORT).show();
//                        }
                    }



                }else{
                    Toast.makeText(RegisterTutor.this, "Plese fill-in the details", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    private MultipartBody.Part prepareFilePart(String partName, Uri fileUri) {

        File file = new File(getRealPathFromURI(fileUri));
        RequestBody requestFile = RequestBody.create(MediaType.parse("image/*"), file);

        return MultipartBody.Part.createFormData(partName, file.getName(), requestFile);
    }
    private String getRealPathFromURI(Uri contentUri) {
        Cursor cursor = getContentResolver().query(contentUri, null, null, null, null);
        if (cursor == null) {
            return contentUri.getPath();
        } else {
            cursor.moveToFirst();
            int index = cursor.getColumnIndex(MediaStore.Images.Media.DATA);
            String realPath = cursor.getString(index);
            cursor.close();
            return realPath;
        }
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
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(RegisterTutor.this,
                            android.R.layout.simple_dropdown_item_1line, suggestions);
                    locationEditText.setAdapter(adapter);
                    locationEditText.showDropDown();
                });

            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

    }
    private void openImageChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();
            imageView2.setImageURI(imageUri); // show the image
            imagePart=prepareFilePart("imageFile", imageUri);
        }
    }


}
