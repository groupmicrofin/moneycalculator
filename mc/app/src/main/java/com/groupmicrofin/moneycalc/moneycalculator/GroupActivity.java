package com.groupmicrofin.moneycalc.moneycalculator;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.groupmicrofin.moneycalc.moneycalculator.util.GroupDatabaseManager;

import static android.widget.ListView.*;

public class GroupActivity extends AppCompatActivity {

    private SQLiteOpenHelper dbHelper;
    private SQLiteDatabase dbMngr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);

        Bundle grpActBundle = getIntent().getExtras();
        if(grpActBundle!=null){
            int groupId = grpActBundle.getInt("grpId");
            Toast itemToast = Toast.makeText(GroupActivity.this, "Group Created:"+groupId, Toast.LENGTH_LONG);
            itemToast.show();
        }

        ListView listView = findViewById(R.id.listGroups);

        OnItemClickListener onItemClickListener =new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long groupId) {
                String tostMsg = "Item Selected:"+i+":"+groupId;

                Toast itemToast = Toast.makeText(GroupActivity.this, tostMsg, Toast.LENGTH_LONG);
                itemToast.show();
            }
        };

        listView.setOnItemClickListener(onItemClickListener);

        try {
            dbHelper = new GroupDatabaseManager(this);
            dbMngr = dbHelper.getReadableDatabase();

            Cursor grpDetailCur = dbMngr.query("society_master", new String[]{"_id", "society_name"}, null, null, null, null, null);

            CursorAdapter grpActCurAd = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1, grpDetailCur, new String[]{"society_name"}, new int[]{android.R.id.text1}, 0);
            listView.setAdapter(grpActCurAd);

            //grpDetailCur.close();
        } catch (SQLiteException sqlEx) {
            Toast ex = Toast.makeText(this, "Database UnAvailable", Toast.LENGTH_LONG);
            ex.show();
        }


    }

    public void onGroupAddClick(View view) {
        Intent grpRegActIntent = new Intent(GroupActivity.this, GroupRegistrationActivity.class);
        startActivity(grpRegActIntent);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if(dbMngr!=null) {
            dbMngr.close();
        }
    }

}
