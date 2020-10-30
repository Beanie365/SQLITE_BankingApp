package com.example.javaproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class BalancePage extends AppCompatActivity {
    DBHelper db = new DBHelper(this);
    UserClass c = new UserClass();
    TextView viewName, viewSurname, viewBalance, viewSavings;
    ImageView backbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balance_page);

        viewName = (TextView) findViewById(R.id.viewName);
        viewSurname = (TextView) findViewById(R.id.viewSurname);
        viewBalance = (TextView) findViewById(R.id.currentBalance);
        viewSavings = (TextView) findViewById(R.id.currentSavings);
        backbtn = (ImageView) findViewById(R.id.backbtn);

        viewName.setText("Account Holder First Name: " + c.getFirstname() );
        viewSurname.setText("Account Holder Surname: "+c.getLastname());
        viewBalance.setText("Account Holder Current Balance: " +c.getBalance());
        viewSavings.setText("Account Holder Current Savings: "+c.getSavings());

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),HomePAge.class);
                startActivity(intent);
                finish();
            }
        });
    }
}