package com.example.ejemplologin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainLoginActivity extends AppCompatActivity {

    private EditText email, password;
    private CardView login, register;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseApp.initializeApp(this);
        firebaseAuth = FirebaseAuth.getInstance();

        email = (EditText) findViewById(R.id.txtEmail);
        password = (EditText) findViewById(R.id.txtContraseña);
        login = (CardView) findViewById(R.id.CvLogin);
        register = (CardView) findViewById(R.id.CvRegistro);

        //Accion de los botones

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userE = email.getText().toString();
                String passE = password.getText().toString();

                //Validaciones de campos vacios
                if (TextUtils.isEmpty(userE)) {
                    Toast.makeText(MainLoginActivity.this, "Inserte un correo valido", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(passE)) {
                    Toast.makeText(MainLoginActivity.this, "Inserte una contraseña valida", Toast.LENGTH_SHORT).show();
                    return;
                }

                firebaseAuth.signInWithEmailAndPassword(userE, passE).addOnCompleteListener(MainLoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            Toast.makeText(MainLoginActivity.this, "El usuario o contraseña incorrecta", Toast.LENGTH_SHORT).show();
                        }else {
                            Intent intent = new Intent(MainLoginActivity.this, MainHomeActivity.class);
                            startActivity(intent);
                        }
                    }
                });

            }
        });

    }
}

