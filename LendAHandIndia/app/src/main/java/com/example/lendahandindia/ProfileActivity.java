package com.example.lendahandindia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


import com.example.lendahandindia.Modal.StudentModal;
import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import com.rengwuxian.materialedittext.MaterialEditText;


import java.util.HashMap;



public class ProfileActivity extends AppCompatActivity {


    private Button save;
    private ImageView close;
    private MaterialEditText email;
    private MaterialEditText phoneNumber;
    private MaterialEditText className;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        close=findViewById(R.id.close);
        save=findViewById(R.id.save);
        email=findViewById(R.id.email);
        phoneNumber=findViewById(R.id.phone_number);
        className=findViewById(R.id.class_name);


        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        FirebaseDatabase.getInstance().getReference().child("Students").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                StudentModal studentModal=snapshot.getValue(StudentModal.class);
                email.setText(studentModal.getEmail());
                phoneNumber.setText(studentModal.getPhonenumber());
                className.setText(studentModal.getClassname());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateProfile();
                startActivity(new Intent(ProfileActivity.this, student.class));
            }
        });

    }

    private void updateProfile() {

        HashMap<String,Object> map=new HashMap<>();
        map.put("email",email.getText().toString());
        map.put("phonenumber",phoneNumber.getText().toString());
        map.put("classname",className.getText().toString());

        FirebaseDatabase.getInstance().getReference().child("Students").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(map);
    }

}