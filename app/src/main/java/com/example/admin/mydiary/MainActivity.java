package com.example.admin.mydiary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.*;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private TextView txtRegister;
    private  Button btnLogin;
    private EditText txtEmail, txtPassword;
    private DatabaseReference mDatabase;
// ...
    void tb(String notice){
        Toast.makeText(MainActivity.this,notice, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtRegister = (TextView) findViewById(R.id.clickRegister);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        txtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);

            }
        });
        txtEmail = (EditText) findViewById(R.id.txtEmail);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(txtEmail.getText().toString().equals("")) {
                    tb("Email khồng được bỏ trống");
                    txtEmail.requestFocus();
                }
                else
                    if(txtPassword.getText().toString().equals("")){
                        tb("Mật khẩu không được bỏ trống.");
                        txtPassword.requestFocus();
                    }
                    else
                       if(!txtEmail.getText().toString().equals("admin"))
                           tb("Email không tồn tại!!");
                       else
                           if(!txtPassword.getText().toString().equals("123456"))
                               tb("Mật khẩu không chính xác!!");
                           else
                               if(txtEmail.getText().toString().equals("admin") && txtPassword.getText().toString().equals("123456")){
                                      Intent intent;
                                       intent = new Intent(MainActivity.this, Home.class);
                                       startActivity(intent);
//                                     DatabaseReference posts = mDatabase.child("post");
//                                   posts.setValue(new Post("demo", "11/01/1997"));
//                                   posts.setValue(new Post("demo2", "12/01/1998"));
//                                   tb("ok");
                               }
                               else
                                   tb("Thông tin đăng nhập không chính xác!!");

            }
        });

    }



}
