package com.groupmicrofin.moneycalc.moneycalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class LoginAcivtiy extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_acivtiy);
    }

    public void Login(View view) {
        EditText epinET = findViewById(R.id.epin);
        int epin = Integer.parseInt(String.valueOf(epinET.getText()));


        }


    }
