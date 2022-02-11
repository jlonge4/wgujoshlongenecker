package com.example.wgujoshlongenecker.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.wgujoshlongenecker.entities.Course;
import com.example.wgujoshlongenecker.entities.Term;

import java.util.List;

@Dao
public interface CourseDAO {
//    @Query("SELECT title FROM courses_table")
//    List<String> getFoods(String termID);

    @Query("SELECT * FROM courses_table WHERE termId = :termID")
    List<Course> getCourses(String termID);

    @Query("DELETE FROM courses_table WHERE cid = :title")
    void deleteCourse(String title);
//
//    @Query("SELECT calories FROM user_table WHERE username = :un")
//    String getUserCalories(String un);

//    @Query("SELECT * FROM user_table WHERE first_name LIKE :first AND " +
//            "last_name LIKE :last LIMIT 1")
//    User findByName(String first, String last);

//    @Update
//    void insertAll(User... users);
    @Insert
    void insertCourse(Course course);

    @Delete
    void delete(Course course);

    @Update
    void update(Course course);
}
