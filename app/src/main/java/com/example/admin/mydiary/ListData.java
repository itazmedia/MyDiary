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
     ArrayList<Post> posts = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_data);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        lvDanhSach = (ListView) findViewById(R.id.lvListData);
        final DatabaseReference postsDB = mDatabase.child("posts");
        mDatabase.child("posts").child("d").setValue(new Post("default", "default","default","default","default"));

        postsDB.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot postSnap : dataSnapshot.getChildren()) {
                    DatabaseReference dbR = postsDB.child(postSnap.getKey());
                    dbR.addValueEventListener(new ValueEventListener(){
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            Post p = dataSnapshot.getValue(Post.class);
                            posts.add(p);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });
        PostAdapter adapter = new PostAdapter(this,posts);
        lvDanhSach.setAdapter(adapter);
    }
}
