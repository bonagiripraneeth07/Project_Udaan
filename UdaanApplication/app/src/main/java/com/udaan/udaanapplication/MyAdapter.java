package com.udaan.udaanapplication;


import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.udaan.udaanapplication.model.TutoringRecycle;
import com.udaan.udaanapplication.retrofit.RetrofitService;
import com.udaan.udaanapplication.retrofit.TutorController;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyAdapter extends RecyclerView.Adapter<myviewholder> {
    Context context;
    List<TutoringRecycle> tutoringRecycle;
    Button deleteTutoring ;

    public MyAdapter(Context context, List<TutoringRecycle> tutoringRecycle) {
        this.context = context;
        this.tutoringRecycle = tutoringRecycle;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new myviewholder(LayoutInflater.from(context).inflate(R.layout.tutoring_recycle, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {
        holder.subjectView.setText(TextUtils.join(", ",tutoringRecycle.get(position).getSubject()));;
        holder.preferredTimeView.setText(tutoringRecycle.get(position).getPreferredTime());
        holder.postedDateView.setText(tutoringRecycle.get(position).getPreferredDate());
        holder.deleteTutoring.setOnClickListener(v -> {
            Toast.makeText(v.getContext(), "Tutoring ID: " + tutoringRecycle.get(position).getId(), Toast.LENGTH_SHORT).show();
           int id = tutoringRecycle.get(position).getId();
            RetrofitService retrofitService = new RetrofitService();
            TutorController tutorController = retrofitService.getRetrofit().create(TutorController.class);
            tutorController.deleteTutoring(id)
                    .enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            Toast.makeText(context, "Tutoring deleted successfully", Toast.LENGTH_SHORT).show();
                            ((Activity) context).recreate();

                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            Toast.makeText(context, "something went worng", Toast.LENGTH_SHORT).show();
                        }
                    });
        });
    }


    @Override
    public int getItemCount() {
      return   tutoringRecycle.size();
    }
}
