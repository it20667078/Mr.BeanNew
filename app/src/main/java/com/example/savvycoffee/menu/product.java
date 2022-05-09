package com.example.savvycoffee.menu;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.savvycoffee.R;
import com.example.savvycoffee.feedback.add_feedback;
import com.example.savvycoffee.models.Cart;
import com.example.savvycoffee.models.Users;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class product extends AppCompatActivity {

    //String prdName = "AMERICANO";
    private TextView title,description,price;
    private ImageView image;
    private EditText qty;
    private RadioButton small,medium,large;
    private Button addCart,backToMenu;

    int smallPrice = 50;
    int mediumPrice = 0;
    int largePrice = 100;

    int totalPrice = 0;
    String portionSize = "";
    String productName = "";

    DatabaseReference coffeeDbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        String prdNameStr = getIntent().getExtras().getString("prdName");

        title = findViewById(R.id.product_title);
        image = findViewById(R.id.product_img);
        description = findViewById(R.id.product_desc);
        price = findViewById(R.id.prod_price);
        qty = findViewById(R.id.product_qty);
        small = findViewById(R.id.samll);
        medium = findViewById(R.id.medium);
        large = findViewById(R.id.large);
        addCart = findViewById(R.id.prd_add_cart_btn);
        backToMenu = findViewById(R.id.prd_back_menu_btn);

        coffeeDbRef = FirebaseDatabase.getInstance().getReference().child("Products").child(prdNameStr);
        coffeeDbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    String prodTitle = (String) snapshot.child("title").getValue();
                    String prodImage = (String) snapshot.child("imageUrl").getValue();
                    String prodPrice = (String) snapshot.child("price").getValue();
                    String prodDesc = (String) snapshot.child("description").getValue();
                    title.setText(prodTitle);
                    productName = prodTitle;
                    totalPrice = Integer.parseInt(prodPrice);
                    price.setText("Rs :" + prodPrice);
                    description.setText(prodDesc);

                    if(prodTitle.equals("Americano")){
                        image.setImageResource(R.drawable.americano);
                    }
                    if(prodTitle.equals("Espresso")){
                        image.setImageResource(R.drawable.espresso);
                    }
                    if(prodTitle.equals("Macchiato")){
                        image.setImageResource(R.drawable.macchiato);
                    }
                    if(prodTitle.equals("Mocha")){
                        image.setImageResource(R.drawable.mocha);
                    }
                    if(prodTitle.equals("Flat White")){
                        image.setImageResource(R.drawable.flatwhite);
                    }
                    if(prodTitle.equals("Cappuccino")){
                        image.setImageResource(R.drawable.cappuccino);
                    }
                    if(prodTitle.equals("Latte")){
                        image.setImageResource(R.drawable.latte);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        addCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s1 = small.getText().toString();
                String s2 = medium.getText().toString();
                String s3 = large.getText().toString();

                String netQtyStr = qty.getText().toString();

                int netQty = Integer.parseInt(netQtyStr);

                totalPrice = totalPrice * netQty;

                if(small.isChecked()){
                    totalPrice = totalPrice - smallPrice;
                    portionSize = s1;
                }
                else if(medium.isChecked()){
                    totalPrice = totalPrice;
                    portionSize = s2;
                }
                else if(large.isChecked()){
                    totalPrice = totalPrice + largePrice;
                    portionSize = s3;
                }

                String totPriceStr = String.valueOf(totalPrice);

                coffeeDbRef = FirebaseDatabase.getInstance().getReference().child("Cart");
                Cart cart = new Cart(productName,netQtyStr,portionSize,totPriceStr);
                coffeeDbRef.child(productName).setValue(cart);

                Toast.makeText(product.this,"Your Product Successfully added to the cart!",Toast.LENGTH_SHORT).show();
            }
        });

        backToMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(product.this,mainMenu.class);
                startActivity(i);
            }
        });
    }
}