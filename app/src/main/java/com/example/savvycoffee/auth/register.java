package com.example.savvycoffee.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.savvycoffee.R;
import com.example.savvycoffee.models.Users;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class register extends AppCompatActivity {

    private EditText reg_firstName;
    private EditText reg_lastName;
    private RadioGroup reg_gender;
    private EditText reg_email;
    private EditText reg_phoneNo;
    private EditText reg_userName;
    private EditText reg_password;
    private EditText reg_confPassword;
    private Button reg_signUp;
    private RadioButton radioButton;
    private String gender = "";

    FirebaseAuth mAuth;
    DatabaseReference coffeeDbRef;

    String uid = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        reg_firstName = findViewById(R.id.reg_firstName);
        reg_lastName = findViewById(R.id.reg_lastName);
        reg_gender = findViewById(R.id.gender);
        reg_email = findViewById(R.id.reg_email);
        reg_phoneNo = findViewById(R.id.reg_phoneNo);
        reg_userName = findViewById(R.id.reg_userName);
        reg_password = findViewById(R.id.reg_password);
        reg_confPassword = findViewById(R.id.reg_confPassword);
        reg_signUp = findViewById(R.id.regBtnSubmit);

        reg_gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch(checkedId) {
                    case(R.id.regMale):
                        gender = "Male";
                        break;
                    case(R.id.regFemale):
                        gender = "Female";
                        break;
                }
            }
        });

        mAuth = FirebaseAuth.getInstance();

        reg_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String first_name = reg_firstName.getText().toString();
                String last_name = reg_lastName.getText().toString();

//                int selectedGender = reg_gender.getCheckedRadioButtonId();
//                radioButton = (RadioButton)findViewById(selectedGender);

                //String gender = radioButton.getText().toString();
                String email = reg_email.getText().toString();
                String phoneNo = reg_phoneNo.getText().toString();
                String userName = reg_userName.getText().toString();
                String password = reg_password.getText().toString();
                String confPassword = reg_confPassword.getText().toString();

                if(first_name.isEmpty())
                {
                    reg_firstName.setError("First Name is empty");
                    reg_firstName.requestFocus();
                    return;
                }
                if(last_name.isEmpty())
                {
                    reg_lastName.setError("Last Name is empty");
                    reg_lastName.requestFocus();
                    return;
                }
                if(userName.isEmpty())
                {
                    reg_userName.setError("User Name is empty");
                    reg_userName.requestFocus();
                    return;
                }
                if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
                {
                    reg_email.setError("Enter the valid email address");
                    reg_email.requestFocus();
                    return;
                }
                if(password.isEmpty())
                {
                    reg_password.setError("Enter the password");
                    reg_password.requestFocus();
                    return;
                }
                if(password.length()<8)
                {
                    reg_password.setError("Length of the password should be more than 8");
                    reg_password.requestFocus();
                    return;
                }
                if(phoneNo.length()<10)
                {
                    reg_phoneNo.setError("Length of the phone number should be more than 10");
                    reg_phoneNo.requestFocus();
                    return;
                }
//                if(password != confPassword)
//                {
//                    reg_confPassword.setError("Password is not equal! Please check again");
//                    reg_confPassword.requestFocus();
//                    return;
//                }

                loginUser();

            }
        });

    }
    private void loginUser(){
        String email = reg_email.getText().toString();
        String password = reg_password.getText().toString();
        String first_name = reg_firstName.getText().toString();
        String last_name = reg_lastName.getText().toString();
        String phoneNo = reg_phoneNo.getText().toString();
        String userName = reg_userName.getText().toString();

        coffeeDbRef = FirebaseDatabase.getInstance().getReference().child("Users");

        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                uid = Objects.requireNonNull(mAuth.getCurrentUser()).getUid();
                Log.d("currentUid",uid);
                Users users = new Users(first_name,last_name,gender,email,phoneNo,userName,password);
                coffeeDbRef.child(uid).setValue(users);
                Toast.makeText(register.this,"You are successfully Registered!",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(register.this,login.class));
            }
            else{
                Toast.makeText(register.this,"You are not registered! Try Again",Toast.LENGTH_SHORT).show();
            }
        });
    }
}