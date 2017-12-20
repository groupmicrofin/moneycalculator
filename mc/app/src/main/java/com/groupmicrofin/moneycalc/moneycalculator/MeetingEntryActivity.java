package com.groupmicrofin.moneycalc.moneycalculator;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.groupmicrofin.moneycalc.moneycalculator.db.MoneyCalcDbContract.MeetingRegEntry;
import com.groupmicrofin.moneycalc.moneycalculator.db.MoneyCalcDbContract.LoanMasterEntry;
import com.groupmicrofin.moneycalc.moneycalculator.db.MoneyCalcDbContract;
import com.groupmicrofin.moneycalc.moneycalculator.db.MoneyCalcOpenHelper;
import com.groupmicrofin.moneycalc.moneycalculator.util.GroupDatabaseManager;

public class MeetingEntryActivity extends AppCompatActivity {
    Long groupId = 0L;
    String grpName = "";
    String memberName = "";
    String accountId="";
    double loanDis=0;
    ContentValues meetingData;
    ContentValues loanNodata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meeting_entry);
    }

    public void addMeeting(View view) {
        String userId = "SYSTEM";//TODO
        EditText disBusET = findViewById(R.id.disbusmentAmount);
         loanDis = Double.valueOf(String.valueOf(disBusET.getText()));

        EditText paidAmtET = findViewById(R.id.paidAmount);
        double paidAmount = Double.valueOf(String.valueOf(paidAmtET.getText()));

        EditText meetDtEt = findViewById(R.id.groupMeetingDate);
        String meetDate = String.valueOf(meetDtEt.getText());

        Bundle grpMeetExt = getIntent().getExtras();
        if (grpMeetExt != null) {
            groupId = grpMeetExt.getLong("grpId");
            accountId=grpMeetExt.getString("acctId");
            meetingData = new ContentValues();
            meetingData.put(MeetingRegEntry.COLMN_GROUP_ID, groupId);
            meetingData.put(MeetingRegEntry.COLMN_SOC_AC_ID, accountId);
            meetingData.put(MeetingRegEntry.COLMN_LN_DIS_AMT, loanDis);
            meetingData.put(MeetingRegEntry.COLMN_PAID_AMT, paidAmount);
            meetingData.put(MeetingRegEntry.COLMN_MEETING_DT, meetDate);

            String toastText = "DisBus Amount: " + loanDis + " Total Paid Amount: " + paidAmount + " Meeting Date: " + meetDate;
            Toast ourFirstToast = Toast.makeText(this, toastText, Toast.LENGTH_LONG);
            ourFirstToast.show();


            MeetingAddAsyncTask accountAddAsyncTask = new MeetingAddAsyncTask();
            accountAddAsyncTask.execute(userId);
            if(loanDis>0){
                loanNodata = new ContentValues();
                loanNodata.put(LoanMasterEntry.COLMN_SOC_AC_ID,accountId);
                loanNodata.put(LoanMasterEntry.COLMN_LN_DIS_AMT,loanDis);
                loanNodata.put(LoanMasterEntry.COLMN_PENDING_PRI_LN,loanDis);
                loanNodata.put(LoanMasterEntry.COLMN_AC_STATUS,"1");
                loanNodata.put(LoanMasterEntry.COLMN_TOTAL_INT_PAID,"0");
                loanNodata.put(LoanMasterEntry.COLMN_DISBUS_DT,meetDate);
            }


        }
    }

    private class MeetingAddAsyncTask extends AsyncTask<String, Void, Long> {
        @Override
        protected void onPreExecute() {
        }

        @Override
        protected Long doInBackground(String... userID) {
            long result = 0;
            try {
                SQLiteOpenHelper dbHelper = new MoneyCalcOpenHelper(MeetingEntryActivity.this);
                SQLiteDatabase dbMngr = dbHelper.getWritableDatabase();
                result = dbMngr.insert(MeetingRegEntry.TABLE_NAME, null, meetingData);
                if(loanDis>0){
                    result = dbMngr.insert(LoanMasterEntry.TABLE_NAME, null, loanNodata);

                }
                dbMngr.close();

            } catch (SQLiteException sqlEx) {
                result = 0;
            }
            return result;
        }

        @Override
        protected void onPostExecute(Long groupAcId) {
            String statusMsg = "";
            if (groupAcId > 0) {
                statusMsg = getString(R.string.suc_MeetAdd);
                Toast ourFirstToast = Toast.makeText(MeetingEntryActivity.this, statusMsg, Toast.LENGTH_LONG);
                ourFirstToast.show();
                Intent actActivityIntent = new Intent(MeetingEntryActivity.this, meeting_Activity.class);
                startActivity(actActivityIntent);
            } else {
                statusMsg = getString(R.string.err_meetAdd);
                Toast ourFirstToast = Toast.makeText(MeetingEntryActivity.this, statusMsg, Toast.LENGTH_LONG);
                ourFirstToast.show();
            }
        }

    }
}
