package com.example.wgujoshlongenecker.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;


import com.example.wgujoshlongenecker.entities.Assessments;
import com.example.wgujoshlongenecker.entities.Term;

import java.util.List;

@Dao
public interface AssessmentDAO {
    @Query("SELECT * FROM assessments_table ")
    List<Assessments> getAssessments();

    @Query("SELECT * FROM assessments_table WHERE courseId = :courseID")
    List<Assessments> getAssociatedAssessments(String courseID);
//
//    @Query("SELECT calories FROM user_table WHERE username = :un")
//    String getUserCalories(String un);

//    @Query("SELECT * FROM user_table WHERE first_name LIKE :first AND " +
//            "last_name LIKE :last LIMIT 1")
//    User findByName(String first, String last);

//    @Insert
//    void insertAll(User... users);

    @Insert
    void insertAssessment(Assessments ass);

    @Delete
    void delete(Assessments assessments);
}
