package com.example.admin.mydiary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;

public class ListData extends AppCompatActivity {
    ListView lvDanhSach;
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_data);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        lvDanhSach = (ListView) findViewById(R.id.lvListData);

//        final ArrayList<Post> posts = new ArrayList<>();
//        postsDB.addValueEventListener(new ValueEventListener() {
//
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for(DataSnapshot postSnap : dataSnapshot.getChildren()) {
//                    posts.add(dataSnapshot.getValue(Post.class));
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//
//        });
//        PostAdapter adapter = new PostAdapter(this,posts);
//        lvDanhSach.setAdapter(adapter);
    }
}
