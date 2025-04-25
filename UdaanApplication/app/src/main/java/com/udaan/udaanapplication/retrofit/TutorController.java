package com.udaan.udaanapplication.retrofit;

import com.android.volley.ResponseDelivery;
import com.udaan.udaanapplication.model.Tutor;
import com.udaan.udaanapplication.model.TutorLogin;
import com.udaan.udaanapplication.model.TutorWrapper;
import com.udaan.udaanapplication.model.Tutoring;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface TutorController {

    @GET("tutor/gettutors/{location}/{pincode}")
    Call<List<TutorWrapper>> getTutors(  @Path("location") String location,
                                         @Path("pincode") int pincode);
    @GET("tutor/gettutor/{tutorId}")
    Call<TutorWrapper>getTutor(@Path("tutorId")int tutorId);
    @DELETE("tutor/deletetutoring/{id}")
    Call<ResponseBody> deleteTutoring(@Path("id")int id);

    @POST("tutor/tutoring")
    Call<ResponseBody> addTutoring(@Body Tutoring tutoring);
    @POST("tutor/login")
    Call<TutorWrapper> tutorLogin(@Body TutorLogin tutorLogin);
    @Multipart
    @POST("tutor/register")
    Call<Tutor> registerTutor( @Part MultipartBody.Part imageFile,
                               @Part("tutor") RequestBody tutorRequestBody);


}
