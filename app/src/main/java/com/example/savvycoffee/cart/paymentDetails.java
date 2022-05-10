package com.example.savvycoffee.cart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.savvycoffee.R;
import com.example.savvycoffee.models.Payment;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class paymentDetails extends AppCompatActivity {

    private EditText cardName,cardNo,cardType,cvc,expMonth,expYear;
    private Button saveCard;

    DatabaseReference savvyCoffeeDbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_details);

        cardName = findViewById(R.id.card_holder_name);
        cardNo = findViewById(R.id.card_number);
        cardType = findViewById(R.id.card_details);
        cvc = findViewById(R.id.card_cvc);
        expMonth = findViewById(R.id.card_exp_month);
        expYear = findViewById(R.id.card_exp_year);
        saveCard = findViewById(R.id.payment_details_submit_btn);

        saveCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cardNameStr = cardName.getText().toString();
                String cardNoStr = cardNo.getText().toString();
                String cardTypeStr = cardType.getText().toString();
                String cvcStr = cvc.getText().toString();
                String expMstr = expMonth.getText().toString();
                String expYstr = expYear.getText().toString();

                savvyCoffeeDbRef = FirebaseDatabase.getInstance().getReference().child("Cards");
                Payment card = new Payment(cardNameStr,cardTypeStr,cardNoStr,cvcStr,expMstr,expYstr);
                savvyCoffeeDbRef.child(cardNoStr).setValue(card);

                Toast.makeText(paymentDetails.this,"You are successfully add the card!",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(paymentDetails.this, cardSelection.class));
            }
        });
    }
}