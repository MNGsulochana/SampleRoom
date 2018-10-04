package com.hackathlon.sampleroomapp.roomdatabase;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;


/*

HERE I AM  CREATING THE TABLE IF I WANT TO USE SAME AS STUDENT ENTITY AS TABLE NAME LEAVE ANNOTATION AS IT IS OR ELSE MENTION NAME

*/

@Entity(tableName = "studentdetails")
public class StudentEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "sname")
    private  String name;

    @ColumnInfo(name = "semail")
    private String email;

    @ColumnInfo(name = "sadres")
    private  String address;

    @ColumnInfo(name = "smobile")
    private String mobile;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
