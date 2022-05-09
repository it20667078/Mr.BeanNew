package com.example.savvycoffee.profile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.savvycoffee.R;
import com.example.savvycoffee.auth.login;
import com.example.savvycoffee.feedback.add_feedback;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class profileDetails extends AppCompatActivity {

    private TextView userNametxt,firstNametxt,lastNametxt,phoneNotxt,gendertxt,emailtxt;
    private Button editProfile,deleteProfile,feedback,logout;

    DatabaseReference savvyCoffeeDbRef;
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_details);

        userNametxt = findViewById(R.id.profile_userName);
        firstNametxt = findViewById(R.id.profile_firstName);
        lastNametxt = findViewById(R.id.profile_lastName);
        phoneNotxt = findViewById(R.id.profile_phone);
        gendertxt = findViewById(R.id.profile_gender);
        emailtxt = findViewById(R.id.profile_email);

        String uid = Objects.requireNonNull(firebaseAuth.getCurrentUser()).getUid();
        savvyCoffeeDbRef = FirebaseDatabase.getInstance().getReference().child("Users").child(uid);
        String key = savvyCoffeeDbRef.push().getKey();
        Log.d("key",key);

        savvyCoffeeDbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    String userName = (String) snapshot.child("userName").getValue();
                    String firstName = (String) snapshot.child("firstName").getValue();
                    String lastName = (String) snapshot.child("lastName").getValue();
                    String phoneNo = (String) snapshot.child("phoneNo").getValue();
                    String gender = (String) snapshot.child("gender").getValue();
                    String email = (String) snapshot.child("email").getValue();

                    Log.d("username",userName);

                    userNametxt.setText(userName);
                    firstNametxt.setText(firstName);
                    lastNametxt.setText(lastName);
                    phoneNotxt.setText(phoneNo);
                    gendertxt.setText(gender);
                    emailtxt.setText(email);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        Log.d("dbKey",uid);

        editProfile = findViewById(R.id.profile_btn_edit);
        deleteProfile = findViewById(R.id.profile_btn_delete);
        feedback = findViewById(R.id.profile_btn_feedback);
        logout = findViewById(R.id.profile_btn_logout);

        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(profileDetails.this,profileEdit.class);
                startActivity(i);
            }
        });

        deleteProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                savvyCoffeeDbRef.removeValue();
                FirebaseAuth.getInstance().getCurrentUser().delete();
                System.out.println("Successfully deleted user.");
            }
        });

        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(profileDetails.this, add_feedback.class);
                startActivity(i);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.signOut();
                Intent i = new Intent(profileDetails.this, login.class);
                startActivity(i);
            }
        });
    }
}