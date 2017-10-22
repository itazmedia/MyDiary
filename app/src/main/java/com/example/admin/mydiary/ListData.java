package com.example.admin.mydiary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.content.Intent;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
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

        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        final DatabaseReference postsDB = mDatabase.child("posts");
        mDatabase.child("posts").child("d").setValue(new Post("default", "default","default","default","default"));
        final PostAdapter adapter = new PostAdapter(this,posts);
        lvDanhSach.setAdapter(adapter);
        postsDB.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot postSnap : dataSnapshot.getChildren()) {
                    DatabaseReference dbR = postsDB.child(postSnap.getKey());
                    dbR.addValueEventListener(new ValueEventListener(){
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            Post p = dataSnapshot.getValue(Post.class);
                            if(p.getEmail().equalsIgnoreCase(user.getEmail())) {
                                posts.add(p);
                                adapter.notifyDataSetChanged();
                            }
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

        lvDanhSach.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ListData.this,DetailsPageActivity.class);
                intent.putExtra("post_id", posts.get(position).getPost_id());
                intent.putExtra("title", posts.get(position).getName());
                intent.putExtra("content", posts.get(position).getContent());
                intent.putExtra("time", posts.get(position).getTime());
                startActivity(intent);
            }
        });
    }

}
