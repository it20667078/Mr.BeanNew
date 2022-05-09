package com.example.savvycoffee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.savvycoffee.auth.login;

public class splashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        final Thread thread = new Thread()
        {
            @Override
            public void run()
            {
                try {
                    sleep(3000);
                    Intent i= new Intent(splashScreen.this, login.class);
                    startActivity(i);
                    finish();
                }
                catch (Exception ex)
                {
                    ex.printStackTrace();
                }
            }
        };
        thread.start();
    }
}