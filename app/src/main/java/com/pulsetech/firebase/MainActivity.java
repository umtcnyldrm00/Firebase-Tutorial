package com.pulsetech.firebase;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    EditText logintext, passtext;
    Button register;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();


        logintext=findViewById(R.id.posta);
        passtext=findViewById(R.id.pass);
        register=findViewById(R.id.btn);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KayitOl();
            }
        });


    }


    private void KayitOl() {

        String txtMail = logintext.getText().toString();
        String txtPass = passtext.getText().toString();

        if (!TextUtils.isEmpty(txtMail) && !TextUtils.isEmpty(txtPass)) {
            mAuth.createUserWithEmailAndPassword(txtMail, txtPass)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(MainActivity.this, "Kayıt yapıldı", Toast.LENGTH_SHORT).show();
                            } else
                                Toast.makeText(MainActivity.this, "Kayıt yapılamadı", Toast.LENGTH_SHORT).show();

                        }
                    });

        } else
            Toast.makeText(MainActivity.this, "Kullanıcı adı veya şifre boş olamaz", Toast.LENGTH_SHORT).show();

    }
}