package com.example.admin.mydiary;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private Button btnRegister;
    private TextView tvEmail;
    private TextView tvPassword;
    private TextView tvRepass;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        btnRegister = (Button) findViewById(R.id.btnSendRegister);
        tvEmail = (TextView) findViewById(R.id.txtRegEmail);
        tvPassword = (TextView) findViewById(R.id.txtRegPassword);
        tvRepass = (TextView) findViewById(R.id.txtRegRePassword);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tvEmail.getText().toString().equalsIgnoreCase(""))
                {
                    tvEmail.setHint("Bạn chưa nhập Email!!");
                    tvEmail.setHintTextColor(Color.RED);
                }

                else
                    if(tvPassword.getText().toString().equalsIgnoreCase(""))
                    {
                        tvPassword.setHint("Bạn chưa nhập Mật khẩu!!");
                        tvPassword.setHintTextColor(Color.RED);
                    }
                    else
                        if(!tvPassword.getText().toString().equalsIgnoreCase(tvRepass.getText().toString()))
                        {
                            tvRepass.setText("");
                            tvRepass.setHint("Mật khẩu không khớp!!");
                            tvRepass.requestFocus();
                            tvRepass.setHintTextColor(Color.RED);
                        }
                        else {
                            mDatabase = FirebaseDatabase.getInstance().getReference();
                            DatabaseReference dbr = mDatabase.child("users");
                            dbr.push().setValue(new User(tvEmail.getText().toString(), tvPassword.getText().toString()));
                            dbr.addChildEventListener(new ChildEventListener() {
                                @Override
                                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                                    Toast.makeText(RegisterActivity.this, "Đăng ký thành công!!", Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                                    startActivity(intent);
                                }

                                @Override
                                public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                                }

                                @Override
                                public void onChildRemoved(DataSnapshot dataSnapshot) {

                                }

                                @Override
                                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {
                                    Toast.makeText(RegisterActivity.this, "Đăng ký thất bại!!", Toast.LENGTH_LONG).show();
                                }
                            });
                        }
            }
        });

    }
    private void register(String email, String password){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(RegisterActivity.this, "Đăng ký thành công!!", Toast.LENGTH_SHORT);
                            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(RegisterActivity.this, "Đăng ký thất bại!!", Toast.LENGTH_SHORT);
                        }
                    }
                });
    }
}
