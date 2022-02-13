package com.example.wgujoshlongenecker.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.wgujoshlongenecker.entities.Course;
import com.example.wgujoshlongenecker.entities.CourseNotes;
import com.example.wgujoshlongenecker.entities.Term;

import java.util.List;

@Dao
public interface NoteDAO {


    @Query("SELECT noteContent FROM notes_table WHERE courseId = :courseID")
    List<String> getCourseNotes(String courseID);

    @Query("DELETE FROM notes_table WHERE courseId = :courseID")
    void deleteNote(String courseID);

    @Insert
    void insert(CourseNotes note);

    @Delete
    void delete(CourseNotes note);
}