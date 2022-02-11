package com.example.wgujoshlongenecker.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wgujoshlongenecker.R;
import com.example.wgujoshlongenecker.entities.Course;

import java.util.ArrayList;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder>{
    private ArrayList<Course> courseList;
    private final Context context;
    private final LayoutInflater mInflater;

public CourseAdapter(Context context) {
    mInflater = LayoutInflater.from(context);
    this.context = context;
}


    public class CourseViewHolder extends RecyclerView.ViewHolder {
        private TextView nameCourse;

        public CourseViewHolder(final View view) {
            super(view);
            nameCourse = view.findViewById(R.id.nameCourse);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    final Course current = courseList.get(position);
                    Intent intent = new Intent(context, DetailedCourse.class);
                    intent.putExtra("courseName", current.getTitle());
                    intent.putExtra("courseStart", current.getStartDate());
                    intent.putExtra("termID", current.getTermId());
                    intent.putExtra("courseEnd", current.getEndDate());
                    intent.putExtra("courseStatus", current.getStatus());
                    intent.putExtra("instructorName" , current.getInstructorName());
                    intent.putExtra("instructorPhone" , current.getInstructorPhone());
                    intent.putExtra("instructorEmail" , current.getInstructorEmail());
                    intent.putExtra("courseID", current.getCid());


//                    intent.putExtra("position", position);
                    context.startActivity(intent);
                }
            });
        }
    }

    @NonNull
    @Override
    public CourseAdapter.CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.lsit_items_course, parent, false);
        return new CourseViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseAdapter.CourseViewHolder holder, int position) {
//        String name = termsList.get(position).getTermName();
//        holder.nameText.setText(name);
        if (courseList != null) {
            final Course current = courseList.get(position);
            holder.nameCourse.setText(current.getTitle());
        } else {
            holder.nameCourse.setText("No Word");
        }

    }

    @Override
    public int getItemCount() {
        return courseList.size();
    }

    public void setCourses(ArrayList<Course> courses) {
        courseList = courses;
        notifyDataSetChanged();
    }
}

