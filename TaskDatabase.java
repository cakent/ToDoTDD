package com.example.ckent.todotdd;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities={Tasks.class},version = 1)
abstract class TaskDatabase extends RoomDatabase{
    abstract TaskStore taskStore();

    private static final String DB_NAME ="tasks.db";
    private static volatile TaskDatabase INSTANCE = null;

    synchronized static TaskDatabase get(Context ctxt){
        if(INSTANCE==null){
            INSTANCE=create(ctxt,false);
        }

        return(INSTANCE);
    }

    static TaskDatabase create(Context ctxt, boolean memoryOnly){
    RoomDatabase.Builder<TaskDatabase> b;

    if(memoryOnly){
        b = Room.inMemoryDatabaseBuilder(ctxt.getApplicationContext(), TaskDatabase.class);
    }else{
        b=Room.databaseBuilder(ctxt.getApplicationContext(),TaskDatabase.class,DB_NAME);
    }
    return(b.build());
    }
}


