package com.example.wgujoshlongenecker.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import com.example.wgujoshlongenecker.entities.Assessments;
import com.example.wgujoshlongenecker.entities.Term;

import java.util.List;

@Dao
public interface AssessmentDAO {
    @Query("SELECT * FROM assessments_table ")
    List<Assessments> getAssessments();

    @Query("SELECT * FROM assessments_table WHERE courseId = :courseID")
    List<Assessments> getAssociatedAssessments(String courseID);

    @Query("DELETE FROM assessments_table WHERE aid = :title")
    void deleteById(String title);

    @Insert
    void insertAssessment(Assessments ass);

    @Update
    void update(Assessments ass);

    @Delete
    void delete(Assessments assessments);
}
