package com.groupmicrofin.moneycalc.moneycalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class GroupRegistrationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_registration);
    }
    public void addAccount(View view){
        String grpNameLabel = getString(R.string.grpName);

        EditText grpRegET = findViewById(R.id.groupRegNo);
        String grpRegNo = String.valueOf(grpRegET.getText());

        EditText grpNmET = findViewById(R.id.groupName);
        String grpRegNm = String.valueOf(grpNmET.getText());

        EditText grpSDET = findViewById(R.id.groupStartDate);
        String grpSD = String.valueOf(grpSDET.getText());

        EditText grpShareAmtET = findViewById(R.id.shareAmount);
        int grpShareAmt = Integer.parseInt(String.valueOf(grpShareAmtET.getText()));

        EditText grpIntRtET = findViewById(R.id.interestRate);
        String grpIntRt = String.valueOf(grpIntRtET.getText());


        String toastText = "RegNo: "+grpRegNo+" RegNm: "+grpRegNm+grpSD+" ShareAmt: "+grpShareAmt+" Int Rate:"+ grpIntRt;

        Toast ourFirstToast = Toast.makeText(this,toastText,Toast.LENGTH_LONG);
        ourFirstToast.show();
    }


}


