package com.udaan.udaanapplication;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.udaan.udaanapplication.model.TutorDetailsRecycle;
import com.udaan.udaanapplication.model.TutoringRecycle;

import java.util.List;

public class MyAdapter2 extends  RecyclerView.Adapter<myviewholder>{
    Context context;
    List<TutorDetailsRecycle> tutorDetailsRecycles;
    public MyAdapter2(Context context, List<TutorDetailsRecycle> tutorDetailsRecycles) {
        this.context = context;
        this.tutorDetailsRecycles = tutorDetailsRecycles;
    }



    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new myviewholder(LayoutInflater.from(context).inflate(R.layout.item_tutoring, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {
        holder.subjectTextView.setText(TextUtils.join(", ",tutorDetailsRecycles.get(position).getSubjects()));;
        holder.preferredtimeTextView.setText(tutorDetailsRecycles.get(position).getPreferredTime());
        holder.postedDate.setText(tutorDetailsRecycles.get(position).getPostedDate());
        holder.tutorName.setText(tutorDetailsRecycles.get(position).getTutorName());


    }

    @Override
    public int getItemCount() {
        return   tutorDetailsRecycles.size();
    }
}
