package com.example.wgujoshlongenecker.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "notes_table")
public class CourseNotes {
    @PrimaryKey(autoGenerate = true)
    public int nid;

    @ColumnInfo(name = "courseId")
    public String cid;

    @ColumnInfo(name = "noteContent")
    public String note;

    public int getNid() {
        return nid;
    }

    public void setNid(int nid) {
        this.nid = nid;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
