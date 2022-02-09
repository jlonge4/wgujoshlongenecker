package com.example.wgujoshlongenecker.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "assessments_table")
public class Assessments {
    @PrimaryKey(autoGenerate = true)
    public int aid;

    @ColumnInfo(name = "courseId")
    public String courseId;

    @ColumnInfo(name = "title")
    public String title;

    @ColumnInfo(name = "startDate")
    public String startDate;

    @ColumnInfo(name = "endDate")
    public String endDate;

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public String getCourseId() { return courseId;}

    public void setCourseId(String courseId) { this.courseId = courseId;}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}