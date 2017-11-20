package com.groupmicrofin.moneycalc.moneycalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginAcivtiy extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_acivtiy);
    }

    public void login(View view) {
        EditText epinET = findViewById(R.id.epin);
        int epin = Integer.parseInt(String.valueOf(epinET.getText()));
        if(epin==1234){
            Intent grpRegActivityIntent = new Intent(LoginAcivtiy.this,GroupActivity.class);
            startActivity(grpRegActivityIntent);
        } else {
            Toast invLogin = Toast.makeText(this,"Incorrect PIN",Toast.LENGTH_SHORT);
            invLogin.show();
            epinET.clearFocus();
        }
    }


}
