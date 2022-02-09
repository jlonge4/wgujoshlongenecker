package com.example.wgujoshlongenecker.activities;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wgujoshlongenecker.R;
import com.example.wgujoshlongenecker.entities.Assessments;
import com.example.wgujoshlongenecker.entities.Term;

import java.util.ArrayList;

public class AssessmentAdapter extends RecyclerView.Adapter<AssessmentAdapter.AssessmentViewHolder>{
    private ArrayList<Assessments> assessmentList;
    private final Context context;
    private final LayoutInflater mInflater;

//    public TermAdapter(ArrayList<Term> termsList) {
//        this.termsList = termsList;
//    }
public AssessmentAdapter(Context context) {
    mInflater = LayoutInflater.from(context);
    this.context = context;
}


    public class AssessmentViewHolder extends RecyclerView.ViewHolder {
        private TextView nameAssessment;

        public AssessmentViewHolder(final View view) {
            super(view);
            nameAssessment = view.findViewById(R.id.nameAssessment);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    final Assessments current = assessmentList.get(position);
                    Intent intent = new Intent(context, ScheduledAssessments.class);
                    intent.putExtra("assessmentName", current.getTitle());
                    intent.putExtra("courseId" , current.getCourseId());
//                    intent.putExtra("termStart", current.getStartDate());
//                    intent.putExtra("termEnd", current.getEndDate());
//                    intent.putExtra("termID", current.getTid());
//                    intent.putExtra("position", position);
                    context.startActivity(intent);
                }
            });
        }
    }

    @NonNull
    @Override
    public AssessmentAdapter.AssessmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.lsit_items_assessment, parent, false);
        return new AssessmentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AssessmentAdapter.AssessmentViewHolder holder, int position) {
//        String name = termsList.get(position).getTermName();
//        holder.nameText.setText(name);
        if (assessmentList != null) {
            final Assessments current = assessmentList.get(position);
            holder.nameAssessment.setText(current.getTitle());
        } else {
            holder.nameAssessment.setText("No Word");
        }

    }

    @Override
    public int getItemCount() {
        return assessmentList.size();
    }

    public void setAssessments(ArrayList<Assessments> terms) {
        assessmentList = terms;
        notifyDataSetChanged();
    }
}

