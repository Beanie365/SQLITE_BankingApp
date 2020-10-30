package com.example.javaproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    EditText username, password, repassword, mobile, email;
    Button loginbtn;
    TextView moveRegister;
    DBHelper db = new DBHelper(this);
    UserClass c = new UserClass();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (EditText) findViewById(R.id.loginEmail);
        password = (EditText) findViewById(R.id.loginPass);
        loginbtn = (Button) findViewById(R.id.loginbtn);
        moveRegister = (TextView) findViewById(R.id.moveRegister);
        db = new DBHelper(this);


        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                if (user.equals("") || pass.equals("")) {
                    Toast.makeText(MainActivity.this, "Please enter details in all the fields", Toast.LENGTH_SHORT).show();
                } else {
                    if (pass.length() < 5) {
                        Toast.makeText(MainActivity.this, "Password too short", Toast.LENGTH_SHORT).show();
                    } else {
                        Boolean checkEmail = isValidEmailId(user);
                        if(checkEmail==false){
                            Toast.makeText(MainActivity.this, "Invalid Email", Toast.LENGTH_SHORT).show();
                        }else {

                            Boolean check = db.checkUser(user, pass);
                            if (check == true) {
                                Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(getApplicationContext(), HomePAge.class);
                                intent.putExtra("email", user);

                                startActivity(intent);
                                finish();
                            } else {
                                Toast.makeText(MainActivity.this, "Login Unsuccessful", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }
            }

        });
        moveRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RegisterPAge.class);
                startActivity(intent);
                finish();
            }
        });

    }
    private boolean isValidEmailId(String email){

        return Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$").matcher(email).matches();
    }

}