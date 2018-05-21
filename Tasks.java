package com.example.ckent.todotdd;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import java.util.UUID;


@Entity(tableName = "tasks")
public class Tasks {
    @PrimaryKey
    @NonNull
    public final String id;
    public final String title;
    public final String desc;


    @Ignore
    Tasks(String title, String desc) {
        this(UUID.randomUUID().toString(), title, desc);
    }

    Tasks(@NonNull String id, String title, String desc) {
        this.id=id;
        this.title=title;
        this.desc=desc;
    }

    @Override
    public String toString(){
        return(title);
    }

}
