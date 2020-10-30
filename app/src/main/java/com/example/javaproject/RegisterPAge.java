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

public class RegisterPAge extends AppCompatActivity {

    EditText firstname,lastname, password, mobile, email;
    Button registerbtn;
    TextView moveLogin;
    DBHelper db = new DBHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_p_age);

        firstname = (EditText) findViewById(R.id.firstname);
        lastname = (EditText) findViewById(R.id.lastname);
        password = (EditText) findViewById(R.id.password);
        mobile = (EditText) findViewById(R.id.mobile);
        email = (EditText) findViewById(R.id.email);
       final RegisterPAge reg = new RegisterPAge();
        registerbtn= (Button) findViewById(R.id.registerbtn);
        moveLogin = (TextView) findViewById(R.id.moveLogin);

        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String first = firstname.getText().toString();
                String last = lastname.getText().toString();
                String pass = password.getText().toString();
                String mail = email.getText().toString().trim();
                String number = mobile.getText().toString();


                if(first.equals("") || pass.equals("") || last.equals("") || mail.equals("") || number.equals("") || number.length() != 10){
                    Toast.makeText(RegisterPAge.this, "Please enter details in all the fields", Toast.LENGTH_SHORT).show();
                }else{
                    if(pass.length()<5){
                        Toast.makeText(RegisterPAge.this, "Password too short", Toast.LENGTH_SHORT).show();
                    }else {


                        Boolean check = db.checkUser(mail);
                        if (check == false) {
                            Boolean mailcheck = reg.isValidEmailId(mail);
                            if (mailcheck == true) {

                                UserClass c = new UserClass();

                                c.setEmail(mail);
                                c.setFirstname(first);
                                c.setLastName(last);
                                c.setPassword(pass);
                                c.setMobile(number);
                                db.addUser(c);
                                Toast.makeText(RegisterPAge.this, "Registered Successfully.", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), HomePAge.class);
                                startActivity(intent);
                                finish();

                            } else {
                                Toast.makeText(RegisterPAge.this, "Invalid email", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(RegisterPAge.this, "User already exists.", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });
        moveLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
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
