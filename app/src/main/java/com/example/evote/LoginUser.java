package com.example.evote;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.evote.Home.HomeUser;
import com.example.evote.customfonts.MyEditText;
import com.example.evote.customfonts.MyTextView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginUser extends AppCompatActivity {

    private MyEditText email_et;
    private MyEditText password_et;
    private MyTextView btn_masuk;

    private ProgressBar progressBar;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user);

        email_et = findViewById(R.id.email);
        password_et = findViewById(R.id.passworduser);
        btn_masuk = findViewById(R.id.btn_masuk);

        mAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressbar);
        progressBar.setVisibility(View.GONE);

        btn_masuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email_txt = email_et.getText().toString();
                String password_txt = password_et.getText().toString();
                if (TextUtils.isEmpty(email_txt)) {
                    email_et.setError("Masukan Email");
                    email_et.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(password_txt)) {
                    password_et.setError("Masukan Password");
                    password_et.requestFocus();
                    return;}
                progressBar.setVisibility(View.VISIBLE);
                mAuth.signInWithEmailAndPassword(email_txt, password_txt)
                        .addOnCompleteListener(LoginUser.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
                                    // Login sukses, masuk ke Main User
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    Intent intent = new Intent(LoginUser.this, HomeUser.class);
                                    startActivity( intent );
                                    finish();
                                } else {
                                    // Jika Login gagal, memberikan pesan
                                    Toast.makeText(LoginUser.this, "Proses Login gagal : " +  task.getException(),
                                            Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            }
        });

    }
}
