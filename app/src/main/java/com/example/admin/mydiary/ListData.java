package com.example.admin.mydiary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ListData extends AppCompatActivity {
    ListView lvDanhSach;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_data);
        lvDanhSach = (ListView) findViewById(R.id.lvList);
        ArrayList<Post> posts = new ArrayList<>();
        posts.add(new Post("Hôm nay là thứ 2", "02/01/2017"));
        posts.add(new Post("Hôm nay là thứ 3", "03/01/2017"));
        posts.add(new Post("Hôm nay là thứ 4", "04/01/2017"));
        posts.add(new Post("Hôm nay là thứ 5", "05/01/2017"));
        posts.add(new Post("Hôm nay là thứ 6", "06/01/2017"));
        posts.add(new Post("Hôm nay là thứ 7", "07/01/2017"));
        posts.add(new Post("Hôm nay là Chủ nhật", "08/01/2017"));
        PostAdapter adapter = new PostAdapter(this,posts);
        lvDanhSach.setAdapter(adapter);
    }
}
