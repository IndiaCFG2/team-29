package com.example.lendahandindia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class student extends AppCompatActivity {

    public static final String MY_PREFERENCE="com.example.restaurant.user";
    FirebaseAuth mAuth;
    TextView doubt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAuth=FirebaseAuth.getInstance();
        setContentView(R.layout.activity_student);
        doubt=findViewById(R.id.doubt);
        doubt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(student.this,DoubtsFourmActivity.class));
            }
        });
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
            case R.id.logout:
                mAuth.signOut();
                SharedPreferences.Editor editor=getSharedPreferences(MY_PREFERENCE,MODE_PRIVATE).edit();
                editor.putBoolean("user", false);
                editor.commit();
            default:
                return super.onOptionsItemSelected(item);

        }

    }
}