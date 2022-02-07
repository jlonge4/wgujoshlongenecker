package com.example.wgujoshlongenecker.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.wgujoshlongenecker.entities.Term;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface TermDAO {
    @Query("SELECT * FROM terms_table")
    List<Term> getTerms();

    @Query("SELECT endDate FROM terms_table WHERE termName= :un")
    String getTermEnd(String un);

    @Query("SELECT startDate FROM terms_table WHERE termName = :un")
    String getTermStart(String un);

    @Query("SELECT * FROM terms_table WHERE termName= :un")
    Term getTermToDelete(String un);

    @Query("SELECT tid FROM terms_table WHERE termName= :un")
    Term getTerm(String un);

    @Update
    void update(Term term);

    @Insert
    void insertAll(Term... terms);

    @Insert
    void insertTerm(Term term);

    @Delete
    void delete(Term term);
}
