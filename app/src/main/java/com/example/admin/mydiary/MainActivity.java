package com.example.admin.mydiary;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.*;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private TextView txtRegister;
    private  Button btnLogin;
    private EditText txtEmail, txtPassword;
    private FirebaseAuth mAuth;
    public String email = "";
    public int count = 0;
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
                    txtEmail.setHint("Email không được bỏ trống!!");
                    txtEmail.setHintTextColor(Color.RED);
                    txtEmail.requestFocus();
                }
                else
                    if(txtPassword.getText().toString().equals("")){
                        txtPassword.setHint("Mật khẩu không được bỏ trống!!");
                        txtPassword.setHintTextColor(Color.RED);
                        txtPassword.requestFocus();
                    }
                    else
                    {
                        mAuth = FirebaseAuth.getInstance();
                        login(txtEmail.getText().toString(),txtPassword.getText().toString());
                        /*Intent intent;
                        intent = new Intent(MainActivity.this, Home.class);
                        tb("Đăng nhập thành công!!");
                        startActivity(intent);*/
                    }
            }
        });

    }
    private void login(String email, String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent intent;
                            intent = new Intent(MainActivity.this, Home.class);
                            tb("Đăng nhập thành công!!");
                            FirebaseUser user = mAuth.getCurrentUser();
                            startActivity(intent);
                        } else {
                            tb("Đăng nhập thất bại, Kiểm tra Email hoặc mật khẩu!!");
                        }
                    }
                });
    }







}
