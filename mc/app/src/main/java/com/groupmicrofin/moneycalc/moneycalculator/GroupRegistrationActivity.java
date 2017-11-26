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
import com.groupmicrofin.moneycalc.moneycalculator.util.GroupDatabaseManager;

public class GroupRegistrationActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_registration);
    }

    public void registerGroup(View view) {
        String userId = "SYSTEM";//TODO
        GroupRegAsyncTask groupRegAsyncTask = new GroupRegAsyncTask();
        groupRegAsyncTask.execute(userId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    private class GroupRegAsyncTask extends AsyncTask<String, Void, Long> {
        ContentValues groupData;

        @Override
        protected void onPreExecute() {
            EditText grpRegET = findViewById(R.id.groupRegNo);
            String grpRegNo = String.valueOf(grpRegET.getText());

            EditText grpNmET = findViewById(R.id.groupName);
            String grpRegNm = String.valueOf(grpNmET.getText());

            EditText grpSDET = findViewById(R.id.groupStartDate);
            String grpSD = String.valueOf(grpSDET.getText());

            EditText grpShareAmtET = findViewById(R.id.shareAmount);
            double grpShareAmt = Double.parseDouble(String.valueOf(grpShareAmtET.getText()));

            EditText grpIntRtET = findViewById(R.id.interestRate);
            double grpIntRt = Double.valueOf(String.valueOf(grpIntRtET.getText()));

            String grpScheduleFreq = "0 0 0 0 0L";

            groupData = new ContentValues();
            groupData.put("society_ref_no", grpRegNo);
            groupData.put("society_name", grpRegNm);
            groupData.put("society_start_dt", grpSD);
            groupData.put("share_amount", grpShareAmt);
            groupData.put("int_rate", grpIntRt);
            groupData.put("schedule_frequency", grpScheduleFreq);
        }

        @Override
        protected Long doInBackground(String... userID) {
            long result = 0;
            try {
                SQLiteOpenHelper dbHelper = new GroupDatabaseManager(GroupRegistrationActivity.this);
                SQLiteDatabase dbMngr = dbHelper.getWritableDatabase();
                result = dbMngr.insert("society_master", null, groupData);
                dbMngr.close();
            } catch (SQLiteException sqlEx) {
                result = 0;
            }
            return result;
        }

        @Override
        protected void onPostExecute(Long groupId) {
            String statusMsg = "";
            if (groupId > 0) {
                statusMsg = getString(R.string.suc_grpAdd);
                Toast ourFirstToast = Toast.makeText(GroupRegistrationActivity.this, statusMsg, Toast.LENGTH_LONG);
                ourFirstToast.show();
                Intent grpActivityIntent = new Intent(GroupRegistrationActivity.this, GroupActivity.class);
                grpActivityIntent.putExtra("grpId", groupId);
                startActivity(grpActivityIntent);



            } else {
                statusMsg = getString(R.string.err_grpAdd);
                Toast ourFirstToast = Toast.makeText(GroupRegistrationActivity.this, statusMsg, Toast.LENGTH_LONG);
                ourFirstToast.show();
            }
        }
    }

}



