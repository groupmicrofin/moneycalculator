package com.groupmicrofin.moneycalc.moneycalculator;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.groupmicrofin.moneycalc.moneycalculator.model.Society;
import com.groupmicrofin.moneycalc.moneycalculator.util.GroupDatabaseManager;

public class GroupRegistrationActivity extends AppCompatActivity {

    private SQLiteOpenHelper dbHelper;
    private SQLiteDatabase dbMngr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_registration);

        try {
            dbHelper = new GroupDatabaseManager(this);
            dbMngr = dbHelper.getWritableDatabase();
        } catch (SQLiteException sqlEx) {
            Toast ex = Toast.makeText(this, "Database UnAvailable", Toast.LENGTH_LONG);
            ex.show();
        }
    }

    public void registerGroup(View view) {
        String grpNameLabel = getString(R.string.grpName);

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

        Society group = new Society();
        group.setIntrestRate(grpIntRt);
        group.setShareAmount(grpShareAmt);
        group.setSocietyName(grpRegNm);
        group.setSocietyRefId(grpRegNo);
        group.setSocietyStartDate(grpSD);
        group.setScheduleFrequency("0 0 0 ? 0L");//TODO


        ContentValues groupData = new ContentValues();
        groupData.put("society_ref_no", group.getSocietyRefId());
        groupData.put("society_name", group.getSocietyName());
        groupData.put("society_start_dt", group.getSocietyStartDate());
        groupData.put("share_amount", group.getShareAmount());
        groupData.put("int_rate", group.getIntrestRate());
        groupData.put("schedule_frequency", group.getScheduleFrequency());

        long result = 0;
        result = dbMngr.insert("society_master", null, groupData);

        String statusMsg = "";
        if (result > 0) {
            statusMsg = "Group Added Successfully";
            Toast ourFirstToast = Toast.makeText(this, statusMsg, Toast.LENGTH_LONG);
            ourFirstToast.show();
            Intent grpActivityIntent = new Intent(GroupRegistrationActivity.this, GroupActivity.class);
            grpActivityIntent.putExtra("grpId",100);
            startActivity(grpActivityIntent);
        } else {
            statusMsg = "Failed Add of Group" + " ::" + group.toString();
            Toast ourFirstToast = Toast.makeText(this, statusMsg, Toast.LENGTH_LONG);
            ourFirstToast.show();
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        dbMngr.close();
    }

}


