package com.example.wgujoshlongenecker.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;


@Entity(tableName = "courses_table")
public class Course {
    @PrimaryKey(autoGenerate = true)
    public int cid;

    @ColumnInfo(name = "termId")
    public String termId;

    @ColumnInfo(name = "title")
    public String title;

    @ColumnInfo(name = "startDate")
    public String startDate;

    @ColumnInfo(name = "endDate")
    public String endDate;

    @ColumnInfo(name = "status")
    public String status;

    @ColumnInfo(name = "instructorInfo")
    public String instructorInfo;

    @ColumnInfo(name = "noteInfo")
    public String noteInfo;

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getTermId() {
        return termId;
    }

    public void setTermId(String termId) {
        this.termId = termId;
    }

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getInstructorInfo() {
        return instructorInfo;
    }

    public void setInstructorInfo(String instructorInfo) {
        this.instructorInfo = instructorInfo;
    }

    public String getNoteInfo() {
        return noteInfo;
    }

    public void setNoteInfo(String noteInfo) {
        this.noteInfo = noteInfo;
    }
}