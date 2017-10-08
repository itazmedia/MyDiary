package com.example.admin.mydiary;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private Button btnRegister;
    private TextView tvEmail;
    private TextView tvPassword;
    private TextView tvRepass;
    private TextView regErrEmail;
    private TextView regErrPass;
    private TextView regErrRePass;

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
                            DatabaseReference posts = mDatabase.child("users");
                            User u = new User(tvEmail.getText().toString(), tvPassword.getText().toString());
                            posts.push().setValue(u);
                            Toast.makeText(RegisterActivity.this,"Đăng ký thành công!!",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                            startActivity(intent);
                        }
            }
        });

    }
}
