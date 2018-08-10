package com.example.ckent.todotdd;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.Date;
import java.util.UUID;


@Entity(tableName = "tasks")
public class Tasks {
    @PrimaryKey
    @NonNull
    public final String id;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String title;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String status;

    public Date dueDate;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String desc;


    @Ignore
    Tasks(String title, String desc) {
        this(UUID.randomUUID().toString(), title, desc);
    }

    Tasks(@NonNull String id, String title, String desc) {
        Date today = new Date();
        this.dueDate=today;
        this.id=id;
        this.title=title;
        this.desc=desc;
        this.status="incomplete";

    }


    @Override
    public String toString(){
        return(title);
    }

}
