package com.jalexcode.misnotas.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.jalexcode.misnotas.Util.Util;

@Entity(tableName = "notes")
public class Note implements Comparable<Note> {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "title")
    private String title;
    @ColumnInfo(name = "text")
    private String text;
    @ColumnInfo(name = "creation_date")
    private long creationDate;

    @Ignore
    public Note(String title, String text) {
        this.title = title;
        this.text = text;
    }

    public Note(String title, String text, long creationDate) {
        this.title = title;
        this.text = text;
        this.creationDate = creationDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(long creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public int compareTo(Note p2) {
        return Util.long2Date(this.creationDate).compareTo(Util.long2Date(p2.getCreationDate()));
    }
}
