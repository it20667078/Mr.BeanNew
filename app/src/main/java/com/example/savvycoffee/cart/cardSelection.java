package com.example.savvycoffee.cart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.savvycoffee.R;
import com.example.savvycoffee.feedback.feedback_view;
import com.example.savvycoffee.menu.mainMenu;
import com.example.savvycoffee.models.Payment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class cardSelection extends AppCompatActivity {

    private ListView listView;
    private Button cardEdit,cardDelete,pay;
    DatabaseReference savvyCoffeeDbRef;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    Payment card;
    String cardNo = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_selection);

        card = new Payment();

        listView = findViewById(R.id.card_list);
//        cardEdit = findViewById(R.id.edit_card_btn);
//        cardDelete = findViewById(R.id.delete_card_btn);
        pay = findViewById(R.id.pay_btn);

        savvyCoffeeDbRef = FirebaseDatabase.getInstance().getReference().child("Cards");
        list = new ArrayList<>();
        adapter = new ArrayAdapter<String>(this,R.layout.card_list,R.id.card_details,list);

        savvyCoffeeDbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds: snapshot.getChildren()){
                    card = ds.getValue(Payment.class);
                    cardNo = card.getCardNumber().toString();
                    list.add(card.getCardType().toString() + "  " +card.getCardNumber().toString());

//                    cardDelete.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View view) {
//                            savvyCoffeeDbRef = FirebaseDatabase.getInstance().getReference().child("Cards").child(cardNo);
//                            savvyCoffeeDbRef.removeValue();
//                            Toast.makeText(cardSelection.this,"Card Successfully Deleted!",Toast.LENGTH_SHORT).show();
//                        }
//                    });
                }
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                savvyCoffeeDbRef = FirebaseDatabase.getInstance().getReference().child("Cart");
                savvyCoffeeDbRef.removeValue();
                Toast.makeText(cardSelection.this,"Payment Successfully Done!",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(cardSelection.this, mainMenu.class));
            }
        });
    }
}