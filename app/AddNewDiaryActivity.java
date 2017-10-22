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
                    SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyhhmmss");
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                    p.setPost_id(sdf.format(new Date()));
                    p.setName(tvTitle.getText().toString());
                    p.setContent(tvContent.getText().toString());
                    p.setEmail(user.getEmail());
                    p.setTime(getDate());
                    mDatabase = FirebaseDatabase.getInstance().getReference();
                    DatabaseReference ref = mDatabase.child("posts");
                    ref.child(p.getPost_id()).setValue(p);
                    Toast.makeText(AddNewDiaryActivity.this, "Thêm mới thành công", Toast.LENGTH_SHORT).show();
                    tvTitle.setText("");
                    tvContent.setText("");
                    Intent intent = new Intent(AddNewDiaryActivity.this, ListData.class);
                    startActivity(intent);
                }
            }
        });
    }

    public String getDate(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return simpleDateFormat.format(new Date());
    }
}
