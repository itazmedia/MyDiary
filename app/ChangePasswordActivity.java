package com.example.admin.mydiary;

import android.content.Intent;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ChangePasswordActivity extends AppCompatActivity {
    private EditText txtOldPass, txtNewPass, txtReNewPass;
    private Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        txtOldPass = (EditText) findViewById(R.id.txtOldPassword);
        txtNewPass = (EditText) findViewById(R.id.txtNewPassword);
        txtReNewPass = (EditText) findViewById(R.id.txtReNewPassword);

        btnSubmit = (Button) findViewById(R.id.btnChangePassword);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (txtOldPass.getText().toString().equalsIgnoreCase("")) {
                    txtOldPass.setHint("Không được trống!");
                } else {
                    if (txtNewPass.getText().toString().equalsIgnoreCase("")) {
                        txtNewPass.setHint("Không đươc trống!");
                    } else {
                        if (txtReNewPass.getText().toString().equalsIgnoreCase("")) {
                            txtReNewPass.setHint("Không được trống!");
                        } else if (!txtNewPass.getText().toString().equalsIgnoreCase(txtReNewPass.getText().toString())) {
                            Toast.makeText(getBaseContext(), "Error", Toast.LENGTH_SHORT).show();
                        } else {
                            changePass(txtNewPass.getText().toString());

                        }
                    }
                }
            }

        });
    }


    public void changePass(String newPass)
    {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        user.updatePassword(newPass.trim())
                .addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(ChangePasswordActivity.this, "Thay đổi mật khẩu thành công!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(ChangePasswordActivity.this, MainActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(ChangePasswordActivity.this, "Thay đổi mật khẩu thất bại!!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
//
    }
}
