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
    private EditText txtOldPass, txtNewPass, txtReNewPass, txtEmail;
    private Button btnSubmit;

    AuthCredential credential = EmailAuthProvider
            .getCredential("user@example.com", "password1234");

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
                if (txtNewPass.getText().toString().equalsIgnoreCase("")) {
                    txtNewPass.setHint("Không được trống!");
                } else {
                    if (txtReNewPass.getText().toString().equalsIgnoreCase("")) {
                        txtReNewPass.setHint("Không đươc trống!");
                    } else {
                        if (txtOldPass.getText().toString().equalsIgnoreCase("")) {
                            txtOldPass.setHint("Không được trống!");
                        } else if (!txtNewPass.getText().toString().equalsIgnoreCase(txtReNewPass.getText().toString())) {
                            Toast.makeText(getBaseContext(), "Error", Toast.LENGTH_SHORT).show();
                        } else {
                            changePass(txtEmail.getText().toString(), txtOldPass.getText().toString(), txtNewPass.getText().toString(),
                                    txtReNewPass.getText().toString());
                            Intent intent = new Intent(ChangePasswordActivity.this, MainActivity.class);
                            startActivity(intent);
                        }
                    }
                }
            }

        });
    }

    public void changePass(String email, String oldPass, final String newPass, String reNewPass)
    {
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();


        AuthCredential credential = EmailAuthProvider
                .getCredential("user@example.com", "password1234");

        user.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    user.updatePassword(newPass).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(getBaseContext(),"Password updated", Toast.LENGTH_SHORT).show();

                            } else {
                                Toast.makeText(getBaseContext(),"Error password not updated", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } else {
                    Toast.makeText(getBaseContext(),"Error auth failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
