package com.example.savvycoffee.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.savvycoffee.R;
import com.example.savvycoffee.menu.mainMenu;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {

    private EditText userName;
    private EditText log_password;
    private Button login, signUp;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userName = findViewById(R.id.login_userName);
        log_password = findViewById(R.id.login_password);
        login = findViewById(R.id.loginBtnSubmit);
        signUp = findViewById(R.id.sign_up_btn);

        mAuth = FirebaseAuth.getInstance();

        login.setOnClickListener(v -> {
            String email = userName.getText().toString();
            String password = log_password.getText().toString();

            if(email.isEmpty())
            {
                userName.setError("User Name is empty");
                userName.requestFocus();
                return;
            }
            if(password.isEmpty())
            {
                log_password.setError("Password is empty");
                log_password.requestFocus();
                return;
            }
            if(password.length()<8)
            {
                log_password.setError("Length of password is more than 6");
                log_password.requestFocus();
                return;
            }
            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(task -> {
                if(task.isSuccessful())
                {
                    startActivity(new Intent(login.this, mainMenu.class));
                }
                else
                {
                    Toast.makeText(login.this,"Please Check your login Credentials", Toast.LENGTH_SHORT).show();
                }
            });
        });
        signUp.setOnClickListener(v -> startActivity(new Intent(login.this,register.class )));
    }
}