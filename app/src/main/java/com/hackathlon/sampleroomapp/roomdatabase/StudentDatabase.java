package com.hackathlon.sampleroomapp.roomdatabase;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {StudentEntity.class},version = 1)
public abstract class StudentDatabase extends RoomDatabase {

    public abstract StudentDao studentDao();

}
