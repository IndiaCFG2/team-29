package com.example.lendahandindia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    private Button register;
    private EditText email;
    private EditText password;
    private EditText phoneNumber;
    private EditText className;
    private TextView haveAcc;
    FirebaseAuth firebaseAuth;
    DatabaseReference reference;
    FirebaseUser firebaseUser;

    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        register=findViewById(R.id.register);
        email=findViewById(R.id.email);
        phoneNumber=findViewById(R.id.phone_number);
        className=findViewById(R.id.class_name);
        haveAcc=findViewById(R.id.hava_acc);
        password=findViewById(R.id.password);
        firebaseAuth= FirebaseAuth.getInstance();

        haveAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this,signin.class));
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String studentMail=email.getText().toString();
                final String studentPhoneNumber=phoneNumber.getText().toString();
                final String studentClass=className.getText().toString();
                final String pwd=password.getText().toString();

                if(!TextUtils.isEmpty(studentClass)&&!TextUtils.isEmpty(studentMail)&&!TextUtils.isEmpty(studentPhoneNumber)&&!TextUtils.isEmpty(pwd))
                {
                    pd=new ProgressDialog(RegisterActivity.this);
                    pd.setMessage("Please wait...");
                    pd.show();
                    register(studentClass,studentMail,studentPhoneNumber,pwd);
                }
                else{
                    Toast.makeText(RegisterActivity.this,"Fill all the fields",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void register(final String studentClass, final String studentMail, final String studentPhoneNumber, final String pwd) {
        firebaseAuth.createUserWithEmailAndPassword(studentMail,pwd).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    firebaseUser=firebaseAuth.getCurrentUser();
                    String userId=firebaseUser.getUid();
                    reference= FirebaseDatabase.getInstance().getReference().child("Students").child(userId);
                    HashMap<String,Object> hashMap=new HashMap<>();
                    hashMap.put("id",userId);
                    hashMap.put("password",pwd);
                    hashMap.put("email",studentMail);
                    hashMap.put("classname",studentClass);
                    hashMap.put("phonenumber",studentPhoneNumber);

                    reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Intent intent=new Intent(RegisterActivity.this,signin.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                            }
                        }
                    });
                    Toast.makeText(RegisterActivity.this,"Registration Successful :)",Toast.LENGTH_LONG).show();
                }
                else{
                    pd.dismiss();
                    Toast.makeText(RegisterActivity.this,"Registration Failed :(",Toast.LENGTH_LONG).show();
                }
            }
        });


    }
}