package com.hackathlon.sampleroomapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hackathlon.sampleroomapp.roomdatabase.StudentEntity;

import java.util.List;

public class ReadActivity extends AppCompatActivity {

    List<StudentEntity> st_tabel;
    RecyclerView myrecycler;
    MyAdapter myadapter;
    List<StudentEntity> studentEntities;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        myrecycler=findViewById(R.id.myrecycler);
        readData();

    }

    private void deletedata(final int id) {
    new AsyncTask<Void, Void, Void>() {

      @Override
      protected Void doInBackground(Void... voids) {

        int c = MainActivity.studentDatabase.studentDao().deleteUser(id);
        Log.d("TAG_delete", "doInBackground: a "+c);
        if(c==1)
        {
            readData();
        }
        return null;
      }
   }.execute();
    }

    private void readData() {

        new AsyncTask<Void, Void, List<StudentEntity>>() {
            @Override
            protected List<StudentEntity> doInBackground(Void... voids) {
                st_tabel=MainActivity.studentDatabase.studentDao().gettheDatafromDB();
                for(int i=0;i<st_tabel.size();i++)
                {
                    Log.d("TAG_NMAHE", "onCreate: "+st_tabel.get(i).getName());
                }

                return st_tabel;
            }

            @Override
            protected void onPostExecute(List<StudentEntity> studentEntities) {
                super.onPostExecute(studentEntities);
                myadapter=new MyAdapter(studentEntities);
                RecyclerView.LayoutManager manager=new LinearLayoutManager(ReadActivity.this);
                myrecycler.setLayoutManager(manager);

                RecyclerView.ItemDecoration itemDecoration=new DividerItemDecoration(ReadActivity.this,DividerItemDecoration.VERTICAL);
                myrecycler.addItemDecoration(itemDecoration);
                myrecycler.setAdapter(myadapter);
                for(int i=0;i<studentEntities.size();i++)
                {
                    Log.d("TAG_NMAHE_post", "onCreate: "+studentEntities.get(i).getName());
                }

            }
        }.execute();
    }



    private void updateData(final int i) {

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                MainActivity.studentDatabase.studentDao().update("MOBIOTICS",i);
                readData();
                return null;
            }
        }.execute();
    }

    class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>
    {
        List<StudentEntity> list;

        public MyAdapter(List<StudentEntity> studentEntities) {
            list=studentEntities;
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new MyViewHolder(getLayoutInflater().inflate(R.layout.recycler_layout,viewGroup,false));
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {
            final StudentEntity se=list.get(i);
            myViewHolder.tadrs.setText(se.getAddress());
            myViewHolder.temail.setText(se.getEmail());
            myViewHolder.tmobil.setText(se.getMobile());
            myViewHolder.tname.setText(se.getName());
            myViewHolder.tid.setText(""+se.getId());
            myViewHolder.update.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    updateData(se.getId());
                }
            });

            myViewHolder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    deletedata(se.getId());
                }
            });
        }

        @Override
        public int getItemCount() {
      Log.d("TAG_listsze", "getItemCount: "+list.size());
            return list.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            TextView tid,tname,tmobil,temail,tadrs;
            ImageView update,delete;
            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                tid=itemView.findViewById(R.id.textno);
                tname=itemView.findViewById(R.id.textname);
                tmobil=itemView.findViewById(R.id.textmobil);
                temail=itemView.findViewById(R.id.textemail);
                tadrs=itemView.findViewById(R.id.textadres);
                update=itemView.findViewById(R.id.update);
               delete=itemView.findViewById(R.id.delete);

            }
        }
    }

}
