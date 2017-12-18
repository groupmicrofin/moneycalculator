package com.groupmicrofin.moneycalc.moneycalculator;

import android.app.LoaderManager;
import android.content.Context;
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
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.groupmicrofin.moneycalc.moneycalculator.db.MoneyCalcDbContract.GroupRegistrationEntry;
import com.groupmicrofin.moneycalc.moneycalculator.db.MoneyCalcOpenHelper;
import com.groupmicrofin.moneycalc.moneycalculator.model.Society;
import com.groupmicrofin.moneycalc.moneycalculator.util.GroupDatabaseManager;

import java.security.acl.Group;
import java.util.ArrayList;

import static android.widget.ListView.*;

public class GroupActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    public static final int LOADER_GRP_REG = 0;

    private MoneyCalcOpenHelper moneyCalcOpenHelper;
    private Cursor groupCursor;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);

        moneyCalcOpenHelper = new MoneyCalcOpenHelper(this);
        listView = findViewById(R.id.listGroups);

        OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long groupId) {
                Cursor grpCur = (Cursor) adapterView.getItemAtPosition(i);
                String groupName = grpCur.getString(grpCur.getColumnIndex(GroupRegistrationEntry.COLMN_GROUP_NAME));
                int grpId = grpCur.getInt(grpCur.getColumnIndex(GroupRegistrationEntry._ID));
                String tostMsg = "Item Selected:" + i + ":" + groupId + ":GroupName:" + groupName;
                Toast itemToast = Toast.makeText(GroupActivity.this, tostMsg, Toast.LENGTH_SHORT);
                itemToast.show();

                Intent intentForAcctActivity = new Intent(GroupActivity.this, AccountActivity.class);
                intentForAcctActivity.putExtra("grpName", groupName);
                intentForAcctActivity.putExtra("grpId", grpId);
                startActivity(intentForAcctActivity);
            }
        };
        listView.setOnItemClickListener(onItemClickListener);

        getLoaderManager().initLoader(LOADER_GRP_REG, null, this);
    }

    public void onGroupAddClick(View view) {
        Intent grpRegActIntent = new Intent(GroupActivity.this, GroupRegistrationActivity.class);
        startActivity(grpRegActIntent);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        CursorLoader cursorLoader = null;
        if (i == LOADER_GRP_REG) {
            cursorLoader = getGrpupRegCL();
        }
        return cursorLoader;
    }

    private CursorLoader getGrpupRegCL() {
        CursorLoader cursorLoader = new CursorLoader(this) {
            public Cursor loadInBackground() {
                SQLiteDatabase db = moneyCalcOpenHelper.getReadableDatabase();

                String[] selectColumns = {GroupRegistrationEntry._ID,
                        GroupRegistrationEntry.COLMN_GROUP_NAME};
                return db.query(GroupRegistrationEntry.TABLE_NAME, selectColumns, null, null, null, null, GroupRegistrationEntry.COLMN_GROUP_NAME);
            }
        };

        return cursorLoader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor groupCursor) {
        if (loader.getId() == LOADER_GRP_REG) {
            loadGroupActivityList(groupCursor);
        }
    }

    private void loadGroupActivityList(Cursor groupCursor) {
        this.groupCursor = groupCursor;
        SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1, groupCursor,
                new String[]{GroupRegistrationEntry.COLMN_GROUP_NAME},
                new int[]{android.R.id.text1}, 0);
        listView.setAdapter(simpleCursorAdapter);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        if (loader.getId() == LOADER_GRP_REG) {
            if (groupCursor != null) {
                groupCursor.close();
            }
        }
    }

}
