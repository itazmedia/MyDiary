package com.example.admin.mydiary;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

public class EditPostActivity extends AppCompatActivity {

    Button btnSaveEdit;
    TextView tvEditTitle;
    TextView tvEditContent, tvEditTime;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_post);
        btnSaveEdit = (Button) findViewById(R.id.btnSaveEdit);
        tvEditTitle = (TextView) findViewById(R.id.txtEditTitle);
        tvEditContent = (TextView) findViewById(R.id.txtEditContent);
        tvEditTime = (TextView) findViewById(R.id.tvEditTime);
        Intent intent = getIntent();
        String title,content,time;
        title = intent.getStringExtra("title");
        content = intent.getStringExtra("content");
        time = intent.getStringExtra("time");
        tvEditContent.setText(content);
        tvEditTitle.setText(title);
        tvEditTime.setText(time);
        btnSaveEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tvEditTitle.getText().toString().equalsIgnoreCase(""))
                {
                    tvEditTitle.setHint("Bạn chưa nhập tiêu đề");
                    tvEditTitle.setHintTextColor(Color.RED);
                }
                else if(tvEditContent.getText().toString().equalsIgnoreCase(""))
                {
                    tvEditContent.setHint("Nhập vài câu gì đi chứ!!");
                    tvEditContent.setHintTextColor(Color.RED);
                }
                else
                {
                    Intent intent = getIntent();
                    String post_id = intent.getStringExtra("post_id");
                    mDatabase = FirebaseDatabase.getInstance().getReference();
                    mDatabase.child("posts").child(post_id).child("name").setValue(tvEditTitle.getText().toString());
                    mDatabase.child("posts").child(post_id).child("content").setValue(tvEditContent.getText().toString());
                    Toast.makeText(EditPostActivity.this,"Chỉnh sửa thành công!!", Toast.LENGTH_SHORT).show();
                    Intent intent2 = new Intent(EditPostActivity.this, ListData.class);
                    startActivity(intent2);
                }
            }
        });
    }

}
