package com.example.lendahandindia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lendahandindia.Modal.lesson;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class filter extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseFirestore db;
    FirestoreRecyclerAdapter<lesson, student.LessonViewHolder> adapter;
    String cls;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        cls=getIntent().getStringExtra("cls");
        mAuth=FirebaseAuth.getInstance();
        LoadData();
    }

    private void LoadData()
    {
        db= FirebaseFirestore.getInstance();

        Query query=db.collection("lesson"). whereEqualTo("grade",cls);


        FirestoreRecyclerOptions<lesson> options=new FirestoreRecyclerOptions.Builder<lesson>()
                .setQuery(query,lesson.class)
                .build();



        adapter=new FirestoreRecyclerAdapter<lesson, student.LessonViewHolder>(options) {


            @NonNull
            @Override
            public student.LessonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.les_row,parent,false);
                return new student.LessonViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull final student.LessonViewHolder holder, final int position, @NonNull lesson model) {


                holder.setChapter(model.getChapter());
                holder.setGrade(model.getGrade());
                holder.setDescription(model.getDescription());
                holder.setTeacher(model.getTeacher());



            }


        };

        final RecyclerView mHouseList = (RecyclerView) findViewById(R.id.lesson_list);

        mHouseList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        mHouseList.setAdapter(adapter);


    }


    public static class LessonViewHolder extends RecyclerView.ViewHolder
    {
        View mView;


        public LessonViewHolder(@NonNull View itemView) {
            super(itemView);
            mView=itemView;
        }

        public void setChapter(String chapter)
        {
            TextView reschapter =(TextView) mView.findViewById(R.id.res_chapter);
            reschapter.setText(chapter);
        }
        public void setGrade(String grade)
        {
            TextView resgrade =(TextView) mView.findViewById(R.id.res_class);
            resgrade.setText("Grade :"+ grade);
        }
        public void setDescription(String descripton)
        {
            TextView resdesc =(TextView) mView.findViewById(R.id.res_description);
            resdesc.setText(descripton);
        }
        public void setTeacher(String teacher)
        {
            TextView resteacher=(TextView) mView.findViewById(R.id.res_teacher);
            resteacher.setText("Teacher :"+teacher);
        }



    }
    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }
}