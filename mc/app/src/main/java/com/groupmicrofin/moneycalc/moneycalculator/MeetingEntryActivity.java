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

import com.groupmicrofin.moneycalc.moneycalculator.db.MoneyCalcOpenHelper;
import com.groupmicrofin.moneycalc.moneycalculator.util.GroupDatabaseManager;

public class MeetingEntryActivity extends AppCompatActivity {
    Long groupId = 0L;
    String grpName = "";
    String memberName = "";
    ContentValues meetingData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meeting_entry);
    }

    public void addMeeting(View view) {
        String userId = "SYSTEM";//TODO
        EditText disBusET = findViewById(R.id.disbusmentAmount);
        double disBusAmt = Double.valueOf(String.valueOf(disBusET.getText()));

        EditText paidAmtET = findViewById(R.id.paidAmount);
        double paidAmount = Double.valueOf(String.valueOf(paidAmtET.getText()));

        EditText meetDtEt = findViewById(R.id.groupMeetingDate);
        String meetDate = String.valueOf(meetDtEt.getText());

        Bundle grpMeetExt = getIntent().getExtras();
        if (grpMeetExt != null) {
            groupId = grpMeetExt.getLong("grpId");
            meetingData = new ContentValues();
            meetingData.put("society_master_id", groupId);
            //smeetingData.put("account_master_id", grpAcId);
            meetingData.put("loan_disburse", disBusAmt);
            meetingData.put("paid_Amount", paidAmount);
            meetingData.put("meeting_dt", meetDate);

            String toastText = "DisBus Amount: " + disBusAmt + " Total Paid Amount: " + paidAmount + " Meeting Date: " + meetDate;
            Toast ourFirstToast = Toast.makeText(this, toastText, Toast.LENGTH_LONG);
            ourFirstToast.show();

            MeetingAddAsyncTask accountAddAsyncTask = new MeetingAddAsyncTask();
            accountAddAsyncTask.execute(userId);



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
                result = dbMngr.insert("meeting_nobook", null, meetingData);
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
