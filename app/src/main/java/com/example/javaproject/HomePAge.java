package com.example.javaproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class HomePAge extends AppCompatActivity {
    DBHelper db = new DBHelper(this);
    TextView name;
    Button balancebtn, transferbtn, logoutbtn;
    String mail;
    Cursor cursor;
    UserClass c = new UserClass();
    String a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_p_age);

        name = (TextView) findViewById(R.id.user);
        balancebtn = (Button) findViewById(R.id.balancebtn);
        transferbtn = (Button) findViewById(R.id.transferbtn);
        logoutbtn = (Button) findViewById(R.id.logoutbtn);
        mail = getIntent().getStringExtra("email");

        db.getDetails(mail);
        name.setText("Hello, " + c.getFirstname());

        balancebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), BalancePage.class);
                startActivity(intent);
                finish();

            }
        });

        transferbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), TransferPage.class);
                startActivity(intent);
                finish();
            }
        });

        logoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(HomePAge.this, "Logging Out...", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}