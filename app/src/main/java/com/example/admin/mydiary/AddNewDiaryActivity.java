package com.example.admin.mydiary;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddNewDiaryActivity extends AppCompatActivity {

    Button btnSavePost;
    TextView tvTitle;
    TextView tvContent;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_diary);
        btnSavePost = (Button) findViewById(R.id.btnSavePost);
        tvTitle = (TextView) findViewById(R.id.txtTitle);
        tvContent = (TextView) findViewById(R.id.txtContent);

        btnSavePost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tvTitle.getText().toString().equalsIgnoreCase(""))
                {
                    tvTitle.setHint("Bạn chưa nhập tiêu đề");
                    tvTitle.setHintTextColor(Color.RED);
                }
                else if(tvContent.getText().toString().equalsIgnoreCase(""))
                {
                    tvContent.setHint("Nhập vài câu gì đi chứ!!");
                    tvContent.setHintTextColor(Color.RED);
                }
                else
                {
                    Post p = new Post();
                    p.setName(tvTitle.getText().toString());
                    p.setContent(tvContent.getText().toString());
                    p.setEmail(MainActivity.email);
                    p.setTime("11/1/2016");
                    mDatabase = FirebaseDatabase.getInstance().getReference();
                    DatabaseReference ref = mDatabase.child("posts");
                    ref.push().setValue(p);
                    Toast.makeText(AddNewDiaryActivity.this, "Thêm mới thành công", Toast.LENGTH_SHORT);
                }
            }
        });
    }
}
