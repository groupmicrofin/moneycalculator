package com.groupmicrofin.moneycalc.moneycalculator;

import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AccountRegistrationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_registration);
    }
    public void addAccount(View view){


        EditText grpAcIdET = findViewById(R.id.groupAcId);
        String grpAcId= String.valueOf(grpAcIdET.getText());

        EditText grpMrNmET = findViewById(R.id.groupMemberName);
        String grpMrNm = String.valueOf(grpMrNmET.getText());

        EditText grpLMDET = findViewById(R.id.lastMeetDate);
        String grpLMD = String.valueOf(grpLMDET.getText());

        EditText grpPnLoanET = findViewById(R.id.pendingLoan);
        int grpPnLoan = Integer.parseInt(String.valueOf(grpPnLoanET.getText()));

        EditText emailET = findViewById(R.id.emailId);
        String email = String.valueOf(emailET.getText());

        EditText phoneET = findViewById(R.id.phoneNo);
        int phone = Integer.parseInt(String.valueOf(phoneET.getText()));


        EditText ptIdET = findViewById(R.id.photoId);
        String ptId = String.valueOf(ptIdET.getText());



        String toastText = "AcId: "+grpAcId+" MemNm: "+grpMrNm+grpLMD+" pnLoan: "+grpPnLoan+" email:"+ email+phone+"photoId:"+ ptId;

        Toast ourSecondToast = Toast.makeText(this,toastText,Toast.LENGTH_LONG);
        ourSecondToast.show();
    }


}


