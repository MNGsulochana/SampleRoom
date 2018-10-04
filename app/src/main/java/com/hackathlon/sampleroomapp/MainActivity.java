package com.hackathlon.sampleroomapp;

import android.annotation.SuppressLint;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hackathlon.sampleroomapp.roomdatabase.StudentDao;
import com.hackathlon.sampleroomapp.roomdatabase.StudentDatabase;
import com.hackathlon.sampleroomapp.roomdatabase.StudentEntity;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button add_student,read_student;
    EditText name,email,adres,mobile;
    public static  StudentDatabase studentDatabase;

    StudentEntity studentEntity=new StudentEntity();
    String etname,etemail,etadres,etmobil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_student);
        add_student=findViewById(R.id.add_data);
        read_student=findViewById(R.id.read_data);

        name=findViewById(R.id.getname);
        email=findViewById(R.id.getmail);
        adres=findViewById(R.id.getadres);
        mobile=findViewById(R.id.getmobile);
        studentDatabase=Room.databaseBuilder(getApplicationContext(),StudentDatabase.class,"studentdb").build();
        add_student.setOnClickListener(this);
        read_student.setOnClickListener(this);




    }


    public void getthedata()
    {

        etadres=adres.getText().toString().trim();
        etemail=email.getText().toString().trim();
        etmobil=mobile.getText().toString().trim();
        etname=name.getText().toString().trim();


        if(!TextUtils.isEmpty(etname)&&!TextUtils.isEmpty(etadres)&&!TextUtils.isEmpty(etemail)&& Patterns.EMAIL_ADDRESS.matcher(etemail).matches()&&!TextUtils.isEmpty(etmobil))
        {


            studentEntity.setAddress(etadres);
            studentEntity.setEmail(etemail);
            studentEntity.setName(etname);
            studentEntity.setMobile(etmobil);
            //studentEntity.setId(count++);
            insertdata(studentEntity);
            Toast.makeText(MainActivity.this,"inserted...",Toast.LENGTH_SHORT).show();

            adres.setText("");
            name.setText("");
            email.setText("");
            mobile.setText("");
            name.requestFocus();

        }
        else {
            Toast.makeText(MainActivity.this,"please fill all the fields",Toast.LENGTH_SHORT).show();
        }

    }

    private void insertdata(final StudentEntity studentEntity) {
        new AsyncTask<Void, Void, StudentDao>() {
            @Override
            protected StudentDao doInBackground(Void... voids) {
               studentDatabase.studentDao().insertstdnt_Data(studentEntity);
                return null;
            }

        }.execute();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.read_data:

                Intent in=new Intent(MainActivity.this,ReadActivity.class);
                startActivity(in);

                break;
            case R.id.add_data:
                getthedata();
                break;

        }

    }


}
