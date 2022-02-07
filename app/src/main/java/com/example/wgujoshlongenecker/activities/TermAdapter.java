package com.example.wgujoshlongenecker.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.wgujoshlongenecker.R;
import com.example.wgujoshlongenecker.entities.Term;

import java.util.ArrayList;
import java.util.List;

public class TermAdapter extends RecyclerView.Adapter<TermAdapter.TermViewHolder>{
    private ArrayList<Term> termsList;
    private final Context context;
    private final LayoutInflater mInflater;

//    public TermAdapter(ArrayList<Term> termsList) {
//        this.termsList = termsList;
//    }
public TermAdapter(Context context) {
    mInflater = LayoutInflater.from(context);
    this.context = context;
}


    public class TermViewHolder extends RecyclerView.ViewHolder {
        private TextView nameText;

        public TermViewHolder(final View view) {
            super(view);
            nameText = view.findViewById(R.id.nameTerm);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    final Term current = termsList.get(position);
                    Intent intent = new Intent(context, DetailedTerm.class);
                    intent.putExtra("termName", current.getTermName());
                    intent.putExtra("termStart", current.getStartDate());
                    intent.putExtra("termEnd", current.getEndDate());
                    intent.putExtra("termID", current.getTid());
                    intent.putExtra("position", position);
                    context.startActivity(intent);
                }
            });
        }
    }

    @NonNull
    @Override
    public TermAdapter.TermViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.lsit_items, parent, false);
        return new TermViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TermAdapter.TermViewHolder holder, int position) {
//        String name = termsList.get(position).getTermName();
//        holder.nameText.setText(name);
        if (termsList != null) {
            final Term current = termsList.get(position);
            holder.nameText.setText(current.getTermName());
        } else {
            holder.nameText.setText("No Word");
        }

    }

    @Override
    public int getItemCount() {
        return termsList.size();
    }

    public void setTerms(ArrayList<Term> terms) {
        termsList = terms;
        notifyDataSetChanged();
    }
}

