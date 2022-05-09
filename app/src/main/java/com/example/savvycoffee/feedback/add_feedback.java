package com.example.savvycoffee.feedback;

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
import com.example.savvycoffee.models.Feedbacks;
import com.example.savvycoffee.models.Users;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class add_feedback extends AppCompatActivity {

    private TextView comment;
    private Button addComment;

    DatabaseReference savvyCoffeeDbRef;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_feedback);

        mAuth = FirebaseAuth.getInstance();

        comment = findViewById(R.id.feedback_comment);
        addComment = findViewById(R.id.add_comment);

        addComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newComment = comment.getText().toString();
                String uid = Objects.requireNonNull(mAuth.getCurrentUser()).getUid();

                savvyCoffeeDbRef = FirebaseDatabase.getInstance().getReference().child("Feedbacks");
                Feedbacks feedback = new Feedbacks(uid,newComment);
                savvyCoffeeDbRef.child(uid).setValue(feedback);

                Toast.makeText(add_feedback.this,"Your Feedback Successfully Send!",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(add_feedback.this, feedback_view.class));
            }
        });
    }
}