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
import android.widget.ListView;
import android.widget.Toast;

import com.groupmicrofin.moneycalc.moneycalculator.util.GroupDatabaseManager;

import java.util.ArrayList;

import static android.widget.ListView.*;


public class AccountActivity extends AppCompatActivity {
    Long groupId = 0L;
    String groupName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        ListView listView = findViewById(R.id.listAccounts);
        OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long groupId) {
                String tostMsg = "Item Selected:" + i + ":" + groupId;
                Toast itemToast = Toast.makeText(AccountActivity.this, tostMsg, Toast.LENGTH_LONG);
                itemToast.show();
            }
        };
        listView.setOnItemClickListener(onItemClickListener);

        Bundle grpActBundle = getIntent().getExtras();

        if (grpActBundle != null) {
            groupId = grpActBundle.getLong("grpId");
            groupName = grpActBundle.getString("grpName");
            Toast itemToast = Toast.makeText(AccountActivity.this, "Group Created:"+groupId+":"+groupName, Toast.LENGTH_LONG);
            itemToast.show();
        }

        AcctListFetchAsyncTask acctListFetchAsyncTask = new AcctListFetchAsyncTask();
        acctListFetchAsyncTask.execute(groupId);

    }

    public void onAccountAddClick(View view) {
        Intent actAddActIntent = new Intent(AccountActivity.this, AccountRegistrationActivity.class);
        actAddActIntent.putExtra("grpName",groupName);
        actAddActIntent.putExtra("grpId",groupId);
        startActivity(actAddActIntent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }


    private class AcctListFetchAsyncTask extends AsyncTask<Long, Void, String[]> {

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
                dbHelper = new GroupDatabaseManager(AccountActivity.this);
                dbMngr = dbHelper.getReadableDatabase();
                //TODO to handle per GROUP Vise
                grpDetailCur = dbMngr.query("account_masters", new String[]{"_id", "member_name"}, null, null, null, null, null);

                while (grpDetailCur.moveToNext()) {
                    accounts.add(grpDetailCur.getString(1));
                }

                grpDetailCur.close();
                if (dbMngr != null) {
                    dbMngr.close();
                }
            } catch (SQLiteException sqlEx) {
                /*Toast ex = Toast.makeText(AccountActivity.this, "Failed Adding Account", Toast.LENGTH_LONG);
                ex.show();*/
            }

            String[] grpAccounts = new String[accounts.size()];
            grpAccounts = accounts.toArray(grpAccounts);

            return grpAccounts;
        }

        protected void onPostExecute(String[] result) {
            if (result == null) {
                String errMsg = getString(R.string.err_actAdd);
                Toast ex = Toast.makeText(AccountActivity.this, errMsg, Toast.LENGTH_LONG);
                ex.show();
            } else if(result.length > 0) {
                ListView listView = findViewById(R.id.listAccounts);
                ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(AccountActivity.this, android.R.layout.simple_list_item_1, result);
                listView.setAdapter(listAdapter);
            }
        }
    }

}
