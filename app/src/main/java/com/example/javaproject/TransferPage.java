package com.example.javaproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class TransferPage extends AppCompatActivity {
    DBHelper db = new DBHelper(this);
    TextView viewBalance, viewSavings;
    ImageView backbtn;
    EditText transferAmount;
    Button transferbtn;
    Spinner transferType;
    Double balance,savings;
String email;
    UserClass c = new UserClass();
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer_page);

        viewBalance = (TextView) findViewById(R.id.transferBalance);
        viewSavings = (TextView) findViewById(R.id.transferSavings);
        backbtn = (ImageView) findViewById(R.id.backbtnTransfer);
        transferAmount = (EditText) findViewById(R.id.transferAmount);
        transferType = (Spinner) findViewById(R.id.transferType);
        transferbtn = (Button) findViewById(R.id.transfertoAccBtn);

        viewBalance.setText("Current Account Balance: " +c.getBalance());
        viewSavings.setText("Current Account Savings: " + c.getSavings());
balance = c.getBalance();
savings = c.getSavings();
email = c.getEmail();
        ArrayAdapter<String> transferChoice = new ArrayAdapter<String>(TransferPage.this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.spinnerItems));
        transferChoice.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        transferType.setAdapter(transferChoice);


        transferbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String choice = transferType.getSelectedItem().toString();
                String amount = transferAmount.getText().toString();
                if(amount.equals( "")|| amount .equals(null)){
                    Toast.makeText(TransferPage.this, "Transfer amount cannot be empty", Toast.LENGTH_SHORT).show();
                }else{
                    Double value = Double.parseDouble(amount);
                    if(choice.equals("Balance to Savings")){
                        if(balance>value){
                        balance = balance-value;
                        savings = savings+value;
                        db.updateDetails(balance,savings,email);
                        Toast.makeText(TransferPage.this, "Transfer Successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),TransferPage.class);
                        startActivity(intent);
                        finish();
                        }else{
                            Toast.makeText(TransferPage.this, "Insufficient Funds", Toast.LENGTH_SHORT).show();
                        }

                    }else if(choice.equals("Savings to Balance")){
                        if(savings>value) {
                            balance = balance + value;
                            savings = savings - value;
                            db.updateDetails(balance, savings, email);

                            Toast.makeText(TransferPage.this, "Transfer Successful", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), TransferPage.class);
                            startActivity(intent);
                            finish();
                        }else{
                            Toast.makeText(TransferPage.this, "Insufficient Funds", Toast.LENGTH_SHORT).show();
                        }
                    }
                }



            }
        });



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