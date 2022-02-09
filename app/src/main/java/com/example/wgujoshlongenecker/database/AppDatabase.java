package com.example.wgujoshlongenecker.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.wgujoshlongenecker.dao.AssessmentDAO;
import com.example.wgujoshlongenecker.dao.CourseDAO;
import com.example.wgujoshlongenecker.dao.TermDAO;
import com.example.wgujoshlongenecker.entities.Assessments;
import com.example.wgujoshlongenecker.entities.Course;
import com.example.wgujoshlongenecker.entities.Term;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Database(entities = {Term.class, Course.class, Assessments.class}, exportSchema = false, version = 5)
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
    public static volatile AppDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            databaseWriteExecutor.execute(() -> {
//                AssessmentDAO mAssessmentDao = INSTANCE.assessmentDAO();
//                CourseDAO mCourseDao = INSTANCE.courseDAO();
//                MentorDAO mMentorDao = INSTANCE.mentorDAO();
                TermDAO mTermDao = INSTANCE.termDao();

                //Deletes current DB to start fresh upon restart.
                /*mAssessmentDao.deleteAllAssessments();
                mCourseDao.deleteAllCourses();
                mMentorDao.deleteAllMentors();;
                mTermDao.deleteAllTerms();*/

//                /**
//                 *Assessment Data for activity
//                 */
//                AssessmentEntity assessment = new AssessmentEntity(1, "assessment1", "10/25/2021", "Project", 1);
//                mAssessmentDao.insert(assessment);
//                assessment = new AssessmentEntity(2, "assessment2", "10/25/2021", "Project", 2);
//                mAssessmentDao.insert(assessment);
//                assessment = new AssessmentEntity(3, "assessment3", "10/25/2021", "Assessment", 3);
//                mAssessmentDao.insert(assessment);
//                assessment = new AssessmentEntity(4, "assessment4", "10/25/2021", "Assessment", 4);
//                mAssessmentDao.insert(assessment);
//
//                /**
//                 *Course Data for activity
//                 */
//                //CourseEntity course = new CourseEntity(1, "C196", "1/1/2021", "2/28/2021", "Active", "This Class is so Difficult like C195", 1);
//                //mCourseDao.insert(course);
//                CourseEntity course = new CourseEntity(2, "C195", "01/01/2021", "02/28/2021", "Active", "This Class is so Difficult :(", 1);
//                mCourseDao.insert(course);
//                course = new CourseEntity(3, "C949", "06/01/2021", "09/28/2021", "Inactive", "Excited to start this class :)", 2);
//                mCourseDao.insert(course);
//                course = new CourseEntity(4, "D191", "06/01/2021", "09/28/2021", "Inactive", "Excited to start this class :)", 2);
//                mCourseDao.insert(course);
//
//                /**
//                 *Course Instructor data for activity
//                 */
////                MentorEntity mentor = new MentorEntity(1, "Course Instructor Name", "Instructor@wgu.edu", "801-111-1111", 4);
////                mMentorDao.insert(mentor);
////                mentor = new MentorEntity(2, "Course Instructor Name", "Instructor@wgu.edu", "801-111-1111", 2);
////                mMentorDao.insert(mentor);
////                mentor = new MentorEntity(3, "Course Instructor Name", "Instructor@wgu.edu", "801-111-1111", 3);
////                mMentorDao.insert(mentor);
//
//                /**
//                 *Term Data for activity
//                 */
//                Term term = new Term(1, "Term 1", "01/01/2021", "06/30/2021");
//                mTermDao.insert(term);
//                term = new TermEntity(2, "Term 2", "07/01/2021", "12/31/2021");
//                mTermDao.insert(term);

            });
        }
    };


}