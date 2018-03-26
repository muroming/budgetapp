package com.example.user.budgetapp;

import Utils.DataStructures.CreditCard;
import Utils.DataStructures.CreditCardDB;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.*;

import java.util.ArrayList;

public class CreateCardActivity extends AppCompatActivity {

    private Spinner paymentSystem;
    private ArrayList<String> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_card);

        EditText title = findViewById(R.id.create_card_title);
        EditText balance = findViewById(R.id.create_card_balance);
        paymentSystem = findViewById(R.id.create_card_system);
        Button addButton = findViewById(R.id.create_card_add_button);

        final CreditCard card = new CreditCard();

        title.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                card.setTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        balance.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                card.setMoney(Integer.parseInt(s.toString()));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        init_spinner();

        paymentSystem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                card.setCardType(data.get(position));
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkCorrect(card)) {
                    CreditCardDB.getDatabase().addCard(card);
                    setResult(0);
                    finish();
                }
            }
        });
    }

    private boolean checkCorrect(CreditCard card) {
        if(card.getTitle() != null){
            if(card.getTitle().isEmpty()) {
                Toast.makeText(getApplicationContext(), "No title is set", Toast.LENGTH_SHORT).show();
                return false;
            }
        }else{
            Toast.makeText(getApplicationContext(), "No title is set", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (card.getCardType() == null){
            Toast.makeText(getApplicationContext(), "No payment system is set", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    void init_spinner() {
        data = new ArrayList<>();
        data.add("Visa");
        data.add("MasterCard");
        data.add("AmericanExpress");
        data.add("MIR");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1,
                data);
        paymentSystem.setAdapter(adapter);
    }
}
