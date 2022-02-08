package com.example.wgujoshlongenecker.database;

import android.app.Application;

import com.example.wgujoshlongenecker.dao.CourseDAO;
import com.example.wgujoshlongenecker.dao.TermDAO;
import com.example.wgujoshlongenecker.entities.Course;
import com.example.wgujoshlongenecker.entities.Term;

import java.util.List;

public class AppRepo {

    private TermDAO termDAO;
    private CourseDAO courseDao;
    private List<Term> mAllTerms;
    private List<Course> mAllCourses;

    public AppRepo(Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
//        mAssessmentDAO = db.assessmentDAO();
//        mCourseDAO = db.courseDAO();
//        mMentorDao = db.mentorDAO();
        termDAO = db.termDao();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public List<Term> getAllTerms() {
        AppDatabase.databaseWriteExecutor.execute(() ->{
            mAllTerms= termDAO.getTerms();
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mAllTerms;
    }

    public List<Course> getAllCourses() {
        AppDatabase.databaseWriteExecutor.execute(() ->{
            mAllCourses= courseDao.getCourses();
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mAllCourses;
    }


}
