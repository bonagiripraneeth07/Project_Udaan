package com.udaan.udaanapplication;//package com.udaan.udaanapplication;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import java.text.BreakIterator;
//import java.util.List;
//
//public class TutoringAdapter extends RecyclerView.Adapter<TutoringAdapter.TutoringViewHolder> {
//
//    private List<TutoringItem> tutoringItems;
//    private OnMoreDetailsClickListener listener;
//
//    public TutoringAdapter(List<TutoringItem> tutoringItems, OnMoreDetailsClickListener listener) {
//        this.tutoringItems = tutoringItems;
//        this.listener = listener;
//    }
//
//    @NonNull
//    @Override
//    public TutoringViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        TextView tutorNameTextView, tutorIdTextView, preferredtimeTextView, postedDateTextView, subjectsTextView;
//        Button moreDetailsButton;
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tutoring, parent, false);
//        return new TutoringViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull TutoringViewHolder holder, int position) {
//        TutoringItem item = tutoringItems.get(position);
//        holder.tutorNameTextView.setText("Name: " + item.getTutorName());
//        holder.tutorIdTextView.setText("Tutor ID: " + item.getTutorId());
//        holder.preferredtimeTextView.setText("Preferred Time: " + item.getPreferredTime());
//        holder.postedDateTextView.setText("Posted: " + item.getPostedDate());
//        holder.subjectsTextView.setText("Subjects: " + String.join(", ", item.getSubjects()));
//
//        holder.moreDetailsButton.setOnClickListener(v -> {
//            listener.onMoreDetailsClick(item);
//        });
//    }
//
//    @Override
//    public int getItemCount() {
//        return tutoringItems.size();
//    }
//
//    public static class TutoringViewHolder extends RecyclerView.ViewHolder {
//         TextView tutorNameTextView, tutorIdTextView, preferredtimeTextView, postedDateTextView, subjectsTextView;
//        Button moreDetailsButton;
//
//        public TutoringViewHolder(@NonNull View itemView) {
//            super(itemView);
//            tutorNameTextView = itemView.findViewById(R.id.tutorName);
//            tutorIdTextView = itemView.findViewById(R.id.tutorIdTextView);
//            preferredtimeTextView = itemView.findViewById(R.id.preferredtimeTextView);
//            postedDateTextView = itemView.findViewById(R.id.postedDate);
//            subjectsTextView = itemView.findViewById(R.id.subject);
//            moreDetailsButton = itemView.findViewById(R.id.moreDetailsButton);
//        }
//    }
//
//    public interface OnMoreDetailsClickListener {
//        void onMoreDetailsClick(TutoringItem item);
//    }
//}

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.udaan.udaanapplication.model.TutorWrapper;
import com.udaan.udaanapplication.model.Tutoring;

import java.util.List;

public class TutoringAdapter extends RecyclerView.Adapter<TutoringAdapter.TutoringViewHolder> {

    private List<TutorWrapper> tutorList;
    private List<TutoringItem> tutoringList;
    private OnMoreDetailsClickListener listener;

    public interface OnMoreDetailsClickListener {
        void onMoreDetailsClick(TutoringItem item);
    }
    public TutoringAdapter(List<TutoringItem> tutoringList, OnMoreDetailsClickListener listener) {
        this.tutoringList = tutoringList;
        this.listener = listener;
    }
    @NonNull
    @Override
    public TutoringViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tutoring, parent, false);
        return new TutoringViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull TutoringViewHolder holder, int position) {
        TutoringItem item = tutoringList.get(position);
        holder.tutorName.setText(item.getTutorName());
        holder.tutorId.setText("ID: " + item.getTutorId());
        holder.preferredTime.setText(item.getPreferredTime());
        holder.postedDate.setText(item.getPostedDate());

        // Convert List<String> subjects to comma separated string
        holder.subjects.setText(String.join(", ", item.getSubjects()));

        holder.moreDetailsButton.setOnClickListener(v -> listener.onMoreDetailsClick(item));
    }

    @Override
    public int getItemCount() {
        return tutoringList.size();
    }

    public static class TutoringViewHolder extends RecyclerView.ViewHolder {
        TextView tutorName, tutorId, preferredTime, postedDate, subjects;
        Button moreDetailsButton;

        public TutoringViewHolder(@NonNull View itemView) {
            super(itemView);
            tutorName = itemView.findViewById(R.id.tutorName);
            tutorId = itemView.findViewById(R.id.tutorId);
            preferredTime = itemView.findViewById(R.id.preferredtimeTextView);
            postedDate = itemView.findViewById(R.id.postedDate);
            subjects = itemView.findViewById(R.id.subject);
            moreDetailsButton = itemView.findViewById(R.id.moreDetailsButton);
        }
    }
}
