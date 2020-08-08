package com.example.lendahandindia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button registerbtn,signinbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        registerbtn =(Button) findViewById(R.id.registerbtn);
    }

    public void register(View view)
    {
        Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
        startActivity(intent);
    }
    public  void login(View view)
    {
        Intent intent = new Intent(getApplicationContext(), signin.class);
        startActivity(intent);
    }

}