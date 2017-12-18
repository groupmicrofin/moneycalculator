package com.groupmicrofin.moneycalc.moneycalculator;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
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
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.groupmicrofin.moneycalc.moneycalculator.db.MoneyCalcDbContract;
import com.groupmicrofin.moneycalc.moneycalculator.db.MoneyCalcOpenHelper;
import com.groupmicrofin.moneycalc.moneycalculator.util.GroupDatabaseManager;

import java.util.ArrayList;

import static android.widget.ListView.*;


public class AccountActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    private static final int LOADER_AC_REG = 0;
    int groupId = 0;
    String groupName = "";
    private MoneyCalcOpenHelper moneyCalcOpenHelper;
    private Cursor accountCursor;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        moneyCalcOpenHelper = new MoneyCalcOpenHelper(this);
        listView = findViewById(R.id.listAccounts);

        OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long acId) {
                String memberName = listView.getItemAtPosition(i).toString();
                String tostMsg = "Item Selected:" + i + ":" + groupId;
                Toast itemToast = Toast.makeText(AccountActivity.this, tostMsg, Toast.LENGTH_LONG);
                itemToast.show();
                Intent acActToMeetReg = new Intent(AccountActivity.this, meeting_Activity.class);
                acActToMeetReg.putExtra("member_name", memberName);
                acActToMeetReg.putExtra("grpId", groupId);
                acActToMeetReg.putExtra("acctId", acId);//TODO
                acActToMeetReg.putExtra("grpName", groupName);
                startActivity(acActToMeetReg);

            }
        };
        listView.setOnItemClickListener(onItemClickListener);

        Bundle grpActBundle = getIntent().getExtras();

        if (grpActBundle != null) {
            groupId = grpActBundle.getInt("grpId");
            groupName = grpActBundle.getString("grpName");
            Toast itemToast = Toast.makeText(AccountActivity.this, "Group Created:" + groupId + ":" + groupName, Toast.LENGTH_LONG);
            itemToast.show();
        }

        getLoaderManager().initLoader(LOADER_AC_REG, null, this);
    }

    public void onAccountAddClick(View view) {
        Intent actAddActIntent = new Intent(AccountActivity.this, AccountRegistrationActivity.class);
        actAddActIntent.putExtra("grpName", groupName);
        actAddActIntent.putExtra("grpId", groupId);
        startActivity(actAddActIntent);
    }


    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        CursorLoader cursorLoader = null;
        if (i == LOADER_AC_REG) {
            cursorLoader = getGrpupAcRegCL();
        }
        return cursorLoader;
    }

    private CursorLoader getGrpupAcRegCL() {
        CursorLoader cursorLoader = new CursorLoader(this) {
            public Cursor loadInBackground() {
                SQLiteDatabase db = moneyCalcOpenHelper.getReadableDatabase();
                String[] selectColumns = {MoneyCalcDbContract.AccountRegistrationEntry._ID,
                        MoneyCalcDbContract.AccountRegistrationEntry.COLMN_MEMBER_NM};
                return db.query(MoneyCalcDbContract.AccountRegistrationEntry.TABLE_NAME, selectColumns, null, null, null, null, MoneyCalcDbContract.AccountRegistrationEntry.COLMN_MEMBER_NM);
            }
        };

        return cursorLoader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor accountCursor) {
        if(loader.getId() == LOADER_AC_REG){
            loadGroupActivityList(accountCursor);
        }
    }

    private void loadGroupActivityList(Cursor accountCursor) {
        this.accountCursor = accountCursor;
        SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1,accountCursor,
                new String[]{MoneyCalcDbContract.AccountRegistrationEntry.COLMN_MEMBER_NM},
                new int[]{android.R.id.text1},0);
        listView.setAdapter(simpleCursorAdapter);

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        if (loader.getId() == LOADER_AC_REG) {
            if (accountCursor != null) {
                accountCursor.close();
            }
        }

    }
}


