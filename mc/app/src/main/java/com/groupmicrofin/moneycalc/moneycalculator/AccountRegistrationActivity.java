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
import com.groupmicrofin.moneycalc.moneycalculator.db.MoneyCalcDbContract.AccountRegistrationEntry;
import com.groupmicrofin.moneycalc.moneycalculator.db.MoneyCalcDbContract;
import com.groupmicrofin.moneycalc.moneycalculator.db.MoneyCalcOpenHelper;
import com.groupmicrofin.moneycalc.moneycalculator.util.GroupDatabaseManager;


public class AccountRegistrationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_registration);
    }

    public void addAccount(View view) {
        String userId = "SYSTEM";//TODO

        AccountAddAsyncTask accountAddAsyncTask = new AccountAddAsyncTask();
        accountAddAsyncTask.execute(userId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private class AccountAddAsyncTask extends AsyncTask<String, Void, Long> {
        ContentValues accountData;

        @Override
        protected void onPreExecute() {
            //To Derive fields from user START
            EditText grpAcIdET = findViewById(R.id.groupAcId);
            String grpAcId = String.valueOf(grpAcIdET.getText());

            EditText grpMrNmET = findViewById(R.id.groupMemberName);
            String grpMrNm = String.valueOf(grpMrNmET.getText());

            EditText grpLMDET = findViewById(R.id.lastMeetDate);
            String grpLMD = String.valueOf(grpLMDET.getText());

            EditText emailET = findViewById(R.id.emailId);
            String email = String.valueOf(emailET.getText());

            EditText phoneET = findViewById(R.id.phoneNo);
            int phone = Integer.parseInt(String.valueOf(phoneET.getText()));

            EditText ptIdET = findViewById(R.id.photoId);
            String ptId = String.valueOf(ptIdET.getText());
            //To Derive fields from user END

            //Dynamic System Fields Logic START
            Bundle grpActBundle = getIntent().getExtras();
            Long groupId=0L;
            if (grpActBundle != null) {
                groupId = grpActBundle.getLong("grpId");
                //groupName = grpActBundle.getString("grpName");
                /*Toast itemToast = Toast.makeText(AccountActivity.this, "Group Created:"+groupId+":"+groupName, Toast.LENGTH_LONG);
                itemToast.show();*/
            }
            String user = "SYSTEM";
            String alertDateTime = grpLMD;//TODO derive from main application
            //Dynamic System Fields Logic END

            accountData = new ContentValues();
            accountData.put(AccountRegistrationEntry.COLMN_GROUP_ID, groupId);
            accountData.put(AccountRegistrationEntry.COLMN_SOC_AC_ID, grpAcId);
            accountData.put(AccountRegistrationEntry.COLMN_MEMBER_NM, grpMrNm);
            accountData.put(AccountRegistrationEntry.COLMN_EMAIL, email);
            accountData.put(AccountRegistrationEntry.COLMN_PHOTO_ID, ptId);
            accountData.put(AccountRegistrationEntry.COLMN_PHONE_NUM, phone);
            accountData.put(AccountRegistrationEntry.COLMN_LAST_MEET_DT, grpLMD);
            accountData.put(AccountRegistrationEntry.COLMN_ALERT_DTTM, alertDateTime);
            accountData.put(AccountRegistrationEntry.COLMN_USER, user);
        }

        @Override
        protected Long doInBackground(String... userID) {
            long result = 0;
            try {
                SQLiteOpenHelper dbHelper = new MoneyCalcOpenHelper(AccountRegistrationActivity.this);
                SQLiteDatabase dbMngr = dbHelper.getWritableDatabase();
                result = dbMngr.insert(AccountRegistrationEntry.TABLE_NAME, null, accountData);
                dbMngr.close();
            } catch (SQLiteException sqlEx) {
                result = 0;
            }
            return result;
        }

        @Override
        protected void onPostExecute(Long grpAcId) {
            String statusMsg = "";
            if (grpAcId > 0) {
                statusMsg = getString(R.string.suc_actAdd);
                Toast ourFirstToast = Toast.makeText(AccountRegistrationActivity.this, statusMsg, Toast.LENGTH_LONG);
                ourFirstToast.show();
                Intent actActivityIntent = new Intent(AccountRegistrationActivity.this, AccountActivity.class);
                startActivity(actActivityIntent);
            } else {
                statusMsg = getString(R.string.err_actAdd);
                Toast ourFirstToast = Toast.makeText(AccountRegistrationActivity.this, statusMsg, Toast.LENGTH_LONG);
                ourFirstToast.show();
            }
        }
    }

}
