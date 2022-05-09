package com.example.savvycoffee.menu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.savvycoffee.R;
import com.example.savvycoffee.cart.cart;
import com.example.savvycoffee.profile.profileDetails;

public class mainMenu extends AppCompatActivity {

    private TextView americanoTxt,espressoTxt,macchiatoTxt,mochaTxt,flatWhiteTxt,cappuccinoTxt,latteTxt;
    private ImageButton profile,cart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        americanoTxt = findViewById(R.id.americano);
        espressoTxt = findViewById(R.id.espresso);
        macchiatoTxt = findViewById(R.id.macchiato);
        mochaTxt = findViewById(R.id.mocha);
        flatWhiteTxt = findViewById(R.id.flat_white);
        cappuccinoTxt = findViewById(R.id.cappuccino);
        latteTxt = findViewById(R.id.latte);
        profile = findViewById(R.id.profileBtn);
        cart = findViewById(R.id.cartBtn);

        americanoTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String americano = americanoTxt.getText().toString();
                Intent i = new Intent(mainMenu.this,product.class);
                i.putExtra("prdName",americano);
                Log.d("btnString",americano);
                startActivity(i);
            }
        });
        espressoTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String espresso = espressoTxt.getText().toString();
                Intent i = new Intent(mainMenu.this,product.class);
                i.putExtra("prdName",espresso);
                startActivity(i);
            }
        });
        macchiatoTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String macchiato = macchiatoTxt.getText().toString();
                Intent i = new Intent(mainMenu.this,product.class);
                i.putExtra("prdName",macchiato);
                startActivity(i);
            }
        });
        mochaTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mocha = mochaTxt.getText().toString();
                Intent i = new Intent(mainMenu.this,product.class);
                i.putExtra("prdName",mocha);
                startActivity(i);
            }
        });
        flatWhiteTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String flatWhite = flatWhiteTxt.getText().toString();
                Intent i = new Intent(mainMenu.this,product.class);
                i.putExtra("prdName",flatWhite);
                startActivity(i);
            }
        });
        cappuccinoTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cuppuccino = cappuccinoTxt.getText().toString();
                Intent i = new Intent(mainMenu.this,product.class);
                i.putExtra("prdName",cuppuccino);
                startActivity(i);
            }
        });
        latteTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String latte = latteTxt.getText().toString();
                Intent i = new Intent(mainMenu.this,product.class);
                i.putExtra("prdName",latte);
                startActivity(i);
            }
        });

        //Profile Button Functions

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(mainMenu.this, profileDetails.class);
                startActivity(i);
            }
        });

        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(mainMenu.this, cart.class);
                startActivity(i);
            }
        });
    }
}