package com.example.wgujoshlongenecker.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.wgujoshlongenecker.dao.AssessmentDAO;
import com.example.wgujoshlongenecker.dao.CourseDAO;
import com.example.wgujoshlongenecker.dao.NoteDAO;
import com.example.wgujoshlongenecker.dao.TermDAO;
import com.example.wgujoshlongenecker.entities.Assessments;
import com.example.wgujoshlongenecker.entities.Course;
import com.example.wgujoshlongenecker.entities.CourseNotes;
import com.example.wgujoshlongenecker.entities.Term;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Database(entities = {Term.class, Course.class, Assessments.class, CourseNotes.class}, exportSchema = false, version = 9)
public abstract class AppDatabase extends RoomDatabase {

    private static final String DB_NAME = "wguTermScheduler.db";
    private static AppDatabase instance;


    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, DB_NAME).allowMainThreadQueries().fallbackToDestructiveMigration().build();
        }
        return instance;
    }

    public abstract TermDAO termDao();
    public abstract AssessmentDAO assessmentDao();
    public abstract CourseDAO courseDao();
    public abstract NoteDAO noteDao();
    public static volatile AppDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            databaseWriteExecutor.execute(() -> {
                TermDAO mTermDao = INSTANCE.termDao();
            });
        }
    };


}