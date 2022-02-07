package com.example.wgujoshlongenecker.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;


@Entity(tableName = "terms_table")
public class Term {
    @PrimaryKey(autoGenerate = true)
    public int tid;

    @ColumnInfo(name = "termName")
    public String termName;

    @ColumnInfo(name = "startDate")
    public String startDate;

    @ColumnInfo(name = "endDate")
    public String endDate;

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public String getTermName() {
        return termName;
    }

    public void setTermName(String termName) {
        this.termName = termName;
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

