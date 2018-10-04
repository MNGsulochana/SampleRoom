package com.hackathlon.sampleroomapp.roomdatabase;


/*
    IT SHOULD BE EITHER ABSTRACT CLASS OR INTERFACE
*/

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;


@Dao
public interface StudentDao {

    @Insert
    public void insertstdnt_Data(StudentEntity studentEntity);

    @Query("select * from studentdetails")
    public List<StudentEntity> gettheDatafromDB();

    @Query("delete from studentdetails where id=:id")
    int deleteUser(int  id);

    @Query("UPDATE studentdetails SET sname=:name WHERE id = :id")
    void update(String name, int id);
}
