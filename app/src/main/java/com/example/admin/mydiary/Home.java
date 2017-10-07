package com.example.admin.mydiary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Home extends AppCompatActivity {
    private ImageButton imgBtnList;

    private  ImageButton imgAddnew;

    private ImageButton imgChangePassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        imgBtnList = (ImageButton) findViewById(R.id.list);
        imgBtnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent = new Intent(Home.this, ListData.class);
                startActivity(intent);
            }
        });

        imgChangePassword = (ImageButton) findViewById(R.id.changePassHome);
        imgChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, ChangePasswordActivity.class);
                startActivity(intent);
            }
        });


        imgAddnew = (ImageButton) findViewById(R.id.addnew);
        imgAddnew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, AddNewDiaryActivity.class);
                startActivity(intent);
            }
        });
    }
}
