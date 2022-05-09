package com.example.savvycoffee.feedback;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.savvycoffee.R;
import com.example.savvycoffee.auth.login;
import com.example.savvycoffee.auth.register;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class feedback_view extends AppCompatActivity {

    TextView feedbackTxt;
    Button delete,update,send;

    DatabaseReference savvyCoffeeDbRef;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_view);

        feedbackTxt = findViewById(R.id.feedback);
        delete = findViewById(R.id.feedback_delete_btn);

        mAuth = FirebaseAuth.getInstance();

        String uid = Objects.requireNonNull(mAuth.getCurrentUser()).getUid();
        savvyCoffeeDbRef = FirebaseDatabase.getInstance().getReference().child("Feedbacks").child(uid);

        savvyCoffeeDbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    String comment = (String) snapshot.child("comment").getValue();

                    feedbackTxt.setText(comment);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        delete.setOnClickListener(view -> {
            savvyCoffeeDbRef.removeValue();
            Toast.makeText(feedback_view.this,"Feedback Successfully Deleted!",Toast.LENGTH_SHORT).show();
            startActivity(new Intent(feedback_view.this, add_feedback.class));
        });
    }
}