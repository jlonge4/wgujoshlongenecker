package com.example.wgujoshlongenecker.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.wgujoshlongenecker.entities.Course;
import com.example.wgujoshlongenecker.entities.Term;

import java.util.List;

@Dao
public interface CourseDAO {
    @Query("SELECT title FROM courses_table")
    List<String> getFoods();

    @Query("SELECT title FROM courses_table")
    List<String> getTerms();
//
//    @Query("SELECT calories FROM user_table WHERE username = :un")
//    String getUserCalories(String un);

//    @Query("SELECT * FROM user_table WHERE first_name LIKE :first AND " +
//            "last_name LIKE :last LIMIT 1")
//    User findByName(String first, String last);

//    @Insert
//    void insertAll(User... users);

    @Insert
    void insertCourse(Course course);

    @Delete
    void delete(Course course);
}
