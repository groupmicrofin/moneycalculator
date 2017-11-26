package com.groupmicrofin.moneycalc.moneycalculator;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;
import com.groupmicrofin.moneycalc.moneycalculator.util.GroupDatabaseManager;
import java.security.acl.Group;
import java.util.ArrayList;
import static android.widget.ListView.*;

public class GroupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);

        final ListView listView = findViewById(R.id.listGroups);
        OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long groupId) {
                String groupName = listView.getItemAtPosition(i).toString();
                String tostMsg = "Item Selected:" + i + ":" + groupId +":GroupName:"+groupName;
                Toast itemToast = Toast.makeText(GroupActivity.this, tostMsg, Toast.LENGTH_SHORT);
                itemToast.show();

                Intent intentForAcctActivity = new Intent(GroupActivity.this, AccountActivity.class);
                intentForAcctActivity.putExtra("grpName",groupName);
                intentForAcctActivity.putExtra("grpId",groupId);
                startActivity(intentForAcctActivity);
            }
        };
        listView.setOnItemClickListener(onItemClickListener);

        Bundle grpActBundle = getIntent().getExtras();
        Long groupId = 0L;
        if (grpActBundle != null) {
            groupId = grpActBundle.getLong("grpId");
            /*Toast itemToast = Toast.makeText(GroupActivity.this, "Group Created:"+groupId, Toast.LENGTH_LONG);
            itemToast.show();*/
        }

        GrpActFetchGroups grpActFetchGroups = new GrpActFetchGroups();
        grpActFetchGroups.execute(groupId);

    }

    public void onGroupAddClick(View view) {
        Intent grpRegActIntent = new Intent(GroupActivity.this, GroupRegistrationActivity.class);
        startActivity(grpRegActIntent);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private class GrpActFetchGroups extends AsyncTask<Long, Void, String[]> {
        @Override
        protected void onPreExecute() {
        }

        @Override
        protected String[] doInBackground(Long... grpId) {
            Cursor grpDetailCur = null;
            ArrayList<String> accounts = new ArrayList<>();

            try {
                SQLiteOpenHelper dbHelper;
                SQLiteDatabase dbMngr;
                dbHelper = new GroupDatabaseManager(GroupActivity.this);
                dbMngr = dbHelper.getReadableDatabase();

                grpDetailCur = dbMngr.query("society_master", new String[]{"_id", "society_name"}, null, null, null, null, null);

                while (grpDetailCur.moveToNext()) {
                    accounts.add(grpDetailCur.getString(1));
                }

                grpDetailCur.close();
                if (dbMngr != null) {
                    dbMngr.close();
                }
            } catch (SQLiteException sqlEx) {
            }

            String[] grpAccounts = new String[accounts.size()];
            grpAccounts = accounts.toArray(grpAccounts);

            return grpAccounts;
        }

        protected void onPostExecute(String[] result) {
            if (result == null) {
                String errMsg = getString(R.string.err_db);
                Toast ex = Toast.makeText(GroupActivity.this, errMsg, Toast.LENGTH_LONG);
                ex.show();
            } else {
                ListView listView = findViewById(R.id.listGroups);
                ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(GroupActivity.this, android.R.layout.simple_list_item_1, result);
                //CursorAdapter grpActCurAd = new SimpleCursorAdapter(GroupActivity.this, android.R.layout.simple_list_item_1, result, new String[]{"society_name"}, new int[]{android.R.id.text1}, 0);
                listView.setAdapter(listAdapter);
                //result.close();
            }
        }
    }

}
