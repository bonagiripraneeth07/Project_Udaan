package com.udaan.udaanapplication;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class myviewholder extends RecyclerView.ViewHolder {
    Button deleteTutoring,moreDetailsButton;
    TextView subjectView,preferredTimeView,postedDateView,preferredtimeTextView,postedDate,tutorName,subjectTextView;
    public myviewholder(@NonNull View itemView) {
        super(itemView);
        subjectView=itemView.findViewById(R.id.subjectTextView);
        preferredTimeView=itemView.findViewById(R.id.preferredTimeView);
        postedDateView=itemView.findViewById(R.id.postedDateView);
        deleteTutoring = itemView.findViewById(R.id.deleteTutorButton);

        preferredtimeTextView = itemView.findViewById(R.id.preferredtimeTextView);
        postedDate = itemView.findViewById(R.id.postedDate);
        tutorName =itemView.findViewById(R.id.tutorName);
        subjectTextView= itemView.findViewById(R.id.subject);
        moreDetailsButton = itemView.findViewById(R.id.moreDetailsButton);
    }


}
