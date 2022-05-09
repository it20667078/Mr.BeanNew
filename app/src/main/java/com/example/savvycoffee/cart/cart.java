package com.example.savvycoffee.cart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.savvycoffee.R;
import com.example.savvycoffee.feedback.feedback_view;
import com.example.savvycoffee.models.Cart;
import com.example.savvycoffee.models.Users;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class cart extends AppCompatActivity {

    private ListView listView;
    private TextView totPrice;
    private Button removeAll,checkout;
    DatabaseReference coffeeDbRef;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    Cart cart;
    int cartTotal = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        cart = new Cart();
        listView = findViewById(R.id.listView);
        totPrice = findViewById(R.id.cart_tot_price);
        removeAll = findViewById(R.id.cart_remove_all_btn);
        checkout = findViewById(R.id.cart_checkout_btn);

        coffeeDbRef = FirebaseDatabase.getInstance().getReference().child("Cart");
        list = new ArrayList<>();
        adapter = new ArrayAdapter<String>(this,R.layout.cart_items,R.id.cartItem,list);

        coffeeDbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds: snapshot.getChildren()){
                    cart = ds.getValue(Cart.class);
                    list.add(cart.getProductName().toString() + "   " +cart.getPortion().toString() + "   " +cart.getQuantity().toString()+ "       " + cart.getNetTotal().toString());
                    int itemTotal = Integer.parseInt(cart.getNetTotal());
                    cartTotal = cartTotal + itemTotal;
                }
                listView.setAdapter(adapter);
                totPrice.setText("Rs :" + cartTotal);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        removeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                coffeeDbRef.removeValue();
                Toast.makeText(cart.this,"All products are deleted!",Toast.LENGTH_SHORT).show();
            }
        });

        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(com.example.savvycoffee.cart.cart.this,paymentDetails.class);
                startActivity(i);
            }
        });
    }
}