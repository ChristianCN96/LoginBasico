package com.example.ejemplologin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainRegistroActivity extends AppCompatActivity {

    private EditText email, password;
    private Button register;
    private ProgressDialog progressDialog;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_registro);

        progressDialog = new ProgressDialog(this);

        firebaseAuth = FirebaseAuth.getInstance();

        email = (EditText) findViewById(R.id.txtEmail);
        password = (EditText) findViewById(R.id.txtContraseña);
        register = (Button) findViewById(R.id.CvRegistro);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String userE = email.getText().toString();
                String passE = password.getText().toString();

                if (TextUtils.isEmpty(userE)) {
                    Toast.makeText(MainRegistroActivity.this, "Inserte un correo valido", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(passE)) {
                    Toast.makeText(MainRegistroActivity.this, "Inserte una contraseña valida", Toast.LENGTH_SHORT).show();
                    return;
                }
                progressDialog.setMessage("En proceso de registro");
                progressDialog.show();

                firebaseAuth.createUserWithEmailAndPassword(userE, passE).addOnCompleteListener(MainRegistroActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Toast.makeText(MainRegistroActivity.this, "Usuario Registrado", Toast.LENGTH_SHORT).show();
                        if (!task.isSuccessful()) {
                            Toast.makeText(MainRegistroActivity.this, "No se registro", Toast.LENGTH_SHORT).show();
                        }
                        //Una vez registrado pasa a la siguiente actividad
                        Intent intent = new Intent( MainRegistroActivity.this, MainLoginActivity.class);
                        startActivity(intent);
                    }
                });
            }
        });
    }
}