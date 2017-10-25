package com.example.admin.mydiary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DetailsPageActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private Button btnSua,btnXoa;
    private TextView tvDetailTitle,tvDetailContent, tvDetailDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_page);
        Intent intent = getIntent();
        final String post_id = intent.getStringExtra("post_id");
        final String title,content,time;
        title = intent.getStringExtra("title");
        content = intent.getStringExtra("content");
        time = intent.getStringExtra("time");
        btnSua = (Button) findViewById(R.id.btnDetailEdit);
        btnXoa = (Button) findViewById(R.id.btnDetailDelete);
        tvDetailContent = (TextView) findViewById(R.id.tvDetailContent);
        tvDetailDate = (TextView) findViewById(R.id.tvDetailDate);
        tvDetailTitle = (TextView) findViewById(R.id.tvDetailTitle);

        tvDetailTitle.setText(title);
        tvDetailContent.setText(content);
        tvDetailDate.setText(time);

        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabase = FirebaseDatabase.getInstance().getReference();
                DatabaseReference item = mDatabase.child("posts").child(post_id);
                item.removeValue();
                Toast.makeText(DetailsPageActivity.this, "Đã xoá thành công!! ", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(DetailsPageActivity.this,ListData.class);
                startActivity(intent);
             }
        });

        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailsPageActivity.this,EditPostActivity.class);
                intent.putExtra("post_id", post_id);
                intent.putExtra("title", title);
                intent.putExtra("content", content);
                intent.putExtra("time", time);
                startActivity(intent);
            }
        });

    }
}
