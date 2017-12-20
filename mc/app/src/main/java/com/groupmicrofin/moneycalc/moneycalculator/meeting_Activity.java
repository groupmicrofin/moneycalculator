package com.groupmicrofin.moneycalc.moneycalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class meeting_Activity extends AppCompatActivity {
    Long groupId = 0L;
    String groupName = "";
    String memberName="";
    String accountId="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meeting_);
        TextView groupAcId = findViewById(R.id.groupAcId);
        groupAcId.setText("12");
        TextView totalMeetings  = findViewById(R.id.totalMeeting);
        totalMeetings.setText("12");
        TextView totalshare  = findViewById(R.id.totalShare);
        totalshare.setText("1200");
        TextView totalLoan  = findViewById(R.id.totalLoanAmt);
        totalLoan.setText("1000");
        TextView loanIntrest  = findViewById(R.id.loanAmtIntPaidAmt);
        loanIntrest.setText("10");
        TextView pendingLoan  = findViewById(R.id.pendingPrincilnAmt);
        pendingLoan.setText("1000");
        TextView amtNeedToClose  = findViewById(R.id.amtNeedToClAlLn);
        amtNeedToClose.setText("1010");
        TextView nxtMeetDate  = findViewById(R.id.nxtMeetDate);
        nxtMeetDate.setText("12/12/2017");
        TextView lstMeetDt  = findViewById(R.id.lastMeetDate);
        lstMeetDt.setText("12/11/2017");
        Bundle meetingBundle = getIntent().getExtras();

        if (meetingBundle != null) {

            groupId = meetingBundle.getLong("grpId");
            groupName = meetingBundle.getString("grpName");
            memberName = meetingBundle.getString("member_name");
            accountId = meetingBundle.getString("acctId");
            Toast itemToast = Toast.makeText(meeting_Activity.this, "Member Name:"+memberName, Toast.LENGTH_LONG);
            itemToast.show();
        }

    }

    public void clickOnaddMeeting(View view){
        Intent meetActToMeetReg = new Intent(meeting_Activity.this,MeetingEntryActivity.class);
        meetActToMeetReg.putExtra("member_name",memberName);
        meetActToMeetReg.putExtra("grpId",groupId);
        meetActToMeetReg.putExtra("grpName",groupName);
        meetActToMeetReg.putExtra("acctId",accountId);
        startActivity(meetActToMeetReg);

    }

}
