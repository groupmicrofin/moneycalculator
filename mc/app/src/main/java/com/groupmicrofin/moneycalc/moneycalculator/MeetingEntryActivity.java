package com.groupmicrofin.moneycalc.moneycalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MeetingEntryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meeting_entry);
    }
    public void addMeeting(View view){

        EditText disBusET = findViewById(R.id.disbusmentAmount);
        double disBusAmt = Double.valueOf(String.valueOf(disBusET.getText()));

        EditText paidAmtET = findViewById(R.id.paidAmount);
        double paidAmount = Double.valueOf(String.valueOf(paidAmtET.getText()));

        EditText meetDtEt = findViewById(R.id.groupMeetingDate);
        String meetDate = String.valueOf(meetDtEt.getText());




        String toastText = "DisBus Amount: "+disBusAmt+" Total Paid Amount: "+paidAmount+" Meeting Date: "+meetDate;

        Toast ourFirstToast = Toast.makeText(this,toastText,Toast.LENGTH_LONG);
        ourFirstToast.show();

    }
}
