package com.example.lendahandindia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lendahandindia.Adapter.DoubtsFourmAdapter;
import com.example.lendahandindia.Modal.DoubtsFourmModal;
import com.example.lendahandindia.Modal.StudentModal;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Comment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class DoubtsFourmActivity extends AppCompatActivity {

    private EditText doubts;
    private TextView post;


    private RecyclerView recyclerView;
    List<DoubtsFourmModal> doubtslist;
    DoubtsFourmAdapter doubtsFourmAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doubts_fourm);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        doubts=findViewById(R.id.doubt);
        post=findViewById(R.id.post_doubt);
        recyclerView=findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        doubtslist=new ArrayList<>();
        doubtsFourmAdapter=new DoubtsFourmAdapter(this,doubtslist);
        recyclerView.setAdapter(doubtsFourmAdapter);

        DoubtsFourmModal doubt=new DoubtsFourmModal();
        final String doubtId=doubt.getId();


        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String studentDoubt=doubts.getText().toString();
                if(!TextUtils.isEmpty(studentDoubt))
                {
                    postDoubt(studentDoubt);
                }
                else
                {
                    Toast.makeText(DoubtsFourmActivity.this,"Need to enter the Doubt",Toast.LENGTH_LONG).show();
                }
            }
        });

        getdoubts();
    }


    private void getdoubts() {
        FirebaseDatabase.getInstance().getReference().child("Doubts").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                doubtslist.clear();
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    DoubtsFourmModal doubt=dataSnapshot.getValue(DoubtsFourmModal.class);
                    doubtslist.add(doubt);

                }
                doubtsFourmAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void postDoubt(final String studentDoubt) {

        HashMap<String,Object> map=new HashMap<>();

        DatabaseReference ref= FirebaseDatabase.getInstance().getReference().child("Doubts").child(FirebaseAuth.getInstance().getCurrentUser().getUid());



        String id=ref.push().getKey();
        map.put("id",id);
        map.put("doubts",studentDoubt);
        map.put("answer","");

        doubts.setText("");

        ref.child(id).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(DoubtsFourmActivity.this,"Doubts Added",Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(DoubtsFourmActivity.this,task.getException().getMessage(),Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}