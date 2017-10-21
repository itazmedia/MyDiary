package com.example.admin.mydiary;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.preference.DialogPreference;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

public class Home extends AppCompatActivity {
    private  ImageButton imgAddnew;

    private ImageButton imgListDiary;

    private ImageButton imgChangePassword;

    private  ImageButton imgLogoutHome;

    private DatabaseReference mDatabase;

    private  ImageButton imgPlan;
    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);



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






        imgListDiary = (ImageButton) findViewById(R.id.listPost);
        imgListDiary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, ListData.class);
                startActivity(intent);

            }
        });
        imgLogoutHome = (ImageButton) findViewById(R.id.logOutHome);
        imgLogoutHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder( context);
                alertDialogBuilder.setTitle("My Diary");
                alertDialogBuilder
                        .setMessage("Bạn có muốn đăng xuất không!")
                        .setCancelable(false)
                        .setPositiveButton("Có",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                FirebaseAuth.getInstance().signOut();
                                Home.this.finish();
                            }
                        })
                        .setNegativeButton("K",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {

                                dialog.cancel();
                            }
                        });


                AlertDialog alertDialog = alertDialogBuilder.create();


                alertDialog.show();

            }
        });

    }



}
