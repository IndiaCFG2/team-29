package com.example.lendahandindia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

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

    RecyclerView mLessonlist;
    FirebaseFirestore db;
    FirestoreRecyclerAdapter<lesson,LessonViewHolder> adapter;
    SearchView sv;
    String url;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAuth=FirebaseAuth.getInstance();

        setContentView(R.layout.activity_student);
        sv=(SearchView) findViewById(R.id.sv);
        sv.setQueryHint("Search for your class");
        mLessonlist=findViewById(R.id.lesson_list);
        FirebaseApp.initializeApp(getApplicationContext());
        LoadData();
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Intent intent =new Intent(getApplicationContext(),filter.class);
                intent.putExtra("cls",query);
                startActivity(intent);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

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
                url=model.getUrl();
                holder.itemView.findViewById(R.id.res_download).setOnClickListener(new View.OnClickListener(){
                    @Override
                    //On click function
                    public void onClick(View view) {
                         Toast.makeText(getApplicationContext(),"Downloading! ",Toast.LENGTH_SHORT).show();
                            DownloadManager downloadmanager = (DownloadManager) getApplicationContext().getSystemService(Context.DOWNLOAD_SERVICE);
                            Uri uri = Uri.parse(url);
                            DownloadManager.Request request = new DownloadManager.Request(uri);

                            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);

                                request.setDestinationInExternalFilesDir(getApplicationContext(), "/downloads", "PDF Document" + ".pdf");

                            downloadmanager.enqueue(request);

                    }
                });



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
            case R.id.pro:
                Intent intent9 = new Intent(getApplicationContext(), ProfileActivity.class);
                startActivity(intent9);
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