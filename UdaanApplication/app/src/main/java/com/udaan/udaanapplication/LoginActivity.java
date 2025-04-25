package com.udaan.udaanapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.udaan.udaanapplication.model.TutorLogin;
import com.udaan.udaanapplication.model.TutorWrapper;
import com.udaan.udaanapplication.retrofit.RetrofitService;
import com.udaan.udaanapplication.retrofit.TutorController;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    EditText userNameEditText,passwordEditText;
    Button signupbutton,loginButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        signupbutton = findViewById(R.id.signupbutton);
        userNameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton=findViewById(R.id.loginButton);
        RetrofitService retrofitService = new RetrofitService();
        TutorController tutorController = retrofitService.getRetrofit().create(TutorController.class);
        TutorLogin tutorLogin = new TutorLogin();
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = userNameEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                System.out.println( "username "+ userName);
                System.out.println("password" + password);

                if(!userName.isEmpty() && !password.isEmpty())
                {
                    tutorLogin.setName(userName);
                    tutorLogin.setPassword(password);
                    tutorController.tutorLogin(tutorLogin)
                            .enqueue(new Callback<TutorWrapper>() {
                                @Override
                                public void onResponse(Call<TutorWrapper> call, Response<TutorWrapper> response) {

                                    TutorWrapper tutorWrapper = response.body();
                                    //System.out.println(tutorWrapper.getName()+ " name");
                                    //System.out.println(tutorWrapper.getTutorId() + " id");
                                    SharedPreferences sharedPreferences = getSharedPreferences("userCredentials",MODE_PRIVATE);
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    if(tutorWrapper.getName()!=null)
                                    {

                                    editor.putString("userName",userName);
                                    editor.putInt("userId",tutorWrapper.getTutorId());
                                    editor.apply();

                                    Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                    Intent i  = new Intent(LoginActivity.this,Home.class);
                                    startActivity(i);
                                    }else{
                                        Toast.makeText(LoginActivity.this, "Check Credentials", Toast.LENGTH_SHORT).show();
                                    }

                                }

                                @Override
                                public void onFailure(Call<TutorWrapper> call, Throwable t) {
                                    System.out.println(t.getMessage());
                                    Toast.makeText(LoginActivity.this, "Please check your Login credentials ", Toast.LENGTH_SHORT).show();
                                }
                            });
                }else{
                    Toast.makeText(LoginActivity.this, "please fill-in the details", Toast.LENGTH_SHORT).show();
                }
            }
        });


        signupbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                Intent i = new Intent(LoginActivity.this, AboutUdaan.class);
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