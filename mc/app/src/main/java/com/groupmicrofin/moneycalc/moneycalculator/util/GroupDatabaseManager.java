package com.groupmicrofin.moneycalc.moneycalculator.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by OMAR KOUL on 11/20/2017.
 */

public class GroupDatabaseManager extends SQLiteOpenHelper {

    public static final String DB_NAME="groupmoneycalc1";
    //public static final String DB_NAME=null;
    public static final int DB_VERSION = 1;

    public GroupDatabaseManager(Context context){
        super(context,DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDb) {
        String GROUP_REG_CREATE = "CREATE TABLE society_master (\n" +
                "  _id INTEGER  PRIMARY KEY AUTOINCREMENT,\n" +
                "  society_ref_no TEXT,\n" +
                "  society_name TEXT,\n" +
                "  society_start_dt TEXT,\n" +
                "  share_amount REAL,\n" +
                "  int_rate REAL,\n" +
                "  schedule_frequency TEXT,\n" +
                "  created_dttm TEXT,\n" +
                "  last_updated_dttm TEXT,\n" +
                "  user TEXT\n" +
                ");";

      sqLiteDb.execSQL(GROUP_REG_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

/*    @Override
    public void onDownGrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }*/
}