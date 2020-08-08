package com.example.lendahandindia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
<<<<<<< HEAD
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
=======
import android.widget.Button;
>>>>>>> 43df8545a42ba8ed3d541149d929ee9951082b56
import android.widget.TextView;

import com.example.lendahandindia.Modal.lesson;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class student extends AppCompatActivity {

    public static final String MY_PREFERENCE="com.example.lahi.user";
    FirebaseAuth mAuth;
<<<<<<< HEAD
    RecyclerView mLessonlist;
    FirebaseFirestore db;
    FirestoreRecyclerAdapter<lesson,LessonViewHolder> adapter;
=======

    private Button profile;
>>>>>>> 43df8545a42ba8ed3d541149d929ee9951082b56

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAuth=FirebaseAuth.getInstance();

        setContentView(R.layout.activity_student);

<<<<<<< HEAD
        mLessonlist=findViewById(R.id.lesson_list);
        FirebaseApp.initializeApp(getApplicationContext());
        LoadData();

    }
    private void LoadData() {
        db= FirebaseFirestore.getInstance();

        Query query=db.collection("lesson");

        Log.i("result", String.valueOf(query));
        FirestoreRecyclerOptions<lesson> options=new FirestoreRecyclerOptions.Builder<lesson>()
                .setQuery(query,lesson.class)
                .build();

        adapter=new FirestoreRecyclerAdapter<lesson,LessonViewHolder>(options) {


            @NonNull
            @Override
            public LessonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.les_row,parent,false);
                return new LessonViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull final LessonViewHolder holder, final int position, @NonNull lesson model) {


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
        public void setGrade(String chapter)
        {
            TextView resgrade =(TextView) mView.findViewById(R.id.res_class);
            resgrade.setText(chapter);
        }
        public void setDescription(String chapter)
        {
            TextView resdesc =(TextView) mView.findViewById(R.id.res_description);
            resdesc.setText(chapter);
        }
        public void setTeacher(String chapter)
        {
            TextView resteacher=(TextView) mView.findViewById(R.id.res_teacher);
            resteacher.setText(chapter);
        }

=======
        profile=findViewById(R.id.profile);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(student.this,ProfileActivity.class));
            }
        });
>>>>>>> 43df8545a42ba8ed3d541149d929ee9951082b56

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.ex_menu,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.queries:
                Intent intent = new Intent(getApplicationContext(), DoubtsFourmActivity.class);
                startActivity(intent);
                return  true;
            case R.id.logout:
                mAuth.signOut();
                SharedPreferences.Editor editor=getSharedPreferences(MY_PREFERENCE,MODE_PRIVATE).edit();
                editor.putBoolean("user", false);
                editor.commit();
                Intent intent2 = new Intent(getApplicationContext(), MainActivity.class);
                intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent2);
                return true;

            default:
                return super.onOptionsItemSelected(item);

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